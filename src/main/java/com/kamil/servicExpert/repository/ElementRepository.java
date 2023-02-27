package com.kamil.servicExpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamil.servicExpert.db.model.Element;

import jakarta.transaction.Transactional;

public interface ElementRepository extends JpaRepository<Element, Long> {

	List<Element> findByTypeId(long typeId);

	@Transactional
	void deleteByTypeId(long typeId);
}