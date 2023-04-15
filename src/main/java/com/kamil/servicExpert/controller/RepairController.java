package com.kamil.servicExpert.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamil.servicExpert.db.model.Repair;
import com.kamil.servicExpert.model.Repair.RepairDtoGet;
import com.kamil.servicExpert.model.Repair.RepairDtoGetDetails;
import com.kamil.servicExpert.model.Repair.RepairDtoPost;
import com.kamil.servicExpert.service.RepairService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class RepairController {

	private RepairService repairService;
	
	@GetMapping("/repairs")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<CollectionModel<RepairDtoGet>> getAllRepairs() {
		if (repairService.findAll().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(CollectionModel.of(repairService.findAll(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllRepairs())
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.deleteAllRepairs())
						.withRel("delete-repairs")));
	}
	
	@GetMapping("/repairs/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<EntityModel<RepairDtoGetDetails>> getRepairById(@PathVariable("id") long id) {
		if (repairService.findById(id).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(EntityModel.of(repairService.findById(id).get(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getRepairById(id))
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllRepairs())
						.withRel("all-repairs"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.deleteRepair(id))
						.withRel("delete-repair")));
	}

	@GetMapping("/devices/{deviceId}/repairs")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<CollectionModel<RepairDtoGet>> getAllRepairsByDeviceId(
			@PathVariable(value = "deviceId") Long deviceId) {
		if (repairService.findByDeviceId(deviceId).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(CollectionModel.of(repairService.findByDeviceId(deviceId),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllRepairsByDeviceId(deviceId))
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(DeviceController.class)
						.getDeviceById(deviceId))
						.withRel("device-by-id"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.deleteAllRepairsOfDevices(deviceId))
						.withRel("delete-repairs_by_device_id")));
	}

	@GetMapping("/users/{userId}/repairs")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<CollectionModel<RepairDtoGet>> getAllRepairsByUserId(@PathVariable(value = "userId") Long userId) {
		if (repairService.findByUserId(userId).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(CollectionModel.of(repairService.findByUserId(userId),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllRepairsByUserId(userId))
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(UserController.class)
						.getUserById(userId))
						.withRel("user-by-id"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.deleteAllRepairsOfUsers(userId))
						.withRel("delete-repairs_by_user_id")));
	}

	@PostMapping("/devices/{deviceId}/users/{userId}/repairs")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<RepairDtoGetDetails> createRepair(@PathVariable(value = "deviceId") Long deviceId,
			@PathVariable(value = "userId") Long userId, @Valid @RequestBody RepairDtoPost repairRequest) {
		return new ResponseEntity<>(repairService.createRepair(deviceId, userId, repairRequest), HttpStatus.CREATED);
	}

	@PutMapping("/repairs/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<RepairDtoGetDetails> updateRepair(@PathVariable("id") long id, @Valid @RequestBody RepairDtoPost repairRequest) {
		if (repairService.findById(id).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(repairService.updateRepair(id, repairRequest), HttpStatus.OK);
	}

	@PutMapping("/repairs/{repairId}/elements/{elementId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<RepairDtoGetDetails> addElement(@PathVariable(value = "repairId") Long repairId,
			@PathVariable(value = "elementId") Long elementId) {
		return new ResponseEntity<>(repairService.addElementToRepair(repairId, elementId), HttpStatus.OK);
	}
	
	@DeleteMapping("/repairs/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteRepair(@PathVariable("id") long id) {
		repairService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/devices/{deviceId}/repairs")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Repair>> deleteAllRepairsOfDevices(@PathVariable(value = "deviceId") Long deviceId) {
		repairService.deleteByDeviceId(deviceId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/users/{userId}/repairs")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Repair>> deleteAllRepairsOfUsers(@PathVariable(value = "userId") Long userId) {
		repairService.deleteByUserId(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/repairs")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteAllRepairs() {
		repairService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/repairs/{repairId}/elements/{elementId}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteElementFromRepair(@PathVariable(value = "repairId") Long repairId,
			@PathVariable(value = "elementId") Long elementId) {
		repairService.deleteElementFromRepair(repairId, elementId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
