package com.realdolmen.course.domain;

import javax.persistence.*;

@Entity
public class Book {
    public enum Genre {
        BIOGRAPHY, FANTASY, THRILLER
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    private String author;

    @Enumerated(EnumType.STRING)
    private Genre genre;

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

    public Genre getGenre() {
        return genre;
    }

    /*
     * Used by JPA.
     */
    protected Book() {
    }
}
