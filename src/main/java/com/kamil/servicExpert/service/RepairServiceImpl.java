package com.kamil.servicExpert.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.mapper.RepairMapper;
import com.kamil.servicExpert.db.model.Device;
import com.kamil.servicExpert.db.model.Element;
import com.kamil.servicExpert.db.model.Repair;
import com.kamil.servicExpert.db.model.UsedElement;
import com.kamil.servicExpert.exception.BadRequestException;
import com.kamil.servicExpert.exception.ResourceNotFoundException;
import com.kamil.servicExpert.model.Repair.RepairDtoGet;
import com.kamil.servicExpert.model.Repair.RepairDtoGetDetails;
import com.kamil.servicExpert.model.Repair.RepairDtoPost;
import com.kamil.servicExpert.repository.DeviceRepository;
import com.kamil.servicExpert.repository.ElementRepository;
import com.kamil.servicExpert.repository.RepairRepository;
import com.kamil.servicExpert.repository.UsedElementRepository;
import com.kamil.servicExpert.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RepairServiceImpl implements RepairService{

    private RepairRepository repairRepository;
	private DeviceRepository deviceRepository;
	private UserRepository userRepository;
	private ElementRepository elementRepository;
	private UsedElementRepository usedElementRepository;
	private RepairMapper repairMapper;
	
	@Override
	public boolean existsById(Long id) {
		if (!repairRepository.existsById(id)) {
			throw new ResourceNotFoundException("Not found Repair with id = " + id);
		}
		return repairRepository.existsById(id);
	}

	@Override
	public Optional<RepairDtoGetDetails> findById(Long id) {
		return Optional.of(repairMapper.repairToRepairGetDetails(repairRepository.findById(id).get()));
	}

	@Override
	public List<RepairDtoGet> findByDeviceId(Long deviceId) {
		deviceRepository.existsById(deviceId);
		return repairRepository.findByDeviceId(deviceId)
				.stream()
				.map(repairMapper::repairToRepairGet)
				.collect(Collectors.toList());
	}

	@Override
	public List<RepairDtoGet> findByUserId(Long userId) {
		userRepository.existsById(userId);
		return repairRepository.findByUserId(userId)
				.stream()
				.map(repairMapper::repairToRepairGet)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteByDeviceId(long deviceId) {
		deviceRepository.existsById(deviceId);
		repairRepository.deleteByDeviceId(deviceId);
	}

	@Override
	public List<RepairDtoGet> findAll() {
		return repairRepository.findAll()
				.stream()
				.map(repairMapper::repairToRepairGet)
				.collect(Collectors.toList());
	}

	@Override
	public RepairDtoGetDetails save(RepairDtoPost repairDtoPost) {
		return repairMapper.repairToRepairGetDetails(repairRepository.save(repairMapper.repairInputToRepair(repairDtoPost)));
	}

	@Override
	public RepairDtoGetDetails createRepair(Long deviceId, Long userId, RepairDtoPost repairDtoPost) {
		Device device = deviceRepository.findById(deviceId).get();
		device.setRepaired(true);
		return repairMapper.repairToRepairGetDetails(repairRepository.save(Repair
				.builder()
				.cost(repairDtoPost.getCost())
				.note(repairDtoPost.getNote())
				.dateCreated(new Date())
				.device(device)
				.user(userRepository.findById(userId).get())
				.usedElements(new ArrayList<>())
				.build()));
	}

	@Override
	public RepairDtoGetDetails updateRepair(Long id, RepairDtoPost repairDtoPost) {
		Repair repairToUpdate = repairRepository.findById(id).get();
		repairToUpdate.setCost(repairDtoPost.getCost());
		repairRepository.save(repairToUpdate);
		return repairMapper.repairToRepairGetDetails(repairToUpdate);
	}
	
	@Override
	public RepairDtoGetDetails addElementToRepair(Long repairId, Long elementId) {
		Repair repair = repairRepository.findById(repairId).get();
		Element element = elementRepository.findById(elementId).get();

		if(element.getQuantity() <= 0) {
			throw new BadRequestException("Quantity is 0!");
		}
		
		element.setQuantity(element.getQuantity() - 1);
		repair.setCost(repair.getCost().add(element.getPriceOfElement()));
		
		UsedElement usedElement = usedElementRepository.save(UsedElement
				.builder()
				.nameOfElement(element.getNameOfElement())
				.priceOfElement(element.getPriceOfElement())
				.repair(repair)
				.build());
		
		List<UsedElement> usedElements = repair.getUsedElements();
		usedElements.add(usedElement);
		repair.setUsedElements(usedElements);
		repairRepository.save(repair);
		return repairMapper.repairToRepairGetDetails(repair);
	}
	
	@Override
	public void deleteElementFromRepair(Long repairId, Long elementId) {
		Repair repair = repairRepository.findById(repairId).get();
		UsedElement usedElement = repair.getUsedElements()
				.stream()
				.filter(t -> t.getId() == elementId)
				.findFirst().orElse(null);
		if (usedElement != null) {
			repair.getUsedElements().remove(usedElement);
			usedElementRepository.deleteById(elementId);
		}
		repairRepository.save(repair);
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
