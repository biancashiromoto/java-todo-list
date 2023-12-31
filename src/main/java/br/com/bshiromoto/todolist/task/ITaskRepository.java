package br.com.bshiromoto.todolist.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
  TaskModel findByTitle(String title);

  List<TaskModel> findAll();

  Optional<TaskModel> findById(UUID id);
}
