package com.jdap.auction.model.type;


public enum PaymentMethod {
	CC("CREDIT_CARD"), CS("CASH"), CA("CHECKING_ACCOUNT");									 

	/*private static final Map<String, PaymentMethod> lookup = new HashMap<String, PaymentMethod>();

	static {
		for (PaymentMethod type : EnumSet.allOf(PaymentMethod.class)) { // PaymentMethod.values()
			System.out.println("static:: " + type.value + " , " + type);
			lookup.put(type.value, type);
		}
	}*/

	private final String value;

	private PaymentMethod(String ordinal) {
		this.value = ordinal;
		//System.out.println(" contructor .. " + ordinal);

	}

	public String getValue() {
		return value;
	}

	/*public static PaymentMethod get(String ordinal) {
		return lookup.get(ordinal);
	}*/
	
    public static PaymentMethod fromValue(String v) {
        for (PaymentMethod c: PaymentMethod.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
	
	public static void main(String arg[]) {
		
		System.out.println("valueof CA : " + PaymentMethod.valueOf("CA"));
		System.out.println("PaymentMethod.CA.value = " + PaymentMethod.CA.value); //or getValue()
		System.out.println(" fromValue CHECKING_ACCOUNT : " + PaymentMethod.fromValue("CHECKING_ACCOUNT"));

		PaymentMethod normalTest = PaymentMethod.CS; 
		if (normalTest.equals(CS))
			System.out.println("==========");
		System.out.println("normalTest:: = " + normalTest);
		System.out.println("normalTest.getValue():: = " + normalTest.getValue());
		System.out.println("normalTest.fromValue(CASH):: = " + PaymentMethod.fromValue("CASH")); 
	
	}

}
