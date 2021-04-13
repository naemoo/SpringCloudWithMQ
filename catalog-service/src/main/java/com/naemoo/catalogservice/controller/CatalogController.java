package com.naemoo.catalogservice.controller;

import com.naemoo.catalogservice.dto.CatalogDto;
import com.naemoo.catalogservice.service.CatalogService;
import com.naemoo.catalogservice.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog-service")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;
    private final ModelMapper modelMapper;

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getAllCatalogs() {
        List<ResponseCatalog> body = catalogService.getAllCatalogs().stream().map(ce -> modelMapper.map(ce, ResponseCatalog.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(body);
    }
}
