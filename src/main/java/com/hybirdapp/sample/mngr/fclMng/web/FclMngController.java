package com.hybirdapp.sample.mngr.fclMng.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hybirdapp.sample.mngr.fclMng.service.ContentsUtils;
import com.hybirdapp.sample.mngr.fclMng.service.ResultCode;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import com.hybirdapp.sample.mngr.fclMng.service.FclMngService;
import com.hybirdapp.sample.mngr.fclMng.service.FclMngVO;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.*;

@Slf4j
@Controller
@RequestMapping("/mngr/fclMng")
public class FclMngController extends CmmnController {

	@Resource(name = "FclMngService")
	private FclMngService FclMngService;

	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;

	/***
	 * 시설관리(목록)
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fclMngView.do")
	public String fclMngView(Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);
		model.addAttribute("mParam", params.getMap());

		return "mngr:/fclMng/fclMngView";
	}

	@RequestMapping(value = "/registFclMng.do")
	public String registFclMng(Model model, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		Enumeration<String> iter = session.getAttributeNames();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			log.debug("key = {}, value = {}", key, session.getAttribute(key));
		}

		EgovMap map = ContentsUtils.listMemberAttributes(request);
		log.debug("Map : {}", map);
		log.debug("MNGR_ID : {}", ContentsUtils.getMemberAttribute(request, "mngrId"));

		return "mngr:/fclMng/registFclMng";
	}

	@RequestMapping(value = "/modifyFclMng.do")
	public String modifyFclMng(Model model, HttpServletRequest request) throws Exception {
		FclMngService.viewFcltForModify(request, model);

		return "mngr:/fclMng/modifyFclMng";
	}

	@RequestMapping(value = "/modifyFclMngPost.ajax")
	public String modifyFclMngPost(MultipartHttpServletRequest mtfRequest, Model model, HttpServletRequest request) throws Exception {
		String code = FclMngService.modifyFclMng(mtfRequest, request);
		log.debug("code : {}", code);
		model.addAttribute("code", code);
		model.addAttribute("msg", ResultCode.getMessage(code));

		return "jsonView";
	}

	@RequestMapping(value = "/deleteFclMngPost.ajax")
	public String deleteFclMngPost(Model model, HttpServletRequest request) throws Exception {
		String code = FclMngService.deleteFclMng(request, model);
		log.debug("code : {}", code);
		model.addAttribute("code", code);
		model.addAttribute("msg", ResultCode.getMessage(code));

		return "jsonView";
	}

	@RequestMapping(value = "/registFclMngPost.ajax")
	public String registFclMngPost(MultipartHttpServletRequest mtfRequest, Model model, HttpServletRequest request) throws Exception {
		String code = FclMngService.registFclMng(mtfRequest, request);
		log.debug("code : {}", code);
		model.addAttribute("code", code);
		model.addAttribute("msg", ResultCode.getMessage(code));

		return "jsonView";
	}


	@RequestMapping(value = "/registFclMngPostTmp.ajax")
	public String registFclMngPostTmp(MultipartHttpServletRequest mtfRequest, Model model, HttpServletRequest request) throws Exception {
		List<MultipartFile> fileList = mtfRequest.getFiles("imageUpload");
		Iterator fileNameIter = mtfRequest.getFileNames();
		while (fileNameIter.hasNext()) {
			String key = (String)fileNameIter.next();
			log.debug("File key : {}", key);
			MultipartFile file = mtfRequest.getFile(key);
			log.debug("File Name : {}", file.getOriginalFilename());
		}
		log.debug("File Length : {}", fileList.size());
		if (fileList != null && fileList.size() > 0) {
			int index = 1;
			for (MultipartFile mf : fileList) {
				cmmnFileSaveService.saveImageFile(mf, "IMAGE", index);
				String originFileName = mf.getOriginalFilename();
				log.debug("File Name : {}", originFileName);
				log.debug("File Size : {}", mf.getSize());
				index++;
			}
		}

		return "jsonView";
	}
	
	// 시설 관리 목록 조회
	@RequestMapping(value = "/searchFclMngList.ajax")
	public String searchFclMngList(@ModelAttribute("FclMngVO") FclMngVO vo, Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);                                                           
		HttpSession ses = request.getSession(true);
		
		/* ===========[ 페이징 필수값 ]=================== */
		params.set("currPageIdx",  vo.getCurrPageIdx());
		params.set("offsetIndex",  vo.getOffsetIndex());
		params.set("pageMemory",  vo.getPageMemory());
		params.set("pageSize",  vo.getPageSize());
		params.set("pageBottomSize",  vo.getPageBottomSize());
		/* ============================================== */
		
		int totPageCnt = FclMngService.searchFclMngListCnt(params);
		vo.setTotPageCnt(totPageCnt);	
		
		/* 총 건수 */
		model.addAttribute("totalCnt",totPageCnt );
		
		/* 조회 */
		model.addAttribute("result", FclMngService.searchFclMngList(params));
		
		/* 페이징 처리 */
		model.addAttribute("pages", vo);
		
		
		return "jsonView";
	}
	
	
	/***
	 * 시설관리(팝업)
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fclMngPopup.do")
	public String fclMngPopupView(Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);                                                           
		HttpSession ses = request.getSession(true);
		
		model.addAttribute("mParam", params.getMap());
		
		String fcltSeq = params.get("fcltSeq");

		/* 조회 */
		if(!"".equals(fcltSeq) && fcltSeq != "" && !fcltSeq.isEmpty()) {
			model.addAttribute("result", FclMngService.searchFclMng(params));
		}
	
		return "mngr/fclMng/popup/fclMngPopup";
	}

	@RequestMapping(value = "/sortFcltPopup.do")
	public String sortFcltPopup(Model model, HttpServletRequest request) throws Exception {
		FclMngService.viewFcltForOrder(request, model);
		return "mngr/fclMng/popup/sortFcltPopup";
	}

	@RequestMapping(value = "/sortFcltPopupPost.ajax")
	public String sortFcltPopupPost(Model model, HttpServletRequest request) throws Exception {
		FclMngService.updateFcltOrder(request, model);
		return "jsonView";
	}
}
