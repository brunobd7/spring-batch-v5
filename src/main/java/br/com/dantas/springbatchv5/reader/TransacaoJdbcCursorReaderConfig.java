package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.CartaoCredito;
import br.com.dantas.springbatchv5.domain.Cliente;
import br.com.dantas.springbatchv5.domain.Transacao;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Configuration
public class TransacaoJdbcCursorReaderConfig {

    @Bean
    public JdbcCursorItemReader<Transacao>  transacaoJdbcCursorItemReader() {
        return new JdbcCursorItemReaderBuilder<Transacao>()
                .name("transacaoJdbcCursorItemReader")
                .sql("""
                        select * from transacao 
                        join cartao_credito cc on transacao.numero_cartao_credito = cc.numero_cartao_credito
                        order by cc.numero_cartao_credito 
                        
                        """)
                .rowMapper((rs, rowNum) -> {

                    CartaoCredito cartaoCredito = CartaoCredito
                            .builder()
                            .numeroCartaoCredito(rs.getInt("numero_cartao_credito"))
                            .build();

                    Cliente cliente = Cliente
                            .builder()
                            .id(rs.getInt("id"))
                            .build();

                    cartaoCredito.setCliente(cliente);

                    Transacao transacao = new Transacao();
                    transacao.setId(rs.getInt("id"));
                    transacao.setDescricao(rs.getString("descricao"));
                    transacao.setValor(rs.getBigDecimal("valor"));
                    transacao.setData(
                            LocalDate.parse(rs.getString("data"), DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    );
                    transacao.setCartaoCredito(cartaoCredito);

                    return transacao;
                })
                .build();
    }
}
