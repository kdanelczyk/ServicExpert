package com.kamil.servicExpert.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamil.servicExpert.db.mapper.DeviceMapper;
import com.kamil.servicExpert.db.model.Device;
import com.kamil.servicExpert.model.Device.DeviceGet;
import com.kamil.servicExpert.model.Device.DeviceGetDetails;
import com.kamil.servicExpert.model.Device.DevicePost;
import com.kamil.servicExpert.service.DeviceService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class DeviceController {

	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private DeviceMapper deviceMapper;

	@GetMapping("/devices/repaired")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<CollectionModel<DeviceGet>> findByRepaired() {
		if (deviceService.findByRepaired(true).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(CollectionModel.of(deviceService.findByRepaired(true)
				.stream()
				.map(deviceMapper::deviceToDeviceGet)
				.collect(Collectors.toList()),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.findByRepaired())
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.findByNotRepaired())
						.withRel("not-repaired-devices"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllDevices())
						.withRel("all-devices")));
	}

	@GetMapping("/devices/not-repaired")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<CollectionModel<DeviceGet>> findByNotRepaired() {
		if (deviceService.findByRepaired(false).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(CollectionModel.of(deviceService.findByRepaired(false)
				.stream()
				.map(deviceMapper::deviceToDeviceGet)
				.collect(Collectors.toList()),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.findByNotRepaired())
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.findByRepaired())
						.withRel("not-repaired-devices"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllDevices())
						.withRel("all-devices")));
	}

	@GetMapping("/devices")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<CollectionModel<DeviceGet>> getAllDevices() {
		if (deviceService.findAll().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(CollectionModel.of(deviceService.findAll()
				.stream()
				.map(deviceMapper::deviceToDeviceGet)
				.collect(Collectors.toList()),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllDevices())
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.findByRepaired())
						.withRel("repaired-devices"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.findByNotRepaired())
						.withRel("not-repaired-devices"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.deleteAllDevices())
						.withRel("delete-devices")));
	}

	@GetMapping("/devices/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<EntityModel<DeviceGetDetails>> getDeviceById(@PathVariable("id") long id) {
		if (deviceService.findById(id).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(EntityModel.of(deviceMapper.deviceToDeviceGetDetails(deviceService.findById(id).get()),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getDeviceById(id))
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllDevices())
						.withRel("all-devices"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(RepairController.class)
						.getAllRepairsByDeviceId(id))
						.withRel("all-repairs-by-device-id"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.updateDevice(id, deviceService.findById(id).get()))
						.withRel("update-device"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.deleteDevice(id))
						.withRel("delete-device"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(RepairController.class)
						.deleteAllRepairsOfDevices(id))
						.withRel("delete-all-repairs-of-device")));
	}

	@GetMapping("/types/{typeId}/devices")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<CollectionModel<DeviceGet>> getAllDevicesByTypeId(@PathVariable(value = "typeId") Long typeId) {
		if (deviceService.findByTypeId(typeId).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(CollectionModel.of(deviceService.findByTypeId(typeId)
				.stream()
				.map(deviceMapper::deviceToDeviceGet)
				.collect(Collectors.toList()),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllDevicesByTypeId(typeId))
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(TypeController.class)
						.getTypeById(typeId))
						.withRel("type-id"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.deleteAllDevicesOfType(typeId))
						.withRel("delete-devices-by-type-id")));
	}

	@PostMapping("/types/{typeId}/devices")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Device> createDeviceForType(@PathVariable(value = "typeId") Long typeId,
			@Valid @RequestBody DevicePost deviceRequest) {
		return new ResponseEntity<>(deviceService.createDeviceForType(typeId, deviceMapper.deviceInputToDevice(deviceRequest)), HttpStatus.CREATED);
	}

	@PutMapping("/devices/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Device> updateDevice(@PathVariable("id") long id, @Valid @RequestBody Device deviceRequest) {
		if (deviceService.findById(id).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(deviceService.updateDevice(id, deviceRequest), HttpStatus.OK);
	}

	@DeleteMapping("/devices")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteAllDevices() {
		deviceService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/devices/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteDevice(@PathVariable("id") long id) {
		deviceService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/types/{typeId}/devices")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Device>> deleteAllDevicesOfType(@PathVariable(value = "typeId") Long typeId) {
		deviceService.deleteByTypeId(typeId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
