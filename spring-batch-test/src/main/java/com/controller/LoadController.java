package com.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/load")
public class LoadController {
    
    @Autowired
    JobLauncher jobLauncher;
    
    @Autowired
    Job job;
    
    @GetMapping
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> nameToJobParameter = new HashMap<>();
        nameToJobParameter.put("time", new JobParameter(System.currentTimeMillis() ));
        JobParameters jobParameters = new JobParameters(nameToJobParameter);

       JobExecution jobExecution = jobLauncher.run(job, jobParameters);
       return jobExecution.getStatus();
    }
    
}
