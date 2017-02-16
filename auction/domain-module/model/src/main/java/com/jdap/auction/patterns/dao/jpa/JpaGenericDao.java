package com.jdap.auction.patterns.dao.jpa;

//~--- non-JDK imports ---------------------------------------------------------

//import org.springframework.dao.DataAccessException;
import com.jdap.auction.patterns.pagination.Pager;
import com.jdap.auction.patterns.session.SessionManager;

//~--- JDK imports -------------------------------------------------------------

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;

/**
 * Interface description
 *
 *
 * @param <T>
 * @param <K>
 *
 * @version        9.0, 2013.May.06 06:22 PM
 * @author         JDAP Corporation
 */
@Local
public interface JpaGenericDao<T extends Object, K extends Serializable>
{
    void setEntityClass( Class<T> entityClass );

    T find( K id );

    T getReference( K id );

    boolean contains( T entity );

    void refresh( T entity );

    void persist( T entity );

    T merge( T entity );

    void remove( T entity );

    void flush();

    List<T> find( String queryString, Pager pager );

    List<T> find( String queryString, Pager pager, Object... values );

    List<T> findByNamedParams( String queryString, Pager pager, Map<String, ?> params );

    List<T> findByNamedQuery( String queryName, Pager pager );

    List<T> findByNamedQuery( String queryName, Pager pager, Object... values );

    List<T> findByNamedQueryAndNamedParams( String queryName, Pager pager, Map<String, ?> params );

    List<T> findByCriteria( CriteriaQuery<T> criteria, Pager pager );

    long count( String queryString );

    long count( String queryString, Object... values );

    long countByNamedParams( String queryString, Map<String, ?> params );

    long countByNamedQuery( String queryName );

    long countByNamedQuery( String queryName, Object... values );

    long countByNamedQueryAndNamedParams( String queryName, Map<String, ?> params );

    long countByCriteria( CriteriaQuery<T> whereCriteria );

    EntityManager getEntityManager();

    void setEntityManager( EntityManager em );

    void setSessionManager( SessionManager sm );

    SessionManager getSessionManager();

    CriteriaBuilder getCriteriaBuilder();

    int bulkUpdate( String sentence, Object... values );

    int bulkUpdateByNamedParams( String sentence, Map<String, ?> params );

    int bulkUpdateByNamedQuery( String queryName, Object... values );

    int bulkUpdateByNamedQueryAndNamedParams( String queryName, Map<String, ?> params );

    /**
     * Method description
     *
     *
     * @param expression
     *
     * @return
     */
    public Expression<Boolean> fixUglyEqualsTrueIssue( Predicate expression );

    List<Object[]> multiSelectByNamedQuery( String queryName, Pager pager, Object... values );

    void clear();

     List<Object[]> multiSelectByQuery( String query, Pager pager, Object[] values ) ;

    /**
     * Method description
     *
     *
     * @param criteria
     * @param pager
     *
     * @return
     */
    public List<Tuple> findByCriteriaTuple( final CriteriaQuery<Tuple> criteria, final Pager pager );

    /**
     * Method description
     *
     *
     * @param clazz
     * @param <C>
     *
     * @return
     */
    public <C> C newEntity( Class<C> clazz );

    /**
     * Method description
     *
     *
     * @param clazz
     *
     * @return
     */
    public Class<T> getEntityClass( Class clazz );

	/*It is not for EJB*/
    
    String getEntityName(Class clazz);

	void flushOnCommit(boolean on);
}
