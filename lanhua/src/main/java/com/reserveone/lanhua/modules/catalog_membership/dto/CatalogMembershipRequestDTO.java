package com.reserveone.lanhua.modules.catalog_membership.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CatalogMembershipRequestDTO {
    @NotNull(message = "El ID del catálogo es obligatorio")
    private Integer idCatalog;

    @NotNull(message = "El ID de la membresía es obligatorio")
    private Integer idMembership;
}