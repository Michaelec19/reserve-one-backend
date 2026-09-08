package com.reserveone.lanhua.modules.catalog.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CatalogResponseDTO {
    private Integer idCatalog;
    private String name;
    private String description;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}