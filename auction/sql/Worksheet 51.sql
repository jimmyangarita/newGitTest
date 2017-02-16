select * from category;
select * from Item;

delete from item where title = '?';
delete from item_category ic where ic.itemid = any (select oid from item where title = '?');


