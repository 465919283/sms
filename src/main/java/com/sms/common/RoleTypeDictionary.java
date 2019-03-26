package com.sms.common;

import java.util.HashMap;
import java.util.Map;

public class RoleTypeDictionary 
{
	private static final Map<RoleType, String> roleTypeMap = new HashMap<>();
	
	static 
	{
		roleTypeMap.put(RoleType.ADMINISTRATOR, "ϵͳ����Ա");
		roleTypeMap.put(RoleType.PRESIDENT, "У��");
		roleTypeMap.put(RoleType.DIRECTOR, "԰��");
		roleTypeMap.put(RoleType.GROUOP_LEADER, "������");
	}
	
	public static String getRoleTypeName(RoleType roleType)
	{
		return roleTypeMap.get(roleType);
	}
}
