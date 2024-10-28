package com.example.taskmanager;

import org.junit.jupiter.api.Test;
import com.example.taskmanager.model.Task;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class TaskModelUnitTest {

    @Test
    void testTaskGettersAndSetters() {
        Task task = new Task();
        
        task.setId(1L);
        task.setTitle("Test Task");
        task.setDueDate(LocalDate.of(2023, 10, 30));
        task.setDone(true);

        assertEquals(1L, task.getId());
        assertEquals("Test Task", task.getTitle());
        assertEquals(LocalDate.of(2023, 10, 30), task.getDueDate());
        assertTrue(task.isDone());
    }
}
