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

import com.kamil.servicExpert.db.mapper.NoteMapper;
import com.kamil.servicExpert.db.model.Note;
import com.kamil.servicExpert.model.Note.NoteDtoGet;
import com.kamil.servicExpert.model.Note.NoteDtoPost;
import com.kamil.servicExpert.service.NoteService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class NoteController {
	

	@Autowired
	private NoteService noteService;
	
	@Autowired
	private NoteMapper noteMapper;
	
	@GetMapping("/notes")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<CollectionModel<NoteDtoGet>> getAllNotes() {
		if (noteService.findAll().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(CollectionModel.of(noteService.findAll()
				.stream()
				.map(noteMapper::noteToNoteGet)
				.collect(Collectors.toList()),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllNotes())
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.deleteAllNotes())
						.withRel("delete-notes")));
	}
	
	@GetMapping("/notes/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<EntityModel<NoteDtoGet>> getNoteById(@PathVariable("id") long id) {
		if (noteService.findById(id).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(EntityModel.of(noteMapper.noteToNoteGet(noteService.findById(id).get()),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getNoteById(id))
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllNotes())
						.withRel("all-notes"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.updateNote(id, noteService.findById(id).get()))
						.withRel("update-note"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.deleteNote(id))
						.withRel("delete-note")));
	}

	@GetMapping("/users/{userId}/notes")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<CollectionModel<NoteDtoGet>> getAllNotesByUserId(@PathVariable(value = "userId") Long userId) {
		if (noteService.findByUserId(userId).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(CollectionModel.of(noteService.findByUserId(userId)
				.stream()
				.map(noteMapper::noteToNoteGet)
				.collect(Collectors.toList()),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllNotesByUserId(userId))
						.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(UserController.class)
						.getUserById(userId))
						.withRel("user-by-id"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.deleteAllNotesByUserId(userId))
						.withRel("delete-devices-by-type-id")));
	}

	@PostMapping("/users/{userId}/notes")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Note> createNoteForUser(@PathVariable(value = "userId") Long userId,
			@Valid @RequestBody NoteDtoPost noteRequest) {
		return new ResponseEntity<>(noteService.createNoteForUser(userId, noteMapper.noteInputToNote(noteRequest)),HttpStatus.CREATED);
	}
	
	@PostMapping("/notes")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Note> createNote(@Valid @RequestBody NoteDtoPost noteRequest) {
		return new ResponseEntity<>(noteService.createNote(noteMapper.noteInputToNote(noteRequest)), HttpStatus.CREATED);
	}

	@PutMapping("/notes/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Note> updateNote(@PathVariable("id") long id, @Valid @RequestBody Note noteRequest) {
		if (noteService.findById(id).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(noteService.updateNote(id, noteRequest), HttpStatus.OK);
	}

	@DeleteMapping("/notes/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteNote(@PathVariable("id") long id) {
		noteService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/users/{userId}/notes")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Note>> deleteAllNotesByUserId(@PathVariable(value = "userId") Long userId) {
		noteService.deleteByUserId(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/notes")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteAllNotes() {
		noteService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
