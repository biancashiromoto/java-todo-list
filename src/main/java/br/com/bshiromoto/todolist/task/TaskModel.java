package br.com.bshiromoto.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "table_tasks")
public class TaskModel {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  private String description;

  @Column(unique = true, length = 50)
  private String title;

  private LocalDateTime dueDate;
  private String priority;
  private String status;
  private UUID userId;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
