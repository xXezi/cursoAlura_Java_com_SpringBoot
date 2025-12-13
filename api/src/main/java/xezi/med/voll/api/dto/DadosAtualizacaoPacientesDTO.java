package xezi.med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPacientesDTO(
		
		@NotNull
		Long id,
		String nome,
		String telefone,
		DadosEnderecoDTO endereco) {}