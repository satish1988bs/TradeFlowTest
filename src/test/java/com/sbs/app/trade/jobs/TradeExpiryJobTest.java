package com.sbs.app.trade.jobs;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.sbs.app.TradeFlowApplication;

@SpringJUnitConfig(TradeFlowApplication.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TradeExpiryJobTest {

	@SpyBean
	private TradeExpiryJob tradeExpiryJob;

	@Test
	public void TestJobTriggered() {
		await().atMost(Duration.ONE_MINUTE).untilAsserted(() -> verify(tradeExpiryJob, atLeast(2)).triggerJob());
	}

}