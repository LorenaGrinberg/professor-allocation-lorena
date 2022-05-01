package com.project.professorallocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professorallocation.model.Course;
import com.project.professorallocation.service.CourseService;

@RestController
@RequestMapping(path = "/courses")
public class CourseController {

	private final CourseService service;

	public CourseController(CourseService service) {
		super();
		this.service = service;
	}
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Course>> findAll() {
		List<Course> allCourses = service.findAll();

		return new ResponseEntity<>(allCourses, HttpStatus.OK);
	}

	@GetMapping(path = "/{cour_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Course> findById(@PathVariable(name = "cour_id") Long id) {
		Course item = service.findById(id);
		if (item == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(item, HttpStatus.OK);
		}
	}
		@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity<Course> create(@RequestBody Course cour) {
			Course item = service.create(cour);

			return new ResponseEntity<>(item, HttpStatus.CREATED);
	}
		@PutMapping(path = "/{cour_id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		@ResponseStatus(HttpStatus.OK)
		public ResponseEntity<Course> update(@PathVariable(name = "cour_id") Long id, @RequestBody Course cour) {
			cour.setId(id);
			Course item = service.update(cour);

			if (item == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(item, HttpStatus.OK);
			}
	}
		@DeleteMapping(path = "/{cour_id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public ResponseEntity<Void> delete(@PathVariable(name = "cour_id") Long id) {
			service.deleteById(id);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

}
//curl -v --request POST --header "Content-Type: application/json" --header "Accept: application/json" --data-raw "{\"name\": \"Física\"}" "http://localhost:8080/courses"
//curl -v --request PUT --header "Content-Type: application/json" --header "Accept: application/json" --data-raw "{\"name\": \"Farmácia\"}" "http://localhost:8080/courses/5"