package com.hybirdapp.sample.mngr.grpPrd.web;

import com.hybirdapp.sample.cmmn.CmmnConstant;
import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.cmmn.CmmnMngrVO;
import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/mngr/grpPrd")
public class GrpPrdController extends CmmnController {

	@Resource(name = "GrpPrdService")
	private com.hybirdapp.sample.mngr.grpPrd.service.GrpPrdService GrpPrdService;

	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;

	/***
	 * 상품그룹관리(목록)
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/grpPrdView.do")
	public String grpPrdView(Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);

		model.addAttribute("mParam", params.getMap());

		return "mngr:/grpPrd/grpPrdView";
	}

	@RequestMapping(value = "/searchGrpPrdList.ajax")
	public String searchGrpPrdList(@ModelAttribute("CmmnMngrVO") CmmnMngrVO vo, Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);
		HttpSession ses = request.getSession(true);

		/* ===========[ 페이징 필수값 ]=================== */
		params.set("currPageIdx", vo.getCurrPageIdx());
		params.set("offsetIndex", vo.getOffsetIndex());
		params.set("pageMemory", vo.getPageMemory());
		params.set("pageSize", vo.getPageSize());
		params.set("pageBottomSize", vo.getPageBottomSize());
		/* ============================================== */

		/* 총 건수 */
		int totPageCnt = GrpPrdService.searchGrpPrdListCnt(params);
		vo.setTotPageCnt(totPageCnt);                //페이징
		model.addAttribute("totalCnt", totPageCnt);

		/* 조회 */
		model.addAttribute("result", GrpPrdService.searchGrpPrdList(params));

		/* 페이징 처리 */
		model.addAttribute("pages", vo);

		return "jsonView";
	}


	/***
	 * 상품그룹관리(팝업)
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/grpPrdPopup.do")
	public String grpPrdPopupView(Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);
		CmmnConstant.setIpInfo(params, request);

		if (StringUtils.hasText(params.get("gr_loc"))) {
			int cnt = GrpPrdService.isUsedGrp(params);
			model.addAttribute("result", GrpPrdService.searchGrpPrd(params));
			model.addAttribute("cnt", cnt);
		}
		model.addAttribute("mParam", params.getMap());

		return "mngr/grpPrd/popup/grpPrdPopup";
	}


	/* 상품그룹 등록 */
	@RequestMapping(value = "/registGrpPrd.ajax")
	public String registMenuGrp(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		CmmnConstant.setIpInfo(params, request);
		GrpPrdService.modifyGrpPrd(params);

		return "jsonView";
	}

	/* 상품그룹 수정 */
	@RequestMapping(value = "/modifyGrpPrd.ajax")
	public String modifyMenuGrp(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		CmmnConstant.setIpInfo(params, request);
		GrpPrdService.modifyGrpPrd(params);

		return "jsonView";
	}

	/* 상품그룹 삭제 */
	@RequestMapping(value = "/deleteGrpPrd.ajax")
	public String deleteGrpPrd(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		CmmnConstant.setIpInfo(params, request);
		GrpPrdService.deleteGrpPrd(params);

		return "jsonView";
	}
}
