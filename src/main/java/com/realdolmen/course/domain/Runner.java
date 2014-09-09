package com.realdolmen.course.domain;

import javax.persistence.*;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        System.out.println("Runner is running");
        /*EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("GPU");

            entityManager = entityManagerFactory.createEntityManager();

            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            // date invoegen
            Passenger p = new Passenger("900629","Aveline","Estié",1500,new byte[5]);
            entityManager.persist(p);
            p = new Passenger("880501","Dieter","Bocklandt",1499,new byte[5]);
            entityManager.persist(p);

            Query namedQuery = entityManager.createNamedQuery("Passenger.findAll");
            List<Passenger> resultList = namedQuery.getResultList();
            for(Passenger pass : resultList){
                System.out.println(pass.toString());
            }
            transaction.commit();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            if(entityManager != null){
                entityManager.close();
            }
            if(entityManagerFactory != null){
                entityManagerFactory.close();
            }
        }*/
        System.out.println("Runner is done running");
    }
}
