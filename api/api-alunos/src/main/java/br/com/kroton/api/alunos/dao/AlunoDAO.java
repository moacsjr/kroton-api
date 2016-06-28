package br.com.kroton.api.alunos.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import br.com.kroton.api.alunos.data.Aluno;

@Mapper
public interface AlunoDAO {
	
	List<Aluno> findAll();

	Aluno findById(String rA, String sistema);

}
