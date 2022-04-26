package com.project.professorallocation.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.model.Allocation;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTests {

	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");
	
	@Autowired
	AllocationService service;
	
	@Test
	public void create() throws ParseException { 
		Allocation allocation = new Allocation();
		allocation.setDayofweek(DayOfWeek.SATURDAY);
		//allocation.setDayofweek(DayOfWeek.MONDAY);//allocation.setProfessorId(4L);//allocation.setCourseId(2L);
		//allocation.setDayofweek(DayOfWeek.FRIDAY);//allocation.setProfessorId(5L);//allocation.setCourseId(5L);
		//allocation.setDayofweek(DayOfWeek.WEDNESDAY);//allocation.setProfessorId(1L);//allocation.setCourseId(1L);
		//allocation.setDayofweek(DayOfWeek.THURSDAY);//allocation.setProfessorId(3L);//allocation.setCourseId(3L);
		//allocation.setDayofweek(DayOfWeek.TUESDAY);//allocation.setProfessorId(2L);//allocation.setCourseId(4L);
		allocation.setProfessorId(6L);
		allocation.setCourseId(6L);
		allocation.setStartHour(sdf.parse("19:25-0300"));
		allocation.setEndHour(sdf.parse("21:25-0300"));
		
		allocation = service.create(allocation); 
	}
	//update
	@Test
	public void update() throws ParseException {
		Allocation allocation = new Allocation();
		allocation.setId(9L);
		allocation.setDayofweek(DayOfWeek.SUNDAY);
		allocation.setProfessorId(6L);
		allocation.setCourseId(6L);
		allocation.setStartHour(sdf.parse("19:23-0300"));
		allocation.setEndHour(sdf.parse("21:23-0300"));
		
		allocation = service.update(allocation);
		
		System.out.println(allocation);
		
	}
}
