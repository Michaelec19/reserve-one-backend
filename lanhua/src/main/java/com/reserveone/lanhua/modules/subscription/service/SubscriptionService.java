package com.reserveone.lanhua.modules.subscription.service;

import com.reserveone.lanhua.exception.ResourceNotFoundException;
import com.reserveone.lanhua.modules.membership.dto.MembershipSummaryDTO;
import com.reserveone.lanhua.modules.membership.entity.Membership;
import com.reserveone.lanhua.modules.membership.repository.MembershipRepository;
import com.reserveone.lanhua.modules.subscription.dto.*;
import com.reserveone.lanhua.modules.subscription.entity.Subscription;
import com.reserveone.lanhua.modules.subscription.repository.SubscriptionRepository;
import com.reserveone.lanhua.modules.user.dto.UserSummaryDTO;
import com.reserveone.lanhua.modules.user.entity.User;
import com.reserveone.lanhua.modules.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final MembershipRepository membershipRepository;
    private final UserRepository usersRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, MembershipRepository membershipRepository, UserRepository usersRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.membershipRepository = membershipRepository;
        this.usersRepository = usersRepository;
    }

    @Transactional(readOnly = true)
    public List<SubscriptionResponseDTO> findAll() {
        return subscriptionRepository
                .findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public SubscriptionResponseDTO findById(Long id) {
        Subscription subscription = subscriptionRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found with id " + id));

        return mapToResponseDTO(subscription);
    }

    @Transactional
    public SubscriptionResponseDTO create(SubscriptionRequestDTO data) {
        Membership membership = membershipRepository
                .findById(data.membershipId())
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id " + data.membershipId()));

        User user = usersRepository
                .findById(data.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + data.userId()));

        Subscription subscription = new Subscription();
        subscription.setMembership(membership);
        subscription.setUser(user);
        subscription.setDateStart(data.dateStart());
        subscription.setDateEnd(data.dateEnd());
        subscription.setStatus(data.status());

        Subscription created = subscriptionRepository.save(subscription);
        return mapToResponseDTO(created);
    }

    @Transactional
    public SubscriptionResponseDTO update(Long id, SubscriptionRequestDTO data) {
        Subscription subscription = subscriptionRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found with id " + id));

        Membership membership = membershipRepository
                .findById(data.membershipId())
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id " + data.membershipId()));

        User user = usersRepository
                .findById(data.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + data.userId()));

        subscription.setMembership(membership);
        subscription.setUser(user);
        subscription.setDateStart(data.dateStart());
        subscription.setDateEnd(data.dateEnd());
        subscription.setStatus(data.status());

        Subscription updated = subscriptionRepository.save(subscription);
        return mapToResponseDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!subscriptionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Subscription not found with id " + id);
        }

        subscriptionRepository.deleteById(id);
    }

    private SubscriptionResponseDTO mapToResponseDTO(Subscription subscription) {
        MembershipSummaryDTO membershipSummary = new MembershipSummaryDTO(
                subscription.getMembership().getId(),
                subscription.getMembership().getName()
        );

        UserSummaryDTO userSummary = new UserSummaryDTO(
                subscription.getUser().getIdUser(),
                subscription.getUser().getNameUser(),
                subscription.getUser().getEmailUser()
        );

        return new SubscriptionResponseDTO(
                subscription.getId(),
                membershipSummary,
                userSummary,
                subscription.getDateStart(),
                subscription.getDateEnd(),
                subscription.getStatus(),
                subscription.getCreatedAt(),
                subscription.getUpdatedAt()
        );
    }
}