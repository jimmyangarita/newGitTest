package com.jdap.auction.patterns.persistence;

import com.jdap.auction.patterns.OID;

public interface PersistentObjectInterface {
	
    public OID getId();
    public void setId( OID id );
	public Long getVersion();
	 public void setVersion( Long version );
	

}
