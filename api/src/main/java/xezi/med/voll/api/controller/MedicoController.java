package xezi.med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;
import xezi.med.voll.api.dto.DadosAtualizacaoMedicosDTO;
import xezi.med.voll.api.dto.DadosCadastroMedicoDTO;
import xezi.med.voll.api.dto.DadosDetalhamentoMedicoDTO;
import xezi.med.voll.api.dto.DadosListagemMedicoDTO;
import xezi.med.voll.api.domain.medico.Medico;
import xezi.med.voll.api.repository.MedicoRepository;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicoDTO dados, UriComponentsBuilder uriBuilder) {

		Medico medico = new Medico(dados);
		repository.save(medico);

		URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDTO(medico));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedicoDTO>> listar(@PageableDefault(size=10, sort="nome") Pageable paginacao){
		Page<DadosListagemMedicoDTO> page = repository.findAllByAtivoTrue(paginacao)
				.map(DadosListagemMedicoDTO::new);
		
		return ResponseEntity.ok(page);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedicosDTO dados) {
		Medico medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		Medico medico = repository.getReferenceById(id);
		medico.excluir();
		
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		Medico medico = repository.getReferenceById(id);

		return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
	}
}
