package com.reserveone.lanhua.modules.catalog.service;

import com.reserveone.lanhua.modules.catalog.dto.*;
import com.reserveone.lanhua.modules.catalog.entity.Catalog;
import com.reserveone.lanhua.modules.catalog.repository.CatalogRepository;
import com.reserveone.lanhua.modules.catalog_membership.dto.CatalogMembershipRequestDTO;
import com.reserveone.lanhua.modules.catalog_membership.dto.CatalogMembershipResponseDTO;
import com.reserveone.lanhua.modules.catalog_membership.entity.CatalogMembership;
import com.reserveone.lanhua.modules.catalog_membership.repository.CatalogMembershipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogService {

    private final CatalogRepository catalogRepository;
    private final CatalogMembershipRepository catalogMembershipRepository;

    public CatalogService(CatalogRepository catalogRepository, CatalogMembershipRepository catalogMembershipRepository) {
        this.catalogRepository = catalogRepository;
        this.catalogMembershipRepository = catalogMembershipRepository;
    }

    // --- MÉTODOS CATALOG ---

    public List<CatalogResponseDTO> getAllCatalogs() {
        return catalogRepository.findAll().stream()
                .map(this::mapToCatalogResponseDTO)
                .collect(Collectors.toList());
    }

    public CatalogResponseDTO getCatalogById(Integer id) {
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catálogo no encontrado con el ID: " + id));
        return mapToCatalogResponseDTO(catalog);
    }

    public CatalogResponseDTO createCatalog(CatalogRequestDTO dto) {
        Catalog catalog = Catalog.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .image(dto.getImage())
                .build();
        return mapToCatalogResponseDTO(catalogRepository.save(catalog));
    }

    public CatalogResponseDTO updateCatalog(Integer id, CatalogRequestDTO dto) {
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catálogo no encontrado con el ID: " + id));

        catalog.setName(dto.getName());
        catalog.setDescription(dto.getDescription());
        catalog.setImage(dto.getImage());

        return mapToCatalogResponseDTO(catalogRepository.save(catalog));
    }

    public void deleteCatalog(Integer id) {
        if (!catalogRepository.existsById(id)) {
            throw new RuntimeException("Catálogo no encontrado con el ID: " + id);
        }
        catalogRepository.deleteById(id);
    }

    // --- MÉTODOS CATALOG MEMBERSHIP ---

    public List<CatalogMembershipResponseDTO> getAllCatalogMemberships() {
        return catalogMembershipRepository.findAll().stream()
                .map(this::mapToCatalogMembershipResponseDTO)
                .collect(Collectors.toList());
    }

    public CatalogMembershipResponseDTO linkCatalogWithMembership(CatalogMembershipRequestDTO dto) {
        Catalog catalog = catalogRepository.findById(dto.getIdCatalog())
                .orElseThrow(() -> new RuntimeException("Catálogo no encontrado con el ID: " + dto.getIdCatalog()));

        CatalogMembership catalogMembership = CatalogMembership.builder()
                .catalog(catalog)
                .idMembership(dto.getIdMembership())
                .build();

        return mapToCatalogMembershipResponseDTO(catalogMembershipRepository.save(catalogMembership));
    }

    public void unlinkCatalogMembership(Integer id) {
        if (!catalogMembershipRepository.existsById(id)) {
            throw new RuntimeException("Relación Catálogo-Membresía no encontrada con el ID: " + id);
        }
        catalogMembershipRepository.deleteById(id);
    }

    // --- MAPPERS ---

    private CatalogResponseDTO mapToCatalogResponseDTO(Catalog catalog) {
        CatalogResponseDTO dto = new CatalogResponseDTO();
        dto.setIdCatalog(catalog.getIdCatalog());
        dto.setName(catalog.getName());
        dto.setDescription(catalog.getDescription());
        dto.setImage(catalog.getImage());
        dto.setCreatedAt(catalog.getCreatedAt());
        dto.setUpdatedAt(catalog.getUpdatedAt());
        return dto;
    }

    private CatalogMembershipResponseDTO mapToCatalogMembershipResponseDTO(CatalogMembership entity) {
        CatalogMembershipResponseDTO dto = new CatalogMembershipResponseDTO();
        dto.setIdCatalogMembership(entity.getIdCatalogMembership());

        if (entity.getCatalog() != null) {
            dto.setIdCatalog(entity.getCatalog().getIdCatalog());
        }

        dto.setIdMembership(entity.getIdMembership());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
}