package br.com.sistema.api.model.endereco;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable // Disponibiliza a conexão para as tabelas principais.
public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String UF;
    private String CEP;

}
