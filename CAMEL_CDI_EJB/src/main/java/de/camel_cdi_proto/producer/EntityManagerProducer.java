
package de.camel_cdi_proto.producer;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {
  @Produces
  @PersistenceContext(unitName = "CamelCdiProto")
  EntityManager entityManager;
}
