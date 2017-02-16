package com.jdap.auction.service.bid;

//~--- non-JDK imports ---------------------------------------------------------

import java.io.IOException;

import javax.ejb.Remote;

/**
 * Interface description
 *
 *
 * @version        9.0, 2012.May.14 06:52 PM
 * @author         jdap Corporation
 */
@Remote
public interface BidRemote
{
     void startTransfer(String umgIDJson) throws Exception;

     void finishTransfer(String umgIDJson) throws Exception;
     
     void uploadHistoricalData(byte[] file, String umgIDJson) throws Exception;
}
