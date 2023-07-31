package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.repositories.OrderRepository;

@Service
//@Component//injeta a classe como componente do inpring e pode atuar com autowierd
public class OrderService {

	@Autowired//injeção de dependencia
	private OrderRepository repository;
	
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	public Order findById(Long id) {//buscar usuario por Id
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
}
