
package com.jdap.auction.persistence.repository.impl;

// ~--- non-JDK imports ---------------------------------------------------------
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TimerService;
import javax.persistence.PersistenceException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.jdap.auction.model.Address;
import com.jdap.auction.model.Bidder;
import com.jdap.auction.model.BillingInfo;
import com.jdap.auction.model.Seller;
import com.jdap.auction.model.User;
import com.jdap.auction.model.type.UserType;
import com.jdap.auction.patterns.OID;
import com.jdap.auction.patterns.dao.DaoFactoryInterface;
import com.jdap.auction.patterns.dao.GenericDaoInterface;
import com.jdap.auction.patterns.repository.AbstractRepository;
import com.jdap.auction.persistence.repository.UserRepository;

/**
 * Session Bean implementation class UserBean
 */
@Stateless(name = "userRepository", mappedName = "ejb.jdap.auction.UserRepository")
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository
{
	/** Field description */
	private static Logger logger = Logger.getLogger(UserRepositoryImpl.class.getName());

	@Resource
	TimerService timerService;

	@Resource
	SessionContext context;

	/**
	 * Constructs ...
	 * 
	 */
	public UserRepositoryImpl()
	{
		super(User.class);
	}

	/**
	 * Set the DaoFactory com.jdap.auction.persistence.dao.AuctionEjbDaoFactory it is a implementation of
	 * DaoFactoryInterface. AuctionEjbDaoFactory extends AbstractEjbDaoFactory implements DaoFactoryInterface
	 */
	@EJB(beanName = "auctionDaoFactory")
	public void setEjbDaoFactory(DaoFactoryInterface daoFactory)
	{
		super.setDaoFactory(daoFactory);
	}

	/**
	 * Method description
	 * 
	 */
	@PostConstruct
	public void initialize()
	{
		logger.log(Level.INFO, "\n................User Repository Initialize......................................\n");
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param user
	 * 
	 * @return
	 * 
	 * @throws PersistenceException
	 * 
	 *         createDao , is a generic method that will create a factory for the entities model passed to it.
	 */

	@Override
    @RolesAllowed("sysroleplatformmss")
	public OID insertUser(User user)
	{
		GenericDaoInterface<User> daoUser = super.getDaoFactory().createDao(User.class);
		daoUser.save(user);
		return user.getId();
	}

	public <T extends User> void updateDbUser(T user, T dbUser)
	{
		dbUser.setFirstName(user.getFirstName());
		dbUser.setLastName(user.getLastName());
		dbUser.setUsername(user.getUsername());
		dbUser.setPassword(user.getPassword());
		dbUser.setEmail(user.getEmail());
		dbUser.setPhone(user.getPhone());
		//dbUser.setCreationDate(user.getCreationDate());

		Address address = new Address();
		address.setStreetLine1(user.getAddress().getStreetLine1());
		address.setStreeLine2(user.getAddress().getStreeLine2());
		address.setCity(user.getAddress().getCity());
		address.setState(user.getAddress().getState());
		address.setCountry(user.getAddress().getCountry());
		address.setZipCode(user.getAddress().getZipCode());
		dbUser.setAddress(address);
		BillingInfo billingInfo = new BillingInfo();
	}

	/**
	 * 
	 */
	@Override
    public <T extends User> User updateUser(T user, Class<T> clazz)
	{
		GenericDaoInterface<T> daoUser = super.getDaoFactory().createDao(clazz);

		T dbUser = (T) this.findUserById(user.getId().toString());

		updateDbUser(user, dbUser);

		if (user.getUserType().getCode().equals("BIDDER"))
		{
			Bidder bidder = (Bidder) user;
			((Bidder) dbUser).setBidFrecuency(bidder.getBidFrecuency());
		}
		else if (user.getUserType().getCode().equals("SELLER"))
		{
			Seller seller = (Seller) user;
			((Seller) dbUser).setCreditWorth(seller.getCreditWorth());
		}
		else
		{
			logger.finest("\n\n *****ERROR not UserType Implemented, user.getUserType().getCode(): "
					+ user.getUserType().getCode());
		}

		daoUser.saveOrUpdate(dbUser);

		return dbUser;
	}

	/**
	 * 
	 */
	@Override
    public void deleteUser(String userId)
	{
		GenericDaoInterface<User> daoUser = super.getDaoFactory().createDao(User.class);
		User user = this.findUserById(userId);
		daoUser.delete(user);
	}

	/**
	 * 
	 * @return
	 */
	public XMLGregorianCalendar getXmlCurrentime()
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(gc.getTime());
		XMLGregorianCalendar xmlgc = null;

		try
		{
			xmlgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		}
		catch (DatatypeConfigurationException e)
		{
			e.printStackTrace();
			// throw new AuctionServiceException(" ** DatatypeConfigurationException **");
		}

		return xmlgc;
	}

	/**
	 * @param userType TODO
	 * 
	 */
	@Override
    public <T extends User> List<T> getUsers(UserType userType)
	{
		logger.finest(" START: getAllUsers...........");
		GenericDaoInterface<User> daoUser = super.getDaoFactory().createDao(User.class);

		String strQuery = "SELECT b FROM User b where b.userType = ?1 ORDER BY b.lastName ASC";
		
		Object [] parameters = new Object[1];
		parameters[0] = userType;

		return (List<T>) daoUser.findByQuery(strQuery, parameters);
	}

	@Override
    public <T extends User> T getUserByUsernamePassword(String userName, String password)
	{
		T user = null;
		GenericDaoInterface<User> daoUser = super.getDaoFactory().createDao(User.class);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("username", userName);
		param.put("password", password);
				
		List<User> users =  daoUser.findByNamedQuery("user.getUserLogin", param);
		
		if (users.size() > 0 ) {
			user = (T) users.get(0);	
		}	
		
		return user; 
	}
	
	@Override
    public <T extends User> T getUserByUsername(String userName)
	{
		T user = null;
		GenericDaoInterface<User> daoUser = super.getDaoFactory().createDao(User.class);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("username", userName);
						
		List<User> users =  daoUser.findByNamedQuery("user.getUserName", param);
		
		if (users.size() > 0 ) {
			user = (T) users.get(0);	
		}	
		
		return user; 
	}
	
	
	/**
	 * 
	 * @param amountValue
	 * @return
	 */
	public List<User> getHighUsers(Double amountValue)
	{
		logger.finest(" START: getHighUsers...........");

		GenericDaoInterface<User> daoUser = super.getDaoFactory().createDao(User.class);

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("amount", amountValue);

		return daoUser.findByNamedQuery("Users.getHighUsers", param);

	}

	/**
	 * 
	 */	
	@Override
    public User findUserById(String user)
	{
		// TODO Auto-generated method stub
		return super.findById(user);
	}

}
