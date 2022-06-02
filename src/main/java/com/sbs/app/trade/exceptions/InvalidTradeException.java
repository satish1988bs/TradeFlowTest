package com.sbs.app.trade.exceptions;

public class InvalidTradeException extends RuntimeException {
	
	private static final long serialVersionUID = -6365955458964161464L;
	private String tradeId;

	public InvalidTradeException(String tradeId,String errorMessage, Throwable cause) {
		super(errorMessage, cause);
		this.tradeId=tradeId;
	}

	public InvalidTradeException(String tradeId, Errors error) {
		super(error.message+" "+tradeId);
		this.tradeId=tradeId;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	
	
}
