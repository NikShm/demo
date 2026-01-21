package com.example.demo.repository;

import com.example.demo.model.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface EntityRepository<E extends Entity<I>, I extends Serializable> extends JpaRepository<E, I>, JpaSpecificationExecutor<E> {}

