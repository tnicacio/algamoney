package com.tnicacio.algamoney.algamoney.repositories;


import com.tnicacio.algamoney.algamoney.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
