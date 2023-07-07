package com.edelph.jhon.gestion_salle.resources.beans;

import jakarta.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OccuperFilterBeans {
    @QueryParam("start") Date start;
    @QueryParam("end") Date end;
    @QueryParam("date") Date date;
}
