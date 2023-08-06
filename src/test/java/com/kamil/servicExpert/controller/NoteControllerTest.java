package com.kamil.servicExpert.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kamil.servicExpert.db.model.Note;
import com.kamil.servicExpert.model.Note.NoteDtoGet;
import com.kamil.servicExpert.model.Note.NoteDtoPost;
import com.kamil.servicExpert.service.NoteService;

public class NoteControllerTest {

    private NoteService noteService;
    private NoteController noteController;

    @BeforeEach
    public void setUp() {
        noteService = mock(NoteService.class);
        noteController = new NoteController(noteService);
    }

    @Test
    public void testGetAllNotes_WithNotes_ReturnsListOfNotes() {
        // given
        List<NoteDtoGet> notes = List.of(
            new NoteDtoGet("Note 1", new Date()),
            new NoteDtoGet("Note 2", new Date())
        );
        // when
        when(noteService.findAll()).thenReturn(notes);
        // then
        ResponseEntity<CollectionModel<NoteDtoGet>> response = noteController.getAllNotes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getContent().size());
        assertNotNull(response.getBody());
        assertEquals("Note 1", response.getBody().getContent().stream().toList().get(0).getDescription());
        assertEquals("Note 2", response.getBody().getContent().stream().toList().get(1).getDescription());
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("delete-notes"));
    }

    @Test
    public void testGetAllNotes_WithNoNotes_ReturnsNoContent() {
        // given
        // when
        when(noteService.findAll()).thenReturn(new ArrayList<>());
        // then
        ResponseEntity<CollectionModel<NoteDtoGet>> response = noteController.getAllNotes();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetNoteById_WithExistingNoteId_ReturnsNoteDetails() {
        // given
        long noteId = 1L;
        NoteDtoGet note = new NoteDtoGet(
			"Note 1",
			 new Date()
        );
        // when
        when(noteService.findById(noteId)).thenReturn(Optional.of(note));
        // then
        ResponseEntity<EntityModel<NoteDtoGet>> response = noteController.getNoteById(noteId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Note 1", response.getBody().getContent().getDescription());
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("all-notes"));
        assertNotNull(response.getBody().getLink("delete-note"));
    }

    @Test
    public void testGetNoteById_WithNonExistingNoteId_ReturnsNotFound() {
        // given
        long noteId = 999L;
        // when
        when(noteService.findById(noteId)).thenReturn(Optional.empty());
        // then
        ResponseEntity<EntityModel<NoteDtoGet>> response = noteController.getNoteById(noteId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetAllNotesByUserId_WithNotesForUser_ReturnsListOfNotes() {
        // given
        long userId = 1L;
        List<NoteDtoGet> notesForUser = List.of(
            new NoteDtoGet("Note 1", new Date()),
            new NoteDtoGet("Note 2", new Date())
        );
        // when
        when(noteService.findByUserId(userId)).thenReturn(notesForUser);
        // then
        ResponseEntity<CollectionModel<NoteDtoGet>> response = noteController.getAllNotesByUserId(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getContent().size());
        assertNotNull(response.getBody());
        assertEquals("Note 1", response.getBody().getContent().stream().toList().get(0).getDescription());
        assertEquals("Note 2", response.getBody().getContent().stream().toList().get(1).getDescription());
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("user-by-id"));
        assertNotNull(response.getBody().getLink("delete-devices-by-type-id"));
    }

    @Test
    public void testGetAllNotesByUserId_WithNoNotesForUser_ReturnsNoContent() {
        // given
        long userId = 1L;
        // when
        when(noteService.findByUserId(userId)).thenReturn(new ArrayList<>());
        // then
        ResponseEntity<CollectionModel<NoteDtoGet>> response = noteController.getAllNotesByUserId(userId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateNoteForUser_ValidInput_ReturnsCreatedNoteDetails() {
        // given
        long userId = 1L;
        NoteDtoPost noteDtoPost = new NoteDtoPost("New Note");
        NoteDtoGet createdNote = new NoteDtoGet("New Note", new Date());
        // when
        when(noteService.createNoteForUser(userId, noteDtoPost)).thenReturn(createdNote);
        // then
        ResponseEntity<NoteDtoGet> response = noteController.createNoteForUser(userId, noteDtoPost);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(createdNote, response.getBody());
    }

    @Test
    public void testCreateNote_ValidInput_ReturnsCreatedNoteDetails() {
        // given
        NoteDtoPost noteDtoPost = new NoteDtoPost("New Note");
        NoteDtoGet createdNote = new NoteDtoGet("New Note", new Date());
        // when
        when(noteService.createNote(noteDtoPost)).thenReturn(createdNote);
        // then
        ResponseEntity<NoteDtoGet> response = noteController.createNote(noteDtoPost);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(createdNote, response.getBody());
    }

    @Test
    public void testUpdateNote_WithExistingNoteId_ReturnsUpdatedNoteDetails() {
        // given
        long noteId = 1L;
        NoteDtoPost noteDtoPost = new NoteDtoPost("Updated Note");
        NoteDtoGet updatedNote = new NoteDtoGet(
            "Updated Note",
            new Date()
        );
        // when
        when(noteService.findById(noteId)).thenReturn(Optional.of(updatedNote));
        when(noteService.updateNote(noteId, noteDtoPost)).thenReturn(updatedNote);
        // then
        ResponseEntity<NoteDtoGet> response = noteController.updateNote(noteId, noteDtoPost);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Note", response.getBody().getDescription());
        assertNotNull(response.getBody());
        assertEquals(updatedNote, response.getBody());
    }

    @Test
    public void testUpdateNote_WithNonExistingNoteId_ReturnsNotFound() {
        // given
        long noteId = 999L;
        NoteDtoPost noteDtoPost = new NoteDtoPost("Updated Note");
        // when
        when(noteService.findById(noteId)).thenReturn(Optional.empty());
        // then
        ResponseEntity<NoteDtoGet> response = noteController.updateNote(noteId, noteDtoPost);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteNote_WithExistingNoteId_ReturnsNoContent() {
        // given
        long noteId = 1L;
        // when
        ResponseEntity<HttpStatus> response = noteController.deleteNote(noteId);
        // then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(noteService, times(1)).deleteById(noteId);
    }

    @Test
    public void testDeleteAllNotesByUserId_WithExistingUserId_ReturnsNoContent() {
        // given
        long userId = 1L;
        // when
		// then
        ResponseEntity<List<Note>> response = noteController.deleteAllNotesByUserId(userId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(noteService, times(1)).deleteByUserId(userId);
    }

    @Test
    public void testDeleteAllNotes_WithAllNotesDeleted_ReturnsNoContent() {
        // given
        // when
		// then
        ResponseEntity<HttpStatus> response = noteController.deleteAllNotes();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(noteService, times(1)).deleteAll();
    }

}
