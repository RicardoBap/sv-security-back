package com.ricbap.salvavidas.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricbap.salvavidas.api.event.RecursoCriadoEvent;
import com.ricbap.salvavidas.api.model.LancamentoInputModel;
import com.ricbap.salvavidas.api.model.LancamentoResumoModel;
import com.ricbap.salvavidas.domain.model.Lancamento;
import com.ricbap.salvavidas.domain.repository.filter.LancamentoFilter;
import com.ricbap.salvavidas.domain.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
//	@GetMapping
//	public List<Lancamento> listar() {
//		return lancamentoRepository.findAll();
//	}
	
//	@GetMapping
//	public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
//		return lancamentoRepository.filtrar(lancamentoFilter, pageable);
//	}	
	
	//************************************************************************************
	@GetMapping("/resumo")
	public List<LancamentoResumoModel> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		List<LancamentoResumoModel> lan = lancamentoService.findAll(lancamentoFilter, pageable);
		return lan;
	}
	//************************************************************************************
	
//	@RequestMapping(value = "/page",  method = RequestMethod.GET)
//	public ResponseEntity<Page<LancamentoDTO>> findPage(
//			LancamentoFilter lancamentoFilter,
//			@RequestParam(value = "page", defaultValue = "0") Integer page,
//			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
//			@RequestParam(value = "orderBy", defaultValue = "descricao") String orderBy,
//			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
//		Page<Lancamento> list = lancamentoService.findPage(lancamentoFilter, page, linesPerPage, orderBy, direction);
//		Page<LancamentoDTO> listDto = list.map(obj -> new LancamentoDTO(obj));
//		return ResponseEntity.ok().body(listDto);
//	}	
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody LancamentoInputModel lancamentoInputModel, HttpServletResponse response) {		
		LancamentoInputModel lancamentoSalvo = lancamentoService.insert(lancamentoInputModel);				
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo() ));	
		return ResponseEntity.status(HttpStatus.CREATED).body(null); // 201 - Created
	}
	
	
//	@PostMapping
//	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {		
//		Lancamento lancamentoSalvo = lancamentoService.insert(lancamento);				
//		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo() ));		
//		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
//	}
	
//	@GetMapping("/{codigo}")
//	public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo) {
//		Optional<Lancamento> lancamento = lancamentoRepository.findById(codigo);
//		return lancamento.isPresent() ? ResponseEntity.ok(lancamento.get()) : ResponseEntity.notFound().build();
//	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<LancamentoInputModel> finfById(@PathVariable Long codigo) {
		LancamentoInputModel lancamento = lancamentoService.findById(codigo);
		return ResponseEntity.ok(lancamento);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Lancamento> atualizar(@PathVariable Long codigo, @Valid @RequestBody Lancamento lancamento) {
		Lancamento lancamentoSalvo = lancamentoService.atualizar(codigo, lancamento);
		return ResponseEntity.ok(lancamentoSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> remover(@PathVariable Long codigo) {
		lancamentoService.excluir(codigo);
		return ResponseEntity.noContent().build();  // 204 sem nada
	}	

}
