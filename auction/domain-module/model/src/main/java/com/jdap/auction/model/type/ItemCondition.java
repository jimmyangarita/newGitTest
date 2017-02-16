package com.jdap.auction.model.type;



public enum ItemCondition 
{
    NEW,
    USED_LIKE_NEW,
    USED_VERY_GOOD,
    USED_GOOD,
    USED_ACCEPTABLE;


public String value() {
    return name();
}

public static ItemCondition fromValue(String v) {
    return valueOf(v);
}
public static void main(String arg []) {
	
	System.out.println("NEW = " + ItemCondition.NEW);
	System.out.println("NEW.Values = " + ItemCondition.NEW.value());
	System.out.println(" Value of NEW = " + ItemCondition.valueOf("NEW")); //must pass string

	ItemCondition normalTest = ItemCondition.USED_ACCEPTABLE; //it Initialize the ItemCondition instance with value Seller.
	System.out.println("normalTest = " + normalTest.value());
	System.out.println( "value from instance :: " + normalTest);
}
}