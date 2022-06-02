package com.sbs.app.trade.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sbs.app.trade.model.Trade;
import com.sbs.app.trade.model.TradeKey;

public interface TradeRepo extends JpaRepository<Trade, TradeKey> {
	
	@Query(nativeQuery = false, value = "SELECT "
            + "n from Trade n where n.tradeKey.tradeId=?1 and n.tradeKey.version=?2")
    List<Trade> getTradeListById(String id,int version,Sort sort);
	@Query(nativeQuery = false, value = "SELECT "
            + "n from Trade n where n.tradeKey.tradeId=?1")
    List<Trade> getTradeListById(String id,Sort sort);
}
