
package com.jdap.auction.application.validator;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator ( "com.jdap.auction.application.validator.EmailValidator" ) //this is the Id can be different name like email
public class EmailValidator implements Validator
{
	
	public static Logger logger = Logger.getLogger(EmailValidator.class.getName());
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
	                + "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" + "(\\.[A-Za-z]{2,})$";
	
	private Pattern pattern;
	
	private Matcher matcher;
	
	public EmailValidator()
	{
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException
	{
		
		matcher = pattern.matcher(value.toString());
		if (!matcher.matches())
		{
			FacesMessage msg = new FacesMessage("E-mail validation failed!", "Invalid E-mail format!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
			
		}
		
	}
}
