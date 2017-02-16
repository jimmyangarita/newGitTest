
package com.jdap.auction.patterns.persistence;

// ~--- JDK imports -------------------------------------------------------------

import java.io.Serializable;

import java.util.Date;
import java.util.logging.Logger;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.jdap.auction.patterns.GUID;
import com.jdap.auction.patterns.OID;
import com.jdap.auction.patterns.OIDGeneratorInterface;
import com.jdap.auction.patterns.dao.DaoFactoryInterface;
import com.jdap.auction.patterns.dao.GenericDaoInterface;

/**
 **/
@MappedSuperclass
public abstract class AbstractPersistentObject implements PersistentObjectInterface, Serializable
{
	/** Field description */
	private static final long serialVersionUID = 8992637046357006767L;

	/** Field description */
	@EmbeddedId
	private GUID guid;

	/** Field description */
	@Version
	private Long version;

	/**
     *
     */
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastModified;

	@Transient
	DaoFactoryInterface daoFactory = null;	// Will use Spring Injection to set

	// this.
	@Transient
	OIDGeneratorInterface oidGenerator = null;

	/** Field description */
	public static Logger logger = Logger.getLogger(AbstractPersistentObject.class.getName());

	/**
	 * 
	 * Constructs an AbstractPersistentObject.
	 * 
	 */
	public AbstractPersistentObject()
	{
	}

	/**
	 * 
	 * Respond to the {@link PrePersist} lifecycle callback by generating and assigning the {@link #guid}.
	 * 
	 */
	@PrePersist
	public void onPrePersist()
	{
		this.lastModified = new Date();

		GUID oid = GUID.randomOID();

		logger.info("\nJDAP100  onPrePersist with id :" + oid + ", " + "entity: " + this.getClass().getName() + "\n");
		setId(oid);
	}

	/**
	 * Method description
	 * 
	 */
	@PostPersist
	public void onPostPersist()
	{
		logger.info("\nJDAP101 onPostPersist with id :" + getId() + ", " + "entity: " + this.getClass().getName()
				+ "\n");
	}

	@PreUpdate
	public void onPreUpdate()
	{
		this.lastModified = new Date();
		logger.info("\nJDAP200 onPreUpdate with id :" + getId() + ", " + "entity: " + this.getClass().getName() + "\n");
	}

	
	@PostUpdate
	public void onPostUpdate()
	{
		logger.info("\nJDAP201 onPostUpdate with id :" + getId() + ", " + "entity: " + this.getClass().getName() + "\n");
	}

	@PreRemove
	public void onPreRemove()
	{
		logger.info("JDAP300 call PreRemove with id :" + getId().toString() + ", on entity: ");
	}

	@PostRemove
	public void onPostRemove()
	{
		logger.info("JDA301 call PostRemove with id :" + getId().toString() + ", on entity: ");
	}

	/**
	 * Method description
	 * 
	 */


	@PostLoad
	public void onPostLoad()
	{
		logger.info("\n\n JDAP: onPostLoad with id :" + getId() + ", " + "entity: " + this.getClass().getName() + "\n");
	}

	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	@Override
    public OID getId()
	{
		return (guid);
	}

	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	@Override
    public Long getVersion()
	{
		return version;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param id
	 */
	@Override
    public void setId(OID id)
	{
		this.guid = (GUID) id;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public String getIdAsString()
	{
		return guid.toString();
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param version
	 */
	@Override
    public void setVersion(Long version)
	{
		this.version = version;
	}

	/**
	 * @return the lastModified
	 */
	public Date getLastModified()
	{
		return lastModified;
	}

	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(Date lastModified)
	{
		this.lastModified = lastModified;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public DaoFactoryInterface getDaoFactory()
	{
		return daoFactory;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param daoFactory
	 */
	public void setDaoFactory(DaoFactoryInterface daoFactory)
	{
		this.daoFactory = daoFactory;
	}

	/**
	 * Get a dao object for this object.
	 * 
	 * @param <T>
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends AbstractPersistentObject> GenericDaoInterface<T> getDao()
	{
		return ((GenericDaoInterface<T>) this.daoFactory.createDao(this.getClass()));
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param obj
	 * 
	 * @return
	 */
	@Override
	public boolean equals(Object obj)
	{
		// this is common case non temporal
		if (this == obj)
		{
			return true;
		}

		// obj = EditionWrapperHelper.unwrap( obj );
		// this is param temporal Proxy and this non Proxy
		if (this == obj)
		{
			return true;
		}

		if (!(obj instanceof AbstractPersistentObject))
		{
			return false;
		}

		// finally if both are Proxies or different instances
		return ((AbstractPersistentObject) obj).getId() != null
				&& ((AbstractPersistentObject) obj).getId().equals(this.guid);
	}
}
