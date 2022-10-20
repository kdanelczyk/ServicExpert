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
import com.kamil.servicExpert.db.mapper.ElementMapper;
import com.kamil.servicExpert.db.mapper.RepairMapper;
import com.kamil.servicExpert.db.model.Element;
import com.kamil.servicExpert.db.model.Repair;
import com.kamil.servicExpert.service.ElementService;

@WebMvcTest(ElementController.class)
@ExtendWith(MockitoExtension.class)
class ElementControllerTest {

	@MockBean
	private ElementService elementService;
	
	@MockBean
	private ElementMapper elementMapper;
	
	@MockBean
	private RepairMapper repairMapper;

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
	void testGetAllElements() throws Exception {
		// given
		Element element = Element
				.builder()
				.id(1L)
				.quantity(1)
				.criticalQuantity(2)
				.nameOfElement("nameOfElement")
				.priceOfElement(20)
				.build();
		List<Element> elements = List.of(element);
		// when
		when(elementService.findAll()).thenReturn(elements);
		// then
		mockMvc.perform(get("/api/elements"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(elements.size()))
				.andDo(print());
	}
	
	@Test
	void testFindAllElementsNotFound() throws Exception {
		// given
		List<Element> elements = List.of();
		// when
		when(elementService.findAll()).thenReturn(elements);
		// then
		mockMvc.perform(get("/api/elements"))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testGetElementById() {

	}

	@Test
	void testGetAllElementsByTypeId() throws Exception  {
		// given
		Long id = 1L;
		Element element = Element
				.builder()
				.id(1L)
				.quantity(1)
				.criticalQuantity(2)
				.nameOfElement("nameOfElement")
				.priceOfElement(20)
				.build();
		List<Element> elements = List.of(element);
		// when
		when(elementService.findByTypeId(id)).thenReturn(elements);
		// then
		mockMvc.perform(get("/api/types/{id}/elements", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(elements.size()))
				.andDo(print());
	}
	
	@Test
	void testGetAllElementsByTypeIdNoContent() throws Exception  {
		// given
		Long id = 1L;
		List<Element> elements = List.of();
		// when
		when(elementService.findByTypeId(id)).thenReturn(elements);
		// then
		mockMvc.perform(get("/api/types/{id}/elements", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testGetAllElementsByRepairId() throws Exception  {
		// given
		Long id = 1L;
		Element element = Element
				.builder()
				.id(1L)
				.quantity(1)
				.criticalQuantity(2)
				.nameOfElement("nameOfElement")
				.priceOfElement(20)
				.build();
		List<Element> elements = List.of(element);
		// when
		when(elementService.findElementsByRepairId(id)).thenReturn(elements);
		// then
		mockMvc.perform(get("/api/repairs/{id}/elements", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(elements.size()))
				.andDo(print());
	}
	
	@Test
	void testGetAllElementsByRepairIdNoContent() throws Exception  {
		// given
		Long id = 1L;
		List<Element> elements = List.of();
		// when
		when(elementService.findElementsByRepairId(id)).thenReturn(elements);
		// then
		mockMvc.perform(get("/api/repairs/{id}/elements", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testGetAllRepairsByElementId() throws Exception  {

	}
	
	@Test
	void testGetAllRepairsByElementIdNoContent() throws Exception  {
		// given
		Long id = 1L;
		List<Repair> repairs = List.of();
		// when
		when(elementService.findRepairsByElementsId(id)).thenReturn(repairs);
		// then
		mockMvc.perform(get("/api/repairs/{id}/elements", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testCreateElementForType() throws Exception  {
		// given
		Long id = 1L;
		Element element = Element
				.builder()
				.id(1L)
				.quantity(1)
				.criticalQuantity(2)
				.nameOfElement("nameOfElement")
				.priceOfElement(20)
				.build();
		// when
		when(elementService.save(element)).thenReturn(element);
		// then
		mockMvc.perform(post("/api/types/{id}/elements", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(element)))
				.andExpect(status().isCreated())
				.andDo(print());
	}

	@Test
	void testUpdateElement() {

	}

	@Test
	void testAddElement() throws Exception  {

	}

	@Test
	void testDeleteElement() throws Exception {
		// given
		long id = 1L;
		// when
		doNothing().when(elementService).deleteById(id);
		// then
		mockMvc.perform(delete("/api/elements/{id}", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testDeleteAllElements() throws Exception {
		// when
		doNothing().when(elementService).deleteAll();
		// then
		mockMvc.perform(delete("/api/elements"))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testDeleteAllElementsOfType() throws Exception {
		// given
		long id = 1L;
		// when
		doNothing().when(elementService).deleteByTypeId(id);
		// then
		mockMvc.perform(delete("/api/types/{id}/elements", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testDeleteElementFromRepair() throws Exception {
		// given
		long id = 1L;
		// when
		doNothing().when(elementService).deleteElementFromRepair(id, id);
		// then
		mockMvc.perform(delete("/api/repairs/{repairId}/elements/{elementId}", id, id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

}
