# ContainerQoS
Simulation of QoS for Containers

STUDENT PROJECT
JAVA

OBJECTIVE

Create priorities for containers in a cloud environment.
Ensure prioritized containers run first


DESCRIPTION

This simulation creates:
  1 host 
  1 VM
  10 containers
  100 requests

Each container has a priority and is assumed to run one application
Each request will require a different bandwidth 

Traffic be scheduled based off of priority.

Base.java -> containers run requests using highest priority first
Enhancement.java -> containers run requests using highest priority first and Round Robin 