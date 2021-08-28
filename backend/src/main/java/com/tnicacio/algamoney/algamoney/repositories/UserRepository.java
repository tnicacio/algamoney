package com.tnicacio.algamoney.algamoney.repositories;

import com.tnicacio.algamoney.algamoney.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
