package com.reserveone.lanhua.modules.catalog.controller;

import com.reserveone.lanhua.modules.catalog.dto.*;
import com.reserveone.lanhua.modules.catalog.service.CatalogService;
import com.reserveone.lanhua.modules.catalog_membership.dto.CatalogMembershipRequestDTO;
import com.reserveone.lanhua.modules.catalog_membership.dto.CatalogMembershipResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
@CrossOrigin(origins = "*")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    // --- ENDPOINTS CATALOG ---

    @GetMapping
    public ResponseEntity<List<CatalogResponseDTO>> getAll() {
        return ResponseEntity.ok(catalogService.getAllCatalogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(catalogService.getCatalogById(id));
    }

    @PostMapping
    public ResponseEntity<CatalogResponseDTO> create(@Valid @RequestBody CatalogRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(catalogService.createCatalog(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatalogResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody CatalogRequestDTO dto) {
        return ResponseEntity.ok(catalogService.updateCatalog(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        catalogService.deleteCatalog(id);
        return ResponseEntity.noContent().build();
    }

    // --- ENDPOINTS CATALOG MEMBERSHIP ---

    @GetMapping("/memberships")
    public ResponseEntity<List<CatalogMembershipResponseDTO>> getAllCatalogMemberships() {
        return ResponseEntity.ok(catalogService.getAllCatalogMemberships());
    }

    @PostMapping("/memberships")
    public ResponseEntity<CatalogMembershipResponseDTO> linkMembership(@Valid @RequestBody CatalogMembershipRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(catalogService.linkCatalogWithMembership(dto));
    }

    @DeleteMapping("/memberships/{id}")
    public ResponseEntity<Void> unlinkMembership(@PathVariable Integer id) {
        catalogService.unlinkCatalogMembership(id);
        return ResponseEntity.noContent().build();
    }
}