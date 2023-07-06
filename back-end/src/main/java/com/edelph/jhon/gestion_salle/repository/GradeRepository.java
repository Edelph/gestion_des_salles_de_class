package com.edelph.jhon.gestion_salle.repository;

import com.edelph.jhon.gestion_salle.entity.Grade;
import com.edelph.jhon.gestion_salle.repository.exeption.NotFoundException;
import com.edelph.jhon.gestion_salle.sessionFactory.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
        Optional<Grade> gradeOptional = session.byId(Grade.class).loadOptional(idThing);
        transaction.commit();
        if(gradeOptional.isPresent()) return gradeOptional.get();
        throw new NotFoundException("Not found grade having id " + idThing);
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
        Optional<Grade> gradeOptional = session.byId(Grade.class).loadOptional(idGrade);
        if(gradeOptional.isEmpty()) throw new NotFoundException("Not found grade having id " + idGrade);
        session.remove(gradeOptional.get());
        transaction.commit();
        return true;
    }

    public Collection<Grade>designationLike(String designation){
        String query = "from Grade as g where g.designation like :designation ";

        Transaction transaction = session.beginTransaction();
        Collection<Grade> gradeCollection =  session.createQuery(query,Grade.class)
                        .setParameter("designation","%"+designation+"%")
                        .getResultList();
        transaction.commit();
        return gradeCollection;
    }


    public List<Grade> findAll() {
        String query = "from Grade g order by g.createdAt desc ";

        Transaction transaction = session.beginTransaction();
        Collection<Grade> gradeCollection =  session.createQuery(query,Grade.class)
                .getResultList();
        transaction.commit();
        return gradeCollection.stream().toList();
    }
}
