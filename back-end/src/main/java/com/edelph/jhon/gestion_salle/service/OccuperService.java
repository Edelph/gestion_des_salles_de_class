package com.edelph.jhon.gestion_salle.service;

import com.edelph.jhon.gestion_salle.entity.Occuper;
import com.edelph.jhon.gestion_salle.repository.OccuperRepository;
import java.util.Date;
import java.util.List;

public class OccuperService {
    private final OccuperRepository repository;

    public OccuperService(){
        repository = new OccuperRepository();
    }

    public List<Occuper> getAllOccuper(){
        return repository.findAll().stream().toList();
    }
    public Occuper find(Long codeProf){
        return  repository.read(codeProf);
    }
    public Occuper update(Occuper occuper){
        ProfesseurService pService = new ProfesseurService();
        SalleService sService = new SalleService();
        occuper.setProfesseur(pService.find(occuper.getProfesseur().getCodeProf()));
        occuper.setSalle(sService.find(occuper.getSalle().getCodeSal()));
        return repository.update(occuper);
    }
    public Occuper save(Occuper occuper){
        ProfesseurService pService = new ProfesseurService();
        SalleService sService = new SalleService();
        occuper.setProfesseur(pService.find(occuper.getProfesseur().getCodeProf()));
        occuper.setSalle(sService.find(occuper.getSalle().getCodeSal()));
        return repository.create(occuper);
    }
    public boolean delete(Long codeocc) {
        return repository.delete(codeocc);
    }

    public List<Occuper> findByInterval(Date start, Date end){
        return repository.findByInterval(start, end).stream().toList();
    }
    public List<Occuper> findByInterval(Date date, Date start, Date end){
        return repository.findByInterval(date, start, end).stream().toList();
    }
    public List<Occuper> findNotInInterval(Date date, Date start, Date end){
        return repository.findNotInInterval(start, end).stream().toList();
    }

    public List<Occuper> findByDate(Date date){
        return repository.findByDate(date).stream().toList();
    }
    public List<Occuper> findNotInDate(Date date){
        return repository.findNotOccuper(date).stream().toList();
    }

}
