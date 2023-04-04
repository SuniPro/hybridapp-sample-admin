package com.hybirdapp.sample.mngr.tckPkg.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hybirdapp.sample.mngr.tckPkg.service.TckPkgService;
import com.hybirdapp.sample.mngr.tckPkg.service.TckPkgVO;
import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/mngr/tckPkg")
public class TckPkgController extends CmmnController {

	@Resource(name = "TckPkgService")
	private TckPkgService tckPkgService;

	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;

	/***
	 * 패키지 상품 티켓 관리
	 */
	@RequestMapping(value = "/tckPkgView.do")
	public String tckInqView(Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);

		model.addAttribute("mParam", params.getMap());

		return "mngr:/tckPkg/tckPkgView";
	}

	/***
	 * 패키지 상품명 조회
	 *
	 */
	@RequestMapping(value = "/searchMenuPkgNmList.ajax")
	public String searchMenuPkgNmList(Model model) throws Exception {

		model.addAttribute("result", tckPkgService.searchMenuPkgNmList());

		return "jsonView";
	}

	/***
	 * 패키지티켓분류 상품명 조회
	 */
	@RequestMapping(value = "/pkgTckGrpList.ajax")
	public String pkgTckGrpList(Model model) throws Exception {

		model.addAttribute("result", tckPkgService.getPkgTckGrp());

		return "jsonView";
	}

	@RequestMapping(value = "/searchTckPkgList.ajax")
	public String searchTckPkgList(@ModelAttribute("TckPkgVO") TckPkgVO vo, Model model, HttpServletRequest request) throws Exception {
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
		int totPageCnt = tckPkgService.searchTckPkgListCnt(params);
		vo.setTotPageCnt(totPageCnt);
		model.addAttribute("totalCnt", totPageCnt);

		/* 조회 */
		model.addAttribute("result", tckPkgService.searchTckPkgList(params));

		/* 페이징 처리 */
		model.addAttribute("pages", vo);

		return "jsonView";
	}

	/***
	 * 패키지상품티켓 관리
	 */
	@RequestMapping(value = "/pkgPrdTicket.do")
	public String pkgPrdTicket(Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);
		String m_menu = params.get("m_menu");
		HashMap map = tckPkgService.searchPkgTicket(params);
		if (params.get("viewType").equals("U")) {
			String seq = map.get("MP_TICKET_SEQ").toString();
			List<HashMap> tickets = this.tckPkgService.getPkgUnitTickets(seq);
			model.addAttribute("tickets", tickets);
		}
		model.addAttribute("mParam", params.getMap());
		if (StringUtils.hasText(m_menu)) {
			model.addAttribute("result", map);
		}
		return "mngr:/tckPkg/popup/pkgPrdTicket";
	}

	/***
	 * 패키지상품티켓 관리(팝업)
	 */
	@RequestMapping(value = "/pkgPrdTicketPopup.do")
	public String pkgPrdTicketPopup(Model model, HttpServletRequest request) throws Exception {

		DataClass params = new DataClass(request);

		String mpTicketSeq = params.get("mp_ticket_seq");

		model.addAttribute("mParam", params.getMap());
		String ticket = new ObjectMapper().writeValueAsString(tckPkgService.getPkgUnitTicketsPriceSum(params));
		if (StringUtils.hasText(mpTicketSeq)) {
			model.addAttribute("list0", tckPkgService.searchTckPkg4Np(params));
			model.addAttribute("list", tckPkgService.searchPkgTicketPopup(params));
			model.addAttribute("ticket", ticket);
		}
		return "mngr/tckPkg/popup/pkgPrdTicketPopup";
	}

	/*

	 */
	/* 패키지 상품 티켓 등록 *//*

	@RequestMapping(value = "/registTckPkg.ajax")
	public String registTckPkg(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		tckPkgService.registTckPkg(params);

		return "jsonView";
	}

	*/
	/* 패키지 상품 티켓 수정 *//*

	@RequestMapping(value = "/modifyTckPkg.ajax")
	public String modifyTckPkg(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		tckPkgService.modifyTckPkg(params);

		return "jsonView";
	}

	*/
	/* 패키지 상품 티켓 삭제 *//*

	@RequestMapping(value = "/deleteTckPkg.ajax")
	public String deleteTckPkg(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		tckPkgService.deleteTckPkg(params);
		return "jsonView";
	}


*/

	/* 패키지 상품 티켓 등록 */
	@PostMapping(value = "/savePkgTicket.ajax")
	public String mergeMenuPkgTicket(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		tckPkgService.mergeMenuPkgTicket(params);
		return "jsonView";
	}

	/* 패키지 상품 티켓 등록 */
	@PostMapping(value = "/updatePkgTicket.ajax")
	public String updatePkgTicket(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		tckPkgService.updatePkgTicket(params);
		return "jsonView";
	}

	/* 패키지 상품 티켓 등록 */
	@PostMapping(value = "/savePkgUnitTicket.ajax")
	public String mergeMenuPkgUnitTicket(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		tckPkgService.mergeMenuPkgUnitTicket(params);
//		tckPkgService.makePpkgmenu(params);
		return "jsonView";
	}

	@PostMapping(value = "/removePkg.ajax")
	public String removePkg(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		tckPkgService.removePkg(params);
		return "jsonView";
	}
	@PostMapping(value = "/removePkgTicket.ajax")
	public String removePkgTicket(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		tckPkgService.removePkgTicket(params);
		return "jsonView";
	}
}
