package br.com.bshiromoto.todolist.user;

import lombok.Data;

@Data // cria automaticamente os getters e setters
// se quisesse usar só os getters, usaria @Getter, e só os setters, @Setter

public class UserModel {
  private String username;
  private String name;
  private String password;
}
