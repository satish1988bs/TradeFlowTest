package com.sbs.app.trade.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sbs.app.trade.dao.TradeRepo;
import com.sbs.app.trade.model.Trade;

@Service
public class TradeFlowServiceImpl implements TradeFlowService {
	private static final Logger log = LoggerFactory.getLogger(TradeFlowServiceImpl.class);
	@Autowired
	TradeRepo tradeRepo;

	@Override
	public void saveTrade(Trade trade) {
		trade.setCreatedDate(LocalDateTime.now());
		tradeRepo.save(trade);

	}

	@Override
	public List<Trade> findAll() {
		return tradeRepo.findAll();
	}
	@Override
	public void clearTrades() {
		tradeRepo.deleteAll();
	}

	public boolean isValid(Trade trade) {
		if (isMaturityDateValid(trade)) {
			List<Trade> existingTrade = tradeRepo.getTradeListById(trade.getTradeKey().getTradeId(),Sort.by("tradeKey.version").descending());
			if (!existingTrade.isEmpty()) {
				return validateVersion(trade, existingTrade.get(0));
			} else {
				return true;
			}
		}
		return false;
	}

	private boolean validateVersion(Trade trade, Trade oldTrade) {
		// validation 1 During transmission if the
		// lower version is being received by the store it will reject the trade and
		// throw an exception.
		if (trade.getTradeKey().getVersion() >= oldTrade.getTradeKey().getVersion()) {
			return true;
		}
		return false;
	}

	private boolean isMaturityDateValid(Trade trade) {
		return trade.getMaturityDate().isBefore(LocalDate.now()) ? false : true;
	}

	@Override
	public void updateTradeExpirationFlag() {
		tradeRepo.findAll().stream().forEach(t -> {
			if (!isMaturityDateValid(t)) {
				t.setExpired("Y");
				log.info("Updated trade is {}", t);
				tradeRepo.save(t);
			}
		});
	}

}
