// Componente designado para ser a primeira camada de acesso entre a requisição (usuário) e as demais camadas
package br.com.bshiromoto.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // usado para construir APIs - o Spring tem vários outros tipos de controller
@RequestMapping("/users") // especifica que todas as requisições que começam com /home serão manipuladas por métodos dentro desta classe

public class UserController {
  @Autowired // gerencia o ciclo de vida do método IUserRepository
  private IUserRepository userRepository;

  @Autowired
  private UserService userService;

  @PostMapping("")

  // o ResponseEntity permite que tenhamos retornos diferentes na mesma requisição
  // o @RequestBody indica que o que for recebido no corpo da requisição virá na estrutura do UserModel
  public ResponseEntity create(@RequestBody UserModel userModel) {
    try {
      
      // usa o método findByUsername definido na interface
      var foundUser = this.userRepository.findByUsername(userModel.getUsername());

      if(foundUser != null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
      }
      
      String hashedPassword = userService.hashPassword(userModel.getPassword());
      
      userModel.setPassword(hashedPassword);

      // salva os dados do usuário no db
      var createdUser = this.userRepository.save(userModel);

      // retorna o status 201 e o novo usuário
      return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping("")
  public ResponseEntity findAll() {
    var users = this.userRepository.findAll();

    return ResponseEntity.status(HttpStatus.OK).body(users);
  }
}
