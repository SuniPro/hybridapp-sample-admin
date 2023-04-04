package com.hybirdapp.sample.mngr.grpPrd.service.impl;

import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.cmmn.script.service.ScriptService;
import com.hybirdapp.sample.mngr.grpPrd.service.GrpPrdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service("GrpPrdService")
public class GrpPrdServiceImpl implements GrpPrdService {

	@Resource(name = "GrpPrdDAO")
	private GrpPrdDAO grpPrdDAO;

	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;

	@Resource(name = "ScriptService")
	private ScriptService scriptService;

	// 상품그룹관리 목록 건수
	public int searchGrpPrdListCnt(DataClass params) throws Exception {
		int result = 0;
		try {
			result = grpPrdDAO.searchGrpPrdListCnt(params.getMap());
		}
		catch (Exception e) {
			log.error("searchGrpPrdListCnt", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 상품그룹관리 목록 조회
	public List searchGrpPrdList(DataClass params) throws Exception {
		List result = null;

		try {
			result = grpPrdDAO.searchGrpPrdList(params.getMap());
		}
		catch (Exception e) {
			log.error("searchGrpPrdList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}

	// 상품그룹관리 단건 조회
	public HashMap searchGrpPrd(DataClass params) throws Exception {

		HashMap result;
		try {
			result = grpPrdDAO.searchGrpPrd(params.getMap());
		}
		catch (Exception e) {
			log.error("searchGrpPrd", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 상품그룹관리 수정
	public int modifyGrpPrd(DataClass params) throws Exception {
		int result = 0;
		try {
			String grCode = (!StringUtils.hasText(params.get("gr_code"))) ? grpPrdDAO.makeSertbGrcode() : params.get("gr_code");
			String grLOC = grpPrdDAO.getPlaceCode(params.get("fclt_seq"));
			params.set("gr_loc", grLOC);
			params.set("gr_code", grCode);
			result = grpPrdDAO.upsertGrpPrd(params.getMap());
			grpPrdDAO.upsertGrpPrdDesc(params.getMap()); // 상세설명 upsert
		}
		catch (Exception e) {
			log.error("modifyGrpPrd", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.insert", null));
		}
		return result;
	}

	// 상품그룹관리 삭제
	public int deleteGrpPrd(DataClass params) throws Exception {
		int result = 0;
		try {
			result = grpPrdDAO.deleteGrpPrd(params.getMap());
		}
		catch (Exception e) {
			log.error("deleteGrpPrd", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.delete", null));
		}
		return result;
	}

	@Override
	public int isUsedGrp(DataClass params) throws Exception {
		return this.grpPrdDAO.isUsedGrp(params.getMap());
	}
}
