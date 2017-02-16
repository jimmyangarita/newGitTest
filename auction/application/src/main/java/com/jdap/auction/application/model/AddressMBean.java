
package com.jdap.auction.application.model;

import java.util.logging.Logger;
import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name="address")
@SessionScoped
public class AddressMBean implements Serializable
{
	private String line1;
	
	private String line2;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String zipcode;
	
    /** Field description */
    public static Logger logger = Logger.getLogger( AddressMBean.class.getName() );
    
    private static final Locale[] countries = { Locale.US, Locale.CANADA };
    public Locale[] getCountries() { return countries; };
	
	public AddressMBean()
	{
		logger.info( "Creating.... New Address MBean()" );
		/*this.line1="";
		this.city="";
		this.state="";
		this.country="";
		this.zipcode="";*/
	}
	
	
	public AddressMBean(String line1, String line2, String city, String state, String country, String zipcode)
	{
		logger.info( "Creating2.... New Address MBean(parameters.....)" );
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
	}
	
	public String getLine1()
	{
		return line1;
	}
	
	public void setLine1(String line1)
	{
		this.line1 = line1;
	}
	
	public String getLine2()
	{
		return line2;
	}
	
	public void setLine2(String line2)
	{
		this.line2 = line2;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getState()
	{
		return state;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	public String getZipcode()
	{
		return zipcode;
	}
	
	public void setZipcode(String zipcode)
	{
		this.zipcode = zipcode;
	}
	
	public void countryChanged(ValueChangeEvent event)
	{
		logger.info("... Into AdressBean.countryChanged...");
		FacesContext context = FacesContext.getCurrentInstance();
		for(Locale loc: countries)
		{
			if(loc.getCountry().equals(event.getNewValue())) 
			{
				logger.info("... Into AdressBean.countryChanged.Locale: " + loc.getCountry());
				context.getViewRoot().setLocale(loc);
				//context.renderResponse();
			}	
		}	
		
		//context.renderResponse();		
	}
	
}
