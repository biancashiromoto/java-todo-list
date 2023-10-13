# faz o build com base no ubuntu
FROM ubuntu:latest as build

# instala o jdk 17 dando yes em todas as opções
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

# copia o conteúdo para o diretório do render
COPY . .

# instala o maven
RUN apt-get install maven -y

# constrói e empacota o projeto java 
RUN mvn clean install

# imagem base para rodar a aplicação
FROM openjdk:17-jdk-slim

# expõe a porta 8080
EXPOSE 8080

# copia o arquivo JAR do ambiente de construção para o diretório atual
COPY --from=build target/todolist-0.0.1-SNAPSHOT.jar app.jar

# roda o projeto java 
ENTRYPOINT [ "java", "-jar", "app.jar" ]