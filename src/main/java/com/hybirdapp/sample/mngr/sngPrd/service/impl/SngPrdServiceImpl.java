package com.hybirdapp.sample.mngr.sngPrd.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.cmmn.script.service.ScriptService;
import com.hybirdapp.sample.mngr.sngPrd.service.SngPrdService;
import com.hybirdapp.sample.cmmn.DataClass;

@Service("SngPrdService")
public class SngPrdServiceImpl implements SngPrdService {

	/**
	 * Logger available to subclasses
	 */
	protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "SngPrdDAO")
	private SngPrdDAO sngPrdDAO;

	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;

	@Resource(name = "ScriptService")
	private ScriptService scriptService;

	// 단일 상품 목록 건수
	public int searchSngPrdListCnt(DataClass params) throws Exception {
		int result = 0;
		try {
			result = sngPrdDAO.searchSngPrdListCnt(params.getMap());
		}
		catch (Exception e) {
			logger.error("searchSngPrdListCnt", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 단일 상품 목록 조회
	public List searchSngPrdList(DataClass params) throws Exception {
		List result = null;

		try {
			result = sngPrdDAO.searchSngPrdList(params.getMap());
		}
		catch (Exception e) {
			logger.error("searchSngPrdList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 단일 상품 단건 조회
	public HashMap searchSngPrd(DataClass params) throws Exception {
		HashMap result = new HashMap();
		try {
			result = sngPrdDAO.searchSngPrd(params.getMap());
		}
		catch (Exception e) {
			logger.error("searchSngPrd", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 단일 상품 등록
	public int registSngPrd(DataClass params) throws Exception {
		int result = 0;
		try {
			result = sngPrdDAO.registSngPrd(params.getMap());
		}
		catch (Exception e) {
			logger.error("registSngPrd", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.insert", null));
		}
		return result;
	}

	// 단일 상품 수정
	public int modifySngPrd(DataClass params) throws Exception {
		int result = 0;

		try {
			result = sngPrdDAO.modifySngPrd(params.getMap());
		}
		catch (Exception e) {
			logger.error("modifySngPrd", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.update", null));
		}

		return result;
	}

	// 단일 상품 삭제
	public int deleteSngPrd(DataClass params) throws Exception {
		int result = 0;

		try {
			result = sngPrdDAO.deleteSngPrd(params.getMap());
		}
		catch (Exception e) {
			logger.error("deleteSngPrd", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.delete", null));
		}

		return result;
	}

	@Override
	public int isUsedSngPrd(DataClass params) throws Exception {

		int result = 0;

		try {
			result = sngPrdDAO.isUsedSngPrd(params.getMap());
		}
		catch (Exception e) {
			logger.error("deleteSngPrd", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.delete", null));
		}
		return result;
	}
}
