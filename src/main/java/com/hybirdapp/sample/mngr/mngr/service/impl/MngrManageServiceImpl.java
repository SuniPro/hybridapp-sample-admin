package com.hybirdapp.sample.mngr.mngr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.hybirdapp.sample.mngr.mngr.service.MngrManageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.mngr.mngr.service.MngrManageVO;

@Service("MngrManageService")
public class MngrManageServiceImpl implements MngrManageService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "MngrManageDAO")
	private MngrManageDAO mngrManageDAO;

	
	@Override
	public List<MngrManageVO> searchMngrManageList(MngrManageVO vo) throws Exception {
		List<MngrManageVO> result = null;
		
		try {

			result = mngrManageDAO.selectMngrManageList(vo);
			vo.setTotPageCnt(mngrManageDAO.selectMngrManageTotalCnt(vo));
			
			
		} catch (Exception e) {
			logger.error("searchMberManageList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public MngrManageVO searchMngrManage(MngrManageVO vo) throws Exception {
		MngrManageVO result = null;
		
		
		result = mngrManageDAO.selectMngrManage(vo);
		
		return result;
	}

	@Override
	public List<MngrManageVO> searchMngrManageGradList(MngrManageVO vo) throws Exception {
		return mngrManageDAO.selectMngrManageGradList(vo);
	}

	@Override
	public int searchMngrIdCo(MngrManageVO vo) throws Exception {
		return mngrManageDAO.selectMngrIdCo(vo);
	}
	
	@Override
	public void modifyMngrManage(MngrManageVO vo) throws Exception {
		
		int retCnt =  mngrManageDAO.updateMngrManage(vo);
		
		if (retCnt > 0) {
			mngrManageDAO.updateMngrManageAuth(vo);
		}
	}
	
	@Override
	public int searchMngrPwChk(MngrManageVO vo) throws Exception {

		int result = 0;
		
		try {
			result = mngrManageDAO.searchMngrPwChk(vo);
		
		} catch (Exception e) {
			logger.error("selectUserInfo", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}

	@Override
	public void registMngrManage(MngrManageVO vo) throws Exception {
		
		int retCnt = mngrManageDAO.insertMngrManage(vo);

		if (retCnt > 0) {
			mngrManageDAO.insertMngrManageAuth(vo);
		}
	}

	@Override
	public void modifyMngrManagePwd(MngrManageVO vo) throws Exception {
		try {
			mngrManageDAO.updateMngrManagePwd(vo);
		}catch (Exception ex) {
			
			logger.error("modifyMngrManagePwd", ex);
			// 저장중 오류발생
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.update", null));
		}
	}

	@Override
	public void removeMngrManage(MngrManageVO vo) throws Exception {
		mngrManageDAO.deleteMngrManage(vo);
	}
}
