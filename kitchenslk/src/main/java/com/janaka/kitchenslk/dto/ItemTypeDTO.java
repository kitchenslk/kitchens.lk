package com.janaka.kitchenslk.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 14, 2013 - 8:03:20 PM
 * Project	: kitchenslk
 */
public class ItemTypeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int itemTypeId;
	private List<ItemAttributeDTO> itemAttributeDTOs;

}
