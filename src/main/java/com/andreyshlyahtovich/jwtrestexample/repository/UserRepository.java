package com.andreyshlyahtovich.jwtrestexample.repository;

import com.andreyshlyahtovich.jwtrestexample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

