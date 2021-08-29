package com.tnicacio.algamoney.dto;

import com.tnicacio.algamoney.entities.Category;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    @NotBlank(message = "Required field")
    private String name;

    public CategoryDTO(){}

    public CategoryDTO(String id, String name){
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
