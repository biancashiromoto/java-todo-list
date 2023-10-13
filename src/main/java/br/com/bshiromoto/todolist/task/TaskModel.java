package br.com.bshiromoto.todolist.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

  @Column(length = 255)
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

  public void setDueDate(LocalDate dueDate) throws Exception {
    var currDate = LocalDate.now();

    if(currDate.isAfter(dueDate)) {
      throw new Exception("Invalid date option");
    }
  }

  public void setDueTime(String time) throws Exception {
    // objeto que será usado para fazer a conversão de uma string em um LocalTime no formato de 24 horas
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    // converte o 
    LocalTime parsedDueTime = LocalTime.parse(time, formatter);

    LocalDateTime currDateTime = LocalDateTime.now();
    LocalDateTime dueDateTime = LocalDateTime.of(currDateTime.toLocalDate(), parsedDueTime);

    if (LocalDateTime.now().isAfter(dueDateTime)) {
        throw new Exception("Invalid time option");
    }

    this.dueTime = parsedDueTime;
  }
}
