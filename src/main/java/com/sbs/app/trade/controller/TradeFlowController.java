package com.sbs.app.trade.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sbs.app.trade.model.Trade;

public interface TradeFlowController {

	List<Trade> findAllTrades();

	ResponseEntity<String> saveTradeToStore(Trade trade);

	ResponseEntity<Object> clearTrades();

}
