package com.hybirdapp.sample.cmmn.login.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.hybirdapp.sample.cmmn.security.service.impl.DynamicSecurityDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.CmmnConstant;
import com.hybirdapp.sample.cmmn.login.service.LoginService;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name = "DynamicSecurityDAO")
	private DynamicSecurityDAO dynamicSecurityDAO;
	
	@Override
	public List<?> searchBasicViewMenuList() throws Exception {
		return dynamicSecurityDAO.selectSecurityUserMenuList(CmmnConstant.SECURITY_ANONYMOUS_ROLE);
	}
	
	@Override
	public List<?> searchBasicViewMenuNaviList() throws Exception {
		return dynamicSecurityDAO.selectSecurityMenuNaviList(CmmnConstant.SECURITY_ANONYMOUS_ROLE);
	}
}
