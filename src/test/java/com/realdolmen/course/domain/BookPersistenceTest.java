package com.realdolmen.course.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JUZAU33 on 8/09/2014.
 */
public class BookPersistenceTest extends PersistenceTest {

    @Test
    public void bookCanBePersisted() throws Exception {
        Book book = new Book("Animal Farm", "George Orwell");
        entityManager().persist(book);
        assertNotNull(book.getId());
    }
}
