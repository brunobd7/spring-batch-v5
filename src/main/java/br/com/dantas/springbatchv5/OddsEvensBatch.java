package br.com.dantas.springbatchv5;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.List;

/**
 * The annotation @EnableBatchProcessing is not required since Spring Boot 3
 */
@Configuration
public class OddsEvensBatch {

    @Bean
    public Job job(JobRepository jobRepository, Step printOddsEvensStep) {
        return new JobBuilder("oddsEvensJob", jobRepository)
                .start(printOddsEvensStep)
                .incrementer(new RunIdIncrementer()) // INCREMENTED TO ALLOWS BATCH RUNS MANY TIMES AND COULD INDENTIFY ON DATABASE
                .build();
    }

    /**
     * <b>Chuck Based Step:</b>
     * <p></p>
     *  The COMMIT INTERVAL equals CHUCK SIZE is a sensible definition, a large size equals a fewer transactions
     *  but consumes more memory and has more chances to lost data if something goes wrong on data processing steps.
     * */
    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("printOddsEvensStep", jobRepository)
                .<Integer,String>chunk(10, transactionManager)
                .reader(countUntilTenItemReader())
                .processor(oddEvenItemProcessor())
                .writer(printResultItemWritter())
                .build();
    }


    /**
     * The item reader receives a collection
     * */
    public IteratorItemReader<Integer> countUntilTenItemReader(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        return new IteratorItemReader<>(numbers.iterator());
    }

    /**
     * The item processor receives an item of reader collection per time
     */
    public FunctionItemProcessor<Integer,String> oddEvenItemProcessor(){
        return new FunctionItemProcessor<Integer,String>(
                number -> number % 2 == 0
                        ? String.format("Item %s is ODD", number)
                        : String.format("Item %s is EVEN", number));
    }

    /**
     * The item writter receives a collection proccessed by item processor
     */
    public ItemWriter<String> printResultItemWritter() {
        return chunk -> chunk.forEach(System.out::println);
    }
}
