package com.example.springbatch.service;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Service;

@Service
public class SecondTasklet implements Tasklet {


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Iniciando mi  segundo job Nelson");
        System.out.println("------");
        System.out.println("------");
        System.out.println("------");
        System.out.println("Finalizando mi segundo job Nelson");
        return RepeatStatus.FINISHED;
    }
}
