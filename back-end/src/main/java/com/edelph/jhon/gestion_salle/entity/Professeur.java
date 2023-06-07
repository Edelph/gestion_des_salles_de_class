package com.edelph.jhon.gestion_salle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "professeur")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Professeur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codeProf;
    private String name;
    @ManyToOne
    @JoinColumn(name = "grade_code_grade")
    private Grade grade;
}
