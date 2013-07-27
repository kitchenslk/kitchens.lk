package com.janaka.kitchenslk.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
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
	public <Entity> long createEntity(Entity entity) throws Exception{
		Object obj=sessionFactory.getCurrentSession().save(entity);
		if(!(obj==null))
		return (Long) obj;
		return 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public <Entity> String deleteEntity(Entity entity) throws Exception{
		sessionFactory.getCurrentSession().delete(entity);
		return "success";
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public <Entity> Entity getEntityById(Class classz, long id) throws Exception{

		return (Entity) sessionFactory.getCurrentSession().get(classz, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public <Entity> Entity loadEntityById(Class classz, long id)throws Exception {
		return (Entity) sessionFactory.getCurrentSession().load(classz, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public <Entity> String updateEntity(Entity entity)throws Exception {
		sessionFactory.getCurrentSession().update(entity);
		return ApplicationConstants.SUCCESS;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public <Entity> String saveOrUpdateEntity(Entity entity)throws Exception {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return ApplicationConstants.SUCCESS;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public <Entity> List<Entity> getAllEntitiesByStatus(Class classz,Status status) throws Exception{
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(classz);
		if(!(status==null)){
			criteria.add(Restrictions.eq("status", status));
		}
		return criteria.list();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Map<String, Object>> listGivenFieldsByGivenCriteria(Class classz,Map<String,String> retrivingFieldMap, Criterion criterion)throws Exception {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(classz);
		ProjectionList projectionList=Projections.projectionList();
		for (Entry<String, String> entry : retrivingFieldMap.entrySet()) {
			projectionList.add(Property.forName(entry.getKey()).as(entry.getValue()));
		}
		criteria.setProjection(projectionList);
		criteria.add(criterion);
		return criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public <Entity> Entity getEntityByGivenFieldValue(Class<Entity> class1,	String fieldName, String fieldValue) throws Exception {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(class1);
		criteria.add(Restrictions.eq(fieldName, fieldValue));
		return (Entity) criteria.uniqueResult();
	}
	
}
