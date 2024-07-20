package br.com.dantas.springbatchv5.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PrintSomethingTasklet implements Tasklet {

    @Value("#{jobParameters['parameterToMyBatch']}")
    String parameterToBatchTasklet;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println(String.format("Spring batch job -> %s", parameterToBatchTasklet));
        return RepeatStatus.FINISHED;
    }
}
