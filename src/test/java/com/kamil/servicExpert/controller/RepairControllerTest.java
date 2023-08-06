package com.kamil.servicExpert.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import com.kamil.servicExpert.model.Repair.RepairDtoGet;
import com.kamil.servicExpert.model.Repair.RepairDtoGetDetails;
import com.kamil.servicExpert.model.Repair.RepairDtoPost;
import com.kamil.servicExpert.service.RepairService;

public class RepairControllerTest {

    private RepairService repairService;
    private RepairController repairController;

    @BeforeEach
    public void setUp() {
        repairService = mock(RepairService.class);
        repairController = new RepairController(repairService);
    }

    @Test
    public void testGetAllRepairs_WithRepairs_ReturnsListOfRepairs() {
        // given
        List<RepairDtoGet> repairs = List.of(
            new RepairDtoGet(new Date(), new BigDecimal("100.00")),
            new RepairDtoGet(new Date(), new BigDecimal("100.00"))
        );
        // when
        when(repairService.findAll()).thenReturn(repairs);
        // then
        ResponseEntity<CollectionModel<RepairDtoGet>> response = repairController.getAllRepairs();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getContent().size());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().stream().toList().get(0).getCost().equals(new BigDecimal("100.00")));
        assertTrue(response.getBody().getContent().stream().toList().get(1).getCost().equals(new BigDecimal("100.00")));
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("delete-repairs"));
    }

    @Test
    public void testGetAllRepairs_WithNoRepairs_ReturnsNoContent() {
        // given
        // when
        when(repairService.findAll()).thenReturn(new ArrayList<>());
        // then
        ResponseEntity<CollectionModel<RepairDtoGet>> response = repairController.getAllRepairs();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetRepairById_WithExistingRepairId_ReturnsRepairDetails() {
        // given
        long repairId = 1L;
        RepairDtoGetDetails repairDetails = new RepairDtoGetDetails(
            new Date(),
			new BigDecimal("100.00"), 
			"Repair 1", 
			null,
			new ArrayList<>()
        );
        // when
        when(repairService.findById(repairId)).thenReturn(Optional.of(repairDetails));
        // then
        ResponseEntity<EntityModel<RepairDtoGetDetails>> response = repairController.getRepairById(repairId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Repair 1", response.getBody().getContent().getNote());
        assertTrue(response.getBody().getContent().getCost().equals(new BigDecimal("100.00")));
        assertTrue(response.getBody().getContent().getUsedElements().isEmpty());
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("all-repairs"));
        assertNotNull(response.getBody().getLink("delete-repair"));
    }

    @Test
    public void testGetRepairById_WithNonExistingRepairId_ReturnsNotFound() {
        // given
        long repairId = 999L;
        // when
        when(repairService.findById(repairId)).thenReturn(Optional.empty());
        // then
        ResponseEntity<EntityModel<RepairDtoGetDetails>> response = repairController.getRepairById(repairId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetAllRepairsByDeviceId_WithRepairs_ReturnsListOfRepairs() {
        // given
        long deviceId = 1L;
        List<RepairDtoGet> repairsByDeviceId = List.of(
            new RepairDtoGet(new Date(), new BigDecimal("100.00")),
            new RepairDtoGet(new Date(), new BigDecimal("100.00"))
        );
        // when
        when(repairService.findByDeviceId(deviceId)).thenReturn(repairsByDeviceId);
        // then
        ResponseEntity<CollectionModel<RepairDtoGet>> response = repairController.getAllRepairsByDeviceId(deviceId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getContent().size());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().stream().toList().get(0).getCost().equals(new BigDecimal("100.00")));
        assertTrue(response.getBody().getContent().stream().toList().get(1).getCost().equals(new BigDecimal("100.00")));
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("device-by-id"));
        assertNotNull(response.getBody().getLink("delete-repairs_by_device_id"));
    }

    @Test
    public void testGetAllRepairsByDeviceId_WithNoRepairs_ReturnsNoContent() {
        // given
        long deviceId = 1L;
        // when
        when(repairService.findByDeviceId(deviceId)).thenReturn(new ArrayList<>());
        // then
        ResponseEntity<CollectionModel<RepairDtoGet>> response = repairController.getAllRepairsByDeviceId(deviceId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetAllRepairsByUserId_WithRepairs_ReturnsListOfRepairs() {
        // given
        long userId = 1L;
        List<RepairDtoGet> repairsByUserId = List.of(
            new RepairDtoGet(new Date(), new BigDecimal("100.00")),
            new RepairDtoGet(new Date(), new BigDecimal("100.00"))
        );
        // when
        when(repairService.findByUserId(userId)).thenReturn(repairsByUserId);
        // then
        ResponseEntity<CollectionModel<RepairDtoGet>> response = repairController.getAllRepairsByUserId(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getContent().size());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().stream().toList().get(0).getCost().equals(new BigDecimal("100.00")));
        assertTrue(response.getBody().getContent().stream().toList().get(1).getCost().equals(new BigDecimal("100.00")));
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("user-by-id"));
        assertNotNull(response.getBody().getLink("delete-repairs_by_user_id"));
    }

    @Test
    public void testGetAllRepairsByUserId_WithNoRepairs_ReturnsNoContent() {
        // given
        long userId = 1L;
        // when
        when(repairService.findByUserId(userId)).thenReturn(new ArrayList<>());
        // then
        ResponseEntity<CollectionModel<RepairDtoGet>> response = repairController.getAllRepairsByUserId(userId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateRepair_WithValidData_ReturnsCreatedRepair() {
        // given
        RepairDtoPost repairDtoPost = new RepairDtoPost(
			new BigDecimal("100.00"), 
			"Repair 1"
		);
        RepairDtoGetDetails repairDetails = new RepairDtoGetDetails(
            new Date(),
			new BigDecimal("100.00"), 
			"Repair 1", 
			null,
			new ArrayList<>()
        );
        // when
        when(repairService.createRepair(1L, 1L, repairDtoPost)).thenReturn(repairDetails);
        // then
        ResponseEntity<RepairDtoGetDetails> response = repairController.createRepair(1L, 1L, repairDtoPost);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Repair 1", response.getBody().getNote());
        assertTrue(response.getBody().getCost().equals(new BigDecimal("100.00")));
        assertTrue(response.getBody().getUsedElements().isEmpty());
    }

    @Test
    public void testDeleteRepair_WithExistingRepairId_ReturnsNoContent() {
        // given
        long repairId = 1L;
        // when
        ResponseEntity<?> response = repairController.deleteRepair(repairId);
        // then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(repairService, times(1)).deleteById(repairId);
    }

    @Test
    public void testAddUsedElementToRepair_WithValidData_ReturnsOk() {
        // given
        long repairId = 1L;
        long elementId = 1L;
        RepairDtoGetDetails repairDetails = new RepairDtoGetDetails(
            new Date(),
			new BigDecimal("100.00"), 
			"Repair 1", 
			null,
			new ArrayList<>()
        );
        // when
        when(repairService.addElementToRepair(repairId, elementId)).thenReturn(repairDetails);
        // then
        ResponseEntity<?> response = repairController.addElement(repairId, elementId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRemoveUsedElementFromRepair_WithValidData_ReturnsOk() {
        // given
        long repairId = 1L;
        long elementId = 1L;
        // when
        repairController.deleteElementFromRepair(repairId, elementId);
        // then
        verify(repairService, times(1)).deleteElementFromRepair(repairId, elementId);
    }

}
