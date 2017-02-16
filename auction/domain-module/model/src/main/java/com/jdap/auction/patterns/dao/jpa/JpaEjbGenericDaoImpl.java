package com.jdap.auction.patterns.dao.jpa;


import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import org.eclipse.persistence.expressions.ExpressionOperator;
import org.eclipse.persistence.internal.expressions.CompoundExpression;
import org.eclipse.persistence.internal.expressions.ConstantExpression;
import org.eclipse.persistence.internal.jpa.querydef.CompoundExpressionImpl;
import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.queries.DatabaseQuery;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.JpaCallback;
import com.jdap.auction.patterns.OID;
import com.jdap.auction.patterns.pagination.Pager;
import com.jdap.auction.patterns.session.SessionManager;
import com.jdap.auction.patterns.session.SessionParam;


public class JpaEjbGenericDaoImpl<T extends Object> implements JpaGenericDao<T, OID>
{
   private static final Logger logger = Logger.getLogger( JpaEjbGenericDaoImpl.class.getName() );

   private EntityManager em;
   private SessionManager sm;
   private Class<T> entityClass;

   /**
    * Create a new JpaGenericDao instance.
    * 
    */
   public JpaEjbGenericDaoImpl()
   {
      super();
   }

   /**
    * Sets the entity class
    */
   //@Override
   @Override
public void setEntityClass( Class<T> entityClass )
   {
      this.entityClass = entityClass;
   }

   /**
    * Gets the entity class
    */
   protected Class<T> getEntityClass()
   {
      return this.entityClass;
   }

   /**
    * Sets the entityManager to use.
    * 
    * @param em
    *           EntityManager to use
    */
   @Override
public void setEntityManager( EntityManager em )
   {
      this.em = em;
   }
   
   /*************************************************************************
   *
   * (non-Javadoc)
   * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#getSessionManager()
   *
   *************************************************************************/
   //@Override
   @Override
public SessionManager getSessionManager()
   {
      return this.sm;
   }
   
   /**
    * Sets the sessionManager to use this is used to store and retrieve values 
    * from session
    * 
    * @param em
    *           SessionManager to use
    */
   @Override
public void setSessionManager( SessionManager sm )
   {
      this.sm = sm;
   }

   // -------------------------------------------------------------------------
   // DAO methods for load, save, delete
   // -------------------------------------------------------------------------

