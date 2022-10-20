package com.kamil.servicExpert.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.model.Device;
import com.kamil.servicExpert.db.model.Repair;
import com.kamil.servicExpert.db.model.User;
import com.kamil.servicExpert.exception.ResourceNotFoundException;
import com.kamil.servicExpert.repository.RepairRepository;

@Service
public class RepairServiceImpl implements RepairService{
	
    @Autowired
    private RepairRepository repairRepository;

	@Autowired
	private DeviceService deviceService;
    
	@Autowired
	private UserService userService;
	
	@Override
	public boolean existsById(Long id) {
		if (!repairRepository.existsById(id)) {
			throw new ResourceNotFoundException("Not found Repair with id = " + id);
		}
		return repairRepository.existsById(id);
	}

	@Override
	public Optional<Repair> findById(Long id) {
		Optional<Repair> repair = repairRepository.findById(id);
		return repair;
	}

	@Override
	public List<Repair> findByDeviceId(Long deviceId) {
		deviceService.existsById(deviceId);
		return repairRepository.findByDeviceId(deviceId);
	}

	@Override
	public List<Repair> findByUserId(Long userId) {
		userService.existsById(userId);
		return repairRepository.findByUserId(userId);
	}

	@Override
	public List<Repair> findRepairsByElementsId(Long elementId) {
		return repairRepository.findRepairsByElementsId(elementId);
	}

	@Override
	public List<Repair> findByDateCreated(Date dateCreated) {
		return repairRepository.findByDateCreated(dateCreated);
	}

	@Override
	public void deleteByDeviceId(long deviceId) {
		deviceService.existsById(deviceId);
		repairRepository.deleteByDeviceId(deviceId);
	}

	@Override
	public List<Repair> findAll() {
		return repairRepository.findAll();
	}

	@Override
	public Repair save(Repair repair) {
		return repairRepository.save(repair);
	}

	@Override
	public Repair createRepair(Long deviceId, Long userId, Repair repair) {
		Device device = deviceService.findById(deviceId).get();
		User user = userService.findById(userId).get();
		device.setRepaired(true);
		return save(Repair.builder()
				.cost(repair.getCost())
				.note(repair.getNote())
				.dateCreated(new Date())
				.device(device)
				.user(user)
				.build());
	}

	@Override
	public Repair updateRepair(Long id, Repair repair) {
		Repair _repair = findById(id).get();
		_repair.setCost(repair.getCost());
		save(_repair);
		return _repair;
	}

	@Override
	public void deleteById(Long id) {
		existsById(id);
		repairRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		repairRepository.deleteAll();
	}

	@Override
	public void deleteByUserId(long userId) {
		repairRepository.deleteByUserId(userId);
	}
}
