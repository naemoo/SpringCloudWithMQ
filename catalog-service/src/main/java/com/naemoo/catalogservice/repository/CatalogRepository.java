package com.naemoo.catalogservice.repository;

import com.naemoo.catalogservice.entity.CatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<CatalogEntity, Long> {
    public CatalogEntity findByproductId(String productId);
}
