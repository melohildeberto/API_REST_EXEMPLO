package com.apirest.demo;

public class AlunoNotFoundException extends RuntimeException {

	AlunoNotFoundException(Long id) {
		super("Aluno não encontrado " + id);
	}
}