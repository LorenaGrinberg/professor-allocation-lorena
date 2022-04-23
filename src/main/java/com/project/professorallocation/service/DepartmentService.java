package com.project.professorallocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professorallocation.model.Department;
import com.project.professorallocation.repository.DepartmentRepository;

@Service
public class DepartmentService {
private final DepartmentRepository repository;

public DepartmentService(DepartmentRepository repository) {
		super();
		this.repository = repository;
		}
public Department create(Department department) {
	Department insertedDepartment = saveinternal(department);
	return insertedDepartment;
}

private Department saveinternal(Department department) {
	department.getId();
	Department insertedDepartment = repository.save(department);
	return insertedDepartment;
}

public Department update(Department department) {
	Long id = department.getId();
if (id == null || !repository.existsById(department.getId())) {
	return null;
}else {
Department updatedDepartment = repository.save(department);
return updatedDepartment;
}}
public void deleteById(Long id) {
	if(repository.existsById(id)) {
	repository.deleteById(id);
}}
	public Department findById(Long id) {
		return repository.findById(id).orElse(null);
	}
public List<Department> findAll(){
	return repository.findAll();
	
}
	}
