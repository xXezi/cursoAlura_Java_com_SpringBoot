package xezi.med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xezi.med.voll.api.dto.DadosEnderecoDTO;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
	private String logradouro;
	private String bairro;
	private String cep;
	private String complemento;
	private String cidade;
	private String uf;
	private String numero;	
	
	public Endereco(DadosEnderecoDTO dados) {
		this.logradouro = dados.logradouro();
		this.bairro = dados.bairro();
		this.cep = dados.cep();
		this.uf = dados.uf();
		this.cidade = dados.cidade();
		this.numero = dados.numero();
		this.complemento = dados.complemento();
	}
}
