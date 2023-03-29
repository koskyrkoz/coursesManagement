package com.example.coursesmanagement;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.coursesmanagement.domain.User;
import com.example.coursesmanagement.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	/*
	 * Succefull JUnit test => Change application.properties -> spring.jpa.hibernate.ddl-auto=create
	 */
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setUsername("testing");
		user.setPassword("123456789");
		
		User savedUser = userRepository.save(user);
		
		User existUser = entityManager.find(User.class, savedUser.getId());
	
		assertThat(existUser.getUsername().equals(user.getUsername()));
		assertThat(existUser.getPassword().equals(user.getPassword()));
	}
	
	
	/*
	 * Successfull JUnit test => Change application.properties -> spring.jpa.hibernate.ddl-auto=none
	 */
	@Test
	public void testFindUsernameAndPassword() {
		String username = "testing";
		String password = "123456789";
		
		User user = userRepository.findByUsernameAndPassword(username, password);
		
		assertThat(user).isNotNull();
	}

}
