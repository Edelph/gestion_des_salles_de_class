package com.edelph.jhon.gestion_salle.resources;

import com.edelph.jhon.gestion_salle.entity.Professeur;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("professeurs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfesseurResource {

    @GET
    public Professeur get(){
        return new Professeur();
    }
}
