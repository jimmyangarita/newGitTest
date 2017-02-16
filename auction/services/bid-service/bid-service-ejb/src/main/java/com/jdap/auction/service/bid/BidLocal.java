package com.jdap.auction.service.bid;
import java.io.IOException;

import javax.ejb.Local;

@Local
public interface BidLocal {
	
	 public String ProcessGZIPFile( byte[] data ) throws IOException;
    
	 public String ProcessGZIPcreateFile(byte[] data) throws IOException;
	 
	 public void changeBidAmount(String bidId, Double newAmount) throws Exception;
	    
	 public String ProcessFile(byte[] data) throws Exception;
	    
	 public void ProcessFile2(byte[] data) throws Exception;
	 
	 public String ProcessFileString(String data) throws Exception;
}
