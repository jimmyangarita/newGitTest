package com.jdap.auction.persistence.dao;

//~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.patterns.dao.DaoFactoryInterface;
import com.jdap.auction.patterns.dao.AbstractEjbDaoFactory;

//~--- JDK imports -------------------------------------------------------------

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
* Class description
*
*
* @version        9.0, 2012.February.25 04:09 PM
* @author         jdap Corporation
*/
@Stateless( name = "auctionDaoFactory",
          mappedName = "ejb.jdap.auction.auctionDaoFactory" )
public class AuctionEjbDaoFactory
      extends AbstractEjbDaoFactory implements DaoFactoryInterface
{
  /**
   * Method description
   *
   *
   * @param em
   */
  @Override
  @PersistenceContext( unitName = "auctionPU" )
  public void setEntityManager( EntityManager em )
  {
      super.setEntityManager( em );
  }
}
