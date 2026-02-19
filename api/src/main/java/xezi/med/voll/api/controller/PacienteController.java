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
import xezi.med.voll.api.dto.DadosAtualizacaoPacientesDTO;
import xezi.med.voll.api.dto.DadosCadastroPacienteDTO;
import xezi.med.voll.api.dto.DadosDetalhamentoPacienteDTO;
import xezi.med.voll.api.dto.DadosListagemPacienteDTO;
import xezi.med.voll.api.domain.paciente.Paciente;
import xezi.med.voll.api.repository.PacienteRepository;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPacienteDTO dados, UriComponentsBuilder uriBuilder) {

		Paciente paciente = new Paciente(dados);
		repository.save(paciente);

		URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

		return ResponseEntity.created(uri).body(new DadosDetalhamentoPacienteDTO(paciente));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemPacienteDTO>> listar(@PageableDefault(sort="nome", size=10) Pageable paginacao){
		Page<DadosListagemPacienteDTO> page = repository.findAllByAtivoTrue(paginacao)
				.map(DadosListagemPacienteDTO::new);

		return ResponseEntity.ok(page);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPacientesDTO dados) {
		Paciente paciente = repository.getReferenceById(dados.id());
		paciente.atualizarInformacoes(dados);

		return ResponseEntity.ok(new DadosDetalhamentoPacienteDTO(paciente));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		Paciente paciente = repository.getReferenceById(id);
		paciente.excluir();

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id){
		Paciente paciente = repository.getReferenceById(id);

		return ResponseEntity.ok(new DadosDetalhamentoPacienteDTO(paciente));
	}
}