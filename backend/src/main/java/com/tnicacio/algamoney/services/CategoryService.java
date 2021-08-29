package com.tnicacio.algamoney.services;

import com.tnicacio.algamoney.dto.CategoryDTO;
import com.tnicacio.algamoney.entities.Category;
import com.tnicacio.algamoney.repositories.CategoryRepository;
import com.tnicacio.algamoney.services.exceptions.DatabaseException;
import com.tnicacio.algamoney.services.exceptions.InvalidUniqueIdentifierException;
import com.tnicacio.algamoney.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;


@Service
public class CategoryService {

    private final CategoryRepository repository;

    private final AuthService authService;

    @Autowired
    public CategoryService(CategoryRepository repository, AuthService authService) {
        this.repository = repository;
        this.authService = authService;
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(Pageable pageable) {
        Page<Category> categories = repository.findAll(pageable);
        return categories.map(category -> new CategoryDTO(category));
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(String id) {
        try {
            Optional<Category> obj = repository.findById(UUID.fromString(id));
            Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
            return new CategoryDTO(entity);
        } catch (IllegalArgumentException e) {
            throw new InvalidUniqueIdentifierException("Invalid Unique Identifier " + id);
        }
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO update(String id, CategoryDTO dto) {
        try {
            Category entity = repository.getById(UUID.fromString(id));
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new CategoryDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (IllegalArgumentException e) {
            throw new InvalidUniqueIdentifierException("Invalid Unique Identifier " + id);
        }
    }

    public void delete(String id) {
        try {
            repository.deleteById(UUID.fromString(id));
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        } catch (IllegalArgumentException e) {
            throw new InvalidUniqueIdentifierException("Invalid Unique Identifier " + id);
        }
    }

}
