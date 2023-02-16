package com.kamil.servicExpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kamil.servicExpert.db.model.UsedElement;

public interface UsedElementRepository extends JpaRepository<UsedElement, Long> {

}
