package com.sbs.app.trade.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
@Embeddable
public class TradeKey implements Serializable {

    private String tradeId;

    private int version;

	public TradeKey(String tradeId, int version) {
		super();
		this.tradeId = tradeId;
		this.version = version;
	}
	

	public TradeKey() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tradeId, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TradeKey other = (TradeKey) obj;
		return Objects.equals(tradeId, other.tradeId) && version == other.version;
	}

	@Override
	public String toString() {
		return "TradeId [tradeId=" + tradeId + ", version=" + version + "]";
	}
}