package com.jdap.auction.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public enum UserType
{
    S("SELLER"), 
    B("BIDDER"),
    C("CSM"),
    A("ADMIN");

    private static final Map<String, UserType> lookup =
        new HashMap<String, UserType>();

    static    {
        for( UserType type : EnumSet.allOf( UserType.class ) )  { //UserType.values()
        	System.out.println("static:: " +type.ordinal + " , "+ type);
            lookup.put( type.ordinal, type );
        }
    }

    private String ordinal;

    private UserType( String ordinal )   {
        this.ordinal = ordinal;
        System.out.println(" contructor .. "+ ordinal);
 
    }

    public String getCode()   {
        return ordinal;
    }

    public static UserType get( String ordinal )    {
        return lookup.get( ordinal );
    }
    
    public static void main(String arg []) {
    	
    	System.out.println("Seller = " + UserType.S);
    	System.out.println(" Value of Seller = " + Enum.valueOf(UserType.class, "S")); //must pass string
    	System.out.println("Bidder = " + UserType.B);
    	System.out.println("value of Bidder= " + UserType.valueOf( "B" ));
    	System.out.println("ordinal 0 = " + UserType.get("SELLER"));
    	System.out.println("ordinal 1 = " + UserType.get("B"));
    	
    	    	
    	for (Map.Entry<String, UserType> entry : lookup.entrySet()) {
    		System.out.println(entry.getKey() + " - " + entry.getValue());
    	}
    	
    	UserType normalTest = UserType.S; //it Initialize the UserType instance with value Seller.
    	System.out.println("normalTest = " + normalTest.getCode());
    	System.out.println( "value from instance :: " + UserType.get(normalTest.getCode()));
    	System.out.println( "value from instance :: " + normalTest);
    	System.out.println( "value from instance :: " + new Double("5"));
    	
    	
    }
}

