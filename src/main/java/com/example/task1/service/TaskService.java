package com.example.task1.service;

import com.example.task1.domain.Task;
import com.example.task1.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task updateEffort(Long taskId, int newEffort) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()-> new IllegalArgumentException("Task not found."));

        task.setRemainingEffort(newEffort);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    public List<Task> getTaskByOwner(Long ownerId) {
        return taskRepository.findByOwnerId(ownerId);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
