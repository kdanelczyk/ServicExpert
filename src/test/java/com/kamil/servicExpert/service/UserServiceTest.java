package com.kamil.servicExpert.service;

import static org.junit.jupiter.api.Assertions.*;
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

import com.kamil.servicExpert.model.User.UserDtoGet;

@WebMvcTest(UserService.class)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@MockBean
	private UserService userService;
	
	@Test
	void testExistsByIdFalse() {
		// Given
		Long id = 1L;
		// When
		when(userService.existsById(id)).thenReturn(false);
		// Then
        assertEquals(userService.existsById(id), false);
        verify(userService).existsById(id);
        
	}

	@Test
	void testExistsByIdTrue() {
		// Given
		Long id = 1L;
		// When
		when(userService.existsById(id)).thenReturn(true);
		// Then
        assertEquals(userService.existsById(id), true);
        verify(userService).existsById(id);
	}
	
	@Test
	void testFindById() {
		// Given
		Long id = 1L;
		// When
		when(userService.findById(id)).thenReturn(Optional.empty());
		// Then
        assertEquals(userService.findById(id), Optional.empty());
        verify(userService).findById(1L);
	}

	@Test
	void testExistsByUsername() {
		// Given
		String username = "username";
		// When
		when(userService.existsByEmail(username)).thenReturn(true);
		// Then
        assertEquals(userService.existsByEmail(username), true);
        verify(userService).existsByEmail(username);
	}

	@Test
	void testExistsByUsernameFalse() {
		// Given
		String username = "username";
		// When
		when(userService.existsByEmail(username)).thenReturn(false);
		// Then
        assertEquals(userService.existsByEmail(username), false);
        verify(userService).existsByEmail(username);
	}
	
	@Test
	void testExistsByEmail() {
		// Given
		String email = "email";
		// When
		when(userService.existsByEmail(email)).thenReturn(true);
		// Then
        assertEquals(userService.existsByEmail(email), true);
        verify(userService).existsByEmail(email);
	}
	
	@Test
	void testExistsByEmailFalse() {
		// Given
		String email = "email";
		// When
		when(userService.existsByEmail(email)).thenReturn(false);
		// Then
        assertEquals(userService.existsByEmail(email), false);
        verify(userService).existsByEmail(email);
	}

	@Test
	void testFindAllEmpty() {
		// Given
		List<UserDtoGet> users = new ArrayList<>();
		// When
		when(userService.findAll()).thenReturn(users);
		// Then
        assertEquals(true, userService.findAll().isEmpty());
        verify(userService).findAll();
	}

	@Test
	void testDeleteById() {
		// Given
		// When
		// Then
		userService.deleteById(1L);
		verify(userService).deleteById(1L);
	}

	@Test
	void testDeleteAll() {
		// Given
		// When
		// Then
		userService.deleteAll();
		verify(userService).deleteAll();
	}

}