   /**
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#contains(java.lang.Object)
    */
   //@Override
   @Override
public boolean contains( final T entity ) throws DataAccessException
   {
	   return em.contains( entity );

   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#find(java.io.Serializable)
    * 
    *************************************************************************/
   //@Override
   @Override
public T find( final OID id ) throws DataAccessException
   {
	   T t;
	   t = em.find( getEntityClass(), id );
	   return t;

   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#find(java.lang.String,
    *      com.jdap.cdmr.patterns.pagination.Pager)
    * 
    *************************************************************************/
   //@Override
   @Override
public List<T> find( String queryString, Pager pager ) throws DataAccessException
   {
      return find( queryString, pager, (Object[]) null );
   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#find(java.lang.String,
    *      com.jdap.cdmr.patterns.pagination.Pager, java.lang.Object[])
    * 
    *************************************************************************/
   //@Override
   @Override
public List<T> find( final String queryString, final Pager pager, final Object... values ) throws DataAccessException

   {
	   
	   Query queryObject = em.createQuery( queryString );
       prepareQuery( queryObject, pager );
       if ( values != null )
       {
          for ( int i = 0; i < values.length; i++ )
          {
             queryObject.setParameter( i + 1, values[i] );
          }
       }
       return queryObject.getResultList();

   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#findByNamedParams(java.lang.String,
    *      com.jdap.cdmr.patterns.pagination.Pager, java.util.Map)
    * 
    *************************************************************************/
   //@Override
   @Override
public List<T> findByNamedParams( final String queryString, final Pager pager,
         final Map<String, ?> params ) throws DataAccessException
   {
	   
	   Query queryObject = em.createQuery( queryString );
       prepareQuery( queryObject, pager );
       if ( params != null )
       {
          for ( Map.Entry<String, ?> entry : params.entrySet() )
          {
             queryObject.setParameter( entry.getKey(), entry.getValue() );
          }
       }
       return queryObject.getResultList();
      
   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#findByNamedQuery(java.lang.String,
    *      com.jdap.cdmr.patterns.pagination.Pager)
    * 
    *************************************************************************/
   //@Override
   @Override
public List<T> findByNamedQuery( String queryName, Pager pager ) throws DataAccessException
   {
      return findByNamedQuery( queryName, pager, (Object[]) null );
   }
   
  
   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#findByNamedQuery(java.lang.String,
    *      com.jdap.cdmr.patterns.pagination.Pager, java.lang.Object[])
    * 
    *************************************************************************/
   //@Override
   @Override
public List<T> findByNamedQuery( final String queryName, final Pager pager,
         final Object... values ) throws DataAccessException
   {
	   
       Query queryObject = em.createNamedQuery( queryName );
       prepareQuery( queryObject, pager );
       if ( values != null )
       {
          for ( int i = 0; i < values.length; i++ )
          {
             queryObject.setParameter( i + 1, values[i] );
          }
       }
       return queryObject.getResultList(); 
	 
     
   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#findByNamedQueryAndNamedParams(java.lang.String,
    *      com.jdap.cdmr.patterns.pagination.Pager, java.util.Map)
    * 
    *************************************************************************/
   //@Override
   @Override
public List<T> findByNamedQueryAndNamedParams( final String queryName, final Pager pager,
         final Map<String, ?> params ) throws DataAccessException
   {
	    //  logger.fine( " START1: findByNamedQueryAndNamedParams..........." );
       Query queryObject = em.createNamedQuery( queryName );
       prepareQuery( queryObject, pager );
       if ( params != null )
       {
          for ( Map.Entry<String, ?> entry : params.entrySet() )
          {
             queryObject.setParameter( entry.getKey(), entry.getValue() );
          }
         
          /*List<T> list = queryObject.getResultList();
          for(Object bid :list)
          {
        	  logger.warning(bid.toString());
          }*/
       }
       return queryObject.getResultList();
       
   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#flush()
    * 
    *************************************************************************/
   //@Override
   @Override
public void flush()
   {
	   em.flush();
   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#getReference(java.io.Serializable)
    * 
    *************************************************************************/
   //@Override
   @Override
public T getReference( final OID id )
   {
	   
	   return em.getReference( getEntityClass(), id );
   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#merge(java.lang.Object)
    * 
    *************************************************************************/
   //@Override
   @Override
public T merge( final T entity )
   {
	   return em.merge( entity );
   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#persist(java.lang.Object)
    * 
    *************************************************************************/
   //@Override
   @Override
public void persist( final T entity )
   {
	  
	   em.persist( entity );
     
   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#refresh(java.lang.Object)
    * 
    *************************************************************************/
   //@Override
   @Override
public void refresh( final T entity )
   {
	   em.refresh( entity );

   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#remove(java.lang.Object)
    * 
    *************************************************************************/
   //@Override
   @Override
public void remove( final T entity )
   {
	   em.remove( entity );
	    
   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#findByCriteria(javax.persistence.criteria.CriteriaQuery,
    *      com.jdap.cdmr.patterns.pagination.Pager)
    * 
    *************************************************************************/
  
   //@Override
   @Override
public List<T> findByCriteria( final CriteriaQuery<T> criteria, final Pager pager )

   {
	   /*CriteriaBuilder cb = em.getCriteriaBuilder();
	   CriteriaQuery cq = cb.createQuery();
	   Root e = cq.from(Bid.class);
	   Query query = em.createQuery(cq);
	   List<Bid> result = query.getResultList();*/
	   
       //TypedQuery<T> queryObject = em.createQuery( criteria );
       //prepareQuery( queryObject, pager );
       return null;//queryObject.getResultList();
	       
   }
   

   private void closeEntityManager( EntityManager em2 )
   {
      if ( em != null )
      {
         if ( em.isOpen() )
         {
            em.close();
         }
      }
   }

   private RuntimeException translateIfNecessary( RuntimeException ex )
   {
      // TODO implement a mapping between JPA exceptions a CDMR ones
      return new RuntimeException( ex );
   }

   /**
    * Prepare the given JPA query object. To be used within a JpaCallback.
    * <p>
    * Applies a transaction timeout, if any. If you don't use such timeouts, the call is a
    * no-op.
    * <p>
    * In general, prefer a proxied EntityManager instead, which will automatically apply the
    * transaction timeout (through the use of a special EntityManager proxy). You need to set
    * the "exposeNativeEntityManager" property to "false" to activate this. Note that you won't
    * be able to cast to a provider-specific JPA EntityManager class anymore then.
    * 
    * @param query
    *           the JPA query object
    * @see JpaCallback#doInJpa
    * @see EntityManagerFactoryUtils#applyTransactionTimeout
    * @see #setExposeNativeEntityManager
    */
   protected void prepareQuery( Query query, Pager pager )
   {
      if ( pager != null )
      {
         // sets the Query pagination support
         query.setFirstResult( pager.getStartPosition() );
         query.setMaxResults( pager.getPageSize() );
      }
      // enable cache
      // diable to fix hibernate isue Hibernate Core HHH-4459
      // query.setHint("org.hibernate.cacheable", true);
      // query.setHint("org.hibernate.cacheRegion", getEntityClass().getName()+".query");
   }

   /*************************************************************************
   *
   * Set the entity manager properties to execute a query
   *
   * @param em entity manager instance to set
   *
   *************************************************************************/
   protected void setSessionProperties( EntityManager em )
   {
     // System.out.println( "setSessionProperties SM = " + sm );
      if ( sm != null )
      {
         //System.out.println( "$$$$ sm.getProperties:" + sm.getProperties() );
         Map<SessionParam, Object> properties = sm.getProperties();
         //print values
         //em.clear();
         for ( SessionParam key : sm.getPropertyNames() )
         {
            //small fix to solve issue trying to set null property values
            if( sm.getProperty( key ) == null )
            {
               //we do need to call to database session properties to set null
               //RepeatableWriteUnitOfWork uow = em.unwrap(RepeatableWriteUnitOfWork.class);
              // uow.getParent().getProperties().remove(key.toString());
               //System.out.println( "$$$$ removed sm.getProperty:" + key.toString() );
            }
            else
            {
              // em.setProperty( key.toString(), sm.getProperty( key ) );
               //System.out.println( "$$$$ added sm.getProperty:" + key.toString() +", value:" + sm.getProperty( key ) );
            }
         }
      }
      //System.out.println( "$$$$ Em.Properties:" + em.getProperties() );
   }

  

  

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#count(java.lang.String)
    * 
    *************************************************************************/
   //@Override
   @Override
public long count( String queryString )
   {
      return count( queryString, (Object[]) null );
   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#count(java.lang.String,
    *      java.lang.Object[])
    * 
    *************************************************************************/
   //@Override
   @Override
public long count( final String queryString, final Object... values )

   {
	   
	   String countQuery = changeJPQLSelectToCount( queryString );

       Query queryObject = em.createQuery( countQuery );
       if ( values != null )
       {
          for ( int i = 0; i < values.length; i++ )
          {
             queryObject.setParameter( i + 1, values[i] );
          }
       }
       return (Long) queryObject.getSingleResult();
      
   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#countByNamedParams(java.lang.String,
    *      java.util.Map)
    * 
    *************************************************************************/
   //@Override
   @Override
public long countByNamedParams( final String queryString, final Map<String, ?> params )

   {
	   
	   // the easy soluction is to replace the string from "Select XXX from ..."
       // to "select count(*) from ..."

       // because the sentence is final we need to change assign it to a work var
       String countQuery = changeJPQLSelectToCount( queryString );

       Query queryObject = em.createQuery( countQuery );
       if ( params != null )
       {
          for ( Map.Entry<String, ?> entry : params.entrySet() )
          {
             queryObject.setParameter( entry.getKey(), entry.getValue() );
          }
       }
       return (Long) queryObject.getSingleResult();
    }
 

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#countByNamedQuery(java.lang.String)
    * 
    *************************************************************************/
   //@Override
   @Override
public long countByNamedQuery( String queryName )
   {
      return countByNamedQuery( queryName, (Object[]) null );
   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#countByNamedQuery(java.lang.String,
    *      java.lang.Object[])
    * 
    *************************************************************************/
   //@Override
   @Override
public long countByNamedQuery( final String queryName, final Object... values )

   {
	   
	   // We are going to retrieve the query string from the named query
       // this function is implemented in hibernate TODO implement it in toplink
       logger.finest( "queryName" + queryName );
       Query query = em.createNamedQuery( queryName );
       if ( query == null )
       {
          throw new PersistenceException( "cannot find namequery :" + queryName );
       }
       String queryString = getQueryStringFromQuery( query );
       if ( queryString == null )
       {
          throw new PersistenceException( "cannot parse namequery :" + queryName
                + " of class:" + query.getClass() + " instance:" + query );
       }
       // because the sentence is final we need to change assign it to a work var
       // because the sentence is final we need to change assign it to a work var
       String countQuery = changeJPQLSelectToCount( queryString );
       Query queryObject = em.createQuery( countQuery );
       if ( values != null )
       {
          for ( int i = 0; i < values.length; i++ )
          {
             queryObject.setParameter( i + 1, values[i] );
          }
       }
       return (Long) queryObject.getSingleResult();
     
   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#countByNamedQueryAndNamedParams(java.lang.String,
    *      java.util.Map)
    * 
    *************************************************************************/
   //@Override
   @Override
public long countByNamedQueryAndNamedParams( final String queryName,
         final Map<String, ?> params )
   {
 
            // We are going to retrieve the query string from the named query
            // this function is implemented in hibernate TODO implement it in toplink
            String queryString = getQueryStringFromQuery( em.createNamedQuery( queryName ) );
            // because the sentence is final we need to change assign it to a work var
            String countQuery = null;
            countQuery = changeJPQLSelectToCount( queryString );

            Query queryObject = em.createQuery( countQuery );
            if ( params != null )
            {
               for ( Map.Entry<String, ?> entry : params.entrySet() )
               {
                  queryObject.setParameter( entry.getKey(), entry.getValue() );
               }
            }
            List<Long> list = queryObject.getResultList();
            return ( list.size() > 0 ? list.iterator().next() : null );
         

   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#countByCriteria(javax.persistence.criteria.CriteriaQuery)
    * 
    *************************************************************************/
   //@Override
   @Override
public long countByCriteria( final CriteriaQuery<T> whereCriteria )

   {

            CriteriaBuilder cb = getCriteriaBuilder();
            CriteriaQuery<Long> countQuery = cb.createQuery( Long.class );
            countQuery.select( cb.count( whereCriteria.getRoots().iterator().next() ) );
            countQuery.getRoots().addAll( whereCriteria.getRoots() );
            if ( whereCriteria.getRestriction() != null )
            {
               countQuery.where( whereCriteria.getRestriction() );
            }
            //TypedQuery<Long> queryObject = em.createQuery( countQuery );
            Long records = null;//queryObject.getSingleResult();
            if ( records != null )
            {
               return records;
            }
            else
            {
               return 0L;
            }

   }

   /*************************************************************************
    * 
    * @see com.jdap.cdmr.persistence.dao.jpa.JpaGenericDao#getCriteriaBuilder()
    * 
    *************************************************************************/
   //@Override
   @Override
public CriteriaBuilder getCriteriaBuilder()
   {
      return null;//getEntityManager().getCriteriaBuilder();
   }

   private String getQueryStringFromQuery( Query queryObject )
   {
      DatabaseQuery databaseQuery = JpaHelper.getDatabaseQuery( queryObject );
      return databaseQuery.getJPQLString();
   }

   //@Override
   @Override
public int bulkUpdate( final String sentence, final Object... values )

   {
	   
	   Query queryObject = em.createQuery( sentence );
       if ( values != null )
       {
          for ( int i = 0; i < values.length; i++ )
          {
             queryObject.setParameter( i + 1, values[i] );
          }
       }
       return queryObject.executeUpdate();

   }

   //@Override
   @Override
public int bulkUpdateByNamedParams( final String sentence, final Map<String, ?> params )

   {
            Query queryObject = em.createQuery( sentence );
            if ( params != null )
            {
               for ( Map.Entry<String, ?> entry : params.entrySet() )
               {
                  queryObject.setParameter( entry.getKey(), entry.getValue() );
               }
            }
            return queryObject.executeUpdate();

   }

   //@Override
   @Override
public int bulkUpdateByNamedQuery( final String queryName, final Object... values )

   {

            Query queryObject = em.createNamedQuery( queryName );
            if ( values != null )
            {
               for ( int i = 0; i < values.length; i++ )
               {
                  queryObject.setParameter( i + 1, values[i] );
               }
            }
            return queryObject.executeUpdate();
         
   }

   //@Override
   @Override
public int bulkUpdateByNamedQueryAndNamedParams( final String queryName,
         final Map<String, ?> params )
   {

            Query queryObject = em.createNamedQuery( queryName );
            if ( params != null )
            {
               for ( Map.Entry<String, ?> entry : params.entrySet() )
               {
                  queryObject.setParameter( entry.getKey(), entry.getValue() );
               }
            }
            return queryObject.executeUpdate();

   }

   //@Override
   @Override
public EntityManager getEntityManager()
   {
      return em;
   }

   // the easy solution is to replace the string from "Select XXX from ..."
   // to "select count(XXX) from ..."
   private String changeJPQLSelectToCount( String queryString )
   {
      String fields;
      // check if has select
      if ( queryString.toUpperCase().indexOf( "SELECT" ) >= 0 )
      {
         fields =
               queryString.substring(
                     queryString.toUpperCase().indexOf( "SELECT" ) + "SELECT".length(),
                     queryString.toUpperCase().indexOf( "FROM " ) );
      }
      else
      {
         fields = queryString.substring( 0, queryString.toUpperCase().indexOf( "FROM " ) );
      }
      String from;
      // check if has order
      if ( queryString.toUpperCase().indexOf( "ORDER" ) >= 0 )
      {
         from =
               queryString.substring( queryString.toUpperCase().indexOf( "FROM " ),
                     queryString.toUpperCase().indexOf( "ORDER" ) );

      }
      else
      {
         from = queryString.substring( queryString.toUpperCase().indexOf( "FROM " ) );
      }

      // check if has group by now this is a real issue what happens if i we receive 
      // "GROUP  BY" 2 or 3 spaces instead of 1?
      if ( from.toUpperCase().indexOf( "GROUP BY" ) >= 0 )
      {
         from = from.substring( 0, from.toUpperCase().indexOf( "GROUP BY" ) );
      }

      return "SELECT count( " + fields + " ) " + from;
   }
   //@Override
   @Override
public Expression<Boolean> fixUglyEqualsTrueIssue( Predicate expression )
   {
      if ( expression instanceof CompoundExpressionImpl )
      {
         CompoundExpressionImpl compoundExpression = (CompoundExpressionImpl) expression;
         org.eclipse.persistence.expressions.Expression node =
               compoundExpression.getCurrentNode();
         List<Expression<?>> childExpresions = compoundExpression.getChildExpressions();
         // the validations is more complex than the fix itself, but what we are looking for is
         // ( expression = true ) node and remove the no sense "= true" parent node and just
         // leaving "expression"
         if ( node.getOperator().getType() == ExpressionOperator.ComparisonOperator
               && node.getOperator().getSelector() == ExpressionOperator.Equal
               && childExpresions.size() == 1
               && ( node instanceof CompoundExpression )
               && ( ( (CompoundExpression) node ).getSecondChild() instanceof ConstantExpression )
               && ( (ConstantExpression) ( ( (CompoundExpression) node ).getSecondChild() ) )
                     .getValue() instanceof Boolean
               && ( (Boolean) ( (ConstantExpression) ( ( (CompoundExpression) node )
                     .getSecondChild() ) ).getValue() ) )
         {
            // checking there is only one child node and it is CompoundExpression
            if ( childExpresions.get( 0 ) instanceof CompoundExpressionImpl )
            {
               // return the child node
               CompoundExpressionImpl childCompound =
                     (CompoundExpressionImpl) childExpresions.get( 0 );
               return childCompound;
            }
         }
      }
      return expression;
   }

   //@Override
   @Override
public List<Object[]> multiSelectByNamedQuery(final String query,final Pager pager, final Object... values )
   {
				   Query queryObject = em.createNamedQuery( query );
				   if(null != pager){
					   prepareQuery( queryObject, pager );
				   }
				   if ( values != null )
				   {
					   for ( int i = 0; i < values.length; i++ )
					   {
						   queryObject.setParameter( i + 1, values[i] );
					   }
				   }
				   List<Object[]> multipleValues = queryObject.getResultList();
				   return multipleValues;

   }

   //@Override
   @Override
public void clear()
   {

            //em.getEntityManagerFactory().getCache().evictAll();
            em.clear();

   }

   //@Override
   @Override
public List<Object[]> multiSelectByQuery( final String query, final Pager pager, final Object[] values ) 
   {


                  Query queryObject = em.createQuery( query );
                  if(null != pager){
                     prepareQuery( queryObject, pager );
                  }
                  if ( values != null )
                  {
                     for ( int i = 0; i < values.length; i++ )
                     {
                        queryObject.setParameter( i + 1, values[i] );
                     }
                  }
                  List<Object[]> multipleValues = queryObject.getResultList();
                  return multipleValues;

   }
   
   //@Override
   @Override
public List<Tuple> findByCriteriaTuple( final CriteriaQuery<Tuple> criteria,
         final Pager pager )
   {
 
            TypedQuery<Tuple> queryObject = null;//em.createQuery( criteria );
            prepareQuery( queryObject, pager );
            return queryObject.getResultList();

   }

@Override
public <C> C newEntity(Class<C> clazz) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Class<T> getEntityClass(Class clazz) {
	// TODO Auto-generated method stub
	return clazz;
}

@Override
public String getEntityName(Class clazz) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void flushOnCommit(boolean on) {
	// TODO Auto-generated method stub
	
}



 

}

