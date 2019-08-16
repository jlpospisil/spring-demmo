package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name = "catalog_name", columnNames = {"catalog_name"}))
public class Catalog implements Serializable {

    public enum CatalogStatus {
        ACTIVE, INACTIVE, DELETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long catalogId;

    @NotNull
    @Column(name = "catalog_name")
    private String catalogName;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Builder.Default
    private CatalogStatus status = CatalogStatus.INACTIVE;
}
