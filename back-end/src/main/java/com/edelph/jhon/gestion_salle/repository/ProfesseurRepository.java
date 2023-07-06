package com.edelph.jhon.gestion_salle.repository;

import com.edelph.jhon.gestion_salle.entity.Genre;
import com.edelph.jhon.gestion_salle.entity.Grade;
import com.edelph.jhon.gestion_salle.entity.Professeur;
import com.edelph.jhon.gestion_salle.repository.exeption.NotFoundException;
import com.edelph.jhon.gestion_salle.sessionFactory.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.awt.geom.GeneralPath;
import java.util.Collection;
import java.util.Optional;

public class ProfesseurRepository implements InterfaceCRUD<Professeur>{
    Session session;

    public ProfesseurRepository(Session session) {
        this.session = MySessionFactory.getSessionFactory().getCurrentSession();
    }

    @Override
    public Professeur create(Professeur professeur) {
        Transaction transaction = session.getTransaction();
        session.persist(professeur);
        transaction.commit();
        return professeur;
    }

    @Override
    public Professeur read(Long codeProfesseur) {
        Transaction transaction = session.getTransaction();
        Optional<Professeur> professeurOpt =  session.byId(Professeur.class).loadOptional(codeProfesseur);
        transaction.commit();
        if (professeurOpt.isPresent()) return professeurOpt.get();
        throw new NotFoundException("Could not find professeur having id " + codeProfesseur);
    }

    @Override
    public Professeur update(Professeur professeur) {
        Transaction transaction = session.getTransaction();
        session.persist(professeur);
        transaction.commit();
        return professeur;
    }

    @Override
    public boolean delete(Long codeProfesseur) {
        Transaction transaction = session.getTransaction();
        Optional<Professeur> professeurOpt =  session.byId(Professeur.class).loadOptional(codeProfesseur);
        if(professeurOpt.isEmpty()) throw new NotFoundException("No professeur has code :" + codeProfesseur);
        session.remove(professeurOpt.get());
        transaction.commit();
        return true;
    }

    public Collection<Professeur> findAll(){
        Transaction transaction = session.getTransaction();
        Collection<Professeur> professeurCollection = session.createQuery("from Professeur p order by p.createdAt DESC ",Professeur.class).getResultList();
        transaction.commit();
        return professeurCollection;
    }

    public Collection<Professeur> nameLike(String name) {
        String query = "from Professeur p where name like :name order by p.createdAt DESC " ;
        Transaction transaction = session.beginTransaction();
        Collection<Professeur> professeurCollection =  session
                .createQuery(query, Professeur.class)
                .setParameter("name", "%" + name + "%").getResultList();

        transaction.commit();
        return professeurCollection;
    }

    public Collection<Professeur> gradeLike(String gradeName) {
        String query = "select p from Professeur p " +
                "left join fetch p.grade where p.grade.designation like :name order by p.createdAt DESC" ;
        Transaction transaction = session.beginTransaction();
        Collection<Professeur> professeurCollection =  session
                .createQuery(query, Professeur.class)
                .setParameter("name", "%" + gradeName + "%").getResultList();

        transaction.commit();
        return professeurCollection;
    }

    public Collection<Professeur> byGenre(Genre genre){
        String query = "from Professeur p where fk(p.genre) = :genre order by p.createdAt DESC" ;
        Transaction transaction = session.beginTransaction();
        Collection<Professeur> professeurCollection =  session
                .createQuery(query, Professeur.class)
                .setParameter("genre", genre).getResultList();
        transaction.commit();
        return professeurCollection;
    }
}
