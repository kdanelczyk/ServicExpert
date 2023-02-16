package com.kamil.servicExpert.db.mapper;

import org.mapstruct.Mapper;

import com.kamil.servicExpert.db.model.UsedElement;
import com.kamil.servicExpert.model.UsedElement.UsedElementSlim;

@Mapper(componentModel = "spring")
public interface UsedElementMapper {

	UsedElementSlim usedElementToUsedElementSlim(UsedElement usedElement);
}
