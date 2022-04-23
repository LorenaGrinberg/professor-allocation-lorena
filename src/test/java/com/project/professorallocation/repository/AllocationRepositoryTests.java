package com.project.professorallocation.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.model.Allocation;
import com.project.professorallocation.model.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTests {

	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

	@Autowired
	private AllocationRepository repository;

	@Test
	public void create() throws ParseException {
		Allocation allocation = new Allocation();
		allocation.setDayofweek(DayOfWeek.FRIDAY);
		allocation.setStartHour(sdf.parse("19:10-0300"));
		allocation.setEndHour(sdf.parse("21:10-0300"));
		allocation.setProfessorId(5L);
		allocation.setCourseId(5L);

		allocation = repository.save(allocation);
		System.out.println(allocation);
	}

	@Test
	public void findAll() {
		List<Allocation> allAllocations = repository.findAll();
		allAllocations.forEach(System.out::println);
	}

	@Test
	public void deleteAll() {
	repository.deleteAllInBatch();
}
}
