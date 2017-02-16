package com.jdap.auction.application.model;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="localChanger")
@RequestScoped
public class LocaleChangerMBean
{
	
	public static Logger logger = Logger.getLogger( LocaleChangerMBean.class.getName() );
    
	
	public  String changeLocale()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		String languageCode = getLanguageCode(context);
		logger.info("##########Into changeLocale, languageCode: " +languageCode );
		context.getViewRoot().setLocale(new Locale(languageCode));
		return null;
	}
	
	private String getLanguageCode(FacesContext context)
	{
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		return params.get("languageCode");
	}
}
