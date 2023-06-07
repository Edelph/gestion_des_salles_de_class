package com.edelph.jhon.gestion_salle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "occuper")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Occuper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codeOcc;
    @ManyToOne
    @JoinColumn(name = "professeur_code_prof")
    private Professeur professeur;
    @ManyToOne
    @JoinColumn(name = "salle_code_sal")
    private Salle salle;
    private Date dateOccupe;
}
