
package de.camel_cdi_proto.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.jms.ConnectionFactory;

import org.apache.camel.Component;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.springframework.jms.support.destination.JndiDestinationResolver;
import org.springframework.transaction.PlatformTransactionManager;

public class JmsComponentProducer {
  @Produces
  @ApplicationScoped
  @Named("jms")
  Component getJmsComponent(ConnectionFactory connectionFactory, PlatformTransactionManager jtaTransactionManager) {
    // simple jms Komponente
    // JmsComponent jms = JmsComponent.jmsComponentAutoAcknowledge(connectionFactory);
    // jms.setDestinationResolver(new JndiDestinationResolver());

    // simple jms component with local transaction
    // JmsComponent jms = JmsComponent.jmsComponentTransacted(connectionFactory, jtaTransactionManager);
    // jms.setDestinationResolver(new JndiDestinationResolver());

    // transaction by UserTransaktionManager from server with policy in route
    JmsConfiguration configuration = new JmsConfiguration(connectionFactory);
    configuration.setTransactionManager(jtaTransactionManager);
    configuration.setTransacted(false);
    JmsComponent jms = JmsComponent.jmsComponent(configuration);
    jms.setDestinationResolver(new JndiDestinationResolver());

    return jms;
  }

  // @Produces
  // @Named("jms")
  // @ApplicationScoped
  // // Some external configuration parameters
  // Component produceJmsComponent(@ConfigProperty(name = "jms.consumers") String inputQueueConcurrentConsumers)
  // {
  // CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);
  // cachingConnectionFactory.setSessionCacheSize(Integer.parseInt(consumers));
  // return JmsComponent.jmsComponent(cachingConnectionFactory);
  // }

  // void disposeJmsComponent(@Disposes @Named("jms") Component component) {
  // ((SingleConnectionFactory) ((JmsComponent) component).getConfiguration().getConnectionFactory()).destroy();
  // }
}
