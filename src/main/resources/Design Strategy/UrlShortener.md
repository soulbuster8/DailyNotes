Functional Requirements :-
1. Given an url, our service should generate a shorter and unique alias for it.
2. When users access this short link, our service should redirect them to original link.
3. These short links should expire after some time.(and user should be able to configure thoso expire time.)

Non-Functional Requirements :-
1. System should be highly available.
2. Minimum latency(URL redirection should happen in real-time.)
3. It should not be guessable.

Extended Requirements :-
1. Our service should be able to access through Rest Api by other services.
2. Analytics:- How many times a redirection happened?


Capacity Estimations and Constraints :- <br/>
Our system will be read-heavy. Let's assume 100:1 ratio between read and write.<br/>
Traffic Estimates :- 
Assuming, we will have 500M new URL shortenings per month, with 100:1 read/write ratio.<br/>
Read RQs :- 500M * 100 = 50B<br/>
QPS for write :- 500M/(30 days * 24 hours * 3600 secs) :- 200 URLs/sec <br/>
QPS for read :- 100*200 URLs/sec = 20K/sec

Storage estimates :- <br/>
Total URLs for 5 years :- 500M * 12 months * 5 years = 30B <br/>
Suppose each URL will take 500 bytes, then total storage :- 30B*500 bytes = 15 TB 

Bandwidth estimates :- <br/>
Total Incoming data :- 200 URLs/sec * 500 bytes for each URL = 100KB/sec
Total outgoing data :- 20K*500 bytes ~= 10MB/sec

Memory Estimates :- <br/>
If we want to cache some of the hot URLs that are frequently accessed, how much memory will be needed
to store them ? If we follow 80-20 rule, meaning 20% of the URLs generate 80% of the traffic, we would
like to cache these 20% hot URLs.

Since we have 20K requests/sec, we will be getting 1.7B requests/day.<br/>
20K QPS * 3600 sec * 24 hours= ~1.7B/day <br/>
To cache 20% of these requests, we will need 170GB of data.<br/>
1.7 B * 0.2 * 500 bytes = ~170GB <br/>
Note :- There will be many duplicate requests(of the same URL), our actual memory usage will be less than 
170 GB.

System Apis :- <br/>
createUrl(api_dev_key, original_url, expire_date):- <br/> 
api_dev_key :- The API developer key of a registered account. This will be used to, among other things, throttle users based on their allocated quota.

deleteUrl(api_dev_key, url_key)

Detect and prevent abuse:- A malicious user can put us out of business by consuming all URL keys in current design. To prevent abuse, we can limit users via their api_dev_key.
Each api_dev_key can be limited to certain number of URL creations and redirections per time period.   
  

Database Design :- <br/>
Observations :- 
1. We need to store billions of records.
2. Each object is small(less than 1K).
3. No relationships b/w records - other than storing which user created a URL.
4. Our service is read-heavy.

User :- user_id(PK), email_id, name, creation_date, last_login <br/>
URL :- id(PK), original_url, creation_date, expiration_date, user_id(FK). <br/>

We need to store billions of rows and we dont need to use relationship b/w objects - a NOSQL store like DynamoDB, Cassandra would be better choice. 


Basic System design and Algorithm :- <br/>

Length of the short key :- 6 or 8 or 10.<br/>
6 letters long key would result in 64^6 = ~68.7 billion for base 64 encoding. <br/>
8 letters long key would result in 64^8 = ~281 billion for base 64 encoding. <br/>
So 6 letter keys would be suffice.<br/>

1. We can compute a unique hash(MD5, SHA256) of the given url. 