package xezi.med.voll.api.dto;

import xezi.med.voll.api.domain.endereco.Endereco;
import xezi.med.voll.api.domain.paciente.Paciente;

public record DadosDetalhamentoPacienteDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco)
{
    public DadosDetalhamentoPacienteDTO(Paciente paciente){
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getCpf(),
                paciente.getEndereco()
        );
    }
}
