package com.jdap.auction.patterns.session;


import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jdap.auction.patterns.session.SessionManager;
import com.jdap.auction.patterns.session.SessionParam;
import com.jdap.auction.patterns.session.SessionManagerThreadLocal;
//import com.jdap.auction.ds.DataSecurityServiceEJB;

@Stateless( name = "defaultSessionManager" )
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class DefaultSessionManager  implements SessionManager
{
   private SessionManager sessionManagerTL = SessionManagerThreadLocal.INSTANCE;
   
   private static final String AUTHORIZATION_AUTHORIZATION_SERVICES = "AUTH_AUTHORIZATIONSERVICES";
   private static final String AUTHORIZATION_AUTHORIZATION_SERVICES_PRINCIPAL = "AUTH_AUTHORIZATIONSERVICES_PRINCIPAL";
   private static final String AUTHORIZATION_AUTHORIZATION_SERVICES_CREDENTIAL = "AUTH_AUTHORIZATIONSERVICES_CREDENTIAL";
 
   private static final String USER_ANONYMOUS = "<anonymous>";
   private static final String DEFAULT_ACTION = "READ";
   private static final String DEFAULT_ROLE = "";
   private static final String ROLE_SUPER_USER = "auctionAdministrator";   
   private static final Character DELIMITER_CHARACTER = '|';
   private static final String TOPTENANCY = "TOPTENANCY";
   private static final String CONTEXT_FACTORY = "weblogic.jndi.WLInitialContextFactory";
   

   private SessionContext sessionContext;

   @Resource( name = AUTHORIZATION_AUTHORIZATION_SERVICES )
   private String authorizationServicesServer;

   @Resource( name = AUTHORIZATION_AUTHORIZATION_SERVICES_PRINCIPAL )
   private String authorizationServicesPrincipal;

   @Resource( name = AUTHORIZATION_AUTHORIZATION_SERVICES_CREDENTIAL )
   private String authorizationServicesCredential;


   @Resource
   public void setSessionContext( SessionContext sessionContext )
   {
      //System.out.println( "$$$$:" + this );
      this.sessionContext = sessionContext;
      //System.out.println( "$$$$ sessionContext:" + sessionContext );
   }

   // calls overwrite to turn skip validation to true in EJB environment
   //@Override
   @Override
public Map<SessionParam, Object> getProperties()
   {
      Map<SessionParam, Object> props;
      
      if ( !(this.hasProperties()) )
      {
         
         String user;
         
         if( sessionManagerTL.getProperty(SessionParam.AUTHORIZATION_USER) != null )
         {
            //if there are impersonation the run with this user, this feature is provided 
            //to allow reports to run queries without EJB principal
            user = ((String) sessionManagerTL.getProperty(SessionParam.AUTHORIZATION_USER)).toLowerCase();
         }
         else
         {
            //no impersonation then run with ejb context, there is always a principal at least <annonimous>
            user = sessionContext.getCallerPrincipal().getName().toLowerCase();
         }

         //By default we do not skip authorization
         String skipValidation = VALUE_FALSE;
         String roles;

         // In an EJB context principal is never null, if that information is not passed
         // it assumes ANONYMOUS 
         {
            StringBuffer rolesBuf = new StringBuffer();
            try
            {
               if(authorizationServicesServer == null || authorizationServicesServer.isEmpty())
               {
                  roles = DEFAULT_ROLE;
                  skipValidation = VALUE_TRUE;
               }
               else
               {
                  List<String> userRoles = getRoles( user );
                  for ( int i = 0; i < userRoles.size(); i++ )
                  {
                     //role must be not null and have no spaces before or after
                     String role = userRoles.get( i );
                     rolesBuf.append( role );
                     if ( i != userRoles.size() - 1 )
                     {
                        rolesBuf.append( DELIMITER_CHARACTER );
                     }
                     if(ROLE_SUPER_USER.equals( role ))
                     {
                        //Super User so we are going to skip authorization for this user 
                        skipValidation = VALUE_TRUE;
                     }
                  }
                  roles = rolesBuf.toString();
               }
            }
            catch ( Exception e )
            {
               // error retrieving roles
               e.printStackTrace();
               // take default
               roles = DEFAULT_ROLE;
            }
         }

         sessionManagerTL.setProperty( SessionParam.AUTHORIZATION_USER, user );
         sessionManagerTL.setProperty( SessionParam.AUTHORIZATION_TENANCY, TOPTENANCY );
         sessionManagerTL.setProperty( SessionParam.AUTHORIZATION_ROLE, roles );
         sessionManagerTL.setProperty( SessionParam.AUTHORIZATION_ACTION_TYPE, DEFAULT_ACTION );
         sessionManagerTL.setProperty( SessionParam.AUTHORIZATION_SKIP_VALIDATION, skipValidation );

      }
      props = sessionManagerTL.getProperties();
      //System.out.println( "$$$$ return getProperties():" + props );
      return props;
   }

   //@Override
   @Override
public boolean hasProperties()
   {
      //we do validate only Tenancy property, beause that property cannot be null after getProperties had been call 
      return sessionManagerTL.getProperty( SessionParam.AUTHORIZATION_TENANCY ) != null;
   }

   //@Override
   @Override
public void impersonateUser(String user)
   {
      //clean previous session properties
      this.clearProperties();
      //set the impersonation user as user
      sessionManagerTL.setProperty( SessionParam.AUTHORIZATION_USER, user );
      //next time someone ask for properties it will retrieve roles and access rights
      //of the impersonation user
   }

   protected List<String> getRoles( String principal ) throws Exception 
   {
      //System.out.println( "$$$$Calling get roles for:" + principal );
      Context context = getInitialContext();
      //DataSecurityServiceEJB dataSecurityServiceEJB = (DataSecurityServiceEJB) context.lookup( "DataSecurityServiceEJB#com.jdap.auction.ds.DataSecurityServiceEJB" );
      //List<String> roles = dataSecurityServiceEJB.getUserRoles( principal );
      //System.out.println( "$$$$got roles:"+roles );
      context.close();
      return null;
   }

   private Context getInitialContext() throws NamingException
   {
      //System.out.println( "$$$$Getting initial context for:" + authorizationServicesServer );
      Hashtable<String,String> env = new Hashtable<String,String>();
      // WebLogic Server 10.x connection details
      env.put( Context.INITIAL_CONTEXT_FACTORY, CONTEXT_FACTORY );
      env.put( Context.PROVIDER_URL, authorizationServicesServer );
      env.put( Context.SECURITY_PRINCIPAL, authorizationServicesPrincipal == null? USER_ANONYMOUS : authorizationServicesPrincipal);
      env.put( Context.SECURITY_CREDENTIALS, authorizationServicesCredential == null? "" : authorizationServicesCredential );
      InitialContext context = new InitialContext( env );
      return context;
   }

   //@Override
   @Override
public void setProperty( SessionParam key, Object value )
   {
      sessionManagerTL.setProperty( key, value );      
   }


   //@Override
   @Override
public Object getProperty( SessionParam key )
   {
      return sessionManagerTL.getProperty( key );
   }

   //@Override
   @Override
public void removeProperty( SessionParam key )
   {
      sessionManagerTL.removeProperty( key );      
   }

   //@Override
   @Override
public void setProperties( Map<SessionParam, Object> map )
   {
      sessionManagerTL.setProperties( map );      
   }

   //@Override
   @Override
public Set<SessionParam> getPropertyNames()
   {
      return sessionManagerTL.getPropertyNames();
   }

   //@Override
   @Override
public void clearProperties()
   {
      sessionManagerTL.clearProperties();      
   }
}

