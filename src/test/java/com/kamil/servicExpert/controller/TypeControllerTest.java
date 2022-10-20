package com.kamil.servicExpert.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamil.servicExpert.db.mapper.TypeMapper;
import com.kamil.servicExpert.db.model.Type;
import com.kamil.servicExpert.service.TypeService;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(TypeController.class)
@ExtendWith(MockitoExtension.class)
public class TypeControllerTest {

	@MockBean
	private TypeService typeService;
	
	@MockBean
	private TypeMapper typeMapper;

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
	void testCreateType() throws Exception {
		// given
		Type type = Type.builder().nameOfType("type device name").build();
		// when
		when(typeService.save(type)).thenReturn(type);
		// then
		mockMvc.perform(post("/api/types")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(type)))
				.andExpect(status().isCreated())
				.andDo(print());
	}

	@Test
	void testFindTypeById() throws Exception {

	}
	
    @Test
    	void testFindTypeByIdNotFound() throws Exception {
    	long id = 1L;

    	when(typeService.findById(id)).thenReturn(Optional.empty());
    	mockMvc.perform(MockMvcRequestBuilders.get("/api/types/{id}", id))
         		.andExpect(status().isNotFound())
         		.andDo(print());
  }

	@Test
	void testFindAllTypes() throws Exception {
		// given
		Type type = Type.builder().nameOfType("type device name").build();
		List<Type> types = List.of(type);
		// when
		when(typeService.findAll()).thenReturn(types);
		// then
		mockMvc.perform(get("/api/types"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(types.size()))
				.andDo(print());
	}
	
	@Test
	void testFindAllTypesNotFound() throws Exception {
		// given
		List<Type> types = List.of();
		// when
		when(typeService.findAll()).thenReturn(types);
		// then
		mockMvc.perform(get("/api/types"))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testUpdateType() throws Exception {

	}
	
	@Test
	void testUpdateTypeNotFound() throws Exception {

	}

	@Test
	void testDeleteType() throws Exception {
		// given
		long id = 1L;
		// when
		doNothing().when(typeService).deleteById(id);
		// then
		mockMvc.perform(delete("/api/types/{id}", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testDeleteAllTypes() throws Exception {
		// when
		doNothing().when(typeService).deleteAll();
		// then
		mockMvc.perform(delete("/api/types"))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

}
