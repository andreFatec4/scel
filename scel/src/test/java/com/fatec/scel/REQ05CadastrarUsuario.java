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
import com.fatec.scel.model.Usuario;
import com.fatec.scel.model.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class REQ05CadastrarUsuario {
	@Autowired
	UsuarioRepository repository;
	private Validator validator;
	private ValidatorFactory validatorFactory;

	@Test
	public void CT01CadastrarUsuarioComSucesso() {
		// dado que o usuário nao esta cadastrado
		repository.deleteAll();
		// quando o atendente inclui as informacoes obrigatorias e confirma a operacao
		Usuario usuario = new Usuario("1234", "Maria Teste", "mt@teste.com", "04040-000", "Rua A, n 10");
		repository.save(usuario);
		// entao
		//final list<usuario> todos = (list<usuario>) repository.findAll();
		assertEquals(1, repository.count());
		//repository.deleteById();
	}
		
	@Test
	public void CT02CadastrarUsuarioComSucesso_dados_validos() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// given:
		Usuario usuario = new Usuario("1234", "Maria Teste", "mt@teste.com", "04040-000", "Rua A, n 10");
		// when:
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		// then:
		assertTrue(violations.isEmpty());
	}
	
	
	@Test
	public void CT03DeveDetectarRAUsuarioInvalido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o RA do usuário está inválido
		Usuario usuario = new Usuario("", "Maria Teste", "mt@teste.com", "04040-000", "Rua A, n 10");
		// when:
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		// then:
		assertEquals(violations.size(), 1);
		assertEquals("O RA do usuário deve ser preenchido", violations.iterator().next().getMessage());
	}
	
	@Test
	public void CT04DeveDetectarNomeUsuarioInvalido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o nome do usuário está inválido
		Usuario usuario = new Usuario("1234", "", "mt@teste.com", "04040-000", "Rua A, n 10");
		// when:
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		// then:
		assertEquals(violations.size(), 1);
		assertEquals("O nome do usuário deve ser preenchido", violations.iterator().next().getMessage());
	}
	
	@Test
	public void CT05DeveDetectarEmailUsuarioInvalido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o Email do usuário está inválido
		Usuario usuario = new Usuario("aaaa", "Maria Teste", "", "04040-000", "Rua A, n 10");
		// when:
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		// then:
		assertEquals(violations.size(), 1);
		assertEquals("O Email do usuário deve ser preenchido", violations.iterator().next().getMessage());
	}
	
	
}	
