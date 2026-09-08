package com.reserveone.lanhua.modules.catalog_membership.repository;

import com.reserveone.lanhua.modules.catalog_membership.entity.CatalogMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogMembershipRepository extends JpaRepository<CatalogMembership, Integer> {
    List<CatalogMembership> findByCatalog_IdCatalog(Integer idCatalog);
    List<CatalogMembership> findByIdMembership(Integer idMembership);
}