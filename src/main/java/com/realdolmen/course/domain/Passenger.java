package com.realdolmen.course.domain;

import org.hibernate.annotations.Cascade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Passenger {

    @Transient
    private Logger logger = LoggerFactory.getLogger(Passenger.class);

    @Id
    @GeneratedValue
    private Long id;

    private String ssn;
    private String firstName;
    private String lastName;
    private int frequentFlyerMiles;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Transient
    private int age;
    @Enumerated(EnumType.STRING)
    private PassengerType passengerType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastFlight;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastUpdated;

    @OneToMany(mappedBy = "passenger",cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Ticket> tickets = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "preferences", joinColumns = @JoinColumn(name="passengerId"))
    private List<String> preferences;

    @Embedded
    private Address address;

    // Constructors
    public Passenger(String ssn, String firstName, String lastName, int frequentFlyerMiles, Date dateOfBirth, PassengerType passengerType, Date lastFlight, Address address, List<String> preferences) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.frequentFlyerMiles = frequentFlyerMiles;
        this.dateOfBirth = dateOfBirth;
        this.passengerType = passengerType;
        this.lastFlight = lastFlight;
        this.address = address;
        this.preferences = preferences;
    }

    protected Passenger() {
    }

    @PrePersist
    @PreUpdate
    private void generateNewTimestamp() {
        logger.info("Generating creation date for entity with name " +
                firstName + " " + lastName);
        this.dateLastUpdated = new Date();
    }

    public Long getId() {
        return id;
    }

    public String getSsn() {
        return ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getFrequentFlyerMiles() {
        return frequentFlyerMiles;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        Calendar calender1 = Calendar.getInstance();
        Calendar calender2 = Calendar.getInstance();
        calender1.setTime(this.getDateOfBirth());
        calender2.setTime(new Date());

        return calender2.get(Calendar.YEAR) - calender1.get(Calendar.YEAR);
    }

    public Date getDateLastUpdated() {
        return dateLastUpdated;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public Date getLastFlight() {
        return lastFlight;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public Address getAddress() {
        return address;
    }

    public void addPreference(String preference){
        this.preferences.add(preference);
    }
}
