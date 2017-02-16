
package com.jdap.auction.application.converter;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.jdap.auction.application.model.CreditCard;
import com.jdap.auction.application.model.LocaleChangerMBean;

@FacesConverter ( "com.jdap.auction.application.converter.Card" )
public class CreditCardConverter implements Converter, Serializable
{
	
	/*Parameter coming through the custom tag Library (corejsf.taglib.xml)*/
	private String separator;

	public static Logger logger = Logger.getLogger( CreditCardConverter.class.getName() );
	
	public void setSeparator(String newValue)
	{
		separator = newValue;
	}
	
	/**
	 * convert to a CreditCard Object from the string typed by the user
	 * 
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String newValue) throws ConverterException
	{
		StringBuilder builder = new StringBuilder(newValue);
		boolean foundInvalidCharacter = false;
		char invalidCharacter = '\0';
		int i = 0;
		while (i < builder.length() && !foundInvalidCharacter)
		{
			char ch = builder.charAt(i);
			if (Character.isDigit(ch))
				i++;
			else if (Character.isWhitespace(ch) || ch == '-')
				builder.deleteCharAt(i);
			else
			{
				foundInvalidCharacter = true;
				invalidCharacter = ch;
			}
		}
		
		if (foundInvalidCharacter)
		{
			FacesMessage message = com.jdap.auction.application.util.Messages
			                .getMessage("com.jdap.auction.application.messages", "badCreditCardCharacter", new Object[]
				                {new Character(invalidCharacter)});
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(message);
		}
		
		return new CreditCard(builder.toString());
	}
	
	/**
	 * After validate It will format or convert to string the object Credit Card that it is just a CC number
	 * 
	 */
	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException
	{
		// length 13: xxxx xxx xxx xxx
		// length 14: xxxxx xxxx xxxxx
		// length 15: xxxx xxxxxx xxxxx
		// length 16: xxxx xxxx xxxx xxxx
		// length 22: xxxxxx xxxxxxxx xxxxxxxx
		if (!(value instanceof CreditCard))
			throw new ConverterException();
		String v = ((CreditCard) value).toString();
		String sep = separator;
		if (sep == null)
			sep = " ";
		int[] boundaries = null;
		int length = v.length();
		if (length == 13)
			boundaries = new int[]
				{4, 7, 10};
		else if (length == 14)
			boundaries = new int[]
				{5, 9};
		else if (length == 15)
			boundaries = new int[]
				{4, 10};
		else if (length == 16)
			boundaries = new int[]
				{4, 8, 12};
		else if (length == 22)
			boundaries = new int[]
				{6, 14};
		else
			return v;
		StringBuilder result = new StringBuilder();
		int start = 0;
		for (int i = 0; i < boundaries.length; i++)
		{
			int end = boundaries[i];
			result.append(v.substring(start, end));
			result.append(sep);
			start = end;
		}
		result.append(v.substring(start));
		return result.toString();
	}
}
