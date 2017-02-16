package com.jdap.auction.patterns.dao;


import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import com.jdap.auction.patterns.OID;
import com.jdap.auction.patterns.pagination.Pager;
import com.jdap.auction.patterns.persistence.PersistentObjectInterface;



/**
 * Basic interface for all data access objects.
 * 
 * @param <T>
 *            Type of the repository.
 */

public interface GenericDaoInterface<T extends PersistentObjectInterface> {
	/**
	 * Loads the {@link PersistedObject} that has the given <code>id</code>.
	 * 
	 * @param id
	 *            <code>id</code> of the object to find.
	 * @return {@link PersistedObject} with given <code>id</code>, or
	 *         <code>null</code> if not found. @ Underlying data error.
	 */

	public T findById(OID id);
	
	public T findById(String id);

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

	public List<T> findByCriteria(Object criteria);

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

	//public List<T> findByCriteria(Object criteria, Pager pager);

	/**
	 * Find by Query string and positional parameters.
	 * 
	 * @param queryString
	 *            - passed in query string.
	 * @param values
	 * @return @
	 * 
	 */

	public List<T> findByQuery(String queryString, Object... values);

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

	//public List<T> findByQuery(String queryString, Pager pager,
		//	Object... values);

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

	public List<T> findByQuery(String queryString, Map<String, ?> params);

   /**
    * Find by Query string and named parameters.
    * 
    * @param queryString
    *            - passed in query string.
    * @param param
    *            - map
    * @param user
    *            - user which access rights will be used to run the query
    * @return @
    * 
    */

   public List<T> findByQueryWithUser(String queryString, Map<String, ?> params, String user );

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

	//public List<T> findByQuery(String queryString, Pager pager,
		//	Map<String, ?> params);

   /**
    * Find by Query string and named parameters.
    * 
    * @param queryString
    *            - passed in query string.
    * @param pager
    *            object used to store pagination information
    * @param param
    *            - map
    * @param user
    *            - user which access rights will be used to run the query
    * @return @
    * 
    */

 //  public List<T> findByQueryWithUser(String queryString, Pager pager,
  //       Map<String, ?> params, String user);
	/**
	 * Find by named query with no parameters (static).
	 * 
	 * @param queryName
	 *            - passed in query string.
	 * @return @
	 * 
	 */

	public List<T> findByNamedQuery(String queryName);

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

	//public List<T> findByNamedQuery(String queryName, Pager pager);

	/**
	 * Find by named query with positional parameters.
	 * 
	 * @param queryName
	 *            - passed in query string.
	 * @param values
	 *            query parameter values
	 * @return @
	 * 
	 */

	public List<T> findByNamedQuery(String queryName, Object... values);

	/**
	 * Find by named query with positional parameters.
	 * 
	 * @param queryName
	 *            - passed in query string.
	 * @param pager
	 *            object used to store pagination information
	 * @param values
	 *            query parameter values
	 * @return @
	 * 
	 */

	//public List<T> findByNamedQuery(String queryName, Pager pager,
	//		Object... values);

	/**
	 * Find by named query with named parameters.
	 * 
	 * @param queryName
	 *            - passed in query string.
	 * @param params
	 * @return @
	 * 
	 */

	public List<T> findByNamedQuery(String queryName, Map<String, ?> params);

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

	//public List<T> findByNamedQuery(String queryName, Pager pager,
	//		Map<String, ?> params);

	/**
	 * Saves the given {@link PersistedObject}.
	 * 
	 * @param obj
	 *            {@link PersistedObject} to save. @ Underlying data error.
	 */

	public OID save(T obj);

	/**
	 * Saves the given {@link PersistedObject}. If the object is already
	 * persisted it will be updated.
	 * 
	 * @param obj
	 *            {@link PersistedObject} to save. @ Underlying data error.
	 */

	public OID saveOrUpdate(T obj);

	/**
	 * Saves the given list of {@link PersistedObject}. If the object is already
	 * persisted it will be updated. If some objects failed to be persisted,
	 * they will be returned back in HashMap mapped to the exception.
	 * 
	 * @param objs
	 *            {@link PersistedObject} list to save. @ Underlying data error.
	 */

	public Map<T, Exception> saveOrUpdateAll(Collection<T> objs);

