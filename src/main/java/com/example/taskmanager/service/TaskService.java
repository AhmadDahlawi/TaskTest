package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepositoryInterface taskRepository;

    @Autowired
    public TaskService(TaskRepositoryInterface taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAllTasks();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findTaskById(id);
    }

    public Task saveTask(Task task) {
        return taskRepository.saveTask(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteTaskById(id);
    }
}
