
/*----------USER ROLE----------------------------------------*/
/*THE ENUM*/
/*
 * ROLE_USER(1),
 * ROLE_MERCHANT(2),
 * ROLE_MERCHANT_ADMIN(3),
 * ROLE_MERCHANT_MANAGER(4),
 * ROLE_MERCHANT_DELIVERER(5),
 * ROLE_SUPERADMIN(6);	 
 * */
INSERT INTO user_role(user_role_id, user_role_type, version_id)
VALUES (1, 'ROLE_USER', 0)
	  ,(2, 'ROLE_MERCHANT', 0)
	  ,(3, 'ROLE_MERCHANT_ADMIN', 0)
      ,(4, 'ROLE_MERCHANT_MANAGER', 0)
      ,(5, 'ROLE_MERCHANT_DELIVERER', 0)
      ,(6, 'ROLE_SUPERADMIN', 0);