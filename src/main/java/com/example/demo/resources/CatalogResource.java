package com.example.demo.resources;

import com.example.demo.entities.Catalog;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.repositories.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/catalogs")
public class CatalogResource {
    private final CatalogRepository catalogRepository;

    @GetMapping
    public List<Catalog> getAllCatalogs() {
        return catalogRepository.findAll();
    }

    @GetMapping("/{catalogId}")
    public Catalog getCatalog(@PathVariable Long catalogId) {
        return catalogRepository.findOneByCatalogId(catalogId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Catalog createCatalog(@RequestBody @Valid Catalog newCatalog) {
        return catalogRepository.save(newCatalog);
    }

    @PutMapping("/{catalogId}")
    public Catalog updateCatalog(
            @RequestBody @Valid Catalog updatedCatalog,
            @PathVariable Long catalogId
    ) throws EntityNotFoundException {
        Catalog existingCatalog = catalogRepository.findOneByCatalogId(catalogId);

        if (existingCatalog == null) {
            throw new EntityNotFoundException("No catalog found with id of " + catalogId);
        }

        existingCatalog.setCatalogName(updatedCatalog.getCatalogName());
        existingCatalog.setStatus(updatedCatalog.getStatus());

        return catalogRepository.saveAndFlush(existingCatalog);
    }
}
