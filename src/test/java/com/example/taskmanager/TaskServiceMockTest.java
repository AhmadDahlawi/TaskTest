package com.example.taskmanager;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepositoryInterface;
import com.example.taskmanager.service.TaskService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceMockTest {

    @Mock
    private TaskRepositoryInterface taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTasks_ShouldReturnTasksList() {
        Task task1 = new Task();
        task1.setId(1L);
        Task task2 = new Task();
        task2.setId(2L);

        when(taskRepository.findAllTasks()).thenReturn(Arrays.asList(task1, task2));

        List<Task> tasks = taskService.getAllTasks();
        assertEquals(2, tasks.size());
        verify(taskRepository, times(1)).findAllTasks();
    }

    @Test
    void getTaskById_ShouldReturnTask_WhenTaskExists() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Sample Task");

        when(taskRepository.findTaskById(1L)).thenReturn(Optional.of(task));

        Optional<Task> foundTask = taskService.getTaskById(1L);
        assertTrue(foundTask.isPresent());
        assertEquals("Sample Task", foundTask.get().getTitle());
        verify(taskRepository, times(1)).findTaskById(1L);
    }

    @Test
    void saveTask_ShouldSaveAndReturnTask() {
        Task task = new Task();
        task.setTitle("New Task");
        task.setDueDate(LocalDate.of(2023, 10, 30));

        when(taskRepository.saveTask(task)).thenReturn(task);

        Task savedTask = taskService.saveTask(task);
        assertEquals("New Task", savedTask.getTitle());
        verify(taskRepository, times(1)).saveTask(task);
    }

    @Test
    void deleteTask_ShouldInvokeRepositoryDeleteById() {
        Long taskId = 1L;

        doNothing().when(taskRepository).deleteTaskById(taskId);

        taskService.deleteTask(taskId);
        verify(taskRepository, times(1)).deleteTaskById(taskId);
    }
}
