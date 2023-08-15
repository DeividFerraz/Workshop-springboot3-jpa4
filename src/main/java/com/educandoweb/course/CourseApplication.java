package com.educandoweb.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}
/*
 O controlador rest depende do Service, e o 
 service depende do repositori
 
 Não é uma regra ir ir do controlador 
 rest"Resource" para service, mas é uma boa 
 pratica para dizer o que poderia ou nao ter no 
 banco é uma camada intermediaria para 
 implementar as regras de negocio*/
}
