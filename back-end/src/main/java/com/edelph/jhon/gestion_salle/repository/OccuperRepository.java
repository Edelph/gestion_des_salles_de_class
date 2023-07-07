package com.edelph.jhon.gestion_salle.repository;

import com.edelph.jhon.gestion_salle.entity.Occuper;
import com.edelph.jhon.gestion_salle.repository.exeption.NotFoundException;
import com.edelph.jhon.gestion_salle.sessionFactory.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public class OccuperRepository implements InterfaceCRUD<Occuper> {
    private Session session ;

    public OccuperRepository() {
        this.session = MySessionFactory.getSessionFactory().openSession();
    }

    @Override
    public Occuper create(Occuper occuper) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.persist(occuper);
        transaction.commit();
        return occuper;
    }

    @Override
    public Occuper read(Long codeOcc) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Optional<Occuper> occOpt =  session.byId(Occuper.class).loadOptional(codeOcc);
        transaction.commit();
        if (occOpt.isPresent()) return occOpt.get();
        throw new NotFoundException("Could not find Occupation having id " + codeOcc);
    }

    @Override
    public Occuper update(Occuper occuper) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Occuper occuperDB = session.find(Occuper.class,occuper.getCodeOcc());
        occuperDB.update(occuper);
        session.flush();
        transaction.commit();
        return occuperDB;
    }

    @Override
    public boolean delete(Long codeOcc) {
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Optional<Occuper> occOpt =  session.byId(Occuper.class).loadOptional(codeOcc);
        if(occOpt.isEmpty()) throw new NotFoundException("No occupation has code :" + codeOcc);
        session.remove(occOpt.get());
        transaction.commit();
        return true;
    }
    public Collection<Occuper> findAll(){
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Collection<Occuper> occuperCollection = session.createQuery("from Occuper o order by o.createdAt DESC ",Occuper.class).getResultList();
        transaction.commit();
        return occuperCollection;
    }

    public Collection<Occuper> findByDate(Date date){
        String query = "from Occuper o where o.dateOccupe = :dateOcc  order by o.createdAt DESC ";
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Collection<Occuper> occuperCollection = session.createQuery(query,Occuper.class)
                .setParameter("dateOcc", date)
                .getResultList();
        transaction.commit();
        return occuperCollection;
    }

    public Collection<Occuper> findNotOccuper(Date date){
        String query = "from Occuper o where o.dateOccupe != :dateOcc  order by o.createdAt DESC ";
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Collection<Occuper> occuperCollection = session.createQuery(query,Occuper.class)
                .setParameter("dateOcc", date)
                .getResultList();
        transaction.commit();
        return occuperCollection;
    }


    public Collection<Occuper> findByInterval(Date start , Date end){
        String query = "from Occuper o where o.startTime = :start and o.endTime =:end  order by o.createdAt DESC ";
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Collection<Occuper> occuperCollection = session.createQuery(query,Occuper.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
        transaction.commit();
        return occuperCollection;
    }
    public Collection<Occuper> findNotInInterval(Date start , Date end){
        String query = "from Occuper o where o.startTime!= :start and o.endTime!=:end  order by o.createdAt DESC ";
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Collection<Occuper> occuperCollection = session.createQuery(query,Occuper.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
        transaction.commit();
        return occuperCollection;
    }

    public Collection<Occuper> findByInterval(Date date, Date start , Date end){
        String query = "from Occuper o where o.dateOccupe = :dateO and o.startTime = :start and o.endTime =:end  order by o.createdAt DESC ";
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Collection<Occuper> occuperCollection = session.createQuery(query,Occuper.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .setParameter("dateO",date)
                .getResultList();
        transaction.commit();
        return occuperCollection;
    }
}
