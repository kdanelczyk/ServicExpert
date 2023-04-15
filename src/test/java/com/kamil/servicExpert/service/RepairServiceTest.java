package com.kamil.servicExpert.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.kamil.servicExpert.db.model.Repair;

@WebMvcTest(RepairService.class)
@ExtendWith(MockitoExtension.class)
class RepairServiceTest {
	
	@MockBean
	private RepairService repairService;
	
	@Test
	void testExistsByIdFalse() {
		// Given
		Long id = 1L;
		// When
		when(repairService.existsById(id)).thenReturn(false);
		// Then
        assertEquals(repairService.existsById(id), false);
        verify(repairService).existsById(id);
        
	}

	@Test
	void testExistsByIdTrue() {
		// Given
		Long id = 1L;
		// When
		when(repairService.existsById(id)).thenReturn(true);
		// Then
        assertEquals(repairService.existsById(id), true);
        verify(repairService).existsById(id);
	}
	
	@Test
	void testFindById() {
		// Given
		Long id = 1L;
		Repair repair = Repair
				.builder()
				.note("note of repair")
				.cost(BigDecimal.valueOf(20))
				.build();
		// When
		when(repairService.findById(id)).thenReturn(Optional.of(repair));
		// Then
        assertEquals(repairService.findById(id), Optional.of(repair));
        assertEquals(repairService.findById(id).get().getNote(), "note of repair");
        verify(repairService, times(2)).findById(id);
	}
	
	@Test
	void testFindByIdEmpty() {
		// Given
		Long id = 1L;
		// When
		when(repairService.findById(id)).thenReturn(Optional.empty());
		// Then
        assertEquals(repairService.findById(id), Optional.empty());
        verify(repairService).findById(id);
	}

	@Test
	void testFindByDeviceId() {
		// Given
		Long id = 1L;
		Repair repair = Repair
				.builder()
				.note("note of repair")
				.cost(BigDecimal.valueOf(20))
				.build();
		List<Repair> repairs = List.of(repair);
		// When
		when(repairService.findByDeviceId(id)).thenReturn(repairs);
		// Then
        assertEquals(repairService.findByDeviceId(id), repairs);
        assertEquals(repairService.findByDeviceId(id).isEmpty(), false);
        verify(repairService, times(2)).findByDeviceId(id);
	}

	@Test
	void testFindByUserId() {
		// Given
		Long id = 1L;
		Repair repair = Repair
				.builder()
				.note("note of repair")
				.cost(BigDecimal.valueOf(20))
				.build();
		List<Repair> repairs = List.of(repair);
		// When
		when(repairService.findByUserId(id)).thenReturn(repairs);
		// Then
        assertEquals(repairService.findByUserId(id), repairs);
        assertEquals(repairService.findByUserId(id).isEmpty(), false);
        verify(repairService, times(2)).findByUserId(id);
	}


	@Test
	void testFindAllEmpty() {
		// Given
		List<Repair> repairs = new ArrayList<>();
		// When
		when(repairService.findAll()).thenReturn(repairs);
		// Then
        assertEquals(true, repairService.findAll().isEmpty());
        verify(repairService).findAll();
	}
	
	@Test
	void testFindAllNotEmpty() {
		// Given
		Repair repair = Repair
				.builder()
				.note("note of repair")
				.cost(BigDecimal.valueOf(20))
				.build();
		List<Repair> repairs = List.of(repair);
		// When
		when(repairService.findAll()).thenReturn(repairs);
		// Then
        assertEquals(false, repairService.findAll().isEmpty());
        assertEquals(1, repairService.findAll().size());
        verify(repairService, times(2)).findAll();
	}

	@Test
	void testSave() {
		// Given
		Repair repair = Repair
				.builder()
				.note("note of repair")
				.cost(BigDecimal.valueOf(20))
				.build();
		// When
		when(repairService.save(repair)).thenReturn(repair);
		// Then
        assertEquals(repairService.save(repair), repair);
        verify(repairService).save(repair);
	}

	@Test
	void testCreateRepair() {
		// Given
		Long id = 1L;
		Repair repair = Repair
				.builder()
				.note("note of repair")
				.cost(BigDecimal.valueOf(20))
				.build();
		// When
		when(repairService.createRepair(id, id, repair)).thenReturn(repair);
		// Then
        assertEquals(repairService.createRepair(id, id, repair), repair);
        verify(repairService).createRepair(id, id, repair);
	}

	@Test
	void testUpdateRepair() {
		// Given
		Long id = 1L;
		Repair repair = Repair
				.builder()
				.note("note of repair")
				.cost(BigDecimal.valueOf(20))
				.build();
		Repair updatedRepair = Repair
				.builder()
				.note("note of repairUpdated")
				.cost(BigDecimal.valueOf(30))
				.build();
		// When
		when(repairService.updateRepair(id, repair)).thenReturn(updatedRepair);
		// Then
        assertEquals(repairService.updateRepair(id, repair), updatedRepair);
        verify(repairService).updateRepair(id, repair);
	}

	@Test
	void testDeleteById() {
		repairService.deleteById(1L);
		verify(repairService).deleteById(1L);
	}

	@Test
	void testDeleteAll() {
		repairService.deleteAll();
		verify(repairService).deleteAll();
	}

	@Test
	void testDeleteByDeviceId() {
		repairService.deleteByDeviceId(1L);
		verify(repairService).deleteByDeviceId(1L);
	}

}
