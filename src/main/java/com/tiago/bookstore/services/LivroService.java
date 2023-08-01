package com.tiago.bookstore.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.tiago.bookstore.assembler.LivroAssembler;
import com.tiago.bookstore.domain.Categoria;
import com.tiago.bookstore.domain.Livro;
import com.tiago.bookstore.dto.LivroDto;
import com.tiago.bookstore.repository.LivroRepository;
import com.tiago.bookstore.services.exception.ObjectNotFoundException;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private LivroAssembler livroAssembler;
	
	public Livro findById(Integer id) {
		Optional<Livro> obj = livroRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException
				("Objeto não encontrado! ID: " + id + ", tipo: " + Livro.class.getName()));
	}
	
	public CollectionModel<LivroDto> findAll() {
		return livroAssembler.toListLivroDto(livroRepository.findAll());
	
	}
	
	/*public LivroDto update(Integer id, LivroDto objDto) {
		Livro livro = findById(id);
		return livroAssembler.toLivroDto(livro);
	}*/

	public Livro update(Integer id, Livro oldLivro) {
		Livro newLivro = findById(id);
		newLivro.setTitulo(oldLivro.getTitulo());
		newLivro.setNome_autor(oldLivro.getNome_autor());
		newLivro.setTexto(oldLivro.getTexto());
		return livroRepository.save(newLivro);
	}

	public Livro create(Integer id_cat, Livro obj) {
		obj.setId(null);
		Categoria cat = categoriaService.findById(id_cat);
		obj.setCategoria(cat);
		return livroRepository.save(obj);
	}

	public void deletar(Integer id) {
		Livro obj = findById(id);/*Antes de deletar verifica se livro existe*/
		livroRepository.delete(obj);
	}
	
	
	/*RETORNA LISTA DE LIVROS POR CAT. SEM HATEOS*/
	/*public List<Livro> findAll(Integer id_cat) {
		categoriaService.findById(id_cat);
		return livroRepository.findAllByCategoria(id_cat);
		
	}*/
	
}
