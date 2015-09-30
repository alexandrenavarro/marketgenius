package com.github.marketgenius;

import java.time.LocalDateTime;

import com.google.gson.Gson;

import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * @author smillar
 *
 */
@GeneratePojoBuilder(withFactoryMethod="create", withSetterNamePattern="*")
public final class Feed {
	
	private long feedId;
	private String feedCode;
	private String market;
	//private LocalDateTime timestamp;
	private long timestamp;

	private Integer qtyAsk1;
	private Integer qtyAsk2;
	private Integer qtyAsk3;
	private Integer qtyAsk4;
	private Integer qtyAsk5;
	private Integer qtyAsk6;
	private Integer qtyAsk7;
	private Integer qtyAsk8;
	private Integer qtyAsk9;
	private Integer qtyAsk10;
	
	private Integer qtyBid1;
	private Integer qtyBid2;
	private Integer qtyBid3;
	private Integer qtyBid4;
	private Integer qtyBid5;
	private Integer qtyBid6;
	private Integer qtyBid7;
	private Integer qtyBid8;
	private Integer qtyBid9;
	private Integer qtyBid10;
	
	private Double ask1;
	private Double ask2;
	private Double ask3;
	private Double ask4;
	private Double ask5;
	private Double ask6;
	private Double ask7;
	private Double ask8;
	private Double ask9;
	private Double ask10;
	
	private Double bid1;
	private Double bid2;
	private Double bid3;
	private Double bid4;
	private Double bid5;
	private Double bid6;
	private Double bid7;
	private Double bid8;
	private Double bid9;
	private Double bid10;
	
	private Integer qtyLast;
	private Double last;
	
	
	
	@Override
	public String toString() {
		final Gson gson = new Gson();
		return gson.toJson(this);
	}



	public long getFeedId() {
		return feedId;
	}



	public void setFeedId(long feedId) {
		this.feedId = feedId;
	}



	public String getFeedCode() {
		return feedCode;
	}



	public void setFeedCode(String feedCode) {
		this.feedCode = feedCode;
	}



	public String getMarket() {
		return market;
	}



	public void setMarket(String market) {
		this.market = market;
	}



