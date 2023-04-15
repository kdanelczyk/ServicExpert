package com.kamil.servicExpert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.model.Repair.RepairDtoGet;
import com.kamil.servicExpert.model.Repair.RepairDtoGetDetails;
import com.kamil.servicExpert.model.Repair.RepairDtoPost;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface RepairService {
	
	public boolean existsById(Long id);
	
	public Optional<RepairDtoGetDetails> findById(Long id);
    
	public List<RepairDtoGet> findByDeviceId(Long deviceId);
	
	public List<RepairDtoGet> findByUserId(Long userId);

	public List<RepairDtoGet> findAll();
	
	public RepairDtoGetDetails save(RepairDtoPost repair);
		
	public RepairDtoGetDetails createRepair(Long deviceId, Long userId, RepairDtoPost repairDtoPost);
	
	public RepairDtoGetDetails updateRepair(Long id, RepairDtoPost repairDtoPost);
	
	public RepairDtoGetDetails addElementToRepair(Long repairId, Long elementId);
	
	public void deleteElementFromRepair(Long repairId, Long elementId);
	
	public void deleteById(Long id);
	
	public void deleteAll();
	
	public void deleteByDeviceId(long deviceId);
	
	public void deleteByUserId(long userId);
	
}
