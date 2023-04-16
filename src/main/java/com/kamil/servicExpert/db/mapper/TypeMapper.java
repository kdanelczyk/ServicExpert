package com.kamil.servicExpert.db.mapper;

import org.mapstruct.Mapper;

import com.kamil.servicExpert.db.model.Type;
import com.kamil.servicExpert.model.Type.TypeDtoGet;
import com.kamil.servicExpert.model.Type.TypeDtoGetDetails;
import com.kamil.servicExpert.model.Type.TypeDtoPost;

@Mapper(componentModel = "spring")
public interface TypeMapper {

	Type typePostToType(TypeDtoPost typeDtoPost);
	
	TypeDtoGet typeToTypeGet(Type type);
	
	TypeDtoGetDetails typesToTypeGetDetails(Type type);
	
}
