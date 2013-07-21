package com.janaka.kitchenslk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaka.kitchenslk.commons.CommonFunctions;
import com.janaka.kitchenslk.entity.Attribute;
import com.janaka.kitchenslk.entity.SystemUser;
import com.janaka.kitchenslk.enums.Status;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 21, 2013 - 7:37:23 PM
 * Project	: kitchenslk
 */
@Service(value="masterService")
public class MasterServiceImpl implements MasterService {
	
	@Autowired
	private CommonService commonService;


	@Override
	public List<Attribute> listAllAttributesByStatus(Status status)throws Exception {
		return commonService.getAllEntitiesByStatus(Attribute.class, Status.ACTIVE);
	}

	@Override
	public String saveOrUpdateAttribute(Attribute attribute, long currentUserId) throws Exception {
		long id=attribute.getAttributeId();
		Attribute attributeFromDb=null;
		SystemUser currentUser=commonService.loadEntityById(SystemUser.class, currentUserId);
		if(id==0){			
			attribute.setCommonDomainProperty(CommonFunctions.getCommonDomainPropertyForSavingEntity(currentUser));
			attributeFromDb=attribute;
		}else{
			attributeFromDb=commonService.getEntityById(Attribute.class, attribute.getAttributeId());
			attributeFromDb.setAttributeName(attribute.getAttributeName());
			attributeFromDb.setAttributeDescription(attribute.getAttributeDescription());
			attributeFromDb.setStatus(attribute.getStatus());
			attributeFromDb.setCommonDomainProperty(CommonFunctions.getCommonDomainPropertyForUpdatingEntity(attributeFromDb.getCommonDomainProperty(), currentUser));
		}
		return commonService.saveOrUpdateEntity(attributeFromDb);
	}

	@Override
	public Attribute getAttributeById(long attributeId) throws Exception {
		return commonService.getEntityById(Attribute.class, attributeId);
	}
	
	@Override
	public List<Map<String, Object>> listAttributesByTerm(String term)throws Exception {
		Map<String,String> retrivingFieldMap=new HashMap<String, String>();
		retrivingFieldMap.put("attributeId", "attributeId");
		retrivingFieldMap.put("attributeName", "attributeName");
		retrivingFieldMap.put("attributeDescription", "attributeDescription");
		Conjunction conjunction=Restrictions.conjunction();
		conjunction.add(Restrictions.eq("status", Status.ACTIVE));
		Disjunction disjunction=Restrictions.disjunction();
		disjunction.add(Restrictions.ilike("attributeName", term, MatchMode.ANYWHERE));
		disjunction.add(Restrictions.ilike("attributeDescription", term, MatchMode.ANYWHERE));
		conjunction.add(disjunction);
		return commonService.listGivenFieldsByGivenCriteria(Attribute.class, retrivingFieldMap, conjunction);
	}

}
