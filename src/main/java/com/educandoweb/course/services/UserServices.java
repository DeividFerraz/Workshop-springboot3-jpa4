package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

@Service
//@Component//injeta a classe como componente do inpring e pode atuar com autowierd
public class UserServices {

	@Autowired//injeção de dependencia
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {//buscar usuario por Id
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
}
