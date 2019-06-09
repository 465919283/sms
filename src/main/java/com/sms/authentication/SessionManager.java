package com.sms.authentication;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.sms.vo.RolesVO;

public class SessionManager {
    private Logger logger = null;
    private HashMap<String, Integer> sessionIdUserIdMap;
    private  HashMap<String, List<RolesVO>> roleList;
    
    public SessionManager() {
	logger = Logger.getLogger(SessionManager.class);
	sessionIdUserIdMap = new HashMap<String, Integer>();
	roleList=new HashMap<String, List<RolesVO>>();
    }
   
    public void insertSession(String sessionId, Integer userId) {
	logger.debug("User '" + userId + "' logon with session '" + sessionId + "'.");
	
	// Remove old session
	sessionIdUserIdMap.entrySet().removeIf(e -> e.getValue() == userId);

	// Put new session
	sessionIdUserIdMap.put(sessionId, userId);
    }
    public void insertRoles(String userId, List<RolesVO> roles) {
    	
    	// Remove old session
    	roleList.entrySet().removeIf(e -> e.getKey() == userId);

    	// Put new session
    	roleList.put(userId, roles);
        }
    
    public void removeSession(String sessionId) {
	logger.debug("Remove session '" + sessionId + "'.");
	sessionIdUserIdMap.remove(sessionId);
    }
    
     public void removeRoles(String userId) {
    	logger.debug("Remove roles '" + userId + "'.");
    	roleList.remove(userId);
        }
    public void removeSession(Integer userId) {
    	sessionIdUserIdMap.entrySet().removeIf(e -> e.getValue() == userId);
    }
    
    public Integer getUserIdBySessionId(String sessionId) {
	return sessionIdUserIdMap.getOrDefault(sessionId, null);
    }
    
    public List<RolesVO> getRolesByUserId(String userId) {
    	return roleList.getOrDefault(userId, null);
        }
    
    public boolean isValidSession(String sessionId) {
	return sessionIdUserIdMap.containsKey(sessionId);
    }
}
