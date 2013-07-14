package com.janaka.kitchenslk.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.security.Restrict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.janaka.kitchenslk.enums.Status;
import com.janaka.kitchenslk.util.ApplicationConstants;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository(value = "commonDAO")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CommonDAOImpl implements CommonDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public CommonDAOImpl(){}

	public CommonDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public <Entity> long createEntity(Entity entity) {
		Object obj=sessionFactory.getCurrentSession().save(entity);
		if(!(obj==null))
		return (Long) obj;
		return 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public <Entity> String deleteEntity(Entity entity) {
		sessionFactory.getCurrentSession().delete(entity);
		return "success";
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public <Entity> Entity getEntityById(Class classz, long id) {

		return (Entity) sessionFactory.getCurrentSession().get(classz, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public <Entity> Entity loadEntityById(Class classz, long id) {
		return (Entity) sessionFactory.getCurrentSession().load(classz, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public <Entity> String updateEntity(Entity entity) {
		sessionFactory.getCurrentSession().update(entity);
		return ApplicationConstants.SUCCESS;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public <Entity> String saveOrUpdateEntity(Entity entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return ApplicationConstants.SUCCESS;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public <Entity> List<Entity> getAllEntitiesByStatus(Class classz,Status status) {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(classz);
		if(!(status==null)){
			criteria.add(Restrictions.eq("status", status));
		}
		return criteria.list();
	}
	
}
