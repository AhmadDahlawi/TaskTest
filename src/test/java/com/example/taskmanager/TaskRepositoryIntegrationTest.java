package com.example.taskmanager;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TaskRepositoryIntegrationTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testSaveAndFindAllTasks_ShouldReturnAllTasks() {
        Task task1 = new Task();
        task1.setTitle("Task 1");
        task1.setDueDate(LocalDate.now());

        Task task2 = new Task();
        task2.setTitle("Task 2");
        task2.setDueDate(LocalDate.now().plusDays(1));

        taskRepository.save(task1);
        taskRepository.save(task2);

        List<Task> tasks = taskRepository.findAll();
        assertEquals(2, tasks.size());
    }

    @Test
    void testFindById_ShouldReturnTask_WhenTaskExists() {
        Task task = new Task();
        task.setTitle("Sample Task");
        task.setDueDate(LocalDate.now());

        task = taskRepository.save(task);
        Optional<Task> foundTask = taskRepository.findById(task.getId());

        assertTrue(foundTask.isPresent());
        assertEquals("Sample Task", foundTask.get().getTitle());
    }

    @Test
    void testDeleteById_ShouldRemoveTask() {
        Task task = new Task();
        task.setTitle("Task to Delete");
        task.setDueDate(LocalDate.now());

        task = taskRepository.save(task);
        Long taskId = task.getId();

        taskRepository.deleteById(taskId);
        Optional<Task> deletedTask = taskRepository.findById(taskId);

        assertFalse(deletedTask.isPresent());
    }
}
