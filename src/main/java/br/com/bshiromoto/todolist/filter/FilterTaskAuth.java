package br.com.bshiromoto.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // para que o Spring fique responsável pelo gerenciamento dessa classe, controlando o ciclo de vida e a injeção de dependências

// a implementação de Filter significa que a classe vai atuar como um filtro em um ambiente de aplicação web Java EE
public class FilterTaskAuth extends OncePerRequestFilter {
  
  // o método doFilterInternal é chamado quando uma requisição é feita e processa a requisição antes de passá-la adiante
  // funciona como se fosse um middleware, e o chain seria o next
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    throws ServletException, IOException {
      var authorization = request.getHeader("Authorization");

      // substring - método que recebe como parâmetro um ou dois ints - primeiro é o index de início e o segundo é o final. Pode-se usar string também
      // trim - remove todos os espaços em branco
      var encoded = authorization.substring("Basic".length()).trim();

      // cria uma variável que consiste em um array de bytes - contém a senha decodificada
      byte[] decoded = Base64.getDecoder().decode(encoded);
      var auth = new String(decoded);
      
      String[] credentials = auth.split(":");
      String username = credentials[0];
      String password = credentials[1];
    }
}
