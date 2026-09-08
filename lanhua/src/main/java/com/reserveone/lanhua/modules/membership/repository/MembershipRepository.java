package com.reserveone.lanhua.modules.membership.repository;

import com.reserveone.lanhua.modules.membership.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
}
