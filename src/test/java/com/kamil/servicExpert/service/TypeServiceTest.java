package com.kamil.servicExpert.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.kamil.servicExpert.model.Type.TypeDtoGet;
import com.kamil.servicExpert.model.Type.TypeDtoGetDetails;
import com.kamil.servicExpert.model.Type.TypeDtoPost;

@WebMvcTest(TypeService.class)
@ExtendWith(MockitoExtension.class)
class TypeServiceTest {

	@MockBean
	private TypeService typeService;
	
	@Test
	void testExistsByIdFalse() {
		// Given
		Long id = 1L;
		// When
		when(typeService.existsById(id)).thenReturn(false);
		// Then
        assertEquals(typeService.existsById(id), false);
        verify(typeService).existsById(id);
        
	}

	@Test
	void testExistsByIdTrue() {
		// Given
		Long id = 1L;
		// When
		when(typeService.existsById(id)).thenReturn(true);
		// Then
        assertEquals(typeService.existsById(id), true);
        verify(typeService).existsById(id);
	}
	
	@Test
	void testFindById() {
		// Given
		Long id = 1L;
		TypeDtoGetDetails type = TypeDtoGetDetails
				.builder()
				.typeName("name")
				.elements(new ArrayList<>())
				.build();
		// When
		when(typeService.findById(id)).thenReturn(Optional.of(type));
		// Then
        assertEquals(typeService.findById(id), Optional.of(type));
        assertEquals(typeService.findById(id).get().getTypeName(), "name");
        verify(typeService, times(2)).findById(id);
	}
	
	@Test
	void testFindByIdEmpty() {
		// Given
		Long id = 1L;
		// When
		when(typeService.findById(id)).thenReturn(Optional.empty());
		// Then
        assertEquals(typeService.findById(id), Optional.empty());
        verify(typeService).findById(id);
	}
	
	@Test
	void testFindAllEmpty() {
		// Given
		List<TypeDtoGet> types = new ArrayList<>();
		// When
		when(typeService.findAll()).thenReturn(types);
		// Then
        assertEquals(true, typeService.findAll().isEmpty());
        verify(typeService).findAll();
	}
	
	@Test
	void testFindAllNotEmpty() {
		// Given
		List<TypeDtoGet> types = List.of(TypeDtoGet
				.builder()
				.typeName("name")
				.build()
		);
		// When
		when(typeService.findAll()).thenReturn(types);
		// Then
        assertEquals(false, typeService.findAll().isEmpty());
        assertEquals(1, typeService.findAll().size());
        verify(typeService, times(2)).findAll();
	}

	@Test
	void testSave() {
		// Given;
		TypeDtoPost type = TypeDtoPost
				.builder()
				.typeName("name")
				.build();
		TypeDtoGetDetails typeDetails = TypeDtoGetDetails
				.builder()
				.typeName("name")
				.elements(new ArrayList<>())
				.build();
		// When
		when(typeService.save(type)).thenReturn(typeDetails);
		// Then
        assertEquals(typeService.save(type), typeDetails);
        verify(typeService).save(type);
	}

	@Test
	void testCreateType() {
		// Given
		TypeDtoPost type = TypeDtoPost
				.builder()
				.typeName("name")
				.build();
		TypeDtoGetDetails typeDetails = TypeDtoGetDetails
				.builder()
				.typeName("name")
				.elements(new ArrayList<>())
				.build();
		// When
		when(typeService.createType(type)).thenReturn(typeDetails);
		// Then
        assertEquals(typeService.createType(type), typeDetails);
        verify(typeService).createType(type);
	}

	@Test
	void testUpdateType() {
		// Given
		Long id = 1L;
		TypeDtoPost type = TypeDtoPost
				.builder()
				.typeName("name")
				.build();
		TypeDtoGetDetails typeDetails = TypeDtoGetDetails
				.builder()
				.typeName("updated name")
				.elements(new ArrayList<>())
				.build();
		// When
		when(typeService.updateType(id, type)).thenReturn(typeDetails);
		// Then
        assertEquals(typeService.updateType(id, type), typeDetails);
        verify(typeService).updateType(id, type);
	}

	@Test
	void testDeleteById() {
		// Given
		// When
		// Then
		typeService.deleteById(1L);
		verify(typeService).deleteById(1L);
	}

	@Test
	void testDeleteAll() {
		// Given
		// When
		// Then
		typeService.deleteAll();
		verify(typeService).deleteAll();
	}
	
}
