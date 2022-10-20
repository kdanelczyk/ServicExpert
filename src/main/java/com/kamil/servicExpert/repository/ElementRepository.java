package com.kamil.servicExpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamil.servicExpert.db.model.Element;

import jakarta.transaction.Transactional;

public interface ElementRepository extends JpaRepository<Element, Long> {

	List<Element> findByTypeId(long typeId);

	List<Element> findByQuantity(int quantity);

	List<Element> findByPriceOfElement(int priceOfElement);

	List<Element> findByNameOfElement(String nameOfElement);
	
	List<Element> findElementsByRepairsId(Long repairId);

	@Transactional
	void deleteByTypeId(long typeId);
}