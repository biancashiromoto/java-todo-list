package br.com.bshiromoto.todolist.user;

import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class UserService {
  public String hashPassword(String password) {
    // usa o BCrypt e retorna um objeto configurado com as configurações padrão do bcrypt
    return BCrypt.withDefaults()
      // gera um hash a partir de uma senha
      // toCharArray()obtém a senha do objeto userModel como um array de caracteres
      .hashToString(12, password.toCharArray());
  }

  public boolean validatePassword(String password, String hashedPassword) {
    BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
    return result.verified;
  }
}
