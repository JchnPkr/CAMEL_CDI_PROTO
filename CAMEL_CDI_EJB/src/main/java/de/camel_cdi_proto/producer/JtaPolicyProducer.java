
package de.camel_cdi_proto.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.springframework.transaction.PlatformTransactionManager;

public class JtaPolicyProducer {
  @Named
  @Produces
  @ApplicationScoped
  public SpringTransactionPolicy jtaRequiredPolicy(PlatformTransactionManager tm) {
    SpringTransactionPolicy jtaTrans = new SpringTransactionPolicy(tm);
    jtaTrans.setPropagationBehaviorName("PROPAGATION_REQUIRED");

    return jtaTrans;
  }

  @Named
  @Produces
  @ApplicationScoped
  public SpringTransactionPolicy jtaRequiresNewPolicy(PlatformTransactionManager tm) {
    SpringTransactionPolicy jtaTrans = new SpringTransactionPolicy(tm);
    jtaTrans.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");

    return jtaTrans;
  }

  @Named
  @Produces
  @ApplicationScoped
  public SpringTransactionPolicy jtaSupportsPolicy(PlatformTransactionManager tm) {
    SpringTransactionPolicy jtaTrans = new SpringTransactionPolicy(tm);
    jtaTrans.setPropagationBehaviorName("PROPAGATION_SUPPORTS");

    return jtaTrans;
  }

  @Named
  @Produces
  @ApplicationScoped
  public SpringTransactionPolicy jtaNotSupportedPolicy(PlatformTransactionManager tm) {
    SpringTransactionPolicy jtaTrans = new SpringTransactionPolicy(tm);
    jtaTrans.setPropagationBehaviorName("PROPAGATION_NOT_SUPPORTED");

    return jtaTrans;
  }

  @Named
  @Produces
  @ApplicationScoped
  public SpringTransactionPolicy jtaNeverPolicy(PlatformTransactionManager tm) {
    SpringTransactionPolicy jtaTrans = new SpringTransactionPolicy(tm);
    jtaTrans.setPropagationBehaviorName("PROPAGATION_NEVER");

    return jtaTrans;
  }

  @Named
  @Produces
  @ApplicationScoped
  public SpringTransactionPolicy jtaMandatoryPolicy(PlatformTransactionManager tm) {
    SpringTransactionPolicy jtaTrans = new SpringTransactionPolicy(tm);
    jtaTrans.setPropagationBehaviorName("PROPAGATION_MANDATORY");

    return jtaTrans;
  }
}
