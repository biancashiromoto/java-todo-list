package br.com.bshiromoto.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data // cria automaticamente os getters e setters
// se quisesse usar só os getters, usaria @Getter, e só os setters, @Setter, por exemplo:
//   public class UserModel {
  // @Getter
  // @Setter
//   private String username; // neste caso, só seria permitido usar getter e setter de username
//   private String name;
//   private String password;
// }

@Entity(name = "table_users") // define um nome para a tabela

public class UserModel {

  @Id // informa ao db que id é a PK da tabela
  @GeneratedValue(generator = "UUID") // faz a geração dos ids de forma automática
  private UUID id;

  @Column(unique = true) // define que a coluna username deve ser um valor único
  private String username;
  private String name;
  private String password;

  @CreationTimestamp // quando o dado for gerado, o db salva automaticamente o timestamp
  private LocalDateTime createdAt;

  public void setUsername(String username) throws Exception {
    if (username.length() < 3) {
      throw new Exception("Username length must be at least 3 characters long");
    }
  }

  public void setName(String name) throws Exception {
    if (name.length() < 3) {
      throw new Exception("Name length must be at least 3 characters long");
    }
  }

  public void setPassword(String password) throws Exception {
    if (password.length() < 6) {
      throw new Exception("Password length must be at least 6 characters long");
    }
  }
}
