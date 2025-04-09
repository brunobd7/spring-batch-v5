package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.Cliente;
import br.com.dantas.springbatchv5.domain.InteresseClienteProduto;
import br.com.dantas.springbatchv5.domain.Produto;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class InteresseClienteProdutoJdbcReaderConfig {

    @Bean
    public JdbcCursorItemReader<InteresseClienteProduto> interesseClienteProdutoReader(
            @Qualifier("applicationDbDataSource") DataSource applicationDbDataSource ) {
        //MAP HERE
        return new JdbcCursorItemReaderBuilder<InteresseClienteProduto>()
                .name("interesseClienteProdutoReader")
                .dataSource(applicationDbDataSource)
                .sql("""
                        SELECT *
                        FROM interesse_produto_cliente
                                 join cliente on interesse_produto_cliente.cliente = cliente.id
                                 join produto on interesse_produto_cliente.produto = produto.id
                        WHERE interesse_produto_cliente.cliente is not null;
                        """)
                .rowMapper((resultSet, rowNum) -> {

                    Cliente cliente = new Cliente();
                    cliente.setId(resultSet.getInt("id"));
                    cliente.setNome(resultSet.getString("nome"));
                    cliente.setEmail(resultSet.getString("email"));

                    // SAME COLUM NAME ON RESULT SET , REFACTOR QUERY OR GET BY COLUMN INDEX ON RESULTSET
                    Produto produto = new Produto();
                    produto.setId(resultSet.getInt(6));
                    produto.setNome(resultSet.getString(7));
                    produto.setDescricao(resultSet.getString("descricao"));
                    produto.setPreco(resultSet.getBigDecimal("preco"));

                    InteresseClienteProduto interesseClienteProduto = new InteresseClienteProduto();
                    interesseClienteProduto.setCliente(cliente);
                    interesseClienteProduto.setProduto(produto);

                    return interesseClienteProduto;

                })
                .build();
    }


//    private RowMapper<InteresseClienteProduto> mapperResulsetFromDb() {
//        return new RowMapper<InteresseClienteProduto>() {
//
//            @Override
//            public InteresseClienteProduto mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return null;
//            }
//        };
//    }
}
