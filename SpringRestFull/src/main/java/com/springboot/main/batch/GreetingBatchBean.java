package com.springboot.main.batch;


import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springboot.main.model.Greeting;
import com.springboot.main.service.GreetingService;

@Component
public class GreetingBatchBean {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GreetingService greetingService;
	
	//@Scheduled(cron = "0,30 * * * * *")
	public void cronJob() {
		logger.info("> clonejob");
		Collection<Greeting> greetings = greetingService.findAll();
		logger.info("There are {} greetings in the data store.",greetings.size());
		
		logger.info("< clonejob");
	}
	
	//@Scheduled(initialDelay = 5000, fixedRate = 15000)
	public void fixedRateJobWithInitialDelay(){
		logger.info("> fixedRateJobWithInitialDelay");
		
		//add scheduled logic here
		//simulate job processing time
		long pause = 5000;
		long start = System.currentTimeMillis();
		do{
			if(start + pause < System.currentTimeMillis()){
				break;
			}
		}while(true);
		logger.info("Processing tiem was {} seconds.", pause/1000);
		logger.info("< fixedRateJobWithInitialDelay");
	}
	
	@Scheduled(initialDelay = 5000, fixedDelay = 15000)
	public void fixedDelayJobWithInitialDelay(){
		logger.info("> fixedRateJobWithInitialDelay");
		
		//add scheduled logic here
		//simulate job processing time
		long pause = 5000;
		long start = System.currentTimeMillis();
		do{
			if(start + pause < System.currentTimeMillis()){
				break;
			}
		}while(true);
		logger.info("Processing tiem was {} seconds.", pause/1000);
		logger.info("< fixedRateJobWithInitialDelay");
	}
}
