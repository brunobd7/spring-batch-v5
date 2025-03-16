package br.com.dantas.springbatchv5.domain;

import ch.qos.logback.core.net.server.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartaoCredito {

    private Cliente cliente;
    private Integer numeroCartaoCredito;
}
