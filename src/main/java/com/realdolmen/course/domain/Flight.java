package com.realdolmen.course.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Flight {

    @Id
    private int id;

    private String number;

    @Temporal(TemporalType.TIME)
    private Date departureTime;

    @Temporal(TemporalType.TIME)
    private Date arrivalTime;

    @OneToMany(mappedBy="flight")
    private List<Ticket> tickets;

    @Transient
    private List<Passenger> passengers;

    public Flight() {
    }

    public Flight(String number, Date departureTime, Date arrivalTime) {
        this.number = number;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public List<Passenger> getPassengers(){
        List<Passenger> retrievedPassengers = new ArrayList<Passenger>();
        for(Ticket ticket : tickets){
            retrievedPassengers.add(ticket.getPassenger());
        }
        this.passengers = retrievedPassengers;
        return retrievedPassengers;
    }
}
