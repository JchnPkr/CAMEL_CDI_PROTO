
package de.camel_cdi_proto.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.camel_cdi_proto.entity.DemoEntity;

public class DemoDAO implements IDemoDAO {
	@Inject
	private EntityManager em;

	public DemoEntity create(String value) {
		DemoEntity demoEntity = new DemoEntity();
		demoEntity.setDemoValue(value);

		em.persist(demoEntity);

		return demoEntity;
	}

	public DemoEntity create(DemoEntity entity) {
		em.persist(entity);

		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<DemoEntity> findByValue(String value) {
		Query query = em.createNativeQuery(
				"select * from DEMO where DEMO_VALUE = ?1 ", DemoEntity.class);

		query.setParameter(1, value);

		return query.getResultList();
	}

	public DemoEntity find(long pk) {
		return em.find(DemoEntity.class, pk);
	}

	public DemoEntity update(DemoEntity entity) {
		return em.merge(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getData(){
		return em.createQuery("SELECT d.ID from DEMO d;").getResultList();
	}
}
