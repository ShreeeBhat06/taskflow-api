package com.taskflow.service;

import com.taskflow.dto.request.TaskRequest;
import com.taskflow.dto.response.TaskResponse;
import com.taskflow.exception.TaskNotFoundException;
import com.taskflow.model.Task;
import com.taskflow.model.User;
import com.taskflow.repository.TaskRepository;
import com.taskflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getStatus(),
                task.getPriority(),
                task.getOwner().getUsername()
        );
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Page<TaskResponse> getTasks(String username, int page, int size,
                                        String sortBy, String sortDir, String keyword) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        User user = getUser(username);

        Page<Task> result = (keyword != null && !keyword.isEmpty())
                ? taskRepository.findByOwnerAndTitleContainingIgnoreCase(user, keyword, pageable)
                : taskRepository.findByOwner(user, pageable);

        return result.map(this::toResponse);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public TaskResponse getTaskById(Long id, String username) {
        Task task = taskRepository.findByIdAndOwner(id, getUser(username))
                .orElseThrow(() -> new TaskNotFoundException(id));
        return toResponse(task);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public TaskResponse createTask(TaskRequest request, String username) {
        Task task = Task.builder()
                .title(request.getTitle())
                .status(request.getStatus())
                .priority(request.getPriority())
                .owner(getUser(username))
                .build();
        return toResponse(taskRepository.save(task));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public TaskResponse updateTask(Long id, TaskRequest request, String username) {
        Task task = taskRepository.findByIdAndOwner(id, getUser(username))
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setTitle(request.getTitle());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        return toResponse(taskRepository.save(task));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTask(Long id, String username) {
        Task task = taskRepository.findByIdAndOwner(id, getUser(username))
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(task);
    }
}