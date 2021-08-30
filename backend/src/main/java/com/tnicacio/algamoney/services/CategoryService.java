package com.tnicacio.algamoney.services;

import com.tnicacio.algamoney.dto.CategoryDTO;
import com.tnicacio.algamoney.entities.Category;
import com.tnicacio.algamoney.repositories.CategoryRepository;
import com.tnicacio.algamoney.services.exceptions.DatabaseException;
import com.tnicacio.algamoney.services.exceptions.InvalidUniqueIdentifierException;
import com.tnicacio.algamoney.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

    private final MessageSource messageSource;

    @Autowired
    public CategoryService(CategoryRepository repository, AuthService authService, MessageSource messageSource) {
        this.repository = repository;
        this.authService = authService;
        this.messageSource = messageSource;
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
            Category entity = obj.orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("exception.service.entity_not_found", null, LocaleContextHolder.getLocale())));
            return new CategoryDTO(entity);
        } catch (IllegalArgumentException e) {
            throw new InvalidUniqueIdentifierException(messageSource.getMessage("exception.service.invalid_unique_identifier", new Object[] { id }, LocaleContextHolder.getLocale()));
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
            throw new ResourceNotFoundException(messageSource.getMessage("exception.service.id_not_found", new Object[] { id }, LocaleContextHolder.getLocale()));
        } catch (IllegalArgumentException e) {
            throw new InvalidUniqueIdentifierException(messageSource.getMessage("exception.service.invalid_unique_identifier", new Object[] { id }, LocaleContextHolder.getLocale()));
        }
    }

    public void delete(String id) {
        try {
            repository.deleteById(UUID.fromString(id));
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(messageSource.getMessage("exception.service.id_not_found", new Object[] { id }, LocaleContextHolder.getLocale()));
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(messageSource.getMessage("exception.service.integrity_violation", null, LocaleContextHolder.getLocale()));
        } catch (IllegalArgumentException e) {
            throw new InvalidUniqueIdentifierException(messageSource.getMessage("exception.service.invalid_unique_identifier", new Object[] { id }, LocaleContextHolder.getLocale()));
        }
    }

}
