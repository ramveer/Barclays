package com.store;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.store.dto.TradesRepository;

@Component
public class Scheduler {
	@Autowired
	TradesRepository repo;
	
	//run cron job at every night at 12:01 AM 
	 @Scheduled(cron = "0 01 0 * * *") 
	//@Scheduled(cron = "0 10 15 * * *")
	public void run() {
	  System.out.println("Current time is :: " + Calendar.getInstance().getTime());
	  repo.updateExpiredByMaturityDate(new Date());
	}
}
