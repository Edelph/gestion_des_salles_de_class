package com.edelph.jhon.gestion_salle.resources.beans;

import com.edelph.jhon.gestion_salle.entity.Grade;
import jakarta.ws.rs.QueryParam;



public class ProfFilterBeans {
    @QueryParam("name") String name;
    @QueryParam("Genre") Character genre;
    @QueryParam("grade") Grade grade;
}
