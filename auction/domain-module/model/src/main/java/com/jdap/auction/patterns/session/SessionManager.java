package com.jdap.auction.patterns.session;

import java.util.Map;
import java.util.Set;

import javax.ejb.Local;



/*************************************************************************
 * 
 * INterfase which allow to set and retrieve properties from the Session context. This class
 * will be used by Authorization and Temporality to set its session properties.
 * 
 *
 *************************************************************************/
@Local
public interface SessionManager
{
   public static final String VALUE_TRUE = "TRUE";
   public static final String VALUE_FALSE = "FALSE";   

   /*************************************************************************
    * 
    * Sets a property value in the session context.
    * 
    * @param key
    * @param value
    * 
    *************************************************************************/
   void setProperty( SessionParam key, Object value );

   /*************************************************************************
    * 
    * Sets a map of properties values in the session context.
    * 
    * @param map
    *           of properties and values
    * 
    *************************************************************************/
   void setProperties( Map<SessionParam, Object> map );

   /*************************************************************************
    * 
    * Gets a property value in the session context.
    * 
    * @param key
    * @return
    * 
    *************************************************************************/
   Object getProperty( SessionParam key );

   /*************************************************************************
    * 
    * Gets as set whit all the property names available in the session context.
    * 
    * @return
    * 
    *************************************************************************/
   Set<SessionParam> getPropertyNames();

   /*************************************************************************
    * 
    * returns all the session context properties.
    * 
    * @return
    * 
    *************************************************************************/
   Map<SessionParam, Object> getProperties();

   /*************************************************************************
    * 
    * Clears session properties.
    * 
    * 
    *************************************************************************/
   void clearProperties();

   /*************************************************************************
    * 
    * Removes a property from the session context.
    * 
    * @param key
    * @return
    * 
    *************************************************************************/
   void removeProperty( SessionParam key );

   /*************************************************************************
    * 
    * returns true if at least one property had been set in the session
    * 
    * 
    *************************************************************************/
   boolean hasProperties();

   /*************************************************************************
   *
   * Allows to impersonate a user to run queries with this user access rights
   *
   * @param user
   *
   *************************************************************************/
   void impersonateUser( String user );
}

