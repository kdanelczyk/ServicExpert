package com.kamil.servicExpert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.model.Element;
import com.kamil.servicExpert.exception.ResourceNotFoundException;
import com.kamil.servicExpert.repository.ElementRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ElementServiceImpl implements ElementService{

	private ElementRepository elementRepository;
	private TypeService typeService;
	
	@Override
	public boolean existsById(Long id) {
		if (!elementRepository.existsById(id)) {
			throw new ResourceNotFoundException("Not found Element with id = " + id);
		}
		return elementRepository.existsById(id);
	}

	@Override
	public Optional<Element> findById(Long id) {
		return elementRepository.findById(id);
	}

	@Override
	public List<Element> findByTypeId(long typeId) {
		typeService.existsById(typeId);
		return elementRepository.findByTypeId(typeId);
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
		return save(Element.builder()
				.quantity(element.getQuantity())
				.criticalQuantity(element.getCriticalQuantity())
				.nameOfElement(element.getNameOfElement())
				.priceOfElement(element.getPriceOfElement())
				.type(typeService.findById(typeId).get()).build());
	}

	@Override
	public Element updateElement(Long id, Element element) {
		Element elementToUpdate = findById(id).get();
		elementToUpdate.setQuantity(element.getQuantity());
		elementToUpdate.setNameOfElement(element.getNameOfElement());
		elementToUpdate.setPriceOfElement(element.getPriceOfElement());
		save(elementToUpdate);
		return elementToUpdate;
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

}
