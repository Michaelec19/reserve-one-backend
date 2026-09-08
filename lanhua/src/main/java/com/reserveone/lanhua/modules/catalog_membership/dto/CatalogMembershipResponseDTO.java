package com.reserveone.lanhua.modules.catalog_membership.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CatalogMembershipResponseDTO {
    private Integer idCatalogMembership;
    private Integer idCatalog;
    private Integer idMembership;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}