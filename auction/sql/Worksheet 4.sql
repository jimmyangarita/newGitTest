select * from item order by 2 desc;
select * from category;
select * from item_category ic ;
where ic.categoryid = any ('851e69f9-b43c-4ebd-b96d-ed7e5d69b8bf', '61b646b8-8258-4e09-b9bc-14ab83c1a44b')
  and ic.itemid = any ('8123fde8-4b42-43eb-a35d-0bbdb55ee655');
