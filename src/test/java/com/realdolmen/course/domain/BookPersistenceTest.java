package com.realdolmen.course.domain;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Aveline Estié on 8/09/2014.
 */
public class BookPersistenceTest extends PersistenceTest{

    @Test
    public void booksCanBePersisted() throws Exception {
        Book book = new Book("Animal Farm", "George Orwell");
        entityManager().persist(book);
        assertNotNull(book.getId());
    }
}
