package com.realdolmen.course.domain;

import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class PassengerPersistenceTest extends PersistenceTest {

    private Passenger createPassenger(){
        LocalDate dob = LocalDate.of(1990,6,29);
        Date dateOfBirth = Date.from(dob.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Passenger p = new Passenger("900629","Aveline","Estié",1500,new byte[5],PassengerType.OCCASIONAL, dateOfBirth, new Date());
        return p;
    }
    @Test
    public void passengerHasBeenPersisted() throws Exception {
        LocalDate dob = LocalDate.of(1990,6,29);
        Date dateOfBirth = Date.from(dob.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Passenger p = new Passenger("900629","Aveline","Estié",1500,new byte[5],PassengerType.OCCASIONAL, dateOfBirth, new Date());
        System.out.println(p.toString());
        entityManager().persist(p);
        assertNotNull(p.getId());
    }

    @Test
    public void ageIsCorrectlyCalculated() throws Exception{
        // june 29, 1990 - turned 24
        Passenger p = createPassenger();
        System.out.println(p.getAge() + "");
        assertSame(24, p.getAge());

        // december 31, 1990 - didn't turn 24 yet
        LocalDate dob = LocalDate.of(1990,12,31);
        Date dateOfBirth = Date.from(dob.atStartOfDay(ZoneId.systemDefault()).toInstant());
        p = new Passenger("900629","Aveline","Estié",1500,new byte[5],PassengerType.OCCASIONAL, dateOfBirth, new Date());
        System.out.println(p.getAge() + "");
        assertSame(23, p.getAge());
    }

    @Test
    public void timestampIsUpdated() throws Exception{
        Passenger p = createPassenger();
        assertSame(null, p.getDateLastUpdated());
        entityManager().persist(p);
        assertNotNull(p.getDateLastUpdated());
    }
}
