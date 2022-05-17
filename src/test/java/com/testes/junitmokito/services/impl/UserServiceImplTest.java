package com.testes.junitmokito.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.testes.junitmokito.domain.User;
import com.testes.junitmokito.dto.UserDTO;
import com.testes.junitmokito.repositories.UserRepository;
import com.testes.junitmokito.services.exception.ObjectNotFoundException;

@SpringBootTest
public class UserServiceImplTest {

	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
	private static final Integer ID = 1;
	private static final String NAME = "Mokito 1";
	private static final String EMAIL = "mokito1@email.com";
	private static final String PASSWORD = "123";

	@InjectMocks
	private UserServiceImpl service;
	
	@Mock
	private UserRepository repository;
	
	@Mock
	private ModelMapper mapper;
	
	private User user;
	
	private UserDTO userDTO;
	
	private Optional<User> optionalUser;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}
	
	@Test
	void findById() {
		when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
		
		User response = service.findById(ID);
		
		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
	}

	@Test
	void findByIdObjectNotFoundException() {
		when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
		
		try {
			service.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals(OBJETO_NAO_ENCONTRADO, e.getMessage());
		}

	}
	
	@Test
	void findAll() {
		
	}
	
	@Test
	void create() {
		
	}
	
	@Test
	void update() {
		
	}
	
	@Test
	void delete() {
		
	}
	
	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD); 
		userDTO = new UserDTO(1, NAME, EMAIL, PASSWORD); 
		optionalUser = Optional.of(new User(1, NAME, EMAIL, PASSWORD));
	}
}
