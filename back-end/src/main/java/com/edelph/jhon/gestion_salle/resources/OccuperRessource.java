package com.edelph.jhon.gestion_salle.resources;

import com.edelph.jhon.gestion_salle.entity.Occuper;
import com.edelph.jhon.gestion_salle.entity.Salle;
import com.edelph.jhon.gestion_salle.resources.beans.OccuperFilterBeans;
import com.edelph.jhon.gestion_salle.service.OccuperService;
import com.edelph.jhon.gestion_salle.service.SalleService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Date;
import java.util.List;

@Path("occupes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OccuperRessource {

    @GET
    @Path("/{codeOcc}")
    public Occuper getSalle(@PathParam("codeOcc") Long codeOcc){
        OccuperService service = new OccuperService();
        return service.find(codeOcc);
    }


    @POST
    public Occuper createProfesseur(Occuper occuper){
        OccuperService service = new OccuperService();
        System.out.println(occuper);
        return service.save(occuper);
    }
    @PUT
    @Path("/{codeOcc}")
    public Occuper createProfesseur(@PathParam("codeOcc") Long codeOcc, Occuper occuper){
        OccuperService service = new OccuperService();
        occuper.setCodeOcc(codeOcc);
        return service.update(occuper);
    }
    @DELETE
    @Path("/{codeOcc}")
    public  boolean deleteProfesseur(@PathParam("codeOcc") Long codeOcc){
        OccuperService service = new OccuperService();
        return service.delete(codeOcc);
    }

    @GET
    public List<Occuper> getOccByIntervall(@BeanParam()OccuperFilterBeans filterBeans) {
        OccuperService service = new OccuperService();
        if( filterBeans.getDate() == null){
            return service.findByInterval(filterBeans.getStart(), filterBeans.getEnd());
        }
        if( filterBeans.getStart() == null && filterBeans.getEnd()==null){
            return service.findByDate(filterBeans.getDate());
        }
        if(filterBeans.getStart() != null && filterBeans.getEnd() != null) {
            return service.findByInterval(filterBeans.getDate(), filterBeans.getStart(), filterBeans.getEnd());
        }
        return service.getAllOccuper();
    }

}
