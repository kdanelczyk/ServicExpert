package com.kamil.servicExpert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.model.Element.ElementDtoGet;
import com.kamil.servicExpert.model.Element.ElementDtoGetDetails;
import com.kamil.servicExpert.model.Element.ElementDtoPost;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface ElementService {
	
	public boolean existsById(Long id);

	public Optional<ElementDtoGetDetails> findById(Long id);

	public List<ElementDtoGet> findByTypeId(long typeId);

	public List<ElementDtoGet> findAll();
	
	public ElementDtoGetDetails save(ElementDtoPost elementDtoPost);
	
	public ElementDtoGetDetails createElementForType(Long typeId, ElementDtoPost elementDtoPost);

	public ElementDtoGetDetails updateElement(Long id, ElementDtoPost elementDtoPost);
	
	public void deleteById(Long id);

	public void deleteAll();

	public void deleteByTypeId(long typeId);
	
}
