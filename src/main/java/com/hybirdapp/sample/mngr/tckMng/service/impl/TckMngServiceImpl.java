package com.hybirdapp.sample.mngr.tckMng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.cmmn.script.service.ScriptService;
import com.hybirdapp.sample.mngr.tckMng.service.TckMngService;
import com.hybirdapp.sample.mngr.tckMng.service.TckMngVO;

@Service("TckMngService")
public class TckMngServiceImpl implements TckMngService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "TckMngDAO")
	private TckMngDAO tckMngDAO;
	
	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;
	
	@Resource(name = "ScriptService")
	private ScriptService scriptService;

	// 티켓 분류 조회 건수
	@Override
	public int searchTckMngListCnt(DataClass params) throws Exception {
		int result = 0;
		try {
			result = tckMngDAO.searchTckMngListCnt(params.getMap());
		} catch (Exception e) {
			logger.error("searchTckMngListCnt", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 티켓 분류 조회 목록
	@Override
	public List searchTckMngList(DataClass params) throws Exception {
		List result = null;

		try {
			result = tckMngDAO.searchTckMngList(params.getMap());
		} catch (Exception e) {
			logger.error("searchTckMngList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}

	// 티켓 분류 조회 목록
	@Override
	public List searchTckList(DataClass params) throws Exception {
		List result = null;

		try {
			result = tckMngDAO.searchTckList(params.getMap());
		} catch (Exception e) {
			logger.error("searchTckList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}

	// 티켓 분류 정보 수정
	@Override
	public int modifyTckMng(TckMngVO vo) throws Exception {
		int result = 0;
		try {
			result = tckMngDAO.modifyTckMng(vo);
			
		} catch (Exception e) {
			logger.error("modifyTckMng", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 티켓 분류 정보 등록
	@Override
	public int registTckMng(DataClass params) throws Exception {
		int result = 0;
		try {
			result = tckMngDAO.registTckMng(params.getMap());
		} catch (Exception e) {
			logger.error("registTckMng", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 티켓 분류 정보 삭제
	@Override
	public int deleteTckMng(DataClass params) throws Exception {
		int result = 0;
		try {
			result = tckMngDAO.deleteTckMng(params.getMap());
		} catch (Exception e) {
			logger.error("deleteTckMng", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}
	
	
}
