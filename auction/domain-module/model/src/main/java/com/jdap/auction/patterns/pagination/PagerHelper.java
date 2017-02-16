package com.jdap.auction.patterns.pagination;

import com.jdap.auction.patterns.pagination.Pager;

public class PagerHelper
{
   public static void configureTotalPages( Pager pager, double count )
   {
      if ( pager != null && pager.isRetrieveTotal() )
      {
         pager.setTotalPages( ( (Double) Math.ceil( count / pager.getPageSize() ) ).intValue() );
      }
   }
}
