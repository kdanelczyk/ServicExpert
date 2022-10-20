package com.kamil.servicExpert.db.mapper;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.Repair;
import com.kamil.servicExpert.model.Repair.RepairGet;
import com.kamil.servicExpert.model.Repair.RepairGetDetails;
import com.kamil.servicExpert.model.Repair.RepairPost;

@Component
public class RepairMapperImpl implements RepairMapper{
	
	@Autowired
	DeviceMapper deviceMapper;
	
	@Autowired
	ElementMapper elementMapper;

	@Override
	public Repair repairInputToRepair(RepairPost repairPost) {
		if(repairPost == null) {
			return null;
		}		
		return 	Repair.builder()
				.cost(repairPost.getCost())
				.note(repairPost.getNote())
				.dateCreated(new Date())
				.build();
	}

	@Override
	public RepairGet repairToRepairGet(Repair repair) {
		if(repair == null) {
			return null;
		}		
		return 	RepairGet.builder()
				.cost(repair.getCost())
				.dateCreated(repair.getDateCreated())
				.build();
	}

	@Override
	public RepairGetDetails repairToRepairGetDetails(Repair repair) {
		if(repair == null) {
			return null;
		}		
		return 	RepairGetDetails.builder()
				.dateCreated(repair.getDateCreated())
				.cost(repair.getCost())
				.note(repair.getNote())
				.device(deviceMapper.deviceToDeviceGet(repair.getDevice()))
				.elements(repair.getElements()
						.stream()
						.map(elementMapper::elementToElementSlim)
						.collect(Collectors.toList()))
				.build();
	}

}
