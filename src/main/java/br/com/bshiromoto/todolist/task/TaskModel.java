package br.com.bshiromoto.todolist.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

  @Column(unique = true, length = 255)
  private String description;

  @Column(unique = true, length = 50, nullable = false)
  private String title;

  @Column(nullable = false)
  private LocalDate dueDate;

  private LocalTime dueTime;

  @Column(nullable = false)
  private String priority;

  @Column(nullable = false)
  private String status;

  @Column(nullable = false)
  private UUID userId;

  @CreationTimestamp
  private LocalDateTime createdAt;

  public void setTitle(String title) throws Exception {
    if (title.length() > 50) {
      throw new Exception("Title length must be up to 50 characters");
    }
    this.title = title;
  }
}
