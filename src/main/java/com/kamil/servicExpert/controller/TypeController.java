package com.kamil.servicExpert.controller;

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

import com.kamil.servicExpert.db.mapper.TypeMapper;
import com.kamil.servicExpert.db.model.Type;
import com.kamil.servicExpert.model.Type.TypeGet;
import com.kamil.servicExpert.model.Type.TypeGetDetails;
import com.kamil.servicExpert.model.Type.TypePost;
import com.kamil.servicExpert.service.TypeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TypeController {

	@Autowired
	private TypeService typeService;
	
    @Autowired
    private TypeMapper typeMapper;

	@GetMapping("/types")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<CollectionModel<TypeGet>> getAllTypes() {
		if (typeService.findAll().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(CollectionModel.of(typeService.findAll()
				.stream()
				.map(typeMapper::typeToTypeGet)
				.collect(Collectors.toList()),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllTypes())
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.deleteAllTypes())
						.withRel("delete-types")));
	}

	@GetMapping("/types/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<EntityModel<TypeGetDetails>> getTypeById(@PathVariable("id") long id) {
		if (typeService.findById(id).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(EntityModel.of(typeMapper.typesToTypeGetDetails(typeService.findById(id).get()),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getTypeById(id))
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllTypes())
						.withRel("all-types"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(DeviceController.class)
						.getAllDevicesByTypeId(id))
						.withRel("all-devices-of-types"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(ElementController.class)
						.getAllElementsByTypeId(id))
						.withRel("all-elements-of-types"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.updateType(id, typeService.findById(id).get()))
						.withRel("update-type"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.deleteType(id))
						.withRel("delete-type"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(DeviceController.class)
						.deleteAllDevicesOfType(id))
						.withRel("delete-all-devices-of-type"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(ElementController.class)
						.deleteAllElementsOfType(id))
						.withRel("delete-all-elements-of-type")));
	}

	@PostMapping("/types")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Type> createType(@RequestBody TypePost typeRequest) {
		return new ResponseEntity<>(typeService.createType(typeMapper.typeInputToType(typeRequest)), HttpStatus.CREATED);
	}

	@PutMapping("/types/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Type> updateType(@PathVariable("id") long id, @RequestBody Type type) {
		if (typeService.findById(id).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(typeService.updateType(id, type), HttpStatus.OK);
	}

	@DeleteMapping("/types/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteType(@PathVariable("id") long id) {
		typeService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/types")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteAllTypes() {
		typeService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
