package com.jdap.auction.service;

import com.jdap.auction.exceptions.AuctionServicePublishEventException;
import com.jdap.auction.exceptions.AuctionServicePublisherException;
import javax.xml.namespace.QName;


public interface JmsPublisherUtilBeanLocal {

	public void send (String message) throws AuctionServicePublisherException;
	public
    void sendEvent( Object event,
               QName name ) throws AuctionServicePublishEventException;
}
