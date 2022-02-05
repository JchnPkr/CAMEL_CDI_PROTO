
package de.camel_cdi_proto.producer;

import javax.jms.ConnectionFactory;

public interface ConnectionFactoryProducer {
  public ConnectionFactory getConnectionFactory();
}