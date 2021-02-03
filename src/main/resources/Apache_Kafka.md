Apache Kafka :- It is a distributed streaming platform for building real-time data pipelines and real-time streaming applications. Like a publish-subscribe system that
                can deliver in-order, persistent messages in a scalable way. Kafka is run as a cluster comprised of one or more servers each of which is called a broker 
                and communication between the clients and the servers is done with a simple, high-performance, language agnostic TCP protocol.
                

Note :- Kafka has many applications, one of which is real-time processing. Stream is a continuous stream of data. Some form of analysis is done and we get some useful data out
        of it. In terms of kafka, real-time processing involved reading data from a topic(source), doing some analysis or transformation work, and then writing the results back
        to another topic(sink). We can use kafka stream for this
    

Kafka Broker :- It will listen on port(by default 9092). We have to manually create the topic in broker and publisher will publish to the topic and broker will send it to 
    consumer. Consumer will poll the values from broker, it is not a push model (In Rabbit MQ, broker will push values to the consumer).
    
Kafka is log based meaning consumer will maintain the pointer and it will fetch the values from there. 
    
Queue vs Pub-Sub :- 
Queue :- Message published once, consumed once.
Pub-Sub :- Message publihed once, comsumed many times.                      

Kafka Stream :-  


                
ETL Process, Kafka streams :- 