package com.jdap.auction.service;
import java.sql.SQLException;

import javax.ejb.Local;

@Local
public interface BidderAccountCreatorLocal {
	void addLoginInfo(String logInfo);
	void addBiographicalInfo(String biographicalInfo);
	void addBillingInfo(String billingInfo);
	void cancelAccountCreation();
	void createAccount()  throws SQLException;;
}
