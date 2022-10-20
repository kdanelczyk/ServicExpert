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
import com.kamil.servicExpert.db.mapper.DeviceMapper;
import com.kamil.servicExpert.db.model.Device;
import com.kamil.servicExpert.service.DeviceService;

@WebMvcTest(DeviceController.class)
@ExtendWith(MockitoExtension.class)
class DeviceControllerTest {

	@MockBean
	private DeviceService deviceService;
	
	@MockBean
	private DeviceMapper deviceMapper;
	
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
	void testFindByNotRepaired() throws Exception {
		// given
		Device device = Device
				.builder()
				.customerPhoneNumber(404040404)
				.nameOfCustomer("Frank")
				.repaired(false)
				.build();
		List<Device> devices = List.of(device);
		// when
		when(deviceService.findByRepaired(false)).thenReturn(devices);
		// then
		mockMvc.perform(get("/api/devices/not-repaired"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(devices.size()))
				.andDo(print());
	}
	
	@Test
	void testFindByNotRepairedNoContent() throws Exception {
		// given
		List<Device> devices = List.of();
		// when
		when(deviceService.findByRepaired(false)).thenReturn(devices);
		// then
		mockMvc.perform(get("/api/devices/not-repaired"))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testFindByRepaired() throws Exception {
		// given
		Device device = Device
				.builder()
				.customerPhoneNumber(404040404)
				.nameOfCustomer("Frank")
				.repaired(true)
				.build();
		List<Device> devices = List.of(device);
		// when
		when(deviceService.findByRepaired(true)).thenReturn(devices);
		// then
		mockMvc.perform(get("/api/devices/repaired"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(devices.size()))
				.andDo(print());
	}
	
	@Test
	void testFindByRepairedNoContent() throws Exception {
		// given
		List<Device> devices = List.of();
		// when
		when(deviceService.findByRepaired(true)).thenReturn(devices);
		// then
		mockMvc.perform(get("/api/devices/repaired"))
		.andExpect(status().isNoContent())
		.andDo(print());;
	}

	@Test
	void testGetAllDevices() throws Exception {
		// given
		Device device = Device
				.builder()
				.customerPhoneNumber(404040404)
				.nameOfCustomer("Frank")
				.repaired(false)
				.build();
		List<Device> devices = List.of(device);
		// when
		when(deviceService.findAll()).thenReturn(devices);
		// then
		mockMvc.perform(get("/api/devices"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(devices.size()))
				.andDo(print());
	}
	
	@Test
	void testFindAllDevicesNotFound() throws Exception {
		// given
		List<Device> devices = List.of();
		// when
		when(deviceService.findAll()).thenReturn(devices);
		// then
		mockMvc.perform(get("/api/devices"))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testGetDeviceById() {
	}

	@Test
	void testGetAllDevicesByTypeId() throws Exception {
		// given
		long id = 1L;
		Device device = Device
				.builder()
				.customerPhoneNumber(404040404)
				.nameOfCustomer("Frank")
				.repaired(false)
				.build();
		List<Device> devices = List.of(device);
		// when
		when(deviceService.findByTypeId(id)).thenReturn(devices);
		// then
		mockMvc.perform(get("/api/types/{id}/devices", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(devices.size()))
				.andDo(print());
	}
	
	@Test
	void testGetAllDevicesByTypeIdNoContent() throws Exception {
		// given
		long id = 1L;
		List<Device> devices = List.of();
		// when
		when(deviceService.findByTypeId(id)).thenReturn(devices);
		// then
		mockMvc.perform(get("/api/devices"))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testCreateDeviceForType() throws Exception {
		// given
		long id = 1L;
		Device device = Device
				.builder()
				.customerPhoneNumber(404040404)
				.nameOfCustomer("Frank")
				.build();
		// when
		when(deviceService.save(device)).thenReturn(device);
		// then
		mockMvc.perform(post("/api/types/{id}/devices", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(device)))
				.andExpect(status().isCreated())
				.andDo(print());
	}

	@Test
	void testUpdateDevice() {

	}

	@Test
	void testDeleteDevice() throws Exception {
		// given
		long id = 1L;
		// when
		doNothing().when(deviceService).deleteById(id);
		// then
		mockMvc.perform(delete("/api/devices/{id}", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testDeleteAllDevices() throws Exception {
		// when
		doNothing().when(deviceService).deleteAll();
		// then
		mockMvc.perform(delete("/api/devices"))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testDeleteAllDevicesOfType() throws Exception {
		// given
		long id = 1L;
		// when
		doNothing().when(deviceService).deleteByTypeId(id);
		// then
		mockMvc.perform(delete("/api/types/{id}/devices", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

}
