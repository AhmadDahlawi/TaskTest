package com.example.taskmanager.repository;

import com.example.taskmanager.model.Task;
import java.util.List;
import java.util.Optional;

public interface TaskRepositoryInterface {
    List<Task> findAllTasks();
    Optional<Task> findTaskById(Long id);
    Task saveTask(Task task);
    void deleteTaskById(Long id);
}
