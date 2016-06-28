/**
 * 
 */
package br.com.kroton.api.alunos.repository;

import java.nio.charset.Charset;
import java.rmi.server.UID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.kroton.api.alunos.dao.AlunoDAO;
import br.com.kroton.api.alunos.data.Aluno;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.queryParams.params.FilterParams;
import io.katharsis.repository.annotations.JsonApiFindAll;
import io.katharsis.repository.annotations.JsonApiFindOne;
import io.katharsis.repository.annotations.JsonApiMeta;
import io.katharsis.repository.annotations.JsonApiResourceRepository;
import io.katharsis.resource.exception.ResourceException;
import io.katharsis.resource.exception.ResourceNotFoundException;
import io.katharsis.response.MetaInformation;

/**
 * @author jair.souza
 *
 */
@JsonApiResourceRepository(Aluno.class)
@Component
public class AlunoRepository{
	
	@Value("${api.version}")
	private String apiVersion;
	
	@Autowired
	private AlunoDAO alunoDao;

    @JsonApiFindAll
    public Iterable<Aluno> findAll(QueryParams requestParams) {
    	throw new ResourceException("Operação não permitida");
    }
    
    @JsonApiFindOne
    public Aluno findOne(String RA, QueryParams requestParams){
    	String sistema = null;
    	FilterParams param = requestParams.getFilters().getParams().get("alunos");
    	if(param!=null){
    		Set<String> value = param.getParams().get("sistema");
    		if(value !=null){
    			sistema = value.iterator().next();
    		}
    	}
    	Aluno aluno = alunoDao.findById(RA, sistema );
    	if (aluno == null ){
    		throw new ResourceNotFoundException("Aluno não encontrado");
    	}
		return aluno;
    }

    @JsonApiMeta
	public MetaInformation getMetaInformation(List<Aluno> resources) {
		
		return new MetaInformation() {
			public String transactionId = AlunoRepository.this.getTransactionId();
			public String apiVersion = AlunoRepository.this.apiVersion;
		};
	}

	protected String getTransactionId() {
		long time = new Date().getTime();
		UID uid = new java.rmi.server.UID();
		Double rand = Math.random();
		String transactionID = time+"."+uid+"."+rand;
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(transactionID.getBytes(Charset.forName("UTF8")));  
	    final byte[] resultByte = messageDigest.digest();
	    final String result = new String(Hex.encodeHex(resultByte));
	    return result;
		
	}
}
