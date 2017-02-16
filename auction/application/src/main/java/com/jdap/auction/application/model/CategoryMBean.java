package com.jdap.auction.application.model;

//~--- JDK imports -------------------------------------------------------------

import java.util.List;

/**
 * Class description
 *
 *
 * @version        9.0, 2014.October.29 10:58 AM
 * @author         JDAP Corporation    
 */
public class CategoryMBean
{
    /** Field description */
    private String id;

    /** Field description */
    private boolean category;

    /** Field description */
    private String name;

    /** Field description */
    private String description;

    /** Field description */
    private List<CategoryMBean> subcategory;

        
    public CategoryMBean(String id, boolean category, String name, String description, List<CategoryMBean> subcategory)
    {
	    this.id = id;
	    this.category = category;
	    this.name = name;
	    this.description = description;
	    this.subcategory = subcategory;
    }

	public CategoryMBean()
    {

    }
	

	/**
     * Method description
     *
     *
     * @return
     */
    public String getId()
    {
        return id;
    }

    /**
     * Method description
     *
     *
     * @param id
     */
    public void setId( String id )
    {
        this.id = id;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public boolean isCategory()
    {
        return category;
    }

    /**
     * Method description
     *
     *
     * @param category
     */
    public void setCategory( boolean category )
    {
        this.category = category;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * Method description
     *
     *
     * @param name
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Method description
     *
     *
     * @param description
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public List<CategoryMBean> getSubcategory()
    {
        return subcategory;
    }

    /**
     * Method description
     *
     *
     * @param subcategory
     */
    public void setSubcategory( List<CategoryMBean> subcategory )
    {
        this.subcategory = subcategory;
    }
}
