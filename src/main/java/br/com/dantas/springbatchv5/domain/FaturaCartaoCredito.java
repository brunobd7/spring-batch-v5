package br.com.dantas.springbatchv5.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaturaCartaoCredito {

    private Cliente cliente;
    private CartaoCredito cartaoCredito;
    private List<Transacao> transacoes = new ArrayList<>();

}
