package com.janaka.kitchenslk.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Junction;

import com.janaka.kitchenslk.entity.Token;
import com.janaka.kitchenslk.enums.Status;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 14, 2013 - 12:57:32 PM
 * Project	: kitchenslk
 */
public interface CommonService {
	
	 /**
     * Get a row from the database populated into the entity
     * 
     * @param classz
     * @param id
     * @return
     *
     */
    public <Entity> Entity getEntityById(Class classz, long id)throws Exception;

    /**
     * Generic create
     * 
     * @param entity
     * @return
     *
     */
    public <Entity> long createEntity(Entity entity)throws Exception;

    /**
     * Generic update
     * 
     * @param entity
     * @return
     *
     */
    public <Entity> String updateEntity(Entity entity)throws Exception;
    
    /**
     * Generic save or update
     * 
     * @param entity
     * @return
     *
     */
    public <Entity> String saveOrUpdateEntity(Entity entity)throws Exception;

    /**
     * Generic delete
     * 
     * @param entity
     * @return
     *
     */
    public <Entity> String deleteEntity(Entity entity) throws Exception;

    /**
     * Get a proxy object without hitting the db
     * 
     * @param classz
     * @param id
     * @return
     *
     */
    public <Entity> Entity loadEntityById(Class classz, long id) throws Exception;
    
    
    /**
     * @param classz
     * @param status
     * @return
     */
    public <Entity> List<Entity> getAllEntitiesByStatus(Class classz,Status status)throws Exception;

	
	/**
	 * @param classz
	 * @param retrivingFieldMap
	 * @param junction
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> listGivenFieldsByGivenCriteria(Class classz,Map<String,String> retrivingFieldMap, Criterion criterion)throws Exception;

	/**
	 * @param class1
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 * @throws Exception 
	 */
	public <Entity> Entity getEntityByGivenFieldValue(Class<Entity> class1, String fieldName,	String fieldValue) throws Exception; 

}
