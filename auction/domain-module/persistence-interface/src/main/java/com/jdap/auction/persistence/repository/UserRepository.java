package com.jdap.auction.persistence.repository;

//~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.model.type.UserType;
import com.jdap.auction.model.User;
import com.jdap.auction.patterns.OID;

//~--- JDK imports -------------------------------------------------------------

import java.util.List;

import javax.ejb.Local;

/**
 * Interface description
 *
 *
 * @version        9.0, 2014.September.09 04:11 PM
 * @author         JDAP Corporation    
 */
@Local
public interface UserRepository
{
    /**
     * Method description
     *
     *
     * @param user
     *
     * @return
     */
    public OID insertUser( User user );

    /**
     * Method description
     *
     *
     * @param user
     * @param clazz
     * @param <T>
     *
     * @return
     */
    public <T extends User> User updateUser( T user, Class<T> clazz );

    /**
     * Method description
     *
     *
     * @param userId
     */
    public void deleteUser( String userId );

    /**
     * Method description
     *
     *
     * @param user
     *
     * @return
     */
    public User findUserById( String user );

    /**
     * Method description
     *
     *
     * @param userType
     * @param <T>
     *
     * @return
     */
    public <T extends User> List<T> getUsers( UserType userType );

    /**
     * Method description
     *
     *
     * @param userName
     * @param Passw0rd
     * @param <T>
     *
     * @return
     */
    public <T extends User> T getUserByUsernamePassword( String userName, String Passw0rd );

    /**
     * Method description
     *
     *
     * @param userName
     * @param <T>
     *
     * @return
     */
    public <T extends User> T getUserByUsername( String userName );
}
