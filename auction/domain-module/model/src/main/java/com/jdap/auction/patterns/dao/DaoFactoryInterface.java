package com.jdap.auction.patterns.dao;


//import javax.ejb.Local;
import javax.persistence.EntityManager;

import com.jdap.auction.patterns.persistence.PersistentObjectInterface;

//import com.jdap.cdmr.patterns.persistence.PersistentObjectInterface;



//@Local // we expose this interfase as local only. Remote access to DAO is no suported
public interface DaoFactoryInterface 
{
	/** 
	 * interface method to create a DAO 
	 */
	public <T extends PersistentObjectInterface> GenericDaoInterface<T> createDao( Class<T> clazz );
   /** 
    * interface method to create a DAO for DTO 
    */
   //public <E> DtoDaoInterface<E> createDtoDao( Class<E> clazz );
   /** 
    * interface method to flush transaction into db, this function is used to avoid container to flush the transaction outside
    * out exception handling AOP
    */
   public void flushTransaction();
   

   /**
    * Clears entity manager cache. Use with care.
    */
   public void clearTransaction();

   /** 
    * interface method to flush transaction into db, this function is used to avoid container to flush the transaction outside
    * out exception handling AOP
    */
   public void clearSessionProperties();
   
   /** 
    * interface method to expose entity manager Deprecated do not use
    */
   @Deprecated
   public EntityManager getEntityManager();
}
