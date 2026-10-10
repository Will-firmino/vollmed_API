package br.com.sistema.api.model.medico;

public record DadosListagemMedico(
				Integer id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        DadosCadastroEndereco endereco) {

				public DadosListagemMedico(Medico medico){ // nome, especialidade, crm
					this(medico.getId(), medico.getNome(), medico.getEspecialidade(), medido.getCrm())
				};

				

}