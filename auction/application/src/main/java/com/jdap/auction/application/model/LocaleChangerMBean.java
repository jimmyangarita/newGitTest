
package com.jdap.auction.application.model;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.jdap.auction.application.base.BaseItemMBean;

@ManagedBean ( name = "localeChanger" )
@SessionScoped
public class LocaleChangerMBean extends BaseItemMBean implements Serializable
{
	
	public LocaleChangerMBean()
	{
		logger.info("*** creating bean  LocaleChangerMBean()*******");
	}
	
	public String changeLocale()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		String languageCode = getLanguageCode(context);
		logger.info("****Into changeLocale, languageCode: " + languageCode);
		context.getViewRoot().setLocale(new Locale(languageCode));
		return null;
	}
	
	private String getLanguageCode(FacesContext context)
	{
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		return params.get("languageCode");
	}
}
