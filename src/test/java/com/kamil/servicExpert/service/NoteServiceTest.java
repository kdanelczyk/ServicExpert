package com.kamil.servicExpert.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.kamil.servicExpert.model.Note.NoteDtoGet;
import com.kamil.servicExpert.model.Note.NoteDtoPost;

@WebMvcTest(NoteService.class)
@ExtendWith(MockitoExtension.class)
class NoteServiceTest {
	
	@MockBean
	private NoteService noteService;
	
	@Test
	void testExistsByIdFalse() {
		// Given
		Long id = 1L;
		// When
		when(noteService.existsById(id)).thenReturn(false);
		// Then
        assertEquals(noteService.existsById(id), false);
        verify(noteService).existsById(id);
        
	}

	@Test
	void testExistsByIdTrue() {
		// Given
		Long id = 1L;
		// When
		when(noteService.existsById(id)).thenReturn(true);
		// Then
        assertEquals(noteService.existsById(id), true);
        verify(noteService).existsById(id);
	}
	
	@Test
	void testFindById() {
		// Given
		Long id = 1L;
		NoteDtoGet note = NoteDtoGet
				.builder()
				.description("description of note")
				.build();
		// When
		when(noteService.findById(id)).thenReturn(Optional.of(note));
		// Then
        assertEquals(noteService.findById(id), Optional.of(note));
        assertEquals(noteService.findById(id).get().getDescription(), "description of note");
        verify(noteService, times(2)).findById(id);
	}
	
	@Test
	void testFindByIdEmpty() {
		// Given
		Long id = 1L;
		// When
		when(noteService.findById(id)).thenReturn(Optional.empty());
		// Then
        assertEquals(noteService.findById(id), Optional.empty());
        verify(noteService).findById(id);
	}

	@Test
	void testFindByUserId() {
		// Given
		Long id = 1L;
		// When
		when(noteService.findByUserId(id)).thenReturn(List.of());
		// Then
        assertEquals(noteService.findByUserId(id).isEmpty(), true);
        verify(noteService).findByUserId(id);
	}

	@Test
	void testFindAllEmpty() {
		// Given
		List<NoteDtoGet> notes = new ArrayList<>();
		// When
		when(noteService.findAll()).thenReturn(notes);
		// Then
        assertEquals(true, noteService.findAll().isEmpty());
        verify(noteService).findAll();
	}
	
	@Test
	void testFindAllNotEmpty() {
		// Given
		List<NoteDtoGet> notes = List.of(NoteDtoGet
				.builder()
				.description("description of note")
				.build()
		);
		// When
		when(noteService.findAll()).thenReturn(notes);
		// Then
        assertEquals(false, noteService.findAll().isEmpty());
        assertEquals(1, noteService.findAll().size());
        verify(noteService, times(2)).findAll();
	}

	@Test
	void testSave() {
		// Given
		NoteDtoPost note = NoteDtoPost
				.builder()
				.description("description of note")
				.build();
		NoteDtoGet noteGet = NoteDtoGet
				.builder()
				.description("description of note")
				.build();
		// When
		when(noteService.save(note)).thenReturn(noteGet);
		// Then
        assertEquals(noteService.save(note), noteGet);
        verify(noteService).save(note);
	}

	@Test
	void testCreateNoteForUser() {
		// Given
		Long id = 1L;
		NoteDtoPost note = NoteDtoPost
				.builder()
				.description("description of note")
				.build();
		NoteDtoGet noteGet = NoteDtoGet
				.builder()
				.description("description of note")
				.build();
		// When
		when(noteService.createNoteForUser(id, note)).thenReturn(noteGet);
		// Then
        assertEquals(noteService.createNoteForUser(id, note), noteGet);
        verify(noteService).createNoteForUser(id, note);
	}

	@Test
	void testCreateNote() {
		// Given
		NoteDtoPost note = NoteDtoPost
				.builder()
				.description("description of note")
				.build();
		NoteDtoGet noteGet = NoteDtoGet
				.builder()
				.description("description of note")
				.build();
		// When
		when(noteService.createNote(note)).thenReturn(noteGet);
		// Then
        assertEquals(noteService.createNote(note), noteGet);
        verify(noteService).createNote(note);
	}

	@Test
	void testUpdateNote() {
		// Given
		Long id = 1L;
		NoteDtoPost note = NoteDtoPost
				.builder()
				.description("description of note")
				.build();
		NoteDtoGet updatedNote = NoteDtoGet
				.builder()
				.description("updated note")
				.build();
		// When
		when(noteService.updateNote(id, note)).thenReturn(updatedNote);
		// Then
        assertEquals(noteService.updateNote(id, note), updatedNote);
        verify(noteService).updateNote(id, note);
	}

	@Test
	void testElementChecker() {
		// Given
		// When
		// Then
		noteService.elementChecker();
		verify(noteService).elementChecker();
	}

	@Test
	void testDeleteById() {
		// Given
		// When
		// Then
		noteService.deleteById(1L);
		verify(noteService).deleteById(1L);
	}

	@Test
	void testDeleteAll() {
		// Given
		// When
		// Then
		noteService.deleteAll();
		verify(noteService).deleteAll();
	}
	
	@Test
	void testDeleteByUserId() {
		// Given
		// When
		// Then
		noteService.deleteByUserId(1L);
		verify(noteService).deleteByUserId(1L);
	}

}
