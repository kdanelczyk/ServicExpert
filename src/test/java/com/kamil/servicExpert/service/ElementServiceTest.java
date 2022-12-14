package com.kamil.servicExpert.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.kamil.servicExpert.db.model.Element;
import com.kamil.servicExpert.db.model.Repair;

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
		Element element = Element
				.builder()
				.id(1L)
				.quantity(1)
				.criticalQuantity(2)
				.nameOfElement("nameOfElement")
				.priceOfElement(20)
				.build();
		// When
		when(elementService.findById(id)).thenReturn(Optional.of(element));
		// Then
        assertEquals(elementService.findById(id), Optional.of(element));
        assertEquals(elementService.findById(id).get().getNameOfElement(), "nameOfElement");
        assertEquals(elementService.findById(id).get().getPriceOfElement(), 20);
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
		Element element = Element
				.builder()
				.id(1L)
				.quantity(1)
				.criticalQuantity(2)
				.nameOfElement("nameOfElement")
				.priceOfElement(20)
				.build();
		List<Element> elements = List.of(element);
		// When
		when(elementService.findByTypeId(id)).thenReturn(elements);
		// Then
		assertEquals(1, elementService.findByTypeId(id).size());
		verify(elementService).findByTypeId(id);
	}

	@Test
	void testFindByQuantity() {
		// Given
		Element element = Element
				.builder()
				.id(1L)
				.quantity(1)
				.criticalQuantity(2)
				.nameOfElement("nameOfElement")
				.priceOfElement(20)
				.build();
		List<Element> elements = List.of(element);
		// When
		when(elementService.findByQuantity(1)).thenReturn(elements);
		// Then
		assertEquals(1, elementService.findByQuantity(1).size());
		verify(elementService).findByQuantity(1);
	}

	@Test
	void testFindByPriceOfElement() {
		// Given
		Element element = Element
				.builder()
				.id(1L)
				.quantity(1)
				.criticalQuantity(2)
				.nameOfElement("nameOfElement")
				.priceOfElement(20)
				.build();
		List<Element> elements = List.of(element);
		// When
		when(elementService.findByPriceOfElement(20)).thenReturn(elements);
		// Then
		assertEquals(1, elementService.findByPriceOfElement(20).size());
		verify(elementService).findByPriceOfElement(20);
	}

	@Test
	void testFindByNameOfElement() {
		// Given
		Element element = Element
				.builder()
				.id(1L)
				.quantity(1)
				.criticalQuantity(2)
				.nameOfElement("nameOfElement")
				.priceOfElement(20)
				.build();
		List<Element> elements = List.of(element);
		// When
		when(elementService.findByNameOfElement("nameOfElement")).thenReturn(elements);
		// Then
		assertEquals(1, elementService.findByNameOfElement("nameOfElement").size());
		verify(elementService).findByNameOfElement("nameOfElement");
	}

	@Test
	void testFindElementsByRepairId() {
		// Given
		Long id = 1L;
		Element element = Element
				.builder()
				.id(1L)
				.quantity(1)
				.criticalQuantity(2)
				.nameOfElement("nameOfElement")
				.priceOfElement(20)
				.build();
		List<Element> elements = List.of(element);
		// When
		when(elementService.findElementsByRepairId(id)).thenReturn(elements);
		// Then
		assertEquals(1, elementService.findElementsByRepairId(id).size());
		verify(elementService).findElementsByRepairId(id);
	}

	@Test
	void testFindRepairsByElementsId() {
		// Given
		Long id = 1L;
		Repair repair = Repair
				.builder()
				.note("note of repair")
				.cost(20)
				.build();
		List<Repair> repairs = List.of(repair);
		// When
		when(elementService.findRepairsByElementsId(id)).thenReturn(repairs);
		// Then
		assertEquals(1, elementService.findRepairsByElementsId(id).size());
		verify(elementService).findRepairsByElementsId(id);
	}

	@Test
	void testFindAll() {
		// Given
		Element element = Element
				.builder()
				.id(1L)
				.quantity(1)
				.criticalQuantity(2)
				.nameOfElement("nameOfElement")
				.priceOfElement(20)
				.build();
		List<Element> elements = List.of(element);
		// When
		when(elementService.findAll()).thenReturn(elements);
        assertEquals(1, elementService.findAll().size());
        assertEquals(false, elementService.findAll().isEmpty());
        verify(elementService, times(2)).findAll();
	}
	
	@Test
	void testFindAllEmpty() {
		// Given
		List<Element> elements = List.of();
		// When
		when(elementService.findAll()).thenReturn(elements);
        assertEquals(0, elementService.findAll().size());
        assertEquals(true, elementService.findAll().isEmpty());
        verify(elementService, times(2)).findAll();
	}

	@Test
	void testSave() {
		// Given
		Element element = Element
				.builder()
				.id(1L)
				.quantity(1)
				.criticalQuantity(2)
				.nameOfElement("nameOfElement")
				.priceOfElement(20)
				.build();
		// When
		when(elementService.save(element)).thenReturn(element);
		// Then
        assertEquals(elementService.save(element), element);
        verify(elementService).save(element);
	}

	@Test
	void testCreateElementForType() {
		// Given
		Long id = 1L;
		Element element = Element
				.builder()
				.id(1L)
				.quantity(1)
				.criticalQuantity(2)
				.nameOfElement("nameOfElement")
				.priceOfElement(20)
				.build();
		// When
		when(elementService.createElementForType(id, element)).thenReturn(element);
		// Then
        assertEquals(elementService.createElementForType(id, element), element);
        verify(elementService).createElementForType(id, element);
	}

	@Test
	void testUpdateElement() {
		// Given
		Long id = 1L;
		Element element = Element
				.builder()
				.id(1L)
				.quantity(1)
				.criticalQuantity(2)
				.nameOfElement("nameOfElement")
				.priceOfElement(20)
				.build();
		Element updatedElement = Element
				.builder()
				.id(1L)
				.quantity(1)
				.criticalQuantity(2)
				.nameOfElement("nameOfElement")
				.priceOfElement(20)
				.build();
		// When
		when(elementService.updateElement(id, element)).thenReturn(updatedElement);
		// Then
        assertEquals(elementService.updateElement(id, element), updatedElement);
        verify(elementService).updateElement(id, element);
	}

	@Test
	void testAddElementToRepair() {
		// Given
		Long id = 1L;
		Element element = Element
				.builder()
				.id(1L)
				.quantity(1)
				.criticalQuantity(2)
				.nameOfElement("nameOfElement")
				.priceOfElement(20)
				.build();
		Element updatedElement = Element
				.builder()
				.id(1L)
				.quantity(1)
				.criticalQuantity(2)
				.nameOfElement("nameOfElement")
				.priceOfElement(20)
				.build();
		// When
		when(elementService.updateElement(id, element)).thenReturn(updatedElement);
		// Then
        assertEquals(elementService.updateElement(id, element), updatedElement);
        verify(elementService).updateElement(id, element);
	}

	@Test
	void testDeleteById() {
		elementService.deleteById(1L);
		verify(elementService).deleteById(1L);
	}

	@Test
	void testDeleteAll() {
		elementService.deleteAll();
		verify(elementService).deleteAll();
	}

	@Test
	void testDeleteByTypeId() {
		Long id = 1L;
		elementService.deleteByTypeId(id);
		verify(elementService).deleteByTypeId(id);
	}

	@Test
	void testDeleteElementFromRepair() {
		Long id = 1L;
		elementService.deleteElementFromRepair(id, id);
		verify(elementService).deleteElementFromRepair(id, id);
	}

}
