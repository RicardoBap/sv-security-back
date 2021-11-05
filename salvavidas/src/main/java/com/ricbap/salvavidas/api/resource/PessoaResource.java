package com.ricbap.salvavidas.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricbap.salvavidas.api.event.RecursoCriadoEvent;
import com.ricbap.salvavidas.api.model.PessoaResumoModel;
import com.ricbap.salvavidas.domain.model.Pessoa;
import com.ricbap.salvavidas.domain.repository.PessoaRepository;
import com.ricbap.salvavidas.domain.service.PessoaService;


@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	

	@PreAuthorize("hasAnyRole('ADMIN')") // <-- Só ADMIN pode listar todas pessoas paginadas
	@GetMapping
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}
	
	// Pessoa pode acessar ele mesmo
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
		Pessoa pessoa = pessoaService.find(codigo);
		return ResponseEntity.ok(pessoa);
	}	

	// Carrega pessoa no cadastro de lancamento
	@GetMapping("/resumo")
	public List<PessoaResumoModel> findAll() {
		return pessoaService.findAll(); 
	}
	
	// liberado a todos principalmente para quem está logado
	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo() ));				
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	// Pessoa pode acessar ele mesmo
//	@GetMapping("/{codigo}")
//	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
//		Optional<Pessoa> pessoa = pessoaRepository.findById(codigo);
//		return pessoa.isPresent() ? ResponseEntity.ok(pessoa.get()) : ResponseEntity.notFound().build();
//	}

	
	// Pessoa pode apenas alterar ele mesmo
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
		return ResponseEntity.ok(pessoaSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	public ResponseEntity<Void> atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
		return ResponseEntity.noContent().build(); // 204 sem nada
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')") // <-- Só ADMIN pode excluir algum usuario
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> remover(@PathVariable Long codigo) {		
		pessoaService.excluir(codigo);
		return ResponseEntity.noContent().build();  // 204 sem nada
	}
	

}
