package com.kamil.servicExpert.db.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.Type;
import com.kamil.servicExpert.model.Type.TypeGet;
import com.kamil.servicExpert.model.Type.TypeGetDetails;
import com.kamil.servicExpert.model.Type.TypePost;

@Component
public class TypeMapperImpl implements TypeMapper{
	
	ElementMapper elementMapper;
	
	public TypeMapperImpl(ElementMapper elementMapper) {
		this.elementMapper = elementMapper;
	}

	@Override
	public Type typeInputToType(TypePost typePost) {
		if(typePost == null) {
			return null;
		}		
		return 	Type.builder()
				.nameOfType(typePost.getNameOfType())
				.build();
	}

	@Override
	public TypeGet typeToTypeGet(Type type) {
		if(type == null) {
			return null;
		}		
		return 	TypeGet.builder()
				.nameOfType(type.getNameOfType())
				.build();
	}

	@Override
	public TypeGetDetails typesToTypeGetDetails(Type type) {
		if(type == null) {
			return null;
		}		
		return 	TypeGetDetails.builder()
				.nameOfType(type.getNameOfType())
				.elements(type.getElements()
						.stream()
						.map(elementMapper::elementToElementSlim)
						.collect(Collectors.toList()))
				.build();
	}
	
}
