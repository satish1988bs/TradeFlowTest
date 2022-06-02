package com.sbs.app;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbs.app.trade.controller.TradeFlowController;
import com.sbs.app.trade.exceptions.InvalidTradeException;
import com.sbs.app.trade.model.Trade;
import com.sbs.app.trade.model.TradeKey;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TradeFlowApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private TradeFlowController tradeController;

	@Test
	void testTradeValidateAndSaveTradeSuccessfully() {
		ResponseEntity responseEntity = tradeController.saveTradeToStore(createTrade("T1",1,LocalDate.now()));
		Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build(),responseEntity);
		List<Trade> tradeList =tradeController.findAllTrades();
		Assertions.assertEquals(1, tradeList.size());
		Assertions.assertEquals("T1",tradeList.get(0).getTradeKey().getTradeId());
	}

	@Test
	void testTradeValidateAndSaveWhenMaturityDatePasses() {
		try {
			LocalDate localDate = getLocalDate(2015, 05, 21);
			ResponseEntity responseEntity = tradeController.saveTradeToStore(createTrade("T2", 1, localDate));
		}catch (InvalidTradeException ie) {
			Assertions.assertEquals("Invalid trade id provided T2", ie.getMessage());
		}
	}

	@Test
	void testTradeValidateAndSaveWhenOldTraversionReceived() {
		tradeController.clearTrades();
		// step-1 create trade
		ResponseEntity responseEntity = tradeController.saveTradeToStore(createTrade("T1",2,LocalDate.now()));
		Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build(),responseEntity);
		List<Trade> tradeList =tradeController.findAllTrades();
		Assertions.assertEquals(1, tradeList.size());
		Assertions.assertEquals("T1",tradeList.get(0).getTradeKey().getTradeId());
		Assertions.assertEquals(2,tradeList.get(0).getTradeKey().getVersion());
		Assertions.assertEquals("T1B1",tradeList.get(0).getBookId());

		//step-2 create trade with old version
		try {
			ResponseEntity responseEntity1 = tradeController.saveTradeToStore(createTrade("T1", 1, LocalDate.now()));


		}catch (InvalidTradeException e){
			System.out.println(e.getTradeId());
			System.out.println(e.getMessage());
		}
		List<Trade> tradeList1 =tradeController.findAllTrades();
		Assertions.assertEquals(1, tradeList1.size());
		Assertions.assertEquals("T1",tradeList1.get(0).getTradeKey().getTradeId());
		Assertions.assertEquals(2,tradeList1.get(0).getTradeKey().getVersion());
		Assertions.assertEquals("T1B1",tradeList.get(0).getBookId());
	}

	@Test
	void testTradeValidateAndSaveWhenTradeHasSameVersion(){
		tradeController.clearTrades();
		ResponseEntity responseEntity = tradeController.saveTradeToStore(createTrade("T1",2,LocalDate.now()));
		Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build(),responseEntity);
		List<Trade> tradeList =tradeController.findAllTrades();
		Assertions.assertEquals(1, tradeList.size());
		Assertions.assertEquals("T1",tradeList.get(0).getTradeKey().getTradeId());
		Assertions.assertEquals(2,tradeList.get(0).getTradeKey().getVersion());
		Assertions.assertEquals("T1B1",tradeList.get(0).getBookId());

		//step-2 create trade with same version
		Trade trade2 = createTrade("T1",2,LocalDate.now());
		trade2.setBookId("T1B1V2");
		ResponseEntity responseEntity2 = tradeController.saveTradeToStore(trade2);
		Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build(),responseEntity2);
		List<Trade> tradeList2 =tradeController.findAllTrades();
		Assertions.assertEquals(1, tradeList2.size());
		Assertions.assertEquals("T1",tradeList2.get(0).getTradeKey().getTradeId());
		Assertions.assertEquals(2,tradeList2.get(0).getTradeKey().getVersion());
		Assertions.assertEquals("T1B1V2",tradeList2.get(0).getBookId());

		//step-2 create trade with new version
		Trade trade3 = createTrade("T1",2,LocalDate.now());
		trade3.setBookId("T1B1V3");
		ResponseEntity responseEntity3 = tradeController.saveTradeToStore(trade3);
		Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build(),responseEntity3);
		List<Trade> tradeList3 =tradeController.findAllTrades();
		Assertions.assertEquals(1, tradeList3.size());
		Assertions.assertEquals("T1",tradeList3.get(0).getTradeKey().getTradeId());
		Assertions.assertEquals(2,tradeList3.get(0).getTradeKey().getVersion());
		Assertions.assertEquals("T1B1V3",tradeList3.get(0).getBookId());

	}
	
	private static Trade createTrade(String tradeId,int version,LocalDate  maturityDate){
		Trade trade = new Trade();
		TradeKey tradeKey=new TradeKey(tradeId, version);
		trade.setTradeKey(tradeKey);
		trade.setBookId(tradeId+"B1");
		trade.setCounterPartyId(tradeId+"Cpty");
		trade.setMaturityDate(maturityDate);
		trade.setExpired("Y");
		return trade;
	}

	public static LocalDate getLocalDate(int year,int month, int day){
		LocalDate localDate = LocalDate.of(year,month,day);
		return localDate;
	}
	/*
	 * public static void main(String[] args) throws JsonProcessingException { Trade
	 * createTrade = createTrade("T1",2,LocalDate.now()); String mapper=new
	 * ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(
	 * createTrade); System.out.println(mapper); }
	 */



}
