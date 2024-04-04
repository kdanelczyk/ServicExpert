package com.kamil.servicExpert.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.kamil.servicExpert.model.Element.ElementDtoGet;
import com.kamil.servicExpert.model.Element.ElementDtoGetDetails;
import com.kamil.servicExpert.model.Element.ElementDtoPost;

@WebMvcTest(ElementService.class)
@ExtendWith(MockitoExtension.class)
class ElementServiceTest {

	@MockBean
	private ElementService elementService;

	@Test
	void testExistsByIdFalse() {
		// Given
		Long id = 1L;
		// When
		when(elementService.existsById(id)).thenReturn(false);
		// Then
        assertEquals(elementService.existsById(id), false);
        verify(elementService).existsById(id);
        
	}

	@Test
	void testExistsByIdTrue() {
		// Given
		Long id = 1L;
		// When
		when(elementService.existsById(id)).thenReturn(true);
		// Then
        assertEquals(elementService.existsById(id), true);
        verify(elementService).existsById(id);
	}
	
	@Test
	void testFindById() {
		// Given
		Long id = 1L;
		ElementDtoGetDetails element = ElementDtoGetDetails
				.builder()
				.quantity(1)
				.criticalQuantity(2)
				.elementName("nameOfElement")
				.elementPrice(BigDecimal.valueOf(20))
				.build();
		// When
		when(elementService.findById(id)).thenReturn(Optional.of(element));
		// Then
        assertEquals(elementService.findById(id), Optional.of(element));
        assertEquals(elementService.findById(id).get().getElementName(), "nameOfElement");
        assertEquals(elementService.findById(id).get().getElementPrice(), BigDecimal.valueOf(20));
        verify(elementService, times(3)).findById(id);
	}
	
	@Test
	void testFindByIdEmpty() {
		// Given
		Long id = 1L;
		// When
		when(elementService.findById(id)).thenReturn(Optional.empty());
		// Then
        assertEquals(elementService.findById(id), Optional.empty());
        verify(elementService).findById(id);
	}

	@Test
	void testFindByTypeId() {
		// Given
		Long id = 1L;
		List<ElementDtoGet> elements = List.of(ElementDtoGet
				.builder()
				.quantity(1)
				.elementName("nameOfElement")
				.build()
		);
		// When
		when(elementService.findByTypeId(id)).thenReturn(elements);
		// Then
		assertEquals(1, elementService.findByTypeId(id).size());
		verify(elementService).findByTypeId(id);
	}

	@Test
	void testFindAll() {
		// Given
		List<ElementDtoGet> elements = List.of(ElementDtoGet
				.builder()
				.quantity(1)
				.elementName("nameOfElement")
				.build()
		);
		// When
		when(elementService.findAll()).thenReturn(elements);
        assertEquals(1, elementService.findAll().size());
        assertEquals(false, elementService.findAll().isEmpty());
        verify(elementService, times(2)).findAll();
	}
	
	@Test
	void testFindAllEmpty() {
		// Given
		List<ElementDtoGet> elements = List.of();
		// When
		when(elementService.findAll()).thenReturn(elements);
        assertEquals(0, elementService.findAll().size());
        assertEquals(true, elementService.findAll().isEmpty());
        verify(elementService, times(2)).findAll();
	}

	@Test
	void testSave() {
		// Given
		ElementDtoPost element = ElementDtoPost
				.builder()
				.quantity(1)
				.criticalQuantity(2)
				.elementName("nameOfElement")
				.elementPrice(BigDecimal.valueOf(20))
				.build();
		ElementDtoGetDetails elementDetails = ElementDtoGetDetails
				.builder()
				.quantity(1)
				.criticalQuantity(2)
				.elementName("nameOfElement")
				.elementPrice(BigDecimal.valueOf(20))
				.build();
		// When
		when(elementService.save(element)).thenReturn(elementDetails);
		// Then
        assertEquals(elementService.save(element), elementDetails);
        verify(elementService).save(element);
	}

	@Test
	void testCreateElementForType() {
		// Given
		Long id = 1L;
		ElementDtoPost element = ElementDtoPost
				.builder()
				.quantity(1)
				.criticalQuantity(2)
				.elementName("nameOfElement")
				.elementPrice(BigDecimal.valueOf(20))
				.build();
		ElementDtoGetDetails elementDetails = ElementDtoGetDetails
				.builder()
				.quantity(1)
				.criticalQuantity(2)
				.elementName("nameOfElement")
				.elementPrice(BigDecimal.valueOf(20))
				.build();
		// When
		when(elementService.createElementForType(id, element)).thenReturn(elementDetails);
		// Then
        assertEquals(elementService.createElementForType(id, element), elementDetails);
        verify(elementService).createElementForType(id, element);
	}

	@Test
	void testUpdateElement() {
		// Given
		Long id = 1L;
		ElementDtoPost element = ElementDtoPost
				.builder()
				.quantity(1)
				.criticalQuantity(2)
				.elementName("nameOfElement")
				.elementPrice(BigDecimal.valueOf(20))
				.build();
		ElementDtoGetDetails elementDetails = ElementDtoGetDetails
				.builder()
				.quantity(1)
				.criticalQuantity(2)
				.elementName("nameOfUpdated")
				.elementPrice(BigDecimal.valueOf(20))
				.build();
		// When
		when(elementService.updateElement(id, element)).thenReturn(elementDetails);
		// Then
        assertEquals(elementService.updateElement(id, element), elementDetails);
        verify(elementService).updateElement(id, element);
	}

	@Test
	void testDeleteById() {
		// Given
		// When
		// Then
		elementService.deleteById(1L);
		verify(elementService).deleteById(1L);
	}

	@Test
	void testDeleteAll() {
		// Given
		// When
		// Then
		elementService.deleteAll();
		verify(elementService).deleteAll();
	}

	@Test
	void testDeleteByTypeId() {
		// Given
		Long id = 1L;
		// When
		// Then
		elementService.deleteByTypeId(id);
		verify(elementService).deleteByTypeId(id);
	}

}
