package com.kamil.servicExpert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.model.Type.TypeDtoGet;
import com.kamil.servicExpert.model.Type.TypeDtoGetDetails;
import com.kamil.servicExpert.model.Type.TypeDtoPost;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface TypeService {
	
	public boolean existsById(Long id);
	
	public Optional<TypeDtoGetDetails> findById(Long id);
	
	public List<TypeDtoGet> findAll();
	
	public TypeDtoGetDetails save(TypeDtoPost typeDtoPost);
	
	public TypeDtoGetDetails createType(TypeDtoPost typeDtoPost);
	
	public TypeDtoGetDetails updateType(Long id, TypeDtoPost typeDtoPost);
	
	public void deleteById(Long id);
	
	public void deleteAll();
	
}
