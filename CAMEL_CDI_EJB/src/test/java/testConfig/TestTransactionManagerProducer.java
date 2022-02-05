
package testConfig;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import de.camel_cdi_proto.producer.TransactionManagerProducer;

@Alternative
public class TestTransactionManagerProducer implements TransactionManagerProducer {
  @Produces
  public PlatformTransactionManager getJtaTransactionManager() {
    return new JtaTransactionManager(com.arjuna.ats.jta.UserTransaction.userTransaction());
  }
}
