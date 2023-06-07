package com.edelph.jhon.gestion_salle.sessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySessionFactory {
    private static SessionFactory sessionFactory;

    public static void build(){
        if(sessionFactory == null){
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
    }

    public static SessionFactory getSessionFactory(){
        build();
        return sessionFactory;
    }
}
