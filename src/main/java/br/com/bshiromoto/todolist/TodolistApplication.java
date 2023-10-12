package br.com.bshiromoto.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Annotations - interface que define a classe inicial do projeto
@SpringBootApplication
public class TodolistApplication {

	// O método main é o método principal onde se executa algo na aplicação
	// SpringApplication.run - inicializa um container que executa a aplicação
	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}
