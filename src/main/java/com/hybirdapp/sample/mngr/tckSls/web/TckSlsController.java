package com.hybirdapp.sample.mngr.tckSls.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hybirdapp.sample.mngr.tckSls.service.TckSlsService;
import com.hybirdapp.sample.mngr.tckSls.service.TckSlsVO;
import com.hybirdapp.sample.cmmn.CmmnConstant;
import com.hybirdapp.sample.mngr.tckMng.service.TckMngVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;

@Controller
@RequestMapping("/mngr/tckSls")
public class TckSlsController extends CmmnController {

	@Resource(name = "TckSlsService")
	private TckSlsService tckSlsService;

	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;

	/***
	 * 티켓 판매 관리
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckSlsDtlView.do")
	public String tckSlsDtlView(Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);
		
		model.addAttribute("mParam", params.getMap());
		
		return "mngr:/tckMng/tckSlsDtlView";
	}
	
	@RequestMapping(value = "/searchTckSlsList.ajax")
	public String searchTckMngList(@ModelAttribute("TckSlsVO") TckSlsVO vo, Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);                                                           
		HttpSession ses = request.getSession(true);
		
		/* ===========[ 페이징 필수값 ]=================== */
		params.set("currPageIdx",  vo.getCurrPageIdx());
		params.set("offsetIndex",  vo.getOffsetIndex());
		params.set("pageMemory",  vo.getPageMemory());
		params.set("pageSize",  vo.getPageSize());
		params.set("pageBottomSize",  vo.getPageBottomSize());
		/* ============================================== */
		
		/* 총 건수 */
		int totPageCnt = tckSlsService.searchTckSlsListCnt(params);
		vo.setTotPageCnt(totPageCnt);				//페이징
		model.addAttribute("totalCnt",totPageCnt);
		
		/* 조회 */
		model.addAttribute("result", tckSlsService.searchTckSlsList(params));
		
		/* 총 금액 조회 */
		int resultAmt	=	tckSlsService.searchTckSlsAmt(params);
		model.addAttribute("resultAmt", resultAmt);

		/* 당일환불 조회 */
		DataClass refundParams = new DataClass(request);
		refundParams.set("serStartDate",	CmmnConstant.SER_START_DATE);
		refundParams.set("currentDate",		params.get("endDate"));
		int currentDayRefundAmt	=	tckSlsService.searchCurrentDayRefundAmt(refundParams);
		model.addAttribute("currentDayRefundAmt", currentDayRefundAmt);

		/* 이전환불 조회 */
		int beforeDayRefundAmt =  tckSlsService.searchBeforeDayRefundAmt(refundParams);
		model.addAttribute("beforeDayRefundAmt", beforeDayRefundAmt);

		/* 실매출 조회 */
		int	actualSalesAmt	=	resultAmt - currentDayRefundAmt - beforeDayRefundAmt;
		model.addAttribute("actualSalesAmt", actualSalesAmt);


		/* 페이징 처리 */
		model.addAttribute("pages", vo);
		
		return "jsonView";
	}
	
	/***
	 * 티켓판매관리(팝업)
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckSlsPopup.do")
	public String tckSlsPopup(Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);                                                           
		HttpSession ses = request.getSession(true);

		model.addAttribute("mParam", params.getMap());
		
		String orderNo = params.get("orderNo");
		
		/* 조회 */
		if(!"".equals(orderNo) && orderNo != "" && !orderNo.isEmpty()) {
			model.addAttribute("result", tckSlsService.searchTckSls(params));
		}

		return "mngr/tckMng/popup/tckSlsPopup";
	}

	/***
	 * 이전취소 목록 조회(팝업)
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/beforeRefundListPopup.do")
	public String beforeRefundListPopup(Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);
		HttpSession ses = request.getSession(true);

		model.addAttribute("mParam", params.getMap());

        model.addAttribute("result", tckSlsService.beforeRefundList(params));


		return "mngr/tckMng/popup/beforeRefundListPopup";
	}

	/**
	 * 검색조건을 위한 시설목록 조회
	 * @param vo
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchFcltList.ajax")
	public String searchFcltList( Model model, HttpServletRequest request) throws Exception {
		HttpSession ses = request.getSession(true);

		model.addAttribute("searchFcltList", tckSlsService.searchFcltList());

		return "jsonView";
	}


	/**
	 * 시설에 해당하는 티켓조회
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchFcltTckList.ajax")
	public String	searchFcltTckList (@ModelAttribute("TckMngVO") TckMngVO vo, Model model, HttpServletRequest request) throws Exception {
		HttpSession ses = request.getSession(true);

		DataClass tckParams = new DataClass(request);
		tckParams.set("fcltSeq",	vo.getFcltSeq());

		model.addAttribute("searchFcltTckList", tckSlsService.searchFcltTckList(tckParams));

		return "jsonView";
	}




}
