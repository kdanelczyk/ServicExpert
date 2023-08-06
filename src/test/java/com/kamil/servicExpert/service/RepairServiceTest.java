package com.kamil.servicExpert.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.kamil.servicExpert.model.Repair.RepairDtoGet;
import com.kamil.servicExpert.model.Repair.RepairDtoGetDetails;
import com.kamil.servicExpert.model.Repair.RepairDtoPost;

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
		RepairDtoGetDetails repair = RepairDtoGetDetails
				.builder()
				.dateCreated(new Date())
				.cost(BigDecimal.valueOf(20))
				.note("note of repair")
				.device(null)
				.usedElements(new ArrayList<>())
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
		List<RepairDtoGet> repairs = List.of(RepairDtoGet
			.builder()
			.dateCreated(new Date())
			.cost(BigDecimal.valueOf(20))
			.build()
		);
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
		List<RepairDtoGet> repairs = List.of(RepairDtoGet
			.builder()
			.dateCreated(new Date())
			.cost(BigDecimal.valueOf(20))
			.build()
		);
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
		List<RepairDtoGet> repairs = new ArrayList<>();
		// When
		when(repairService.findAll()).thenReturn(repairs);
		// Then
        assertEquals(true, repairService.findAll().isEmpty());
        verify(repairService).findAll();
	}
	
	@Test
	void testFindAllNotEmpty() {
		// Given
		List<RepairDtoGet> repairs = List.of(RepairDtoGet
			.builder()
			.dateCreated(new Date())
			.cost(BigDecimal.valueOf(20))
			.build()
		);
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
		RepairDtoPost repair = RepairDtoPost
				.builder()
				.note("note of repair")
				.cost(BigDecimal.valueOf(20))
				.build();
		RepairDtoGetDetails repairDetails = RepairDtoGetDetails
				.builder()
				.dateCreated(new Date())
				.cost(BigDecimal.valueOf(20))
				.note("note of repair")
				.device(null)
				.usedElements(new ArrayList<>())
				.build();
		// When
		when(repairService.save(repair)).thenReturn(repairDetails);
		// Then
        assertEquals(repairService.save(repair), repairDetails);
        verify(repairService).save(repair);
	}

	@Test
	void testCreateRepair() {
		// Given
		Long id = 1L;
		RepairDtoPost repair = RepairDtoPost
				.builder()
				.note("note of repair")
				.cost(BigDecimal.valueOf(20))
				.build();
		RepairDtoGetDetails repairDetails = RepairDtoGetDetails
				.builder()
				.dateCreated(new Date())
				.cost(BigDecimal.valueOf(20))
				.note("note of repair")
				.device(null)
				.usedElements(new ArrayList<>())
				.build();
		// When
		when(repairService.createRepair(id, id, repair)).thenReturn(repairDetails);
		// Then
        assertEquals(repairService.createRepair(id, id, repair), repairDetails);
        verify(repairService).createRepair(id, id, repair);
	}

	@Test
	void testUpdateRepair() {
		// Given
		Long id = 1L;
		RepairDtoPost repair = RepairDtoPost
				.builder()
				.note("note of repair")
				.cost(BigDecimal.valueOf(20))
				.build();
		RepairDtoGetDetails repairDetails = RepairDtoGetDetails
				.builder()
				.dateCreated(new Date())
				.cost(BigDecimal.valueOf(20))
				.note("updated note of repair")
				.device(null)
				.usedElements(new ArrayList<>())
				.build();
		// When
		when(repairService.updateRepair(id, repair)).thenReturn(repairDetails);
		// Then
        assertEquals(repairService.updateRepair(id, repair), repairDetails);
        verify(repairService).updateRepair(id, repair);
	}

	@Test
	void testDeleteById() {
		// Given
		// When
		// Then
		repairService.deleteById(1L);
		verify(repairService).deleteById(1L);
	}

	@Test
	void testDeleteAll() {
		// Given
		// When
		// Then
		repairService.deleteAll();
		verify(repairService).deleteAll();
	}

	@Test
	void testDeleteByDeviceId() {
		// Given
		// When
		// Then
		repairService.deleteByDeviceId(1L);
		verify(repairService).deleteByDeviceId(1L);
	}

}