	public long getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}



	public Integer getQtyAsk1() {
		return qtyAsk1;
	}



	public void setQtyAsk1(Integer qtyAsk1) {
		this.qtyAsk1 = qtyAsk1;
	}



	public Integer getQtyAsk2() {
		return qtyAsk2;
	}



	public void setQtyAsk2(Integer qtyAsk2) {
		this.qtyAsk2 = qtyAsk2;
	}



	public Integer getQtyAsk3() {
		return qtyAsk3;
	}



	public void setQtyAsk3(Integer qtyAsk3) {
		this.qtyAsk3 = qtyAsk3;
	}



	public Integer getQtyAsk4() {
		return qtyAsk4;
	}



	public void setQtyAsk4(Integer qtyAsk4) {
		this.qtyAsk4 = qtyAsk4;
	}



	public Integer getQtyAsk5() {
		return qtyAsk5;
	}



	public void setQtyAsk5(Integer qtyAsk5) {
		this.qtyAsk5 = qtyAsk5;
	}



	public Integer getQtyAsk6() {
		return qtyAsk6;
	}



	public void setQtyAsk6(Integer qtyAsk6) {
		this.qtyAsk6 = qtyAsk6;
	}



	public Integer getQtyAsk7() {
		return qtyAsk7;
	}



	public void setQtyAsk7(Integer qtyAsk7) {
		this.qtyAsk7 = qtyAsk7;
	}



	public Integer getQtyAsk8() {
		return qtyAsk8;
	}



	public void setQtyAsk8(Integer qtyAsk8) {
		this.qtyAsk8 = qtyAsk8;
	}



	public Integer getQtyAsk9() {
		return qtyAsk9;
	}



	public void setQtyAsk9(Integer qtyAsk9) {
		this.qtyAsk9 = qtyAsk9;
	}



	public Integer getQtyAsk10() {
		return qtyAsk10;
	}



	public void setQtyAsk10(Integer qtyAsk10) {
		this.qtyAsk10 = qtyAsk10;
	}



	public Integer getQtyBid1() {
		return qtyBid1;
	}



	public void setQtyBid1(Integer qtyBid1) {
		this.qtyBid1 = qtyBid1;
	}



	public Integer getQtyBid2() {
		return qtyBid2;
	}



	public void setQtyBid2(Integer qtyBid2) {
		this.qtyBid2 = qtyBid2;
	}



	public Integer getQtyBid3() {
		return qtyBid3;
	}



	public void setQtyBid3(Integer qtyBid3) {
		this.qtyBid3 = qtyBid3;
	}



	public Integer getQtyBid4() {
		return qtyBid4;
	}



	public void setQtyBid4(Integer qtyBid4) {
		this.qtyBid4 = qtyBid4;
	}



	public Integer getQtyBid5() {
		return qtyBid5;
	}



	public void setQtyBid5(Integer qtyBid5) {
		this.qtyBid5 = qtyBid5;
	}



	public Integer getQtyBid6() {
		return qtyBid6;
	}



	public void setQtyBid6(Integer qtyBid6) {
		this.qtyBid6 = qtyBid6;
	}



	public Integer getQtyBid7() {
		return qtyBid7;
	}



	public void setQtyBid7(Integer qtyBid7) {
		this.qtyBid7 = qtyBid7;
	}



	public Integer getQtyBid8() {
		return qtyBid8;
	}



	public void setQtyBid8(Integer qtyBid8) {
		this.qtyBid8 = qtyBid8;
	}



	public Integer getQtyBid9() {
		return qtyBid9;
	}



	public void setQtyBid9(Integer qtyBid9) {
		this.qtyBid9 = qtyBid9;
	}



	public Integer getQtyBid10() {
		return qtyBid10;
	}



	public void setQtyBid10(Integer qtyBid10) {
		this.qtyBid10 = qtyBid10;
	}



	public Double getAsk1() {
		return ask1;
	}



	public void setAsk1(Double ask1) {
		this.ask1 = ask1;
	}



	public Double getAsk2() {
		return ask2;
	}



	public void setAsk2(Double ask2) {
		this.ask2 = ask2;
	}



	public Double getAsk3() {
		return ask3;
	}



	public void setAsk3(Double ask3) {
		this.ask3 = ask3;
	}



	public Double getAsk4() {
		return ask4;
	}



	public void setAsk4(Double ask4) {
		this.ask4 = ask4;
	}



	public Double getAsk5() {
		return ask5;
	}



	public void setAsk5(Double ask5) {
		this.ask5 = ask5;
	}



	public Double getAsk6() {
		return ask6;
	}



	public void setAsk6(Double ask6) {
		this.ask6 = ask6;
	}



	public Double getAsk7() {
		return ask7;
	}



	public void setAsk7(Double ask7) {
		this.ask7 = ask7;
	}



	public Double getAsk8() {
		return ask8;
	}



	public void setAsk8(Double ask8) {
		this.ask8 = ask8;
	}



	public Double getAsk9() {
		return ask9;
	}



	public void setAsk9(Double ask9) {
		this.ask9 = ask9;
	}



	public Double getAsk10() {
		return ask10;
	}



	public void setAsk10(Double ask10) {
		this.ask10 = ask10;
	}



	public Double getBid1() {
		return bid1;
	}



	public void setBid1(Double bid1) {
		this.bid1 = bid1;
	}



	public Double getBid2() {
		return bid2;
	}



	public void setBid2(Double bid2) {
		this.bid2 = bid2;
	}



	public Double getBid3() {
		return bid3;
	}



	public void setBid3(Double bid3) {
		this.bid3 = bid3;
	}



	public Double getBid4() {
		return bid4;
	}



	public void setBid4(Double bid4) {
		this.bid4 = bid4;
	}



	public Double getBid5() {
		return bid5;
	}



	public void setBid5(Double bid5) {
		this.bid5 = bid5;
	}



	public Double getBid6() {
		return bid6;
	}



	public void setBid6(Double bid6) {
		this.bid6 = bid6;
	}



	public Double getBid7() {
		return bid7;
	}



	public void setBid7(Double bid7) {
		this.bid7 = bid7;
	}



	public Double getBid8() {
		return bid8;
	}



	public void setBid8(Double bid8) {
		this.bid8 = bid8;
	}



	public Double getBid9() {
		return bid9;
	}



	public void setBid9(Double bid9) {
		this.bid9 = bid9;
	}



	public Double getBid10() {
		return bid10;
	}



	public void setBid10(Double bid10) {
		this.bid10 = bid10;
	}



	public Integer getQtyLast() {
		return qtyLast;
	}



	public void setQtyLast(Integer qtyLast) {
		this.qtyLast = qtyLast;
	}



	public Double getLast() {
		return last;
	}



	public void setLast(Double last) {
		this.last = last;
	}
	
	
	
	
	
	

}
