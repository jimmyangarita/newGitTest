
package com.jdap.auction.application.model;

// ~--- non-JDK imports ---------------------------------------------------------

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.jdap.auction.common.xml.user.AddressType;
import com.jdap.auction.common.xml.user.BidderType;
import com.jdap.auction.common.xml.user.SellerType;
import com.jdap.auction.common.xml.user.UserKind;
import com.jdap.auction.common.xml.user.UserType;
import com.jdap.auction.exceptions.AuctionServiceException;
//import com.jdap.auction.model.Address;
//import com.jdap.auction.model.User;
import com.jdap.auction.service.user.UserRemote;

/**
 * Class description
 * 
 * 
 * @version 9.0, 2014.July.22 02:50 PM
 * @author jdap Corporation
 */
/**
 * @author jangarita
 * 
 */
@ManagedBean ( name = "user" )
// @Named("user")
@SessionScoped
public class UserMBean extends BaseUserMBean implements Serializable
{
	/** Field description */
	private String id;
	
	/** Field description */
	private String userKind;
	
	/** Field description */
	private String userName;
	
	/** Field description */
	private String password;
	
	/** Field description */
	private String firstName;
	
	/** Field description */
	private String lastName;
	
	/** Field description */
	private String email;
	
	/** Field description */
	private long phone;
	
	// NO DB fields down
	/** Field description */
	private List<SelectItem> userKinds;
	
	/** Field description */
	private boolean userValid;// = true;
	
	/** Field description */
	private boolean emailValid;// = true;
	
	/** Field description */
	private boolean newUser;// = false;
	
	private boolean editable;
	
	/** Test Field **/
	private CreditCard card;
	
	/** Field description */
	@ManagedProperty ( value = "#{address}" )
	private AddressMBean addressMBean;
	
	/** Field description */
	@EJB
	private UserRemote userRemote;
	
	/**
	 * Constructs ...
	 * 
	 */
	public UserMBean()
	{
		logger.info("Creating.... User MBean()");
		this.userValid = true;
		this.emailValid = true;
	}
	
	
	public UserMBean(String id, String userKind, String password, String firstName, String lastName, String userName,
	                long phone, AddressMBean tAddress)
	{
		logger.info("Creating.... User MBean(Parameters..........)");
		this.id = id;
		this.userKind = userKind;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = userName;
		this.phone = phone;
		this.addressMBean = tAddress;
	}
	
	/**
     * If the username email is already exit in the DB can not be used again for a new user
     * 
     * @return
     * @throws AuctionServiceException
     */
    private boolean isValidUsername() throws AuctionServiceException
    {
    	UserType dbUser = null;
    	
    	if (!userName.equals(""))
    		dbUser = userRemote.getUserByUsername(userName);
    	else
    		throw new AuctionServiceException("Username cant be empty!");
    	
    	if (dbUser != null)
    		this.emailValid = false;
    	
    	logger.info("isValidUsername(), this.emailValid:  " + this.emailValid);
    	return this.emailValid;
    }


	/**
	 * 
	 * @param tUser
	 * @param <T>
	 * @return
	 */
	private <T extends UserType> T createUser(T tUser)
	{
		AddressType tAddress = new AddressType();
		
		tAddress.setLine1(getAddressMBean().getLine1());
		tAddress.setLine2(getAddressMBean().getLine2());
		tAddress.setCity(getAddressMBean().getCity());
		tAddress.setState(getAddressMBean().getState());
		tAddress.setCountry(getAddressMBean().getCountry());
		tAddress.setZip(getAddressMBean().getZipcode());
		tUser.setFirstName(firstName);
		tUser.setLastName(lastName);
		tUser.setPhone(new Long(phone).toString());
		tUser.setEmail(userName);
		tUser.setUsername(userName);
		tUser.setPassword(password);
		
		//the user is better to be created without Address, and better update the address in the future. for shipping
		//tUser.setAddress(tAddress);
		
		return tUser;
	}
	
	/**
     * 
     * @return
     * @throws AuctionServiceException
     */
    public String createUser() throws AuctionServiceException
    {
    	logger.info("****APPS****: Into UserBean.createUser()");
    	
    	String navigationValue = "welcome";
    	SellerType tSeller = new SellerType();
    	BidderType tBidder = new BidderType();
    	
    	/*the user is better to be created without Address,
    	and update the address in the future. for shipping*/
    	this.setAddressMBean(null);
    	
    	if (!isValidUsername())
    		return null;
    	
    	if (userKind.equalsIgnoreCase("BIDDER"))
    	{
    		//createUser(bidder);
    		super.mapUserMBeanToUserType(this, tBidder);
    		tBidder.setBidFrequency(26.7);
    		this.userRemote.addBidder(tBidder);
    		this.id = tBidder.getId();
    	}
    	else if (userKind.equalsIgnoreCase("SELLER"))
    	{
    		//createUser(seller);
    		super.mapUserMBeanToUserType(this, tSeller);
    		tSeller.setCreditWorth(68.7);
    		this.userRemote.addSeller(tSeller);
    		this.id = tSeller.getId();
    	}
    	else
    	{
    		navigationValue = "errors";
    		return navigationValue;
    	}
    	
    	this.userValid = true;
    	//logger.info("****APPS****: Finish UserBean.createUser(), navigationValue1== " + navigationValue);
    	return navigationValue;
    }


