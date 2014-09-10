package com.realdolmen.course.domain;

import javax.persistence.*;
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

    public Flight() {
    }

    public Flight(String number, Date departureTime, Date arrivalTime) {
        this.number = number;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}
