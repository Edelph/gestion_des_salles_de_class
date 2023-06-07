package com.edelph.jhon.gestion_salle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "grade")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Grade  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codeGrade;
    private String designation;
}
