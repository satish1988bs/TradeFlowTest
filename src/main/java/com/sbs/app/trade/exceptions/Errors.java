package com.sbs.app.trade.exceptions;

public enum Errors {
	TRADE_ID_NOT_PRESENT("Trade id not present"),
	TRADE_ID_INVALID("Invalid trade id provided");
	
	String message;
	Errors(String msg) {
	this.message=msg;
	}

}
