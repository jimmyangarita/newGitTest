package com.jdap.auction.model.eclipselink;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Logger;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;
/*************************************************************************
 *
 * GMTConverter is an EclipseLink {@link Converter} that is responsible
 * for converting {@link Calendar} objects between the server-local
 * {@link TimeZone} and the GMT {@link TimeZone}.
 * 
 * @author dmuirhead - 02/08/2011
 *
 *************************************************************************/
public class GMTConverter implements Converter
{
   //CONSTANTS

   /** The serialization uid */
   private static final long serialVersionUID = 5925360635372234652L;

   /** The {@link Log} to use when emitting log messages */
   private static final Logger LOG = Logger.getLogger( GMTConverter.class.getName() );

   /* The Greenwich Mean Time (UTC) time zone */
   private static final TimeZone GMT = TimeZone.getTimeZone( "GMT" );
   
   /*************************************************************************
    *
    * Constructs an UTCConverter.
    *
    *************************************************************************/
   public GMTConverter()
   {
      super();
   }
   
   public static Timestamp getCurrentTimeStamp()
   {
	    Calendar calendar = Calendar.getInstance();
	  	
	    //LOG.info( "JDAP TIME Format10: " + calendar.getTime() + "" + "\n Format20: " + calendar.getTime().getTime() );
	
	    Long timeStamp = calendar.getTime().getTime();
	    Timestamp currentTime = new Timestamp( timeStamp );
	
	    return currentTime;
	}
   
   public static Timestamp getCurretTimestampPlusDays(int numberDays)
   {
	   Calendar calendar = Calendar.getInstance();
       
       calendar.add(Calendar.DAY_OF_MONTH, numberDays);
       Timestamp futureTime = new Timestamp(calendar.getTime().getTime());
	
	    return futureTime;
	}

   //CONVERTER

   /* 
    * @see org.eclipse.persistence.mappings.converters.Converter#convertObjectValueToDataValue(java.lang.Object, org.eclipse.persistence.sessions.Session)
    */
  // @Override
   @Override
public Object convertObjectValueToDataValue(Object objectValue, Session session)
   {
      return convertToTimeZone( (Calendar) objectValue, GMT );
   }

   /* 
    * @see org.eclipse.persistence.mappings.converters.Converter#convertDataValueToObjectValue(java.lang.Object, org.eclipse.persistence.sessions.Session)
    */
   //@Override
   @Override
public Object convertDataValueToObjectValue(Object dataValue, Session session)
   {
      return convertToTimeZone( (Calendar) dataValue, TimeZone.getDefault() );
   }

   /* 
    * @see org.eclipse.persistence.mappings.converters.Converter#isMutable()
    */
   //@Override
   @Override
public boolean isMutable()
   {
      return true;
   }

   /* 
    * @see org.eclipse.persistence.mappings.converters.Converter#initialize(org.eclipse.persistence.mappings.DatabaseMapping, org.eclipse.persistence.sessions.Session)
    */
  // @Override
   @Override
public void initialize(DatabaseMapping mapping, Session session)
   {
      return;
   }

   //UTILITY METHOS

   protected Calendar convertToTimeZone( Calendar unconverted, TimeZone targetTimezone )
   {
      Calendar converted = null;
      if ( unconverted != null)
      {
         converted = Calendar.getInstance( targetTimezone );
         converted.setTimeInMillis( unconverted.getTimeInMillis() );

         LOG.finest( 
               "convertToTimeZone - unconverted=" + 
            toString(unconverted) + 
            ", targetTimezone=" + 
            targetTimezone.getID() + 
            ", converted=" + 
            toString(converted)
         );
      }
      return converted;
   }

   protected String toString( Calendar calendar )
   {
      StringBuilder builder = new StringBuilder();
      builder
         .append("Calendar(")
         .append( calendar.get(Calendar.YEAR)).append( "-" )
         .append( calendar.get( Calendar.MONTH) ).append("-")
         .append( calendar.get( Calendar.DAY_OF_MONTH) ).append(" ")
         .append( calendar.get( Calendar.HOUR_OF_DAY) ).append( ":" )
         .append( calendar.get( Calendar.MINUTE) ).append( ":" )
         .append( calendar.get( Calendar.SECOND) ).append( "." )
         .append( calendar.get( Calendar.MILLISECOND) )
         .append(")");
      return builder.toString();
   }
}
