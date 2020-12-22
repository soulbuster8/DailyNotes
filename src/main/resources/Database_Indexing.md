https://www.youtube.com/watch?v=aZjYr87r1b8  <br/>
**How data is stored in the memory**
1. Hard disk is divided into track and sectors and where that track and sector is intersecting each
other that part is known as blocks and our data will be stored on that blocks. Each blocks has a size
of 512 Bytes(it can be of any size.) 
2. Our rows are storing in that blocks itself in the hard disk.
3. So suppose whenever we are querying the DB like select * from employee. it will have to go to all
blocks and fetch the data. DB by default do indexing on primary key and it help us to find all the
records faster(for indexing we use B trees).  

**Database Indexing** <br/>
1. Index is used to quicken the search by reducing the number of record to search for.
2. 


**Primary Indexing**<br/>
1. Main file has to be sorted.
2. Primary key is used as an anchor attribute 
3. It is an example of sparse indexing.
4. No of entries = No of blocks acquired in the index file by main file.
https://www.youtube.com/watch?v=L-THExvsv0s

**Clustered Indexing**<br/>
Clustered index is a type of index which sorts the data rows in the table on the particular column
values. In the database, there is only one clustered index per table. 


**Secondary Indexing**<br/>
When we want to query the database with non-primary key because the frequencies of that queries is
very high then we need the secondary index to index on that column. It will not directly store tha 
table values instead of that it will store the reference of that.


**Composite Indexing** <br/>
MYSQL lets us define indices on multiple columns, up to 16 columns. It is called as Composite/Compound
/Multi-column indexing.<br/>
ex.:- We have an index defined on 4 columns :- col1, col2, col3, col4. With a compound indexing we 
have search capability on col1, (col1, col2), (col1, col2, col3), (col1, col2, col3, col4).<br/>
**Note**:- We can use any left side prefix of the indexed columns, but we can't omit a column from
a middle & use like that -(col1, col3) or (col1, col2, col4).

**Note**:- <br/>
Question :- Why not define multiple secondary indices on the columns we are interested in. Why to use
composite indices? <br/> 
Answer :- MYSQL uses only one index per table per query except for union. So defining multiple indices
on multiple columns does not guarantee those indices will be used even if they are part of the query.
<br/>
MYSQL maintains something called as **index statistics** which helps MYSQL infer what the data looks
like in the system. Index statistics is a generalization though, but based on this meta data, MYSQL
decides which index is appropriate for the current query. <br/>

**Dense Index** :- 
1. For every search key value in the data file, there is an index record.
2. This record contains the search key and also a reference to the first data record with that search
value.<br/>

**Sparse Index**:-
1. The index record appears only for a few items in the data file. Each item points to a block as
shown.
2. We will use binary search for looking the data.


**B-Tree** <br/>
1. B-Tree is a self-balancing search tree. In most of the other self-balancing trees(AVL, Red-black
trees), it is assumed that everything is in main memory. But in B-Trees, we must think of the huge 
amount of data that cannot fit in main memory.
2. When the number of keys is high, the data is read from the disk in form of blocks. Disk access time
is very high compared to the main memory access time. The main idea of using B-trees is to reduce
the number of disk accesses. <br/>

**BitMap Index** <br/>


1. In MYSQL, Primary key is usually stored with every single index so that query can be faster.
2. In MYSQL(InnoDB engine), there is always primary key(if we didn't specify, MYSQL will create for us) and primary key index and that index will point to the table. Any
    secondary index will not point to the table, it will point to the primary value which corresponds to the table. <br/>
    
**Covering Index** :- <br/>
An index that includes all the columns retrieved by a query. Instead of using the index values as pointers to find the full table rows, the query returns values from the index
structure, saving disk I/O. InnoDB can apply this optimization technique to more indexes than MYISAM can, because InnoDB secondary indexes also include the primary key columns.


Note:-<br/>
There are two things in DB:- <br/>
1. Planning time :- DB will plan whether to use index or do the sequential searching. 
2. Execution time :- Actual searching time.
<br/>

Having an index does not mean that DB will always use it. It will plan and according to the planer
it will decide to use index or not.<br/>
Suppose we have a employee table with Id, Name as a column and by default we have indexing on Id
and again we have done indexing on name. So if we execute our query like this
**select id, name from employee where name = 'ZX'** then this query will run fast because we have done
indexing on name(in MYSQL, every primary key will come with other index as well). <br/>
But if we do query **select id, name from employee where name like '%ZX%'** then in this scenario
our query will not run fast because in this case indexing will not help us. This is an expression
and for expression whole table will searched and we are not able to make use of indexing.




 
 