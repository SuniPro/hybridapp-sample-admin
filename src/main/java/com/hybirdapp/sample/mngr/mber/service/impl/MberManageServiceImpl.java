package com.hybirdapp.sample.mngr.mber.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.hybirdapp.sample.mngr.mber.service.MberManageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.cmmn.script.service.ScriptService;
import com.hybirdapp.sample.mngr.mber.service.MberManageVO;

@Service("MberManageService")
public class MberManageServiceImpl implements MberManageService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name="MberManageDAO")
	private MberManageDAO mberManageDAO;
	
	@Resource(name = "ScriptService")
	private ScriptService  scriptService;
	
	@Override
	public List<MberManageVO> searchMberManageList(MberManageVO vo) throws Exception {
		
		List<MberManageVO> result = null;
		
		try {
			result = mberManageDAO.selectMngrMemberList(vo);
			vo.setTotPageCnt(mberManageDAO.selectMberManageTotalCnt(vo));
			
		} catch (Exception e) {
			logger.error("searchMberManageList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}

	
	@Override
	public MberManageVO selectMngrMemberDetail(MberManageVO vo) throws Exception {
		
		MberManageVO result = null;
		
		try {
			result = mberManageDAO.selectMngrMemberDetail(vo);
		} catch (Exception e) {
			logger.error("searchMberManageList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}


	@Override
	public int memberBlock(MberManageVO vo) throws Exception {
		int result = 0;
		
		try {
			result = mberManageDAO.memberBlock(vo);
		} catch (Exception e) {
			logger.error("memberBlock", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.update", null));
		}
		
		return result;
	}


	@Override
	public List<MberManageVO> areaSelect() throws Exception {
			List<MberManageVO> result = null;
		try {
			result = mberManageDAO.areaSelect();
			
		} catch (Exception e) {
			logger.error("areaSelect", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public MberManageVO mainMemberCount() throws Exception {
		MberManageVO result = null;
		try {
			result = mberManageDAO.mainMemberCount();
			
		} catch (Exception e) {
			logger.error("mainMemberCount", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public MberManageVO mainTodayMemberCount() throws Exception {
		MberManageVO result = null;
		try {
			result = mberManageDAO.mainTodayMemberCount();
			
		} catch (Exception e) {
			logger.error("mainMemberCount", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public MberManageVO mainEveMemberCount() throws Exception {
		MberManageVO result = null;
		try {
			result = mberManageDAO.mainEveMemberCount();
			
		} catch (Exception e) {
			logger.error("mainMemberCount", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> mainMemberSelaCount() throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		try {
			result = mberManageDAO.mainMemberSelaCount();
			
			
		} catch (Exception e) {
			logger.error("mainMemberSelaCount", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> mainMemberSelaFcltCount() throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		try {
			result = mberManageDAO.mainMemberSelaFcltCount();
			
			
		} catch (Exception e) {
			logger.error("mainMemberSelaFcltCount", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> mainMemberSelaEveCount() throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		try {
			result = mberManageDAO.mainMemberSelaEveCount();
			
			
		} catch (Exception e) {
			logger.error("mainMemberSelaEveCount", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> mainMemberSelngCount() throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		try {
			result = mberManageDAO.mainMemberSelngCount();
			
			
		} catch (Exception e) {
			logger.error("mainMemberSelngCount", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> mainMemberSelngFcltCount() throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		try {
			result = mberManageDAO.mainMemberSelngFcltCount();
			
			
		} catch (Exception e) {
			logger.error("mainMemberSelngFcltCount", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> mainMemberSelngEveCount() throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		try {
			result = mberManageDAO.mainMemberSelngEveCount();
			
			
		} catch (Exception e) {
			logger.error("mainMemberSelngEveCount", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}

	@Override
	public List<HashMap<String, Object>> execSqlString ( String  sql ) throws Exception {
		List<HashMap<String, Object>>	result	=	null;

		try {
			result = mberManageDAO.execSqlString( sql );
		}catch (Exception e) {
			logger.error("execSqlString", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}


}
