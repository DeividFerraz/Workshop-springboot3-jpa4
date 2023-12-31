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

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired//ProductResource depende de ProductServices
	private ProductService service;
	
	@GetMapping//controlador resting que responde no caminho users
	public ResponseEntity<List<Product>> findAll(){
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Product> insert(
		@RequestBody Product obj){//Para dizer que esse objeto vai chegar certo para o json e ele vai associar a classe user, precisa por esse rquest
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
