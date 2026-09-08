package com.reserveone.lanhua.modules.membership.controller;

import com.reserveone.lanhua.modules.membership.dto.MembershipRequestDTO;
import com.reserveone.lanhua.modules.membership.dto.MembershipResponseDTO;
import com.reserveone.lanhua.modules.membership.service.MembershipService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
public class MembershipController {
    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @GetMapping
    public List<MembershipResponseDTO> findAll() {
        return membershipService.findAll();
    }

    @GetMapping("/{id}")
    public MembershipResponseDTO findById(@PathVariable Long id) {
        return membershipService.findById(id);
    }

    @PostMapping
    public ResponseEntity<MembershipResponseDTO> create(@Valid @RequestBody MembershipRequestDTO dto) {
        MembershipResponseDTO created = membershipService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public MembershipResponseDTO update(@PathVariable Long id, @Valid @RequestBody MembershipRequestDTO dto) {
        return membershipService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        membershipService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
