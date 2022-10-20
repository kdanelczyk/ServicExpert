package com.kamil.servicExpert.db.mapper;

import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.Element;
import com.kamil.servicExpert.model.Element.ElementGet;
import com.kamil.servicExpert.model.Element.ElementGetDetails;
import com.kamil.servicExpert.model.Element.ElementPost;
import com.kamil.servicExpert.model.Element.ElementSlim;

@Component
public class ElementMapperImpl implements ElementMapper{

	@Override
	public Element elementInputToElement(ElementPost elementPost) {
		if(elementPost == null) {
			return null;
		}		
		return 	Element.builder()
				.quantity(elementPost.getQuantity())
				.criticalQuantity(elementPost.getCriticalQuantity())
				.nameOfElement(elementPost.getNameOfElement())
				.priceOfElement(elementPost.getPriceOfElement())
				.build();
	}
	
	@Override
	public ElementSlim elementToElementSlim(Element element) {
		if(element == null) {
			return null;
		}		
		return 	ElementSlim.builder()
				.nameOfElement(element.getNameOfElement())
				.build();
	}

	@Override
	public ElementGet elementToElementGet(Element element) {
		if(element == null) {
			return null;
		}		
		return 	ElementGet.builder()
				.quantity(element.getQuantity())
				.nameOfElement(element.getNameOfElement())
				.build();
	}

	@Override
	public ElementGetDetails elementToElementGetDetails(Element element) {
		if(element == null) {
			return null;
		}		
		return 	ElementGetDetails.builder()
				.quantity(element.getQuantity())
				.criticalQuantity(element.getCriticalQuantity())
				.nameOfElement(element.getNameOfElement())
				.priceOfElement(element.getPriceOfElement())
				.build();
	}

}
