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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tiago.bookstore.assembler.CategoriaAssembler;
import com.tiago.bookstore.domain.Categoria;
import com.tiago.bookstore.dto.CategoriaDto;
import com.tiago.bookstore.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource<TCategoriaDto> {

	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private CategoriaAssembler categoriaAssembler;

	@GetMapping(value = "/{categoriaId}")
	public ResponseEntity<CategoriaDto> findById(@PathVariable Integer categoriaId) {
		return ResponseEntity.ok().body(categoriaAssembler.toCategoriaDto(categoriaService.findById(categoriaId)));
	}
	
	/*@GetMapping(value = "/{categoriaId}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer categoriaId) {
		return ResponseEntity.ok().body(categoriaAssembler.
				toCategoriaHateos(categoriaService.findById(categoriaId)));
	}*/

	@GetMapping
	public ResponseEntity<CollectionModel<CategoriaDto>> findAll() {
		return ResponseEntity.ok().body(categoriaService.findAll()) ;

	}

	@PostMapping
	public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria obj) {
		obj = categoriaService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoriaDto> update(@Valid @PathVariable Integer id, @RequestBody CategoriaDto objDto) {
		objDto.setId(id);
		return ResponseEntity.ok().body(categoriaAssembler.
				toCategoriaDto(categoriaService.update(id, objDto)));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		categoriaService.deletar(id);
		
		return ResponseEntity.ok().body("Categoria Deletada!");
	}
	
	/*@GetMapping RETORNA TODOS CATEGORIA JA EM DTO E COM LINKS, MAS NÃO ESTA USANDO PADRÃO HALL
	 * IREMOS MUDAR O RETORNO DE LIST PARA COLLECTIONMODEL, O método toListCategoriaDto TAMBEM TERÁ
	 * QUE SER ALTERADO SEU RETORNO.
	public ResponseEntity<List<CategoriaDto>> findAll() {
		return ResponseEntity.ok().body(categoriaAssembler.toListCategoriaDto(categoriaService.findAll()));

	}*/
	
	/*@DeleteMapping(value = "/{id}")
	public ResponseEntity<CategoriaDto> delete(@PathVariable Integer id) {
		categoriaService.deletar(id);
		return ResponseEntity.noContent().build();
	}*/
	
}