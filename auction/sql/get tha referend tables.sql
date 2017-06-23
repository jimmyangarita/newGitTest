select   a.table_name
        , a.constraint_name
        , a.constraint_type
        , b.table_name
        , b.column_name
        , b.position
from     user_constraints a
        , user_cons_columns b
where    a.owner = b.owner
and      a.constraint_name = b.constraint_name
and      a.constraint_type = 'P'
and     a.table_name = 'USERS'
union all
select   a.table_name
        , a.constraint_name
        , a.constraint_type
        , b.table_name
        , b.column_name
        , b.position
from     user_constraints a
        , user_cons_columns b
where    a.owner = b.owner
and      a.r_constraint_name = b.constraint_name
and      a.constraint_type = 'R'
and      a.table_name = 'USERS'
order by 1, 2, 3, 4, 5
/



select table_name, constraint_name, status, owner
from all_constraints
where r_owner = 'CDMR'
and constraint_type = 'R'
and r_constraint_name in
 (
   select constraint_name from all_constraints
   where constraint_type in ('P', 'U')
   and table_name = 'USERS'
   and owner = 'CDMR'
 )
order by table_name, constraint_name;
