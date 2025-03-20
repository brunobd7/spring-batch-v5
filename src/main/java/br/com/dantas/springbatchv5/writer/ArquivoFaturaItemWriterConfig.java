package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.FaturaCartaoCredito;
import br.com.dantas.springbatchv5.domain.Transacao;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Configuration
public class ArquivoFaturaItemWriterConfig {

    @Bean
    public MultiResourceItemWriter<FaturaCartaoCredito> faturaCartaoCreditoMultiResourceItemWriter() {
        return new MultiResourceItemWriterBuilder<FaturaCartaoCredito>()
                .name("faturaCartaoCreditoMultiResourceItemWriter")
                .resource(new FileSystemResource("C:\\Users\\Bruno\\IdeaProjects\\spring-batch-v5\\files\\fatura")) // FILE location and preffix
                .itemCountLimitPerResource(1)
                .resourceSuffixCreator(index -> index + ".txt") // IMPLEMENTS FUNCTIONAL INTERFACE "ResourceSuffixCreator"
                .delegate(this.arquivoFaturaFlatFileItemWriter())
                .build();
    }

    public FlatFileItemWriter<FaturaCartaoCredito> arquivoFaturaFlatFileItemWriter() {
        return new FlatFileItemWriterBuilder<FaturaCartaoCredito>()
                .name("arquivoFaturaFlatFileItemWriter")
                .resource(new FileSystemResource("C:\\Users\\Bruno\\IdeaProjects\\spring-batch-v5\\files\\fatura.txt"))
                .lineAggregator(faturaLinaAggrefator()) // HOW THE FILE IS WRITE
                .headerCallback(writer ->
                        writer.append(String.format("%121s \n", "CREDIT CARD NAME"))
                                .append(String.format("%121s \n", "SOME, FICTIONAL ADDRESS"))
                )
                .footerCallback(totalFaturaFooterCallbackImpl())
                .build();
    }

    @Bean // Must be a LISTENER (Setup/Pass at STEP configuration), BECAUSE IT NEED AWAIT THE MOMENT BEFORE THE FILE WRITING (See the footerCallbackImplementation).
    public FlatFileFooterCallback totalFaturaFooterCallbackImpl() {
        return new TotalizadorTransacoesFaturaFooterCallback();
    }

    private LineAggregator<FaturaCartaoCredito> faturaLinaAggrefator() {
        return new LineAggregator<FaturaCartaoCredito>() {

            @Override
            public String aggregate(FaturaCartaoCredito faturaCartao) {

                StringBuilder writer = new StringBuilder();
				writer.append(String.format("Nome: %s\n", faturaCartao.getCliente().getNome()));
				writer.append(String.format("Endereço: %s\n\n\n", faturaCartao.getCliente().getEndereco()));
				writer.append(String.format("Fatura completa do cartão %d\n",
						faturaCartao.getCartaoCredito().getNumeroCartaoCredito()));
				writer.append(
						"-------------------------------------------------------------------------------------------------------------------------\n");
				writer.append("DATA DESCRICAO VALOR\n");
				writer.append(
						"-------------------------------------------------------------------------------------------------------------------------\n");

				for (Transacao transacao : faturaCartao.getTransacoes()) {
					writer.append(String.format("\n[%10s] %-80s - %s",
							DateTimeFormatter.ofPattern("dd/MM/yyyy").format(transacao.getData()),
							transacao.getDescricao(),
							NumberFormat.getCurrencyInstance().format(transacao.getValor())));
				}
				return writer.toString();
            }

        };
    }

}
