package com.hybirdapp.sample.mngr.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.hybirdapp.sample.mngr.auth.service.AuthService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.mngr.auth.service.AuthVO;

@Service("AuthService")
public class AuthServiceImpl implements AuthService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name = "AuthDAO")
	private AuthDAO authDAO;
	
	@Override
	public List<AuthVO> selectAuthMenuList(AuthVO vo) throws Exception {
		
		List<AuthVO> result = null;
		
		try {
			
			result = authDAO.selectAuthMenuList(vo);
			
		} catch (Exception e) {
			logger.error("selectAuthMenuList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}

	@Override
	public List<AuthVO> selectAuthList(AuthVO vo) throws Exception {
		
		List<AuthVO> result = null;
		
		try {
			
			result = authDAO.selectAuthList(vo);
			
		} catch (Exception e) {
			logger.error("selectAuthList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public void registAuth(AuthVO vo) throws Exception {
		
		try {
			authDAO.insertAuth(vo);
			List<String> checkArr =  new ArrayList<>();
			if("Y".equals(vo.getAuthBasicAt())) {
				checkArr = authDAO.selectMenuList();
				for(int i = 0 ; i < checkArr.size() ; i++) {
					vo.setMenuCd(checkArr.get(i));
					authDAO.authMenuCheckSave(vo);
				}
			}
			
		} catch (Exception e) {
			logger.error("registAuth", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.insert", null));
		}
	}

	@Override
	public void authRemove(AuthVO vo) throws Exception {
		try {
			
			authDAO.authRemove(vo);
			
		} catch (Exception e) {
			logger.error("authRemove", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.delete", null));
		}
	}
	
	@Override
	public AuthVO authRemoveCheck(AuthVO vo) throws Exception {
		AuthVO authVO = null;
		try {
			authVO = authDAO.authRemoveCheck(vo);
		} catch (Exception e) {
			logger.error("authRemoveCheck", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.delete", null));
		}
		return authVO;
	}

	@Override
	public void authMenuCheckSave(List<String> checkArr, AuthVO vo ) throws Exception {

		try {
			for(int i = 1 ; i < checkArr.size() ; i++) {
				vo.setAuthCd(checkArr.get(0));
				vo.setMenuCd(checkArr.get(i));
				authDAO.authMenuCheckSave(vo);
			}
		} catch (Exception e) {
			logger.error("authMenuCheckSave", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.save", null));
		}
	}

	@Override
	public void authMenuNotCheckSave(List<String> unCheckArr) throws Exception {
		
		if(!unCheckArr.isEmpty()) {
			
			AuthVO vo = new AuthVO();
			
			try {
				
				for(int i = 1 ; i < unCheckArr.size() ; i++) {
					vo.setAuthCd(unCheckArr.get(0));
					vo.setMenuCd(unCheckArr.get(i));
					authDAO.authMenuNotCheckSave(vo);
					
				}
			} catch (Exception e) {
				logger.error("authMenuNotCheckSave", e);
				throw new CmmnUserException(MessageManager.getIGMessage("server.fail.save", null));
			}
		}
	}

	@Override
	public List<?> selectMenu(String authCd) throws Exception {
		
		List<?> result = null;
		
		try {
			
			result = authDAO.selectMenu(authCd);
			
		} catch (Exception e) {
			logger.error("selectMenu", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}

	@Override
	public AuthVO selectAuthOne(AuthVO vo) throws Exception {
		AuthVO authVO = null;

		try{
			
			authVO = authDAO.selectAuthOne(vo);
			
		}
		catch(Exception ex) {

			logger.error("searchClGrp", ex);
			// 조회중 오류가 발생했습니다.
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return authVO;
	}

	@Override
	public void updateAuth(AuthVO vo) throws Exception {
		try {		
			authDAO.updateAuth(vo);				
		}
		catch (Exception ex) {
			
			logger.error("modifyClGrp", ex);
			// 수정중 오류발생
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.update", null));
		}
		
	}
}
