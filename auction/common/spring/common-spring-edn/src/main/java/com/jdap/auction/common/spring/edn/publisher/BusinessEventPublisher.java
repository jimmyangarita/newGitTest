package com.jdap.auction.common.spring.edn.publisher;

import java.io.IOException;

import javax.xml.namespace.QName;

import com.jdap.auction.common.spring.edn.XmlMappingException;
import com.jdap.auction.common.spring.edn.DeliverableApplicationEvent;

/**
 * Interface description
 *
 *
 * @version        9.0, 2012.December.03 03:16 PM
 * @author         jdap Corporation    
 */
public interface BusinessEventPublisher
{
    void publishEvent( DeliverableApplicationEvent applicationEvent ) throws XmlMappingException, IOException;

    void publishEvent( QName eventName,
                       DeliverableApplicationEvent applicationEvent ) throws XmlMappingException,
                                                                             IOException;
}
