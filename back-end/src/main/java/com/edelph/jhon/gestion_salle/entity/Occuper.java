package com.edelph.jhon.gestion_salle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "occuper")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Occuper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeOcc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "professeur_code",
            foreignKey = @ForeignKey(name = "O_PROFESSEUR_CODE")
    )
    private Professeur professeur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "salle_code",
            foreignKey = @ForeignKey(name = "0_SALLE_CODE")
    )
    private Salle salle;

    @NotNull
    private Date dateOccupe;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
