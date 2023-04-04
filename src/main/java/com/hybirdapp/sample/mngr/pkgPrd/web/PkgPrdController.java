package com.hybirdapp.sample.mngr.pkgPrd.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hybirdapp.sample.mngr.pkgPrd.service.PkgPrdVO;
import com.hybirdapp.sample.mngr.fclMng.service.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Enumeration;

@Slf4j
@Controller
@RequestMapping("/mngr/pkgPrd")
public class PkgPrdController extends CmmnController {

	@Resource(name = "PkgPrdService")
	private com.hybirdapp.sample.mngr.pkgPrd.service.PkgPrdService PkgPrdService;

	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;

	/**
	 * 패키지상품관리(목록)
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pkgPrdView.do")
	public String pkgPrdView(Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);
		
		model.addAttribute("mParam", params.getMap());

		return "mngr:/pkgProduct/pkgPrdView";
	}
	
	// 패키지 상품 목록 조회
	@RequestMapping(value = "/searchPkgPrdList.ajax")
	public String searchPkgPrdList(@ModelAttribute("PkgPrdVO") PkgPrdVO vo, Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);                                                           
		HttpSession ses = request.getSession(true);
		
		/* ===========[ 페이징 필수값 ]=================== */
		params.set("currPageIdx",  vo.getCurrPageIdx());
		params.set("offsetIndex",  vo.getOffsetIndex());
		params.set("pageMemory",  vo.getPageMemory());
		params.set("pageSize",  vo.getPageSize());
		params.set("pageBottomSize",  vo.getPageBottomSize());
		/* ============================================== */
		
		int totPageCnt = PkgPrdService.searchPkgPrdListCnt(params);
		vo.setTotPageCnt(totPageCnt);
		
		/* 총 건수 */
		model.addAttribute("totalCnt", totPageCnt);
		
		/* 조회 */
		model.addAttribute("result", PkgPrdService.searchPkgPrdList(params));
		
		/* 페이징 처리 */
		model.addAttribute("pages", vo);
		
		
		return "jsonView";
	}
	
	
	/**
	 * 패키지상품관리(팝업)
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pkgPrdPopup.do")
	public String pkgPrdPopupView(Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);                                                           
		HttpSession ses = request.getSession(true);
		
		model.addAttribute("mParam", params.getMap()); 

		/* 조회 */
		model.addAttribute("result", PkgPrdService.searchPkgPrd(params));
		
		return "mngr/pkgProduct/popup/pkgPrdPopup";
	}

	@RequestMapping(value = "/registPkgPrd.do")
	public String registPkgPrd(Model model, HttpServletRequest request) throws Exception {
		log.debug("/registPkgPrd.do");
		HttpSession session = request.getSession();
		Enumeration<String> iter = session.getAttributeNames();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			log.debug("key = {}, value = {}", key, session.getAttribute(key));
		}
		return "mngr:/pkgProduct/registPkgPrd";
	}

	@RequestMapping(value = "/registPkgPrdPost.ajax")
	public String registFclMngPost(MultipartHttpServletRequest mtfRequest, Model model, HttpServletRequest request) throws Exception {
		String code = PkgPrdService.registPkgPrd(mtfRequest, request);
		log.debug("code : {}", code);
		model.addAttribute("code", code);
		model.addAttribute("msg", ResultCode.getMessage(code));

		return "jsonView";
	}

	@RequestMapping(value = "/modifyPkgPrd.do")
	public String modifyPkgPrd(Model model, HttpServletRequest request) throws Exception {
		PkgPrdService.viewPkgPrdForModify(request, model);

		return "mngr:/pkgProduct/modifyPkgPrd";
	}

	@RequestMapping(value = "/modifyPkgPrdPost.ajax")
	public String modifyPkgPrdPost(MultipartHttpServletRequest mtfRequest, Model model, HttpServletRequest request) throws Exception {
		String code = PkgPrdService.modifyPkgPrd(mtfRequest, request);
		log.debug("code : {}", code);
		model.addAttribute("code", code);
		model.addAttribute("msg", ResultCode.getMessage(code));

		return "jsonView";
	}

	@RequestMapping(value = "/deletePkgPrdPost.ajax")
	public String deletePkgPrdPost(Model model, HttpServletRequest request) throws Exception {
		String code = PkgPrdService.deletePkgPrd(request, model);
		log.debug("code : {}", code);
		model.addAttribute("code", code);
		model.addAttribute("msg", ResultCode.getMessage(code));

		return "jsonView";
	}
}
