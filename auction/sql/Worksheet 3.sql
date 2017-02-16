select distinct se.*, us.* from users us, seller se
where us.oid = se.oid
order by us.lastmodified desc; 

select distinct bi.*, us.* from users us,  bidder bi
where us.oid = bi.oid
order by us.lastmodified desc; 
 
select * from users; 
select * from seller;
select * from bidder;
select * from users_category;
select * from billinginfo;

select * from Item order by 2 desc;
select * from item_category where itemid = 'a36eedb1-664e-46c6-8333-70215fdc910c';
select * from category;

Category Test	03/21/2014 09:55:02	Category 1	5	61b646b8-8258-4e09-b9bc-14ab83c1a44b	{null}