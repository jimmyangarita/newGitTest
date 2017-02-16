package com.jdap.auction.patterns.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ServiceLoader;
import java.util.Vector;

import javax.persistence.EntityManager;

import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.queries.DatabaseQuery;
import org.eclipse.persistence.sessions.server.ServerSession;

import java.util.logging.Logger;

//import com.jdap.auction.model.eclipselink.WeavingDetection;
import com.jdap.auction.patterns.dao.DaoFactoryInterface;
//import com.jdap.auction.patterns.dao.DtoDaoInterface;
import com.jdap.auction.patterns.dao.GenericDaoInterface;
//import com.jdap.auction.patterns.exception.CdmVersion;
import com.jdap.auction.patterns.OID;
import com.jdap.auction.patterns.persistence.PersistentObjectInterface;
import com.jdap.auction.patterns.session.SessionManager;
import com.jdap.auction.patterns.dao.jpa.JpaEjbGenericDaoImpl;
import com.jdap.auction.patterns.dao.jpa.JpaGenericDao;


// The concrete class should be declare as an EJB and must be placed at
// same Jar as the one declaring the persistence unit beacuse of that we need
// to declare this class as abstract an leave the concrete to define the 
// persistence unit and deploy it in the same jar as the concrete
public abstract class AbstractEjbDaoFactory implements DaoFactoryInterface
{
   private static final Logger LOG = Logger.getLogger( AbstractEjbDaoFactory.class.getName() );
   
   private EntityManager em;
   private SessionManager sm;

   /**
    * Constructor
    */

   public AbstractEjbDaoFactory()
   {
      //LOG.fine( "Weaving state: " + WeavingDetection.isWeavingOn() );
      LOG.fine( "Creating AbstractEjbDaoFactory. "  );
   }

   /** interface method to create a DAO 
    *  THe Factory method is generic T the class is not.*/
   
   @Override
@SuppressWarnings(
   { "rawtypes", "unchecked" } )
   public <T extends PersistentObjectInterface> GenericDaoInterface<T> createDao(
         Class<T> clazz )
   {
      // find if there is a class that implements dao interface for that class
      ServiceLoader<GenericDaoInterface> daoServices =
            ServiceLoader.load( GenericDaoInterface.class );
      for ( GenericDaoInterface daoImpl : daoServices )
      {
         if ( isDaoImpl( daoImpl.getClass(), clazz ) )
         {
        	 LOG.warning("PROBLEM is a class that implements dao interface for that class \nXXXX\n");
            return daoImpl;
         }
      }
      // an specific implementation of EjbDao
      JpaGenericDao<T, OID> jpaDao = new JpaEjbGenericDaoImpl<T>();
      //jpaDao.setEntityManager( emf.createEntityManager() );
      jpaDao.setEntityManager( em );
      jpaDao.setSessionManager( sm );
      GenericDaoInterface<T> dao = new GenericDaoImpl<T>( clazz, jpaDao );
      dao.setDaoFactory( this );
      return ( dao );
   }

   /** interface method to create a DTO DAO */
  /* @SuppressWarnings(
   { "rawtypes", "unchecked" } )
   public <T> DtoDaoInterface<T> createDtoDao( Class<T> clazz )
   {
      // find if there is a class that implements dao interface for that class
      ServiceLoader<DtoDaoInterface> daoServices = ServiceLoader.load( DtoDaoInterface.class );
      for ( DtoDaoInterface daoImpl : daoServices )
      {
         if ( isDtoDaoImpl( daoImpl.getClass(), clazz ) )
         {
            return daoImpl;
         }
      }
      // an specific implementation of EjbDao
      JpaGenericDao<T, OID> jpaDao = new JpaEjbGenericDaoImpl<T>();
      //jpaDao.setEntityManager( emf.createEntityManager() );
      jpaDao.setEntityManager( em );
      jpaDao.setSessionManager( sm );
      // Create or look up our DAO objects.
      DtoDaoImpl<T> dao = new DtoDaoImpl<T>( clazz, jpaDao );

      return ( dao );
   }*/

   /*************************************************************************
    * 
    * Checks if a GenericDaoInterfase implements the generic dao interface for an specific
    * entity.
    * 
    * @param class1
    *           class that implements GenericDaoInterface
    * @param clazz
    *           class that implements PersistentObjectInterface
    * @return true if daoClazz is a valid dao implementation of entityClazz
    * 
    *************************************************************************/
   @SuppressWarnings( "rawtypes" )
   private boolean isDaoImpl( Class<? extends GenericDaoInterface> class1, Class<?> clazz )
   {
      Class<?> entity = getGenericDeclarationClass( class1, GenericDaoInterface.class, "T" );
      if ( clazz.equals( entity ) )
      {
         return true;
      }
      else
      {
         return false;
      }
   }

   /*************************************************************************
    * 
    * Checks if a GenericDaoInterfase implements the generic dao interface for an specific
    * entity.
    * 
    * @param class1
    *           class that implements GenericDaoInterface
    * @param clazz
    *           class that implements PersistentObjectInterface
    * @return true if daoClazz is a valid dao implementation of entityClazz
    * 
    *************************************************************************/
   @SuppressWarnings( "rawtypes" )
   /*private boolean isDtoDaoImpl( Class<? extends DtoDaoInterface> class1, Class<?> clazz )
   {
      Class<?> entity = getGenericDeclarationClass( class1, DtoDaoInterface.class, "T" );
      if ( clazz.equals( entity ) )
      {
         return true;
      }
      else
      {
         return false;
      }
   }*/

