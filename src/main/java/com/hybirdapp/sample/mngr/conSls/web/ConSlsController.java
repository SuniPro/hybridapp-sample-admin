package com.hybirdapp.sample.mngr.conSls.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hybirdapp.sample.mngr.conSls.service.ConSlsService;
import com.hybirdapp.sample.mngr.conSls.service.ConSlsVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnConstant;
import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;

@Controller
@RequestMapping("/mngr/conMng")
public class ConSlsController extends CmmnController {
	
	@Resource(name = "ConSlsService")
	private ConSlsService conSlsService;
	
	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;
	
	/***
	 * 콘도 판매 관리
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/conSlsDtlView.do")
	public String conSlsDtlView(Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);
		
		model.addAttribute("mParam", params.getMap());
		
		return "mngr:/conMng/conSlsDtlView";
	}
	
	/***
	 * 콘도판매관리(팝업)
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/conSlsPopup.do")
	public String conSlsPopup(Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);                                                           
		HttpSession ses = request.getSession(true);

		model.addAttribute("mParam", params.getMap());
		
		String orderNo = params.get("orderNo");
		
		/* 조회 */
		if(!"".equals(orderNo) && orderNo != "" && !orderNo.isEmpty()) {
			model.addAttribute("result", conSlsService.searchConSls(params));
		}

		return "mngr/conMng/popup/conSlsPopup";
	}
	
	@RequestMapping(value = "/searchConSlsList.ajax")
	public String searchConMngList(@ModelAttribute("ConSlsVO") ConSlsVO vo, Model model, HttpServletRequest request) throws Exception {
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
		int totPageCnt = conSlsService.searchConSlsListCnt(params);
		vo.setTotPageCnt(totPageCnt);				//페이징
		model.addAttribute("totalCnt",totPageCnt);
		
		/* 조회 */
		model.addAttribute("result", conSlsService.searchConSlsList(params));
		
		/* 총 금액 조회 */
		int resultAmt	=	conSlsService.searchConSlsAmt(params);
		model.addAttribute("resultAmt", resultAmt);

		/* 당일환불 조회 */
		DataClass refundParams = new DataClass(request);
		refundParams.set("serStartDate",	CmmnConstant.SER_START_DATE);
		refundParams.set("currentDate",		params.get("endDate"));
		int currentDayRefundAmt	=	conSlsService.searchCurrentDayRefundAmt(refundParams);
		model.addAttribute("currentDayRefundAmt", currentDayRefundAmt);

		/* 실매출 조회 */
		int	actualSalesAmt	=	resultAmt - currentDayRefundAmt;
		model.addAttribute("actualSalesAmt", actualSalesAmt);

		/* 페이징 처리 */
		model.addAttribute("pages", vo);
		
		return "jsonView";
	}
}
