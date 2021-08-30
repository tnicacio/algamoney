package com.tnicacio.algamoney.dto;

import com.tnicacio.algamoney.entities.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    @NotBlank
    @Size(min = 3, max = 50, message = "The size must be between {min} and {max} characters")
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(String id, String name) {
        this.name = name;
    }

    public CategoryDTO(Category entity) {
        this.id = entity.getId().toString();
        this.name = entity.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
