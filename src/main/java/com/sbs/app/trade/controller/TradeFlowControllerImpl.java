package com.sbs.app.trade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbs.app.trade.exceptions.Errors;
import com.sbs.app.trade.exceptions.InvalidTradeException;
import com.sbs.app.trade.model.Trade;
import com.sbs.app.trade.service.TradeFlowService;
@RestController
//@RequestMapping("/api")
public class TradeFlowControllerImpl implements TradeFlowController{
	
	@Autowired
	TradeFlowService tradeFlowService;
	@Override
	@GetMapping("/trades")
    public List<Trade> findAllTrades(){
        return tradeFlowService.findAll();
    }
	@Override
	@PostMapping(value = "/savetrade",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveTradeToStore(@RequestBody Trade trade){
       if(tradeFlowService.isValid(trade)) {
    	   tradeFlowService.saveTrade(trade);
       }else{
          // return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
           throw new InvalidTradeException(trade.getTradeKey().getTradeId(),Errors.TRADE_ID_INVALID);
       }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
	@Override
	@GetMapping("/clearTrades")
    public ResponseEntity<Object> clearTrades(){
        tradeFlowService.clearTrades();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
	
	
}
