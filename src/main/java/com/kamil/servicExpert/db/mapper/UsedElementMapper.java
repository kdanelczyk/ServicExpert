package com.kamil.servicExpert.db.mapper;

import com.kamil.servicExpert.db.model.UsedElement;
import com.kamil.servicExpert.model.UsedElement.UsedElementDtoPost;
import com.kamil.servicExpert.model.UsedElement.UsedElementDtoSlim;

public interface UsedElementMapper {

	UsedElement usedElementPostToUsedElement(UsedElementDtoPost usedElement);
	
	UsedElementDtoSlim usedElementToUsedElementSlim(UsedElement usedElement);
	
}
