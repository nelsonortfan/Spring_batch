package com.example.springbatch.config;

import com.example.springbatch.task.SimpleTask;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SimpleTaskConfig {
    @Autowired
    private SimpleTask simpleTask;
    private final String SIMPLE_JOB = "Simple job of Nelson";
    private final String SIMPLE_TASK = "Simple task of mine";
    @Bean
    public Job simpleTaskJob(JobRepository jobRepository, Step simpleTaskStep){
        return new JobBuilder(SIMPLE_JOB,jobRepository)
                .start(simpleTaskStep)
                .build();
    }
    @Bean
    public Step simpleTaskStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder(SIMPLE_TASK, jobRepository)
                .tasklet(simpleTask, platformTransactionManager)
                .allowStartIfComplete(true)
                .build();
    }
}