   /*************************************************************************
    * 
    * This function Retrieves the Class that is defined as a parameter in a Generic Type
    * declaration at a parent level and inheritance by a Subclass. by example if we have a
    * class Parent&lt;D> and an another class Child extends Parent&lt;String> and a class
    * GrandChild extends Child a call like: getGenericDeclarationClass(GrandChild.class ,
    * Parent.class, "D") Will return String.class
    * 
    * @param instanceClazz
    *           the child class that does not implement the generic declaration but a parent
    *           class does.
    * @param genericClazz
    *           the parent class that implements the Generic declaration
    * @param genericName
    *           the generic name we are looking for retrieve the class
    * @return the class that is referenced in genericClazz declaration with genericName name
    * 
    *************************************************************************/
   //@SuppressWarnings( "null" )
   private Class<?> getGenericDeclarationClass( Class<?> instanceClazz, Class<?> genericClazz,
         String genericName )
   {
      Class<?> genericDeclarationClazz = null;
      //we need to pass a genericInterface class otherwise this validation does not make sense
      if( genericClazz != null )
      {
         // set the current type with the first generic superclass
         Type superClazz = instanceClazz.getGenericSuperclass();
         // validate the class is not an Object class
         main:
         while ( !Object.class.equals( superClazz ) )
         {
            // check if the class is a parameterized type
            if ( superClazz instanceof ParameterizedType )
            {
               ParameterizedType pt = (ParameterizedType) superClazz;
               // check if we reach the class we want to ask for
               if ( genericClazz.isAssignableFrom( (Class) pt.getRawType() ) )
               {
                  TypeVariable<?> type = null;
                  // we start looking for the parameter that matches the generic name
                  for ( int i = 0; i < genericClazz.getTypeParameters().length; i++ )
                  {
                     // get the parameter i and check if the name matches
                     type = genericClazz.getTypeParameters()[i];
                     if ( type.getName().equals( genericName ) )
                     {
                        // if the name matches then we retrieve the value passed as Type
                        // Argument
                        genericDeclarationClazz = (Class<?>) pt.getActualTypeArguments()[i];
                        // we found the value so break the while condition to finish the search
                        break main;
                     }
                  }
               }
               // if we couldn't find the generic declaration let's try with one level up
               superClazz = ( (Class<?>) pt.getRawType() ).getGenericSuperclass();
            }
            else
            {
               // let's try with one level up
               superClazz = ( (Class<?>) superClazz ).getGenericSuperclass();
            }
         }
      }
      // return the class object or null if it wasn't found
      return genericDeclarationClazz;
   }

   /**
    * Sets the entityManager to use.
    * 
    * @param em
    *           EntityManager to use
    */
   // We need to provide the persistence unit name here (in the concrete)
   public void setEntityManager( EntityManager em )
   {
      this.em = em;
   }

   @Override
public EntityManager getEntityManager()
   {
      return this.em;
   }

   public void setSessionManager( SessionManager sm )
   {
      this.sm = sm;
   }
   
   /*************************************************************************
   *
   * @see com.jdap.cdmr.patterns.dao.DaoFactoryInterface#flushTransaction()
   *
   *************************************************************************/
   
   @Override
public void flushTransaction()
   {
      if( this.em != null )
      {
         this.em.flush();
      }
   }
   
   
   /*************************************************************************
   *
   * @see com.jdap.cdmr.patterns.dao.DaoFactoryInterface#clearTransaction()
   *
   *************************************************************************/
   
   @Override
public void clearTransaction()
   {
      if( this.em != null )
      {
         LOG.finest( "About to evictAll cache and clear entity manager" );
        //this.em.getEntityManagerFactory().getCache().evictAll();
         this.em.clear();
      }
   }

   /*************************************************************************
   *
   * @see com.jdap.cdmr.patterns.dao.DaoFactoryInterface#clearSessionProperties()
   *
   *************************************************************************/
   @Override
public void clearSessionProperties()
   {
      if( this.sm != null )
      {
         this.sm.clearProperties();
      }
   }

   /*************************************************************************
   *
   * (non-Javadoc)
   * @see com.jdap.cdmr.patterns.dao.DaoFactoryInterface#executeDataBaseQuery(org.eclipse.persistence.queries.DatabaseQuery, java.util.Vector)
   *
   *************************************************************************/
   //@Override
   public Object executeDataBaseQuery( DatabaseQuery databaseQuery, Vector args )
   {
      return JpaHelper.getEntityManager( em ).getActiveSession().executeQuery( databaseQuery, args );
   }
   
   /*************************************************************************
   *
   * (non-Javadoc)
   * @see com.jdap.cdmr.patterns.dao.DaoFactoryInterface#addDescriptor(org.eclipse.persistence.descriptors.ClassDescriptor)
   *
   *************************************************************************/
   //@Override
   public void addDescriptor( ClassDescriptor descriptor )
   {
      ServerSession session = ( (JpaEntityManager) em.getDelegate() ).getServerSession();
      session.addDescriptor( descriptor );
   }
}
