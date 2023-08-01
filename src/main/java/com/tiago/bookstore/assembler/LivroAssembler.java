package com.tiago.bookstore.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.tiago.bookstore.domain.Livro;
import com.tiago.bookstore.dto.LivroDto;
import com.tiago.bookstore.resource.LivroResource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class LivroAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	
	/* TRANSFORMA UM OBJ PARA DTO */
	public LivroDto toLivroDto(Livro livro) {
		LivroDto livroDto = modelMapper.map(livro, LivroDto.class);

		/* Gera link para objDto de forma dinâmica através do metodo findById */
		livroDto.add(WebMvcLinkBuilder.linkTo
				(WebMvcLinkBuilder.methodOn(LivroResource.class)
				.findById(livro.getId()))
				.withSelfRel());

		/* gera Link para o próprio recurso dessa coleção */
		livroDto.add(linkTo(methodOn(LivroResource.class).findAll()).withSelfRel());

		return livroDto;
	}

	/* TRANSFORMA "COLEÇÃO" PARA DTO */
	/* Alterado de List p/ CollectionModel adicionando tudo dentro do Embedded */

	public CollectionModel<LivroDto> toListLivroDto(List<Livro> livros) {
		var livroDto = CollectionModel.of(livros.stream()
				.map(livro -> toLivroDto(livro))
				.collect(Collectors.toList()));

		/* gera Link para o próprio recurso dessa coleção */
		livroDto.add(linkTo(methodOn(LivroResource.class).findAll()).withSelfRel());

		return livroDto;
	}

	/* Não transforma para DTO apenas gerá link */
	public Livro toLivroHateoas(Livro livro) {
		livro.add(WebMvcLinkBuilder.linkTo
				(WebMvcLinkBuilder.methodOn(LivroResource.class)
				.findById(livro.getId()))
				.withSelfRel());
		/* ainda falta gerar link para a coleção de livros ainda dentro desse método */
		return livro;
	}

	/* Transforma para DTO e gera link */
	/*
	 * public LivroDto toLivroDto(Livro livro) { 
	 * LivroDto objDto = modelMapper.map(livro, LivroDto.class);
	 * objDto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LivroResource.
	 * class) .findById(livro.getId())).withSelfRel());

	 * objDto.add(linkTo(methodOn(LivroResource.class)).withSelfRel()); return
	 * objDto; }
	 */

	/*
	 * public List<LivroDto> toListLivroDto(List<Livro> livros) { return
	 * livros.stream().map(this::toLivroDto).collect(Collectors.toList());
	 * 
	 * }
	 */

	/*
	 * METODOS SEM HATEOAS TRANSFORMA DE DOMAIN P/ DTO public LivroDto
	 * toLivroDto(Livro livro) { return modelMapper.map(livro, LivroDto.class); }
	 * 
	 * public List<LivroDto> toListLivroDto(List<Livro> livros) { return
	 * livros.stream().map(this::toLivroDto).collect(Collectors.toList());
	 * 
	 * }
	 */

}
