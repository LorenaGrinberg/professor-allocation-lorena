package com.project.professorallocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.model.Course;
import com.project.professorallocation.model.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class CourseRepositoryTests {

	@Autowired
	private CourseRepository repository;

	@Test
	public void findAll() {
		List<Course> items = repository.findAll();
		System.out.println("Qtde de cursos retornados: " + items.size());

		for (Course item : items) {
			System.out.println(item);
		}
	}

	@Test
	public void create() {
		Course courseBeingCreated = new Course();
		courseBeingCreated.setName("Desenvolvimento de Software");

		courseBeingCreated = repository.save(courseBeingCreated);
		System.out.println(courseBeingCreated);
	}
	@Test
	public void update() {
		Course courseBeingCreated = new Course();
		courseBeingCreated.setId(3L);
		courseBeingCreated.setName("Informática");

		courseBeingCreated = repository.save(courseBeingCreated);
		System.out.println(courseBeingCreated);
}
}
