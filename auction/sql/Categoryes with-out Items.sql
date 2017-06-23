select * 
  from category ca
 where (select count(*) 
         from item_category ic , item it
  	 	where ic.itemid = it.oid  
		  and ic.categoryid = ca.oid) = 0
  order by ca.name;
		  
select ca.*
	from category ca, item_category ic
	where ca.oid  = ic.categoryid (+)
minus 
select ca.*
	from category ca, item_category ic
	where ca.oid  = ic.categoryid; 
				  
		  
select ca.* 
  from category ca, item_category ic, item it
  where ic.itemid = it.oid
  	and ic.categoryid = ca.oid; 		  
	
select b.* from bid b
where b.itemid = 'b9e3330a-4339-44cc-aba8-761995890423';	

@NamedQuery(name = "Bids.getHighBids",
		query = "Select bid from Bid bid where bid.bidAmount > :amount")
