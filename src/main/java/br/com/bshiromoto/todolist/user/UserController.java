// Componente designado para ser a primeira camada de acesso entre a requisição (usuário) e as demais camadas
package br.com.bshiromoto.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController // usado para construir APIs - o Spring tem vários outros tipos de controller
@RequestMapping("/users") // especifica que todas as requisições que começam com /home serão manipuladas por métodos dentro desta classe

public class UserController {
  @Autowired // gerencia o ciclo de vida do método IUserRepository
  private IUserRepository userRepository;

  @PostMapping("/")

  // o ResponseEntity permite que tenhamos retornos diferentes na mesma requisição
  // o @RequestBody indica que o que for recebido no corpo da requisição virá na estrutura do UserModel
  public ResponseEntity create(@RequestBody UserModel userModel) {
  
    // usa o método findByUsername definido na interface
    var foundUser = this.userRepository.findByUsername(userModel.getUsername());

    if(foundUser != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
    }
    
    // usa o BCrypt e retorna um objeto configurado com as configurações padrão do bcrypt
    var hashedPassword = BCrypt.withDefaults()
      // gera um hash a partir de uma senha
      // toCharArray()obtém a senha do objeto userModel como um array de caracteres
      .hashToString(12, userModel.getPassword().toCharArray());

    userModel.setPassword(hashedPassword);

    // salva os dados do usuário no db
    var createdUser = this.userRepository.save(userModel);

    // retorna o status 201 e o novo usuário
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
  }
}
