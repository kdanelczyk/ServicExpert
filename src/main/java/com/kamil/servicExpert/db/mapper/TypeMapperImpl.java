package com.kamil.servicExpert.db.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.Type;
import com.kamil.servicExpert.model.Type.TypeDtoGet;
import com.kamil.servicExpert.model.Type.TypeDtoGetDetails;
import com.kamil.servicExpert.model.Type.TypeDtoPost;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class TypeMapperImpl implements TypeMapper{
	
	ElementMapper elementMapper;

	@Override
	public Type typePostToType(TypeDtoPost typeDtoPost) {
		if(typeDtoPost == null) {
			return null;
		}		
		return 	Type
				.builder()
				.typeName(typeDtoPost.getTypeName())
				.build();
	}

	@Override
	public TypeDtoGet typeToTypeGet(Type type) {
		if(type == null) {
			return null;
		}		
		return 	TypeDtoGet
				.builder()
				.typeName(type.getTypeName())
				.build();
	}

	@Override
	public TypeDtoGetDetails typesToTypeGetDetails(Type type) {
		if(type == null) {
			return null;
		}		
		return 	TypeDtoGetDetails
				.builder()
				.typeName(type.getTypeName())
				.elements(type.getElements()
						.stream()
						.map(elementMapper::elementToElementSlim)
						.collect(Collectors.toList()))
				.build();
	}
	
}
