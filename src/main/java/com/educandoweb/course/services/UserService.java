package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DataBaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service//faz a mesma coisa do component
//@Component//injeta a classe como componente do inpring e pode atuar com autowierd
public class UserService {/*A camada de serviço fica entre 
o resource e repository, o resource depende dela, e ela 
depende de repository. e por que fazemos essa camada em vez de
fazer o controladorRest "UserResource" acessar direto o repository???
não é proibido mas é uma boa pratica implementar uma camada
de serviço para impmentar as regras de negocio, implementar
algumas informações e tratar exceções. Por exemplo, ao salvar o pedido eu posso
ver no esoque se tem item, ou ver se nao tem, vou salvar 
primeiro os itens e dps o pedido, então s~çao essas operações
que a classe service trata.*/

	@Autowired//injeção de dependencia
	private UserRepository repository;
	
	public List<User> findAll(){//operação para buscar todos os usuarios
		return repository.findAll();
	}
	
	public User findById(Long id) {//buscar usuario por Id
		Optional<User> obj = repository.findById(id);//findBy Id vai no banco de dados e traz o objeto para nos
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));//Expressão lambda no meio
	}
	
	public User insert(User obj) {//esssa operação retorna o usuariio salvo
		return repository.save(obj);//operação para inserir no banco de dados um objeto do tipo user
	}
	
	public void delete(Long id) {//Quando for add um endPoint
		//precisa mexer na classe service e resource
		try {
			repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User obj) {
		try {
			User entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	//metodo para atualizar
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
