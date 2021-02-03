Amazon SQS(Simmple Queue Service) :- 
It is a queue service where publisher can post the message and consumer can consume it in default/FIFO manner.

Amazon SQS visibility timeout :- 
When a consumer receives and processes a message from a queue, the message remains in the queue. Amazon SQS doesn't automatically delete the message. Because Amazon SQS is a 
distributed system, there's no guarantee that the consumer actually receives the message (for example, due to a connectivity issue, or due to an issue in the consumer
application). Thus, the consumer must delete the message from the queue after receiving and processing it. 
Immediately after a message is received, it remains in the queue. To prevent other consumers from processing the message again, Amazon SQS sets a visibility timeout, a period 
of time during which Amazon SQS prevents other consumers from receiving and processing the message. The default visibility timeout for a message is 30 seconds. The minimum is 0
seconds. The maximum is 12 hours.

SQS have a capability where it says that you have to push jobs into the queue after x seconds.