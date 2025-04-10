package br.com.dantas.springbatchv5.processor;

import br.com.dantas.springbatchv5.domain.InteresseClienteProduto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class ProcessaEmailClienteProdutoProcessorConfig implements ItemProcessor<InteresseClienteProduto, SimpleMailMessage> {

    @Override
    public SimpleMailMessage process(InteresseClienteProduto interesseClienteProduto) throws Exception {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("dantas@no-reply.com");
        mailMessage.setTo(interesseClienteProduto.getCliente().getEmail());
        mailMessage.setSubject("We got a awesome sales for you !!!");
        mailMessage.setText("""
                SOME MARKTING TEXT ABOUT PRODUCT >>>> ${interesseClienteProduto.getProduto().getDescricao()} <<<<\s
                AND IT PRICE  >>>> R$ ${interesseClienteProduto.getProduto().getPreco()} <<<<\s
               \s""");

        return mailMessage;
    }
}
