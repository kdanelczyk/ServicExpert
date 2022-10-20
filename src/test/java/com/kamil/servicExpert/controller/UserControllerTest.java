package com.kamil.servicExpert.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.kamil.servicExpert.db.mapper.UserMapper;
import com.kamil.servicExpert.db.model.User;
import com.kamil.servicExpert.service.UserService;

@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@MockBean
	private UserService userService;
	
	@MockBean
	private UserMapper userMapper;

	@Autowired
	private MockMvc mockMvc;
	
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    
	@Test
	void testGetAllUsers() throws Exception {
		// given
		List<User> users = List.of();
		// when
		when(userService.findAll()).thenReturn(users);
		// then
		mockMvc.perform(get("/api/users"))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testGetUserById() throws Exception {

	}

	@Test
	void testUpdateUser() {

	}

	@Test
	void testDeleteUser() throws Exception {
		// given
		long id = 1L;
		// when
		doNothing().when(userService).deleteById(id);
		// then
		mockMvc.perform(delete("/api/users/{id}", id))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	void testDeleteAllTypes() throws Exception {
		// when
		doNothing().when(userService).deleteAll();
		// then
		mockMvc.perform(delete("/api/users"))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

}
