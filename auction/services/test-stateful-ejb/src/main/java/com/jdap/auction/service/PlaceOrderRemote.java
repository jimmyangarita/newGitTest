package com.jdap.auction.service;
import javax.ejb.Remote;

@Remote
public interface PlaceOrderRemote {
	public void setBiderID(Long biderID);
	public void addItem(Long itemID);
	public void setShipingInfo(String shipingInfo);
	public void setBillingInfo(String billingInfo);
	public Long confirmOrder() throws Exception;

}
