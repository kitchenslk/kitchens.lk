package com.janaka.kitchenslk.service;

import java.util.List;
import java.util.Map;

import com.janaka.kitchenslk.entity.Attribute;
import com.janaka.kitchenslk.enums.Status;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 21, 2013 - 7:24:55 PM
 * Project	: kitchenslk
 */
public interface MasterService {
	
	/**
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<Attribute> listAllAttributesByStatus(Status status)throws Exception;
	
	
	/**
	 * @param attribute
	 * @param currentUserId TODO
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdateAttribute(Attribute attribute, long currentUserId)throws Exception;
	
	
	/**
	 * @param attributeId
	 * @return
	 * @throws Exception
	 */
	public Attribute getAttributeById(long attributeId)throws Exception;


	/**
	 * @param term
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> listAttributesByTerm(String term)throws Exception;

}
