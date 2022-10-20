package com.kamil.servicExpert.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.kamil.servicExpert.db.model.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {

}
