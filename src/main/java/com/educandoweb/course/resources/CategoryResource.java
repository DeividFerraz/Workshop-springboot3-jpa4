package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired//CategoryResource depende de CategoryServices
	private CategoryService service;
	
	@GetMapping//controlador resting que responde no caminho users
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id){
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Category> insert(
		@RequestBody Category obj){//Para dizer que esse objeto vai chegar certo para o json e ele vai associar a classe user, precisa por esse rquest
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);//essa linha URI e essa que estou agr foi feito para que o codigo retorne 201 na api
	}
	
	@DeleteMapping(value = "/{id}")//passa o id que vai deletar
	public ResponseEntity<Void> delete(@PathVariable Long id){////esse Path é para o ID ser reconhecido como uma variavel da URL
		service.delete(id);
		return ResponseEntity.noContent().build();//aqui retorna o 204
	}
}
