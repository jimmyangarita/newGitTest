
package com.jdap.auction.application.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import com.jdap.auction.common.xml.user.BidderType;
import com.jdap.auction.common.xml.user.SellerType;
import com.jdap.auction.common.xml.user.UserType;
import com.jdap.auction.exceptions.AuctionServiceException;
import com.jdap.auction.service.user.UserRemote;

@ManagedBean ( name = "tableUsers" )
@SessionScoped
public class TableUserMBean extends BaseUserMBean implements Serializable
{
	private  static List<UserMBean> users = new ArrayList<UserMBean>();
	
	private SortFilterModel filterModel;
	
	/** Field description */
	@EJB
	private UserRemote userRemote;
	
	public TableUserMBean() throws AuctionServiceException
	{
		logger.info("Creating Mbean TableUser() ...");
	}
	
	/**
	 * Check if the User exist in the list in other way is added
	 * 
	 * @param userType
	 */
	private <T extends UserType> void populateUsers(List<T> userType)
	{
		
		for (UserType tUser : userType)
		{
			logger.info("into for tUser.getFirstName(): " +tUser.getFirstName());
			boolean found = false;
			
			for (UserMBean user : TableUserMBean.users)
			{
				if (user.getId().equals(tUser.getId()))
					found = true;
			}
			
			if (!found)
			{
				UserMBean userMBean = mapUserTypeToUserMBean(tUser);
				logger.info("Adding userMBean.getFirstName(): " +userMBean.getFirstName());
				TableUserMBean.users.add(userMBean);			
			}
		}
		
	}
	
	/**
	 * 
	 * @throws AuctionServiceException
	 */
	private void queryBidders() throws AuctionServiceException
	{
		List<BidderType> tBibber = this.userRemote.getBiddersType();
		populateUsers(tBibber);
	}
	
	/**
	 * 
	 * @throws AuctionServiceException
	 */
	private void querySellers() throws AuctionServiceException
	{
		List<SellerType> tSeller = userRemote.getSellersType();
		populateUsers(tSeller);
	}
	
	/**
	 * Load all the User from persistence to the UI
	 * 
	 * @return
	 * @throws AuctionServiceException
	 */
	public String queryUsers() throws AuctionServiceException
	{
		logger.info("TableUser.queryUsers()...");
		//filterModel = new SortFilterModel(new ArrayDataModel(users.toArray()));
		
		querySellers();
		queryBidders();
		
		filterModel = new SortFilterModel(new ArrayDataModel(users.toArray()));
		
		return "tableusers";
	}
	
	/**
	 * Save updated Users to the DB through the services
	 * 
	 * @return
	 * @throws AuctionServiceException
	 */
	public String save() throws AuctionServiceException
	{
		UserType tUser = null;
		
		for (UserMBean user : users)
		{
			if (user.getUserKind().equals("SELLER"))
				tUser = new SellerType();
			else if (user.getUserKind().equals("BIDDER"))
				tUser = new BidderType();
			else
				return "errors";
			
			tUser = mapUserMBeanToUserType(user, tUser);
			this.userRemote.updateUserByType(tUser);
			
			user.setEditable(false);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param userToDelete
	 * @return
	 * @throws AuctionServiceException
	 */
	public String deleteRow(UserMBean userToDelete) throws AuctionServiceException
	{
		UserType tUser;
		
		if (userToDelete.getUserKind().equals("SELLER"))
			tUser = new SellerType();
		else if (userToDelete.getUserKind().equals("BIDDER"))
			tUser = new BidderType();
		else
			return "errors";
		
		//delete in the DB
		tUser = mapUserMBeanToUserType(userToDelete, tUser);
		this.userRemote.deleteUser(tUser);
		
		//delete the in the view
		users.remove(userToDelete);
		
		//refresh the view
		filterModel = new SortFilterModel(new ArrayDataModel(users.toArray()));
		
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public String sortByFirst()
	{
		filterModel.sortBy(new Comparator<UserMBean>()
		{
			public int compare(UserMBean n1, UserMBean n2)
			{
				return n1.getFirstName().compareTo(n2.getFirstName());
			}
		});
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public String sortByLast()
	{
		filterModel.sortBy(new Comparator<UserMBean>()
		{
			public int compare(UserMBean n1, UserMBean n2)
			{
				return n1.getLastName().compareTo(n2.getLastName());
			}
		});
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public DataModel getUsers()
	{
		// logger.info("TableUser.getUsers()...........");
		// return users;
		return filterModel;
	}
	
	public UserRemote getUserRemote()
	{
		return userRemote;
	}
	
	public void setUserRemote(UserRemote userRemote)
	{
		this.userRemote = userRemote;
	}
	
}
