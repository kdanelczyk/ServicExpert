package com.kamil.servicExpert.db.mapper;

import org.mapstruct.Mapper;

import com.kamil.servicExpert.db.model.Element;
import com.kamil.servicExpert.model.Element.ElementGet;
import com.kamil.servicExpert.model.Element.ElementGetDetails;
import com.kamil.servicExpert.model.Element.ElementPost;
import com.kamil.servicExpert.model.Element.ElementSlim;

@Mapper(componentModel = "spring")
public interface ElementMapper {
	
	Element elementInputToElement(ElementPost elementPost);
	
	ElementSlim elementToElementSlim(Element element);
	
	ElementGet elementToElementGet(Element element);
	
	ElementGetDetails elementToElementGetDetails(Element element);
}
