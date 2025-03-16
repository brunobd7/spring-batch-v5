package br.com.dantas.springbatchv5.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transacao {

    private Integer id;
    private CartaoCredito cartaoCredito;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
}
