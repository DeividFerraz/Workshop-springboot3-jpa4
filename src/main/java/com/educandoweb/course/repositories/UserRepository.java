package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
/*
 * Essa interface serve para salvar a classe no banco de dados
 * 
 * O UserRepositoy vai ser o responsavel por fazer as 
 * operações com a entidade User
 * 
 * Ai para acessa a entidade User e fazer as operações, 
 * passamo A entidade User que é a que vamos usar, e o tipo da 
 * chave, que no caso é Long
 * 
 * E isso esta como interface, por que o JPARepository é interface
 * 
 * Só isso é capaz de instanciar um objeto repository que vai ter 
 * varias operações par trablhar com ususario
 * 
 * essa é a "classe" que salva os dados
 */
}
