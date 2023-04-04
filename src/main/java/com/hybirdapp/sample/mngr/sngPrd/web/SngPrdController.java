package com.hybirdapp.sample.mngr.sngPrd.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnConstant;
import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.cmmn.CmmnMngrVO;
import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;

@Controller
@RequestMapping("/mngr/sngPrd")
public class SngPrdController extends CmmnController {

	@Resource(name = "SngPrdService")
	private com.hybirdapp.sample.mngr.sngPrd.service.SngPrdService SngPrdService;

	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;

	/***
	 * 단일상품관리(목록)
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sngPrdView.do")
	public String sngPrdView(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		model.addAttribute("mParam", params.getMap());

		return "mngr:/sngProduct/sngPrdView";
	}


	/* 상품그룹 목록 조회 */
	@RequestMapping(value = "/searchSngPrdList.ajax")
	public String searchSngPrdList(@ModelAttribute("CmmnMngrVO") CmmnMngrVO vo, Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);
		HttpSession ses = request.getSession(true);

		/* ===========[ 페이징 필수값 ]=================== */
		params.set("currPageIdx", vo.getCurrPageIdx());
		params.set("offsetIndex", vo.getOffsetIndex());
		params.set("pageMemory", vo.getPageMemory());
		params.set("pageSize", vo.getPageSize());
		params.set("pageBottomSize", vo.getPageBottomSize());
		/* =========================================== */

		/* 총 건수*/
		int totPageCnt = SngPrdService.searchSngPrdListCnt(params);
		vo.setTotPageCnt(totPageCnt);                //페이징
		model.addAttribute("totalCnt", totPageCnt);

		/* 조회 */
		model.addAttribute("result", SngPrdService.searchSngPrdList(params));

		/* 페이징 처리 */
		model.addAttribute("pages", vo);

		return "jsonView";
	}


	/***
	 * 단일상품관리 - 등록(팝업)
	 */
	@RequestMapping(value = "/sngPrdPopup.do")
	public String sngPrdPopupView(HttpServletRequest request, Model model) throws Exception {
		DataClass params = new DataClass(request);
		HttpSession ses = request.getSession(true);

		model.addAttribute("mParam", params.getMap());

		String menuUnitSeq = params.get("menuUnitSeq");

		int cnt = SngPrdService.isUsedSngPrd(params);

		if (StringUtils.hasText(menuUnitSeq)) {
			model.addAttribute("result", SngPrdService.searchSngPrd(params));
			model.addAttribute("cnt", cnt);
		}
		return "mngr/sngProduct/popup/sngPrdPopup";
	}

	/* 단일상품 등록 */
	@RequestMapping(value = "/registSngPrd.ajax")
	public String registSngPrd(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		CmmnConstant.setIpInfo(params, request);
		SngPrdService.registSngPrd(params);

		return "jsonView";
	}

	/* 단일상품 수정 */
	@RequestMapping(value = "/modifySngPrd.ajax")
	public String modifySngPrd(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		SngPrdService.modifySngPrd(params);

		return "jsonView";
	}

	/* 단일상품 삭제 */
	@RequestMapping(value = "/deleteSngPrd.ajax")
	public String deleteSngPrd(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		SngPrdService.deleteSngPrd(params);

		return "jsonView";
	}
}
