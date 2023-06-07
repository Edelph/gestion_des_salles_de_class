package com.edelph.jhon.gestion_salle.resources;

import com.edelph.jhon.gestion_salle.entity.Occuper;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("occupes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OccuperRessource {
    @GET
    public Occuper get(){
        return new Occuper();
    }
}
