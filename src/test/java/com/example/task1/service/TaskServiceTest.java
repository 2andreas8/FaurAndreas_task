package com.example.task1.service;

import com.example.task1.domain.Task;
import com.example.task1.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository; // Simulăm repository-ul

    @InjectMocks
    private TaskService taskService; // Injectăm mock-ul în serviciu

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inițializăm Mockito
    }

    @Test
    void testUpdateEffort_TaskExists() {
        // Arrange (Pregătire)
        Long taskId = 1L;
        Task mockTask = new Task();
        mockTask.setId(taskId);
        mockTask.setRemainingEffort(5);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(mockTask));
        when(taskRepository.save(any(Task.class))).thenReturn(mockTask);

        // Act (Executare)
        Task updatedTask = taskService.updateEffort(taskId, 10);

        // Assert (Verificare)
        assertNotNull(updatedTask);
        assertEquals(10, updatedTask.getRemainingEffort());
        verify(taskRepository).findById(taskId);
        verify(taskRepository).save(mockTask);
    }

    @Test
    void testUpdateEffort_TaskNotFound() {
        Long taskId = 1L;

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Verificăm dacă metoda aruncă excepția așteptată
        Exception exception = assertThrows(IllegalArgumentException.class, () -> taskService.updateEffort(taskId, 10));
        assertEquals("Task not found.", exception.getMessage());
    }

    @Test
    void testGetAllTasks() {
        // Arrange
        List<Task> mockTasks = Arrays.asList(new Task(), new Task());
        when(taskRepository.findAll()).thenReturn(mockTasks);

        // Act
        List<Task> tasks = taskService.getAllTasks();

        // Assert
        assertEquals(2, tasks.size());
        verify(taskRepository).findAll();
    }

    @Test
    void testGetTaskById_Found() {
        Long taskId = 1L;
        Task mockTask = new Task();
        mockTask.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(mockTask));

        Task task = taskService.getTaskById(taskId);

        assertNotNull(task);
        assertEquals(taskId, task.getId());
        verify(taskRepository).findById(taskId);
    }

    @Test
    void testGetTaskById_NotFound() {
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        Task task = taskService.getTaskById(taskId);

        assertNull(task);
        verify(taskRepository).findById(taskId);
    }

    @Test
    void testGetTaskByOwner() {
        Long ownerId = 100L;
        List<Task> mockTasks = Arrays.asList(new Task(), new Task());
        when(taskRepository.findByOwnerId(ownerId)).thenReturn(mockTasks);

        List<Task> tasks = taskService.getTaskByOwner(ownerId);

        assertEquals(2, tasks.size());
        verify(taskRepository).findByOwnerId(ownerId);
    }

    @Test
    void testCreateTask() {
        Task newTask = new Task();
        newTask.setId(1L);

        when(taskRepository.save(newTask)).thenReturn(newTask);

        Task createdTask = taskService.createTask(newTask);

        assertNotNull(createdTask);
        assertEquals(1L, createdTask.getId());
        verify(taskRepository).save(newTask);
    }

    @Test
    void testDeleteTask() {
        Long taskId = 1L;
        doNothing().when(taskRepository).deleteById(taskId);

        taskService.deleteTask(taskId);

        verify(taskRepository).deleteById(taskId);
    }
}
