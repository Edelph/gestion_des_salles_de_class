package com.edelph.jhon.gestion_salle.resources;

import com.edelph.jhon.gestion_salle.entity.Genre;
import com.edelph.jhon.gestion_salle.entity.Professeur;
import com.edelph.jhon.gestion_salle.service.ProfesseurService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("professeurs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfesseurResource {

    @GET
    public List<Professeur> getProfesseurs(){
        ProfesseurService service = new ProfesseurService();
       return service.getAllProfesseurs();
    }

    @GET
    @Path("/{idProf}")
    public Professeur getProfesseur(@PathParam("idProf") Long codeProf){
        ProfesseurService service = new ProfesseurService();
        return service.find(codeProf);
    }


    @POST
    public Professeur createProfesseur(Professeur professeur){
        System.out.println(professeur);
        ProfesseurService service = new ProfesseurService();
        return service.save(professeur);
    }
    @PUT
    @Path("/{codeProf}")
    public Professeur createProfesseur(@PathParam("codeProf") Long codeProf, Professeur professeur){
        ProfesseurService service = new ProfesseurService();
        professeur.setCodeProf(codeProf);
        return service.update(professeur);
    }
    @DELETE
    @Path("/{codeProf}")
    public  boolean deleteProfesseur(@PathParam("codeProf") Long codeProf){
        ProfesseurService service = new ProfesseurService();
        return service.delete(codeProf);
    }

}
