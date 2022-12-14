package com.kamil.servicExpert.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.model.Device;
import com.kamil.servicExpert.db.model.Type;
import com.kamil.servicExpert.exception.ResourceNotFoundException;
import com.kamil.servicExpert.repository.DeviceRepository;

@Service
public class DeviceServiceImpl implements DeviceService{

	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private TypeService typeService;
	
	@Override
	public boolean existsById(Long id) {
		if (!deviceRepository.existsById(id)) {
			throw new ResourceNotFoundException("Not found Device with id = " + id);
		}
		return deviceRepository.existsById(id);
	}

	@Override
	public Optional<Device> findById(Long id) {
		Optional<Device> device = deviceRepository.findById(id);
		return device;
	}

	@Override
	public List<Device> findByRepaired(boolean repaired) {
		return deviceRepository.findByRepaired(repaired);
	}

	@Override
	public List<Device> findByTypeId(long typeId) {
		typeService.existsById(typeId);
		return deviceRepository.findByTypeId(typeId);
	}

	@Override
	public List<Device> findAll() {
		return deviceRepository.findAll();
	}

	@Override
	public Device save(Device device) {
		return deviceRepository.save(device);
	}

	@Override
	public Device createDeviceForType(Long typeId, Device device) {
		Type type = typeService.findById(typeId).get();
		
		return save(Device.builder()
				.customerPhoneNumber(device.getCustomerPhoneNumber())
				.nameOfCustomer(device.getNameOfCustomer())
				.type(type)
				.dateOfReceipt(new Date()).repaired(false).build());
	}

	@Override
	public Device updateDevice(Long id, Device device) {
		Device _device = findById(id).get();
		_device.setNameOfCustomer(device.getNameOfCustomer());
		_device.setCustomerPhoneNumber(device.getCustomerPhoneNumber());
		save(_device);
		return _device;
	}

	@Override
	public void deleteById(Long id) {
		existsById(id);
		deviceRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		deviceRepository.deleteAll();
	}

	@Override
	public void deleteByTypeId(long typeId) {
		typeService.existsById(typeId);
		deviceRepository.deleteById(typeId);
	}
	
}
