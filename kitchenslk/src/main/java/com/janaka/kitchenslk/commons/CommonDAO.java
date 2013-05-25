package com.janaka.kitchenslk.commons;

@SuppressWarnings("rawtypes") 
public interface CommonDAO {

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

}
