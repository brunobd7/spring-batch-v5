package br.com.dantas.springbatchv5.step;

import br.com.dantas.springbatchv5.domain.InteresseClienteProduto;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SendEmailCustomerStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    public SendEmailCustomerStepConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    public Step sendEmailCustomerStep(ItemReader<InteresseClienteProduto> interesseClienteProdutoReader,
                                      ItemProcessor<InteresseClienteProduto, SimpleMailMessage> processaEmailClienteProdutoProcessor,
                                      ItemWriter<SimpleMailMessage> enviarEmailClienteProdutoWriter) {
        return new StepBuilder("sendEmailCustomerStep", jobRepository)
                .<InteresseClienteProduto,SimpleMailMessage>chunk(1, platformTransactionManager)
                .reader(interesseClienteProdutoReader)
                .processor(processaEmailClienteProdutoProcessor)
                .writer(enviarEmailClienteProdutoWriter)
                .build();
    }
}
