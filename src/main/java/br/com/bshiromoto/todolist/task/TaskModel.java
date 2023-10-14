package br.com.bshiromoto.todolist.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
// import java.time.LocalTime;
// import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import br.com.bshiromoto.todolist.task.types.Priority;
import br.com.bshiromoto.todolist.task.types.Status;
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

  // private LocalTime dueTime;

  @Column(nullable = false)
  private Priority priority;

  @Column(nullable = false)
  private Status status;

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
    LocalDate currDate = LocalDate.now();

    if(currDate.isAfter(dueDate)) {
      throw new Exception("Invalid date option");
    }
    this.dueDate = dueDate;
  }

  // public void setDueTime(String time) throws Exception {
  //   // objeto que será usado para fazer a conversão de uma string em um LocalTime no formato de 24 horas
  //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
  //   // converte o time em um objeto LocalTime
  //   LocalTime parsedDueTime = LocalTime.parse(time, formatter);

  //   var currDateTime = LocalDateTime.now();
  //   var currTime = LocalTime.now();
  //   LocalTime dTime = LocalTime.parse(time, formatter);
    
  //   // cria um novo objeto que usa a data atual e o parsedDueTime para combinar a data e o tempo
  //   LocalDateTime dueDateTime = LocalDateTime.of(currDateTime.toLocalDate(), parsedDueTime);

  //   System.out.println("dTime " + dTime);
  //   System.out.println("PARSEDDUETIME " + parsedDueTime);
  //   System.out.println("dueDateTime " + dueDateTime);
  //   System.out.println("currTime " + currTime);

  //   if (LocalDateTime.now().isAfter(dueDateTime)) {
  //       throw new Exception("Invalid time option");
  //   }

  //   this.dueTime = parsedDueTime;
  // }

  public void setPriority(Priority priority) {
    this.priority = priority;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
