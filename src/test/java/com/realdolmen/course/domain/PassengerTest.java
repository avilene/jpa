package com.realdolmen.course.domain;

import org.junit.Assert;
import org.junit.Test;

import javax.persistence.FlushModeType;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PassengerTest extends DataSetPersistenceTest {


    private Passenger createPassenger() {
        Date dateOfBirth = null;
        Date lastFlight = null;
        try {
            dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse("1990-06-29");
            lastFlight = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-09-02 13:15:43");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Address address = new Address("Test","","Ergens","1337","L33TT0WN");
        List<String> preferences = new ArrayList<>();
        preferences.add("Testje");
        return new Passenger("654654654", "Aveline", "Estié", 846, dateOfBirth, PassengerType.REGULAR, lastFlight, address, preferences);
    }

    @Test
    public void retrievableById() {
        Passenger foundPassenger = entityManager().find(Passenger.class, 1L);
        Assert.assertNotNull(foundPassenger.getId());
    }


    @Test
    public void doesUpdate() {
        Passenger passenger = entityManager().find(Passenger.class, 1L);
        entityManager().detach(passenger);
        passenger.setFirstName("Bart");
        entityManager().merge(passenger);
        entityManager().flush();
        assertEquals("Bart", passenger.getFirstName());
    }

    @Test
    public void doesDelete() {
        Passenger passenger = entityManager().find(Passenger.class, 1L);
        entityManager().remove(passenger);
        entityManager().flush();
        Passenger foundPassenger = entityManager().find(Passenger.class, 1L);
        Assert.assertNull(foundPassenger);
    }

    @Test
    public void canBeRefreshed() {
        entityManager().setFlushMode(FlushModeType.COMMIT);
        Passenger passenger = entityManager().find(Passenger.class, 1L);
        passenger.setFirstName("Dieter");
        entityManager().refresh(passenger);
        assertEquals("Aveline", passenger.getFirstName());
    }

    @Test
    public void canBePersisted() {
        Passenger passenger = createPassenger();
        entityManager().persist(passenger);
        Assert.assertNotNull(passenger.getId());
    }

    @Test
    public void ageIsCalculated() {
        Passenger passenger = entityManager().find(Passenger.class, 1L);
        assertEquals(24, passenger.getAge());
    }

    @Test
    public void lastUpdatedIsGenerated() {
        Passenger passenger = createPassenger();
        entityManager().persist(passenger);
        Date firstDate = passenger.getDateLastUpdated();
        passenger.setFirstName("Dieter");
        entityManager().flush();
        Date secondDate = passenger.getDateLastUpdated();
        Assert.assertTrue((firstDate.getTime() < secondDate.getTime()));
    }

    @Test
    public void canAddTicket(){
        Passenger passenger = entityManager().find(Passenger.class, 1L);
        Ticket t = new Ticket(BigDecimal.valueOf(399.00),new Date(),"AMS");
        passenger.addTicket(t);
        assertEquals(2, passenger.getTickets().size());
    }



}
