package com.kamil.servicExpert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.model.Type;
import com.kamil.servicExpert.exception.ResourceNotFoundException;
import com.kamil.servicExpert.repository.TypeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TypeServiceImpl implements TypeService{

    private TypeRepository typeRepository;
	
	@Override
	public boolean existsById(Long id) {
		if (!typeRepository.existsById(id)) {
			throw new ResourceNotFoundException("Not found Type with id = " + id);
		}
		return typeRepository.existsById(id);
	}

	@Override
	public Optional<Type> findById(Long id) {
		return typeRepository.findById(id);
	}

	@Override
	public List<Type> findAll() {
		return typeRepository.findAll();
	}

	@Override
	public Type save(Type type) {
		return typeRepository.save(type);
	}

	@Override
	public Type createType(Type type) {
		return save(Type.builder()
				.nameOfType(type.getNameOfType())
				.build());
	}

	@Override
	public Type updateType(Long id, Type type) {
		Type typeToUpdate = findById(id).get();
		typeToUpdate.setNameOfType(type.getNameOfType());
		save(typeToUpdate);
		return typeToUpdate;
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
