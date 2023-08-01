package com.tiago.bookstore.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Livro extends RepresentationModel<Livro> implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Campo TITULO é requerido!")
	@Length(min = 3, max = 255, message = "Campo TITULO pode ser de 3 a 255 Caracteres")
	private String titulo;
	
	@NotEmpty(message = "Campo NOME DO AUTOR é requerido!")
	@Length(min = 3, max = 255,  message = "Campo NOME DO AUTOR pode ser de 3 a 255 Caracteres")
	private String nome_autor;
	
	@NotEmpty(message = "Campo TEXTO é requerido!")
	@Length(min = 10, max = 2000000, message = "Campo TEXTO pode ser de 10 a Dois Milhoes de caracteres")
	private String texto;
	
	@JsonIgnore /*Não vai trazer este atributo*/
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
}
