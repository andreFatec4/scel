package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class REQ02ConsultarLivro {
	@Autowired
	LivroRepository repository;
	static Livro livro;

	@Test
	public void CT01ConsultarLivroComSucesso() {
		// dado que o livro está cadastrado
		livro = new Livro("3333", "Teste de Software", "Delamaro");	
		repository.save(livro);
		// quando o usuário consulta o livro
		Livro ro = repository.findByIsbn("3333");
		// entao 
		assertThat(ro.getTitulo()).isEqualTo(livro.getTitulo());
		equals (ro.equals(livro));
	}
	
	@Test
	public void CT01ConsultarLivroNaoCadastrado() {
		// dado que o livro não está cadastrado
		// quando o usuário consulta o livro
		Livro ro = repository.findByIsbn("2222");
		// entao 
		assertThat(ro).isNull();
	}
}
