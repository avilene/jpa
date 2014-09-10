package com.realdolmen.course;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.PassengerType;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.FlushModeType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JUZAU33 on 8/09/2014.
 */
public class PassengerTest extends DataSetPersistenceTest {


    private Passenger createPassenger() {
        Date dateOfBirth = null;
        Date lastFlight = null;
        try {
            dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse("1992-08-06");
            lastFlight = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-09-02 13:15:43");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return new Passenger("654654654", "Joren", "Uitzetter", 846, dateOfBirth, PassengerType.REGULAR, lastFlight);
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

        Assert.assertEquals("Bart", passenger.getFirstName());

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

        passenger.setFirstName("Bart");

        entityManager().refresh(passenger);

        Assert.assertEquals("Joren", passenger.getFirstName());
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


        Assert.assertEquals(22, passenger.getAge());
    }

    @Test
    public void lastUpdatedIsGenerated() {



        Passenger passenger = createPassenger();

        entityManager().persist(passenger);

        Date firstDate = passenger.getDateLastUpdated();

        passenger.setFirstName("Test");

        entityManager().flush();

        Date secondDate = passenger.getDateLastUpdated();

        Assert.assertTrue((firstDate.getTime() < secondDate.getTime()));
    }


}
