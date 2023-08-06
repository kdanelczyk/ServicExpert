package com.kamil.servicExpert.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kamil.servicExpert.model.Type.TypeDtoGet;
import com.kamil.servicExpert.model.Type.TypeDtoGetDetails;
import com.kamil.servicExpert.model.Type.TypeDtoPost;
import com.kamil.servicExpert.service.TypeService;

public class TypeControllerTest {

    private TypeService typeService;
    private TypeController typeController;

    @BeforeEach
    public void setUp() {
        typeService = mock(TypeService.class);
        typeController = new TypeController(typeService);
    }

    @Test
    public void testGetAllTypes_WithTypes_ReturnsListOfTypes() {
        // given
        List<TypeDtoGet> types = List.of(
            new TypeDtoGet("Type 1"),
            new TypeDtoGet("Type 2")
        );
        // when
        when(typeService.findAll()).thenReturn(types);
        // then
        ResponseEntity<CollectionModel<TypeDtoGet>> response = typeController.getAllTypes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getContent().size());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().stream().toList().get(0).getTypeName().equals("Type 1"));
        assertTrue(response.getBody().getContent().stream().toList().get(1).getTypeName().equals("Type 2"));
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("delete-types"));
    }

    @Test
    public void testGetAllTypes_WithNoTypes_ReturnsNoContent() {
        // given
        // when
        when(typeService.findAll()).thenReturn(new ArrayList<>());
        // then
        ResponseEntity<CollectionModel<TypeDtoGet>> response = typeController.getAllTypes();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetTypeById_WithExistingTypeId_ReturnsTypeDetails() {
        // given
        long typeId = 1L;
        TypeDtoGetDetails typeDetails = new TypeDtoGetDetails(
            "Type 1",
            new ArrayList<>()
        );
        // when
        when(typeService.findById(typeId)).thenReturn(Optional.of(typeDetails));
        // then
        ResponseEntity<EntityModel<TypeDtoGetDetails>> response = typeController.getTypeById(typeId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().getTypeName().equals("Type 1"));
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("all-types"));
        assertNotNull(response.getBody().getLink("all-devices-of-types"));
        assertNotNull(response.getBody().getLink("all-elements-of-types"));
        assertNotNull(response.getBody().getLink("delete-type"));
        assertNotNull(response.getBody().getLink("delete-all-devices-of-type"));
        assertNotNull(response.getBody().getLink("delete-all-elements-of-type"));
    }

    @Test
    public void testGetTypeById_WithNonExistingTypeId_ReturnsNotFound() {
        // given
        long typeId = 999L;
        // when
        when(typeService.findById(typeId)).thenReturn(Optional.empty());
        // then
        ResponseEntity<EntityModel<TypeDtoGetDetails>> response = typeController.getTypeById(typeId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateType_ValidInput_ReturnsCreatedTypeDetails() {
        // given
        TypeDtoPost typeDtoPost = new TypeDtoPost("Type 1");
        TypeDtoGetDetails createdTypeDetails = new TypeDtoGetDetails(
            "Type 1",
            new ArrayList<>()
        );
        // when
        when(typeService.createType(typeDtoPost)).thenReturn(createdTypeDetails);
        // then
        ResponseEntity<TypeDtoGetDetails> response = typeController.createType(typeDtoPost);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(createdTypeDetails, response.getBody());
    }

    @Test
    public void testUpdateType_WithExistingTypeId_ReturnsUpdatedTypeDetails() {
        // given
        long typeId = 1L;
        TypeDtoPost typeDtoPost = new TypeDtoPost("Type 2");
        TypeDtoGetDetails updatedTypeDetails = new TypeDtoGetDetails(
            "Type 2",
            new ArrayList<>()
        );
        // when
        when(typeService.findById(typeId)).thenReturn(Optional.of(updatedTypeDetails));
        when(typeService.updateType(typeId, typeDtoPost)).thenReturn(updatedTypeDetails);
        // then
        ResponseEntity<TypeDtoGetDetails> response = typeController.updateType(typeId, typeDtoPost);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Type 2", response.getBody().getTypeName());
        assertNotNull(response.getBody());
        assertEquals(updatedTypeDetails, response.getBody());
    }

    @Test
    public void testUpdateType_WithNonExistingTypeId_ReturnsNotFound() {
        // given
        long typeId = 999L;
        TypeDtoPost typeDtoPost = new TypeDtoPost("Type 2");
        // when
        when(typeService.findById(typeId)).thenReturn(Optional.empty());
        // then
        ResponseEntity<TypeDtoGetDetails> response = typeController.updateType(typeId, typeDtoPost);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteType_WithExistingTypeId_ReturnsNoContent() {
        // given
        long typeId = 1L;
        // when
        ResponseEntity<HttpStatus> response = typeController.deleteType(typeId);
        // then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(typeService, times(1)).deleteById(typeId);
    }

    @Test
    public void testDeleteAllTypes_AllTypesDeleted_ReturnsNoContent() {
        // given
        // when
        // then
        ResponseEntity<HttpStatus> response = typeController.deleteAllTypes();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(typeService, times(1)).deleteAll();
    }

}
