package com.tiago.bookstore.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tiago.bookstore.assembler.LivroAssembler;
import com.tiago.bookstore.domain.Livro;
import com.tiago.bookstore.dto.LivroDto;
import com.tiago.bookstore.services.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource  {
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
	private LivroAssembler livroAssembler;
	

	@PostMapping
	public ResponseEntity<Livro> create(@Valid @RequestParam (value="categoria", defaultValue = "0")
	/*tem que informar na URI qual o ID da categoria*/
	Integer id_cat,	@RequestBody Livro obj){
		Livro newObj = livroService.create(id_cat, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}")
				.buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LivroDto> findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(livroAssembler.toLivroDto
				(livroService.findById(id)));
	}
	
	 @GetMapping
		public ResponseEntity<CollectionModel<LivroDto>> findAll(){
			return ResponseEntity.ok().body(livroService.findAll());
			
		}
	 @PutMapping(value = "/{id}")
	 public ResponseEntity<Livro> update (@Valid @PathVariable Integer id, @RequestBody Livro obj){
		 obj.setId(id);
		 Livro newObj = livroService.update(id, obj);
		 return ResponseEntity.ok().body(newObj);
	 }
	
	 @DeleteMapping(value = "/{id}")
	 public ResponseEntity <Void> delete(@PathVariable Integer id){
		 livroService.deletar(id);
		 return ResponseEntity.noContent().build();		 
	 }
}
