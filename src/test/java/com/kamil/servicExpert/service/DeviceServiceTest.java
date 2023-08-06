package com.kamil.servicExpert.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.kamil.servicExpert.model.Device.DeviceDtoGet;
import com.kamil.servicExpert.model.Device.DeviceDtoGetDetails;
import com.kamil.servicExpert.model.Device.DeviceDtoPost;

@WebMvcTest(DeviceService.class)
@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {
	
	@MockBean
	private DeviceService deviceService;

	@Test
	void testExistsByIdFalse() {
		// Given
		Long id = 1L;
		// When
		when(deviceService.existsById(id)).thenReturn(false);
		// Then
        assertEquals(deviceService.existsById(id), false);
        verify(deviceService).existsById(id);
        
	}

	@Test
	void testExistsByIdTrue() {
		// Given
		Long id = 1L;
		// When
		when(deviceService.existsById(id)).thenReturn(true);
		// Then
        assertEquals(deviceService.existsById(id), true);
        verify(deviceService).existsById(id);
	}
	
	@Test
	void testFindById() {
		// Given
		Long id = 1L;
		DeviceDtoGetDetails device = DeviceDtoGetDetails
				.builder()
				.customerPhoneNumber(404040404)
				.customerName("Frank")
				.dateOfReceipt(new Date())
				.repaired(false)
				.build();
		// When
		when(deviceService.findById(id)).thenReturn(Optional.of(device));
		// Then
        assertEquals(deviceService.findById(id), Optional.of(device));
        assertEquals(deviceService.findById(id).get().getCustomerPhoneNumber(), 404040404);
        assertEquals(deviceService.findById(id).get().getCustomerName(), "Frank");
        verify(deviceService, times(3)).findById(id);
	}
	
	@Test
	void testFindByIdEmpty() {
		// Given
		Long id = 1L;
		// When
		when(deviceService.findById(id)).thenReturn(Optional.empty());
		// Then
        assertEquals(deviceService.findById(id), Optional.empty());
        verify(deviceService).findById(id);
	}
	
	@Test
	void testFindByRepairedFalse() {
		// Given
		List<DeviceDtoGet> devices = List.of(
			DeviceDtoGet
				.builder()
				.customerName("Frank")
				.repaired(false)
				.build()
		);
		// When
		when(deviceService.findByRepaired(false)).thenReturn(devices);
		// Then
        assertEquals(1, deviceService.findByRepaired(false).size());
        assertEquals("Frank", deviceService.findByRepaired(false).get(0).getCustomerName());
        verify(deviceService, times(2)).findByRepaired(false);
	}

	@Test
	void testFindByRepairedTrue() {
		// Given
		List<DeviceDtoGet> devices = List.of(
			DeviceDtoGet
				.builder()
				.customerName("Frank")
				.repaired(false)
				.build()
		);
		// When
		when(deviceService.findByRepaired(true)).thenReturn(devices);
		// Then
        assertEquals(1, deviceService.findByRepaired(true).size());
        assertEquals("Frank", deviceService.findByRepaired(true).get(0).getCustomerName());
        verify(deviceService, times(2)).findByRepaired(true);
	}

	@Test
	void testFindByTypeId() {
		// Given
		Long id = 1L;
		List<DeviceDtoGet> devices = List.of(
			DeviceDtoGet
				.builder()
				.customerName("Frank")
				.repaired(false)
				.build()
		);
		// When
		when(deviceService.findByTypeId(id)).thenReturn(devices);
		// Then
        assertEquals(1, deviceService.findByTypeId(id).size());
        assertEquals("Frank", deviceService.findByTypeId(id).get(0).getCustomerName());
	}

	@Test
	void testFindByTypeIdEmpty() {
		// Given
		Long id = 1L;
		List<DeviceDtoGet> devices = List.of();
		// When
		when(deviceService.findByTypeId(id)).thenReturn(devices);
		// Then
        assertEquals(0, deviceService.findByTypeId(id).size());
        assertEquals(true, deviceService.findByTypeId(id).isEmpty());
        verify(deviceService, times(2)).findByTypeId(id);
	}

	@Test
	void testFindAll() {
		// Given
		List<DeviceDtoGet> devices = List.of(
			DeviceDtoGet
				.builder()
				.customerName("Frank")
				.repaired(false)
				.build()
		);
		// When
		when(deviceService.findAll()).thenReturn(devices);
		// Then
        assertEquals(1, deviceService.findAll().size());
        assertEquals(false, deviceService.findAll().isEmpty());
        verify(deviceService, times(2)).findAll();
	}
	
	@Test
	void testFindAllEmpty() {
		// Given
		List<DeviceDtoGet> devices = List.of();
		// When
		when(deviceService.findAll()).thenReturn(devices);
		// Then
        assertEquals(0, deviceService.findAll().size());
        assertEquals(true, deviceService.findAll().isEmpty());
        verify(deviceService, times(2)).findAll();
	}

	@Test
	void testSave() {
		// Given
		DeviceDtoPost device = DeviceDtoPost
				.builder()
				.customerPhoneNumber(404040404)
				.customerName("Frank")
				.build();
		DeviceDtoGetDetails deviceDetails = DeviceDtoGetDetails
				.builder()
				.customerPhoneNumber(404040404)
				.customerName("Frank")
				.dateOfReceipt(new Date())
				.repaired(false)
				.build();
		// When
		when(deviceService.save(device)).thenReturn(deviceDetails);
		// Then
        assertEquals(deviceService.save(device), deviceDetails);
        verify(deviceService).save(device);
	}

	@Test
	void testCreateDeviceForType() {
		// Given
		Long id = 1L;
		DeviceDtoPost device = DeviceDtoPost
				.builder()
				.customerPhoneNumber(404040404)
				.customerName("Frank")
				.build();
		DeviceDtoGetDetails deviceDetails = DeviceDtoGetDetails
				.builder()
				.customerPhoneNumber(404040404)
				.customerName("Frank")
				.dateOfReceipt(new Date())
				.repaired(false)
				.build();
		// When
		when(deviceService.createDeviceForType(id, device)).thenReturn(deviceDetails);
		// Then
        assertEquals(deviceService.createDeviceForType(id, device), deviceDetails);
        verify(deviceService).createDeviceForType(id, device);
	}

	@Test
	void testUpdateDevice() {
		// Given
		Long id = 1L;
		DeviceDtoPost device = DeviceDtoPost
				.builder()
				.customerPhoneNumber(404040404)
				.customerName("Frank")
				.build();
		DeviceDtoGetDetails deviceDetails = DeviceDtoGetDetails
				.builder()
				.customerPhoneNumber(404040404)
				.customerName("Frank2")
				.dateOfReceipt(new Date())
				.repaired(false)
				.build();
		// When
		when(deviceService.updateDevice(id, device)).thenReturn(deviceDetails);
		// Then
        assertEquals(deviceService.updateDevice(id, device), deviceDetails);
        verify(deviceService).updateDevice(id, device);
	}

	@Test
	void testDeleteById() {
		// Given
		// When
		// Then
		deviceService.deleteById(1L);
		verify(deviceService).deleteById(1L);
	}

	@Test
	void testDeleteAll() {
		// Given
		// When
		// Then
		deviceService.deleteAll();
		verify(deviceService).deleteAll();
	}

	@Test
	void testDeleteByTypeId() {
		// Given
		Long id = 1L;
		// When
		// Then
		deviceService.deleteByTypeId(id);
		verify(deviceService).deleteByTypeId(id);
	}

}
