package com.kamil.servicExpert.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import com.kamil.servicExpert.db.model.Device;
import com.kamil.servicExpert.model.Device.DeviceDtoGet;
import com.kamil.servicExpert.model.Device.DeviceDtoGetDetails;
import com.kamil.servicExpert.model.Device.DeviceDtoPost;
import com.kamil.servicExpert.service.DeviceService;

public class DeviceControllerTest {

    private DeviceService deviceService;
    private DeviceController deviceController;

    @BeforeEach
    public void setUp() {
        deviceService = mock(DeviceService.class);
        deviceController = new DeviceController(deviceService);
    }

    @Test
    public void testFindByRepaired_WithRepairedDevices_ReturnsListOfDevices() {
        // given
        List<DeviceDtoGet> repairedDevices = List.of(
            new DeviceDtoGet("John Doe", true),
            new DeviceDtoGet("Jane Smith", true)
        );
        // when
        when(deviceService.findByRepaired(true)).thenReturn(repairedDevices);
        // then
        ResponseEntity<CollectionModel<DeviceDtoGet>> response = deviceController.findByRepaired();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getContent().size());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().stream().toList().get(0).isRepaired());
        assertTrue(response.getBody().getContent().stream().toList().get(1).isRepaired());
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("not-repaired-devices"));
        assertNotNull(response.getBody().getLink("all-devices"));
    }

	@Test
    public void testFindByNotRepaired_WithNotRepairedDevices_ReturnsListOfDevices() {
        // given
        List<DeviceDtoGet> notRepairedDevices = List.of(
            new DeviceDtoGet("John Doe", false),
            new DeviceDtoGet("Jane Smith", false)
        );
        // when
        when(deviceService.findByRepaired(false)).thenReturn(notRepairedDevices);
        // then
        ResponseEntity<CollectionModel<DeviceDtoGet>> response = deviceController.findByNotRepaired();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getContent().size());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().getContent().stream().toList().get(0).isRepaired());
        assertFalse(response.getBody().getContent().stream().toList().get(1).isRepaired());
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("not-repaired-devices"));
        assertNotNull(response.getBody().getLink("all-devices"));
    }

    @Test
    public void testFindByRepaired_WithNoRepairedDevices_ReturnsNoContent() {
        // given
        // when
        when(deviceService.findByRepaired(true)).thenReturn(new ArrayList<>());
        // then
        ResponseEntity<CollectionModel<DeviceDtoGet>> response = deviceController.findByRepaired();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testFindByNotRepaired_WithNoNotRepairedDevices_ReturnsNoContent() {
        // given
        // when
        when(deviceService.findByRepaired(false)).thenReturn(new ArrayList<>());
        // then
        ResponseEntity<CollectionModel<DeviceDtoGet>> response = deviceController.findByNotRepaired();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetAllDevices() {
        // given
        List<DeviceDtoGet> devices = List.of(
            new DeviceDtoGet("John Doe", true),
            new DeviceDtoGet("Jane Smith", false),
            new DeviceDtoGet("Jane Doe", true)
        );
        // when
        when(deviceService.findAll()).thenReturn(devices); 
        // then
        ResponseEntity<CollectionModel<DeviceDtoGet>> response = deviceController.getAllDevices();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, response.getBody().getContent().size());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().stream().toList().get(0).isRepaired());
        assertFalse(response.getBody().getContent().stream().toList().get(1).isRepaired());
        assertTrue(response.getBody().getContent().stream().toList().get(2).isRepaired());
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("not-repaired-devices"));
        assertNotNull(response.getBody().getLink("all-devices"));
    }

    @Test
    public void testGetDeviceById_WithExistingDeviceId_ReturnsDeviceDetails() {
        // given
        long deviceId = 1L;
        DeviceDtoGetDetails deviceDetails = new DeviceDtoGetDetails(
            404404404, 
            "Device 1", new Date(), 
            true
        );
        // when
        when(deviceService.findById(deviceId)).thenReturn(Optional.of(deviceDetails));
        // then
        ResponseEntity<EntityModel<DeviceDtoGetDetails>> response = deviceController.getDeviceById(deviceId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().isRepaired());
        assertEquals(404404404, response.getBody().getContent().getCustomerPhoneNumber());
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("all-devices"));
        assertNotNull(response.getBody().getLink("all-repairs-by-device-id"));
        assertNotNull(response.getBody().getLink("delete-device"));
        assertNotNull(response.getBody().getLink("delete-all-repairs-of-device"));
    }

    @Test
    public void testGetDeviceById_WithNonExistingDeviceId_ReturnsNotFound() {
        // given
        long deviceId = 999L;
        // when
        when(deviceService.findById(deviceId)).thenReturn(Optional.empty());
        // then
        ResponseEntity<EntityModel<DeviceDtoGetDetails>> response = deviceController.getDeviceById(deviceId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

	@Test
    public void testGetAllDevicesByTypeId_WithExistingTypeId_ReturnsListOfDevices() {
        // given
        long typeId = 1L;
        List<DeviceDtoGet> devicesOfType = List.of(
            new DeviceDtoGet("John Doe", true),
            new DeviceDtoGet("Jane Smith", true)
        );
        // when
        when(deviceService.findByTypeId(typeId)).thenReturn(devicesOfType);
        // then
        ResponseEntity<CollectionModel<DeviceDtoGet>> response = deviceController.getAllDevicesByTypeId(typeId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("type-id"));
        assertNotNull(response.getBody().getLink("delete-devices-by-type-id"));
    }

    @Test
    public void testGetAllDevicesByTypeId_WithNoDevicesOfType_ReturnsNoContent() {
        // given
        long typeId = 1L;
        // when
        when(deviceService.findByTypeId(typeId)).thenReturn(new ArrayList<>());
        ResponseEntity<CollectionModel<DeviceDtoGet>> response = deviceController.getAllDevicesByTypeId(typeId);
        // then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

	@Test
    public void testGetAllDevices_WithDevices_ReturnsListOfDevices() {
        // given
        List<DeviceDtoGet> devices = List.of(
            new DeviceDtoGet("John Doe", true),
            new DeviceDtoGet("John Doe", true)
        );
        // when
        when(deviceService.findAll()).thenReturn(devices);
        // then
        ResponseEntity<CollectionModel<DeviceDtoGet>> response = deviceController.getAllDevices();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("repaired-devices"));
        assertNotNull(response.getBody().getLink("not-repaired-devices"));
        assertNotNull(response.getBody().getLink("all-devices"));
        assertNotNull(response.getBody().getLink("delete-devices"));
    }

    @Test
    public void testGetAllDevices_WithNoDevices_ReturnsNoContent() {
        // given
        // when
        when(deviceService.findAll()).thenReturn(new ArrayList<>());
        // then
        ResponseEntity<CollectionModel<DeviceDtoGet>> response = deviceController.getAllDevices();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateDevice_WithInvalidDeviceId_ReturnsNotFound() {
        // given
        long deviceId = 999L;
        DeviceDtoPost deviceDtoPost = new DeviceDtoPost(
            404404404, 
            "John Doe"
        );
        // when
        when(deviceService.findById(deviceId)).thenReturn(Optional.empty());
        // then
        ResponseEntity<DeviceDtoGetDetails> response = deviceController.updateDevice(deviceId, deviceDtoPost);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetAllDevices_WithNoContent_ReturnsNoContent() {
        // given
        // when
        when(deviceService.findAll()).thenReturn(new ArrayList<>());
        // then
        ResponseEntity<CollectionModel<DeviceDtoGet>> response = deviceController.getAllDevices();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetAllDevicesByTypeId_WithNoContent_ReturnsNoContent() {
        // given
        long typeId = 1L;
        // when
        when(deviceService.findByTypeId(typeId)).thenReturn(new ArrayList<>());
        // then
        ResponseEntity<CollectionModel<DeviceDtoGet>> response = deviceController.getAllDevicesByTypeId(typeId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
    
    @Test
    public void testCreateDeviceForType_ValidInput_ReturnsCreatedDeviceDetails() {
        // given
        long typeId = 1L;
        DeviceDtoPost deviceDtoPost = new DeviceDtoPost(
            404404404, 
            "John Doe"
        );
        DeviceDtoGetDetails createdDeviceDetails = new DeviceDtoGetDetails(
            404404404, 
            "Device 1", new Date(), 
            true
        );
        // when
        when(deviceService.createDeviceForType(typeId, deviceDtoPost)).thenReturn(createdDeviceDetails);
        // then
        ResponseEntity<DeviceDtoGetDetails> response = deviceController.createDeviceForType(typeId, deviceDtoPost);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(createdDeviceDetails, response.getBody());
    }
	
    @Test
    public void testUpdateDevice_WithExistingDeviceId_ReturnsUpdatedDeviceDetails() {
        // given
        long deviceId = 1L;
        DeviceDtoPost deviceDtoPost = new DeviceDtoPost(
            404404404, 
            "John Doe"
        );
        DeviceDtoGetDetails updatedDeviceDetails = new DeviceDtoGetDetails(
            404404404,
            "Device 1", 
            new Date(), 
            true
        );
        // when
        when(deviceService.findById(deviceId)).thenReturn(Optional.of(updatedDeviceDetails));
        when(deviceService.updateDevice(deviceId, deviceDtoPost)).thenReturn(updatedDeviceDetails);
        // then
        ResponseEntity<DeviceDtoGetDetails> response = deviceController.updateDevice(deviceId, deviceDtoPost);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Device 1", response.getBody().getCustomerName());
        assertNotNull(response.getBody());
        assertEquals(updatedDeviceDetails, response.getBody());
    }

    @Test
    public void testUpdateDevice_WithNonExistingDeviceId_ReturnsNotFound() {
        // given
        long deviceId = 999L;
        DeviceDtoPost deviceDtoPost = new DeviceDtoPost(
            404404404, 
            "John Doe"
        );
        // when
        when(deviceService.findById(deviceId)).thenReturn(Optional.empty());
        // then
        ResponseEntity<DeviceDtoGetDetails> response = deviceController.updateDevice(deviceId, deviceDtoPost);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteDevice_WithExistingDeviceId_ReturnsNoContent() {
        // given
        long deviceId = 1L;
        // when
        ResponseEntity<HttpStatus> response = deviceController.deleteDevice(deviceId);
        //then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(deviceService, times(1)).deleteById(deviceId);
    }

	@Test
    public void testDeleteAllDevices_AllDevicesDeleted_ReturnsNoContent() {
        // given
        //when
        //then
        ResponseEntity<HttpStatus> response = deviceController.deleteAllDevices();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(deviceService, times(1)).deleteAll();
    }

    @Test
    public void testDeleteAllDevicesOfType_WithExistingTypeId_ReturnsNoContent() {
        // given
        long typeId = 1L;
        //when
        //then
        ResponseEntity<List<Device>> response = deviceController.deleteAllDevicesOfType(typeId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(deviceService, times(1)).deleteByTypeId(typeId);
    }

}
