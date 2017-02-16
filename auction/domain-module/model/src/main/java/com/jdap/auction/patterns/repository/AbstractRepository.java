
package com.jdap.auction.patterns.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.jdap.auction.patterns.GUID;
import com.jdap.auction.patterns.persistence.AbstractPersistentObject;
import com.jdap.auction.patterns.dao.DaoFactoryInterface;
// import com.jdap.auction.patterns.dao.DtoDaoInterface;
import com.jdap.auction.patterns.dao.GenericDaoInterface;
// import com.jdap.auction.patterns.exception.CdmEntityDoesNotExistRuntimeException;
import com.jdap.auction.exceptions.AuctionRuntimeException;
import com.jdap.auction.patterns.OID;
import com.jdap.auction.patterns.pagination.Pager;
import com.jdap.auction.patterns.persistence.PersistentObjectInterface;
import com.jdap.auction.patterns.repository.RepositoryInterface;
import com.jdap.auction.patterns.session.SessionParam;
import com.jdap.auction.patterns.pagination.JpaPagerImpl;
import com.jdap.auction.patterns.session.SessionManagerThreadLocal;
import com.jdap.auction.persistence.exceptions.AuctionPersistenceException;

/**
 * Default implementation of a repository object.
 * 
 * Note: JPA code should not migrate above this layer.
 * 
 * @param <T> Type of the repository.
 */
public abstract class AbstractRepository<T extends PersistentObjectInterface> implements RepositoryInterface<T>
{

	private Class<T> clazz;

	private DaoFactoryInterface daoFactory;

	public AbstractRepository(Class<T> clazz)
	{
		this.clazz = clazz;
	}

	/* (non-Javadoc)
	 * @see com.jdap.auction.patterns.repository.RepositoryInterface#contains(com
	 * .jdap.cdmr.patterns.persistence.PersistentObjectInterface) */
	@Override
    public boolean contains(T obj)
	{
		return daoFactory.createDao(clazz).contains(obj);
	}

	/* (non-Javadoc)
	 * @see com.jdap.auction.patterns.repository.RepositoryInterface#delete(com.jdap.auction.patterns
	 * .persistence.PersistentObjectInterface) */
	@Override
    public void delete(T obj)
	{
		daoFactory.createDao(clazz).delete(obj);
	}

	/* (non-Javadoc)
	 * @see com.jdap.auction.patterns.repository.RepositoryInterface#findById(com.jdap.auction.utils
	 * .objectidentifier.OID) */
	@Override
    public T findById(OID id)
	{
		return daoFactory.createDao(clazz).findById(id);
	}

	public T findById(String bidId)
	{
		// GenericDaoInterface<T> daoBid = getDaoFactory().createDao(clazz);
		T type = null;

		try
		{
			type = findById(GUID.fromString(bidId));
			if (type == null)
			{
				AuctionPersistenceException exception = new AuctionPersistenceException("ID didn't found: " + bidId);
				throw exception;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			AuctionPersistenceException persistenceException = new AuctionPersistenceException(this.getClass()
					.getName() + ", " + e.getMessage(), e.getCause());
			throw persistenceException;
		}

		return type;

	}

	// @Override
	@Override
    public <P extends PersistentObjectInterface> P findValidEntityById(Class<P> clazz, String oid)
	{
		// Guid.fromString, will validate for a null or empty string.
		P entity = getDaoFactory().createDao(clazz).findById((GUID.fromString(oid)));

		if (entity == null)
		{
			throw new AuctionRuntimeException("Referenced Entity of type " + clazz.getSimpleName()
					+ " does not exist for oid: " + oid + ". Effective time: "
					+ SessionManagerThreadLocal.INSTANCE.getProperty(SessionParam.TEMPORALITY_EFF_TS));
		}
		return entity;
	}

	public Class<T> getClazz()
	{
		return clazz;
	}

	/**
	 * @return daoFactory
	 */
	public DaoFactoryInterface getDaoFactory()
	{
		return daoFactory;
	}

	/* (non-Javadoc)
	 * @see com.jdap.auction.patterns.repository.RepositoryInterface#save(com.jdap.auction.patterns
	 * .persistence.PersistentObjectInterface) */
	@Override
    public OID save(T obj)
	{
		return daoFactory.createDao(clazz).save(obj);
	}

	/* (non-Javadoc)
	 * @see com.jdap.auction.patterns.repository.RepositoryInterface#saveOrUpdate(com.jdap.auction
	 * .patterns.persistence.PersistentObjectInterface) */
	@Override
    public OID saveOrUpdate(T obj)
	{
		return daoFactory.createDao(clazz).saveOrUpdate(obj);
	}

	/**
	 * @param daoFactory
	 */
	public void setDaoFactory(DaoFactoryInterface daoFactory)
	{
		this.daoFactory = daoFactory;
	}

	/**
	 * Convenience class to change to calendar.
	 * 
	 * @param d
	 * @return
	 */

	protected Calendar toCalendar(Date d)
	{
		final Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		c.setTime(d);
		return c;
	}

	public boolean isDifferent(Object original, Object update)
	{
		return ((update != null && !update.equals(original)) || (update == null && original != null));
	}

	/* (non-Javadoc)
	 * @see com.jdap.auction.patterns.repository.RepositoryInterface#update(com.jdap.auction.patterns
	 * .persistence.PersistentObjectInterface) */
	@Override
    public void update(T obj)
	{
		daoFactory.createDao(clazz).update(obj);
	}

	/*************************************************************************
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.jdap.auction.patterns.repository.RepositoryInterface#merge
	 *      (com.jdap.auction.patterns.persistence.PersistentObjectInterface)
	 * 
	 *************************************************************************/
	@Override
    public void merge(T obj)
	{
		daoFactory.createDao(clazz).merge(obj);
	}

	@Override
    public List<T> findAll()
	{
		return getDaoFactory().createDao(clazz).findAll();
	}

	/**
	 * Basic count of all objects in the entity.
	 * 
	 * @return
	 */

	@Override
    public Long countAll()
	{
		return getDaoFactory().createDao(clazz).countAll();
	}

	/**
	 * Basic query for all objects, with paging.
	 * 
	 * @param pager
	 * @return
	 */

	@Override
    public List<T> findAll(Pager pager)
	{
		return getDaoFactory().createDao(clazz).findAll(pager);
	}

	/*************************************************************************
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.jdap.auction.patterns.repository.RepositoryInterface#flush()
	 * 
	 *************************************************************************/
	// @Override
	@Override
    public void flush()
	{
		daoFactory.createDao(clazz).flush();
	}

	/*************************************************************************
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.jdap.auction.patterns.repository.RepositoryInterface#queryByRange(String, int, int)
	 * 
	 *************************************************************************/
	// @Override
	@Override
    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults)
	{
		return queryByRange(jpqlStmt, firstResult, maxResults, clazz);
	}

