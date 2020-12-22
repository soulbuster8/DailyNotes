**SQL Query**

First three character of first name :-

Select substring(FIRST_NAME, 1, 3) from Worker;

Select * from Worker where DEPARTMENT = 'Admin';

Select * from Worker order by FIRST_NAME asc;

Select * from Worker order by FIRST_NAME asc, DEPARTMENT desc;

Select * from Worker where FIRST_NAME in ('Vipul', 'Satish');

Select * from Worker where FIRST_NAME like '%ab%';

Select * from Worker where FIRST_NAME like '_____h';

Select * from Worker where SALARY between 1000 and 3000;

Select count(*) from Worker where DEPARTMENT = 'Admin';

Select DEPARTMENT, count(WORKER_ID) as worker_no from worker
group by DEPARTMENT
order by worker_no;

Select TOP 10 * from worker order by salary desc;

Select * from worker order by salary desc limit 10;

Select TOP 1 from 
(
Select distinct top n salary
from worker
order by salary desc
)
order by asc;

select salary from worker order by salary desc limit n-1, 1;

select max(salary) from worker 
where salary not in (
select max(salary) from worker
);

Select salary from worker W1
where n-1 = (
select count(distinct (W2.salary))
from worker W2
where W2.salary > W1.salary
);

Select distinct W.first_name
from worker W
inner join Title T
on W.id = T.ref_id
and T.title in ('Manager');

**Note :- ** <br/>
When we will run the subquery, it will create a new table for us.

**Subquery :- **
SELECT first_name, last_name FROM employees 
WHERE manager_id in (select employee_id 
FROM employees WHERE department_id 
IN (SELECT department_id FROM departments WHERE location_id 
IN (select location_id from locations where country_id='US')));

select * from employess where salary > (select avg(salary) from employees);

**Subquery with All, Any :-** 
<br/>

select * from employees where salary > all(select avg(salary) from employees group by department_id);

<br/>
Subquery has to produce a single value table if we are using comparison operators such as =, <, <=,
>, >=, !=. <br/>
select * from facebook where # of friends = (select max(# of connections) from linkedin); <br/>
select * from facebook where # of friends in (Select # of connections from linkedin); <br/>
<br/>
User variables are written as @var_name where var_name consists of alphanumeric characters.<br/>
Note:- The sub-query in the from clause is evaluated first and then the results of evaluation are
stored in a new temporary relation. <br/>
set @i=0;
select i, employee_id
from (select @i := @i+1 as i, employee_id from employees) a
where mod(a.i, 2) = 0;



**Correlated subqueries:** <br/>
1. Correlated subqueries are used for row-by-row processing. Each subqueries is executed once for every
row of the outer query. <br/>
ex. select employee_id, first_name from employees as A
    where salary > (select avg(salary) from employees where department_id = A.department_id);
<br/>

    
 






