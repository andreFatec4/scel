package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;

class REQ03ExcluirLivro {
	@Autowired
	LivroRepository repository;

	@Test
	public void CT01ExcluirLivroComSucesso() {
		// dado que o isbn nao esta cadastrado
		repository.deleteAll();
		// quando o usuario inclui as informacoes obrigatorias e confirma a operacao
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		// então o sistema assegura que o livro existe no bd
		repository.save(livro);
		// o sistema procura o livro pelo ISBN informado
		Livro ro = repository.findByIsbn("3333");
		// então deleta-o
		repository.deleteById(ro.getId());
		// entao o sistema valida as informações E envia uma mensagem de livro
		// excluído com sucesso
		assertThat(repository.findByIsbn("3333")).isEqualTo(null);
	}

}