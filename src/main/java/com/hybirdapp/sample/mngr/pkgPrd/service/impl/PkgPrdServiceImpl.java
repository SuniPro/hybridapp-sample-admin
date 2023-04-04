package com.hybirdapp.sample.mngr.pkgPrd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hybirdapp.sample.cmmn.CmmnConstant;
import com.hybirdapp.sample.cmmn.ex.CmmnMngrException;
import com.hybirdapp.sample.cmmn.script.service.impl.ScriptDAO;
import com.hybirdapp.sample.mngr.fclMng.service.ContentMapGenerator;
import com.hybirdapp.sample.mngr.fclMng.service.ContentsUtils;
import com.hybirdapp.sample.mngr.fclMng.service.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.cmmn.script.service.ScriptService;
import com.hybirdapp.sample.mngr.pkgPrd.service.PkgPrdService;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Slf4j
@Service("PkgPrdService")
@Transactional(transactionManager = "txManager", readOnly = false)
public class PkgPrdServiceImpl implements PkgPrdService {
	
	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "PkgPrdDAO")
	private PkgPrdDAO pkgPrdDAO;

	@Resource(name = "ScriptDAO")
	private ScriptDAO scriptDAO;

	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;

	@Resource(name = "ScriptService")
	private ScriptService scriptService;

	private final String MAIN_BOARD_SE = "EQIPP";
	private final String SUB_BOARD_SE = "EQIPP_DTL";

	// 패키지 상품 목록 건수
	public int searchPkgPrdListCnt(DataClass params) throws Exception {
		int result = 0;
		try {
			result = pkgPrdDAO.searchPkgPrdListCnt(params.getMap());
		} catch (Exception e) {
			logger.error("searchPkgPrdListCnt", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}
	
	// 패키지 상품 목록 조회
	public List searchPkgPrdList(DataClass params) throws Exception {
		List result = null;

		try {
			result = pkgPrdDAO.searchPkgPrdList(params.getMap());
		} catch (Exception e) {
			logger.error("searchPkgPrdList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}

	// 패키지 상품 단건 조회
	public HashMap searchPkgPrd(DataClass params) throws Exception {
		HashMap result = new HashMap();
		try {
			result = pkgPrdDAO.searchPkgPrd(params.getMap());
		} catch (Exception e) {
			logger.error("searchPkgPrd", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}
	
	// 패키지 상품 등록
	public int registPkgPrd(DataClass params) throws Exception{
		int result = 0;
		try {
			result = pkgPrdDAO.registPkgPrd(params);
		
		} catch (Exception e) {
			logger.error("registPkgPrd", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	/* 패키지 상품 등록 */
	@Override
	@Transactional(transactionManager = "txManager", rollbackFor = CmmnMngrException.class)
	public String registPkgPrd(MultipartHttpServletRequest mtfRequest, HttpServletRequest request) throws Exception {
		/* 0. 공통으로 사용할 변수 */
		final String mngrId = ContentsUtils.getMemberAttribute(request, "mngrId");
		final String remoteIp = ContentsUtils.getClientIP(request);
		/* 1. 요청 파라미터 유효성 체크 */
		String code = ContentsUtils.validatePkgPrdRegist(request);
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
		final Map<String, String> params = ContentsUtils.setRegistParams(request, ContentsUtils.REGIST_PKGPRD_PARAMS);
		log.debug("params : {}", params);
		/* 3. SERTB_MENU_PKG 테이블에 저장 */
		final String mainSequence = pkgPrdDAO.getPkgPrdSequence();
		/* 3.1 썸네일이미지 저장 */
		saveThumbImage(mtfRequest, request, mainSequence);
		/* 3.2 슬라이드 이미지 저장 */
		saveSlideImage(mtfRequest, request, mainSequence);
		/* 3.3 시설정보 등록 : SERTB_MENU_PKG */
		Map<String, String> insertFcltMap = ContentMapGenerator.makePkgPrdInsert(params, mngrId, remoteIp);
		insertFcltMap.put("fcltSequence", mainSequence);
		log.debug("insertFcltMap : {}", insertFcltMap);
		pkgPrdDAO.insertPkgPrd(insertFcltMap);
		/* 4. 상세메뉴 저장 */
		insertPkgPrdDtl(mtfRequest, request, mainSequence, params.get("descIndex"));

		return ResultCode.SYS_0000.getCode();
	}

	/* P_PLACE 테이블에 저장 */
	private void insertPkgPrdDtl(MultipartHttpServletRequest mtfRequest, HttpServletRequest request, final String mainSequence, final String menuIndex) throws Exception {
		if (mtfRequest == null || request == null || mainSequence == null || menuIndex == null) {
			return;
		}
		/* 1 상세메뉴 정보 */
		final String[] indexArray = menuIndex.split(",");
		int dtlOrder = 1;
		for (String index : indexArray) {
			String subSequence = pkgPrdDAO.getPkgPrdDtlSequence();
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
			pkgPrdDAO.insertPkgPrdDtl(insertFcltDtlMap);
			dtlOrder++;
		}
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
	public void viewPkgPrdForModify(HttpServletRequest request, Model model) throws Exception {
		final String fcltSequence = request.getParameter("fcltSeq");
		/* 1. 시설조회 */
		Map<String, Object> selectFcltMap = pkgPrdDAO.selectPkgPrd(fcltSequence);
		if (selectFcltMap == null) {
			model.addAttribute("code", ResultCode.SYS_3000.getCode());
			model.addAttribute("msg", ResultCode.getMessage(ResultCode.SYS_3000.getCode()));
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
		/* 2.3 슬라이드이미지 */
		List<Map<String, Object>> slideImageMap = scriptDAO.selectMultiFile(params);
//		if (slideImageMap == null || slideImageMap.size() == 0) {
//			model.addAttribute("code", ResultCode.SYS_2003.getCode());
//			model.addAttribute("msg", ResultCode.getMessage(ResultCode.SYS_2003.getCode()));
//			return;
//		}
		/* 3. 상세메뉴 */
		List<Map<String, String>> listFcltDtl = pkgPrdDAO.listPkgPrdDtl(fcltSequence);
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
		log.debug("slideImageMap : {}", slideImageMap);
		log.debug("listFcltDtl : {}", listFcltDtl);
		model.addAttribute("fcltInfo", selectFcltMap);
		model.addAttribute("thumbImage", thumbImageMap);
		model.addAttribute("listSlideImage", slideImageMap);
		model.addAttribute("listFcltDtl", listFcltDtl);
	}

	@Override
	@Transactional(transactionManager = "txManager", rollbackFor = CmmnMngrException.class)
	public String modifyPkgPrd(MultipartHttpServletRequest mtfRequest, HttpServletRequest request) throws Exception {
		/* 0. 공통으로 사용할 변수 */
		final String mngrId = ContentsUtils.getMemberAttribute(request, "mngrId");
		final String remoteIp = ContentsUtils.getClientIP(request);
		/* 1. 요청 파라미터 유효성 체크 */
		String code = ContentsUtils.validatePkgPrdModify(request);
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
		final Map<String, String> params = ContentsUtils.setRegistParams(request, ContentsUtils.MODIFY_PKGPRD_PARAMS);
		log.debug("params : {}", params);
		/* 3. SERTB_FCLT 테이블에 저장 */
		final String mainSequence = params.get("fcltSeq");
		/* 3.1 썸네일이미지 저장 */
		modifyThumbImage(mtfRequest, request, mainSequence);
		/* 3.2 슬라이드 이미지 저장 */
		modifySlideImage(mtfRequest, request, mainSequence);
		/* 3.3 시설정보 등록 : SERTB_FCLT */
		Map<String, String> updateFcltMap = ContentMapGenerator.makePkgPrdUpdate(params, mngrId, remoteIp);
		log.debug("updateFcltMap : {}", updateFcltMap);
		pkgPrdDAO.updatePkgPrd(updateFcltMap);
		/* 4. 상세메뉴 저장 */
		updatePkgPrdDtl(mtfRequest, request, mainSequence, params.get("descIndex"), params.get("deleteMenuIndex"));

		return ResultCode.SYS_0000.getCode();
	}

	/* DTL 테이블 업데이트 */
	private void updatePkgPrdDtl(MultipartHttpServletRequest mtfRequest, HttpServletRequest request, final String mainSequence, final String menuIndex, final String deleteMenuIndex) throws Exception {
		if (mtfRequest == null || request == null || mainSequence == null || menuIndex == null) {
			return;
		}
		/* 상세메뉴 목록 조회 */
		List<Map<String, String>> listFcltDtl = pkgPrdDAO.listTotalPkgPrdDtl(mainSequence);
		/* 0. 삭제 상세메뉴 */
		if (deleteMenuIndex != null && deleteMenuIndex.trim().length() > 0) {
			final String[] deleteArray = deleteMenuIndex.split(",");
			for (String sequence : deleteArray) {
				if (sequence.trim().length() > 0) {
					Map<String, String> deleteFcltDtlMap = new HashMap<>();
					deleteFcltDtlMap.put("fcltDtlSeq", sequence.trim());
					deleteFcltDtlMap.put("updtId", ContentsUtils.getMemberAttribute(request, "mngrId"));
					deleteFcltDtlMap.put("updtIp", ContentsUtils.getClientIP(request));
					pkgPrdDAO.deletePkgPrdDtl(deleteFcltDtlMap);
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
				subSequence = pkgPrdDAO.getPkgPrdDtlSequence();
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
				pkgPrdDAO.insertPkgPrdDtl(insertFcltDtlMap);
				dtlOrder++;
			} else {
				Map<String, String> dtlMap = null;
				for (Map<String, String> map : listFcltDtl) {
					log.debug("dtlMap : {}", map.get("MENU_PKG_DTL_SEQ"));
					if (subSequence.trim().equals(map.get("MENU_PKG_DTL_SEQ").trim())) {
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
				pkgPrdDAO.updatePkgPrdDtl(updateFcltDtlMap);
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

	// 패키지 상품 수정
	public int modifyPkgPrd(DataClass params) throws Exception{
		int result = 0;
		try {
			result = pkgPrdDAO.modifyPkgPrd(params);
		} catch (Exception e) {
			logger.error("modifyPkgPrd", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}
	
	// 패키지 상품 삭제
//	public int deletePkgPrd(DataClass params) throws Exception{
//		int result = 0;
//		try {
//			result = pkgPrdDAO.deletePkgPrd(params);
//		} catch (Exception e) {
//			logger.error("deletePkgPrd", e);
//			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
//		}
//		return result;
//	}

	@Override
	public String deletePkgPrd(HttpServletRequest request, Model model) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("fcltSeq", request.getParameter("fcltSeq"));
		params.put("updtId", ContentsUtils.getMemberAttribute(request, "mngrId"));
		params.put("updtIp", ContentsUtils.getClientIP(request));
		pkgPrdDAO.deletePkgPrd(params);

		return ResultCode.SYS_0000.getCode();
	}
	
}
