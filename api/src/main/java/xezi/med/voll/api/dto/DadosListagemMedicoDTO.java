package xezi.med.voll.api.dto;

import xezi.med.voll.api.enums.Especialidade;
import xezi.med.voll.api.medico.Medico;

public record DadosListagemMedicoDTO(
		Long id,
		String nome,
		String email,
		String crm,
		Especialidade especialidade) 
{
	public DadosListagemMedicoDTO(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}
}
