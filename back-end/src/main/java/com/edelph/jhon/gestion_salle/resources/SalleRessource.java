package com.edelph.jhon.gestion_salle.resources;

import com.edelph.jhon.gestion_salle.entity.Salle;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("salles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SalleRessource {

    @GET
    public Salle get(){
        return new Salle();
    }
}
