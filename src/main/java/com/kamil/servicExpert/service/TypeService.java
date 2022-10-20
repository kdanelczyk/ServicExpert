package com.kamil.servicExpert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.model.Type;
import jakarta.transaction.Transactional;

@Service
@Transactional
public interface TypeService {
	
	public boolean existsById(Long id);
	
	public Optional<Type> findById(Long id);
	
	public List<Type> findAll();
	
	public Type save(Type type);
	
	public Type createType(Type type);
	
	public Type updateType(Long id, Type type);
	
	public void deleteById(Long id);
	
	public void deleteAll();
}
