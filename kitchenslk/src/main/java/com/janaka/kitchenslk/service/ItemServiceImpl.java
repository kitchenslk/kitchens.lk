package com.janaka.kitchenslk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaka.kitchenslk.commons.CommonFunctions;
import com.janaka.kitchenslk.dao.CommonDAO;
import com.janaka.kitchenslk.entity.Item;
import com.janaka.kitchenslk.entity.ItemAttributeValue;
import com.janaka.kitchenslk.entity.ItemType;
import com.janaka.kitchenslk.entity.SystemUser;
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
	public String createItem(Item item, SystemUser systemUser) throws Exception {
		item.setCommonDomainProperty(CommonFunctions.getCommonDomainPropertyForSavingEntity(systemUser));
		item.setStatus(Status.ACTIVE);
		if(!(item.getItemTypes()==null)){
			for (ItemType itemType : item.getItemTypes()) {
				itemType.setItem(item);	
				itemType.setStatus(Status.ACTIVE);
				itemType.setCommonDomainProperty(CommonFunctions.getCommonDomainPropertyForSavingEntity(systemUser));
				if(!(itemType.getItemAttributeValues()==null)){
					for (ItemAttributeValue itemAttributeValue : itemType.getItemAttributeValues()) {
						itemAttributeValue.setItemType(itemType);
						itemAttributeValue.setStatus(Status.ACTIVE);
						itemAttributeValue.setCommonDomainProperty(CommonFunctions.getCommonDomainPropertyForSavingEntity(systemUser));
					}
				}
			}
		}
		commonDAO.createEntity(item);
		return ApplicationConstants.SUCCESS;
	}


	@Override
	public List<Item> listAllItems(Status status) throws Exception {
		return commonDAO.getAllEntitiesByStatus(Item.class,status);
	}

}
