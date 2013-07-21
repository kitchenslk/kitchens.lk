package com.janaka.kitchenslk.service;

import java.util.List;

import com.janaka.kitchenslk.entity.Item;
import com.janaka.kitchenslk.enums.Status;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 13, 2013 - 11:52:32 PM
 * Project	: kitchenslk
 */
public interface ItemService {
	
	public String createItem(Item item)throws Exception;	
	
	public List<Item> listAllItems(Status status)throws Exception;
	

}
