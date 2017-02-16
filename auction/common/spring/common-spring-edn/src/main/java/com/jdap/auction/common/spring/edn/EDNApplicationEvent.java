/**
 * EDNApplicationEvent.java   2012.February.25 04:44 PM
 *
 * Copyright (c) 2010 jdap Corporation
 */

package com.jdap.auction.common.spring.edn;

//~--- non-JDK imports ---------------------------------------------------------

import org.springframework.context.ApplicationEvent;

//~--- JDK imports -------------------------------------------------------------

import java.util.UUID;

/**
 * EDNApplicationEvent
 * @author jdap@jdap.com
 *
 */
public abstract class EDNApplicationEvent extends ApplicationEvent
{
    /** Field description */
    private Object content;

    /** Field description */
    private UUID conversationId;

    /** Field description */
    private UUID eventId;

    /** Field description */
    private String name;

    /** Field description */
    private String namespace;

    /**
     * EDNApplicationEvent
     * @param source  the producer of the event
     * @param namespace  the EDL namespace
     * @param name  the name of the event
     * @param content  the actual event
     *
     */
    public
    EDNApplicationEvent( Object source,
                         String namespace,
                         String name,
                         Object content )
    {
        super( source );
        this.namespace = namespace;
        this.name = name;
        this.content = content;
        this.eventId = UUID.randomUUID();
        this.conversationId = UUID.randomUUID();
    }

    /**
     * Get namespace
     * @return String namespace
     */
    public String getNamespace()
    {
        return namespace;
    }

    /**
     * Get name
     * @return String name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get content
     * @return Object content
     */
    public Object getContent()
    {
        return content;
    }

    /**
     * Get event id
     * @return UUID eventId
     */
    public UUID getEventId()
    {
        return eventId;
    }

    /**
     * Get conversation id
     * @return UUID conversationId
     */
    public UUID getConversationId()
    {
        return conversationId;
    }
}
