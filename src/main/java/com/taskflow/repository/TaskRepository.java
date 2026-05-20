package com.taskflow.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.taskflow.model.Task;
import com.taskflow.model.User;

public interface TaskRepository extends JpaRepository<Task, Long> {
	Page<Task> findByOwner(User owner, Pageable pageable);
    Page<Task> findByOwnerAndTitleContainingIgnoreCase(User owner, String keyword, Pageable pageable);
    Optional<Task> findByIdAndOwner(Long id, User owner);
}
