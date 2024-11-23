package br.com.dantas.springbatchv5.reader.mapper;

import br.com.dantas.springbatchv5.domain.Cliente;
import br.com.dantas.springbatchv5.domain.Transacao;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ClienteTransacaoLineMapperConfig {


    /** This component could use a pattern to discover/identify which mapper it needs to apply.
     */
    @Bean
    public PatternMatchingCompositeLineMapper lineMapper() {
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper();

        lineMapper.setTokenizers(setupTokenizers()); // GET LINE DATA AND TRANSFORM THEM INTO 'WORDS'
        lineMapper.setFieldSetMappers(setupFieldSetMappers()); // GET WORDS FROM TOKENIZERS AND MAP IN SOME DOMAIN OBJECT

        return lineMapper;
    }

    /* Setting up field mapper to domain class using useful spring component to help this proccess */
    private Map<String, FieldSetMapper> setupFieldSetMappers() {
        Map<String, FieldSetMapper> fieldSetMappers = new HashMap<>();
        fieldSetMappers.put("0*" , getFieldSetMapperByDomainClass(Cliente.class) ); 
        fieldSetMappers.put("1*" , getFieldSetMapperByDomainClass(Transacao.class) );
        return fieldSetMappers;
    }

    private FieldSetMapper getFieldSetMapperByDomainClass(Class domainClass) {
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper();
        fieldSetMapper.setTargetType(domainClass);
        return fieldSetMapper;
    }


    private Map<String, LineTokenizer> setupTokenizers() {
        Map<String, LineTokenizer> tokenizers = new HashMap<>();
        tokenizers.put("0*", clienteLineTokenizer()); // Pattern to identify registers on file related with 'cliente' data (starts with '0' ) ;
        tokenizers.put("1*", transacaoLineTokenizer()); // Pattern of registers on file related with 'transacoes' data (starts with '1' ) ;

        return tokenizers;
    }

    private LineTokenizer clienteLineTokenizer() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(); // Type of 'read' on datasource file

        // Setting properties and position for each item from datasource file
        tokenizer.setNames("nome", "sobrenome", "idade", "email");
        tokenizer.setIncludedFields(1,2,3,4); // Include fields from file starting on index 1 to ignore first column , because this one is used as tokenizer identifier (0 to cliente and 1 for transacao)

        return tokenizer;
    }

    private LineTokenizer transacaoLineTokenizer() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(); // Type of 'read' on datasource file

        tokenizer.setNames("id", "descricao","valor");
        tokenizer.setIncludedFields(1,2,3); // Include fields from file starting on index 1 to ignore first column , because this one is used as tokenizer identifier (0 to cliente and 1 for transacao)

        return tokenizer;

    }
}
