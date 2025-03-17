package br.com.dantas.springbatchv5.processor;

import br.com.dantas.springbatchv5.domain.Cliente;
import br.com.dantas.springbatchv5.domain.FaturaCartaoCredito;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ConsultaDadosClienteItemProcessor implements ItemProcessor<FaturaCartaoCredito,FaturaCartaoCredito> {

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public FaturaCartaoCredito process(FaturaCartaoCredito faturaCartaoCredito) throws Exception {
        String uri = String.format("https://my-json-server.typicode.com/giuliana-bezerra/demo/profile/%d",
                faturaCartaoCredito.getCliente().getId());

        ResponseEntity<Cliente> response = restTemplate.getForEntity(uri, Cliente.class);
        faturaCartaoCredito.setCliente(response.getBody());
        return faturaCartaoCredito;
    }
}
