package com.jdap.auction.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.sql.DataSource;

import java.util.logging.Logger;

/**
 * Session Bean implementation class BidderAccountCreatorBean
 */
@Stateful(name = "BidderAccountCreatorEJB", mappedName = "ejb.jdap.auction.service.BidderAccountCreatorEJB")
public class BidderAccountCreatorBean implements  BidderAccountCreatorLocal {

	public static Logger logger = Logger.getLogger(BidderAccountCreatorBean.class.getName());
	
	@Resource(mappedName="jdbc.cdmrDS", name="CDMRDataSource")
	private DataSource dataSource;
	
    private String loginInfo;
    private String biographicalInfo;
    private String billingInfo;
	
    private Connection connection;
    
    public BidderAccountCreatorBean() { }
    
    @PostConstruct
    @PostActivate
    public void openConnection() {
    	try {
    		connection = dataSource.getConnection();
    		logger.info("PostActivate............................");
    	} catch (SQLException sqle) {
    		sqle.printStackTrace();
    	}
    }

	@Override
    public void addLoginInfo(String logInfo) {
		this.loginInfo = logInfo;		
	}

	@Override
    public void addBiographicalInfo(String biographicalInfo) {
		this.biographicalInfo = biographicalInfo;
	}

	@Override
    public void addBillingInfo(String billingInfo) {
		this.billingInfo = billingInfo;
	}
	
	@PrePassivate
	@PreDestroy
	public void cleanup() {
		try {
			connection.close();
			connection = null;
			logger.info("PreDestroy.........................");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
    @Remove
	public void cancelAccountCreation() {
		this.loginInfo = null;
		this.billingInfo = null;
		this.biographicalInfo = null;
	}
	
	@Override
    @Remove
	public void createAccount() throws SQLException {
		String sqlInsertBidder = "INSERT INTO BIDDERS (username,first_name,credit_card_type)" +
				"VALUES('"+ loginInfo +"', '"+ biographicalInfo + "', '" + billingInfo +"')";
		
		logger.info("INSERT SQL: \n" + sqlInsertBidder);
		
		Statement statement = connection.createStatement();
		statement.execute(sqlInsertBidder);
		 
	}

}
