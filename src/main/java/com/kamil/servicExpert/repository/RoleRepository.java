package com.kamil.servicExpert.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamil.servicExpert.db.model.ERole;
import com.kamil.servicExpert.db.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
  Optional<Role> findByName(ERole name);
}