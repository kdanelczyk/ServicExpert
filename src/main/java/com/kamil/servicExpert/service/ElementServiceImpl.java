package com.kamil.servicExpert.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.mapper.ElementMapper;
import com.kamil.servicExpert.db.model.Element;
import com.kamil.servicExpert.exception.ResourceNotFoundException;
import com.kamil.servicExpert.model.Element.ElementDtoGet;
import com.kamil.servicExpert.model.Element.ElementDtoGetDetails;
import com.kamil.servicExpert.model.Element.ElementDtoPost;
import com.kamil.servicExpert.repository.ElementRepository;
import com.kamil.servicExpert.repository.TypeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ElementServiceImpl implements ElementService{

	private ElementRepository elementRepository;
	private TypeRepository typeRepository;
	private ElementMapper elementMapper;
	
	@Override
	public boolean existsById(Long id) {
		if (!elementRepository.existsById(id)) {
			throw new ResourceNotFoundException("Not found Element with id = " + id);
		}
		return elementRepository.existsById(id);
	}

	@Override
	public Optional<ElementDtoGetDetails> findById(Long id) {
		return Optional.of(elementMapper.elementToElementGetDetails(elementRepository.findById(id).get()));
	}

	@Override
	public List<ElementDtoGet> findByTypeId(long typeId) {
		typeRepository.existsById(typeId);
		return elementRepository.findByTypeId(typeId)
				.stream()
				.map(elementMapper::elementToElementGet)
				.collect(Collectors.toList());
	}

	@Override
	public List<ElementDtoGet> findAll() {
		return elementRepository.findAll()
				.stream()
				.map(elementMapper::elementToElementGet)
				.collect(Collectors.toList());
	}

	@Override
	public ElementDtoGetDetails save(ElementDtoPost elementDtoPost) {
		return elementMapper.elementToElementGetDetails(elementRepository.save(elementMapper.elementPostToElement(elementDtoPost)));
	}

	@Override
	public ElementDtoGetDetails createElementForType(Long typeId, ElementDtoPost elementDtoPost) {
		return elementMapper.elementToElementGetDetails(elementRepository.save(Element.builder()
				.quantity(elementDtoPost.getQuantity())
				.criticalQuantity(elementDtoPost.getCriticalQuantity())
				.elementName(elementDtoPost.getElementName())
				.elementPrice(elementDtoPost.getElementPrice())
				.type(typeRepository.findById(typeId).get()).build()));
	}

	@Override
	public ElementDtoGetDetails updateElement(Long id, ElementDtoPost elementDtoPost) {
		Element elementToUpdate = elementRepository.findById(id).get();
		elementToUpdate.setQuantity(elementDtoPost.getQuantity());
		elementToUpdate.setElementName(elementDtoPost.getElementName());
		elementToUpdate.setElementPrice(elementDtoPost.getElementPrice());
		elementRepository.save(elementToUpdate);
		return elementMapper.elementToElementGetDetails(elementToUpdate);
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
		typeRepository.existsById(typeId);
		elementRepository.deleteByTypeId(typeId);
	}

}
