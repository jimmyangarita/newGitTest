
package com.jdap.auction.model.type;

public class test
{

	
	public void getNames()
	{
		System.out.println(this.getClass().getName());
		System.out.println(this.getClass().getSimpleName());
		System.out.println(this.getClass().getEnclosingClass());
	}
	
	public static  void main (String sd [] ) {
		test t = new test();
		t.getNames();
	}
}
