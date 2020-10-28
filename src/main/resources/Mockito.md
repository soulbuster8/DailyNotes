**Mockito Framework**

Verify call on the mock objects :-
Mockito keeps track of all the method calls and their parameters 
to the mock object. You can use the verify method on mock object
to verify that the specified conditions are met.

List<String> mockedList = mock(MyList.class);
mockedList.size();
1. verify(mockedList).size();
2. verify(mockedList, times(1)).size();
