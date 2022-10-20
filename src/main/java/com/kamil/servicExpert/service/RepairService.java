package com.kamil.servicExpert.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.model.Repair;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface RepairService {
	
	public boolean existsById(Long id);
	
	public Optional<Repair> findById(Long id);
    
	public List<Repair> findByDeviceId(Long deviceId);
	
	public List<Repair> findByUserId(Long userId);

	public List<Repair> findRepairsByElementsId(Long elementId);

	public List<Repair> findByDateCreated(Date dateCreated);
	
	public List<Repair> findAll();
	
	public Repair save(Repair repair);
		
	public Repair createRepair(Long deviceId, Long userId, Repair repair);
	
	public Repair updateRepair(Long id, Repair repair);
	
	public void deleteById(Long id);
	
	public void deleteAll();
	
	public void deleteByDeviceId(long deviceId);
	
	public void deleteByUserId(long userId);
}
