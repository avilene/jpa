package com.realdolmen.course.domain;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceTest {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;
    private static final Logger logger = LoggerFactory.getLogger(PersistenceTest.class);

    @BeforeClass
    public static void initializeEntityManagerFactory() {
        logger.info("Creating EntityManagerFactory");
        entityManagerFactory = Persistence.createEntityManagerFactory("GPU");
    }

    @Before
    public void initialize() {
        logger.info("Creating transacted EntityManager");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @After
    public void destroy() {
        logger.info("Committing and closing transacted EntityManager");
        if (transaction != null) transaction.commit();
        if (entityManager != null) entityManager.close();
    }

    @AfterClass
    public static void destroyEntityManagerFactory() {
        if (entityManagerFactory != null) entityManagerFactory.close();
    }

    protected EntityManager entityManager() {
        return this.entityManager;
    }



}
