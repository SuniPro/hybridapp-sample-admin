package com.hybirdapp.sample.mngr.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.cmmn.sys.service.impl.AuthorMngDAO;
import com.hybirdapp.sample.mngr.menu.service.MngrMenuService;
import com.hybirdapp.sample.mngr.menu.service.MngrMenuVO;

@Service("MngrMenuService")
public class MngrMenuServiceImpl implements MngrMenuService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name = "MngrMenuDAO")
	private MngrMenuDAO mngrMenuDAO;
	
	@Resource(name = "AuthorMngDAO")
	private AuthorMngDAO authorMngDAO;
	

	@Override
	public List<MngrMenuVO> selectMngrMenuList(MngrMenuVO vo) throws Exception {
		
		List<MngrMenuVO> result = null;
		
		try {
			
			result = mngrMenuDAO.selectMngrMenuList(vo);
			
		} catch (Exception e) {
			logger.error("selectMngrMenuList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}

	@Override
	public List<MngrMenuVO> selectMngrMenuTreeList() throws Exception {
		
		List<MngrMenuVO> result = null;
		
		try {
			
			result = mngrMenuDAO.selectMngrMenuTreeList();
			
		} catch (Exception e) {
			logger.error("selectMngrMenuTreeList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}

	@Override
	public void registMngrMenu(MngrMenuVO vo) throws Exception {
		try {
//			AuthorMngMenuVO authorMngMenuVO = null;
			if(vo.getMenuType().equals("I")) {
				mngrMenuDAO.insertMngrMenu(vo);
//				authorMngDAO.insertAuthorMngMenu(authorMngMenuVO);
			} else {
				mngrMenuDAO.updateMngrMenu(vo);
			}
			
		} catch (Exception e) {
			logger.error("registMngrMenu", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.save", null));
		}
	}

	@Override
	public void deleteMngrMenu(MngrMenuVO vo) throws Exception {
		
		try {
			
			mngrMenuDAO.deleteMngrMenu(vo);
			
		} catch (Exception e) {
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.delete", null));
		}
	}

	@Override
	public MngrMenuVO selectMenuDetailList(MngrMenuVO vo) throws Exception {
		
		MngrMenuVO result = new MngrMenuVO();
		
		try {
			
			result = mngrMenuDAO.selectMenuDetailList(vo);
			
		} catch (Exception e) {
			logger.error("selectMenuDetailList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.page", null));
		}
		
		return result;
	}

	@Override
	public MngrMenuVO searchMenuCd(MngrMenuVO vo) throws Exception {

		MngrMenuVO result = new MngrMenuVO();
		
		try {
			
			result = mngrMenuDAO.selectMenuCd(vo);
			
		} catch (Exception e) {
			logger.error("searchMenuCd", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.page", null));
		}
		
		return result;
	}


}
