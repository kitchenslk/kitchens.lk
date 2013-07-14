package com.janaka.kitchenslk.service;

import java.util.List;

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
    public <Entity> Entity getEntityById(Class classz, long id) ;

    /**
     * Generic create
     * 
     * @param entity
     * @return
     *
     */
    public <Entity> long createEntity(Entity entity) ;

    /**
     * Generic update
     * 
     * @param entity
     * @return
     *
     */
    public <Entity> String updateEntity(Entity entity) ;
    
    /**
     * Generic save or update
     * 
     * @param entity
     * @return
     *
     */
    public <Entity> String saveOrUpdateEntity(Entity entity) ;

    /**
     * Generic delete
     * 
     * @param entity
     * @return
     *
     */
    public <Entity> String deleteEntity(Entity entity) ;

    /**
     * Get a proxy object without hitting the db
     * 
     * @param classz
     * @param id
     * @return
     *
     */
    public <Entity> Entity loadEntityById(Class classz, long id) ;
    
    
    /**
     * @param classz
     * @param status
     * @return
     */
    public <Entity> List<Entity> getAllEntitiesByStatus(Class classz,Status status); 

}
