package com.naemoo.catalogservice.service;

import com.naemoo.catalogservice.dto.CatalogDto;
import com.naemoo.catalogservice.entity.CatalogEntity;

import java.util.List;

public interface CatalogService {
    public List<CatalogEntity> getAllCatalogs();
}
