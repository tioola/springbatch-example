package com.example.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
						  .tasklet((stepContribution, chunckContext) -> {
							 System.out.println("step1 testing");
							 return RepeatStatus.FINISHED; 
						  }).build();
	}
	
	
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
				  .tasklet((stepContribution, chunckContext) -> {
					 System.out.println("step2 testing");
					 return RepeatStatus.FINISHED; 
				  }).build();
	}
	
	
	@Bean
	public Step step3() {
		return stepBuilderFactory.get("readWriteStep")
				.<String,String>chunk(1)
				.reader(new JobReader())
				.writer(list -> {
					for (String string : list) {
						System.out.println("Item to show=" + string);
					}
				}).build();
	}
	
	@Bean
	public Job flowFirstJob() {
		return jobBuilderFactory.get("flowFirstJob")
				.start(step1())
				.on("COMPLETED").to(step2())
				.on("COMPLETED").to(step3())
				.end()
				.build();
	}
	
	
	
}
