package com.jdap.auction.patterns.dao;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

//import com.jdap.auction.model.extension.AbstractExtensionObject;
//import com.jdap.auction.model.extension.AbstractTemporalExtensionObject;
//import com.jdap.auction.model.property.*;
//import com.jdap.auction.model.temporal.wrapper.EditionWrapperHelper;
import com.jdap.auction.patterns.dao.DaoFactoryInterface;
import com.jdap.auction.patterns.dao.GenericDaoInterface;
//import com.jdap.auction.patterns.exception.CdmEntityDoesNotExistRuntimeException;
//import com.jdap.auction.patterns.extension.ExtensionInterface;
import com.jdap.auction.patterns.GUID;
import com.jdap.auction.patterns.OID;
import com.jdap.auction.patterns.pagination.Pager;
import com.jdap.auction.patterns.persistence.PersistentObjectInterface;
//import com.jdap.auction.patterns.property.*;
//import com.jdap.auction.cdmr.patterns.temporal.ContextInterface;
import com.jdap.auction.patterns.dao.jpa.JpaGenericDao;
//import com.ejb3inaction.exampe.patterns.dao.jpa.JpaGenericDaoImpl;
import com.jdap.auction.patterns.pagination.JpaPagerImpl;
import com.jdap.auction.patterns.pagination.PagerHelper;

/**
 * GenericDaoImpl.java
 *
 * Not Code Reviewed
 *
 * Copyright (c) 2010 jdap Corporation
 */

/**
 * GenericDaoImpl is a basic implementation of a DAO pattern that acts as a
 * Facade or Proxy to hide the underlying Implementation of the ORM layer, ORM
 * layer can be switched out via this class
 * 
 * @author mbrowner - 10/23/2010
 * 
 */

