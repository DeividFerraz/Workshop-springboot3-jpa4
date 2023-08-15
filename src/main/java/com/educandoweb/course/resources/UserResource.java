package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController//anotation usada para dizer que essa clase é um recurso web, que é implemnetada por um controlador rest
@RequestMapping(value = "/users")//Anotation usada para dar um nome para o recurso
public class UserResource {//Esses são os recursoso da aplicação user
	
	@Autowired//UserResource depende de UserServices
	private UserService service;/*para essa injeção 
	funcionar, minha classe service, tem que esta 
	injetada como componente do spring*/
	
	@GetMapping//controlador resting que responde no caminho users
	public ResponseEntity<List<User>> findAll(){//findAll é um metodo q retorna os usuarios 
		//ResponseEntity é um tipo especifico do Spring para retornar respostas de requisições web
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);//chamando usuarios
		//ResponseEntity.Ok()retorna a resposta dela msm, e o .body(list) retorna o corpo em forma de json do que esta dentro da list
	}
	
	@GetMapping(value = "/{id}")//Serve para retornar um usuario especifico
	public ResponseEntity<User> findById(@PathVariable Long id){/*
 	Agr é Só USer, pois eu quero Só um ususario 
 	@PathVariable anotation usada para opring aceitar o 
 	ID e considerar o parametro que vai chegar na URL*/
		User obj = service.findById(id);/*esse é o ID que chegou na requisição
		pega o id
		que buscou no service, e retorna ele mas no corpo da requisição em json*/
		return ResponseEntity.ok().body(obj);//nosso endPoint
	}
	
	@PostMapping
	public ResponseEntity<User> insert(
		@RequestBody User obj){//Para dizer que esse objeto vai chegar certo para o json e ele vai associar a classe user, precisa por esse rquest
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		//O UrI foi importado para retornar o codigo 201, q siginifica q eu criei um novo recurso
		return ResponseEntity.created(uri).body(obj);//essa linha URI e essa que estou agr foi feito para que o codigo retorne 201 na api
	}
	
	//metodo Http
	@DeleteMapping(value = "/{id}")//passa o id que vai deletar
	//coloca o "Void" por 	que  resposta dess requisição n retorna nenhum corpo
	public ResponseEntity<Void> delete(@PathVariable Long id){////esse Path é para o ID ser reconhecido como uma variavel da URL
		service.delete(id);
		return ResponseEntity.noContent().build();//o noContent retorna o 204
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){/*
	estou dizendo que no path vai ter o Id, e no body vai ter o json  atualizado daquele usuario*/
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
