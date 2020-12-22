Note:- 
1. In SQL DB, we store data in tables and we have kind of foreign key concepts. In NOSQL, we store whole data at once.

SQL :- 
Person Table :- Id, Name, Address(FK), Age, Role
Address Table :- Id, City, State, Country, Pin code

No SQL :-
It will store all person details at once.
"23": { "name": "", "address": { "city": "",.....}, "age": "", "Role": "" }

So NoSQL is useful when we want insertion and retrieval of whole objects at once and it is very frequently.

2. In NOSQL, Adding of new column is very easy as compare to SQL.
3. Built for scale because they are horizontally scalable and highly available. 
4. Built for metrics analytics/aggregation.

Disadvantages :-
1. Not built for too many updates meaning consistency is not guarenteed over here(ACID Property).
2. Read times are comparatively slower. 
3. Relations are not implicit.
4. Joins are hard.

Cassandra :- 
1. It is giving us Load Balancing.
2. It is replicating the DB.
3. It has Quorum :- 