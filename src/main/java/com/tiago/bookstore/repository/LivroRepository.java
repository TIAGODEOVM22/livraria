package com.tiago.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Repository;

import com.tiago.bookstore.domain.Livro;
import com.tiago.bookstore.dto.LivroDto;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

//	@Query("SELECT obj FROM Livro obj WHERE obj.categoria.id = id_cat ORDER BY titulo")
	//CollectionModel<Livro>findAllByCategoria(@Param(value = "id_cat") Integer id_cat);
	/*lista todos os livros de uma determinada categoria ordenando pelo titulo */
	
	@Query("SELECT obj FROM Livro obj WHERE obj.categoria.id = id_cat ORDER BY titulo")
	CollectionModel<LivroDto>findAllByCategoria(@Param(value = "id_cat") Integer id_cat);
	/*lista todos os livros de uma determinada categoria ordenando pelo titulo */
}

