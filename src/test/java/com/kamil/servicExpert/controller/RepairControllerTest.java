package com.kamil.servicExpert.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.kamil.servicExpert.db.mapper.RepairMapper;
import com.kamil.servicExpert.db.model.Repair;
import com.kamil.servicExpert.service.RepairService;

@WebMvcTest(RepairController.class)
@ExtendWith(MockitoExtension.class)
class RepairControllerTest {

	@MockBean
	private RepairService repairService;
	
	@MockBean
	private RepairMapper repairMapper;

	@Autowired
	private MockMvc mockMvc;
	
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    
	@Test
	void testGetAllRepairs() throws Exception {
		// given
		Repair repair = Repair
				.builder()
				.note("note of repair")
				.cost(20)
				.build();
		List<Repair> repairs = List.of(repair);
		// when
		when(repairService.findAll()).thenReturn(repairs);
		// then
		mockMvc.perform(get("/api/repairs"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(repairs.size()))
				.andDo(print());
	}
	
	@Test
	void testFindAllRepairsNotFound() throws Exception {
		// given
		List<Repair> repairs = List.of();
		// when
		when(repairService.findAll()).thenReturn(repairs);
		// then
		mockMvc.perform(get("/api/repairs"))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testGetRepairById() {

	}

	@Test
	void testGetAllRepairsByDeviceId() throws Exception {
		// given
		Long id = 1L;
		Repair repair = Repair
				.builder()
				.note("note of repair")
				.cost(20)
				.build();
		List<Repair> repairs = List.of(repair);
		// when
		when(repairService.findByDeviceId(id)).thenReturn(repairs);
		// then
		mockMvc.perform(get("/api/devices/{id}/repairs", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(repairs.size()))
				.andDo(print());
	}

	@Test
	void testGetAllRepairsByDeviceIdNoContent() throws Exception {
		// given
		Long id = 1L;
		List<Repair> repairs = List.of();
		// when
		when(repairService.findByDeviceId(id)).thenReturn(repairs);
		// then
		mockMvc.perform(get("/api/devices/{id}/repairs", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}
	
	@Test
	void testGetAllRepairsByUserId() throws Exception {
		// given
		Long id = 1L;
		Repair repair = Repair
				.builder()
				.note("note of repair")
				.cost(20)
				.build();
		List<Repair> repairs = List.of(repair);
		// when
		when(repairService.findByUserId(id)).thenReturn(repairs);
		// then
		mockMvc.perform(get("/api/users/{id}/repairs", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(repairs.size()))
				.andDo(print());
	}
	
	@Test
	void testGetAllRepairsByUserIdNoContent() throws Exception {
		// given
		Long id = 1L;

		List<Repair> repairs = List.of();
		// when
		when(repairService.findByUserId(id)).thenReturn(repairs);
		// then
		mockMvc.perform(get("/api/users/{id}/repairs", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testCreateRepair() throws Exception {

	}

	@Test
	void testUpdateRepair() {

	}

	@Test
	void testDeleteRepair() throws Exception {
		// given
		long id = 1L;
		// when
		doNothing().when(repairService).deleteById(id);
		// then
		mockMvc.perform(delete("/api/repairs/{id}", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testDeleteAllRepairs() throws Exception {
		// when
		doNothing().when(repairService).deleteAll();
		// then
		mockMvc.perform(delete("/api/repairs"))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testDeleteAllRepairsOfDevices() throws Exception {
		// given
		long id = 1L;
		// when
		doNothing().when(repairService).deleteByDeviceId(id);
		// then
		mockMvc.perform(delete("/api/devices/{id}/repairs", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testDeleteAllRepairsOfUsers() throws Exception {
		// given
		long id = 1L;
		// when
		doNothing().when(repairService).deleteByUserId(id);
		// then
		mockMvc.perform(delete("/api/users/{id}/repairs", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

}
