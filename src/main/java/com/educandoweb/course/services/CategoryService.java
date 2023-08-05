package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.repositories.CategoryRepository;

@Service
//@Component//injeta a classe como componente do inpring e pode atuar com autowierd
public class CategoryService {

	@Autowired//injeção de dependencia
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category findById(Long id) {//buscar usuario por Id
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}
}
