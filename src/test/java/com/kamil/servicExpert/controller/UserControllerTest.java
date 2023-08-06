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

import com.kamil.servicExpert.db.model.User;
import com.kamil.servicExpert.model.User.UserDtoGet;
import com.kamil.servicExpert.model.User.UserDtoGetDetails;
import com.kamil.servicExpert.service.UserService;

public class UserControllerTest {

    private UserService userService;
    private UserController userController;

    @BeforeEach
    public void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    public void testGetAllUsers_WithUsers_ReturnsListOfUsers() {
        // given
        List<UserDtoGet> users = List.of(
            new UserDtoGet("user1", "user1@example.com", new ArrayList<>(), new ArrayList<>()),
            new UserDtoGet("user2", "user1@example.com", new ArrayList<>(), new ArrayList<>())
        );
        // when
        when(userService.findAll()).thenReturn(users);
        // then
        ResponseEntity<CollectionModel<UserDtoGet>> response = userController.getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getContent().size());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().stream().toList().get(0).getName().equals("user1"));
        assertTrue(response.getBody().getContent().stream().toList().get(1).getName().equals("user2"));
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("delete-users"));
    }

    @Test
    public void testGetAllUsers_WithNoUsers_ReturnsNoContent() {
        // given
        // when
        when(userService.findAll()).thenReturn(new ArrayList<>());
        // then
        ResponseEntity<CollectionModel<UserDtoGet>> response = userController.getAllUsers();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetUserById_WithExistingUserId_ReturnsUserDetails() {
        // given
        long userId = 1L;
        UserDtoGetDetails userDetails = new UserDtoGetDetails(
            "John",
            404040404,
            "John",
            "User@user.com",
            new ArrayList<>(),
            new ArrayList<>()
        );
        // when
        when(userService.findById(userId)).thenReturn(Optional.of(userDetails));
        // then
        ResponseEntity<EntityModel<UserDtoGetDetails>> response = userController.getUserById(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().getUsername().equals("John"));
        assertTrue(response.getBody().hasLinks());
        assertNotNull(response.getBody().getLink("self"));
        assertNotNull(response.getBody().getLink("all-users"));
        assertNotNull(response.getBody().getLink("all-notes-by-user-id"));
        assertNotNull(response.getBody().getLink("delete-user"));
        assertNotNull(response.getBody().getLink("delete-all-notes-by-user-id"));
    }

    @Test
    public void testGetUserById_WithNonExistingUserId_ReturnsNotFound() {
        // given
        long userId = 999L;
        // when
        when(userService.findById(userId)).thenReturn(Optional.empty());
        // then
        ResponseEntity<EntityModel<UserDtoGetDetails>> response = userController.getUserById(userId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateUser_WithExistingUserId_ReturnsUpdatedUserDetails() {
        // given
        long userId = 1L;
        User userToUpdate = new User();
        userToUpdate.setUsername("updated_user");
        userToUpdate.setName("Updated");
        userToUpdate.setSurname("User");
        userToUpdate.setUserPhoneNumber(9876543210L);
        UserDtoGetDetails userDetails = new UserDtoGetDetails(
            "updated_user",
            404040404,
            "John",
            "User@user.com",
            new ArrayList<>(),
            new ArrayList<>()
        );
        // when
        when(userService.findById(userId)).thenReturn(Optional.of(userDetails));
        when(userService.updateUser(userId, userToUpdate)).thenReturn(userDetails);
        // then
        ResponseEntity<UserDtoGetDetails> response = userController.updateUser(userId, userToUpdate);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("updated_user", response.getBody().getUsername());
        assertNotNull(response.getBody());
        assertEquals(userDetails, response.getBody());
    }

    @Test
    public void testUpdateUser_WithNonExistingUserId_ReturnsNotFound() {
        // given
        long userId = 999L;
        User userToUpdate = new User();
        userToUpdate.setUsername("updated_user");
        userToUpdate.setName("Updated");
        userToUpdate.setSurname("User");
        userToUpdate.setUserPhoneNumber(9876543210L);
        // when
        when(userService.findById(userId)).thenReturn(Optional.empty());
        // then
        ResponseEntity<UserDtoGetDetails> response = userController.updateUser(userId, userToUpdate);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteUser_WithExistingUserId_ReturnsNoContent() {
        // given
        long userId = 1L;
        // when
        ResponseEntity<HttpStatus> response = userController.deleteUser(userId);
        // then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService, times(1)).deleteById(userId);
    }

    @Test
    public void testDeleteAllUsers_AllUsersDeleted_ReturnsNoContent() {
        // given
        // when
        // then
        ResponseEntity<HttpStatus> response = userController.deleteAllUsers();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService, times(1)).deleteAll();
    }

}
