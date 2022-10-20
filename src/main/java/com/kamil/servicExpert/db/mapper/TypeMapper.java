package com.kamil.servicExpert.db.mapper;

import org.mapstruct.Mapper;

import com.kamil.servicExpert.db.model.Type;
import com.kamil.servicExpert.model.Type.TypeGet;
import com.kamil.servicExpert.model.Type.TypeGetDetails;
import com.kamil.servicExpert.model.Type.TypePost;

@Mapper(componentModel = "spring")
public interface TypeMapper {

	Type typeInputToType(TypePost typePost);
	
	TypeGet typeToTypeGet(Type type);
	
	TypeGetDetails typesToTypeGetDetails(Type type);
}
