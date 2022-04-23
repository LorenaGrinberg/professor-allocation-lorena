package com.project.professorallocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professorallocation.model.Course;
import com.project.professorallocation.repository.CourseRepository;

@Service
public class CourseService {
	private final CourseRepository repository;

	public CourseService(CourseRepository repository) {

		super();
		this.repository = repository;
	}

	public Course create(Course course) {
		Course insertedCourse = saveinternal(course);
		return insertedCourse;
	}

	private Course saveinternal(Course course) {
		course.setId(null);
		Course insertedCourse = repository.save(course);
		return insertedCourse;
	}

	public Course update(Course course) {
		Long id = course.getId();
	if (id == null || !repository.existsById(course.getId())) {
		return null;
	}else {
	Course updatedCourse = repository.save(course);
	return updatedCourse;
}}
	public void deleteById(Long id) {
		if(repository.existsById(id)) {
		repository.deleteById(id);
	}}
		public Course findById(Long id) {
			return repository.findById(id).orElse(null);
		}
	public List<Course> findAll(){
		return repository.findAll();
		
	}
}