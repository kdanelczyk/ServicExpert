package com.kamil.servicExpert.db.mapper;

import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.Element;
import com.kamil.servicExpert.model.Element.ElementDtoGet;
import com.kamil.servicExpert.model.Element.ElementDtoGetDetails;
import com.kamil.servicExpert.model.Element.ElementDtoPost;

import lombok.AllArgsConstructor;

import com.kamil.servicExpert.model.Element.ElementDtoGetSlim;

@AllArgsConstructor
@Component
public class ElementMapperImpl implements ElementMapper{

	@Override
	public Element elementPostToElement(ElementDtoPost elementDtoPost) {
		if(elementDtoPost == null) {
			return null;
		}		
		return 	Element
				.builder()
				.quantity(elementDtoPost.getQuantity())
				.criticalQuantity(elementDtoPost.getCriticalQuantity())
				.elementName(elementDtoPost.getElementName())
				.elementPrice(elementDtoPost.getElementPrice())
				.build();
	}
	
	@Override
	public ElementDtoGetSlim elementToElementSlim(Element element) {
		if(element == null) {
			return null;
		}		
		return 	ElementDtoGetSlim
				.builder()
				.elementName(element.getElementName())
				.build();
	}

	@Override
	public ElementDtoGet elementToElementGet(Element element) {
		if(element == null) {
			return null;
		}		
		return 	ElementDtoGet
				.builder()
				.quantity(element.getQuantity())
				.elementName(element.getElementName())
				.build();
	}

	@Override
	public ElementDtoGetDetails elementToElementGetDetails(Element element) {
		if(element == null) {
			return null;
		}		
		return 	ElementDtoGetDetails
				.builder()
				.quantity(element.getQuantity())
				.criticalQuantity(element.getCriticalQuantity())
				.elementName(element.getElementName())
				.elementPrice(element.getElementPrice())
				.build();
	}

}
