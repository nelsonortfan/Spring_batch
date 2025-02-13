package com.example.springbatch.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class SimpleTask implements Tasklet {


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Iniciando mi job Nelson");
        System.out.println("------");
        System.out.println("------");
        System.out.println("------");
        System.out.println("Finalizando mi job Nelson");
        return RepeatStatus.FINISHED;
    }
}
