// Componente designado para ser a primeira camada de acesso entre a requisição (usuário) e as demais camadas
package br.com.bshiromoto.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // usado para construir APIs - o Spring tem vários outros tipos de controller
@RequestMapping("/users") // especifica que todas as requisições que começam com /home serão manipuladas por métodos dentro desta classe

public class UserController {
  @PostMapping("/")

  // o @RequestBody indica que o que for recebido no corpo da requisição virá na estrutura do UserModel
  public void create(@RequestBody UserModel userModel) {
    System.out.println(userModel.name);
  }
}
