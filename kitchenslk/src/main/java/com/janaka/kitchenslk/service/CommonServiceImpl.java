package com.janaka.kitchenslk.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Junction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaka.kitchenslk.dao.CommonDAO;
import com.janaka.kitchenslk.entity.Attribute;
import com.janaka.kitchenslk.enums.Status;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 14, 2013 - 12:58:19 PM
 * Project	: kitchenslk
 */
@Service(value="commonService")
public class CommonServiceImpl implements CommonService{
	
	@Autowired
	private CommonDAO commonDAO;

	@Override
	public <Entity> Entity getEntityById(Class classz, long id)throws Exception {
		return commonDAO.getEntityById(classz, id);
	}

	@Override
	public <Entity> long createEntity(Entity entity)throws Exception{
		return commonDAO.createEntity(entity);
	}

	@Override
	public <Entity> String updateEntity(Entity entity)throws Exception {
		return commonDAO.updateEntity(entity);
	}

	@Override
	public <Entity> String saveOrUpdateEntity(Entity entity) throws Exception{
		return commonDAO.saveOrUpdateEntity(entity);
	}

	@Override
	public <Entity> String deleteEntity(Entity entity)throws Exception {
		return commonDAO.deleteEntity(entity);
	}

	@Override
	public <Entity> Entity loadEntityById(Class classz, long id)throws Exception {
		return commonDAO.loadEntityById(classz, id);
	}

	@Override
	public <Entity> List<Entity> getAllEntitiesByStatus(Class classz,
			Status status)throws Exception {
		return commonDAO.getAllEntitiesByStatus(classz, status);
	}
	
	@Override
	public List<Map<String, Object>> listGivenFieldsByGivenCriteria(Class classz,Map<String,String> retrivingFieldMap,Criterion criterion)throws Exception {
		return commonDAO.listGivenFieldsByGivenCriteria(classz,retrivingFieldMap,criterion);
	}
	
	@Override
	public <Entity> Entity getEntityByGivenFieldValue(Class<Entity> class1,	String fieldName, String fieldValue)throws Exception {
		return commonDAO.getEntityByGivenFieldValue(class1,fieldName, fieldValue);
	}

}
