package com.edelph.jhon.gestion_salle.resources;

import com.edelph.jhon.gestion_salle.entity.Grade;
import com.edelph.jhon.gestion_salle.repository.GradeRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

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
        GradeRepository gradeRepository = new GradeRepository();
        return gradeRepository.read(id);
    }

    @POST
    public Grade createGrade(Grade grade){
        GradeRepository gradeRepository = new GradeRepository();
        return gradeRepository.create(grade);
    }


    @DELETE
    @Path("/{id}")
    public boolean deleteGrade(@PathParam("id") Long id) {
        GradeRepository gradeRepository = new GradeRepository();
        return gradeRepository.delete(id);
    }
}
