package com.kamil.servicExpert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.model.Device.DeviceDtoGet;
import com.kamil.servicExpert.model.Device.DeviceDtoGetDetails;
import com.kamil.servicExpert.model.Device.DeviceDtoPost;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface DeviceService {
	
	public boolean existsById(Long id);

	public Optional<DeviceDtoGetDetails> findById(Long id);

	public List<DeviceDtoGet> findByRepaired(boolean repaired);

	public List<DeviceDtoGet> findByTypeId(long typeId);

	public List<DeviceDtoGet> findAll();

	public DeviceDtoGetDetails save(DeviceDtoPost deviceDtoPost);
	
	public DeviceDtoGetDetails createDeviceForType(Long typeId, DeviceDtoPost deviceDtoPost);
	
	public DeviceDtoGetDetails updateDevice(Long id, DeviceDtoPost deviceDtoPost);

	public void deleteById(Long id);

	public void deleteAll();

	public void deleteByTypeId(long typeId);
	
}
