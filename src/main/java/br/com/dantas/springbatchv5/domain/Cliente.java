package br.com.dantas.springbatchv5.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    private Integer id;
    private String nome;
    private String endereco;


}
