# LocksExample

We often come across race conditions in multi threaded environment where multiple threads are competing to 
access the same shared resources. One way of tackling such scenarios is to have syncronized all the resources 
and like wise there are so many ways based on the scenarios. In this example I have used REENTRANT locks in java
to tackle the same situation

NOTE: This example will only work for single instance, in distributed system with multiple instances running (Horizontal scaling) we have to introduce distributed locks which I will cover in another repo and provide link here ;)
