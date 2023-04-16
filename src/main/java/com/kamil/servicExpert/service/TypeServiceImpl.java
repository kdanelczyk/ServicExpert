package com.kamil.servicExpert.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.mapper.TypeMapper;
import com.kamil.servicExpert.db.model.Type;
import com.kamil.servicExpert.exception.ResourceNotFoundException;
import com.kamil.servicExpert.model.Type.TypeDtoGet;
import com.kamil.servicExpert.model.Type.TypeDtoGetDetails;
import com.kamil.servicExpert.model.Type.TypeDtoPost;
import com.kamil.servicExpert.repository.TypeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TypeServiceImpl implements TypeService{

    private TypeRepository typeRepository;
    private TypeMapper typeMapper;
	
	@Override
	public boolean existsById(Long id) {
		if (!typeRepository.existsById(id)) {
			throw new ResourceNotFoundException("Not found Type with id = " + id);
		}
		return typeRepository.existsById(id);
	}

	@Override
	public Optional<TypeDtoGetDetails> findById(Long id) {
		return Optional.of(typeMapper.typesToTypeGetDetails(typeRepository.findById(id).get()));
		
	}

	@Override
	public List<TypeDtoGet> findAll() {
		return typeRepository.findAll()
				.stream()
				.map(typeMapper::typeToTypeGet)
				.collect(Collectors.toList());
	}

	@Override
	public TypeDtoGetDetails save(TypeDtoPost typeDtoPost) {
		return typeMapper.typesToTypeGetDetails(typeRepository.save(typeMapper.typePostToType(typeDtoPost)));
		
	}

	@Override
	public TypeDtoGetDetails createType(TypeDtoPost typeDtoPost) {
		return typeMapper.typesToTypeGetDetails(typeRepository.save(Type.builder()
				.typeName(typeDtoPost.getTypeName())
				.devices(new ArrayList<>())
				.elements(new ArrayList<>())
				.build()));
	}

	@Override
	public TypeDtoGetDetails updateType(Long id, TypeDtoPost typeDtoPost) {
		Type typeToUpdate = typeRepository.findById(id).get();
		typeToUpdate.setTypeName(typeDtoPost.getTypeName());
		typeRepository.save(typeToUpdate);
		return typeMapper.typesToTypeGetDetails(typeToUpdate);
	}
	
	@Override
	public void deleteById(Long id) {
		existsById(id);
		typeRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		typeRepository.deleteAll();
	}
	
}
