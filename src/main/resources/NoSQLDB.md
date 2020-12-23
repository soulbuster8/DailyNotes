Note:- 
1. In SQL DB, we store data in tables and we have kind of foreign key concepts. In NOSQL, we store whole data at once.

SQL :- 
Person Table :- Id, Name, Address(FK), Age, Role
Address Table :- Id, City, State, Country, Pin code

No SQL :-
It will store all person details at once.
"23": { "name": "", "address": { "city": "",.....}, "age": "", "Role": "" }

So NoSQL is useful when we want insertion and retrieval of whole objects at once and it is very frequently.

Common types of NoSQL :- 
1. Key-Value Stores :- Data is stored in an array of key-value pairs. ex.:- Redis, Dynamo.
2. Document Database :- Data is stored in documents(instead of rows, columns in a table) and these documents are grouped together in collections. Each document can have an 
    entirely different structure. ex.:- Couch and MongoDB.
3. Wide-Column Databases :-  Instead of ‘tables,’ in columnar databases we have column families, which are containers for rows. Unlike relational databases, we don’t need to
            know all the columns up front and each row doesn’t have to have the same number of columns. Columnar databases are best suited for analyzing large datasets
            - big names include Cassandra and HBase. 
4. Graph Databases :- These databases are used to store data whose relations are best represented in a graph. Data is saved in graph structures with nodes (entities),
        properties (information about the entities), and lines (connections between the entities). Examples of graph database include Neo4J and InfiniteGraph.

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


Cassandra Database :-
 It is a distributed database management system designed for handling a high volume of structured data across commodity servers. Its main feature is to store data
 on multiple nodes with no single point of failure. It stores data on different nodes with a peer to peer distribution fashion architecture. All nodes exchange information
 with each other using GOSSIP Protocol.

Components in Cassandra :- 
1. Node :- It is the place where data is stored. 
2. Data Center :- A collection of nodes are called data center. 
3. Cluster :- Collection of many data centers.
4. Commit log :- Every write operation is written to commit log. Commit log is used for crash recovery.
5. Mem-table :- After data written in commit log, data is written in Mem-table. Data is written in Mem-Table temporarily.
6. SSTable :- When mem-table reaches a certain threshold, data is flushed to an SSTable disk file.f

Data Replication :- It places of data on different nodes based on theses two factors. 
1. Where to place next replica is determined by the Replication Strategy.
2. While the total number of replicas placed on different nodes is determined by the Replication factor. 

There are two kinds of replication strategies in Cassandra :- 
1. SimpleStrategy :- It places the first replica on the node selected by the partitioner. After that, remaning replicas are placed in clockwise direction in the Node ring.
2. NetworkTopologyStrategy :- In NetworkTopologyStrategy, replicas are set for each data center separately. NetworkTopologyStrategy places replicas in the clockwise 
            direction in the ring until reaches the first node in another rack.


Write Operation :- The coordinator sends a write request to replicas. If all the replicas are up, they will receive write request regardless of their consistency level.
    Consistency level determines how many nodes will respond back with the success acknowledgment. The node will respond back with the success acknowledgment if data
    is written successfully to the commit log and memTable.

For example, in a single data center with replication factor equals to three, three replicas will receive write request. If consistency level is one, only one replica will
 respond back with the success acknowledgment, and the remaining two will remain dormant.
Suppose if remaining two replicas lose data due to node downs or some other problem, Cassandra will make the row consistent by the built-in repair mechanism in Cassandra.

How write process occurs in Cassandra :- 
1. When write request comes to the node, first of all, it logs in the commit log.
2. Then Cassandra writes the data in the mem-table. Data written in the mem-table on each write request also writes in commit log separately. Mem-table is a temporarily 
    stored data in the memory while Commit log logs the transaction records for back up purposes.
3. When mem-table is full, data is flushed to the SSTable data file.

1. each table requires a unique primary key. The first field listed is the partition key, since its hashed value is used to determine the node to store the data. If 
   those fields are wrapped in parentheses then the partition key is composite. Otherwise the first field is the partition key. Any fields listed after the partition key
   are called clustering columns. 
   
   Clustering columns determines the order of data in partitions. The whole point of a column-oriented database like Cassandra is to put adjacent data records next to each
   other for fast retrieval. 



HBase :- 
It is an open-source, column oriented distributed database system. HBase can store massive amounts of data from terabytes to petabytes. The tables present in HBase consists
of billions of rows having millions of columns. HBase is built for low latency operations, which is having some specific features compared to traditional relational models.

Why Choose HBase :- A table for a popular web application may consist of billions of rows. If we want to search particular row from such a huge amount of data, HBase is
    the ideal choice as query fetch time in less. Most of the online analytics applications use HBase    
    

Cassandra vs HBase :- Whenever high availability is required, casssandra would be good because it replicates the data in different node but in HBase there is only one node where
data will be stored. So fir high consistency Hbase is suitable.                                     
