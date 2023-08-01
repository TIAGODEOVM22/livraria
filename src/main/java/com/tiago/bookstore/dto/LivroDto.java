package com.tiago.bookstore.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDto extends RepresentationModel<LivroDto> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Campo TITULO é requerido!")
	@Length(min = 3, max = 255, message = "Campo TITULO pode ser de 3 a 255 Caracteres")
	private String titulo;
	
	@NotEmpty(message = "Campo NOME DO AUTOR é requerido!")
	@Length(min = 3, max = 255, message = "Campo NOME DO AUTOR pode ser de 3 a 255 Caracteres")
	private String nome_autor;
	
	@NotEmpty(message = "Campo TEXTO é requerido!")
	@Length(min = 10, max = 2000000, message = "Campo TEXTO pode ser de 10 a Dois Milhoes de caracteres")
	private String texto;
	

	/* TRANSFORMA SEM USAR MODELMAPPER E HATEOAS
	public LivroDto(Livro obj) {
		super();
		this.id = obj.getId();
		this.titulo = obj.getTitulo();
		this.nome_autor = obj.getNome_autor();

	}*/

}
