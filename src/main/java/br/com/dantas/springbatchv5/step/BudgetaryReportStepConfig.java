package br.com.dantas.springbatchv5.step;

import br.com.dantas.springbatchv5.domain.FinancialTransaction;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BudgetaryReportStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    public BudgetaryReportStepConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    public Step budgetaryReportStep(JdbcCursorItemReader<FinancialTransaction> itemReader, ItemWriter<FinancialTransaction> itemWriter) {
        return new StepBuilder("budgetaryReportStep", jobRepository )
                .<FinancialTransaction, FinancialTransaction>chunk(20,platformTransactionManager)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }


}
