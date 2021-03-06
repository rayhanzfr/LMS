package com.lawencon.lms.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lawencon.lms.service.TransactionsDetailOutService;

@Component
public class Scheduler {
	
	@Autowired
	private TransactionsDetailOutService transactionsDetailOutService;
	
	@Scheduled(cron = "0 0 10 * * *")
	public void expiredAssetsReminder() throws Exception{
		transactionsDetailOutService.sendReminder();
	}
}
