package com.reserveone.lanhua.modules.membership.service;

import com.reserveone.lanhua.exception.ResourceNotFoundException;
import com.reserveone.lanhua.modules.membership.dto.MembershipRequestDTO;
import com.reserveone.lanhua.modules.membership.dto.MembershipResponseDTO;
import com.reserveone.lanhua.modules.membership.entity.Membership;
import com.reserveone.lanhua.modules.membership.repository.MembershipRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public MembershipService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Transactional(readOnly = true)
    public List<MembershipResponseDTO> findAll() {
        return membershipRepository
                .findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public MembershipResponseDTO findById(Long id) {
        Membership membership = membershipRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id " + id));

        return mapToResponseDTO(membership);
    }

    @Transactional
    public MembershipResponseDTO create(MembershipRequestDTO data) {
        Membership membership = new Membership();
        membership.setName(data.name());
        membership.setDescription(data.description());
        membership.setPrice(data.price());

        Membership created = membershipRepository.save(membership);
        return mapToResponseDTO(created);
    }

    @Transactional
    public MembershipResponseDTO update(Long id, MembershipRequestDTO data) {
        Membership membership = membershipRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id " + id));

        membership.setName(data.name());
        membership.setDescription(data.description());
        membership.setPrice(data.price());

        Membership updated = membershipRepository.save(membership);
        return mapToResponseDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!membershipRepository.existsById(id)) {
            throw new ResourceNotFoundException("Membership not found with id " + id);
        }

        membershipRepository.deleteById(id);
    }

    private MembershipResponseDTO mapToResponseDTO(Membership membership) {
        return new MembershipResponseDTO(
                membership.getId(),
                membership.getName(),
                membership.getDescription(),
                membership.getPrice(),
                membership.getCreatedAt(),
                membership.getUpdatedAt()
        );
    }
}