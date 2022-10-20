package com.kamil.servicExpert.db.mapper;

import org.mapstruct.Mapper;

import com.kamil.servicExpert.db.model.Repair;
import com.kamil.servicExpert.model.Repair.RepairGet;
import com.kamil.servicExpert.model.Repair.RepairGetDetails;
import com.kamil.servicExpert.model.Repair.RepairPost;

@Mapper(componentModel = "spring")
public interface RepairMapper {
	
	Repair repairInputToRepair(RepairPost repairPost);
	
	RepairGet repairToRepairGet(Repair device);
	
	RepairGetDetails repairToRepairGetDetails(Repair repair);
}
