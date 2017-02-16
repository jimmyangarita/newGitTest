package com.jdap.auction.service.client.bid;

//~--- non-JDK imports ---------------------------------------------------------

import java.util.logging.Logger;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.jdap.auction.service.bid.BidBean;

/**
 * Class description
 * 
 * 
 * @version 9.0, 2012.April.26 03:43 PM
 * @author jdap Corporation
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class TestUserBeanTest {
	
	public static Logger logger = Logger.getLogger(TestUserBeanTest.class.getName());	
	private BidBean bean;
	//private BidRepository BidLocal;
	private Mockery context;
	
	//private Bid bid;
	
	public TestUserBeanTest(){super();}
	
	/**
	 * Method description
	 * 
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		context = new JUnit4Mockery();
		//this.BidLocal = context.mock(BidRepository.class);
		this.bean = new BidBean();
		//bean.setBidEJB(BidLocal);
	}

	/**
	 * Method description
	 * 
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		bean = null;
		context =null;
	}
	
	@Test
	public void testSayHello () throws Exception {
		
		
/*
		context.checking(new Expectations()
		{
			{			
				one(BidLocal).addBid( with( any (Bid.class) ) );
				will (returnValue(bid));
			}

		});
		String response = bean.persistBid(10L, 17L, 15.0);
		
		logger.info(response);
	*/	
		//context.assertIsSatisfied();
		
		//assertNotNull(response);
					
	}
	
}
