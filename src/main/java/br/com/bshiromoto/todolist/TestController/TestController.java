// Componente designado para ser a primeira camada de acesso entre a requisição (usuário) e as demais camadas
package br.com.bshiromoto.todolist.TestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // usado para construir APIs - o Spring tem vários outros tipos de controller
@RequestMapping("/home") // especifica que todas as requisições que começam com /home serão manipuladas por métodos dentro desta classe

public class TestController {
  @GetMapping("") // mapeia um método para responder às requisições HTTP do tipo GET
  
  // método printMessage que retorna uma string
  public String printMessage() {
    return "hello world";
  }
}