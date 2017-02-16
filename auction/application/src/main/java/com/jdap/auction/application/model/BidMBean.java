package com.jdap.auction.application.model;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;

import com.jdap.auction.common.xml.bid.BidType;
import com.jdap.auction.exceptions.AuctionServiceException;
import com.jdap.auction.service.bid.BidRemote;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="bid")
@SessionScoped
public class BidMBean implements Serializable{
	
	public static Logger logger = Logger.getLogger(BidMBean.class.getName());
	private Long bidderId;
	private Long itemId;
	private Double amount;
	
	@EJB
	BidRemote BidEJBRemote;
	
	public String addBid() throws AuctionServiceException {
		
		//String result = testUserEJBRemote.persistBid(bidderId, itemId, amount);
		//System.out.println( " \n JA ********** \n Bean Back : \n **********" + result );
		logger.info("****APPS****: addBid");
		String addBidResponse = null; 
		BidType addBidRequest = new BidType();
		addBidRequest.setBidderId("69");
		addBidRequest.setItemId("cb65e6ae-9eee-4b28-8987-e7a10bde9bab");
		addBidRequest.setBidAmount(5.0);
		addBidResponse = BidEJBRemote.addBidType(addBidRequest);
		logger.info(addBidRequest.getBidderId());
		logger.info("****END APPS****: addBid." + addBidResponse);
		return "success"; 
	}


	public Long getBidderId() {
		return bidderId;
	}

	public void setBidderId(Long bidderId) {
		this.bidderId = bidderId;
	}
	
	
	public Long getItemId() {
		return itemId;
	}


	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}


	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	
}
