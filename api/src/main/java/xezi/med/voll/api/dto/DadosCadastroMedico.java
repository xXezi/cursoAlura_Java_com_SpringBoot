package xezi.med.voll.api.dto;

import xezi.med.voll.api.endereco.DadosEndereco;
import xezi.med.voll.api.enums.Especialidade;

public record DadosCadastroMedico(
		String nome, 
		String email, 
		String crm, 
		Especialidade especialidade, 
		DadosEndereco endereco
		) {
}