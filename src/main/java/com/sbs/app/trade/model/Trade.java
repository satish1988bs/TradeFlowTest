package com.sbs.app.trade.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
@Entity
@Table(name = "Trade")
public class Trade implements Serializable {

//    @Id
//    private String tradeId;
//
//    private int version;
	@EmbeddedId
	private TradeKey tradeKey;
    private String counterPartyId;

    private String bookId;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate maturityDate;

    private LocalDateTime createdDate;

    private String expired;

	/*
	 * public String getTradeId() { return tradeId; }
	 * 
	 * 
	 * 
	 * public void setTradeId(String tradeId) { this.tradeId = tradeId; }
	 * 
	 * 
	 * 
	 * public int getVersion() { return version; }
	 * 
	 * 
	 * 
	 * public void setVersion(int version) { this.version = version; }
	 */


	public String getCounterPartyId() {
		return counterPartyId;
	}



	public TradeKey getTradeKey() {
		return tradeKey;
	}



	public void setTradeKey(TradeKey tradeKey) {
		this.tradeKey = tradeKey;
	}



	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}



	public String getBookId() {
		return bookId;
	}



	public void setBookId(String bookId) {
		this.bookId = bookId;
	}



	public LocalDate getMaturityDate() {
		return maturityDate;
	}



	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getExpired() {
		return expired;
	}



	public void setExpired(String expired) {
		this.expired = expired;
	}

	@Override
	public String toString() {
		return "Trade [tradeKey=" + tradeKey + ", counterPartyId=" + counterPartyId + ", bookId=" + bookId
				+ ", maturityDate=" + maturityDate + ", createdDate=" + createdDate + ", expired=" + expired + "]";
	}




}
