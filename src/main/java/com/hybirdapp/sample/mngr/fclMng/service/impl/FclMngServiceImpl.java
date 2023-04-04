package com.hybirdapp.sample.mngr.fclMng.service.impl;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hybirdapp.sample.mngr.fclMng.service.ContentMapGenerator;
import com.hybirdapp.sample.mngr.fclMng.service.ContentsUtils;
import com.hybirdapp.sample.cmmn.CmmnConstant;
import com.hybirdapp.sample.cmmn.ex.CmmnMngrException;
import com.hybirdapp.sample.cmmn.script.service.impl.ScriptDAO;
import com.hybirdapp.sample.mngr.fclMng.service.FclMngService;
import com.hybirdapp.sample.mngr.fclMng.service.ResultCode;
import com.plnc.mngr.fclMng.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.cmmn.script.service.ScriptService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Slf4j
@Service("FclMngService")
@Transactional(transactionManager = "txManager", readOnly = false)
public class FclMngServiceImpl implements FclMngService {
	
	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "FclMngDAO")
	private FclMngDAO fclMngDAO;

	@Resource(name = "ScriptDAO")
	private ScriptDAO scriptDAO;

	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;

	@Resource(name = "ScriptService")
	private ScriptService scriptService;

	private final String MAIN_BOARD_SE = "EQIP";
	private final String SUB_BOARD_SE = "EQIP_DTL";

	// 시설관리 목록 건수
	public int searchFclMngListCnt(DataClass params) throws Exception {
		int result = 0;
		try {
			result = fclMngDAO.searchFclMngListCnt(params.getMap());
		} catch (Exception e) {
			logger.error("searchFclMngListCnt", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}
	
	// 시설 관리 목록 조회 
	public List searchFclMngList(DataClass params) throws Exception {
		List result = null;

		try {
			result = fclMngDAO.searchFclMngList(params.getMap());
		} catch (Exception e) {
			logger.error("searchFclMngList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}
	
	// 시설 관리 단건 조회
	public HashMap searchFclMng(DataClass params) throws Exception {
		HashMap result = new HashMap();
		try {
			result = fclMngDAO.searchFclMng(params.getMap());
		} catch (Exception e) {
			logger.error("searchFclMng", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}
	
	// 시설 관리 등록
	public int registFclMng(DataClass params) throws Exception{
		int result = 0;
		try {
			result = fclMngDAO.registFclMng(params);
			
			/* 첨부파일 - 로직확인 필요(Notice) */
//			if (listMap != null && listMap.size() > 0) {
//				for (Map<String, Object> map : listMap) {
//					AttachVO attachVO = new AttachVO();
//					attachVO.setBoardSe("NOTICE");
//					attachVO.setBoardSn(Integer.parseInt(vo.getNoticeSn()));
//					attachVO.setOrgnFileNm((String)map.get("orgnFileNm"));
//					attachVO.setNewFileNm((String)map.get("newFileNm"));
//					attachVO.setSavePath((String)map.get("savePath"));
//					attachVO.setExpsrOrdr((int)map.get("expsrOrdr"));
//					attachVO.setUrl((String)map.get("url"));
//					attachVO.setIsrtIp(vo.getIsrtIp());
//					attachVO.setIsrtId(vo.getIsrtId());
//					scriptService.attachFileSave(attachVO);
//				}
//			}
			/* 첨부파일 END */
		} catch (Exception e) {
			logger.error("registFclMng", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.insert", null));
		}
		return result;
	}

	@Override
	@Transactional(transactionManager = "txManager", rollbackFor = CmmnMngrException.class)
	public String registFclMng(MultipartHttpServletRequest mtfRequest, HttpServletRequest request) throws Exception {
		/* 0. 공통으로 사용할 변수 */
		final String mngrId = ContentsUtils.getMemberAttribute(request, "mngrId");
		final String remoteIp = ContentsUtils.getClientIP(request);
		/* 1. 요청 파라미터 유효성 체크 */
		String code = ContentsUtils.validateFcltRegist(request);
		if (!code.equals(ResultCode.SYS_0000.getCode())) {
			return code;
		}
		/* 2. 업로드 파일 유효성 체크 */
//		code = ContentsUtils.validateFileRegist(mtfRequest);
//		if (!code.equals(ResultCode.SYS_0000.getCode())) {
//			return code;
//		}
		/* 3. 선행 추출할 데이터 */
		/* 3.1 시설 등록 정보 */
		final Map<String, String> params = ContentsUtils.setRegistParams(request, ContentsUtils.REGIST_PARAMS);
		log.debug("params : {}", params);
		/* 3. SERTB_FCLT 테이블에 저장 */
		final String mainSequence = fclMngDAO.getPlaceSequence();
		/* 3.1 썸네일이미지 저장 */
		saveThumbImage(mtfRequest, request, mainSequence);
		/* 3.2 현장대기 이미지 저장 */
		saveWaitingImage(mtfRequest, request, mainSequence);
		/* 3.3 슬라이드 이미지 저장 */
		saveSlideImage(mtfRequest, request, mainSequence);
		/* 3.4 시설정보 등록 : SERTB_FCLT */
		Map<String, String> insertFcltMap = ContentMapGenerator.makeFcltInsert(params, mngrId, remoteIp);
		insertFcltMap.put("fcltSequence", mainSequence);
		log.debug("insertFcltMap : {}", insertFcltMap);
		int order = 999;
		if ("ACTIVITY".equals(insertFcltMap.get("fcltClass"))) {
			order = fclMngDAO.getFcltOrder();
		}
		insertFcltMap.put("ordr", Integer.toString(order));
		fclMngDAO.insertFclt(insertFcltMap);
		/* 4. P_PLACE 테이블에 저장 */
		Map<String, String> insertPlaceMap = ContentMapGenerator.makePlaceInsert(params, mngrId, remoteIp);
//		String placeSequence = fclMngDAO.getPlaceSequence();
		final String placeSequence = mainSequence;
		insertPlaceMap.put("plCode", placeSequence);
		log.debug("insertFcltMap : {}", insertFcltMap);
		fclMngDAO.insertPlace(insertPlaceMap);

		/* 5. 상세메뉴 저장 */
		insertFcltDtl(mtfRequest, request, mainSequence, params.get("descIndex"));

		/* 6. 대기열 테이블에 저장 */
		fclMngDAO.insertFcltWaiting(mainSequence);

		return ResultCode.SYS_0000.getCode();
	}

	/* P_PLACE 테이블에 저장 */
	private void insertFcltDtl(MultipartHttpServletRequest mtfRequest, HttpServletRequest request, final String mainSequence, final String menuIndex) throws Exception {
		if (mtfRequest == null || request == null || mainSequence == null || menuIndex == null) {
			return;
		}
		/* 1 상세메뉴 정보 */
		final String[] indexArray = menuIndex.split(",");
		int dtlOrder = 1;
		for (String index : indexArray) {
			String subSequence = fclMngDAO.getFcltDtlSequence();
			Map<String, String> insertFcltDtlMap = new HashMap<>();
			insertFcltDtlMap.put("fcltDtlSeq", subSequence);
			insertFcltDtlMap.put("fcltSeq", mainSequence);
			insertFcltDtlMap.put("dtlMenu", request.getParameter("inMenu" + index));
			insertFcltDtlMap.put("dtlOrdr", Integer.toString(dtlOrder));
			insertFcltDtlMap.put("isrtId", ContentsUtils.getMemberAttribute(request, "mngrId"));
			insertFcltDtlMap.put("isrtIp", ContentsUtils.getClientIP(request));
			/* 업로드 이미지가 있는지 체크 및 저장 */
			String imgYn = "N";
			String description = request.getParameter("inDesc" + index);
			MultipartFile menuFile = ContentsUtils.getSingleUploadFile(mtfRequest, ContentsUtils.MENU_IMAGE + index);
			if (menuFile != null) {
				Map<String, Object> menuMap = cmmnFileSaveService.saveImageFile(menuFile, SUB_BOARD_SE, 1);
				if (menuMap != null) {
					menuMap.put("boardSe", SUB_BOARD_SE);
					menuMap.put("boardSn", subSequence);
					menuMap.put("fileSn", 1);
					menuMap.put("expsrOrdr", 1);
					menuMap.put("isrtId", ContentsUtils.getMemberAttribute(request, "mngrId"));
					menuMap.put("isrtIp", ContentsUtils.getClientIP(request));
					log.debug("menuMap : {}", menuMap);
					scriptDAO.insertImageFile(menuMap);

					imgYn = "Y";
					description = "<img src=\"" + menuMap.get(CmmnConstant.URL) + "/" + menuMap.get(CmmnConstant.NEW_FILE_NM) + "\" width=\"100%\">" + "\n" + description;
				}
			}
			insertFcltDtlMap.put("imgYn", imgYn);
			insertFcltDtlMap.put("dtlDesc", description);
			fclMngDAO.insertFcltDtl(insertFcltDtlMap);
			dtlOrder++;
		}
	}

	/* 현장대기 이미지 저장 */
	private boolean saveMenuImage(MultipartHttpServletRequest mtfRequest, HttpServletRequest request, String subSequence) throws Exception {
		MultipartFile menuFile = ContentsUtils.getSingleUploadFile(mtfRequest, ContentsUtils.WAITING_IMAGE);
		if (menuFile == null) {
			return false;
		}
		Map<String, Object> menuMap = cmmnFileSaveService.saveImageFile(menuFile, MAIN_BOARD_SE, 2);
		if (menuMap == null) {
			return false;
		}
		menuMap.put("boardSe", SUB_BOARD_SE);
		menuMap.put("boardSn", subSequence);
		menuMap.put("fileSn", 1);
		menuMap.put("expsrOrdr", 1);
		menuMap.put("isrtId", ContentsUtils.getMemberAttribute(request, "mngrId"));
		menuMap.put("isrtIp", ContentsUtils.getClientIP(request));
		log.debug("waitingMap : {}", menuMap);
		scriptDAO.insertImageFile(menuMap);

		return true;
	}

	/* 썸네일 이미지 저장 */
	private void saveThumbImage(MultipartHttpServletRequest mtfRequest, HttpServletRequest request, String mainSequence) throws Exception {
		MultipartFile thumbFile = ContentsUtils.getSingleUploadFile(mtfRequest, ContentsUtils.THUMBNAIL_IMAGE);
		if (thumbFile == null) {
			return;
		}
		Map<String, Object> thumbMap = cmmnFileSaveService.saveImageFile(thumbFile, MAIN_BOARD_SE, 1);
		if (thumbMap == null) {
			return;
		}
		thumbMap.put("boardSe", MAIN_BOARD_SE);
		thumbMap.put("boardSn", mainSequence);
		thumbMap.put("fileSn", 1);
		thumbMap.put("expsrOrdr", 1);
		thumbMap.put("isrtId", ContentsUtils.getMemberAttribute(request, "mngrId"));
		thumbMap.put("isrtIp", ContentsUtils.getClientIP(request));
		log.debug("thumbMap : {}", thumbMap);
		scriptDAO.insertImageFile(thumbMap);
	}

	/* 현장대기 이미지 저장 */
	private void saveWaitingImage(MultipartHttpServletRequest mtfRequest, HttpServletRequest request, String mainSequence) throws Exception {
		MultipartFile waitingFile = ContentsUtils.getSingleUploadFile(mtfRequest, ContentsUtils.WAITING_IMAGE);
		if (waitingFile == null) {
			return;
		}
		Map<String, Object> waitingMap = cmmnFileSaveService.saveImageFile(waitingFile, MAIN_BOARD_SE, 2);
		if (waitingMap == null) {
			return;
		}
		waitingMap.put("boardSe", MAIN_BOARD_SE);
		waitingMap.put("boardSn", mainSequence);
		waitingMap.put("fileSn", 2);
		waitingMap.put("expsrOrdr", 1);
		waitingMap.put("isrtId", ContentsUtils.getMemberAttribute(request, "mngrId"));
		waitingMap.put("isrtIp", ContentsUtils.getClientIP(request));
		log.debug("waitingMap : {}", waitingMap);
		scriptDAO.insertImageFile(waitingMap);
	}

	/* 슬라이드 이미지 저장 */
	private void saveSlideImage(MultipartHttpServletRequest mtfRequest, HttpServletRequest request, String mainSequence) throws Exception {
		List<MultipartFile> fileList = ContentsUtils.getMultiUploadFile(mtfRequest, ContentsUtils.SLIDE_IMAGE);
		if (fileList == null || fileList.size() == 0) {
			return;
		}
		int fileSnIndex = 3;
		int orderIndex = 1;
		for (MultipartFile multiFile : fileList) {
			Map<String, Object> slideMap = cmmnFileSaveService.saveImageFile(multiFile, MAIN_BOARD_SE, orderIndex);
			if (slideMap == null) {
				continue;
			}
			slideMap.put("boardSe", MAIN_BOARD_SE);
			slideMap.put("boardSn", mainSequence);
			slideMap.put("fileSn", fileSnIndex);
			slideMap.put("expsrOrdr", orderIndex);
			slideMap.put("isrtId", ContentsUtils.getMemberAttribute(request, "mngrId"));
			slideMap.put("isrtIp", ContentsUtils.getClientIP(request));
			log.debug("waitingMap : {}", slideMap);
			scriptDAO.insertImageFile(slideMap);

			fileSnIndex++;
			orderIndex++;
		}
	}

	@Override
	public void viewFcltForModify(HttpServletRequest request, Model model) throws Exception {
		final String fcltSequence = request.getParameter("fcltSeq");
		/* 1. 시설조회 */
		Map<String, Object> selectFcltMap = fclMngDAO.selectFclMng(fcltSequence);
		if (selectFcltMap == null) {
			model.addAttribute("code", ResultCode.SYS_2000.getCode());
			model.addAttribute("msg", ResultCode.getMessage(ResultCode.SYS_2000.getCode()));
			return;
		}
		/* 2. 시설 이미지 조회 */
		/* 2.1 썸네일이미지 */
		Map<String, Object> params = new HashMap<>();
		params.put("boardSe", MAIN_BOARD_SE);
		params.put("boardSn", fcltSequence);
		params.put("fileSn", 1);
		Map<String, Object> thumbImageMap = scriptDAO.selectSingleFile(params);
//		if (thumbImageMap == null) {
//			model.addAttribute("code", ResultCode.SYS_2001.getCode());
//			model.addAttribute("msg", ResultCode.getMessage(ResultCode.SYS_2001.getCode()));
//			return;
//		}
		/* 2.2 현장대기시간이미지 */
		params.put("fileSn", 2);
		Map<String, Object> waitingImageMap = scriptDAO.selectSingleFile(params);
//		if (waitingImageMap == null) {
//			model.addAttribute("code", ResultCode.SYS_2002.getCode());
//			model.addAttribute("msg", ResultCode.getMessage(ResultCode.SYS_2002.getCode()));
//			return;
//		}
		/* 2.3 슬라이드이미지 */
		List<Map<String, Object>> slideImageMap = scriptDAO.selectMultiFile(params);
//		if (slideImageMap == null || slideImageMap.size() == 0) {
//			model.addAttribute("code", ResultCode.SYS_2003.getCode());
//			model.addAttribute("msg", ResultCode.getMessage(ResultCode.SYS_2003.getCode()));
//			return;
//		}
		/* 3. 상세메뉴 */
		List<Map<String, String>> listFcltDtl = fclMngDAO.listFcltDtl(fcltSequence);
		if (listFcltDtl == null || listFcltDtl.size() == 0) {
			model.addAttribute("code", ResultCode.SYS_2004.getCode());
			model.addAttribute("msg", ResultCode.getMessage(ResultCode.SYS_2004.getCode()));
			return;
		}
		for(Map<String, String> dtlMap : listFcltDtl) {
			Map<String, Object> subParams = new HashMap<>();
			subParams.put("boardSe", SUB_BOARD_SE);
			subParams.put("boardSn", dtlMap.get("FCLT_DTL_SEQ"));
			subParams.put("fileSn", 1);
			Map<String, Object> menuImageMap = scriptDAO.selectSingleFile(subParams);
			if (menuImageMap == null) {
				dtlMap.put("BOARD_SE", null);
				dtlMap.put("BOARD_SN", null);
				dtlMap.put("FILE_SN", null);
				dtlMap.put("ORGN_FILE_NM", null);
				dtlMap.put("NEW_FILE_NM", null);
				dtlMap.put("SAVE_PATH", null);
				dtlMap.put("URL", null);
				dtlMap.put("USE_AT", null);
			} else {
				dtlMap.put("BOARD_SE", (String) menuImageMap.get("BOARD_SE"));
				dtlMap.put("BOARD_SN", (String) menuImageMap.get("BOARD_SN"));
				dtlMap.put("FILE_SN", (String) menuImageMap.get("FILE_SN"));
				dtlMap.put("ORGN_FILE_NM", (String) menuImageMap.get("ORGN_FILE_NM"));
				dtlMap.put("NEW_FILE_NM", (String) menuImageMap.get("NEW_FILE_NM"));
				dtlMap.put("SAVE_PATH", (String) menuImageMap.get("SAVE_PATH"));
				dtlMap.put("URL", (String) menuImageMap.get("URL"));
				dtlMap.put("USE_AT", (String) menuImageMap.get("USE_AT"));
			}
		}
		/* 4. 데이터 전달 */
		model.addAttribute("code", ResultCode.SYS_0000.getCode());
		model.addAttribute("msg", ResultCode.getMessage(ResultCode.SYS_0000.getCode()));
		log.debug("selectFcltMap : {}", selectFcltMap);
		log.debug("thumbImageMap : {}", thumbImageMap);
		log.debug("waitingImageMap : {}", waitingImageMap);
		log.debug("slideImageMap : {}", slideImageMap);
		log.debug("listFcltDtl : {}", listFcltDtl);
		model.addAttribute("fcltInfo", selectFcltMap);
		model.addAttribute("thumbImage", thumbImageMap);
		model.addAttribute("waitingImage", waitingImageMap);
		model.addAttribute("listSlideImage", slideImageMap);
		model.addAttribute("listFcltDtl", listFcltDtl);
	}

	@Override
	public void viewFcltForOrder(HttpServletRequest request, Model model) throws Exception {
		model.addAttribute("fcltList", fclMngDAO.listFcltOrder());
	}

	@Override
	@Transactional(transactionManager = "txManager", rollbackFor = CmmnMngrException.class)
	public void updateFcltOrder(HttpServletRequest request, Model model) throws Exception {
		model.addAttribute("code", ResultCode.SYS_0000.getCode());
		model.addAttribute("msg", ResultCode.getMessage(ResultCode.SYS_0000.getCode()));
		request.getParameterNames();
		Enumeration e = request.getParameterNames();
		while ( e.hasMoreElements() ) {
			String name = (String) e.nextElement();
			log.debug("request.getParameterNames() : {}", name);
		}

		String fcltCount = request.getParameter("fcltCount");
		log.debug("fcltCount : {}", fcltCount);
		if (fcltCount == null || fcltCount.trim().length() == 0) {
			return;
		}
		int paramCount = Integer.parseInt(fcltCount);
		for (int i = 1; i <= paramCount; i++) {
			String fcltSeq = request.getParameter("fcltSeq" + i);
			log.debug("fcltSeq : {}", fcltSeq);
			String order = request.getParameter("order" + i);
			log.debug("order : {}", order);
			if (fcltSeq == null || order == null) {
				continue;
			}
			Map<String, String> params = new HashMap<>();
			params.put("fcltSeq", fcltSeq);
			params.put("ordr", order);
			fclMngDAO.updateFcltOrder(params);
		}
	}

	@Override
	@Transactional(transactionManager = "txManager", rollbackFor = CmmnMngrException.class)
	public String modifyFclMng(MultipartHttpServletRequest mtfRequest, HttpServletRequest request) throws Exception {
		/* 0. 공통으로 사용할 변수 */
		final String mngrId = ContentsUtils.getMemberAttribute(request, "mngrId");
		final String remoteIp = ContentsUtils.getClientIP(request);
		/* 1. 요청 파라미터 유효성 체크 */
		String code = ContentsUtils.validateFcltModify(request);
		if (!code.equals(ResultCode.SYS_0000.getCode())) {
			return code;
		}
		/* 2. 업로드 파일 유효성 체크 */
//		code = ContentsUtils.validateFileRegist(mtfRequest);
//		if (!code.equals(ResultCode.SYS_0000.getCode())) {
//			return code;
//		}
		/* 3. 선행 추출할 데이터 */
		/* 3.1 시설 등록 정보 */
		final Map<String, String> params = ContentsUtils.setRegistParams(request, ContentsUtils.MODIFY_PARAMS);
		log.debug("params : {}", params);
		/* 3. SERTB_FCLT 테이블에 저장 */
		final String mainSequence = params.get("fcltSeq");
		/* 3.1 썸네일이미지 저장 */
		modifyThumbImage(mtfRequest, request, mainSequence);
		/* 3.2 현장대기 이미지 저장 */
		modifyWaitingImage(mtfRequest, request, mainSequence);
		/* 3.3 슬라이드 이미지 저장 */
		modifySlideImage(mtfRequest, request, mainSequence);
		/* 3.4 시설정보 등록 : SERTB_FCLT */
		Map<String, String> updateFcltMap = ContentMapGenerator.makeFcltUpdate(params, mngrId, remoteIp);
		log.debug("updateFcltMap : {}", updateFcltMap);
		fclMngDAO.updateFclt(updateFcltMap);
		/* 4. P_PLACE 테이블에 저장 */
		Map<String, Object> selectFcltMap = fclMngDAO.selectFclMng(mainSequence);
		if (selectFcltMap != null && selectFcltMap.get("CD_CODE") != null) {
			Map<String, String> updatePlaceMap = ContentMapGenerator.makePlaceUpdate(params, mngrId, remoteIp);
			updatePlaceMap.put("plOutletCd", (String) selectFcltMap.get("CD_CODE"));
			log.debug("updatePlaceMap : {}", updatePlaceMap);
			fclMngDAO.updatePlace(updatePlaceMap);
		}
		/* 5. 상세메뉴 저장 */
		updateFcltDtl(mtfRequest, request, mainSequence, params.get("descIndex"), params.get("deleteMenuIndex"));

		return ResultCode.SYS_0000.getCode();
	}

	/* DTL 테이블 업데이트 */
	private void updateFcltDtl(MultipartHttpServletRequest mtfRequest, HttpServletRequest request, final String mainSequence, final String menuIndex, final String deleteMenuIndex) throws Exception {
		if (mtfRequest == null || request == null || mainSequence == null || menuIndex == null) {
			return;
		}
		/* 상세메뉴 목록 조회 */
		List<Map<String, String>> listFcltDtl = fclMngDAO.listTotalFcltDtl(mainSequence);
		/* 0. 삭제 상세메뉴 */
		if (deleteMenuIndex != null && deleteMenuIndex.trim().length() > 0) {
			final String[] deleteArray = deleteMenuIndex.split(",");
			for (String sequence : deleteArray) {
				if (sequence.trim().length() > 0) {
					Map<String, String> deleteFcltDtlMap = new HashMap<>();
					deleteFcltDtlMap.put("fcltDtlSeq", sequence.trim());
					deleteFcltDtlMap.put("updtId", ContentsUtils.getMemberAttribute(request, "mngrId"));
					deleteFcltDtlMap.put("updtIp", ContentsUtils.getClientIP(request));
					fclMngDAO.deleteFcltDtl(deleteFcltDtlMap);
				}
			}
		}

		/* 1 상세메뉴 정보 */

		final String[] indexArray = menuIndex.split(",");
		int dtlOrder = 1;
		if (listFcltDtl != null && listFcltDtl.size() > 0) {
			dtlOrder = dtlOrder + listFcltDtl.size();
		}
		for (String index : indexArray) {
			if (index.trim().length() == 0) {
				continue;
			}
			String subSequence = request.getParameter("fcltDtlSeq" + index);
			log.debug("subSequence : {}", subSequence);
			/* 신규 등록 */
			if (subSequence == null || subSequence.trim().length() == 0) {
				subSequence = fclMngDAO.getFcltDtlSequence();
				Map<String, String> insertFcltDtlMap = new HashMap<>();
				insertFcltDtlMap.put("fcltDtlSeq", subSequence);
				insertFcltDtlMap.put("fcltSeq", mainSequence);
				insertFcltDtlMap.put("dtlMenu", request.getParameter("inMenu" + index));
				insertFcltDtlMap.put("dtlOrdr", Integer.toString(dtlOrder));
				insertFcltDtlMap.put("isrtId", ContentsUtils.getMemberAttribute(request, "mngrId"));
				insertFcltDtlMap.put("isrtIp", ContentsUtils.getClientIP(request));
				/* 업로드 이미지가 있는지 체크 및 저장 */
				String imgYn = "N";
				String description = request.getParameter("inDesc" + index);
				MultipartFile menuFile = ContentsUtils.getSingleUploadFile(mtfRequest, ContentsUtils.MENU_IMAGE + index);
				if (menuFile != null) {
					Map<String, Object> menuMap = cmmnFileSaveService.saveImageFile(menuFile, SUB_BOARD_SE, 1);
					if (menuMap != null) {
						menuMap.put("boardSe", SUB_BOARD_SE);
						menuMap.put("boardSn", subSequence);
						menuMap.put("fileSn", 1);
						menuMap.put("expsrOrdr", 1);
						menuMap.put("isrtId", ContentsUtils.getMemberAttribute(request, "mngrId"));
						menuMap.put("isrtIp", ContentsUtils.getClientIP(request));
						log.debug("menuMap : {}", menuMap);
						scriptDAO.insertImageFile(menuMap);

						imgYn = "Y";
						description = "<img src=\"" + menuMap.get(CmmnConstant.URL) + "/" + menuMap.get(CmmnConstant.NEW_FILE_NM) + "\" width=\"100%\">" + "\n" + description;
					}
				}
				insertFcltDtlMap.put("imgYn", imgYn);
				insertFcltDtlMap.put("dtlDesc", description);
				fclMngDAO.insertFcltDtl(insertFcltDtlMap);
				dtlOrder++;
			} else {
				Map<String, String> dtlMap = null;
				for (Map<String, String> map : listFcltDtl) {
					log.debug("dtlMap : {}", map.get("FCLT_DTL_SEQ"));
					if (subSequence.trim().equals(map.get("FCLT_DTL_SEQ").trim())) {
						dtlMap = map;
						break;
					}
				}
				log.debug("dtlMap : {}", dtlMap);
				if (dtlMap == null) {
					continue;
				}
				String description = request.getParameter("inDesc" + index);
				MultipartFile menuFile = ContentsUtils.getSingleUploadFile(mtfRequest, ContentsUtils.MENU_IMAGE + index);
				String imageYn = dtlMap.get("IMG_YN");
				if ("Y".equals(dtlMap.get("IMG_YN")) && menuFile != null) {
					imageYn = "Y";
					Map<String, Object> params = new HashMap<>();
					params.put("boardSe", SUB_BOARD_SE);
					params.put("boardSn", subSequence);
					params.put("fileSn", 1);
					Map<String, Object> menuImageMap = scriptDAO.selectSingleFile(params);
					if (menuImageMap != null) {
						cmmnFileSaveService.deleteImageFile(SUB_BOARD_SE, (String) menuImageMap.get("NEW_FILE_NM"));
						scriptDAO.deleteImageFile(params);
					}
				}
				if (menuFile != null) {
					imageYn = "Y";
					Map<String, Object> menuMap = cmmnFileSaveService.saveImageFile(menuFile, SUB_BOARD_SE, 1);
					if (menuMap != null) {
						menuMap.put("boardSe", SUB_BOARD_SE);
						menuMap.put("boardSn", subSequence);
						menuMap.put("fileSn", 1);
						menuMap.put("expsrOrdr", 1);
						menuMap.put("isrtId", ContentsUtils.getMemberAttribute(request, "mngrId"));
						menuMap.put("isrtIp", ContentsUtils.getClientIP(request));
						log.debug("menuMap : {}", menuMap);
						scriptDAO.insertImageFile(menuMap);

						description = "<img src=\"" + menuMap.get(CmmnConstant.URL) + "/" + menuMap.get(CmmnConstant.NEW_FILE_NM) + "\" width=\"100%\">" + "\n" + description;
					}
				}
				Map<String, String> updateFcltDtlMap = new HashMap<>();
				updateFcltDtlMap.put("fcltDtlSeq", subSequence);
				updateFcltDtlMap.put("dtlMenu", request.getParameter("inMenu" + index));
				updateFcltDtlMap.put("updtId", ContentsUtils.getMemberAttribute(request, "mngrId"));
				updateFcltDtlMap.put("updtIp", ContentsUtils.getClientIP(request));
				updateFcltDtlMap.put("imgYn", imageYn);
				updateFcltDtlMap.put("dtlDesc", description);
				fclMngDAO.updateFcltDtl(updateFcltDtlMap);
			}
		}
	}

	/* 썸네일 이미지 저장 */
	private void modifyThumbImage(MultipartHttpServletRequest mtfRequest, HttpServletRequest request, String mainSequence) throws Exception {
		MultipartFile thumbFile = ContentsUtils.getSingleUploadFile(mtfRequest, ContentsUtils.THUMBNAIL_IMAGE);
		if (thumbFile == null) {
			return;
		}
		Map<String, Object> thumbMap = cmmnFileSaveService.saveImageFile(thumbFile, MAIN_BOARD_SE, 1);
		if (thumbMap == null) {
			return;
		}
		Map<String, Object> params = new HashMap<>();
		params.put("boardSe", MAIN_BOARD_SE);
		params.put("boardSn", mainSequence);
		params.put("fileSn", 1);
		Map<String, Object> thumbImageMap = scriptDAO.selectSingleFile(params);
		if (thumbImageMap != null) {
			cmmnFileSaveService.deleteImageFile(MAIN_BOARD_SE, (String) thumbImageMap.get("NEW_FILE_NM"));
			scriptDAO.deleteImageFile(params);
		}

		thumbMap.put("boardSe", MAIN_BOARD_SE);
		thumbMap.put("boardSn", mainSequence);
		thumbMap.put("fileSn", 1);
		thumbMap.put("expsrOrdr", 1);
		thumbMap.put("isrtId", ContentsUtils.getMemberAttribute(request, "mngrId"));
		thumbMap.put("isrtIp", ContentsUtils.getClientIP(request));
		log.debug("thumbMap : {}", thumbMap);
		scriptDAO.insertImageFile(thumbMap);
	}

	/* 현장대기 이미지 저장 */
	private void modifyWaitingImage(MultipartHttpServletRequest mtfRequest, HttpServletRequest request, String mainSequence) throws Exception {
		MultipartFile waitingFile = ContentsUtils.getSingleUploadFile(mtfRequest, ContentsUtils.WAITING_IMAGE);
		if (waitingFile == null) {
			return;
		}
		Map<String, Object> waitingMap = cmmnFileSaveService.saveImageFile(waitingFile, MAIN_BOARD_SE, 2);
		if (waitingMap == null) {
			return;
		}
		Map<String, Object> params = new HashMap<>();
		params.put("boardSe", MAIN_BOARD_SE);
		params.put("boardSn", mainSequence);
		params.put("fileSn", 2);
		Map<String, Object> waitingImageMap = scriptDAO.selectSingleFile(params);
		if (waitingImageMap != null) {
			cmmnFileSaveService.deleteImageFile(MAIN_BOARD_SE, (String) waitingImageMap.get("NEW_FILE_NM"));
			scriptDAO.deleteImageFile(params);
		}

		waitingMap.put("boardSe", MAIN_BOARD_SE);
		waitingMap.put("boardSn", mainSequence);
		waitingMap.put("fileSn", 2);
		waitingMap.put("expsrOrdr", 1);
		waitingMap.put("isrtId", ContentsUtils.getMemberAttribute(request, "mngrId"));
		waitingMap.put("isrtIp", ContentsUtils.getClientIP(request));
		log.debug("waitingMap : {}", waitingMap);
		scriptDAO.insertImageFile(waitingMap);
	}

	/* 슬라이드 이미지 저장 */
	private void modifySlideImage(MultipartHttpServletRequest mtfRequest, HttpServletRequest request, String mainSequence) throws Exception {
		/* 삭제할 이미지 */
		final String deleteImageIndex = request.getParameter("deleteImageIndex");
		Map<String, Object> params = new HashMap<>();
		params.put("boardSe", MAIN_BOARD_SE);
		params.put("boardSn", mainSequence);
		List<Map<String, Object>> slideImageMap = scriptDAO.selectMultiFile(params);
		List<Map<String, Object>> updateImageMap = new ArrayList<>();
		if (deleteImageIndex != null && deleteImageIndex.trim().length() > 0) {
			String[] seqArray = deleteImageIndex.split(",");
			for (Map<String, Object> imageMap : slideImageMap) {
				String fileSn = (String) imageMap.get("FILE_SN");
				boolean isEqual = false;
				for (String sn : seqArray) {
					if (fileSn.equals(sn)) {
						isEqual = true;
						break;
					}
				}
				if (isEqual) {
					params.put("fileSn", fileSn);
					cmmnFileSaveService.deleteImageFile(MAIN_BOARD_SE, (String) imageMap.get("NEW_FILE_NM"));
					scriptDAO.deleteImageFile(params);
				} else {
					updateImageMap.add(imageMap);
				}
			}
		}
		int fileSnIndex = 3;
		int orderIndex = 1;
		if (updateImageMap.size() > 0) {
			for (Map<String, Object> imageMap : updateImageMap) {
				params.put("fileSn", fileSnIndex);
				params.put("oldFileSn", imageMap.get("FILE_SN"));
				params.put("expsrOrdr", orderIndex);
				scriptDAO.updateImageFile(params);
				fileSnIndex++;
				orderIndex++;
			}
		} else {
			fileSnIndex = fileSnIndex + slideImageMap.size();
			orderIndex = orderIndex + slideImageMap.size();
		}

		/* 신규 업로드된 파일 등록 */
		List<MultipartFile> fileList = ContentsUtils.getMultiUploadFile(mtfRequest, ContentsUtils.SLIDE_IMAGE);
		if (fileList == null || fileList.size() == 0) {
			return;
		}

		for (MultipartFile multiFile : fileList) {
			Map<String, Object> slideMap = cmmnFileSaveService.saveImageFile(multiFile, MAIN_BOARD_SE, orderIndex);
			if (slideMap == null) {
				continue;
			}
			slideMap.put("boardSe", MAIN_BOARD_SE);
			slideMap.put("boardSn", mainSequence);
			slideMap.put("fileSn", fileSnIndex);
			slideMap.put("expsrOrdr", orderIndex);
			slideMap.put("isrtId", ContentsUtils.getMemberAttribute(request, "mngrId"));
			slideMap.put("isrtIp", ContentsUtils.getClientIP(request));
			log.debug("waitingMap : {}", slideMap);
			scriptDAO.insertImageFile(slideMap);

			fileSnIndex++;
			orderIndex++;
		}
	}
	// 시설 관리 목록 수정
	public int modifyFclMng(DataClass params) throws Exception{
		int result = 0;
		try {
			result = fclMngDAO.modifyFclMng(params);
		} catch (Exception e) {
			logger.error("modifyFclMng", e);
			throw new CmmnUserException(MessageManager.getIGMessage("FclMngServiceImpl.modifyFclMng", null));
		}
		return result;
	}
	
	// 시설 관리 목록 삭제
	public int deleteFclMng(DataClass params) throws Exception{
		int result = 0;
		try {
			result = fclMngDAO.deleteFclMng(params);
		} catch (Exception e) {
			logger.error("deleteFclMng", e);
			throw new CmmnUserException(MessageManager.getIGMessage("FclMngServiceImpl.deleteFclMng", null));
		}
		return result;
	}

	@Override
	public String deleteFclMng(HttpServletRequest request, Model model) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("fcltSeq", request.getParameter("fcltSeq"));
		params.put("updtId", ContentsUtils.getMemberAttribute(request, "mngrId"));
		params.put("updtIp", ContentsUtils.getClientIP(request));
		fclMngDAO.deleteFclt(params);

		return ResultCode.SYS_0000.getCode();
	}
}
