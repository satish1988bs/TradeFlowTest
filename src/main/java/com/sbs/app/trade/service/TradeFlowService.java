package com.sbs.app.trade.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.app.trade.model.Trade;

@Service
public interface TradeFlowService {
	void saveTrade(Trade trade);

	List<Trade> findAll();

	boolean isValid(Trade trade);

	void updateTradeExpirationFlag();

	void clearTrades();

}
