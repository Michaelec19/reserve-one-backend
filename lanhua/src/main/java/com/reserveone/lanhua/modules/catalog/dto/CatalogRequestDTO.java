package com.reserveone.lanhua.modules.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class CatalogRequestDTO {
    @NotBlank(message = "El nombre del catálogo es obligatorio")
    private String name;

    private String description;
    private String image;


    private List<String> category;
}