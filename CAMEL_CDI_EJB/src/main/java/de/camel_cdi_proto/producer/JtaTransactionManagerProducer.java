
package de.camel_cdi_proto.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

public class JtaTransactionManagerProducer implements TransactionManagerProducer {
  @Produces
  @ApplicationScoped
  public PlatformTransactionManager getJtaTransactionManager() {
    JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
    jtaTransactionManager.afterPropertiesSet();

    return jtaTransactionManager;
  }
}
