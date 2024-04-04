package com.kamil.servicExpert.db.mapper;

import com.kamil.servicExpert.db.model.Type;
import com.kamil.servicExpert.model.Type.TypeDtoGet;
import com.kamil.servicExpert.model.Type.TypeDtoGetDetails;
import com.kamil.servicExpert.model.Type.TypeDtoPost;

public interface TypeMapper {

	Type typePostToType(TypeDtoPost typeDtoPost);
	
	TypeDtoGet typeToTypeGet(Type type);
	
	TypeDtoGetDetails typesToTypeGetDetails(Type type);
	
}
