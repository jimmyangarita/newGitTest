package com.jdap.auction.patterns;

import java.util.UUID;

import javax.persistence.Embeddable;
import javax.persistence.Column;

import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

@Embeddable
public class GUID implements Serializable, OID, Cloneable {

	/**
	 * Explicit serialVersionUID for interoperability
	 */
	private static final long serialVersionUID = 1816461939461100077L;

	@Column(columnDefinition = "VARCHAR(36)", name = "OID", nullable = false)
	private String oid;

	/**
	 * Default constructor to support JAXB
	 */		
	public GUID() {
		super();
	}

	public GUID(String strOid) {
		oid = UUID.fromString(strOid).toString();
	}

	/**
	 * Constructs a new <tt>OID</tt> using the specified data.
	 * <tt>mostSigBits</tt> is used for the most significant 64 bits of the
	 * <tt>OID</tt> and <tt>leastSigBits</tt> becomes the least significant 64
	 * bits of the <tt>OID</tt>.
	 * 
	 * @param mostSigBits
	 * @param leastSigBits
	 */

	public GUID(long mostSigBits, long leastSigBits) {
		oid = new UUID(mostSigBits, leastSigBits).toString();
	}

	/**
	 * Static factory to retrieve a type 4 (pseudo randomly generated) UUID.
	 * 
	 * The <code>OID</code> is generated using a cryptographically strong pseudo
	 * random number generator.
	 * 
	 * @return a randomly generated <tt>OID</tt>.
	 */

	public static GUID randomOID() {
		UUID tmpUUID = UUID.randomUUID();
		return (new GUID(tmpUUID.getMostSignificantBits(),
				tmpUUID.getLeastSignificantBits()));
	}

	/**
	 * Static factory to retrieve a type 3 (name based) <tt>OID</tt> based on
	 * the specified byte array.
	 * 
	 * @param name
	 *            a byte array to be used to construct a <tt>UUID</tt>.
	 * @return a <tt>UUID</tt> generated from the specified array.
	 */

	public static GUID nameOIDFromBytes(byte[] name) {
		UUID tmpUUID = UUID.nameUUIDFromBytes(name);
		return (new GUID(tmpUUID.getMostSignificantBits(),
				tmpUUID.getLeastSignificantBits()));
	}

	/**
	 * Creates a <tt>OID</tt> from the string standard representation as
	 * described in the {@link #toString} method.
	 * 
	 * a string that specifies a <tt>UUID</tt>.
	 * 
	 * @return a <tt>OID</tt> with the specified value.
	 */

	public static GUID fromString(String strOid) {
		if (strOid == null || strOid.trim().isEmpty()) {
			throw new RuntimeException("Id is null or empty");
		} else {
			try {
				UUID tmpUUID = UUID.fromString(strOid);
				return (new GUID(tmpUUID.getMostSignificantBits(),
						tmpUUID.getLeastSignificantBits()));
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(
						"Error trying to create UUID", e);
			}
		}
	}

	// Field Accessor Methods

	/**
	 * Returns the least significant 64 bits of this OID's 128 bit value.
	 * 
	 * @return the least significant 64 bits of this OID's 128 bit value.
	 */

	public long getLeastSignificantBits() {
		if (oid != null) {
			return UUID.fromString(oid).getLeastSignificantBits();
		} else {
			return 0;
		}
	}

	/**
	 * Returns the most significant 64 bits of this UUID's 128 bit value.
	 * 
	 * @return the most significant 64 bits of this UUID's 128 bit value.
	 */

	public long getMostSignificantBits() {
		if (oid != null) {
			return UUID.fromString(oid).getMostSignificantBits();
		} else {
			return 0;
		}
	}

	// Object Inherited Methods

	/**
	 * Returns a <code>String</code> object representing this <code>OID</code>.
	 * 
	 * @return a string representation of this <tt>UUID</tt>.
	 */

	@Override
    public String toString() {
		if (oid != null) {
			return (oid.toString());
		} else {
			return null;
		}
	}

	/**
	 * Returns a hash code for this <code>OID</code>.
	 * 
	 * @return a hash code value for this <tt>OID</tt>.
	 */
	@Override
    public int hashCode() {
		if (oid != null) {
			return (oid.hashCode());
		} else {
			return HashCodeBuilder.reflectionHashCode(this);
		}
	}

	/**
	 * Compares this object to the specified object. The result is <tt>true</tt>
	 * if and only if the argument is not <tt>null</tt>, is a <tt>OID</tt>
	 * object, has the same variant, and contains the same value, bit for bit,
	 * as this <tt>OID</tt>.
	 * 
	 * @param obj
	 *            the object to compare with.
	 * @return <code>true</code> if the objects are the same; <code>false</code>
	 *         otherwise.
	 */
	@Override
    public boolean equals(Object obj) {
		if (!(obj instanceof GUID))
			return false;
		// if ( ( (UUID) obj ).variant() != this.variant() )
		// return false;
		GUID id = (GUID) obj;
		return (this.getMostSignificantBits() == id.getMostSignificantBits() && this
				.getLeastSignificantBits() == id.getLeastSignificantBits());
	}

	// Comparison Operations

	/**
	 * Compares this OID with the specified OID.
	 * 
	 * <p>
	 * The first of two UUIDs follows the second if the most significant field
	 * in which the UUIDs differ is greater for the first UUID.
	 * 
	 * @param oidval
	 *            <tt>UUID</tt> to which this <tt>UUID</tt> is to be compared.
	 * @return -1, 0 or 1 as this <tt>UUID</tt> is less than, equal to, or
	 *         greater than <tt>val</tt>.
	 */

	@Override
    public int compareTo(OID oidval) {
		GUID val = (GUID) oidval;
		// The ordering is intentionally set up so that the UUIDs
		// can simply be numerically compared as two numbers
		return (this.getMostSignificantBits() < val.getMostSignificantBits() ? -1
				: (this.getMostSignificantBits() > val.getMostSignificantBits() ? 1
						: (this.getLeastSignificantBits() < val
								.getLeastSignificantBits() ? -1 : (this
								.getLeastSignificantBits() > val
								.getLeastSignificantBits() ? 1 : 0))));
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
