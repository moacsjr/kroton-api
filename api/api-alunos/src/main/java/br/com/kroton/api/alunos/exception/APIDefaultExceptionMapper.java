package br.com.kroton.api.alunos.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.kroton.api.alunos.APIUtils;
import br.com.kroton.api.alunos.AlunoAPIApplication;
import io.katharsis.errorhandling.ErrorData;
import io.katharsis.errorhandling.ErrorResponse;
import io.katharsis.errorhandling.mapper.ExceptionMapperProvider;
import io.katharsis.errorhandling.mapper.JsonApiExceptionMapper;
import io.katharsis.response.HttpStatus;

@ExceptionMapperProvider
public class APIDefaultExceptionMapper implements JsonApiExceptionMapper<Exception> {

  Log LOG = LogFactory.getLog(APIDefaultExceptionMapper.class);
	
  @Override
  public ErrorResponse toErrorResponse(Exception exception) {
	
	LOG.error(exception);
	
    return ErrorResponse.builder()
      .setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500)
      .setSingleErrorData(ErrorData.builder()
        .addMetaField("transactionId", APIUtils.getUID())
        .addMetaField("apiVersion", APIUtils.getAPIVersion())
        .setTitle("Internal Server Error")
        .setCode("PDA-500")
        .setDetail(exception.getMessage())
        .build())
      .build();
  }
}