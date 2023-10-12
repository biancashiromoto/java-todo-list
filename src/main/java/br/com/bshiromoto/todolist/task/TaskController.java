package br.com.bshiromoto.todolist.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")

public class TaskController {
  @Autowired
  
  private ITaskRepository taskRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    
    var foundTask = this.taskRepository.findByTitle(taskModel.getTitle());
    
    if(foundTask != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("there is already a task with this title");
    }
    
    var userId = request.getAttribute("userId");
    taskModel.setUserId((UUID) userId);

    var currDate = LocalDate.now();
    
    if(currDate.isAfter(taskModel.getDueDate())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date option");
    }

    LocalDateTime dueDateTime = LocalDateTime.of(LocalDate.now(), taskModel.getDueTime());

    if(LocalDateTime.now().isAfter(dueDateTime)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid time option");
    }

    var createdTask = this.taskRepository.save(taskModel);

    return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
  }

  @GetMapping("/")
  public ResponseEntity findAll() {
    var tasks = this.taskRepository.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(tasks);
  }
}
