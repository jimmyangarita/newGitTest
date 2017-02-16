package com.jdap.auction.patterns.session;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.jdap.auction.patterns.session.SessionManager;
import com.jdap.auction.patterns.session.SessionParam;

public enum SessionManagerThreadLocal implements SessionManager
{
   INSTANCE;
   
   private static ThreadLocal<Map<SessionParam, Object>> session = new ThreadLocal<Map<SessionParam, Object>>();
  
   //@Override
   @Override
public void setProperty( SessionParam key, Object value )
   {
      getProperties().put( key, value );
   }

   //@Override
   @Override
public Object getProperty( SessionParam key )
   {
      return getProperties().get( key );
   }

   //@Override
   @Override
public Set<SessionParam> getPropertyNames()
   {
      return getProperties().keySet();
   }

   //@Override
   @Override
public Map<SessionParam, Object> getProperties()
   {
      Map<SessionParam, Object> properties = session.get();
      if ( properties == null )
      {
         properties = new HashMap<SessionParam, Object>();
         session.set( properties );
      }
      return properties;
   }

   //@Override
   @Override
public void clearProperties()
   {
      session.set( null );
   }

   //@Override
   @Override
public void setProperties( Map<SessionParam, Object> map )
   {
      getProperties().putAll( map );
   }

   //@Override
   @Override
public boolean hasProperties()
   {
      return ( ( session.get() != null ) && ( session.get().size() > 0 ) );
   }

   //@Override
   @Override
public void removeProperty( SessionParam key )
   {
      if ( getProperties().containsKey( key ) )
      {
         getProperties().remove( key );
      }
   }

   //@Override
   @Override
public void impersonateUser( String user )
   {
      // Do nothing, this method should be implemented by a real Session Manager implementation 
      // this singleton is just a thread local variable access
   }
}
