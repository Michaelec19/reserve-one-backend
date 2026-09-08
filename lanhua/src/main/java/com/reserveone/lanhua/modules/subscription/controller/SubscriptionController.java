package com.reserveone.lanhua.modules.subscription.controller;

import com.reserveone.lanhua.modules.subscription.dto.SubscriptionRequestDTO;
import com.reserveone.lanhua.modules.subscription.dto.SubscriptionResponseDTO;
import com.reserveone.lanhua.modules.subscription.service.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public List<SubscriptionResponseDTO> findAll() {
        return subscriptionService.findAll();
    }

    @GetMapping("/{id}")
    public SubscriptionResponseDTO findById(@PathVariable Long id) {
        return subscriptionService.findById(id);
    }

    @PostMapping
    public ResponseEntity<SubscriptionResponseDTO> create(@Valid @RequestBody SubscriptionRequestDTO dto) {
        SubscriptionResponseDTO created = subscriptionService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public SubscriptionResponseDTO update(@PathVariable Long id, @Valid @RequestBody SubscriptionRequestDTO dto) {
        return subscriptionService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        subscriptionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
