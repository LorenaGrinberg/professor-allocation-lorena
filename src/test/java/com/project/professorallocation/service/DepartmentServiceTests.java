package com.project.professorallocation.service;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.model.Department;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServiceTests {

	@Autowired
	DepartmentService service;
	
	@Test
	public void create() throws ParseException {
		Department department = new Department();
		department.setName("Departamento de Química");
		//department.setName("Departamento de Engenharia");
		
		department = service.create(department);
		}
	@Test
	public void update() throws ParseException {
		Department department = new Department();
		department.setId(2L);
		department.setName("Departamento de Artes");
		
		department = service.update(department);
	}
	
	
}
