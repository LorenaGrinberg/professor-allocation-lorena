package com.project.professorallocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professorallocation.model.Allocation;
import com.project.professorallocation.repository.AllocationRepository;

@Service
public class AllocationService {
	private final AllocationRepository repository;

	public AllocationService(AllocationRepository repository) {
		super();
		this.repository = repository;
	}

	public Allocation findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<Allocation> findAll() {
		return repository.findAll();
	}

	public List<Allocation> findByProfessor(Long id) {
		return repository.findByProfessorId(id);
	}

	public List<Allocation> findByCourse(Long id) {
		return repository.findByCourseId(id);
	}

	public void deleteById(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		}
	}

	public Allocation create(Allocation allocation) {
		allocation.setId(null);
		return saveinternal(allocation);
	}

	public Allocation update(Allocation allocation) {
		Long id = allocation.getId();
		if (id == null || !repository.existsById(id)) {
			return saveinternal(allocation);
		} else {
			return null;
		}
	}

	private Allocation saveinternal(Allocation allocation) {
		if (hasCollision(allocation)) {
			throw new RuntimeException("There is a time collision for this allocation");
		}
		allocation = repository.save(allocation);
		return allocation;
	}

	private boolean hasCollision(Allocation newAllocation) {
		List<Allocation> currentAllocations = repository.findByProfessorId(newAllocation.getProfessorId());
		boolean collisionFound = false;

		for (Allocation item : currentAllocations) {
			if (hasCollision(item, newAllocation)) {
				collisionFound = true;
				break;
			}
		}
		return collisionFound;
	}

	private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
		boolean collision = !currentAllocation.getId().equals(newAllocation.getId())
				&& currentAllocation.getProfessorId().equals(newAllocation.getProfessorId())
				&& currentAllocation.getDayofweek().equals(newAllocation.getDayofweek())
				&& currentAllocation.getStartHour().compareTo(newAllocation.getEndHour()) < 0
				&& newAllocation.getStartHour().compareTo(currentAllocation.getEndHour()) < 0;
		return collision;
	}
}
