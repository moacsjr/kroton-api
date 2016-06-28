/**
 * 
 */
package br.com.kroton.api.alunos.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.kroton.api.alunos.dao.AlunoDAO;
import br.com.kroton.api.alunos.data.Aluno;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.JsonApiFindAll;
import io.katharsis.repository.annotations.JsonApiResourceRepository;

/**
 * @author jair.souza
 *
 */
@JsonApiResourceRepository(Aluno.class)
@Component
public class AlunoRepository {
	@Autowired
	private AlunoDAO alunoDao;

    @JsonApiFindAll
    public Iterable<Aluno> findAll(QueryParams requestParams) {
    	return alunoDao.findAll();
    }
}
