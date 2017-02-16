package com.jdap.auction.patterns.repository;

import java.util.List;
import java.util.Map;

import com.jdap.auction.patterns.OID;
import com.jdap.auction.patterns.pagination.Pager;
import com.jdap.auction.patterns.persistence.PersistentObjectInterface;

/**
 * Basic interface to a repository object, it sits above 
 * the DAO object or objects and provides a common place to
 * handle higher level functions for the objects.
 * 
 * Note: JPA code should not migrate above this layer.
 * 
 * @param <T>
 *           Type of the repository.
 */

public interface RepositoryInterface<T extends PersistentObjectInterface>
{
   /**
    * Loads the {@link PersistedObject} that has the given <code>id</code>.
    * 
    * @param id
    *           <code>id</code> of the object to find.
    * @return {@link PersistedObject} with given <code>id</code>, or <code>null</code> if not
    *         found.
    */
   
   public T findById( OID id );


   /**
    * Saves the given {@link PersistedObject}.
    * 
    * @param obj
    *           {@link PersistedObject} to save.
    */

   public OID save( T obj );

   /**
    * Saves the given {@link PersistedObject}. If the object is already persisted it will be
    * updated.
    * 
    * @param obj
    *           {@link PersistedObject} to save.
    */
   
   public OID saveOrUpdate( T obj );

   /**
    * Deletes the given {@link PersistedObject}.
    * 
    * @param obj
    *           {@link PersistedObject} to delete.
    */
   
   public void delete( T obj );

   /**
    * Updates the persistence copy of the given {@link PersistedObject}.
    * 
    * @param obj PersistedObject to update.
    */
   
   public void update( T obj );
   
   /**
    * Merges the persistence copy of the given {@link PersistedObject}.
    * 
    * @param obj PersistedObject to update.
    */
   
   public void merge( T obj );

   /**
    * Returns true if the given object is a part of the session cache.
    * 
    * @param obj
    *           Object to check.
    * @return True if it is a part of cache; false otherwise;
    */
   
   public boolean contains( T obj );

   
   /*************************************************************************
   *
   * find and validate existence of an Entity, else throw a MissingDataException.
   *
   * @param <T>
   * @param clazz
   * @param oid
   * @return Entity
   *
   *************************************************************************/
  public <P extends PersistentObjectInterface> P findValidEntityById( Class<P>  clazz, String oid );
   
   /**
    * @return all entities of the concrete implemeting repository
    */
   public List<T> findAll();

   /**
    * @return - returns a count of all the entities for this repo.
    * 
    */
   public Long countAll();
   
   /**
    * @return all entities of the concrete implemeting repository with
    * paging.
    */
   public List<T> findAll( Pager pager );
   
   /*************************************************************************
   *
   * Forcefully commit changes into database and synchronize entity with
   * Persistent context .
 
   *************************************************************************/
   
   public void flush();

   /*************************************************************************
   *
   * Allows to send JPQL queries and executes it with pagination support
   *
   * @param jpqlStmt JPQL statement
   * @param firstResult first record to be returned in current page
   * @param maxResults max records to be returned in current page
   * @return
   *
   *************************************************************************/
   public Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

   /*************************************************************************
   *
   * Allows to send JPQL queries and executes it with pagination support
   *
   * @param jpqlStmt JPQL statement
   * @param params query parameters
   * @param firstResult first record to be returned in current page
   * @param maxResults max records to be returned in current page
   * @return
   *
   *************************************************************************/
   public Object queryByRange(String jpqlStmt, Map<String, Object> params , int firstResult, int maxResults);


   /*************************************************************************
   *
   * Clears the persistence context
   *
   *
   *************************************************************************/
   public void clear(); 

   /*************************************************************************
   *
   * Creates a new Entity instance to be persisted by Repository
   *
   * @return
   *
   *************************************************************************/
   public <C> C newEntity(Class<C> clazz);
   
   /**
    * Retrieves rows (OR count as Long) based on the JPQL passed.
    * 
    * @param jpqlStmt      JPQL query
    * @param firstResult   starting number in the range of results.
    * @param maxResults    max range of result.
    * @param entityClazz The entity class for which jpql statement is written.
    * @return Object       Could be a list of resulting entity or Long (count of rows) based on the JPQL.
    */
   public Object queryByRange( String jpqlStmt, int firstResult, int maxResults,
         Class<? extends PersistentObjectInterface> entityClazz );
   
   /*************************************************************************
   *
   * Retrieves rows (OR count as Long) based on the JPQL passed.
   * 
   * @param jpqlStmt      JPQL query
   * @param params        Query parameters
   * @param firstResult   starting number in the range of results.
   * @param maxResults    max range of result.
   * @param entityClazz   The entity class for which jpql statement is written.
   * @return Object       Could be a list of resulting entity or Long (count of rows) based on the JPQL.
   *
   *************************************************************************/
   public Object queryByRange( String jpqlStmt, Map<String, Object> params , int firstResult, int maxResults, 
         Class<? extends PersistentObjectInterface> entityClazz);
   
   /**
    * Wraps a find by name query so we don't have to create the DAO each time..
    * 
    * @param id
    *           <code>id</code> of the object to find.
    * @return {@link PersistedObject} with given <code>id</code>, or <code>null</code> if not
    *         found.
    */
   
   public List<T> findByNamedQuery( String query, Map<String, Object> paramsMap );

   /**
    *
    * Find the entity that implements the interface provided as parameter in the list of entities provided if entity is not found or 
    * it does not implement the iface it will return null.
    *
    * @param <I>
    * @param oid entity oid
    * @param notTemporalEntities list of non temporal entities
    * @param temporalEntities list of temporal entities
    * @param iface interface to be implemented
    * @return
    *
    */
   public <I> I findEntityInstanceByInterface( String oid, String[] notTemporalEntities, String[] temporalEntities, Class<I> iface );

}

