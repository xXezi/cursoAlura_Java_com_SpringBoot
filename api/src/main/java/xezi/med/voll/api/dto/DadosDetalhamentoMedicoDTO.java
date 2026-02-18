package xezi.med.voll.api.dto;

import xezi.med.voll.api.endereco.Endereco;
import xezi.med.voll.api.enums.Especialidade;
import xezi.med.voll.api.medico.Medico;

public record DadosDetalhamentoMedicoDTO(
		Long id,
		String nome,
		String email,
		String crm,
		String telefone,
		Especialidade especialidade,
		Endereco endereco)
{
	public DadosDetalhamentoMedicoDTO(Medico medico) {
		this(
				medico.getId(),
				medico.getNome(),
				medico.getEmail(),
				medico.getCrm(),
				medico.getTelefone(),
				medico.getEspecialidade(),
				medico.getEndereco()
		);
	}
}