	/**
	 * Deletes the given {@link PersistedObject}.
	 * 
	 * @param obj
	 *            {@link PersistedObject} to delete. @ Underlying data error.
	 */

	public void delete(T obj);

	/**
	 * Deletes the given {@link PersistedObject}.
	 * 
	 * @param id
	 *            <code>id</code> of the object to find. @ Underlying data
	 *            error.
	 */

	public void delete(OID id);

	/**
	 * Updates the persistence copy of the given {@link PersistedObject}.
	 * 
	 * @param obj
	 *            {@link PersistedObject} to update. @ Underlying data error.
	 */

	public void update(T obj);
	
   /**
    * Updates the persistence copy of the given {@link PersistedObject}.
    * 
    * @param obj
    *            {@link PersistedObject} to update. @ Underlying data error.
    */
   public void merge(T obj);

	/**
	 * Returns true if the given object is a part of the session cache.
	 * 
	 * @param obj
	 *            Object to check.
	 * @return True if it is a part of cache; false otherwise; @ Underlying data
	 *         error.
	 */

	public boolean contains(T obj);


	public long countByQuery(String queryString);

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

	public long countByQuery(String queryString, Object... values);

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

	public long countByQuery(String queryString, Map<String, ?> params);

	/*************************************************************************
	 * Returns the number of instances of {@link PersistedObject} based on the
	 * named query specified. This functions is different from criteria or JPQL
	 * queries because it does not change the query referenced by the name query
	 * to retrieve the count. instead the named query passed as parameter must
	 * make reference to the count query
	 * 
	 * @param queryName
	 *            named query which will return the number of records, this
	 *            query must return a single long value
	 * @return long @
	 * 
	 *************************************************************************/

	public long countByNamedQuery(String queryName);

	/*************************************************************************
	 * Returns the number of instances of {@link PersistedObject} based on the
	 * named query specified. This functions is different from criteria or JPQL
	 * queries because it does not change the query referenced by the name query
	 * to retrieve the count. instead the named query passed as parameter must
	 * make reference to the count query
	 * 
	 * @param queryName
	 *            named query which will return the number of records, this
	 *            query must return a single long value
	 * @param values
	 *            parameters to run the query
	 * @return long @
	 * 
	 *************************************************************************/

	public long countByNamedQuery(String queryName, Object... values);

	/*************************************************************************
	 * Returns the number of instances of {@link PersistedObject} based on the
	 * named query specified. This functions is different from criteria or JPQL
	 * queries because it does not change the query referenced by the name query
	 * to retrieve the count. instead the named query passed as parameter must
	 * make reference to the count query
	 * 
	 * @param queryName
	 *            named query which will return the number of records, this
	 *            query must return a single long value
	 * @param params
	 *            named parameters to run the query
	 * @return long @
	 * 
	 *************************************************************************/

	public long countByNamedQueryAndNamedParams(String queryName,
			Map<String, ?> params);

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

	public long countByCriteria(Object whereCriteria);

	/**
	 * Returns an utility class to build criteria expressions.
	 * 
	 * @return expression builder not associated with {@link PersistedObject}
	 * 
	 *         @
	 */
	public Object getExpresionBuilder();

	/**
	 * Flush the entity manager cache. @
	 */
	public void flush();
	
   /**
    * Clear the entity manager cache. Do not abuse.
    */
   public void clear();
   
   /**
    * Refresh the entity 
    */
   public void refresh(T entity);
   


	/*************************************************************************
	 * 
	 * Runs a bulk update sentence
	 * 
	 * @param sentence
	 *            string in JPQL
	 * @param values
	 *            parameters passed
	 * 
	 *            @ if it cannot execute update
	 * 
	 *************************************************************************/

	public void bulkUpdate(String sentence, Object... values);

	/*************************************************************************
	 * 
	 * Runs a bulk update sentence
	 * 
	 * @param sentence
	 *            string in JPQL
	 * @param params
	 *            map of named parameters (name, value) passed
	 * 
	 *            @ if it cannot execute update
	 * 
	 *************************************************************************/

	public void bulkUpdate(String sentence, Map<String, ?> params);

	/*************************************************************************
	 * 
	 * Runs a bulk update sentence
	 * 
	 * @param queryName
	 *            named query to be executed
	 * @param parameters
	 *            passed
	 * 
	 *            @ if it cannot execute update
	 * 
	 *************************************************************************/

