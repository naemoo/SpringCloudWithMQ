package com.naemoo.catalogservice.config;

import com.naemoo.catalogservice.entity.CatalogEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class DBInit {
    private final InitService initService;

    @PostConstruct
    public void post() {
        initService.init();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager entityManager;

        /**
         * INSERT INTO CATALOG(product_id, product_name, stock, unit_price)
         * VALUES (`CATALOG-001`, `Berlin`, 100, 1500);
         * INSERT INTO CATALOG(product_id, product_name, stock, unit_price)
         * VALUES (`CATALOG-002`, `Tokyo`, 110, 1000);
         * INSERT INTO CATALOG(product_id, product_name, stock, unit_price)
         * VALUES (`CATALOG-003`, `Stockholm`, 120, 2000);
         */
        public void init() {
            CatalogEntity ct1 = CatalogEntity.builder()
                    .productId("CATALOG-001")
                    .productName("Berlin")
                    .stock(100)
                    .unitPrice(1500)
                    .build();
            CatalogEntity ct2 = CatalogEntity.builder()
                    .productId("CATALOG-002")
                    .productName("Tokyo")
                    .stock(110)
                    .unitPrice(1000)
                    .build();
            CatalogEntity ct3 = CatalogEntity.builder()
                    .productId("CATALOG-003")
                    .productName("Stockholm")
                    .stock(120)
                    .unitPrice(2000)
                    .build();
            entityManager.persist(ct1);
            entityManager.persist(ct2);
            entityManager.persist(ct3);
        }


    }

}
