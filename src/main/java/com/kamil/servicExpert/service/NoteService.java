package com.kamil.servicExpert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kamil.servicExpert.model.Note.NoteDtoGet;
import com.kamil.servicExpert.model.Note.NoteDtoPost;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface NoteService {
	
	public boolean existsById(Long id);
	
	public Optional<NoteDtoGet> findById(Long id);

	public List<NoteDtoGet> findByUserId(long userId);

	public List<NoteDtoGet> findAll();
	
	public NoteDtoGet save(NoteDtoPost noteDtoPost);
	
	public NoteDtoGet createNoteForUser(Long userId, NoteDtoPost noteDtoPost);
	
	public NoteDtoGet createNote(NoteDtoPost noteDtoPost);

	public NoteDtoGet updateNote(Long id, NoteDtoPost noteDtoPost);
	
	public void elementChecker();
	
	public void deleteById(Long id);

	public void deleteAll();
	
	public void deleteByUserId(long userId);
	
}