	/*************************************************************************
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.jdap.auction.patterns.repository.RepositoryInterface#queryByRange(String, Map<String, Object>
	 *      , int, int)
	 * 
	 *************************************************************************/
	// @Override
	@Override
    public Object queryByRange(String jpqlStmt, Map<String, Object> params, int firstResult, int maxResults)
	{
		return queryByRange(jpqlStmt, params, firstResult, maxResults, clazz);
	}

	// @Override
	@Override
    public void clear()
	{
		daoFactory.createDao(getClazz()).clear();
	}

	// @Override
	@Override
    public <C> C newEntity(Class<C> entityClazz)
	{
		return this.getDaoFactory().createDao(clazz).newEntity(entityClazz);
	}

	/* (non-Javadoc)
	 * @see com.jdap.auction.patterns.repository.RepositoryInterface#queryByRange(java.lang.String, int, int,
	 * java.lang.Class) */
	// @Override
	@Override
    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults,
			Class<? extends PersistentObjectInterface> entityClazz)
	{
		return queryByRange(jpqlStmt, null, firstResult, maxResults, entityClazz);
	}

	/* (non-Javadoc)
	 * @see com.jdap.auction.patterns.repository.RepositoryInterface#queryByRange(java.lang.String,
	 * Map<String, Object>, int, int, java.lang.Class) */
	// @Override
	@Override
    public Object queryByRange(String jpqlStmt, Map<String, Object> params, int firstResult, int maxResults,
			Class<? extends PersistentObjectInterface> entityClazz)
	{
		// check if query is not null throw exception if it is null
		if (jpqlStmt != null)
		{
			// check if it is a count
			if (jpqlStmt.toUpperCase().contains("SELECT COUNT"))
			{
				// count so do not create a pager
				System.out.println("to do");
				// DtoDaoInterface<Long> dao = this.getDaoFactory().createDtoDao( Long.class );
				// return dao.findByQuery( jpqlStmt, params );
				throw new AuctionRuntimeException("DtoDaoInterface: TO DO --JDAP--");
			} else
			{
				// not a count so run the query
				GenericDaoInterface<? extends PersistentObjectInterface> dao = this.getDaoFactory().createDao(
						entityClazz);
				Pager pager = null;
				if (firstResult > 0 || maxResults > 0)
				{
					pager = new JpaPagerImpl();
					pager.setStartPosition(firstResult);
					pager.setPageSize(maxResults);
				}
				return dao.findByQuery(jpqlStmt, pager, params);
			}
		} else
		{
			throw new AuctionRuntimeException("queryByRange: Null querry passed as parameter");
		}

	}

	/**
	 * Wraps a find by name query so we don't have to create the DAO each time..
	 * 
	 * @param id <code>id</code> of the object to find.
	 * @return {@link PersistedObject} with given <code>id</code>, or <code>null</code> if not found.
	 */

	@Override
    public List<T> findByNamedQuery(String queryName, Map<String, Object> params)
	{
		return getDaoFactory().createDao(clazz).findByNamedQuery(queryName, params);
	}

	@Override
    public <I> I findEntityInstanceByInterface(String oid, String[] notTemporalEntities, String[] temporalEntities,
			Class<I> iface)
	{
		List<AbstractPersistentObject> apos;
		List<I> instances = new ArrayList<I>();

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("id", oid);

		GenericDaoInterface<AbstractPersistentObject> dao = getDaoFactory().createDao(AbstractPersistentObject.class);

		// entities implementing ObjectCategoriesInterface first not temporal
		String queryString;
		for (int i = 0; i < notTemporalEntities.length; i++)
		{
			queryString = "SELECT obj FROM " + notTemporalEntities[i] + " obj WHERE obj.guid.oid = :id ";
			apos = dao.findByQuery(queryString, paramsMap);
			if (!apos.isEmpty() && iface.isInstance(apos.get(0)))
			{
				return (I) apos.get(0);
			} else
				if (!apos.isEmpty())
				{
					// not instance of interface
					return null;
				}
		}
		// now temporal
		for (int i = 0; i < temporalEntities.length; i++)
		{
			queryString = "SELECT obj FROM " + temporalEntities[i] + " obj WHERE obj.id = :id ";
			apos = dao.findByQuery(queryString, paramsMap);
			if (!apos.isEmpty() && iface.isInstance(apos.get(0)))
			{
				return (I) apos.get(0);
			} else
				if (!apos.isEmpty())
				{
					// not instance of interface
					return null;
				}
		}
		// cannot find entity
		return null;
	}

}
