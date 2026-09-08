package com.reserveone.lanhua.modules.catalog_membership.entity;

import com.reserveone.lanhua.modules.catalog.entity.Catalog;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "catalog_membership")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatalogMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_catalog_membership")
    private Integer idCatalogMembership;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_catalog", nullable = false)
    private Catalog catalog;

    @Column(name = "id_membership", nullable = false)
    private Integer idMembership;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
}