Camel CDI Example
=================

This example shows how to work with Camel in a JavaEE EJB/CDI Container using CDI to configure components,
endpoints and beans. Unittests included.

The example generates messages using a timer trigger and writes them to an output queue.
To run it, the project has to be deployed on a server with configured connection factory, jms queues and resources,
as defined in the properties and configuration of this project.
The tests are runnable in standalone mode.