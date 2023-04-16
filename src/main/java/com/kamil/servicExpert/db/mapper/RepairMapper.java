package com.kamil.servicExpert.db.mapper;

import org.mapstruct.Mapper;

import com.kamil.servicExpert.db.model.Repair;
import com.kamil.servicExpert.model.Repair.RepairDtoGet;
import com.kamil.servicExpert.model.Repair.RepairDtoGetDetails;
import com.kamil.servicExpert.model.Repair.RepairDtoPost;

@Mapper(componentModel = "spring")
public interface RepairMapper {
	
	Repair repairPostToRepair(RepairDtoPost repairDtoPost);
	
	RepairDtoGet repairToRepairGet(Repair device);
	
	RepairDtoGetDetails repairToRepairGetDetails(Repair repair);
	
}
