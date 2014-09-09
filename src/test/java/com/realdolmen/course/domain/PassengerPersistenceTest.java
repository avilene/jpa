package com.realdolmen.course.domain;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.*;

public class PassengerPersistenceTest extends PersistenceTest {

    private Passenger createPassenger(){
        LocalDate dob = LocalDate.of(1990,6,29);
        Date dateOfBirth = Date.from(dob.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return new Passenger("900629","Aveline","Estié",(int)Math.floor(Math.random() * 5000),new byte[5],PassengerType.OCCASIONAL, dateOfBirth, new Date());
    }
    @Test
    public void passengerHasBeenCreated() throws Exception {
        Passenger p = createPassenger();
        entityManager().persist(p);
        assertNotNull(p.getId());
    }

    @Test
    public void ageIsCorrectlyCalculated() throws Exception{
        // june 29, 1990 - turned 24
        Passenger p = createPassenger();
        assertSame(24, p.getAge());

        // december 31, 1990 - didn't turn 24 yet
        LocalDate dob = LocalDate.of(1990,12,31);
        Date dateOfBirth = Date.from(dob.atStartOfDay(ZoneId.systemDefault()).toInstant());
        p = new Passenger("900629","Aveline","Estié",1,new byte[5],PassengerType.OCCASIONAL, dateOfBirth, new Date());
        assertSame(23, p.getAge());
    }

    @Test
    public void timestampIsUpdated() throws Exception{
        Passenger p = createPassenger();
        assertSame(null, p.getDateLastUpdated());
        entityManager().persist(p);
        assertNotNull(p.getDateLastUpdated());
    }

    @Test
    public void retrievePassengerById(){
        Passenger p = createPassenger();
        entityManager().persist(p);
        entityManager().flush();
        entityManager().clear();
        Passenger passengerById = entityManager().find(Passenger.class, p.getId());
        assertNotNull(passengerById);
    }

    @Test
    public void updatePassenger(){
        Passenger p = createPassenger();
        entityManager().persist(p);
        p.setFirstName("Aafke");
        entityManager().flush();
        entityManager().clear();
        Passenger updatedPassenger = entityManager().find(Passenger.class, p.getId());
        assertEquals("Aafke",updatedPassenger.getFirstName());
    }

    @Test
    public void deletePassenger(){
        // create ticket
        Passenger p = createPassenger();
        entityManager().persist(p);
        entityManager().flush();
        entityManager().clear();

        // retrieve & delete ticket
        Passenger passengerToDelete = entityManager().find(Passenger.class, p.getId());
        entityManager().remove(passengerToDelete);
        entityManager().flush();
        entityManager().clear();

        Passenger passengerToFind = entityManager().find(Passenger.class, p.getId());
        assertNull(passengerToFind);
    }
}
