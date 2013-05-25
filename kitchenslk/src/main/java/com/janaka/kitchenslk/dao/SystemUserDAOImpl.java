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

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 23, 2013 - 12:31:55 PM
 * Project	: kitchenslk
 */
@Repository(value="systemUserDAO")
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class SystemUserDAOImpl implements SystemUserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.janaka.kitchenslk.dao.SystemUserDAO#getSystemUserByUserName(java.lang.String)
	 */
	@Override
	public SystemUser getSystemUserByUserName(String username) throws Exception {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(SystemUser.class);
		criteria.add(Restrictions.eq("userName", username));
		SystemUser systemUser=(SystemUser) criteria.uniqueResult();
		Hibernate.initialize(systemUser.getUserRoles());
		return systemUser;
	}

}
