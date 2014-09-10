package com.realdolmen.course.domain;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlightTest extends DataSetPersistenceTest {

    @Test
    public void canRetrievePassengers(){
        Flight flight = entityManager().find(Flight.class,1);
        List<Passenger> passengers = flight.getPassengers();
        assertEquals(1, passengers.size());
        for(Passenger passenger : passengers){
            System.out.println(passenger.getFirstName() + " " + passenger.getLastName());
        }
    }
}
