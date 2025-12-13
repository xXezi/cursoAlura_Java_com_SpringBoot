package xezi.med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import xezi.med.voll.api.dto.DadosCadastroPacienteDTO;
import xezi.med.voll.api.paciente.Paciente;
import xezi.med.voll.api.repository.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroPacienteDTO dados) {
		repository.save(new Paciente(dados));
	}
}