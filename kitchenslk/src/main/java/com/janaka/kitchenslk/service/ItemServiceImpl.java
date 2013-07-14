package com.janaka.kitchenslk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaka.kitchenslk.dao.CommonDAO;
import com.janaka.kitchenslk.entity.Attribute;
import com.janaka.kitchenslk.entity.Item;
import com.janaka.kitchenslk.enums.Status;
import com.janaka.kitchenslk.util.ApplicationConstants;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 14, 2013 - 12:01:36 AM
 * Project	: kitchenslk
 */
@Service(value="itemService")
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private CommonDAO commonDAO;

	@Override
	public String createItem(Item item) throws Exception {
		commonDAO.createEntity(item);
		return ApplicationConstants.SUCCESS;
	}

	@Override
	public List<Attribute> listAllAttributes(Status status) throws Exception {
		return commonDAO.getAllEntitiesByStatus(Attribute.class, status);
	}
	
	@Override
	public List<Item> listAllItems(Status status) throws Exception {
		return commonDAO.getAllEntitiesByStatus(Item.class,status);
	}

}
