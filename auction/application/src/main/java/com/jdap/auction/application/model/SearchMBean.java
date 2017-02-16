
package com.jdap.auction.application.model;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.jdap.auction.application.base.BaseItemMBean;

@ManagedBean ( name = "search" )
@SessionScoped
public class SearchMBean extends BaseItemMBean implements Serializable
{
	private String input;

		
	public SearchMBean()
    {
		logger.info("------------SearchMBean Initialize......");
    }

	public String getInput()
    {
		logger.info("------------getInput()...... input= " + input);
		return input;
    }

	public void setInput(String input)
    {
		logger.info("------------setInput()...... input= " + input);
    	this.input = input;
    }
	
	
}
