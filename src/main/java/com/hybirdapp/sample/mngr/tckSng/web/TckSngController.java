package com.hybirdapp.sample.mngr.tckSng.web;

import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import com.hybirdapp.sample.cmmn.CmmnMngrVO;
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
@RequestMapping("/mngr/tckSng")
public class TckSngController extends CmmnController {

	@Resource(name = "TckSngService")
	private com.hybirdapp.sample.mngr.tckSng.service.TckSngService TckSngService;

	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;

	/***
	 * 단일상품티겟관리(목록)
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckSngView.do")
	public String TckSngView(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		model.addAttribute("mParam", params.getMap());

		return "mngr:/tckSng/tckSngView";
	}

	/***
	 * 단일상품 목록 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectSngPrdList.ajax")
	public String selectSngPrdList(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);

		model.addAttribute("result", TckSngService.selectSngPrdList(params));

		return "jsonView";
	}

	/***
	 * 단일상품 목록 조회 - SertbMenuUnit
	 * @author yhlee
	 */
	@RequestMapping(value = "/selectMenuUnitList.ajax")
	public String selectMenuUnitList(HttpServletRequest request, Model model) throws Exception {
		DataClass params = new DataClass(request);
		model.addAttribute("result", TckSngService.selectMenuUnitList(params));
		return "jsonView";
	}

	/***
	 * 단일상품티켓 - SertbMenuUnit
	 * @author yhlee
	 */
	@RequestMapping(value = "/selectUnitProdTicket.ajax")
	public String selectUnitProdTicket(HttpServletRequest request, Model model) throws Exception {
		DataClass params = new DataClass(request);
		model.addAttribute("result", TckSngService.selectUnitProdTicket(params));
		return "jsonView";
	}

	/***
	 * 티켓 분류 목록 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectTckGrpList.ajax")
	public String selectTckGrpList(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);

		model.addAttribute("result", TckSngService.selectTckGrpList(params));

		return "jsonView";
	}


	/* 단일 상품 티켓 목록 조회 */
	@RequestMapping(value = "/searchTckSngList.ajax")
	public String searchTckSngList(@ModelAttribute("CmmnMngrVO") CmmnMngrVO vo, Model model, HttpServletRequest request) throws Exception {
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
		int totPageCnt = TckSngService.searchTckSngListCnt(params);
		vo.setTotPageCnt(totPageCnt);                //페이징
		model.addAttribute("totalCnt", totPageCnt);

		/* 조회 */
		model.addAttribute("result", TckSngService.searchTckSngList(params));

		/* 페이징 처리 */
		model.addAttribute("pages", vo);

		return "jsonView";
	}


	/***
	 * 단일 상품 티켓 - 등록(팝업)
	 */
	@RequestMapping(value = "/tckSngPopup.do")
	public String tckSngPopupView(HttpServletRequest request, Model model) throws Exception {

		DataClass params = new DataClass(request);
		model.addAttribute("mParam", params.getMap());
		String mLoc = params.get("mLoc");

		if (StringUtils.hasText(mLoc)) {
			model.addAttribute("result", TckSngService.searchTckSng(params).get(0));
			model.addAttribute("list0", TckSngService.searchTckSng4Np(params));
			model.addAttribute("list", TckSngService.searchTckSng(params));
		}

		// 팝업창은 mngr/(슬러쉬), view 창은 mngr:(콜론)
		return "mngr:/tckSng/popup/tckSngPopup";
	}

	/* 단일상품티켓 등록 */
	@RequestMapping(value = "/registtckSng.ajax")
	public String registTckSng(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		TckSngService.registTckSng(params);

		return "jsonView";
	}

	/* 단일상품티켓 수정 */
	@RequestMapping(value = "/modifyTckSng.ajax")
	public String modifyTckSng(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		TckSngService.modifyTckSng(params);

		return "jsonView";
	}

	/* 단일상품티켓 삭제 */
	@RequestMapping(value = "/deleteTckSng.ajax")
	public String deleteTckSng(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		TckSngService.deleteTckSng(params);

		return "jsonView";
	}
}
