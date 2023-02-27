package com.kamil.servicExpert.db.mapper;

import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.Element;
import com.kamil.servicExpert.model.Element.ElementDtoGet;
import com.kamil.servicExpert.model.Element.ElementDtoGetDetails;
import com.kamil.servicExpert.model.Element.ElementDtoPost;
import com.kamil.servicExpert.model.Element.ElementDtoGetSlim;

@Component
public class ElementMapperImpl implements ElementMapper{

	@Override
	public Element elementInputToElement(ElementDtoPost elementDtoPost) {
		if(elementDtoPost == null) {
			return null;
		}		
		return 	Element.builder()
				.quantity(elementDtoPost.getQuantity())
				.criticalQuantity(elementDtoPost.getCriticalQuantity())
				.nameOfElement(elementDtoPost.getNameOfElement())
				.priceOfElement(elementDtoPost.getPriceOfElement())
				.build();
	}
	
	@Override
	public ElementDtoGetSlim elementToElementSlim(Element element) {
		if(element == null) {
			return null;
		}		
		return 	ElementDtoGetSlim.builder()
				.nameOfElement(element.getNameOfElement())
				.build();
	}

	@Override
	public ElementDtoGet elementToElementGet(Element element) {
		if(element == null) {
			return null;
		}		
		return 	ElementDtoGet.builder()
				.quantity(element.getQuantity())
				.nameOfElement(element.getNameOfElement())
				.build();
	}

	@Override
	public ElementDtoGetDetails elementToElementGetDetails(Element element) {
		if(element == null) {
			return null;
		}		
		return 	ElementDtoGetDetails.builder()
				.quantity(element.getQuantity())
				.criticalQuantity(element.getCriticalQuantity())
				.nameOfElement(element.getNameOfElement())
				.priceOfElement(element.getPriceOfElement())
				.build();
	}

}
