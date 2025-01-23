package com.example.springbatch.config;

import com.example.springbatch.service.SecondTasklet;
import com.example.springbatch.task.SimpleTask;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class SimpleTaskConfig {
    @Autowired
    private SimpleTask simpleTask;

    @Autowired
    private SecondTasklet secondTasklet;
    private final String SIMPLE_JOB = "Simple job of Nelson";
    private final String SIMPLE_TASK = "Simple task of mine";

    private final String SECOND_TASK = "Second simple task of mine";

    @Value("${batchDatasource.driver}")
    private String batchDatasourceDriver;
    @Value("${batchDatasource.url}")
    private String batchDatasourceUrl;
    @Value("${batchDatasource.username}")
    private String batchDatasourceUsername;
    @Value("${batchDatasource.password}")
    private String batchDatasourcePassword;
    @Bean
    public Job simpleTaskJob(JobRepository jobRepository, Step simpleTaskStep, Step secondTaskStep){
        return new JobBuilder(SIMPLE_JOB,jobRepository)
               .start(simpleTaskStep)
               .next(secondTaskStep)
               .build();
    }
    @Bean
    public Step simpleTaskStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder(SIMPLE_TASK, jobRepository)
                .tasklet(simpleTask, platformTransactionManager)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step secondTaskStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder(SECOND_TASK, jobRepository)
                .tasklet(secondTasklet, platformTransactionManager)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    @BatchDataSource
    // @BatchDataSource specifies that this bean will be used as the
    // data source for the ItemReader during batch processing
    public DataSource batchDatasource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(batchDatasourceDriver);
        config.setJdbcUrl(batchDatasourceUrl);
        config.setUsername(batchDatasourceUsername);
        config.setPassword(batchDatasourcePassword);
        return new HikariDataSource(config);
    }

}