	/**
	 * Method description
	 * 
	 * 
	 * @param dbUser
	 */
	private void populateUser(UserType dbUser)
	{
		this.firstName = dbUser.getFirstName();
		this.lastName = dbUser.getLastName();
		this.phone = Long.parseLong(dbUser.getPhone());
		
		AddressType dbAddress = dbUser.getAddress();
		
		if (null != dbAddress)
		{
			this.addressMBean = new AddressMBean(dbAddress.getLine1(), dbAddress.getLine2(), dbAddress
			                .getCity(), dbAddress.getState(), dbAddress.getCountry(), dbAddress.getZip());
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws AuctionServiceException
	 */
	public String login() throws AuctionServiceException
	{
		logger.info("****APPS****: Action checkUserPassword()");
		
		String navigationValue = "welcome";
		this.userValid = false;
		UserType dbUser = null;
		
		if (!userName.equals("") && !password.equals(""))
		{
			dbUser = userRemote.getUserByUsernamePassword(userName, password);
		}
		
		if (null != dbUser)
		{
			userValid = true;
			populateUser(dbUser);
			logger.info("userValid: " + userValid);
		}
		else if (newUser)
		{
			navigationValue = "signup";
		}
		else
		{
			// userValid = false;
			logger.info("userValid: " + userValid);
			navigationValue = null;
			//return null;// null stays in the same one
		}
		
		return navigationValue;
		
	}
	
	
	public String logout() {
	  HttpSession session =	(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	  session.invalidate();
	  return "/login.xhtml?faces-redirect=ture";		
	}
	/**
	 * 
	 * @param event
	 */
	public void isNewUser(ValueChangeEvent event)
	{
		String selectedValue = event.getNewValue().toString();
		
		if (selectedValue.equals("true"))
		{
			this.password = "";
			newUser = true;
		}
		else if (selectedValue.equals("false"))
		{
			newUser = false;
		}
		
		logger.info("passwordisabled: " + newUser);
	}
	
		
	public AddressMBean getAddressMBean()
    {
    	return addressMBean;
    }
	

	public void setAddressMBean(AddressMBean addressMBean)
    {
    	this.addressMBean = addressMBean;
    }
	

	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public String getId()
	{
		return id;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param id
	 */
	public void setId(String id)
	{
		this.id = id;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	
	// Page 310 and 155, we can use an intermediate class and used it directly from the form.
	public List<SelectItem> getUserKinds()
	{
		this.userKinds = new ArrayList<SelectItem>();
		
		for (UserKind kind : UserKind.values())
		{
			this.userKinds.add(new SelectItem(kind, kind.value())); // same value for value, and label
			
			// it will be string for value and label, JSF convert Numbers and Enums.
		}
		
		return this.userKinds;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param userKinds
	 */
	public void setUserKinds(ArrayList<SelectItem> userKinds)
	{
		this.userKinds = userKinds;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public String getUserKind()
	{
		return userKind;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param userKind
	 */
	public void setUserKind(String userKind)
	{
		this.userKind = userKind;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public String getUserName()
	{
		return userName;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param userName
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public String getPassword()
	{
		return password;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public boolean isUserValid()
	{
		return userValid;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param userValid
	 */
	public void setUserValid(boolean userValid)
	{
		this.userValid = userValid;
	}
	
	public boolean isEmailValid()
	{
		return emailValid;
	}
	
	public void setEmailValid(boolean emailValid)
	{
		this.emailValid = emailValid;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public boolean isNewUser()
	{
		return newUser;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param newUser
	 */
	public void setNewUser(boolean newUser)
	{
		this.newUser = newUser;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public long getPhone()
	{
		return phone;
	}
	
	/**
	 * Method description
	 * 
	 * 
	 * @param phone
	 */
	public void setPhone(long phone)
	{
		this.phone = phone;
	}
	
	public boolean isEditable()
	{
		return editable;
	}
	
	public void setEditable(boolean newValue)
	{
		editable = newValue;
	}
	
	public CreditCard getCard()
	{
		return card;
	}
	
	public void setCard(CreditCard card)
	{
		logger.info("**********Card = " + card);
		this.card = card;
	}
	
}
