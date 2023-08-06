package com.kamil.servicExpert.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kamil.servicExpert.model.Element.ElementDtoGet;
import com.kamil.servicExpert.model.Element.ElementDtoGetDetails;
import com.kamil.servicExpert.model.Element.ElementDtoPost;
import com.kamil.servicExpert.service.ElementService;

public class ElementControllerTest {

    private ElementService elementService;
    private ElementController elementController;

    @BeforeEach
    public void setUp() {
        elementService = mock(ElementService.class);
        elementController = new ElementController(elementService);
    }

    @Test
    public void testGetAllElements_WithElements_ReturnsListOfElements() {
        // given
        List<ElementDtoGet> elements = List.of(
            new ElementDtoGet(50, "Element 1"),
            new ElementDtoGet(60, "Element 2")
        );
        // when
        when(elementService.findAll()).thenReturn(elements);
        // then
        ResponseEntity<CollectionModel<ElementDtoGet>> response = elementController.getAllElements();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getContent().size());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().stream().toList().get(0).getElementName().equals("Element 1"));
        assertTrue(response.getBody().getContent().stream().toList().get(1).getElementName().equals("Element 2"));
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("delete-elements"));
    }

    @Test
    public void testGetAllElements_WithNoElements_ReturnsNoContent() {
        // given
        // when
        when(elementService.findAll()).thenReturn(new ArrayList<>());
        // then
        ResponseEntity<CollectionModel<ElementDtoGet>> response = elementController.getAllElements();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetElementById_WithExistingElementId_ReturnsElementDetails() {
        // given
        long elementId = 1L;
        ElementDtoGetDetails elementDetails = new ElementDtoGetDetails(
            50,
            10, 
            "Element 1", 
            new BigDecimal("70.00")
        );
        // when
        when(elementService.findById(elementId)).thenReturn(Optional.of(elementDetails));
        // then
        ResponseEntity<EntityModel<ElementDtoGetDetails>> response = elementController.getElementById(elementId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().getElementName().equals("Element 1"));
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("all-elements"));
        assertNotNull(response.getBody().getLink("delete-element"));
    }

    @Test
    public void testGetElementById_WithNonExistingElementId_ReturnsNotFound() {
        // given
        long elementId = 999L;
        // when
        when(elementService.findById(elementId)).thenReturn(Optional.empty());
        // then
        ResponseEntity<EntityModel<ElementDtoGetDetails>> response = elementController.getElementById(elementId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    public void testGetAllElementsByTypeId_WithElementsOfType_ReturnsListOfElements() {
        // given
        long typeId = 1L;
        List<ElementDtoGet> elementsOfType = List.of(
            new ElementDtoGet(50, "Element 1"),
            new ElementDtoGet(60, "Element 2")
        );
        // when
        when(elementService.findByTypeId(typeId)).thenReturn(elementsOfType);
        // then
        ResponseEntity<CollectionModel<ElementDtoGet>> response = elementController.getAllElementsByTypeId(typeId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getContent().size());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().stream().toList().get(0).getElementName().equals("Element 1"));
        assertTrue(response.getBody().getContent().stream().toList().get(1).getElementName().equals("Element 2"));
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("type-by-id"));
        assertNotNull(response.getBody().getLink("delete-elements_by_type_id"));
    }

    @Test
    public void testGetAllElementsByTypeId_WithNoElementsOfType_ReturnsNoContent() {
        // given
        long typeId = 1L;
        // when
        when(elementService.findByTypeId(typeId)).thenReturn(new ArrayList<>());
        // then
        ResponseEntity<CollectionModel<ElementDtoGet>> response = elementController.getAllElementsByTypeId(typeId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetAllElements_WithNoContent_ReturnsNoContent() {
        // given
        // when
        when(elementService.findAll()).thenReturn(new ArrayList<>());
        // then
        ResponseEntity<CollectionModel<ElementDtoGet>> response = elementController.getAllElements();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateElementForType_ValidInput_ReturnsCreatedElementDetails() {
        // given
        long typeId = 1L;
        ElementDtoPost elementDtoPost = new ElementDtoPost(
            50,
            10, 
            "Element 1", 
            new BigDecimal("70.00")
        );
        ElementDtoGetDetails createdElementDetails = new ElementDtoGetDetails(
            20,
            10, 
            "Element 2", 
            new BigDecimal("30.00")
        );
        // when
        when(elementService.createElementForType(typeId, elementDtoPost)).thenReturn(createdElementDetails);
        // then
        ResponseEntity<ElementDtoGetDetails> response = elementController.createElementForType(typeId, elementDtoPost);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(createdElementDetails, response.getBody());
    }

    @Test
    public void testUpdateElement_WithExistingElementId_ReturnsUpdatedElementDetails() {
        // given
        long elementId = 1L;
        ElementDtoPost elementDtoPost = new ElementDtoPost(
            50,
            10, 
            "Element 1", 
            new BigDecimal("70.00")
        );
        ElementDtoGetDetails updatedElementDetails = new ElementDtoGetDetails(
            50,
            10, 
            "Element 2", 
            new BigDecimal("70.00")
        );
        // when
        when(elementService.findById(elementId)).thenReturn(Optional.of(updatedElementDetails));
        when(elementService.updateElement(elementId, elementDtoPost)).thenReturn(updatedElementDetails);
        // then
        ResponseEntity<ElementDtoGetDetails> response = elementController.updateElement(elementId, elementDtoPost);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Element 2", response.getBody().getElementName());
        assertNotNull(response.getBody());
        assertEquals(updatedElementDetails, response.getBody());
    }

    @Test
    public void testUpdateElement_WithNonExistingElementId_ReturnsNotFound() {
        // given
        long elementId = 999L;
        ElementDtoPost elementDtoPost = new ElementDtoPost(
            50,
            10, 
            "Element 1", 
            new BigDecimal("70.00")
        );
        // when
        when(elementService.findById(elementId)).thenReturn(Optional.empty());
        // then
        ResponseEntity<ElementDtoGetDetails> response = elementController.updateElement(elementId, elementDtoPost);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateElement_WithInvalidElementId_ReturnsNotFound() {
        // given
        long elementId = 999L;
        ElementDtoPost elementDtoPost = new ElementDtoPost(
            50,
            10, 
            "Element 1", 
            new BigDecimal("70.00")
        );
        // when
        when(elementService.findById(elementId)).thenReturn(Optional.empty());
        // then
        ResponseEntity<ElementDtoGetDetails> response = elementController.updateElement(elementId, elementDtoPost);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteElement_WithExistingElementId_ReturnsNoContent() {
        // given
        long elementId = 1L;
        // when
        ResponseEntity<HttpStatus> response = elementController.deleteElement(elementId);
        // then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(elementService, times(1)).deleteById(elementId);
    }

    @Test
    public void testDeleteAllElements_AllElementsDeleted_ReturnsNoContent() {
        // given
        // when
        // then
        ResponseEntity<HttpStatus> response = elementController.deleteAllElements();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(elementService, times(1)).deleteAll();
    }

    @Test
    public void testDeleteAllElementsOfType_WithExistingTypeId_ReturnsNoContent() {
        // given
        long typeId = 1L;
        // when
        // then
        ResponseEntity<List<ElementDtoGet>> response = elementController.deleteAllElementsOfType(typeId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(elementService, times(1)).deleteByTypeId(typeId);
    }

}
