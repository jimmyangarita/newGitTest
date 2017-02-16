package com.jdap.auction.patterns.pagination;


import java.io.Serializable;

public interface Pager extends Serializable
{

   int getPageSize();

   void setPageSize( int pagesize );

   boolean hasNextPage();

   int getTotalPages();

   int getCurrentPage();

   void setCurrentPage( int currentPage );

   void setRetrieveTotal( boolean retrieveTotal );

   boolean isRetrieveTotal();

   void setTotalPages( int totalPages );

   int getStartPosition();

   void setStartPosition( int start );

}
