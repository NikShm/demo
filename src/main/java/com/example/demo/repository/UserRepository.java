package com.example.demo.repository;

import com.example.demo.model.jpa.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends EntityRepository<User, Integer> {

	Optional<User> findByLogin(String login);
}
