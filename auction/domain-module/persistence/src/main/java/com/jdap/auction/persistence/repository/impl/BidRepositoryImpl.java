
package com.jdap.auction.persistence.repository.impl;

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
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.persistence.PersistenceException;
import com.jdap.auction.model.Bid;
import com.jdap.auction.model.Item;
import com.jdap.auction.patterns.OID;
import com.jdap.auction.patterns.dao.DaoFactoryInterface;
import com.jdap.auction.patterns.dao.GenericDaoInterface;
import com.jdap.auction.patterns.repository.AbstractRepository;
import com.jdap.auction.persistence.repository.BidRepository;

/**
 * Session Bean implementation class BidBean
 */
@Stateless(name = "bidRepository", mappedName = "ejb.jdap.auction.BidRepository")
public class BidRepositoryImpl extends AbstractRepository<Bid> implements BidRepository
{
	/** Field description */
	private static Logger logger = Logger.getLogger(BidRepositoryImpl.class.getName());

	/** Field description */
	/* --Now lets use a auctionDaoFactory
	 * @PersistenceContext( unitName = "auctionPU" ) private EntityManager em; */

	@Resource
	TimerService timerService;

	@Resource
	SessionContext context;

	/**
	 * Constructs ...
	 * 
	 */
	public BidRepositoryImpl()
	{
		super(Bid.class);
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
		logger.log(Level.INFO,
				"\n................Place Bid Repository Initialize......................................\n");
	}

	/* public Bid findBidById(String bidId) { logger.finest(" BEFORE: findBidById the Bid...........");
	 * GenericDaoInterface<Bid> daoBid = super.getDaoFactory().createDao(Bid.class); Bid bid = null; try { bid
	 * = daoBid.findById(GUID.fromString(bidId)); if (bid == null) { AuctionPersistenceException exception =
	 * new AuctionPersistenceException("ID didn't found: " + bidId); throw exception; } } catch (Exception e)
	 * { e.printStackTrace(); AuctionPersistenceException persistenceException = new
	 * AuctionPersistenceException(this.getClass().getName() + ", " + e.getMessage(), e.getCause()); throw
	 * persistenceException; } return bid; } */

	/**
	 * Method description
	 * 
	 * 
	 * @param bid 
	 * 
	 * @return
	 * 
	 * @throws PersistenceException
	 * 
	 * createDao , is a generic method that will create a factory for the entities model passed to it.
	 */
	@Override
    // @RunAs("sysroleplatformmss")
	@RolesAllowed("sysroleplatformmss")
	// @PermitAll
	public	OID insertBid(Bid bid)	 // throws AuctionPersistenceException
	{
		// logger.log(Level.FINEST, "PRINCIPAL :: " + context.getCallerPrincipal().getName());
		// logger.log(Level.FINEST, "ROLE :: " + context.isCallerInRole("sysroleplatformmss"));
		timerService.createTimer(1 * 1 * 1000, 60 * 60 * 1000, bid);

		logger.info(" BEFORE: inserting Bid...........");
		GenericDaoInterface<Bid> daoBid = super.getDaoFactory().createDao(Bid.class);
		daoBid.save(bid);
		logger.info(" AFTER: inserting Bid...........:" + bid.getId());

		return bid.getId();
	}


	@Override
    public void updateBidAmount(String bidId, Double newBidAmount)
	{

		GenericDaoInterface<Bid> daoBid = super.getDaoFactory().createDao(Bid.class);
		Bid bid = this.findBidById(bidId);
		bid.setBidAmount(newBidAmount);
		daoBid.saveOrUpdate(bid);		// daoBid.save(bid);
		logger.info(" After: updating the Bid Amount...........");

	}

	// another way of implement it
	@Override
    public Bid updateBid(Bid bid)
	{
		GenericDaoInterface<Bid> daoBid = super.getDaoFactory().createDao(Bid.class);

		Bid dbBid = this.findBidById(bid.getId().toString());
		
		dbBid.setBidAmount(bid.getBidAmount());
		dbBid.setMaxAmount(bid.getMaxAmount());
		dbBid.setBidDate(bid.getBidDate());
		
		daoBid.saveOrUpdate(dbBid);
		
		return dbBid;

	}

	// different way , we do not need to capture persistence and runtime exceptions the interceptore will
	@Override
    public void deleteBid(String bidId)   // throws AuctionPersistenceException
	{
		GenericDaoInterface<Bid> daoBid = super.getDaoFactory().createDao(Bid.class);
		Bid bid = this.findBidById(bidId);
		daoBid.delete(bid);
	}

	/**
	 * 
	 */
	@Override
    public List<Bid> getBidsByItem(Item item)
	{
		GenericDaoInterface<Bid> daoBid = super.getDaoFactory().createDao(Bid.class);
		
		String strQuery = "SELECT b FROM Bid b WHERE b.item = ?1 ORDER BY b.bidAmount ASC";
		
		Object [] parameters = new Object[1];
		parameters[0] = item;

		return daoBid.findByQuery(strQuery, parameters);
	}

	/**
	 * 
	 */
	@Override
    public List<Bid> getHighBids(Double amountValue)
	{
		GenericDaoInterface<Bid> daoBid = super.getDaoFactory().createDao(Bid.class);

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("amount", amountValue);

		return daoBid.findByNamedQuery("Bids.getHighBids", param);
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param timer
	 */
	@Timeout
	public void monitorBid(Timer timer)
	{
		Bid bid = (Bid) timer.getInfo();

		logger.log(Level.INFO,
				"Monitoring timer remaining time: " + timer.getTimeRemaining() + ", Bid Amont: " + bid.getBidAmount());
	}

	@Override
	public Bid findBidById(String bid)
	{
		// TODO Auto-generated method stub
		return super.findById(bid);
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param bid
	 * 
	 * @return
	 */
	/* we are going to use the Dao and repositories private Bid save( Bid bid ) // throws PersistenceException
	 * { logger.info( " JA:  Before Persistence, bidID = " + bid.getId() ); //em.merge(bid); //em.persist( bid
	 * ); Throwable cause; String message = ""; try { em.persist( bid ); //em.flush(); } catch( Exception sqle
	 * ) { message = "DB ERROR :: " + sqle.getMessage(); cause = ( sqle.getCause() != null )?sqle.getCause() :
	 * sqle; logger.log( Level.SEVERE, "DB ERROR :: " + message + "\n Cause: " + cause +
	 * "\n Internal Cause : " + cause.getCause() ); throw new AuctionPersistenceException( message, cause ); }
	 * logger.info( " JA:  NO flush, After Persistence, bidID = " + bid.getId() ); return bid; } */
}
