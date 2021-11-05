package com.ricbap.salvavidas.api.exceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ricbap.salvavidas.domain.exception.AuthorizationException;
import com.ricbap.salvavidas.domain.exception.PessoaInexistenteOuInativaException;

@ControllerAdvice
public class SalvaVidasExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override // Atributos adicionais ao cadastrar categoria
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status1, WebRequest request) {		
		Integer status = HttpStatus.BAD_REQUEST.value();
		String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();		
		List<Erro> erros = Arrays.asList(new Erro(status, mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override // Validacao dos campos do formulário / Post -> Campos não preeenchidos
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {		
		List<Erro> erros = criarlistaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
		
	// Código da categoria inexistente
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {		
		Integer status = HttpStatus.BAD_REQUEST.value();
		String mensagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();		
		List<Erro> erros = Arrays.asList(new Erro(status, mensagemUsuario, mensagemDesenvolvedor));		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
	}
	
	// atualizar pessoa com codigo inexistente / codigo nao encontrado
	@ExceptionHandler({ EmptyResultDataAccessException.class }) // EmptyResultDataAccessException
	public ResponseEntity<Object> handleEmptyResultDataAccessExceptionEntity(EmptyResultDataAccessException ex) {		
		Integer status = HttpStatus.NOT_FOUND.value();
		String mensagemUsuario = messageSource.getMessage("recurso.nao-encontado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();		
		List<Erro> erros = Arrays.asList(new Erro(status, mensagemUsuario, mensagemDesenvolvedor));		
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(erros);
	}
	
	
	@ExceptionHandler({ PessoaInexistenteOuInativaException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex, WebRequest request) {			
		Integer status = HttpStatus.BAD_REQUEST.value();
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();		
		List<Erro> erros = Arrays.asList(new Erro(status, mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({ AuthorizationException.class })
	public ResponseEntity<Object> handleAuthorizationException(AuthorizationException ex, WebRequest request) {			
		Integer status = HttpStatus.FORBIDDEN.value(); // 403
		String mensagemUsuario = messageSource.getMessage("pessoa.acesso-negado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();		
		List<Erro> erros = Arrays.asList(new Erro(status, mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erros);
	}
	
		
	
	
	
	private List<Erro> criarlistaDeErros(BindingResult bindingResult) {
		List<Erro> erros = new ArrayList<>();
		
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			Integer status = HttpStatus.BAD_REQUEST.value();
			String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = fieldError.toString();
			erros.add(new Erro(status, mensagemUsuario, mensagemDesenvolvedor));
		}		
		return erros;
	}
	
	public static class Erro {	
		private Integer status;
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;
		
		public Erro(Integer status, String mensagemUsuario, String mensagemDesenvolvedor) {
			this.status = status;
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}
		
		public Integer getStatus() {
			return status;
		}
		public String getMensagemUsuario() {
			return mensagemUsuario;
		}
		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}		
	}

}
