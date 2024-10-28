package com.example.taskmanager.repository;

import com.example.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, TaskRepositoryInterface {

    // Override methods from TaskRepositoryInterface if any additional customization is required
    default List<Task> findAllTasks() {
        return findAll();
    }

    default Optional<Task> findTaskById(Long id) {
        return findById(id);
    }

    default Task saveTask(Task task) {
        return save(task);
    }

    default void deleteTaskById(Long id) {
        deleteById(id);
    }
}
