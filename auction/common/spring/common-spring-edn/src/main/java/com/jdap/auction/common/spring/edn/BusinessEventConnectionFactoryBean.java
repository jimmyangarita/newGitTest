/**
 * BusinessEventConnectionFactoryBean.java   2012.February.25 04:44 PM
 *
 * Copyright (c) 2010 jdap Corporation
 */

package com.jdap.auction.common.spring.edn;

//~--- non-JDK imports ---------------------------------------------------------

import oracle.fabric.blocks.event.BusinessEventConnection;
import oracle.fabric.blocks.event.BusinessEventConnectionFactory;

import oracle.integration.platform.blocks.event.saq
    .SAQRemoteBusinessEventConnectionFactory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

//~--- JDK imports -------------------------------------------------------------

import javax.sql.DataSource;

/**
 * BusinessEventConnectionFactoryBean
 *
 * To create such an instance of a BusinessEventConnection, the EDN Java API provides a factory interface named BusinessEventConnectionFactory together
 * with an implementation through SAQRemoteBusinessEventConnectionFactory.
 * In order to simplify the creation of a BusinessEventConnection, the Spring Factory Bean mechanism is used.
 *
 * The FactoryBean interface is a point of pluggability into the Spring IoC container's instantiation logic. If you have complex initialization code that
 * is better expressed in Java as opposed to a (potentially) verbose amount of XML, you can create your own FactoryBean, write the complex initialization
 * inside that class, and then plug your custom FactoryBean into the container.
 *
 * @author jdap@jdap.com
 */
public class BusinessEventConnectionFactoryBean
        implements FactoryBean, InitializingBean
{
    BusinessEventConnectionFactory businessEventConnectionFactory;
    DataSource dataSource;

    /**
     * Set DataSource
     * @param dataSource DataSource
     */
    public void setDataSource( DataSource dataSource )
    {
        this.dataSource = dataSource;
    }

    /**
     * Initialize bean
     * @throws Exception on failure
     */
    public void afterPropertiesSet() throws Exception
    {
        businessEventConnectionFactory =
            new SAQRemoteBusinessEventConnectionFactory( dataSource,
                                                         dataSource, null );
    }

    /**
     * Get Object
     * @return Object
     * @throws Exception on failure
     */
    public Object getObject() throws Exception
    {
        return businessEventConnectionFactory.createBusinessEventConnection();
    }

    /**
     * Get Object Type
     * @return Class
     */
    public Class getObjectType()
    {
        return BusinessEventConnection.class;
    }

    /**
     * Is Singleton
     * @return boolean true if singleton, false otherwise
     */
    public boolean isSingleton()
    {
        return false;
    }
}
