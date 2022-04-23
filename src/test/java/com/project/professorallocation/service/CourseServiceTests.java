package com.project.professorallocation.service;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.model.Course;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTests {

	@Autowired
	CourseService service;
	
	@Test
	public void create() throws ParseException { 
		Course course = new Course();
		course.setName("Cabelo e Maquiagem");
		//course.setName("Direito");
		//course.setName("Enfermagem");
		//course.setName("Filosofia");
		//course.setName("Informática");
		//course.setName("Medicina");
		course = service.create(course); 
	}
	@Test
	public void update() throws ParseException {
		Course course = new Course();
		course.setId(6L);
		course.setName("Engenharia");
		
		course = service.update(course);
	}
}
