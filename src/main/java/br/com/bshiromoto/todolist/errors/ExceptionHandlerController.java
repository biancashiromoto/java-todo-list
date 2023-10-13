package br.com.bshiromoto.todolist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// annotation do Spring para definir classes globais ao tratar exceções - todas as exceções passam por esse controller advice
@ControllerAdvice
public class ExceptionHandlerController {
  // annotation que indica que o método será executado quando ocorrer uma exceção do tipo HttpMessageNotReadableException - lançada quando ocorre algum problema ao ler a requisição HTTP
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
    // retorna a resposta como a mensagem da exceção
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMostSpecificCause().getMessage());
  }
}
