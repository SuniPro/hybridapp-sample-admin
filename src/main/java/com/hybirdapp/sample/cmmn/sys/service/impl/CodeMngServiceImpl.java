package com.hybirdapp.sample.cmmn.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.cmmn.sys.service.ClCdVO;
import com.hybirdapp.sample.cmmn.sys.service.ClGrpVO;
import com.hybirdapp.sample.cmmn.sys.service.CodeMngService;

@Service("CodeMngService")
public class CodeMngServiceImpl implements CodeMngService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name = "CodeMngDAO")
	private CodeMngDAO codeMngDAO;

	@Override
	public List<ClGrpVO> searchClGrpList(ClGrpVO vo) throws Exception {
		List<ClGrpVO> resultList = null;
		
		try {
			
			resultList = codeMngDAO.searchClGrpList(vo);
			vo.setTotPageCnt(codeMngDAO.searchClGrpCount());
			
		}
		catch (Exception ex) {
			logger.error("searchClGrpList", ex);
			// 조회중 오류가 발생했습니다.
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return resultList;
	}

	@Override
	public void registClGrp(ClGrpVO vo) throws Exception {
		
		int result = 0;

		try {		
				result = codeMngDAO.registClgrp(vo);				
		}
		catch (Exception ex) {

			logger.error("registClgrp", ex);
			// 저장중 오류발생
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.save", null));
		}
		
		if(result< 1) {
			// 결과가 1보다 작을 경우 이미 사용중
			throw new CmmnUserException(MessageManager.getIGMessage("code.fail.cmmnId.use", null));
		}
		
	}
	
	@Override
	public void modifyClGrp(ClGrpVO vo) throws Exception {
		
		try {		
			codeMngDAO.modifyClGrp(vo);				
		}
		catch (Exception ex) {
			
			logger.error("modifyClGrp", ex);
			// 수정중 오류발생
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.update", null));
		}
		
	}

	@Override
	public ClGrpVO searchClGrp(ClGrpVO vo) throws Exception {
		ClGrpVO clGrpVO = null;
		try{
			
			clGrpVO = codeMngDAO.searchClGrp(vo);
			
		}
		catch(Exception ex) {

			logger.error("searchClGrp", ex);
			// 조회중 오류가 발생했습니다.
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return clGrpVO;
	}

	@Override
	public List<ClCdVO> searchClCdList(ClCdVO vo) throws Exception {
		List<ClCdVO> resultList = null;
		
		try {
			
			resultList = codeMngDAO.searchClCdList(vo);
			vo.setTotPageCnt(codeMngDAO.searchClCdCount());
			
		}
		catch (Exception ex) {
			logger.error("searchCodeDtlMngList", ex);
			// 조회중 오류가 발생했습니다.
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return resultList;
	}

	@Override
	public void registClCd(ClCdVO vo) throws Exception {
		
		int result = 0;

		try {
			
				result = codeMngDAO.registClCd(vo);
				
		}catch (Exception ex) {

			logger.error("registCmmnCode", ex);
			// 저장중 오류발생
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.save", null));
		}
		
		if(result< 1) {
			// 결과가 1보다 작을 경우 이미 사용중
			throw new CmmnUserException(MessageManager.getIGMessage("code.fail.dtlId.use", null));
		}
	}
	
	@Override
	public void modifyClCd(ClCdVO vo) throws Exception {
		try {
			
			codeMngDAO.modifyClCd(vo);
			
		}catch (Exception ex) {
			
			logger.error("modifyCmmnCode", ex);
			// 저장중 오류발생
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.update", null));
		}
		
//		if(result< 1) {
//			// 결과가 1보다 작을 경우 이미 사용중
//			throw new CmmnUserException(MessageManager.getIGMessage("code.fail.dtlId.use", null));
//		}
	}

	@Override
	public ClCdVO searchClCd(ClCdVO vo) throws Exception {
		ClCdVO clCdVO = null;
		
		try {
			
			clCdVO = codeMngDAO.searchClCd(vo);
			
		}
		catch(Exception ex) {
			logger.error("searchCodeDtlMng", ex);
			// 조회중 오류가 발생했습니다.
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return clCdVO;
	}

	@Override
	@Transactional(transactionManager = "txManager", rollbackFor = CmmnUserException.class)
	public void deleteClGrp(ClCdVO vo) throws Exception {
		try {
			
			//공통코드 모두다 삭제
			codeMngDAO.deleteClCd(vo);
			//분류코드 삭제
			codeMngDAO.deleteClGrp(vo);
			
		}catch (Exception ex) {
			
			logger.error("deleteClGrp", ex);
			// 저장중 오류발생
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.delete", null));
		}
	}
	
	@Override
	public void deleteClCd(ClCdVO vo) throws Exception {
		try {
			
			codeMngDAO.deleteClCd(vo);
			
		}catch (Exception ex) {
			
			logger.error("deleteClCd", ex);
			// 저장중 오류발생
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.delete", null));
		}
	}

	
	@Override
	public List selectFcltList(DataClass params) throws Exception {
		List resultList = null;

		try {
			resultList = codeMngDAO.selectFcltList(params.getMap());
//			resultList = commonDAO.listData("prdmng.selectFcltList", params.getMap());
		} catch (Exception e) {
			logger.error("searchNoticeList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return resultList;
	}

	/**
	 * 상품그룹목록 조회
	 * @param DataClass
	 * @throws Exception
	 */
	@Override
	public List selectGrpList(DataClass params) throws Exception {
		List resultList = null;

		try {
			resultList = codeMngDAO.selectGrpList(params.getMap());
			
		} catch (Exception e) {
			logger.error("searchNoticeList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return resultList;
	}
}
