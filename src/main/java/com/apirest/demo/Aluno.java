package com.apirest.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "ALUNO")

public class Aluno {
	private static final long serialVersionUID = -1613208135540479336L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ID;
	@Column(name = "matricula")
	@Min(value = 0, message = "A matricula deverá ser maior que zero")
	private int matricula;
	@Column(name = "nome")
	@NotNull(message = "O nome não pode ser nulo")
	@NotBlank(message = "O nome não pode estar em branco")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O nome só deve conter letras")
	private String nome;
	@Column(name = "salario")
	@Min(value = 1, message = "O salário deverá ser maior que zero")
	@Max(value = 10000, message = "O salário deverá ser menor ou igual a R$ 10.000,00")
	private float salario;
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
}
