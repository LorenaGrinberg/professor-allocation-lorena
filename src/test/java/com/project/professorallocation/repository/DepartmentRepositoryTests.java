package com.project.professorallocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.model.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTests {

	@Autowired
	private DepartmentRepository repository;

	@Test
	public void findAll() {
		List<Department> items = repository.findAll();
		System.out.println("Qtde elementos retornados: " + items.size());

		for (Department item : items) {
			System.out.println(item);
		}

	}

	@Test
	public void create() {
		Department departmentBeingCreated = new Department();
		departmentBeingCreated.setName("Departamento de Medicina");

		departmentBeingCreated = repository.save(departmentBeingCreated);
		System.out.println(departmentBeingCreated);

	}

	@Test
	public void update() {
		Department departmentBeingCreated = new Department();
		departmentBeingCreated.setId(3L);
		departmentBeingCreated.setName("Departamento de Enfermagem");

		departmentBeingCreated = repository.save(departmentBeingCreated);
		System.out.println(departmentBeingCreated);

	}

	@Test
	public void findSpecificDepartment() {
		Department dept = repository.findById(3L).orElse(null);
		System.out.println(dept);
	}
}
