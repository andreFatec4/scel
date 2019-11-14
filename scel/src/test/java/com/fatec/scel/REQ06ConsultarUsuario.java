package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fatec.scel.model.UsuarioRepository;
import com.fatec.scel.model.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
public class REQ06ConsultarUsuario {
	@Autowired
	UsuarioRepository repository;
	static Usuario usuario;

	@Test
	public void CT01ConsultarUsuarioComSucesso() {
		// dado que o usuário está cadastrado
		usuario = new Usuario("1234", "Maria Teste", "mt@teste.com", "04040-000", "Rua A, n 10");
		repository.save(usuario);
		// quando o atendente consulta o usuário
		Usuario ro = repository.findByRa("1234");
		// entao
		assertThat(ro.getNome()).isEqualTo(usuario.getNome());
		equals(ro.equals(usuario));
	}

	@Test
	public void CT01ConsultarUsuarioNaoCadastrado() {
		// dado que o Usuário não é cadastrado
		// quando o atendente consulta o livro
		Usuario ro = repository.findByRa("2222");
		// entao
		assertThat(ro).isNull();
	}

}
