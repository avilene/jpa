package com.realdolmen.course.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {

    public static enum Genre {
        Fantasy, Thriller
    }

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String author;

    /**
     * Used by JPA.
     */
    protected Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
