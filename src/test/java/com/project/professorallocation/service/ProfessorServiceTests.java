package com.project.professorallocation.service;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.model.Professor;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorServiceTests {

	@Autowired
	ProfessorService service;

	@Test
	public void create() throws ParseException {
		Professor professor = new Professor();
		professor.setName("Mario");
		professor.setCpf("12234455566");
		professor.setDepartmentId(6L);

		professor = service.create(professor);
	}

	@Test
	public void update() throws ParseException {
		Professor professor = new Professor();
		professor.setId(6L);
		professor.setName("Luana");
		professor.setCpf("52469871152");
		professor.setDepartmentId(6L);

		professor = service.update(professor);
	}

}
