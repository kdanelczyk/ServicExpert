package com.kamil.servicExpert.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kamil.servicExpert.db.model.Repair;

import jakarta.transaction.Transactional;

public interface RepairRepository extends JpaRepository<Repair, Long> {

	List<Repair> findByDeviceId(Long deviceId);
	
	List<Repair> findByUserId(Long userId);

	List<Repair> findByDateCreated(Date dateCreated);

	@Transactional
	void deleteByDeviceId(long deviceId);
	
	@Transactional
	void deleteByUserId(long userId);
}
