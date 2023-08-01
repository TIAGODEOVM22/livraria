package com.tiago.bookstore.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiago.bookstore.domain.Categoria;
import com.tiago.bookstore.domain.Livro;
import com.tiago.bookstore.repository.CategoriaRepository;
import com.tiago.bookstore.repository.LivroRepository;

@Service
public class DBServices {

	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public void instanciaBD() {
		Categoria cat1 = new Categoria(null, "Basica", "Livros para tecnicos jr");
		Categoria cat2 = new Categoria(null, "Intermediaria", "Livros para tecnicos pleno");
		Categoria cat3 = new Categoria(null, "Avançada", "Livros para tecnicos senior");

		Livro l1 = new Livro(null, "Clean Code", "Robert Martin", "Loren Ipsun", cat1);
		Livro l2 = new Livro(null, "Lógica de Programação", "Bill Gates", "Loren Ipsun", cat3);
		Livro l3 = new Livro(null, "SpringBoot", "Valdir César", "Loren Ipsun", cat2);

		cat1.getLivro().addAll(Arrays.asList(l1));
		cat3.getLivro().addAll(Arrays.asList(l2));

		this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		this.livroRepository.saveAll(Arrays.asList(l1, l2, l3));
	}
}
