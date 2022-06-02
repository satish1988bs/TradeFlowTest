package com.sbs.app.trade.jobs;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sbs.app.trade.service.TradeFlowService;

@Component
public class TradeExpiryJob {
	private static final Logger log = LoggerFactory.getLogger(TradeExpiryJob.class);
	
	@Value("#{new Boolean('${trigger.trade.expiry.update.job.enabled}')}")
//	@Value("trigger.trade.expiry.update.job.enabled")
	private Boolean isJobEnabled; 
	@Autowired
	TradeFlowService tradeFlowService;
	
	@Scheduled(cron = "${trigger.trade.expiry.update.cron.expression}")
	public void triggerJob() {
		log.info("is {} cron job enabled?={}", this.getClass().getName(),isJobEnabled);
		if(this.isJobEnabled) {
			log.info("Starting cron job {} Current system time is {}", this.getClass().getName(),LocalDateTime.now());
			tradeFlowService.updateTradeExpirationFlag();
			
		}
	}

}
