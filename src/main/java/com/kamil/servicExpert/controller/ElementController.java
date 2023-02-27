package com.kamil.servicExpert.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.kamil.servicExpert.db.mapper.ElementMapper;
import com.kamil.servicExpert.db.model.Element;
import com.kamil.servicExpert.model.Element.ElementDtoGet;
import com.kamil.servicExpert.model.Element.ElementDtoGetDetails;
import com.kamil.servicExpert.model.Element.ElementDtoPost;
import com.kamil.servicExpert.service.ElementService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ElementController {

	private ElementService elementService;
	private ElementMapper elementMapper;

	@GetMapping("/elements")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<CollectionModel<ElementDtoGet>> getAllElements() {
		if (elementService.findAll().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(CollectionModel.of(elementService.findAll()
				.stream()
				.map(elementMapper::elementToElementGet)
				.collect(Collectors.toList()),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllElements())
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.deleteAllElements())
						.withRel("delete-elements")));
	}

	@GetMapping("/elements/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<EntityModel<ElementDtoGetDetails>> getElementById(@PathVariable("id") long id) {
		if (elementService.findById(id).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(EntityModel.of(elementMapper.elementToElementGetDetails(elementService.findById(id).get()),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getElementById(id))
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllElements())
						.withRel("all-elements"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.updateElement(id, elementService.findById(id).get()))
						.withRel("update-element"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.deleteElement(id))
						.withRel("delete-element")));
	}

	@GetMapping("/types/{typeId}/elements")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<CollectionModel<ElementDtoGet>> getAllElementsByTypeId(
			@PathVariable(value = "typeId") Long typeId) {
		if (elementService.findByTypeId(typeId).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(CollectionModel.of(elementService.findByTypeId(typeId)
				.stream()
				.map(elementMapper::elementToElementGet)
				.collect(Collectors.toList()),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllElementsByTypeId(typeId))
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(TypeController.class)
						.getTypeById(typeId))
						.withRel("type-by-id"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.deleteAllElementsOfType(typeId))
						.withRel("delete-elements_by_type_id")));
	}

	@PostMapping("/types/{typeId}/elements")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Element> createElementForType(@PathVariable(value = "typeId") Long typeId,
			@Valid @RequestBody ElementDtoPost elementRequest) {
		return new ResponseEntity<>(elementService.createElementForType(typeId, elementMapper.elementInputToElement(elementRequest)), HttpStatus.CREATED);
	}

	@PutMapping("/elements/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Element> updateElement(@PathVariable("id") long id,
			@Valid @RequestBody Element elementRequest) {
		if (elementService.findById(id).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(elementService.updateElement(id, elementRequest), HttpStatus.OK);
	}

	@DeleteMapping("/elements")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteAllElements() {
		elementService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/elements/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteElement(@PathVariable("id") long id) {
		elementService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/types/{typeId}/elements")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Element>> deleteAllElementsOfType(@PathVariable(value = "typeId") Long typeId) {
		elementService.deleteByTypeId(typeId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
