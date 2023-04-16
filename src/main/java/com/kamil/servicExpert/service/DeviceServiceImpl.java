package com.kamil.servicExpert.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.mapper.DeviceMapper;
import com.kamil.servicExpert.db.model.Device;
import com.kamil.servicExpert.exception.ResourceNotFoundException;
import com.kamil.servicExpert.model.Device.DeviceDtoGet;
import com.kamil.servicExpert.model.Device.DeviceDtoGetDetails;
import com.kamil.servicExpert.model.Device.DeviceDtoPost;
import com.kamil.servicExpert.repository.DeviceRepository;
import com.kamil.servicExpert.repository.TypeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DeviceServiceImpl implements DeviceService{

	private DeviceRepository deviceRepository;
	private TypeRepository typeRepository;
	private DeviceMapper deviceMapper;
	
	@Override
	public boolean existsById(Long id) {
		if (!deviceRepository.existsById(id)) {
			throw new ResourceNotFoundException("Not found Device with id = " + id);
		}
		return deviceRepository.existsById(id);
	}

	@Override
	public Optional<DeviceDtoGetDetails> findById(Long id) {
		return Optional.of(deviceMapper.deviceToDeviceGetDetails(deviceRepository.findById(id).get()));
	}

	@Override
	public List<DeviceDtoGet> findByRepaired(boolean repaired) {
		return deviceRepository.findByRepaired(repaired)
			.stream()
			.map(deviceMapper::deviceToDeviceGet)
			.collect(Collectors.toList());
	}

	@Override
	public List<DeviceDtoGet> findByTypeId(long typeId) {
		typeRepository.existsById(typeId);
		return deviceRepository.findByTypeId(typeId)
				.stream()
				.map(deviceMapper::deviceToDeviceGet)
				.collect(Collectors.toList());
	}

	@Override
	public List<DeviceDtoGet> findAll() {
		return deviceRepository.findAll()
				.stream()
				.map(deviceMapper::deviceToDeviceGet)
				.collect(Collectors.toList());
	}

	@Override
	public DeviceDtoGetDetails save(DeviceDtoPost deviceDtoPost) {
		return deviceMapper.deviceToDeviceGetDetails(deviceRepository.save(deviceMapper.devicePostToDevice(deviceDtoPost)));
	}

	@Override
	public DeviceDtoGetDetails createDeviceForType(Long typeId, DeviceDtoPost deviceDtoPost) {
		return deviceMapper.deviceToDeviceGetDetails(deviceRepository.save(Device.builder()
				.customerPhoneNumber(deviceDtoPost.getCustomerPhoneNumber())
				.customerName(deviceDtoPost.getCustomerName())
				.type(typeRepository.findById(typeId).get())
				.dateOfReceipt(new Date())
				.repaired(false).build()));
	}

	@Override
	public DeviceDtoGetDetails updateDevice(Long id, DeviceDtoPost deviceDtoPost) {
		Device deviceToUpdate = deviceRepository.findById(id).get();
		deviceToUpdate.setCustomerName(deviceDtoPost.getCustomerName());
		deviceToUpdate.setCustomerPhoneNumber(deviceDtoPost.getCustomerPhoneNumber());
		deviceRepository.save(deviceToUpdate);
		return deviceMapper.deviceToDeviceGetDetails(deviceToUpdate);
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
		typeRepository.existsById(typeId);
		deviceRepository.deleteById(typeId);
	}
	
}
