
package com.example.taskmanager;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class SecurityTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testSqlInjection() {
        String maliciousTitle = "'; DROP TABLE tasks; --";
        Task maliciousTask = new Task();
        maliciousTask.setTitle(maliciousTitle);

        assertThrows(DataAccessException.class, () -> taskRepository.save(maliciousTask));
    }
}
