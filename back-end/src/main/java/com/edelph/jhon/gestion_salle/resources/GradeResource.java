package com.edelph.jhon.gestion_salle.resources;

import com.edelph.jhon.gestion_salle.entity.Grade;
import com.edelph.jhon.gestion_salle.repository.GradeRepository;
import com.edelph.jhon.gestion_salle.repository.exeption.NotFoundException;
import com.edelph.jhon.gestion_salle.service.GradeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("grades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GradeResource {
    @GET
    @Path("/{id}")
    public Grade  getGrade(@PathParam("id") Long id) {
        GradeService service = new GradeService();
        return service.find(id);
    }

    @PUT
    @Path("/{id}")
    public Grade  updateGrade(@PathParam("id") Long id, Grade grade) {
        if(id == null && id != grade.getCodeGrade()) throw new NotFoundException("Invalid requested");
        GradeService service = new GradeService();
        return service.update(grade);
    }

    @GET
    public List<Grade> getGradeList(){
        GradeService service = new GradeService();
        return service.findAll();
    }

    @POST
    public Grade createGrade(Grade grade){
        GradeService service = new GradeService();
        return service.save(grade);
    }

    @GET
    public List<Grade> searchGrad(@QueryParam("search") String patern){
        GradeService service = new GradeService();
        return service.getGradeLikeDesignation(patern).stream().toList();
    }


    @DELETE
    @Path("/{id}")
    public boolean deleteGrade(@PathParam("id") Long id) {
        GradeService service = new GradeService();
        return service.remove(id);
    }
}
