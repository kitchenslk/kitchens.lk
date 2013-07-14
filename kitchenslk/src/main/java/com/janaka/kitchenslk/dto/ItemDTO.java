package com.janaka.kitchenslk.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 14, 2013 - 7:54:48 PM
 * Project	: kitchenslk
 */
public class ItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String itemName;
	private String itemDescription;
	private List<ItemTypeDTO> itemTypeDTOs;
	

}
