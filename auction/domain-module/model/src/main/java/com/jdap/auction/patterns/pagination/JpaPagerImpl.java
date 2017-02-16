package com.jdap.auction.patterns.pagination;


public class JpaPagerImpl implements Pager
{
   private int pageSize = 10;
   private int currentPage = 1;
   private int totalPages = 0;
   private boolean retrieveTotal = false;
   private int firstResult = -1; 

   /**
    * Simple method to reduce the amount of code where pagers are being used.
    * 
    * @param start
    * @param increment
    * @return
    */
   
   public static Pager newInstance( int start, int increment )
   {
      Pager pager = new JpaPagerImpl();
      
      if ( increment <= 0 )
      {
         throw new RuntimeException( "Pager, Increment size should be greater than ZERO" );
      }
      pager.setCurrentPage( (start + increment - 1) / increment );
      pager.setPageSize( increment );
      
      return( pager );
   }
   
   //Override
   @Override
public int getPageSize()
   {
      return this.pageSize;
   }

   //Override
   @Override
public void setPageSize( int pagesize )
   {
      this.pageSize = pagesize;
   }

   //Override
   @Override
public boolean hasNextPage()
   {
      return totalPages != 0 && currentPage < totalPages;
   }

   //Override
   @Override
public int getTotalPages()
   {
      return totalPages;
   }

   //Override
   @Override
public int getCurrentPage()
   {
      return currentPage;
   }

   //Override
   @Override
public void setCurrentPage( int currentPage )
   {
      this.currentPage = currentPage;
   }

   //Override
   @Override
public void setRetrieveTotal( boolean retrieveTotal )
   {
      this.retrieveTotal = retrieveTotal;
   }

   //Override
   @Override
public boolean isRetrieveTotal()
   {
      return retrieveTotal;
   }

   //Override
   @Override
public void setTotalPages( int totalPages )
   {
      this.totalPages = totalPages;
   }

   //Override
   @Override
public int getStartPosition()
   {
      if( firstResult < 0 )
      {
         return ( this.currentPage > 0 ? this.currentPage - 1 : 0 ) * this.pageSize;
      }
      else
      {
         return firstResult;
      }
   }

   //Override
   @Override
public void setStartPosition( int start )
   {
      firstResult = start;
   }
   
}
