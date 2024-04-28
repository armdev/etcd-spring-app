# etcd-spring-app
spring 3.2.5, etcd, docker compose, java 21

a. ./build.sh

b. ./run.sh

c. http://localhost:5076/swagger-ui.html

d. http://localhost:6076/actuator/httpexchanges

# Description

etcd is commonly used for coordinating distributed systems. It provides a reliable and consistent way to store and retrieve key-value pairs, making it well-suited for managing configuration data and coordinating distributed systems. Some common use cases for etcd include service discovery, distributed locking, and leader election.

One of the key benefits of using etcd for coordinating distributed systems is its strong consistency guarantees. It uses the Raft consensus algorithm to ensure that all nodes in a cluster have a consistent view of the data. This makes it easier to build reliable and fault-tolerant distributed systems.

In addition to its consistency guarantees, etcd is also highly available and scalable. It can be deployed in a cluster of nodes, with each node holding a copy of the data. If one node fails, the other nodes can continue to serve requests and maintain the consistency of the data.

Overall, etcd is a powerful tool for coordinating distributed systems. Its reliability, consistency, and scalability make it a popular choice for managing configuration data and coordinating services in large-scale deployments.
