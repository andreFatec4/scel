package com.fatec.scel;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class REQ01CadastrarLivro {
	@Autowired
	LivroRepository repository;
	private Validator validator;
	private ValidatorFactory validatorFactory;

	/**
	 * Verificar o comportamento da classe LivroRepository
	 */
	@Test
	public void CT01CadastrarLivroComSucesso() {
		// dado que o isbn nao esta cadastrado
		repository.deleteAll();
		// quando o usuário inclui as informacoes obrigatorias e confirma a operacao
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		repository.save(livro);
		// entao
		assertEquals(1, repository.count());
	}

	@Test
	public void CT02CadastrarLivroComSucesso_dados_validos() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// given:
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		// when:
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
		// then:
		assertTrue(violations.isEmpty());
	}

	@Test
	public void CT03DeveDetectarTituloInvalido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o titulo do livro esta invalido
		Livro livro = new Livro("3333", "", "Delamaro");
		// when:
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
		// then:
		assertEquals(violations.size(), 1);
		assertEquals("O titulo deve ser preenchido", violations.iterator().next().getMessage());
	}
	
	@Test
	public void CT04DeveDetectarISBNInvalido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o isbn do livro esta invalido
		Livro livro = new Livro("", "Teste de Software", "Delamaro");
		// when:
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
		// then:
		assertEquals(violations.size(), 1);
		assertEquals("O isbn deve ser preenchido", violations.iterator().next().getMessage());
	}
	
	@Test
	public void CT05DeveDetectarAutorInvalido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o autor do livro esta invalido
		Livro livro = new Livro("3333", "Teste de Software", "");
		// when:
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
		// then:
		assertEquals(violations.size(), 1);
		assertEquals("Autor invalido", violations.iterator().next().getMessage());
	}
	
	@Test
	public void CT06DeveDetectarAutorInvalido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o autor do livro tem mais de 50 caracteres
		Livro livro = new Livro("3333", "Teste de Software", "012345678901234567890123456789012345678901234567890");
		// when:
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
		// then:
		assertEquals(violations.size(), 1);
		assertEquals("Autor deve ter entre 1 e 50 caracteres", violations.iterator().next().getMessage());
	}
}