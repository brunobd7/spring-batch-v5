package br.com.dantas.springbatchv5.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class OddsEvensStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public OddsEvensStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    /**
     * <b>Chuck Based Step:</b>
     * <p></p>
     *  The COMMIT INTERVAL equals CHUCK SIZE is a sensible definition, a large size equals a fewer transactions
     *  but consumes more memory and has more chances to lost data if something goes wrong on data processing steps.
     * */
    @Bean
    public Step step(ItemReader<Integer> itemReader, ItemProcessor<Integer, String> itemProcessor, ItemWriter<String> itemWriter) {
        return new StepBuilder("printOddsEvensStep", jobRepository)
                .<Integer,String>chunk(10, transactionManager)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }
}
