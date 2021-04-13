package com.naemoo.catalogservice.service;

import com.naemoo.catalogservice.dto.CatalogDto;
import com.naemoo.catalogservice.entity.CatalogEntity;
import com.naemoo.catalogservice.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final CatalogRepository catalogRepository;

    @Override
    public List<CatalogEntity> getAllCatalogs() {
        return catalogRepository.findAll();
    }
}
