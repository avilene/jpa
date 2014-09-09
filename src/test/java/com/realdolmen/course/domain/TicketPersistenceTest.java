package com.realdolmen.course.domain;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TicketPersistenceTest extends PersistenceTest {

    @Test
    public void createTicket(){
        Ticket t = new Ticket(BigDecimal.valueOf(3.99),new Date(),"AMS");
        entityManager().persist(t);
        assertNotNull(t.getId());
    }

    @Test
    public void retrieveTicketById(){
        Ticket ticket = new Ticket(BigDecimal.valueOf(3.99),new Date(),"AMS");
        entityManager().persist(ticket);
        entityManager().flush();
        entityManager().clear();
        Ticket ticketById = entityManager().find(Ticket.class, ticket.getId());
        assertNotNull(ticketById);
    }

    @Test
    public void updateTicket(){
        Ticket startTicket = new Ticket(BigDecimal.valueOf(3.99),new Date(),"AMS");
        entityManager().persist(startTicket);
        startTicket.setDestination("BRU");
        entityManager().flush();
        entityManager().clear();
        Ticket updatedTicket = entityManager().find(Ticket.class, startTicket.getId());
        assertEquals("BRU",updatedTicket.getDestination());
    }
}