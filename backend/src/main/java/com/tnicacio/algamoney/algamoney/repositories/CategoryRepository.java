package com.tnicacio.algamoney.algamoney.repositories;

import com.tnicacio.algamoney.algamoney.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
