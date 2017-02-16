select * from orders;
select * from Item;
select * from category;

select * from Item t where t.title like '%ook%';


select * from category c
where c.oid = 'b47d87fc-c4c9-4cea-abbe-d70f98036893';
select * from item;
select * from item_category it
where it.itemid = 'b3d51779-0429-48a3-9095-6641b132d17b';

select * from users;

delete from item_category;
delete from item;
delete from category;


select OID from category where parentcategoryid is null;

alter table item 
MODIFY description varchar(2000);

update category c
set c.name = 'Inteligence and Espionage'
where c.oid = '8b50cea8-bf4c-4ad8-8027-43824be74280';


				
update Item i
set i.title = 'Calculus, 4th edition Spivak'
where i.oid = '52e716cf-f5e1-48fe-aca0-5e55bf8f21ea';
 


select  -- lpad(' ',8*(level-1)) || to_char(c.OID) s,
  i.*
 from category c , item_category ic , item i
  where 
   c.oid = ic.categoryid and
    i.oid = ic.itemid
 start with c.parentcategoryid  is null -- = 
 connect by prior c.oid = c.parentcategoryid; 
 
 
 
 
SELECT i FROM  item i JOIN category c
START WITH c.parentcategoryid = :parentcategoryid
CONNECT BY c.parentcategoryid;
 
 
select-- lpad(' ',8*(level-1)) || to_char(c.OID) s,
  c. *,i.*
 from category c , item_category ic , item i
  where  c.oid = ic.categoryid
 and i.oid = ic.itemid
 and c.parentcategoryid = '0439ed4e-9318-4959-8299-6d81fb2138b9';-- is null;
 

SELECT i FROM  Item i JOIN Category c
WHERE c.parentcategoryid :category; 

 
select lpad(' ',8*(level-1)) || to_char(OID) s, name, description, oid, parentcategoryid
 from category
 start with parentcategoryid  = 'd37cfbff-e1ac-41ec-a8b9-c2e12a65d526' --is null 
 connect by prior oid = parentcategoryid;
 
select * from category ct
where ct.parentcategoryid is null or
ct.parentcategoryid = 'ef59bf20-1d69-4607-8340-df359c63e092';


SELECT * FROM Category c where c.parentcategoryid is null;


select * from category ct
where ct.parentcategoryid =  '71c988da-7da2-4139-ae26-cab8ba0f0bb8';

	