package com.kamil.servicExpert.db.mapper;

import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.UsedElement;
import com.kamil.servicExpert.model.UsedElement.UsedElementDtoPost;
import com.kamil.servicExpert.model.UsedElement.UsedElementDtoSlim;

@Component
public class UsedElementMapperImpl implements UsedElementMapper{

	@Override
	public UsedElement usedElementPostToUsedElement(UsedElementDtoPost usedElement) {
		if(usedElement == null) {
			return null;
		}
		return 	UsedElement
				.builder()
				.elementPrice(usedElement.getElementPrice())
				.elementName(usedElement.getElementName())
				.build();
	}
	
	@Override
	public UsedElementDtoSlim usedElementToUsedElementSlim(UsedElement usedElement) {
		if(usedElement == null) {
			return null;
		}		
		return 	UsedElementDtoSlim
				.builder()
				.elementName(usedElement.getElementName())
				.build();
	}
	
}
