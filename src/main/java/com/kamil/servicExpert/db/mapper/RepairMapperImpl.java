package com.kamil.servicExpert.db.mapper;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.Repair;
import com.kamil.servicExpert.model.Repair.RepairDtoGet;
import com.kamil.servicExpert.model.Repair.RepairDtoGetDetails;
import com.kamil.servicExpert.model.Repair.RepairDtoPost;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class RepairMapperImpl implements RepairMapper{
	
	DeviceMapper deviceMapper;
	UsedElementMapper usedElementMapper;

	@Override
	public Repair repairPostToRepair(RepairDtoPost repairDtoPost) {
		if(repairDtoPost == null) {
			return null;
		}		
		return 	Repair
				.builder()
				.cost(repairDtoPost.getCost())
				.note(repairDtoPost.getNote())
				.dateCreated(new Date())
				.build();
	}

	@Override
	public RepairDtoGet repairToRepairGet(Repair repair) {
		if(repair == null) {
			return null;
		}		
		return 	RepairDtoGet
				.builder()
				.cost(repair.getCost())
				.dateCreated(repair.getDateCreated())
				.build();
	}

	@Override
	public RepairDtoGetDetails repairToRepairGetDetails(Repair repair) {
		if(repair == null) {
			return null;
		}		
		return 	RepairDtoGetDetails
				.builder()
				.dateCreated(repair.getDateCreated())
				.cost(repair.getCost())
				.note(repair.getNote())
				.device(deviceMapper.deviceToDeviceGet(repair.getDevice()))
				.usedElements(repair.getUsedElements()
						.stream()
						.map(usedElementMapper::usedElementToUsedElementSlim)
						.collect(Collectors.toList()))
				.build();
	}

}
