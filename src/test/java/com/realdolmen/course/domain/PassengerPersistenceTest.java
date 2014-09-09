package com.realdolmen.course.domain;

import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class PassengerPersistenceTest extends PersistenceTest {

    @Test
    public void passengerHasBeenPersisted() throws Exception {
        // is voor Embeddable primary key
        //PassengerId id = new PassengerId("900629","Estié");
        //Passenger p = new Passenger(id,"Aveline",1500,new byte[5]);
        //Generate a date
        LocalDate dob = LocalDate.of(1990,6,29);
        Date dateOfBirth = Date.from(dob.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Passenger p = new Passenger("900629","Aveline","Estié",1500,new byte[5],PassengerType.OCCASIONAL, dateOfBirth, new Date());
        System.out.println(p.toString());
        entityManager().persist(p);
        assertNotNull(p.getId());
    }

    @Test
    public void ageIsCorrectlyCalculated() throws Exception{
        LocalDate dob = LocalDate.of(1990,6,29);
        Date dateOfBirth = Date.from(dob.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Passenger p = new Passenger("900629","Aveline","Estié",1500,new byte[5],PassengerType.OCCASIONAL, dateOfBirth, new Date());
        System.out.println(p.getAge() + "");
        assertSame(24, p.getAge());

        dob = LocalDate.of(1990,9,30);
        dateOfBirth = Date.from(dob.atStartOfDay(ZoneId.systemDefault()).toInstant());
        p = new Passenger("900629","Aveline","Estié",1500,new byte[5],PassengerType.OCCASIONAL, dateOfBirth, new Date());
        System.out.println(p.getAge() + "");
        assertSame(23, p.getAge());
    }
}
