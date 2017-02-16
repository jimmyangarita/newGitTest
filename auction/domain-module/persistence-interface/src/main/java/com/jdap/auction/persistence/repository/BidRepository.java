package com.jdap.auction.persistence.repository;
import java.util.List;

import javax.ejb.Local;

import com.jdap.auction.model.Bid;
import com.jdap.auction.model.Item;
import com.jdap.auction.patterns.OID;



@Local
public interface BidRepository {
    
	public OID insertBid( Bid bid );// throws AuctionPersistenceException;
    
	public Bid updateBid(Bid bid);
	
	public void deleteBid(String bidId); //throws Exception;
	
	public void updateBidAmount(String bidId, Double newBidAmount); //throws AuctionPersistenceValidationException;
	
	public Bid findBidById(String bid);
	
	public List<Bid> getBidsByItem(Item item);
	
	public List<Bid> getHighBids(Double amountValue);
}
