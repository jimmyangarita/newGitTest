/**
 * ApplicationEventFactory.java   2012.February.25 04:44 PM
 *
 * Copyright (c) 2010 jdap Corporation
 */

package com.jdap.auction.common.spring.edn;

//~--- JDK imports -------------------------------------------------------------

import java.lang.annotation.Annotation;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchema;

/**
 * ApplicationEventFactory
 * The goal of the ApplicationEventFactory is to avoid having to create
 * separate extensions of DeliverableApplicationEvent and hard-coding the namespace and the
 * element name for each event type. Using annotation reflection we can capture the
 * name and the namespace from the JAXB annotations in the event object that are
 * derived from the EDL and schema definitions.
 *
 * @author jdap
 */
public class ApplicationEventFactory
{
    /** Field description */
    private static final String DEFAULT_NAMESPACE = "##default";

    /**
     * Create an event
     *
     * Note:
     * All events must have an @XmlRootElement annotation specifying the name.
     *
     * @param source Object the source of the DeliverableApplicationEvent
     * @param event  Object the content of the DeliverableApplicationEvent
     * @return ApplicationEvent
     */
    public
    DeliverableApplicationEvent createEvent( Object source,
                                             Object event )
    {
        DeliverableApplicationEvent myEvent = null;
        Annotation xmlSchemaAnnotation =
            event.getClass().getPackage().getAnnotation( XmlSchema.class );
        Annotation xmlRootElementAnnotation =
            event.getClass().getAnnotation( XmlRootElement.class );

        if( null != xmlRootElementAnnotation )
        {
            XmlRootElement element = (XmlRootElement) xmlRootElementAnnotation;
            String namespace = element.namespace();

            if( namespace.equals( DEFAULT_NAMESPACE ) )
            {
                XmlSchema schema = (XmlSchema) xmlSchemaAnnotation;

                namespace = schema.namespace();
            }

            String name = element.name();

            myEvent = new GeneratedApplicationEvent( source, namespace, name,
                                                     event );
        }

        return myEvent;
    }

    /**
     * GeneratedApplicationEvent
     */
    private static class GeneratedApplicationEvent
            extends DeliverableApplicationEvent
    {
        /**
         * DeliverableApplicationEvent
         *
         * @param source    the producer of the event
         * @param namespace the event namespace
         * @param name      the name of the event
         * @param content   the actual event
         */
        public
        GeneratedApplicationEvent( Object source,
                                   String namespace,
                                   String name,
                                   Object content )
        {
            super( source, namespace, name, content );
        }
    }
}
