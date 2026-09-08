package com.reserveone.lanhua.modules.catalog.repository;

import com.reserveone.lanhua.modules.catalog.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
}