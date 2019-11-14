package com.fatec.scel;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import com.fatec.scel.model.Usuario;
import com.fatec.scel.model.UsuarioRepository;

class REQ07ExcluirUsuario {
	@Autowired
	UsuarioRepository repository;

	@Test
	public void CT01ExcluirUsuarioComSucesso() {
		// dado que o ra nâo está cadastrado
		repository.deleteAll();
		// quando o usuário inclui as informações obrigatórias e confirma a operação
		Usuario usuario = new Usuario("1234", "Maria Teste", "mt@teste.com", "04040-000", "Rua A, n 10");
		// então o sistema valida as informações
		repository.save(usuario);
		// o sistema procura o usuário pelo RA cadastrado 
		Usuario ro = repository.findByRa("1234");
		// então o deleta do bd
		repository.deleteById(ro.getId());
	//	assertThat(repository.findByRa("1234")).isEqualTo(null);
	}

}