public class GenericDaoImpl<T extends PersistentObjectInterface> implements
		GenericDaoInterface<T> {

	private JpaGenericDao<T, OID> jpaDao;
	
	private Class<T> clazz ;

	private DaoFactoryInterface daoFactory;

	/**
	 * Create a new GenericDaoImpl instance.
	 * 
	 * @param clazz
	 *            persistenObject class
	 * 
	 */
/*
	public GenericDaoImpl(Class<T> clazz) {
		jpaDao = new JpaGenericDaoImpl<T>();
		jpaDao.setEntityClass(clazz);
		this.clazz = jpaDao.getEntityClass( clazz );
	}*/

	/**
	 * Create a new GenericDaoImpl instance.
	 * 
	 * @param clazz
	 *            persistenObject class
	 * @param dao
	 *            jpa generic dao implementation
	 */

	public GenericDaoImpl(Class<T> clazz, JpaGenericDao<T, OID> dao) {
		jpaDao = dao;
		jpaDao.setEntityClass(clazz);
		this.clazz = jpaDao.getEntityClass( clazz );
	}

	/**
	 * Loads the {@link PersistedObject} that has the given <code>id</code>.
	 * 
	 * @param id
	 *            <code>id</code> of the object to find.
	 * @return {@link PersistedObject} with given <code>id</code>, or
	 *         <code>null</code> if not found. @ Underlying data error.
	 */
	////Override
	@Override
    public T findById(OID id) {
		return jpaDao.find(id);
	}
	
	@Override
    public T findById(String id) {
		return findById(GUID.fromString(id));
	}

	/**
	 * SavesOrUpdates the given {@link PersistedObject}.
	 * 
	 * @param obj
	 *            {@link PersistedObject} to save. @ Underlying data error.
	 */

	////Override
	@Override
    public OID saveOrUpdate(T obj) {
		// this method is legacy of hibernate's save or update JPA does not have
		// such
		// implementation, we are trying to implement it by using find and
		// update methods
/*		if (obj.getId() == null || this.findById(obj.getId()) == null) {
			// save it
			this.save(obj);
		} else {
			// update it
			this.update(obj);
		}
		*/
        ////obj = EditionWrapperHelper.unwrap( obj );
		jpaDao.persist(obj);
		return obj.getId();
	}

	/**
	 * Deletes the given {@link PersistedObject}.
	 * 
	 * @param obj
	 *            {@link PersistedObject} to delete. @ Underlying data error.
	 */
	////Override
	@Override
    public void delete(T obj) {
      //obj = EditionWrapperHelper.unwrap( obj );
	   ///check if entity is not an extension  
	   /*if( !( obj instanceof ExtensionInterface ) )
	   {
	      // delete extensions if obj is not an extension
	      this.deleteAllExtensions( obj );
	      if( !( obj instanceof PropertyInterface ) )
	      {
	         // delete property mappings if obj is not a property mapping
	         this.deleteAllPropertyMappings( obj );
	      }
	   }*/
      jpaDao.remove( obj );
	}

	/**
	 * Deletes the given {@link PersistedObject}.
	 * 
	 * @param id
	 *            <code>id</code> of the object to find. @ Underlying data
	 *            error.
	 */
	/////Override
	@Override
    public void delete(OID id) {
      // find the entity by id this method is provided to support ejbs
      T obj = jpaDao.find( id );
      if ( obj == null )
      {
         throw new RuntimeException( "Entitity not found for id:"+id );
      }
      delete( obj );
   }

	/**
	 * Saves the given {@link PersistedObject}.
	 * 
	 * @param obj
	 *            {@link PersistedObject} to save.
	 * @return @ Underlying data error.
	 */

	////Override
	@Override
    public OID save(T obj) {
        //obj = EditionWrapperHelper.unwrap( obj );
		jpaDao.persist(obj);
		return obj.getId();
		// save or update extensions
		/*
		 * List<AbstractExtensionObject> extensions = this.getExtensions(obj,
		 * AbstractExtensionObject.class); if (extensions != null) {
		 * GenericDaoImpl extDao = new
		 * GenericDaoImpl(AbstractExtensionObject.class);
		 * extDao.saveOrUpdateAll(extensions); }
		 */
	}

	/**
	 * Returns all {@link ExtensionInterface}s for the given object.
	 * 
	 * @param extensibleObj
	 *            Core extensible object.
	 * @param extensionType
	 *            Type of extensions to return.
	 * @return Extensions attached to this object. An empty List if none are
	 *         found. @ Underlying data error.
	 */
	/*
	//Override
	public <E extends ExtensionInterface<T>> List<E> getExtensions(
			T extensibleObj, Class<E> extensionType) {	   
		CriteriaBuilder cb = jpaDao.getEntityManager().getCriteriaBuilder();
		Class clazz = getEntityClass( extensionType );
		CriteriaQuery<E> cq = cb.createQuery(clazz);

		// Filter down to object for this specific extension class.

		Root<E> extensionEntity = cq.from(clazz);
		Path<String> fkOID = extensionEntity.get(ExtensionInterface.CORE_OID);
		cq.where(cb.equal(fkOID, extensibleObj.getId()));

		TypedQuery<E> q = jpaDao.getEntityManager().createQuery(cq);
		List<E> results = q.getResultList();
		
		//fix to set extensible, because extensible is transient
		for ( E e : results )
      {
         e.setExtensible( extensibleObj );
      }

		return (results);
	}*/

	/**
	 * Returns all {@link ExtensionInterface}s for the given object.
	 * 
	 * @param extensibleObj
	 *            Core extensible object.
	 * @param extensionType
	 *            Type of extensions to return.
	 * @return Extensions attached to this object. An empty List if none are
	 *         found. @ Underlying data error.
	 */
	/*
	//Override
	public <E extends ExtensionInterface<T>> List<E> getExtensions(
			T extensibleObj, Class<E> extensionType, ContextInterface iContext) {
		CriteriaBuilder cb = jpaDao.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(getEntityClass( extensionType ));

		// Filter down to object for this specific extension class.

		Root<E> extensionEntity = cq.from(getEntityClass( extensionType ));
		Path<String> fkOID = extensionEntity.get(ExtensionInterface.CORE_OID);
		cq.where(cb.equal(fkOID, extensibleObj.getId()));

		TypedQuery<E> q = jpaDao.getEntityManager().createQuery(cq);
		List<E> results = q.getResultList();

		return (results);
	}*/

   /**
    * Returns all {@link AbstractExtensionObject}s for the given object.
    * 
    * @param core
    *           Core object.
    * @param eContext
    *           Event context to obtain objects in.
    * @return Extensions attached to this object. An empty List if none are found. @ Underlying
    *         data error.
    */
	/*
   @SuppressWarnings( { "unchecked", "rawtypes" } )
   public <E extends ExtensionInterface<T>> List<E> getAllExtensions( T extensibleObj )
   {
      List<E> extensions = new ArrayList<E>();  
      GenericDaoInterface daoExt = getDaoFactory().createDao( AbstractExtensionObject.class );
      extensions.addAll( daoExt.getExtensions( extensibleObj, AbstractExtensionObject.class ) );
      GenericDaoInterface daoExtT = getDaoFactory().createDao( AbstractTemporalExtensionObject.class );
      extensions.addAll( daoExtT.getExtensions( extensibleObj, AbstractTemporalExtensionObject.class ) );
      return extensions;
   }*/
	
	/**
	 * Deletes the given objects extensions {@link PersistedObject}.
	 * 
	 * @param extensibleObj
	 *            {@link PersistedObject} to delete extensions for. @ Underlying
	 *            data error.
	 */
	/*//Override
	public <E extends ExtensionInterface<?>> void deleteExtensions(
			T extensibleObj, Class<E> extensionType) {
		CriteriaBuilder cb = jpaDao.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(getEntityClass( extensionType ));

		// Filter down to object for this specific extension class.

		Root<E> extensionEntity = cq.from(getEntityClass( extensionType ));
		Path<String> fkOID = extensionEntity.get(ExtensionInterface.CORE_OID);
		cq.where(cb.equal(fkOID, extensibleObj.getId()));

		TypedQuery<E> q = jpaDao.getEntityManager().createQuery(cq);

		// Loop through the results to delete the objects.

		List<E> results = q.getResultList();

		for (E ext : results) {
			// Delete the object.
		     GenericDaoInterface daoExt = getDaoFactory().createDao(extensionType);
             daoExt.delete(ext);
		}

	}*/

   /**
   * Deletes all objects extensions {@link PersistedObject}.
   *
   * @param core
   *            {@link PersistedObject} to delete extensions for. @ Underlying
   *            data error.
   */
/*
   @SuppressWarnings( { "rawtypes", "unchecked" } )
   //Override
   public <E extends ExtensionInterface<?>> void deleteAllExtensions( T extensibleObj )
   {
      GenericDaoInterface daoExt = getDaoFactory().createDao( AbstractExtensionObject.class );
      List<AbstractExtensionObject> aeoList = daoExt.getExtensions( extensibleObj, AbstractExtensionObject.class );
      for ( AbstractExtensionObject aeo : aeoList )
      {
         daoExt.delete( aeo );
      }
      GenericDaoInterface daoExtT = getDaoFactory().createDao( AbstractTemporalExtensionObject.class );
      List<AbstractTemporalExtensionObject> aeoTList =
            daoExtT.getExtensions( extensibleObj, AbstractTemporalExtensionObject.class );
      for ( AbstractTemporalExtensionObject aeoT : aeoTList )
      {
         daoExtT.delete( aeoT );
      }
   }*/

   /**
    * Returns all {@link AbstractPropertyExtObject}s for the given object.
    *
    * @param owner
    *            Owner object.
    * @param mappingType
    *            Type of property mappings to return.
    * @return Property mappings attached to this object. An empty List if none are
    *         found. @ Underlying data error.
    */
	/*
   //Override
   public <P extends PropertyInterface<T>> List<P> getPropertyMappings(T owner,
         Class<P> mappingType)
   {
      CriteriaBuilder cb = jpaDao.getEntityManager().getCriteriaBuilder();
      Class clazz = getEntityClass( mappingType );
      CriteriaQuery<P> cq = cb.createQuery(clazz);

      // Filter down to object for this specific mapping class.

      Root<P> mappingEntity = cq.from(clazz);
      Path<String> fkOID = mappingEntity.get(PropertyInterface.OWNER_OID);
      cq.where(cb.equal(fkOID, owner.getId()));

      TypedQuery<P> q = jpaDao.getEntityManager().createQuery(cq);
      List<P> results = q.getResultList();

      //fix to set extensible, because owner is transient
      for ( P p : results )
      {
         p.setOwner( owner );
      }

      return (results);
   }*/

   /**
    * Returns all {@link AbstractPropertyExtObject}s for the given object.
    *
    * @param owner
    *            Owner object.
    * @param mappingType
    *            Type of property mappings to return.
    * @param eContext
    *            Event context to obtain objects in.
    * @return Property mappings attached to this object. An empty List if none are
    *         found. @ Underlying data error.
    */
	/*
   //Override
   public <P extends PropertyInterface<T>> List<P> getPropertyMappings(T owner,
         Class<P> mappingType, ContextInterface eContext)
   {
      CriteriaBuilder cb = jpaDao.getEntityManager().getCriteriaBuilder();
      CriteriaQuery<P> cq = cb.createQuery(getEntityClass( mappingType ));

      // Filter down to object for this specific mapping class.

      Root<P> mappingEntity = cq.from(getEntityClass( mappingType ));
      Path<String> fkOID = mappingEntity.get(PropertyInterface.OWNER_OID);
      cq.where(cb.equal(fkOID, owner.getId()));

      TypedQuery<P> q = jpaDao.getEntityManager().createQuery(cq);
      List<P> results = q.getResultList();

      return (results);
   }
*/
   /**
    * Deletes the given objects property mappings {@link AbstractPropertyExtObject}.
    *
    * @param owner - the property mappings owner
    * @param mappingType
    *            Type of property mappings to delete.
    */
	/*
   //Override
   public <P extends PropertyInterface<?>> void deletePropertyMappings(
         T owner, Class<P> mappingType) {
      CriteriaBuilder cb = jpaDao.getEntityManager().getCriteriaBuilder();
      CriteriaQuery<P> cq = cb.createQuery(getEntityClass( mappingType ));

      // Filter down to object for this specific mapping class.

      Root<P> mappingEntity = cq.from(getEntityClass( mappingType ));
      Path<String> fkOID = mappingEntity.get(PropertyInterface.OWNER_OID);
      cq.where(cb.equal(fkOID, owner.getId()));

      TypedQuery<P> q = jpaDao.getEntityManager().createQuery(cq);

      // Loop through the results to delete the objects.

      List<P> results = q.getResultList();

      for (P ext : results) {
         // Delete the object.
           GenericDaoInterface daoExt = getDaoFactory().createDao(mappingType);
             daoExt.delete(ext);
      }

   }*/

   /**
    * Deletes all objects property mappings {@link AbstractPropertyExtObject}.
    *
    * @param owner - the property mappings owner
    *//*
   @SuppressWarnings( { "rawtypes", "unchecked" } )
   //Override
   public <P extends PropertyInterface<?>> void deleteAllPropertyMappings( T owner) {
       GenericDaoInterface daoExtT = getDaoFactory().createDao( AbstractPropertyExtObject.class );
       List<AbstractPropertyExtObject> aeoTList = daoExtT.getPropertyMappings( owner, AbstractPropertyExtObject.class );
       for ( AbstractPropertyExtObject aeoT : aeoTList )
       {
          daoExtT.delete( aeoT );
       }
   }*/

	/**
	 * Find by Query string and positional parameters.
	 * 
	 * @param queryString
	 *            - passed in query string.
	 * @param pager
	 *            object used to store pagination information
	 * @param values
	 * @return @
	 * 
	 */
	//Override
	public List<T> findByQuery(String queryString, Pager pager,	Object... values)
	{
	   if( pager != null && pager.isRetrieveTotal() )
      {
         PagerHelper.configureTotalPages( pager, countByQuery( queryString, values) );
      }
		return jpaDao.find(queryString, (JpaPagerImpl) pager, values);
	}

	/**
	 * Find by Query string and positional parameters.
	 * 
	 * @param queryString
	 *            - passed in query string.
	 * @param values
	 * @return @
	 * 
	 */
	//Override
	@Override
    public List<T> findByQuery(String queryString, Object... values) {
		return findByQuery(queryString, null, values);
	}

	/**
	 * Find by Query string and named parameters.
	 * 
	 * @param queryString
	 *            - passed in query string.
	 * @param pager
	 *            object used to store pagination information
	 * @param param
	 *            - map
	 * @return @
	 * 
	 */
	//Override
	public List<T> findByQuery(String queryString, Pager pager, Map<String, ?> params) {
      if( pager != null && pager.isRetrieveTotal() )
      {
         PagerHelper.configureTotalPages( pager, countByQuery( queryString, params) );
      }
      return jpaDao.findByNamedParams(queryString, pager, params);
	}


   //Override
   public List<T> findByQueryWithUser(String queryString, Pager pager,
         Map<String, ?> params, String user){
      if( pager != null && pager.isRetrieveTotal() )
      {
         PagerHelper.configureTotalPages( pager, countByQuery( queryString, params) );
      }
      if( user != null && jpaDao.getSessionManager()!= null )
      {
         jpaDao.getSessionManager().impersonateUser( user );
      }
      return jpaDao.findByNamedParams(queryString, pager, params);
   }
	
	/**
	 * Find by Query string and named parameters.
	 * 
	 * @param queryString
	 *            - passed in query string.
	 * @param param
	 *            - map
	 * @return @
	 * 
	 */
	//Override
	@Override
    public List<T> findByQuery(String queryString, Map<String, ?> params) {
		return findByQuery(queryString, null, params);
	}

   /*************************************************************************
   *
   * (non-Javadoc)
   * @see com.jdap.cdmr.patterns.dao.GenericDaoInterface#findByQuery(java.lang.String, java.util.Map, java.lang.String)
   *
   *************************************************************************/
	//Override
   @Override
public List<T> findByQueryWithUser(String queryString, Map<String, ?> params, String user) {
      return findByQueryWithUser(queryString, null, params, user);
   }
	
	/**
	 * Find by named query with no parameters (static).
	 * 
	 * @param queryName
	 *            - passed in query string.
	 * @param pager
	 *            object used to store pagination information
	 * @return @
	 * 
	 */
	//Override
	public List<T> findByNamedQuery(String queryName, Pager pager) {
		return findByNamedQuery(queryName, pager, (Map<String, ?>) null);
	}
	
	/**
	 * Find by named query with no parameters (static).
	 * 
	 * @param queryName
	 *            - passed in query string.
	 * @return @
	 * 
	 */
	//Override
	@Override
    public List<T> findByNamedQuery(String queryName) {
		return findByNamedQuery(queryName, (Pager) null);
	}

	/**
	 * Find by named query with positional parameters.
	 * 
	 * @param queryName
	 *            - passed in query string.
	 * @param pager
	 *            object used to store pagination information
	 * @return @
	 * 
	 */
	//Override
	public List<T> findByNamedQuery(String queryName, Pager pager, Object... values)
	{
	   if( pager != null && pager.isRetrieveTotal() )
      {
         PagerHelper.configureTotalPages( pager, countByNamedQuery(queryName, values) );
      }
		return jpaDao.findByNamedQuery(queryName, pager, values);
	}

	/**
	 * Find by named query with positional parameters.
	 * 
	 * @param queryName
	 *            - passed in query string.
	 * @return @
	 * 
	 */
	//Override
	@Override
    public List<T> findByNamedQuery(String queryName, Object... values) {
		return findByNamedQuery(queryName, null, values);
	}

	/**
	 * Find by named query with named parameters.
	 * 
	 * @param queryName
	 *            - passed in query string.
	 * @param pager
	 *            object used to store pagination information
	 * @param params
	 * @return @
	 * 
	 */
	//Override
	public List<T> findByNamedQuery(String queryName, Pager pager, Map<String, ?> params)
	{
	   if( pager != null && pager.isRetrieveTotal() )
      {
         PagerHelper.configureTotalPages( pager, countByNamedQueryAndNamedParams(queryName, params) );
      }
		return jpaDao.findByNamedQueryAndNamedParams(queryName, pager, params);
	}

	/*************************************************************************
	 * 
	 * @see com.jdap.cdmr.patterns.dao.GenericDaoInterface#countByNamedQuery(java.lang.String)
	 * 
	 *************************************************************************/
	//Override
	@Override
    public long countByNamedQuery(String queryName) {
		return jpaDao.countByNamedQuery(queryName);
	}

	/*************************************************************************
	 * 
	 * @see com.jdap.cdmr.patterns.dao.GenericDaoInterface#countByNamedQuery(java.lang.String,
	 *      java.lang.Object[])
	 * 
	 *************************************************************************/
	//Override
	@Override
    public long countByNamedQuery(String queryName, Object... values) {
		return jpaDao.countByNamedQuery(queryName, values);
	}

	/*************************************************************************
	 * 
	 * @see com.jdap.cdmr.patterns.dao.GenericDaoInterface#countByNamedQueryAndNamedParams(java.lang.String,
	 *      java.util.Map)
	 * 
	 *************************************************************************/
	//Override
	@Override
    public long countByNamedQueryAndNamedParams(String queryName,
			Map<String, ?> params)

	{
		return jpaDao.countByNamedQueryAndNamedParams(queryName, params);
	}

	/**
	 * Find by named query with named parameters.
	 * 
	 * @param queryName
	 *            - passed in query string.
	 * @param params
	 * @return @
	 * 
	 */
	//Override
	@Override
    public List<T> findByNamedQuery(String queryName, Map<String, ?> params) {
		return findByNamedQuery(queryName, null, params);
	}

	/**
	 * Returns the number of instances of {@link PersistedObject} based on the
	 * Criteria specified.
	 * 
	 * @param criteria
	 * @return the number of instances of of {@link PersistedObject} based on
	 *         the passed criteria <code>criteria</code>, or 0 if not found.
	 * 
	 *         @
	 */
	//Override
	@Override
    public long countByCriteria(Object whereCriteria) {
		return jpaDao.countByCriteria((CriteriaQuery<T>) whereCriteria);
	}

	/**
	 * Loads the list of {@link PersistedObject} based on the Criteria
	 * specified.
	 * 
	 * @param criteria
	 * @param pager
	 *            object used to store pagination information
	 * @return A list of {@link PersistedObject} based on the passed criteria
	 *         <code>criteria</code>, or empty list if not found.
	 * 
	 *         @
	 */
	//Override
	public List<T> findByCriteria(Object criteria, Pager pager) 
	{
	   if( pager != null && pager.isRetrieveTotal() )
      {
         PagerHelper.configureTotalPages( pager, countByCriteria(criteria) );
      }
		return jpaDao.findByCriteria((CriteriaQuery<T>) criteria, pager);
	}

	/**
	 * Loads the list of {@link PersistedObject} based on the Criteria
	 * specified.
	 * 
	 * @param criteria
	 * @return A list of {@link PersistedObject} based on the passed criteria
	 *         <code>criteria</code>, or empty list if not found.
	 * 
	 *         @
	 */
	//Override
	@Override
    public List<T> findByCriteria(Object criteria) {
		return findByCriteria(criteria, null);
	}
	
	
	/**
	 * Returns the number of instances of {@link PersistedObject} based on the
	 * Query String specified.
	 * 
	 * @param queryString
	 *            string with query in specific query language
	 * @return the number of instances of {@link PersistedObject} based on the
	 *         passed criteria <code>criteria</code>, or 0 if not found.
	 * 
	 *         @
	 */
	//Override
	@Override
    public long countByQuery(String queryString) {
		return jpaDao.count(queryString);
	}

	/**
	 * Returns the number of instances of {@link PersistedObject} based on the
	 * Query String specified.
	 * 
	 * @param queryString
	 *            string with query in specific query language
	 * @param values
	 *            values passed to query as parameters
	 * @return the number of instances of {@link PersistedObject} based on the
	 *         passed criteria <code>criteria</code>, or 0 if not found.
	 * 
	 *         @
	 */
	//Override
	@Override
    public long countByQuery(String queryString, Object... values) {
		return jpaDao.count(queryString, values);
	}

	/**
	 * Returns the number of instances of {@link PersistedObject} based on the
	 * Query String specified.
	 * 
	 * @param queryString
	 *            string with query in specific query language
	 * @param params
	 *            map of named parameters (name, value) passed to the query
	 * @return the number of instances of {@link PersistedObject} based on the
	 *         passed criteria <code>criteria</code>, or 0 if not found.
	 * 
	 *         @
	 */
	//Override
	@Override
    public long countByQuery(String queryString, Map<String, ?> params) {
		return jpaDao.countByNamedParams(queryString, params);
	}

	/**
	 * Returns an utility class to build criteria expressions.
	 * 
	 * @return expression builder not associated with {@link PersistedObject}
	 * 
	 *         @
	 */
	//Override
	@Override
    public Object getExpresionBuilder() {
		return jpaDao.getCriteriaBuilder();
	}

	/**
	 * Updates the persistence copy of the given {@link PersistedObject}.
	 * 
	 * @param obj
	 *            {@link PersistedObject} to update. @ Underlying data error.
	 */
	//Override
	@Override
    public void update(T obj) {

        //obj = EditionWrapperHelper.unwrap( obj );
		jpaDao.persist(obj);
    }

   /*************************************************************************
   *
   * (non-Javadoc)
   * @see com.jdap.cdmr.patterns.dao.GenericDaoInterface#merge
   * (com.jdap.cdmr.patterns.persistence.PersistentObjectInterface)
   *
   *************************************************************************/
   //Override
   @Override
public void merge( T obj )
   {     
      jpaDao.merge( obj );         
   }

	/**
	 * Returns true if the given object is a part of the session cache.
	 * 
	 * @param obj
	 *            Object to check.
	 * @return True if it is a part of cache; false otherwise; @ Underlying data
	 *         error.
	 */
	//Override
	@Override
    public boolean contains(T obj) {
		return jpaDao.contains(obj);
	}

	//Override
	@Override
    public void flush() {
		jpaDao.flush();
	}

	//Override
	@Override
    public void bulkUpdate(String sentence, Object... values) {
		jpaDao.bulkUpdate(sentence, values);
	}

	//Override
	@Override
    public void bulkUpdate(String sentence, Map<String, ?> params) {
		jpaDao.bulkUpdateByNamedParams(sentence, params);
	}

	//Override
	@Override
    public void bulkUpdateByNamedQuery(String queryName, Object... values) {
		jpaDao.bulkUpdateByNamedQuery(queryName, values);
	}

	//Override
	@Override
    public void bulkUpdateByNamedQuery(String queryName, Map<String, ?> params)

	{
		jpaDao.bulkUpdateByNamedQueryAndNamedParams(queryName, params);
	}

	//Override
	@Override
    public Map<T, Exception> saveOrUpdateAll(Collection<T> objs) {
		Map<T, Exception> errors = new HashMap<T, Exception>();

		for (T obj : objs) {
			try {
				this.saveOrUpdate(obj);
			} catch (Exception e) {
				// TODO log
				errors.put(obj, e);
			}
		}
		return errors;
	}

	@Override
    public void setDaoFactory(DaoFactoryInterface daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
    public DaoFactoryInterface getDaoFactory() {
		return daoFactory;
	}
	/*************************************************************************
	 * 
	 * @see com.jdap.cdmr.patterns.dao.GenericDaoInterface#fixCriteriaRestrictionFetchEqualsTrueIssue(javax.persistence.criteria.Predicate)
	 *      
	 * 
	 *************************************************************************/
	//Override
	@Override
    public Expression<Boolean> fixCriteriaRestrictionFetchEqualsTrueIssue( Predicate expression )
	{
		return jpaDao.fixUglyEqualsTrueIssue( expression );		
	}

   //Override
   @Override
public void refresh( T entity )
   {
      jpaDao.refresh( entity );     
   }

   public List<Object[]> multiSelectByNamedQuery(String query,Pager pager, Object... values ){
		return jpaDao.multiSelectByNamedQuery(query,pager, values);
   }

   //Override
   @Override
public void clear()
   {
      jpaDao.clear();
   }

   //Override
   public List<Object[]> multiSelectByQuery( String query, Pager pager, Object... values )
   {
      return jpaDao.multiSelectByQuery(query,pager, values);
   }

   //Override
   public List<Tuple> findByCriteriaTuple( CriteriaQuery<Tuple> criteria,
          Pager pager ) {
      return jpaDao.findByCriteriaTuple( criteria, pager );
   }

   //Override
   @Override
public <C> C newEntity( Class<C> clazz )
   {
      return jpaDao.newEntity(clazz);
   }

   //Override
   @Override
public Class getEntityClass( Class clazz )
   {
      return jpaDao.getEntityClass( clazz );
   }
   
   //Override
   public String getEntityName( Class clazz )
   {
      return jpaDao.getEntityName( clazz );
   }  


   /* (non-Javadoc)
    * @see com.jdap.cdmr.patterns.dao.GenericDaoInterface#findAll()
    */
   //Override
   @Override
public List<T> findAll()
   {
      CriteriaBuilder cb = (CriteriaBuilder) getExpresionBuilder();
      CriteriaQuery<T> query = cb.createQuery( clazz );
      Root<T> from = query.from( clazz );
      query.select( from );
      return findByCriteria(query);
   }

   /* (non-Javadoc)
    * @see com.jdap.cdmr.patterns.dao.GenericDaoInterface#countAll()
    */
   //Override
   @Override
public Long countAll()
   {
      CriteriaBuilder cb = (CriteriaBuilder) getExpresionBuilder();
      CriteriaQuery<T> query = cb.createQuery( clazz );
      Root<T> from = query.from( clazz );
      query.select( from );
      return countByCriteria( query );
   }

   /* (non-Javadoc)
    * @see com.jdap.cdmr.patterns.dao.GenericDaoInterface#findAll(com.jdap.cdmr.patterns.pagination.Pager)
    */
   //Override
   @Override
public List<T> findAll( Pager pager )
   {
      CriteriaBuilder cb = (CriteriaBuilder) getExpresionBuilder();
      CriteriaQuery<T> query = cb.createQuery( clazz );
      Root<T> from = query.from( clazz );
      query.select( from );
      return findByCriteria(query, pager );
   }

   //Override
   @Override
public void flushOnCommit( boolean on )
   {
      jpaDao.flushOnCommit( on );
   }
   /*
   public int[] executeBatchInsert( final String querySql,final List<List<Object>> values,final List<Integer> type )
   {
      return jpaDao.executeBatchInsert( querySql, values, type );
   }*/

@Override
public int[] executeBatchInsert(String querySql, List<List<Object>> values,
		List<Integer> type) {
	// TODO Auto-generated method stub
	return null;
}
   
}
