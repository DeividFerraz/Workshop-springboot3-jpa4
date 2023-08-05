package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;

@Service
//@Component//injeta a classe como componente do inpring e pode atuar com autowierd
public class ProductService {

	@Autowired//injeção de dependencia
	private ProductRepository repository;
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public Product findById(Long id) {//buscar usuario por Id
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
}
