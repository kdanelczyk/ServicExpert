package com.kamil.servicExpert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.model.Element;
import com.kamil.servicExpert.db.model.Repair;
import com.kamil.servicExpert.db.model.Type;
import com.kamil.servicExpert.exception.ResourceNotFoundException;
import com.kamil.servicExpert.repository.ElementRepository;

@Service
public class ElementServiceImpl implements ElementService{
	
	@Autowired
	private ElementRepository elementRepository;
	
	@Autowired
	private TypeService typeService;

	@Autowired
	private RepairService repairService;
	
	@Override
	public boolean existsById(Long id) {
		if (!elementRepository.existsById(id)) {
			throw new ResourceNotFoundException("Not found Element with id = " + id);
		}
		return elementRepository.existsById(id);
	}

	@Override
	public Optional<Element> findById(Long id) {
		Optional<Element> element = elementRepository.findById(id);
		return element;
	}

	@Override
	public List<Element> findByTypeId(long typeId) {
		typeService.existsById(typeId);
		return elementRepository.findByTypeId(typeId);
	}

	@Override
	public List<Element> findByQuantity(int quantity) {
		return elementRepository.findByQuantity(quantity);
	}

	@Override
	public List<Element> findByPriceOfElement(int priceOfElement) {
		return elementRepository.findByPriceOfElement(priceOfElement);
	}

	@Override
	public List<Element> findByNameOfElement(String nameOfElement) {
		return elementRepository.findByNameOfElement(nameOfElement);
	}

	@Override
	public List<Element> findElementsByRepairId(Long repairId) {
		repairService.existsById(repairId);
		return elementRepository.findElementsByRepairsId(repairId);
	}

	@Override
	public List<Repair> findRepairsByElementsId(Long id) {
		return repairService.findRepairsByElementsId(id);
	}

	@Override
	public List<Element> findAll() {
		return elementRepository.findAll();
	}

	@Override
	public Element save(Element element) {
		return elementRepository.save(element);
	}

	@Override
	public Element createElementForType(Long typeId, Element element) {
		Type type = typeService.findById(typeId).get();
		return save(Element.builder()
				.quantity(element.getQuantity())
				.criticalQuantity(element.getCriticalQuantity())
				.nameOfElement(element.getNameOfElement())
				.priceOfElement(element.getPriceOfElement())
				.type(type).build());
	}

	@Override
	public Element updateElement(Long id, Element element) {
		Element _element = findById(id).get();
		_element.setQuantity(element.getQuantity());
		_element.setNameOfElement(element.getNameOfElement());
		_element.setPriceOfElement(element.getPriceOfElement());
		save(_element);
		return _element;
	}

	@Override
	public Repair addElementToRepair(Long repairId, Element element) {
		Repair repair = repairService.findById(repairId).get();
		Element _element = findById(element.getId()).get();
		_element.setQuantity(_element.getQuantity() - 1);
		repair.setCost(repair.getCost() + _element.getPriceOfElement());
		repair.addElement(_element);
		repairService.save(repair);
		return repair;
	}

	@Override
	public void deleteById(Long id) {
		existsById(id);
		elementRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		elementRepository.deleteAll();
	}

	@Override
	public void deleteByTypeId(long typeId) {
		typeService.existsById(typeId);
		elementRepository.deleteByTypeId(typeId);
	}

	@Override
	public void deleteElementFromRepair(Long repairId, Long elementId) {
		Repair repair = repairService.findById(repairId).get();
		repair.removeElement(elementId);
		repairService.save(repair);
	}
}
