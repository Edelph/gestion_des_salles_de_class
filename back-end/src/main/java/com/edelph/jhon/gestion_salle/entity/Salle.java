package com.edelph.jhon.gestion_salle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "salle")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codeSal;
    private String designation;
}
