package com.realdolmen.course.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private int id;

    private BigDecimal price;

    @Temporal(TemporalType.DATE)
    private Date dateOfDeparture;

    private String destination;

    public Ticket() {
    }

    public Ticket(BigDecimal price, Date dateOfDeparture, String destination) {
        this.price = price;
        this.dateOfDeparture = dateOfDeparture;
        this.destination = destination;
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

    public int getId() {
        return id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
