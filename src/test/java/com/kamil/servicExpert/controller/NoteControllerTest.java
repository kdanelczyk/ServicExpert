package com.kamil.servicExpert.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamil.servicExpert.db.mapper.NoteMapper;
import com.kamil.servicExpert.db.model.Note;
import com.kamil.servicExpert.service.NoteService;

@WebMvcTest(NoteController.class)
@ExtendWith(MockitoExtension.class)
class NoteControllerTest {
	
	@MockBean
	private NoteService noteService;
	
	@MockBean
	private NoteMapper noteMapper;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;
	
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    
	@Test
	void testGetAllNotes() throws Exception {
		// given
		Note note = Note
				.builder()
				.description("description of note")
				.build();
		List<Note> notes = List.of(note);
		// when
		when(noteService.findAll()).thenReturn(notes);
		// then
		mockMvc.perform(get("/api/notes"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(notes.size()))
				.andDo(print());
	}
	
	@Test
	void testGetAllNotesNoContent() throws Exception {
		// given
		List<Note> notes = List.of();
		// when
		when(noteService.findAll()).thenReturn(notes);
		// then
		mockMvc.perform(get("/api/notes"))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testGetNoteById() {

	}

	@Test
	void testGetAllNotesByUserId() throws Exception {
		// given
		Long id = 1L;
		Note note = Note
				.builder()
				.description("description of note")
				.build();
		List<Note> notes = List.of(note);
		// when
		when(noteService.findByUserId(id)).thenReturn(notes);
		// then
		mockMvc.perform(get("/api/users/{id}/notes", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(notes.size()))
				.andDo(print());
	}
	
	@Test
	void testGetAllNotesByUserIdNoContent() throws Exception {
		// given
		Long id = 1L;
		List<Note> notes = List.of();
		// when
		when(noteService.findByUserId(id)).thenReturn(notes);
		// then
		mockMvc.perform(get("/api/users/{id}/notes", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testCreateNoteForUser() throws Exception {
		// given
		Long id = 1L;
		Note note = Note
				.builder()
				.description("description of note")
				.build();
		// when
		when(noteService.createNoteForUser(id, note)).thenReturn(note);
		// then
		mockMvc.perform(post("/api/users/{id}/notes", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(note)))
				.andExpect(status().isCreated())
				.andDo(print());
	}

	@Test
	void testCreateNote() throws Exception {
		// given
		Long id = 1L;
		Note note = Note
				.builder()
				.description("description of note")
				.build();
		// when
		when(noteService.createNote(note)).thenReturn(note);
		// then
		mockMvc.perform(post("/api/notes", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(note)))
				.andExpect(status().isCreated())
				.andDo(print());
	}

	@Test
	void testUpdateNote() {

	}

	@Test
	void testDeleteNote() throws Exception {
		// given
		long id = 1L;
		// when
		doNothing().when(noteService).deleteById(id);
		// then
		mockMvc.perform(delete("/api/notes/{id}", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testDeleteAllNotes() throws Exception {
		// when
		doNothing().when(noteService).deleteAll();
		// then
		mockMvc.perform(delete("/api/notes"))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testDeleteAllNotesByUserId() throws Exception {
		// given
		long id = 1L;
		// when
		doNothing().when(noteService).deleteByUserId(id);
		// then
		mockMvc.perform(delete("/api/users/{id}/notes", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

}
