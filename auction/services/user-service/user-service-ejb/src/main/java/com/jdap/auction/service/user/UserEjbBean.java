
package com.jdap.auction.service.user;

// ~--- non-JDK imports ---------------------------------------------------------

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import com.jdap.auction.common.xml.user.BidderType;
import com.jdap.auction.common.xml.user.SellerType;
import com.jdap.auction.common.xml.user.UserType;
import com.jdap.auction.exceptions.AuctionServiceException;
import com.jdap.auction.model.Bidder;
import com.jdap.auction.model.Seller;
import com.jdap.auction.model.User;
import com.jdap.auction.patterns.GUID;
import com.jdap.auction.patterns.OID;
import com.jdap.auction.service.ActionInterceptorExceptions;

/**
 * 
 * @author jangarita
 * 
 */
@Stateless ( name = "UserEJB" , mappedName = "ejb.jdap.auction.service.UserEJB" )
@Interceptors (
	{ActionInterceptorExceptions.class} )
public class UserEjbBean extends BaseUserService implements UserRemote, UserLocal
{
	/**
	 * Default constructor.
	 */
	public UserEjbBean()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Method description
	 * 
	 */
	@PostConstruct
	public void initialize()
	{
		logger.log(Level.WARNING, "\n...................UserBean Initialize........................\n:");
	}
	
