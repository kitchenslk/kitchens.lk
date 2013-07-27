package com.janaka.kitchenslk.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserRoleType {
	ROLE_USER(1),
	ROLE_MERCHANT(2),	
	ROLE_MERCHANT_ADMIN(3),
	ROLE_MERCHANT_MANAGER(4),
	ROLE_MERCHANT_DELIVERER(5),		
	ROLE_SUPERADMIN(6);
	
	
	private int userRoleId;
	private static Map<Integer,UserRoleType> map;
	
	static{
		map=new HashMap<>();
		for (UserRoleType userRoleType : UserRoleType.values()) {
			map.put(userRoleType.userRoleId, userRoleType);
		}
	}

    private UserRoleType(int userRoleId) {
       this.userRoleId = userRoleId;
    }


    public static UserRoleType getUserRoleTypeByUserRoleId(int userRoleId){
    	return map.get(userRoleId);
    }
    
	
}
