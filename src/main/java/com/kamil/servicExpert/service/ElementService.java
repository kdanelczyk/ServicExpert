package com.kamil.servicExpert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.model.Element;
import com.kamil.servicExpert.db.model.Repair;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface ElementService {
	
	public boolean existsById(Long id);

	public Optional<Element> findById(Long id);

	public List<Element> findByTypeId(long typeId);

	public List<Element> findByQuantity(int quantity);

	public List<Element> findByPriceOfElement(int priceOfElement);

	public List<Element> findByNameOfElement(String nameOfElement);

	public List<Element> findElementsByRepairId(Long repairId);
	
	public List<Repair> findRepairsByElementsId(Long id);

	public List<Element> findAll();
	
	public Element save(Element element);
	
	public Element createElementForType(Long typeId, Element element);

	public Element updateElement(Long id, Element element);
	
	public Repair addElementToRepair(Long repairId, Element element);
	
	public void deleteById(Long id);

	public void deleteAll();

	public void deleteByTypeId(long typeId);
	
	public void deleteElementFromRepair(Long repairId, Long elementId);
}
