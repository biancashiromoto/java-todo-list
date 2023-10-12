// Componente designado para ser a primeira camada de acesso entre a requisição (usuário) e as demais camadas

package br.com.bshiromoto.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // usado para construir APIs - o Spring tem vários outros tipos de controller
@RequestMapping("/home") // estrutura a rota

public class Controller {
  @GetMapping("")
  public String printMessage() {
    return "hello world";
  }
}