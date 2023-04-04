package com.hybirdapp.sample.mngr.conSls.service.impl;

import java.util.HashMap;
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
import com.hybirdapp.sample.mngr.conSls.service.ConSlsService;

@Service("ConSlsService")
public class ConSlsServiceImpl implements ConSlsService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "ConSlsDAO")
	private ConSlsDAO conSlsDAO;
	
	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;
	
	@Resource(name = "ScriptService")
	private ScriptService scriptService;

	// 콘도 판매 조회 건수
	@Override
	public int searchConSlsListCnt(DataClass params) throws Exception {
		int result = 0;
		try {
			result = conSlsDAO.searchConSlsListCnt(params.getMap());
		} catch (Exception e) {
			logger.error("searchConSlsListCnt", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}
	
	// 티켓 판매 조회 목록
	@Override
	public List searchConSlsList(DataClass params) throws Exception {
		List result = null;

		try {
			result = conSlsDAO.searchConSlsList(params.getMap());
		} catch (Exception e) {
			logger.error("searchConSlsList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}
	
	// 티켓 판매 정보 조회(팝업)
	@Override
	public List searchConSls(DataClass params) throws Exception {
		List result = null;

		try {
			result = conSlsDAO.searchConSls(params.getMap());
		} catch (Exception e) {
			logger.error("searchConSls", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}
		
	// 티켓 판매 총 금액 조회
	@Override
	public int searchConSlsAmt(DataClass params) throws Exception {
		int amount = 0;
		HashMap result = new HashMap();
		try {
			amount = conSlsDAO.searchConSlsListAmt(params.getMap());
		} catch (Exception e) {
			logger.error("searchConSls", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return amount;
	}

	/**
	 * 당일환불금액 조회
	 *
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public int searchCurrentDayRefundAmt(DataClass params) throws Exception {
		int amount = 0;
		try {
			amount = conSlsDAO.searchCurrentDayRefundAmt(params.getMap());
		} catch (Exception e) {
			logger.error("searchCurrentDayRefundAmt", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return amount;
	}

}
