
package de.camel_cdi_proto.producer;

import org.springframework.transaction.PlatformTransactionManager;

public interface TransactionManagerProducer {
  public PlatformTransactionManager getJtaTransactionManager();
}
