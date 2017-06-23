package com.jdap.auction.model;

//~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.patterns.persistence.AbstractPersistentObject;

//~--- JDK imports -------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Class description
 *
 *
 * @version        9.0, 2013.February.27 11:39 AM
 * @author         JDAP Corporation
 */
@Entity
@NamedQueries( { @NamedQuery( name = "category.getRootCategories",
                              query = "SELECT c FROM Category c where c.parentCategory is null" ) ,
                 @NamedQuery( name = "category.getCategoriesByParent",
                              query = "SELECT c FROM Category c where c.parentCategory = :parentcategory" ) } )
public class Category extends AbstractPersistentObject
{
    /** Field description */
    private String name;

    /** Field description */
    private String description;

    /** Field description */
    @ManyToMany( mappedBy = "categories", cascade = CascadeType.REMOVE )
    private List<Item> items;

    /*
     * @JoinTable(
     *   name = "category_items",
     *   joinColumns = @JoinColumn( name = "categoryid",
     *                              referencedColumnName = "oid" ) ,
     *   inverseJoinColumns = @JoinColumn( name = "itemid",
     *                                     referencedColumnName = "oid" )
     * )
     */

    /** Field description */
    @ManyToMany( mappedBy = "categories" )
    private List<User> users;

    /** Field description */
    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "parentCategoryid", referencedColumnName = "oid" )
    private Category parentCategory;

    /** Field description */
    @OneToMany( mappedBy = "parentCategory", cascade = CascadeType.ALL )
    private List<Category> subCategories;

    /**
     * Constructs ...
     *
     */
    public Category()
    {
    	//String id = parentCategory.getId();
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
    public List<Item> getItems()
    {
        if( items == null )
        {
            items = new ArrayList<Item>();
        }

        return items;
    }

    /**
     * Method description
     *
     *
     * @param items
     *
     * @return
     */

    /*
     * public void setItems( List<Item> items )
     * {
     *   this.items = items;
     * }
     */

    /**
     * Method description
     *
     *
     * @return
     */
    public Category getParentCategory()
    {
        return parentCategory;
    }

    /**
     * Method description
     *
     *
     * @param parentCategory
     */
    public void setParentCategory( Category parentCategory )
    {
        this.parentCategory = parentCategory;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public List<Category> getSubCategories()
    {
        if( subCategories == null )
        {
            subCategories = new ArrayList<Category>();
        }

        return this.subCategories;
    }

    /**
     * Method description
     *
     *
     * @param subCategories
     *
     * @return
     */

    /*
     * public void setSubCategories( List<Category> subCategories )
     * {
     * this.subCategories = subCategories;
     * }
     */
    public List<User> getUsers()
    {
        return users;
    }

    /**
     * Method description
     *
     *
     * @param users
     */
    public void setUsers( List<User> users )
    {
        this.users = users;
    }
}
