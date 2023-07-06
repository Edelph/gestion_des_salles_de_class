package com.edelph.jhon.gestion_salle.entity;

import com.edelph.jhon.gestion_salle.util.GenreConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "professeur")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Professeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeProf;

    @NotNull
    private String name;

    @Convert(converter = GenreConverter.class)
    @Column
    @NotNull
    private Genre genre;

    @ManyToOne
    @NotNull
    @JoinColumn(
            name = "grade_code",
            foreignKey = @ForeignKey(name = "GRADE_CODE")
    )
    private Grade grade;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