	/**
	 * 
	 * 
	 * @param sellerType
	 * 
	 * @return
	 * 
	 * @throws AuctionServiceException
	 */
	public SellerType addSeller(SellerType sellerType) throws AuctionServiceException
	{
		Seller seller = new Seller();
		
		super.mapUserTypetoUserModel(sellerType, seller);
		seller.setUserType(com.jdap.auction.model.type.UserType.S);
		seller.setCreditWorth(sellerType.getCreditWorth());
		seller.setCreationDate(bUtil.getCurrentTimeStamp());
		userRepository.insertUser(seller);
		mapUserModelToUserType(seller, sellerType);
		
		return sellerType;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param updatedSeller
	 * 
	 * @return
	 * 
	 * @throws AuctionServiceException
	 */
	public SellerType updateSeller(SellerType updatedSeller) throws AuctionServiceException
	{
		Seller seller = new Seller();
		
		super.validateUserId(updatedSeller);
		
		OID oidSellerModel = GUID.fromString(updatedSeller.getId());
		
		seller.setId(oidSellerModel);
		super.mapUserTypetoUserModel(updatedSeller, seller);
		seller.setUserType(com.jdap.auction.model.type.UserType.S);
		seller.setCreditWorth(updatedSeller.getCreditWorth());
		
		User dbUserModel = userRepository.updateUser(seller, Seller.class);
		
		mapUserModelToUserType(dbUserModel, updatedSeller);
		
		return updatedSeller;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param seller
	 * 
	 * @throws AuctionServiceException
	 */
	public void deleteSeller(SellerType seller) throws AuctionServiceException
	{
		super.validateUserId(seller);
		userRepository.deleteUser(seller.getId());
		logger.log(Level.INFO, "End: remove seller" + seller.getId());
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @return
	 * 
	 * @throws AuctionServiceException
	 */
	public List<SellerType> getSellersType() throws AuctionServiceException
	{
		com.jdap.auction.model.type.UserType userKind = com.jdap.auction.model.type.UserType.S;
		List<Seller> sellers = userRepository.getUsers(userKind);
		List<SellerType> SellersType = new ArrayList<SellerType>();
		
		for (Seller seller : sellers)
		{
			// logger.log( Level.INFO, "UserBean.getSellersType():" + seller.getId().toString() );
			SellerType sellerType = new SellerType();
			mapUserModelToUserType(seller, sellerType);
			SellersType.add(sellerType);
		}
		
		return SellersType;
	}
	
	/**
	 * 
	 * 
	 * @param bidderType
	 * 
	 * @return
	 * 
	 * @throws AuctionServiceException
	 */
	public BidderType addBidder(BidderType bidderType) throws AuctionServiceException
	{
		Bidder bidder = new Bidder();
		
		super.mapUserTypetoUserModel(bidderType, bidder);
		bidder.setUserType(com.jdap.auction.model.type.UserType.B);
		bidder.setBidFrecuency(bidderType.getBidFrequency());
		bidder.setCreationDate(bUtil.getCurrentTimeStamp());
		userRepository.insertUser(bidder);
		mapUserModelToUserType(bidder, bidderType);
		
		return bidderType;
	}
	
	public <T extends UserType> T updateUserByType(T userType) throws AuctionServiceException
	{
		if (userType instanceof BidderType)
			return (T) this.updateBidder((BidderType) userType);
		else if (userType instanceof SellerType)
			return (T) this.updateSeller((SellerType) userType);
		else
			throw new AuctionServiceException("Invalid UserType!!!");
	}
	
	/**
	 * 
	 * 
	 * @param updatedBidder
	 * 
	 * @return
	 * 
	 * @throws AuctionServiceException
	 */
	public BidderType updateBidder(BidderType updatedBidder) throws AuctionServiceException
	{
		Bidder bidder = new Bidder();
		
		super.validateUserId(updatedBidder);
		
		OID oidBidderModel = GUID.fromString(updatedBidder.getId());
		
		bidder.setId(oidBidderModel);
		super.mapUserTypetoUserModel(updatedBidder, bidder);
		bidder.setUserType(com.jdap.auction.model.type.UserType.B);
		bidder.setBidFrecuency(updatedBidder.getBidFrequency());
		
		User dbUserModel = userRepository.updateUser(bidder, Bidder.class);
		
		super.mapUserModelToUserType(dbUserModel, updatedBidder);
		
		return updatedBidder;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param bidder
	 * 
	 * @throws AuctionServiceException
	 */
	public void deleteBidder(BidderType bidder) throws AuctionServiceException
	{
		super.validateUserId(bidder);
		userRepository.deleteUser(bidder.getId());
		logger.log(Level.INFO, "End: remove bidder" + bidder.getId());
	}
	
	public void deleteUser(UserType user) throws AuctionServiceException
	{
		super.validateUserId(user);
		userRepository.deleteUser(user.getId());
		logger.log(Level.INFO, "End: remove user" + user.getId());
	}
	
	/**
	 * 
	 * 
	 * @return
	 * 
	 * @throws AuctionServiceException
	 */
	public List<BidderType> getBiddersType() throws AuctionServiceException
	{
		logger.log(Level.INFO, "BEGIN: getBiddersType----------");
		
		com.jdap.auction.model.type.UserType userKind = com.jdap.auction.model.type.UserType.B;
		List<Bidder> bidders = userRepository.getUsers(userKind);
		List<BidderType> BiddersType = new ArrayList<BidderType>();
		
		for (Bidder bidder : bidders)
		{
			logger.log(Level.INFO, "UserBean.getBiddersType():" + bidder.toString());
			BidderType bidderType = new BidderType();
			mapUserModelToUserType(bidder, bidderType);
			BiddersType.add(bidderType);
		}
		
		return BiddersType;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param userName
	 * @param password
	 * @param <T>
	 * 
	 * @return
	 * 
	 * @throws AuctionServiceException
	 */
	public <T extends UserType> T getUserByUsernamePassword(String userName, String password)
	    throws AuctionServiceException
	{
		User user = userRepository.getUserByUsernamePassword(userName, password);
		
		return super.mapUserByInstanceType(user);
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param userName
	 * @param password
	 * @param <T>
	 * 
	 * @return
	 * 
	 * @throws AuctionServiceException
	 */
	public <T extends UserType> T getUserByUsername(String userName) throws AuctionServiceException
	{
		User user = userRepository.getUserByUsername(userName);
		
		return super.mapUserByInstanceType(user);
		
	}
		
	
	/**
	 * 
	 * 
	 * @return
	 * 
	 * @throws AuctionServiceException
	 */
	public List<User> getUsers() throws AuctionServiceException
	{
		logger.log(Level.INFO, "BEGIN: getAllUsers users............");
		
		List<User> users = userRepository.getUsers(null);
		
		for (User user : users)
		{
			logger.log(Level.INFO, "getAllUsers:" + user.toString());
		}
		
		return users;
	}
}
