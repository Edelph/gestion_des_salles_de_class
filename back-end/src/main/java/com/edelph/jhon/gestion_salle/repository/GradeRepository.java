package com.edelph.jhon.gestion_salle.repository;

import com.edelph.jhon.gestion_salle.entity.Grade;
import com.edelph.jhon.gestion_salle.sessionFactory.MySessionFactory;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GradeRepository implements InterfaceCRUD<Grade> {
    Session session;

    public GradeRepository() {
        session = MySessionFactory.getSessionFactory().getCurrentSession();
    }

    @Override
    public Grade create(Grade grade) {
        Transaction transaction = session.beginTransaction();
        session.persist(grade);
        transaction.commit();
        return grade;
    }

    @Override
    public Grade read(Long idThing) {
        Transaction transaction = session.beginTransaction();
        Grade grade = session.find(Grade.class, idThing);
        transaction.commit();
        return grade;
    }

    @Override
    public Grade update(Grade grade) {
        Transaction transaction = session.beginTransaction();
        session.persist(grade);
        transaction.commit();
        return grade;
    }

    @Override
    public boolean delete(Long idGrade) {
        Transaction transaction = session.beginTransaction();
        session.remove(idGrade);
        transaction.commit();
        return true;
    }
}
