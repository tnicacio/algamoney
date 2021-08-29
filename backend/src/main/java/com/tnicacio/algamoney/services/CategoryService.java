package com.tnicacio.algamoney.services;

import com.tnicacio.algamoney.dto.CategoryDTO;
import com.tnicacio.algamoney.entities.Category;
import com.tnicacio.algamoney.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(Pageable pageable) {
        Page<Category> categories = repository.findAll(pageable);
        return categories.map(category -> new CategoryDTO(category));
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(UUID id) {
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
    }
}
