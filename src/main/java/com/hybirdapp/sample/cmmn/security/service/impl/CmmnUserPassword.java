package com.hybirdapp.sample.cmmn.security.service.impl;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CmmnUserPassword {

	//private final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name="DynamicSecurityDAO")
	private DynamicSecurityDAO dynamicSecurityDAO;
	
	public int loadUserByPasswordChk(String id,String pw) throws UsernameNotFoundException {
		int passwordChk ;
		try {
			
	    	passwordChk = dynamicSecurityDAO.selectSecurityUserPassword(id,pw);
			
		} catch (Exception e) {
			//logger.error("loadUserByUsername", e);
			throw new UsernameNotFoundException(id);
		}
		
		return passwordChk;
	}
}
