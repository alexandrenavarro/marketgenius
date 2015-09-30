package com.github.marketgenius.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.joda.time.DateTime;

/**
 * @author smillar
 *
 */
public final class Feed extends ModelBase implements Comparable<Feed> {
	
	

	
	
	private long feedId;
	private String feedCode;
	private String marketCode;
	//private DateTime timestamp;

	private Integer qtyAsk1;
//	private Integer qtyAsk2;
//	private Integer qtyAsk3;
//	private Integer qtyAsk4;
//	private Integer qtyAsk5;
//	private Integer qtyAsk6;
//	private Integer qtyAsk7;
//	private Integer qtyAsk8;
//	private Integer qtyAsk9;
//	private Integer qtyAsk10;
	
	private Integer qtyBid1;
//	private Integer qtyBid2;
//	private Integer qtyBid3;
//	private Integer qtyBid4;
//	private Integer qtyBid5;
//	private Integer qtyBid6;
//	private Integer qtyBid7;
//	private Integer qtyBid8;
//	private Integer qtyBid9;
//	private Integer qtyBid10;
	
	private Double ask1;
//	private Double ask2;
//	private Double ask3;
//	private Double ask4;
//	private Double ask5;
//	private Double ask6;
//	private Double ask7;
//	private Double ask8;
//	private Double ask9;
//	private Double ask10;
	
	private Double bid1;
//	private Double bid2;
//	private Double bid3;
//	private Double bid4;
//	private Double bid5;
//	private Double bid6;
//	private Double bid7;
//	private Double bid8;
//	private Double bid9;
//	private Double bid10;
	
//	private Integer qtyLast;
//	private Double last;
	

	public int compareTo(Feed o) {
		return o.compareTo(this);
	};
	
	public Feed(DateTime timestamp) {
		super(Feed.class.getSimpleName(), timestamp);
		// TODO Auto-generated constructor stub
	}







	public long getFeedId() {
		return feedId;
	}



	public String getFeedCode() {
		return feedCode;
	}



	public void setFeedCode(String feedCode) {
		this.feedCode = feedCode;
	}



	public String getMarketCode() {
		return marketCode;
	}



	public void setMarketCode(String market) {
		this.marketCode = market;
	}



//	public DateTime getTimestamp() {
//		return timestamp;
//	}
//
//
//
//	public void setTimestamp(DateTime timestamp) {
//		this.timestamp = timestamp;
//	}



	public Integer getQtyAsk1() {
		return qtyAsk1;
	}



	public void setQtyAsk1(Integer qtyAsk1) {
		this.qtyAsk1 = qtyAsk1;
	}



	public Integer getQtyBid1() {
		return qtyBid1;
	}



	public void setQtyBid1(Integer qtyBid1) {
		this.qtyBid1 = qtyBid1;
	}



	public Double getAsk1() {
		return ask1;
	}



	public void setAsk1(Double ask1) {
		this.ask1 = ask1;
	}



	public Double getBid1() {
		return bid1;
	}



	public void setBid1(Double bid1) {
		this.bid1 = bid1;
	}



//	public Integer getQtyLast() {
//		return qtyLast;
//	}
//
//
//
//	public void setQtyLast(Integer qtyLast) {
//		this.qtyLast = qtyLast;
//	}
//
//
//
//	public Double getLast() {
//		return last;
//	}
//
//
//
//	public void setLast(Double last) {
//		this.last = last;
//	}



	public void setFeedId(long feedId) {
		this.feedId = feedId;
	}


}
