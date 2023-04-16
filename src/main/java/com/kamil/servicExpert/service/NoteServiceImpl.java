package com.kamil.servicExpert.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.mapper.NoteMapper;
import com.kamil.servicExpert.db.model.Note;
import com.kamil.servicExpert.exception.ResourceNotFoundException;
import com.kamil.servicExpert.model.Note.NoteDtoGet;
import com.kamil.servicExpert.model.Note.NoteDtoPost;
import com.kamil.servicExpert.repository.ElementRepository;
import com.kamil.servicExpert.repository.NoteRepository;
import com.kamil.servicExpert.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NoteServiceImpl implements NoteService{

	private NoteRepository noteRepository;
	private UserRepository userRepository;
	private ElementRepository elementRepository;
	private NoteMapper noteMapper;
	
	@Override
	public boolean existsById(Long id) {
		if (!noteRepository.existsById(id)) {
			throw new ResourceNotFoundException("Not found Note with id = " + id);
		}
		return noteRepository.existsById(id);
	}

	@Override
	public Optional<NoteDtoGet> findById(Long id) {
		return Optional.of(noteMapper.noteToNoteGet(noteRepository.findById(id).get()));
	}

	@Override
	public List<NoteDtoGet> findByUserId(long userId) {
		userRepository.existsById(userId);
		return noteRepository.findByUserId(userId)
				.stream()
				.map(noteMapper::noteToNoteGet)
				.collect(Collectors.toList());
	}

	@Override
	public List<NoteDtoGet> findAll() {
		return noteRepository.findAll()
				.stream()
				.map(noteMapper::noteToNoteGet)
				.collect(Collectors.toList());
	}
	
	@Override
	public NoteDtoGet save(NoteDtoPost noteDtoPost) {
		return noteMapper.noteToNoteGet(noteRepository.save(noteMapper.notePostToNote(noteDtoPost)));
	}

	@Override
	public NoteDtoGet createNoteForUser(Long userId, NoteDtoPost noteDtoPost) {
		return noteMapper.noteToNoteGet(noteRepository.save(Note
				.builder()
				.dateCreated(new Date())
				.description(noteDtoPost.getDescription())
				.user(userRepository.findById(userId).get())
				.build()));
	}

	@Override
	public NoteDtoGet createNote(NoteDtoPost noteDtoPost) {
		return noteMapper.noteToNoteGet(noteRepository.save(Note
				.builder()
				.dateCreated(new Date())
				.description(noteDtoPost.getDescription())
				.build()));
	}

	@Override
	public NoteDtoGet updateNote(Long id, NoteDtoPost noteDtoPost) {
		Note noteToUpdate = noteRepository.findById(id).get();
		noteToUpdate.setDescription(noteDtoPost.getDescription());
		noteRepository.save(noteToUpdate);
		return noteMapper.noteToNoteGet(noteToUpdate);
	}
	
	@Override
	public void elementChecker() {
		elementRepository.findAll().stream()
			.filter(element -> element.getCriticalQuantity() >= element.getQuantity())
			.forEach(element -> noteRepository.save(Note
					.builder()
					.dateCreated(new Date())
					.description("critical Quantity of " + element.getElementName() + " to " + element.getType().getTypeName())
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
		userRepository.existsById(userId);
		noteRepository.deleteByUserId(userId);
	}
	
}