	public void bulkUpdateByNamedQuery(String queryName, Object... values);

	/*************************************************************************
	 * 
	 * Runs a bulk update sentence
	 * 
	 * @param queryName
	 *            named query to be executed
	 * @param params
	 *            map of named parameters (name, value) passed
	 * 
	 *            @ if it cannot execute update
	 * 
	 *************************************************************************/

	public void bulkUpdateByNamedQuery(String queryName, Map<String, ?> params);

	/*************************************************************************
	 * 
	 * Sets the Dao Factory to allow to create new Daos
	 * 
	 * @param daoFactory
	 *            dao factory instance
	 * 
	 *************************************************************************/

	public void setDaoFactory(DaoFactoryInterface daoFactory);

	/*************************************************************************
	 * 
	 * Gets the Dao Factory to allow to create new Daos
	 * 
	 * @return dao factory instance
	 * 
	 *************************************************************************/

	public DaoFactoryInterface getDaoFactory();
	/*
	 * This is a fix to avoid the generation of "t0.OID IN (?, ?)) = true" or "((t0.CURRENTALARMSTATE = ?) = ?)" like statements
	 * while fetching existing where clause restrictions from a given criteriaQuery and then using it further.
	 * For some reason in clauses are compared to = true instead of been cast as Expression<Bollean> or comparison clauses generates
	 * two question marks first for value for compare and second for "true".It pre-processes input predicate to give correct predicate
	 * like "(t0.CURRENTALARMSTATE = ?)" for input predicate "((t0.CURRENTALARMSTATE = ?) = ?)"
	 * 
	 * @param  expression			predicate to correct
	 * 
	 * @return Expression<Boolean>	expression representing correct predicate
   	 *	
	 */
	public Expression<Boolean> fixCriteriaRestrictionFetchEqualsTrueIssue( Predicate expression );

	/*************************************************************************
	*
	* This method returns a list of arrays of objects retrieved by the named query
	* This function is useful to return more than one entity with same query or to 
	* return a collection of non entity values like oids.
	*
	* @param queryName name of the named query to be executed
	* @param pager
	* @param values named query parameters
	* @return
	*
	*************************************************************************/
	//List<Object[]> multiSelectByNamedQuery(String queryName,Pager pager, Object... values );
	
	

   /*************************************************************************
   *
   * This method returns a list of arrays of objects retrieved by a query pased as string
   * This function is useful to return more than one entity with same query or to 
   * return a collection of non entity values like oids.
   *
   * @param query query to be executed
   * @param pager
   * @param values query parameters
   * @return
   *
   *************************************************************************/
	//List<Object[]> multiSelectByQuery(String query,Pager pager, Object... values );
	
   /**
    * ***********************************************************************
    * 
    * This method selects multiple columns (or more than one entity or collection) in a select query as Tuple.
    * Each record selected is a Tuple and the {@link CriteriaQuery} passed should be of type Tuple
    * Query.
    * 
    * @param criteria
    * @param pager
    * @return
    * 
    ************************************************************************ 
    */
	//public List<Tuple> findByCriteriaTuple( CriteriaQuery<Tuple> criteria, Pager pager );
	
	/*************************************************************************
	*
	* Creates a new Entity instance to be persisted by DAO
	*
	* @return
	*
	*************************************************************************/
	public <C> C newEntity(Class<C> clazz);
	

   /*************************************************************************
   *
   * Gets the correct Class for the active time.
   *
   * @return
   *
   *************************************************************************/
   public Class getEntityClass( Class clazz );
   
   /*************************************************************************
   *
   * Gets all the rows for the Generic Entity T for which this dao is invoked. 
   *
   * @return List<T>
   *
   *************************************************************************/
   public List<T> findAll();
   
   /**
    * Basic count of all objects in the entity.
    * 
    * @return
    */
   
   public Long countAll();
   
   /**
    * Basic query for all objects, with paging.
    * @param pager
    * @return
    */
   
   public List<T> findAll( Pager pager );

   /**
    * set the commit method to flush only on commit, this is better if we want to make many changes 
    * but we do not want to flush every time, just one flush at commit transaction 
    */
   void flushOnCommit(boolean on);

   public int[] executeBatchInsert( final String querySql,final List<List<Object>> values,final List<Integer> type );

}
