package com.kamil.servicExpert.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.model.Note;
import com.kamil.servicExpert.exception.ResourceNotFoundException;
import com.kamil.servicExpert.repository.NoteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NoteServiceImpl implements NoteService{

	private NoteRepository noteRepository;
	private UserService userService;
	private ElementService elementService;
	
	@Override
	public boolean existsById(Long id) {
		if (!noteRepository.existsById(id)) {
			throw new ResourceNotFoundException("Not found Note with id = " + id);
		}
		return noteRepository.existsById(id);
	}

	@Override
	public Optional<Note> findById(Long id) {
		return noteRepository.findById(id);
	}

	@Override
	public List<Note> findByUserId(long userId) {
		userService.existsById(userId);
		return noteRepository.findByUserId(userId);
	}

	@Override
	public List<Note> findAll() {
		return noteRepository.findAll();
	}
	
	@Override
	public Note save(Note note) {
		return noteRepository.save(note);
	}

	@Override
	public Note createNoteForUser(Long userId, Note note) {
		return save(Note.builder()
				.dateCreated(new Date())
				.description(note.getDescription())
				.user(userService.findById(userId).get())
				.build());
	}

	@Override
	public Note createNote(Note note) {
		return save(Note.builder()
				.dateCreated(new Date())
				.description(note.getDescription())
				.build());
	}

	@Override
	public Note updateNote(Long id, Note note) {
		Note noteToUpdate = findById(id).get();
		noteToUpdate.setDescription(note.getDescription());
		save(noteToUpdate);
		return noteToUpdate;
	}
	
	@Override
	public void elementChecker() {
		elementService.findAll().stream()
			.filter(element -> element.getCriticalQuantity() >= element.getQuantity())
			.forEach(element -> save(Note.builder()
					.dateCreated(new Date())
					.description("critical Quantity of " + element.getNameOfElement() + " to " + element.getType().getNameOfType())
					.build()));
	}

	@Override
	public void deleteById(Long id) {
		existsById(id);
		noteRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		noteRepository.deleteAll();
	}
	
	@Override
	public void deleteByUserId(long userId) {
		userService.existsById(userId);
		noteRepository.deleteByUserId(userId);
	}
}
