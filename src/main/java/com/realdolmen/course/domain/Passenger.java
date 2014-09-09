package com.realdolmen.course.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
@NamedQueries({
    @NamedQuery(name="Passenger.findAll", query="SELECT p from Passenger p"),
    @NamedQuery(name="Passenger.findById", query="SELECT p from Passenger p WHERE p.id = :id")
})
public class Passenger {
    @Transient
    private Logger logger = LoggerFactory.getLogger(Passenger.class);
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, updatable = false)
    private String ssn;

    @Column(length=50)
    private String firstName;

    @Column(length=50)
    private String lastName;

    private int frequentFlyerMiles;

    @Basic(fetch = FetchType.LAZY)
    private byte[] picture;

    @Column(nullable = false)
    private PassengerType type;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date dateofBirth;

    @Transient
    private int age;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastFlight;

    private Date dateLastUpdated;

    @PrePersist
    @PreUpdate
    private void generateCreationDate(){
        logger.trace("Generating creation date for entity with name " + firstName + " " + lastName);
        this.dateLastUpdated = new Date();
    }

    public Passenger(String ssn, String firstName, String lastName,int frequentFlyerMiles, byte[] picture, PassengerType type, Date dateofBirth, Date lastFlight) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.frequentFlyerMiles = frequentFlyerMiles;
        this.picture = picture;
        this.type = type;
        this.dateofBirth = dateofBirth;
        this.lastFlight = lastFlight;
    }

    /*
     * Used by JPA.
     */
    public Passenger() {
    }

    public PassengerType getType() {
        return type;
    }

    public Date getDateLastUpdated() {
        return dateLastUpdated;
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

    public byte[] getPicture() {
        return picture;
    }

    public Date getDateofBirth() {
        return dateofBirth;
    }

    public Date getLastFlight() {
        return lastFlight;
    }

    public String toString(){
        return "Passenger: " + this.getId() + " - " + this.getFirstName() + " " + this.getLastName() + " - " + this.getSsn() + " - " + this.getFrequentFlyerMiles() + " - " + this.getPicture().toString() + " - " + this.getDateofBirth().toString() + " ("+this.getAge()+") - " + this.getLastFlight().toString() + " - " + this.getType();
    }

    public int getAge(){
        this.age = calculateAge();
        return this.age;
    }

    private int calculateAge(){
        LocalDate now = LocalDate.now();
        LocalDate birthday =  dateofBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int calcYear = now.getYear() - birthday.getYear();
        if(now.getMonthValue() < birthday.getMonthValue()){
            calcYear--;
        }else if(now.getMonthValue() == birthday.getMonthValue() && now.getDayOfMonth() < birthday.getDayOfMonth()){
            calcYear--;
        }
        return calcYear;
    }
}
