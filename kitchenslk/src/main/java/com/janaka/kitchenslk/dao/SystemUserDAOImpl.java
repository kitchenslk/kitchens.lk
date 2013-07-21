package com.janaka.kitchenslk.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.janaka.kitchenslk.entity.SystemUser;
import com.janaka.kitchenslk.entity.TempSystemUser;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 23, 2013 - 12:31:55 PM
 * Project	: kitchenslk
 */
@Repository(value="systemUserDAO")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=false)
public class SystemUserDAOImpl implements SystemUserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public SystemUser getSystemUserByUserName(String username) throws Exception {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(SystemUser.class);
		criteria.add(Restrictions.eq("userName", username));
		SystemUser systemUser=(SystemUser) criteria.uniqueResult();
		if(!(systemUser==null)){
			Hibernate.initialize(systemUser.getUserRoles());
		}
		return systemUser;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public TempSystemUser getTempSystemUserByEncryptedUserName(String userName)
			throws Exception {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(TempSystemUser.class);
		criteria.add(Restrictions.eq("encryptedTempUserName", userName));
		TempSystemUser systemUser=(TempSystemUser) criteria.uniqueResult();		
		return systemUser;
	}

}
