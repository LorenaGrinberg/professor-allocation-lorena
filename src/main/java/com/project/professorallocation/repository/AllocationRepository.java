package com.project.professorallocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professorallocation.model.Allocation;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {

	List<Allocation> findByProfessorId(Long professorId);

	List<Allocation> findByCourseId(Long courseId);

	// SELECT * FROM allocation WHERE professor_id = @professorId And @course_id=
	// @courseId
	List<Allocation> findByProfessorIdAndCourseId(Long professorId, Long courseId);
}
