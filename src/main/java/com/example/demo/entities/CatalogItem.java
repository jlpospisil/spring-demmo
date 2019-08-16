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
@Table(uniqueConstraints = @UniqueConstraint(name = "item_name", columnNames = {"item_name"}))
public class CatalogItem implements Serializable {
    public enum CatalogItemStatus {
        ACTIVE, INACTIVE, DELETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    @NotNull
    @Column(name = "item_name")
    private String itemName;

    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Builder.Default
    private CatalogItemStatus status = CatalogItemStatus.INACTIVE;
}
