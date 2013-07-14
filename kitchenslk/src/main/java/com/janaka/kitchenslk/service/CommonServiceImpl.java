package com.janaka.kitchenslk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaka.kitchenslk.dao.CommonDAO;
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
	public <Entity> Entity getEntityById(Class classz, long id) {
		return commonDAO.getEntityById(classz, id);
	}

	@Override
	public <Entity> long createEntity(Entity entity) {
		return commonDAO.createEntity(entity);
	}

	@Override
	public <Entity> String updateEntity(Entity entity) {
		return commonDAO.updateEntity(entity);
	}

	@Override
	public <Entity> String saveOrUpdateEntity(Entity entity) {
		return commonDAO.saveOrUpdateEntity(entity);
	}

	@Override
	public <Entity> String deleteEntity(Entity entity) {
		return commonDAO.deleteEntity(entity);
	}

	@Override
	public <Entity> Entity loadEntityById(Class classz, long id) {
		return commonDAO.loadEntityById(classz, id);
	}

	@Override
	public <Entity> List<Entity> getAllEntitiesByStatus(Class classz,
			Status status) {
		return commonDAO.getAllEntitiesByStatus(classz, status);
	}

}
