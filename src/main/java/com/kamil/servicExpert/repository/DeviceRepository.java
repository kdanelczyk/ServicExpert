package com.kamil.servicExpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamil.servicExpert.db.model.Device;

import jakarta.transaction.Transactional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

	List<Device> findByRepaired(boolean repaired);

	List<Device> findByTypeId(long typeId);

	@Transactional
	void deleteByTypeId(long typeId);
}