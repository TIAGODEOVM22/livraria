package com.tiago.bookstore.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Relation (collectionRelation = "Categorias") /*altera array de Links CategoriaDtoList para Categorias */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoriaDto extends RepresentationModel<CategoriaDto> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Campo NOME é requerido!")
	@Length(min = 3, max = 100, message = "Nome pode ser de 3 a 100 caracteres!")
	private String nome;
	
	@NotEmpty(message = "Campo DESCRIÇÃO é requerido!")
	@Length(min = 3, max = 255, message = "Descrição pode ser de 3 a 255 caracteres!")
	private String descricao;
	
}
