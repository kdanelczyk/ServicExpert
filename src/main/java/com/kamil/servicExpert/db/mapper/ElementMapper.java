package com.kamil.servicExpert.db.mapper;

import org.mapstruct.Mapper;

import com.kamil.servicExpert.db.model.Element;
import com.kamil.servicExpert.model.Element.ElementDtoGet;
import com.kamil.servicExpert.model.Element.ElementDtoGetDetails;
import com.kamil.servicExpert.model.Element.ElementDtoPost;
import com.kamil.servicExpert.model.Element.ElementDtoGetSlim;

@Mapper(componentModel = "spring")
public interface ElementMapper {
	
	Element elementPostToElement(ElementDtoPost elementDtoPost);
	
	ElementDtoGetSlim elementToElementSlim(Element element);
	
	ElementDtoGet elementToElementGet(Element element);
	
	ElementDtoGetDetails elementToElementGetDetails(Element element);
	
}
