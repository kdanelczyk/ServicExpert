package com.kamil.servicExpert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.model.Device;
import jakarta.transaction.Transactional;

@Service
@Transactional
public interface DeviceService {
	
	public boolean existsById(Long id);

	public Optional<Device> findById(Long id);

	public List<Device> findByRepaired(boolean repaired);

	public List<Device> findByTypeId(long typeId);

	public List<Device> findAll();

	public Device save(Device device);
	
	public Device createDeviceForType(Long typeId, Device device);
	
	public Device updateDevice(Long id, Device device);

	public void deleteById(Long id);

	public void deleteAll();

	public void deleteByTypeId(long typeId);
}
