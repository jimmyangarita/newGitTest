package com.jdap.auction.application.model;

import java.util.logging.Logger;

import com.jdap.auction.common.xml.user.AddressType;
import com.jdap.auction.common.xml.user.UserKind;
import com.jdap.auction.common.xml.user.UserType;

public abstract class BaseUserMBean
{

	/** Field description */
	public static Logger logger = Logger.getLogger(BaseUserMBean.class.getName());
	
	/**
	 * Map User Type (JAXB) to User MBean(Apps)
	 * 
	 * @param tUser
	 * @return
	 */
	protected UserMBean mapUserTypeToUserMBean(UserType tUser)
	{
		UserMBean userMBean = null;
		AddressMBean addressMBean = null;
		
		String id = tUser.getId();
		String userKind = tUser.getUsertype().value();//.toLowerCase();
		String firstName = tUser.getFirstName();
		String lastName = tUser.getLastName();
		String userName = tUser.getEmail();
		String password = tUser.getPassword();
		Long phone = Long.parseLong(tUser.getPhone());
		
		if (null != tUser.getAddress())
		{
			AddressType tAddress = tUser.getAddress();
			String line1 = tAddress.getLine1();
			String line2 = tAddress.getLine2();
			String country = tAddress.getCountry();
			String state = tAddress.getState();
			String city = tAddress.getCity();
			String zip = tAddress.getZip();
			
			addressMBean = new AddressMBean(line1, line2, city, state, country, zip);
		}

		userMBean = new UserMBean(id, userKind, password, firstName, lastName, userName, phone, addressMBean);
		
		return userMBean;
	}
	
	/**
	 * Map the User MBean (Apps) to the the User Type (JAXB object)
	 * 
	 * @param userMBean
	 * @param tUser
	 * @return
	 */
	protected <T extends UserType> T mapUserMBeanToUserType(UserMBean userMBean, T tUser)
	{
		// SellerType tUser = new SellerType();
		AddressType tAddress = new AddressType();
		
		tUser.setId(userMBean.getId());
		tUser.setUsertype(UserKind.fromValue(userMBean.getUserKind()));
		tUser.setFirstName(userMBean.getFirstName());
		tUser.setLastName(userMBean.getLastName());
		tUser.setEmail(userMBean.getUserName());
		tUser.setUsername(userMBean.getUserName());
		tUser.setPassword(userMBean.getPassword());
		tUser.setPhone(new Long (userMBean.getPhone()).toString());
		
		if (null != userMBean.getAddressMBean())
		{
			AddressMBean addressBean = userMBean.getAddressMBean();
			tAddress.setLine1(addressBean.getLine1());
			tAddress.setLine2(addressBean.getLine2());
			tAddress.setCountry(addressBean.getCountry());
			tAddress.setState(addressBean.getState());
			tAddress.setCity(addressBean.getCity());
			tAddress.setZip(addressBean.getZipcode());
		}
		
		tUser.setAddress(tAddress);
		
		return tUser;
	}
	
}
