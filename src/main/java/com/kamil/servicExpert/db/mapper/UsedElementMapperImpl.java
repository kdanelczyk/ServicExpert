package com.kamil.servicExpert.db.mapper;

import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.UsedElement;
import com.kamil.servicExpert.model.UsedElement.UsedElementPost;
import com.kamil.servicExpert.model.UsedElement.UsedElementSlim;

@Component
public class UsedElementMapperImpl implements UsedElementMapper{

	@Override
	public UsedElement usedElementPostToUsedElement(UsedElementPost usedElement) {
		if(usedElement == null) {
			return null;
		}
		return 	UsedElement.builder()
				.priceOfElement(usedElement.getPriceOfElement())
				.nameOfElement(usedElement.getNameOfElement())
				.build();
	}
	
	@Override
	public UsedElementSlim usedElementToUsedElementSlim(UsedElement usedElement) {
		if(usedElement == null) {
			return null;
		}		
		return 	UsedElementSlim.builder()
				.nameOfElement(usedElement.getNameOfElement())
				.build();
	}
}
