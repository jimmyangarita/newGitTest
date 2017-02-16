package com.jdap.auction.exceptions;

public class AuctionServicePublishEventException extends AuctionServiceException{

	 /**
    *
    */
   private static final long serialVersionUID = 8931720041033958013L;

   /**
    * @param message
    */
   public AuctionServicePublishEventException( String message )
   {
       super( message );
   }

   /**
    * @param cause
    */
   public AuctionServicePublishEventException( Throwable cause )
   {
       super( cause );
   }

   /**
    * @param message
    * @param cause
    */
   public AuctionServicePublishEventException( String message, Throwable cause )
   {
       super( message, cause );
   }


}
