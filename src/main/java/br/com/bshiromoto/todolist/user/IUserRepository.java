package br.com.bshiromoto.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


// usa o JpaRepository para usar os métodos padrão de comunicação com o db
public interface IUserRepository extends JpaRepository<UserModel, UUID> {

  // criado pelo próprio Spring
  UserModel findByUsername(String username);
}
