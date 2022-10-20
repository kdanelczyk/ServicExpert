package com.kamil.servicExpert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.db.model.Note;
import jakarta.transaction.Transactional;

@Service
@Transactional
public interface NoteService {
	
	public boolean existsById(Long id);
	
	public Optional<Note> findById(Long id);

	public List<Note> findByUserId(long userId);

	public List<Note> findAll();
	
	public Note save(Note note);
	
	public Note createNoteForUser(Long userId, Note note);
	
	public Note createNote(Note note);

	public Note updateNote(Long id, Note note);
	
	public void elementChecker();
	
	public void deleteById(Long id);

	public void deleteAll();
	
	public void deleteByUserId(long userId);
}
