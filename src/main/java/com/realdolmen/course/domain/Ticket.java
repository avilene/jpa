package com.realdolmen.course.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal price;
    private Date dateOfDeparture;
    private String destination;

    @ManyToOne
    private Passenger passenger;

    @ManyToOne
    private Flight flight;


    public Ticket(BigDecimal price, Date dateOfDeparture, String destination) {
        this.price = price;
        this.dateOfDeparture = dateOfDeparture;
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    public String getDestination() {
        return destination;
    }

    protected Ticket() {
    }

    public void setPassenger(Passenger passenger){
        this.passenger = passenger;
        passenger.addTicket(this);
    }

    public Passenger getPassenger() {
        return passenger;
    }
}
