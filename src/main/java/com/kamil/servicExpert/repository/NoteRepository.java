package com.kamil.servicExpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamil.servicExpert.db.model.Note;

import jakarta.transaction.Transactional;

public interface NoteRepository extends JpaRepository<Note, Long> {

	List<Note> findByUserId(long userId);

	@Transactional
	void deleteByUserId(long userId);
	
}