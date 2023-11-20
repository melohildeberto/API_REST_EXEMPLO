package com.apirest.demo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerAluno {
	private final RepositorioAluno repository;

	ControllerAluno(RepositorioAluno repository) {
		this.repository = repository;
	}

	@GetMapping("/alunos")
	List<Aluno> all() {
		return repository.findAll();
	}

	@PostMapping("/novoaluno")
	ResponseEntity<?> novoAluno(@Valid @RequestBody Aluno aluno, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ResponseError erros = new ResponseError();
			for (ObjectError object : bindingResult.getAllErrors()) {
				erros.mensagens.add(object.getDefaultMessage());
			}
			return new ResponseEntity<>(erros, HttpStatus.BAD_GATEWAY);
		}

		repository.save(aluno);
		return new ResponseEntity<>("User registered Successfully!", HttpStatus.OK);
	}

	// Single item

	@GetMapping("/getaluno/{id}")
	Aluno getAluno(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new AlunoNotFoundException(id));
	}

	@PutMapping("/atualizaraluno/{id}")
	Aluno atualizarAluno(@RequestBody Aluno novoAluno, @PathVariable Long id) {

		return repository.findById(id).map(aluno -> {
//			aluno.setNome(novoAluno.getNome());
//			aluno.setCpf(novoAluno.getCpf());
			return repository.save(aluno);
		}).orElseGet(() -> {
//			novoAluno.setId(id);
			return repository.save(novoAluno);
		});
	}

	@DeleteMapping("/removeraluno/{id}")
	void removerAluno(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
