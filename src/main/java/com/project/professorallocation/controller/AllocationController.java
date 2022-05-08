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

import com.project.professorallocation.model.Allocation;
import com.project.professorallocation.service.AllocationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {

	private final AllocationService service;

	public AllocationController(AllocationService service) {
		super();
		this.service = service;
	}
	@ApiOperation(value = "Find all allocations")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Allocation>> findAll() {
		List<Allocation> allItems = service.findAll();

		return new ResponseEntity<List<Allocation>>(allItems, HttpStatus.OK);
	}
	@ApiOperation(value = "Find an allocation by id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 404, message = "Allocation not found")
	})
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Allocation> findById(@PathVariable(name = "id") Long id) {
		Allocation allocation = service.findById(id);

		if (allocation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(allocation, HttpStatus.OK);
		}
	}

	@GetMapping(path = "/professor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Allocation>> findByProfessorId(@PathVariable(name = "id") Long id) {
		List<Allocation> allAllocations = service.findByProfessor(id);

		return new ResponseEntity<>(allAllocations, HttpStatus.OK);
	}

	@GetMapping(path = "/course/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Allocation>> findByCourseId(@PathVariable(name = "id") Long id) {
		List<Allocation> allAllocations = service.findByCourse(id);

		return new ResponseEntity<>(allAllocations, HttpStatus.OK);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Allocation> create(@RequestBody Allocation allocation) {
		try {
			Allocation item = service.create(allocation);

			return new ResponseEntity<>(item, HttpStatus.CREATED);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(path = "/{item_id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Allocation> update(@PathVariable(name = "item_id") Long id,
			@RequestBody Allocation allocation) {
		try {
			allocation.setId(id);
			Allocation item = service.create(allocation);
			if (item == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(item, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping(path = "/{item_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void>delete(@PathVariable(name = "item_id")Long id){
	service.deleteById(id);
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
}

//curl -v --request POST --header "Content-Type: application/json" --header "Accept: application/json" --data-raw "{\"dayofweek\": \"SUNDAY\", \"startHour\": \"19:35-0300\", \"endHour\": \"21:35-0300\", \"professorId\": \"7\", \"courseId\": \"1\"}" "http://localhost:8080/allocations"
//curl -v --request PUT --header "Content-Type: application/json" --header "Accept: application/json" --data-raw "{\"dayofweek\": \"WEDNESDAY\",\"startHour\": \"19:18-0300\", \"endHour\": \"21:18-0300\",\"professorId\": \"5\", \"courseId\": \"5\"}" "http://localhost:8080/allocations/9"
//curl -v --request PUT --header "Content-Type: application/json" --header "Accept: application/json" --data-raw "{\"dayofweek\": \"WEDNESDAY\",\"startHour\": \"19:18-0300\", \"endHour\": \"21:18-0300\",\"professorId\": \"1\", \"courseId\": \"3\"}" "http://localhost:8080/allocations/12"