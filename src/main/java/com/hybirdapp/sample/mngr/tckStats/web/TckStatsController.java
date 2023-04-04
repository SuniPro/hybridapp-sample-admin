package com.hybirdapp.sample.mngr.tckStats.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hybirdapp.sample.cmmn.fileUtil.MakeExcelManager;
import com.hybirdapp.sample.mngr.tckStats.service.TckStatsService;
import com.hybirdapp.sample.mngr.tckStats.service.TckStatsVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnController;

import egovframework.rte.psl.dataaccess.util.EgovMap;


@Controller
@RequestMapping("/mngr/tckStats")
public class TckStatsController extends CmmnController{
	
	
	@Resource(name = "TckStatsService")
	private TckStatsService tckStatsService;
	
	/***
	 * 티켓매출통계
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckSelngStats.do")
	public String tckSelngStats(@ModelAttribute("StatisticsVO") TckStatsVO vo, Model model) throws Exception {
		return "mngr:/tckStats/tckSelngStats";
	}
	
	/***
	 * 티켓매출통계 상세
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckSelngStatsDetail.do")
	public String tckSelngStatsDetail(@ModelAttribute("StatisticsVO") TckStatsVO vo, Model model) throws Exception {
		model.addAttribute("mParam", vo);
		return "mngr:/tckStats/tckSelngStatsDetail";
	}
	
	
	/***
	 * 티켓매출통계
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckSelngStatsList.ajax")
	public String tckSelngStatsList(@ModelAttribute("StatisticsVO") TckStatsVO vo, Model model) throws Exception {
		
		/* 목록 조회 */
		if("day".equals(vo.getSearchType())) {
			model.addAttribute("result", tckStatsService.tckSelngStatsList(vo));
		}else if("month".equals(vo.getSearchType())) {
			model.addAttribute("result", tckStatsService.tckSelngStatsMonthList(vo));
		}else {
			model.addAttribute("result", tckStatsService.tckSelngStatsYearList(vo));
		}
		
		return "jsonView";
	}
	
	/***
	 * 티켓매출통계 상세
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckSelngStatsDetailList.ajax")
	public String tckSelngStatsDetailList(@ModelAttribute("StatisticsVO") TckStatsVO vo, Model model) throws Exception {
		
		model.addAttribute("result", tckStatsService.tckSelngStatsDetailList(vo));
		model.addAttribute("pkg", tckStatsService.tckSelngStatsDetailPkgList(vo));
		
		return "jsonView";
	}
	
	/***
	 * 티켓매출통계 다운로드
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckSelngStatsListDwonload.do")
	public void tckSelngStatsListDwonload(@ModelAttribute("StatisticsVO") TckStatsVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> excelMap = new HashMap<String, Object>();
		List<Map<String, Object>> sttusList = new ArrayList<Map<String, Object>>();
		String root = "";
		String exeName = "";
		/* 목록 조회 */
		if("day".equals(vo.getSearchType())) {
			sttusList = tckStatsService.tckSelngStatsList(vo);
			root = "/tckStats/tckSelng_day_templete.xlsx";
			exeName = "일 티켓 매출 통계";
		}else if("month".equals(vo.getSearchType())) {
			sttusList = tckStatsService.tckSelngStatsMonthList(vo);
			root = "/tckStats/tckSelng_month_templete.xlsx";
			exeName = "월 티켓 매출 통계";
		}else {
			sttusList = tckStatsService.tckSelngStatsYearList(vo);
			root = "/tckStats/tckSelng_year_templete.xlsx";
			exeName = "연도 티켓 매출 통계";
		}
		List<EgovMap> mberList = new ArrayList<EgovMap>();
		for (int i = 0 ; i < sttusList.size() ; i++) {
			EgovMap egovMap = new EgovMap();
			egovMap.put("dt", sttusList.get(i).get("DT"));
			egovMap.put("saleapp", sttusList.get(i).get("SALEAPP"));
			egovMap.put("cancelapp", sttusList.get(i).get("CANCELAPP"));
			egovMap.put("refundapp", sttusList.get(i).get("REFUNDAPP"));
			egovMap.put("totalapp", sttusList.get(i).get("TOTALAPP"));
			egovMap.put("saleonline", sttusList.get(i).get("SALEONLINE"));
			egovMap.put("cancelonline", sttusList.get(i).get("CANCELONLINE"));
			egovMap.put("refundonline", sttusList.get(i).get("REFUNDONLINE"));
			egovMap.put("totalonline", sttusList.get(i).get("TOTALONLINE"));
			egovMap.put("salepos", sttusList.get(i).get("SALEPOS"));
			egovMap.put("cancelpos", sttusList.get(i).get("CANCELPOS"));
			egovMap.put("refundpos", sttusList.get(i).get("REFUNDPOS"));
			egovMap.put("totalpos", sttusList.get(i).get("TOTALPOS"));
			egovMap.put("grandtotal", sttusList.get(i).get("GRANDTOTAL"));
			mberList.add(egovMap);
		}
		
		excelMap.put("template", mberList);
		MakeExcelManager.downloadBig(request, response, excelMap, root, exeName);
	}
	
	/***
	 * 티켓매출통계 상세 다운로드
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckSelngStatsDetailDwonload.do")
	public void tckSelngStatsDetailDwonload(@ModelAttribute("StatisticsVO") TckStatsVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> excelMap = new HashMap<String, Object>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> pkg = new ArrayList<Map<String, Object>>();
		result = tckStatsService.tckSelngStatsDetailList(vo);
		pkg = tckStatsService.tckSelngStatsDetailPkgList(vo);
				;
		List<EgovMap> list = new ArrayList<EgovMap>();
		if(!result.isEmpty()) {
			for (int i = 0 ; i < result.size() ; i++) {
				String fcltType = "";
				if((result.get(i).get("PLDESC").toString()).indexOf("카트") < 0) {
					fcltType = "액티비티";
				}else {
					fcltType = "승차권";
				}

				EgovMap egovMap = new EgovMap();
				egovMap.put("FCLTTYPE",			fcltType);
				egovMap.put("PLDESC",			result.get(i).get("PLDESC"));
				egovMap.put("ITEMTYPE",			"단일상품");
				egovMap.put("MDNAME",			result.get(i).get("MDNAME"));
				egovMap.put("ds", 				"-");
				egovMap.put("SALEAPP",			result.get(i).get("SALEAPP"));
				egovMap.put("SALEAPPGRE",		result.get(i).get("SALEAPPGRE"));
				egovMap.put("SALEAPPRED",		result.get(i).get("SALEAPPRED"));
				egovMap.put("CANCELAPP",		result.get(i).get("CANCELAPP"));
				egovMap.put("REFUNDAPP",		result.get(i).get("REFUNDAPP"));
				egovMap.put("APPSUBTOTAL",		result.get(i).get("APPSUBTOTAL"));
				egovMap.put("SALEPOS", 			result.get(i).get("SALEPOS"));
				egovMap.put("ds", 				"-");
				egovMap.put("SALEPOSGRE",		result.get(i).get("SALEPOSGRE"));
				egovMap.put("SALEPOSRED",		result.get(i).get("SALEPOSRED"));
				egovMap.put("CANCELPOS",		result.get(i).get("CANCELPOS"));
				egovMap.put("REFUNDPOS",		result.get(i).get("REFUNDPOS"));
				egovMap.put("POSSUBTOTAL",		result.get(i).get("POSSUBTOTAL"));
				egovMap.put("SALEONLINE",		result.get(i).get("SALEONLINE"));
				egovMap.put("CANCELONLINE",		result.get(i).get("CANCELONLINE"));
				egovMap.put("REFUNDONLINE",		result.get(i).get("REFUNDONLINE"));
				egovMap.put("ONLINESUBTOTAL",	result.get(i).get("ONLINESUBTOTAL"));
				egovMap.put("TOTAL", 			result.get(i).get("TOTAL"));
				list.add(egovMap);
			}
		}
		if(!pkg.isEmpty()) {
			for (int i = 0 ; i < pkg.size() ; i++) {
				EgovMap egovMap = new EgovMap();
				egovMap.put("FCLTTYPE", "-");
				egovMap.put("PLDESC", "-");
				egovMap.put("ITEMTYPE", "패키지상품");
				egovMap.put("MDNAME", pkg.get(i).get("MDNAME"));
				egovMap.put("ds", "-");
				egovMap.put("SALEAPP", pkg.get(i).get("SALEAPP"));
				egovMap.put("SALEAPPGRE", pkg.get(i).get("SALEAPPGRE"));
				egovMap.put("SALEAPPRED", pkg.get(i).get("SALEAPPRED"));
				egovMap.put("CANCELAPP", pkg.get(i).get("CANCELAPP"));
				egovMap.put("REFUNDAPP", pkg.get(i).get("REFUNDAPP"));
				egovMap.put("APPSUBTOTAL", pkg.get(i).get("APPSUBTOTAL"));
				egovMap.put("SALEPOS", pkg.get(i).get("SALEPOS"));
				egovMap.put("ds", "-");
				egovMap.put("SALEPOSGRE", pkg.get(i).get("SALEPOSGRE"));
				egovMap.put("SALEPOSRED", pkg.get(i).get("SALEPOSRED"));
				egovMap.put("CANCELPOS", pkg.get(i).get("CANCELPOS"));
				egovMap.put("REFUNDPOS", pkg.get(i).get("REFUNDPOS"));
				egovMap.put("POSSUBTOTAL", pkg.get(i).get("POSSUBTOTAL"));
				egovMap.put("SALEONLINE", pkg.get(i).get("SALEONLINE"));
				egovMap.put("CANCELONLINE", result.get(i).get("CANCELONLINE"));
				egovMap.put("REFUNDONLINE", result.get(i).get("REFUNDONLINE"));
				egovMap.put("ONLINESUBTOTAL", result.get(i).get("ONLINESUBTOTAL"));
				egovMap.put("TOTAL", pkg.get(i).get("TOTAL"));
				list.add(egovMap);
			}
		}
		
		excelMap.put("template", list);
		MakeExcelManager.downloadBig(request, response, excelMap, "/tckStats/tckSelng_detail_templete.xlsx", "티켓 매출판매 통계 상세");
	}
	
	/***
	 * 티켓판매통계
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckSaleStats.do")
	public String tckSaleStats(@ModelAttribute("StatisticsVO") TckStatsVO vo, Model model) throws Exception {
		return "mngr:/tckStats/tckSaleStats";
	}
	
	/***
	 * 티켓판매통계 상세
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckSaleStatsDetail.do")
	public String tckSaleStatsDetail(@ModelAttribute("StatisticsVO") TckStatsVO vo, Model model) throws Exception {
		model.addAttribute("mParam", vo);
		return "mngr:/tckStats/tckSaleStatsDetail";
	}
	
	/***
	 * 티켓판매통계
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckSaleStatsList.ajax")
	public String tckSaleStatsList(@ModelAttribute("StatisticsVO") TckStatsVO vo, Model model) throws Exception {
		
		/* 목록 조회 */
		if("day".equals(vo.getSearchType())) {
			model.addAttribute("result", tckStatsService.tckSaleStatsList(vo));
		}else if("month".equals(vo.getSearchType())) {
			model.addAttribute("result", tckStatsService.tckSaleStatsMonthList(vo));
		}else {
			model.addAttribute("result", tckStatsService.tckSaleStatsYearList(vo));
		}
		
		return "jsonView";
	}
	
	/***
	 * 티켓판매통계 상세
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckSaleStatsDetailList.ajax")
	public String tckSaleStatsDetailList(@ModelAttribute("StatisticsVO") TckStatsVO vo, Model model) throws Exception {
		
		model.addAttribute("result", tckStatsService.tckSaleStatsDetailList(vo));
		model.addAttribute("pkg", tckStatsService.tckSaleStatsDetailPkgList(vo));
		model.addAttribute("total", tckStatsService.tckSaleStatsDetailListTotal(vo));
		
		return "jsonView";
	}
	
	/***
	 * 티켓판매통계 다운로드
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckSaleStatsListDwonload.do")
	public void tckSaleStatsListDwonload(@ModelAttribute("StatisticsVO") TckStatsVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> excelMap = new HashMap<String, Object>();
		List<Map<String, Object>> sttusList = new ArrayList<Map<String, Object>>();
		String root = "";
		String exeName = "";
		/* 목록 조회 */
		if("day".equals(vo.getSearchType())) {
			sttusList = tckStatsService.tckSaleStatsList(vo);
			root = "/tckStats/tckSale_day_templete.xlsx";
			exeName = "일 티켓 판매통계";
		}else if("month".equals(vo.getSearchType())) {
			sttusList = tckStatsService.tckSaleStatsMonthList(vo);
			root = "/tckStats/tckSale_month_templete.xlsx";
			exeName = "월 티켓 판매통계";
		}else {
			sttusList = tckStatsService.tckSaleStatsYearList(vo);
			root = "/tckStats/tckSale_year_templete.xlsx";
			exeName = "연도 티켓 판매통계";
		}
		List<EgovMap> mberList = new ArrayList<EgovMap>();
		for (int i = 0 ; i < sttusList.size() ; i++) {
			EgovMap egovMap = new EgovMap();
			egovMap.put("dt", sttusList.get(i).get("DT"));
			egovMap.put("saleapp", sttusList.get(i).get("SALEAPP"));
			egovMap.put("useapp", sttusList.get(i).get("USEAPP"));
			egovMap.put("cancelapp", sttusList.get(i).get("CANCELAPP"));
			egovMap.put("refundapp", sttusList.get(i).get("REFUNDAPP"));
			egovMap.put("saleonline", sttusList.get(i).get("SALEONLINE"));
			egovMap.put("useonline", sttusList.get(i).get("USEONLINE"));
			egovMap.put("cancelonline", sttusList.get(i).get("CANCELONLINE"));
			egovMap.put("refundonline", sttusList.get(i).get("REFUNDONLINE"));
			egovMap.put("salepos", sttusList.get(i).get("SALEPOS"));
			egovMap.put("usepos", sttusList.get(i).get("USEPOS"));
			egovMap.put("cancelpos", sttusList.get(i).get("CANCELPOS"));
			egovMap.put("refundpos", sttusList.get(i).get("REFUNDPOS"));
			egovMap.put("saletotal", sttusList.get(i).get("SALETOTAL"));
			egovMap.put("usetotal", sttusList.get(i).get("USETOTAL"));
			egovMap.put("canceletotal", sttusList.get(i).get("CANCELETOTAL"));
			egovMap.put("refundtotal", sttusList.get(i).get("REFUNDTOTAL"));
			mberList.add(egovMap);
		}
		
		excelMap.put("template", mberList);
		MakeExcelManager.downloadBig(request, response, excelMap, root, exeName);
	}
	
	/***
	 * 티켓판매통계 상세 다운로드
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckSaleStatsDetailDwonload.do")
	public void tckSaleStatsDetailDwonload(@ModelAttribute("StatisticsVO") TckStatsVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> excelMap = new HashMap<String, Object>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> pkg = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> total = new ArrayList<Map<String, Object>>();
		
		result = tckStatsService.tckSaleStatsDetailList(vo);
		pkg = tckStatsService.tckSaleStatsDetailPkgList(vo);
		total = tckStatsService.tckSaleStatsDetailListTotal(vo);
		/* 목록 조회 */
		List<EgovMap> list = new ArrayList<EgovMap>();
		if(!result.isEmpty()) {
			for (int i = 0 ; i < result.size() ; i++) {
				String fcltType = "";
				if((result.get(i).get("PLDESC").toString()).indexOf("카트") < 0) {
					fcltType = "액티비티";
				}else {
					fcltType = "승차권";
				}
				EgovMap egovMap = new EgovMap();
				if(!"-".equals(result.get(i).get("PLDESC").toString())) {
					egovMap.put("FCLTTYPE", fcltType);
					egovMap.put("PLDESC", result.get(i).get("PLDESC"));
					egovMap.put("ITEMTYPE", "단일상품");
					egovMap.put("MDNAME", result.get(i).get("MDNAME"));
					egovMap.put("WEEKFLAGTEXT", result.get(i).get("WEEKFLAGTEXT"));
					egovMap.put("MTICKETQT", result.get(i).get("MTICKETQT"));
					egovMap.put("ds", "-");
					egovMap.put("ds", "-");
					egovMap.put("ds", "-");
					egovMap.put("ds", "-");
					egovMap.put("SALEAPP", result.get(i).get("SALEAPP"));
					egovMap.put("USEAPP",  result.get(i).get("USEAPP"));
					egovMap.put("CANCELAPP", result.get(i).get("CANCELAPP"));
					egovMap.put("REFUNDAPP", result.get(i).get("REFUNDAPP"));
					egovMap.put("SALEAPPGRE", result.get(i).get("SALEAPPGRE"));
					egovMap.put("USEAPPGRE", result.get(i).get("USEAPPGRE"));
					egovMap.put("CANCELAPPGRE", result.get(i).get("CANCELAPPGRE"));
					egovMap.put("REFUNDAPPGRE", result.get(i).get("REFUNDAPPGRE"));
					egovMap.put("SALEAPPRED", result.get(i).get("SALEAPPRED"));
					egovMap.put("USEAPPRED", result.get(i).get("USEAPPRED"));
					egovMap.put("CANCELAPPRED", result.get(i).get("CANCELAPPRED"));
					egovMap.put("REFUNDAPPRED", result.get(i).get("REFUNDAPPRED"));
					egovMap.put("SALEAPPSUBTOTAL", result.get(i).get("SALEAPPSUBTOTAL"));
					egovMap.put("USEAPPSUBTOTAL", result.get(i).get("USEAPPSUBTOTAL"));
					egovMap.put("CANCELAPPSUBTOTAL", result.get(i).get("CANCELAPPSUBTOTAL"));
					egovMap.put("MTICKETQTAPPSUBTOTAL", result.get(i).get("MTICKETQTAPPSUBTOTAL"));
					egovMap.put("REFUNDAPPSUBTOTAL", result.get(i).get("REFUNDAPPSUBTOTAL"));
					egovMap.put("SALEPOS", result.get(i).get("SALEPOS"));
					egovMap.put("USEPOS", result.get(i).get("USEPOS"));
					egovMap.put("CANCELPOS", result.get(i).get("CANCELPOS"));
					egovMap.put("REFUNDPOS", result.get(i).get("REFUNDPOS"));
					egovMap.put("ds", "-");
					egovMap.put("ds", "-");
					egovMap.put("ds", "-");
					egovMap.put("ds", "-");
					egovMap.put("SALEPOSGRE", result.get(i).get("SALEPOSGRE"));
					egovMap.put("USEPOSGRE", result.get(i).get("USEPOSGRE"));
					egovMap.put("CANCELPOSGRE", result.get(i).get("CANCELPOSGRE"));
					egovMap.put("REFUNDPOSGRE", result.get(i).get("REFUNDPOSGRE"));
					egovMap.put("SALEPOSRED", result.get(i).get("SALEPOSRED"));
					egovMap.put("USEPOSRED", result.get(i).get("USEPOSRED"));
					egovMap.put("CANCELPOSRED", result.get(i).get("CANCELPOSRED"));
					egovMap.put("REFUNDPOSRED", result.get(i).get("REFUNDPOSRED"));
					egovMap.put("SALEPOSSUBTOTAL", result.get(i).get("SALEPOSSUBTOTAL"));
					egovMap.put("USEPOSSUBTOTAL", result.get(i).get("USEPOSSUBTOTAL"));
					egovMap.put("MTICKETQTPOSSUBTOTAL", result.get(i).get("MTICKETQTPOSSUBTOTAL"));
					egovMap.put("CANCELPOSSUBTOTAL", result.get(i).get("CANCELPOSSUBTOTAL"));
					egovMap.put("REFUNDPOSSUBTOTAL", result.get(i).get("REFUNDPOSSUBTOTAL"));
					egovMap.put("SALEONLINE", result.get(i).get("SALEONLINE"));
					egovMap.put("USEONLINE", result.get(i).get("USEONLINE"));
					egovMap.put("CANCELONLINE", result.get(i).get("CANCELONLINE"));
					egovMap.put("REFUNDONLINE", result.get(i).get("REFUNDONLINE"));
					egovMap.put("SALEONLINE", result.get(i).get("SALEONLINE"));
					egovMap.put("USEONLINE", result.get(i).get("USEONLINE"));
					egovMap.put("MTICKETQTONLINE", result.get(i).get("MTICKETQTONLINE"));
					egovMap.put("CANCELONLINE", result.get(i).get("CANCELONLINE"));
					egovMap.put("REFUNDONLINE", result.get(i).get("REFUNDONLINE"));
					egovMap.put("SALEGRANDTOTAL", result.get(i).get("SALEGRANDTOTAL"));
					egovMap.put("USEGRANDTOTAL", result.get(i).get("USEGRANDTOTAL"));
					egovMap.put("USEMTICKETQTGRANDTOTAL", result.get(i).get("USEMTICKETQTGRANDTOTAL"));
					egovMap.put("CANCELGRANDTOTAL", result.get(i).get("CANCELGRANDTOTAL"));
					egovMap.put("REFUNDGRANDTOTAL", result.get(i).get("REFUNDGRANDTOTAL"));
					list.add(egovMap);
				}
				
			}
		}
		if(!pkg.isEmpty()) {
			for (int i = 0 ; i < pkg.size() ; i++) {
				EgovMap egovMap = new EgovMap();
				if(!"-".equals(pkg.get(i).get("PLDESC").toString())) {
					egovMap.put("FCLTTYPE", "-");
					egovMap.put("PLDESC", "-");
					egovMap.put("ITEMTYPE", "패키지상품");
					egovMap.put("MDNAME", pkg.get(i).get("MDNAME"));
					egovMap.put("WEEKFLAGTEXT", pkg.get(i).get("WEEKFLAGTEXT"));
					egovMap.put("MTICKETQT", pkg.get(i).get("MTICKETQT"));
					egovMap.put("ds", "-");
					egovMap.put("ds", "-");
					egovMap.put("ds", "-");
					egovMap.put("ds", "-");
					egovMap.put("SALEAPP", pkg.get(i).get("SALEAPP"));
					egovMap.put("USEAPP",  pkg.get(i).get("USEAPP"));
					egovMap.put("CANCELAPP", pkg.get(i).get("CANCELAPP"));
					egovMap.put("REFUNDAPP", pkg.get(i).get("REFUNDAPP"));
					egovMap.put("SALEAPPGRE", pkg.get(i).get("SALEAPPGRE"));
					egovMap.put("USEAPPGRE", pkg.get(i).get("USEAPPGRE"));
					egovMap.put("CANCELAPPGRE", pkg.get(i).get("CANCELAPPGRE"));
					egovMap.put("REFUNDAPPGRE", pkg.get(i).get("REFUNDAPPGRE"));
					egovMap.put("SALEAPPRED", pkg.get(i).get("SALEAPPRED"));
					egovMap.put("USEAPPRED", pkg.get(i).get("USEAPPRED"));
					egovMap.put("CANCELAPPRED", pkg.get(i).get("CANCELAPPRED"));
					egovMap.put("REFUNDAPPRED", pkg.get(i).get("REFUNDAPPRED"));
					egovMap.put("SALEAPPSUBTOTAL", pkg.get(i).get("SALEAPPSUBTOTAL"));
					egovMap.put("USEAPPSUBTOTAL", pkg.get(i).get("USEAPPSUBTOTAL"));
					egovMap.put("CANCELAPPSUBTOTAL", pkg.get(i).get("CANCELAPPSUBTOTAL"));
					egovMap.put("MTICKETQTAPPSUBTOTAL", pkg.get(i).get("MTICKETQTAPPSUBTOTAL"));
					egovMap.put("REFUNDAPPSUBTOTAL", pkg.get(i).get("REFUNDAPPSUBTOTAL"));
					egovMap.put("SALEPOS", pkg.get(i).get("SALEPOS"));
					egovMap.put("USEPOS", pkg.get(i).get("USEPOS"));
					egovMap.put("CANCELPOS", pkg.get(i).get("CANCELPOS"));
					egovMap.put("REFUNDPOS", pkg.get(i).get("REFUNDPOS"));
					egovMap.put("ds", "-");
					egovMap.put("ds", "-");
					egovMap.put("ds", "-");
					egovMap.put("ds", "-");
					egovMap.put("SALEPOSGRE", pkg.get(i).get("SALEPOSGRE"));
					egovMap.put("USEPOSGRE", pkg.get(i).get("USEPOSGRE"));
					egovMap.put("CANCELPOSGRE", pkg.get(i).get("CANCELPOSGRE"));
					egovMap.put("REFUNDPOSGRE", pkg.get(i).get("REFUNDPOSGRE"));
					egovMap.put("SALEPOSRED", pkg.get(i).get("SALEPOSRED"));
					egovMap.put("USEPOSRED", pkg.get(i).get("USEPOSRED"));
					egovMap.put("CANCELPOSRED", pkg.get(i).get("CANCELPOSRED"));
					egovMap.put("REFUNDPOSRED", pkg.get(i).get("REFUNDPOSRED"));
					egovMap.put("SALEPOSSUBTOTAL", pkg.get(i).get("SALEPOSSUBTOTAL"));
					egovMap.put("USEPOSSUBTOTAL", pkg.get(i).get("USEPOSSUBTOTAL"));
					egovMap.put("MTICKETQTPOSSUBTOTAL", pkg.get(i).get("MTICKETQTPOSSUBTOTAL"));
					egovMap.put("CANCELPOSSUBTOTAL", pkg.get(i).get("CANCELPOSSUBTOTAL"));
					egovMap.put("REFUNDPOSSUBTOTAL", pkg.get(i).get("REFUNDPOSSUBTOTAL"));
					egovMap.put("SALEONLINE", pkg.get(i).get("SALEONLINE"));
					egovMap.put("USEONLINE", pkg.get(i).get("USEONLINE"));
					egovMap.put("CANCELONLINE", pkg.get(i).get("CANCELONLINE"));
					egovMap.put("REFUNDONLINE", pkg.get(i).get("REFUNDONLINE"));
					egovMap.put("SALEONLINE", pkg.get(i).get("SALEONLINE"));
					egovMap.put("USEONLINE", pkg.get(i).get("USEONLINE"));
					egovMap.put("MTICKETQTONLINE", pkg.get(i).get("MTICKETQTONLINE"));
					egovMap.put("CANCELONLINE", pkg.get(i).get("CANCELONLINE"));
					egovMap.put("REFUNDONLINE", pkg.get(i).get("REFUNDONLINE"));
					egovMap.put("SALEGRANDTOTAL", pkg.get(i).get("SALEGRANDTOTAL"));
					egovMap.put("USEGRANDTOTAL", pkg.get(i).get("USEGRANDTOTAL"));
					egovMap.put("USEMTICKETQTGRANDTOTAL", pkg.get(i).get("USEMTICKETQTGRANDTOTAL"));
					egovMap.put("CANCELGRANDTOTAL", pkg.get(i).get("CANCELGRANDTOTAL"));
					egovMap.put("REFUNDGRANDTOTAL", pkg.get(i).get("REFUNDGRANDTOTAL"));
					list.add(egovMap);
				}
				
			}
		}
		if(!total.isEmpty()) {
			for (int i = 0 ; i < total.size() ; i++) {
				EgovMap egovMap = new EgovMap();
				egovMap.put("FCLTTYPE", "Total");
				egovMap.put("PLDESC", "");
				egovMap.put("ITEMTYPE", "");
				egovMap.put("MDNAME", "");
				egovMap.put("WEEKFLAGTEXT", "");
				egovMap.put("MTICKETQT", "");
				egovMap.put("ds", "-");
				egovMap.put("ds", "-");
				egovMap.put("ds", "-");
				egovMap.put("ds", "-");
				egovMap.put("SALEAPP", total.get(i).get("SALEAPP"));
				egovMap.put("USEAPP",  total.get(i).get("USEAPP"));
				egovMap.put("CANCELAPP", total.get(i).get("CANCELAPP"));
				egovMap.put("REFUNDAPP", total.get(i).get("REFUNDAPP"));
				egovMap.put("SALEAPPGRE", total.get(i).get("SALEAPPGRE"));
				egovMap.put("USEAPPGRE", total.get(i).get("USEAPPGRE"));
				egovMap.put("CANCELAPPGRE", total.get(i).get("CANCELAPPGRE"));
				egovMap.put("REFUNDAPPGRE", total.get(i).get("REFUNDAPPGRE"));
				egovMap.put("SALEAPPRED", total.get(i).get("SALEAPPRED"));
				egovMap.put("USEAPPRED", total.get(i).get("USEAPPRED"));
				egovMap.put("CANCELAPPRED", total.get(i).get("CANCELAPPRED"));
				egovMap.put("REFUNDAPPRED", total.get(i).get("REFUNDAPPRED"));
				egovMap.put("SALEAPPSUBTOTAL", total.get(i).get("SALEAPPSUBTOTAL"));
				egovMap.put("USEAPPSUBTOTAL", total.get(i).get("USEAPPSUBTOTAL"));
				egovMap.put("CANCELAPPSUBTOTAL", total.get(i).get("CANCELAPPSUBTOTAL"));
				egovMap.put("MTICKETQTAPPSUBTOTAL", total.get(i).get("MTICKETQTAPPSUBTOTAL"));
				egovMap.put("REFUNDAPPSUBTOTAL", total.get(i).get("REFUNDAPPSUBTOTAL"));
				egovMap.put("SALEPOS", total.get(i).get("SALEPOS"));
				egovMap.put("USEPOS", total.get(i).get("USEPOS"));
				egovMap.put("CANCELPOS", total.get(i).get("CANCELPOS"));
				egovMap.put("REFUNDPOS", total.get(i).get("REFUNDPOS"));
				egovMap.put("ds", "-");
				egovMap.put("ds", "-");
				egovMap.put("ds", "-");
				egovMap.put("ds", "-");
				egovMap.put("SALEPOSGRE", total.get(i).get("SALEPOSGRE"));
				egovMap.put("USEPOSGRE", total.get(i).get("USEPOSGRE"));
				egovMap.put("CANCELPOSGRE", total.get(i).get("CANCELPOSGRE"));
				egovMap.put("REFUNDPOSGRE", total.get(i).get("REFUNDPOSGRE"));
				egovMap.put("SALEPOSRED", total.get(i).get("SALEPOSRED"));
				egovMap.put("USEPOSRED", total.get(i).get("USEPOSRED"));
				egovMap.put("CANCELPOSRED", total.get(i).get("CANCELPOSRED"));
				egovMap.put("REFUNDPOSRED", total.get(i).get("REFUNDPOSRED"));
				egovMap.put("SALEPOSSUBTOTAL", total.get(i).get("SALEPOSSUBTOTAL"));
				egovMap.put("USEPOSSUBTOTAL", total.get(i).get("USEPOSSUBTOTAL"));
				egovMap.put("MTICKETQTPOSSUBTOTAL", total.get(i).get("MTICKETQTPOSSUBTOTAL"));
				egovMap.put("CANCELPOSSUBTOTAL", total.get(i).get("CANCELPOSSUBTOTAL"));
				egovMap.put("REFUNDPOSSUBTOTAL", total.get(i).get("REFUNDPOSSUBTOTAL"));
				egovMap.put("SALEONLINE", total.get(i).get("SALEONLINE"));
				egovMap.put("USEONLINE", total.get(i).get("USEONLINE"));
				egovMap.put("CANCELONLINE", total.get(i).get("CANCELONLINE"));
				egovMap.put("REFUNDONLINE", total.get(i).get("REFUNDONLINE"));
				egovMap.put("SALEONLINE", total.get(i).get("SALEONLINE"));
				egovMap.put("USEONLINE", total.get(i).get("USEONLINE"));
				
				egovMap.put("MTICKETQTONLINE", total.get(i).get("MTICKETQTONLINE"));
				egovMap.put("CANCELONLINE", total.get(i).get("CANCELONLINE"));
				egovMap.put("REFUNDONLINE", total.get(i).get("REFUNDONLINE"));
				egovMap.put("SALEGRANDTOTAL", total.get(i).get("SALEGRANDTOTAL"));
				egovMap.put("USEGRANDTOTAL", total.get(i).get("USEGRANDTOTAL"));
				egovMap.put("USEMTICKETQTGRANDTOTAL", total.get(i).get("USEMTICKETQTGRANDTOTAL"));
				egovMap.put("CANCELGRANDTOTAL", total.get(i).get("CANCELGRANDTOTAL"));
				egovMap.put("REFUNDGRANDTOTAL", total.get(i).get("REFUNDGRANDTOTAL"));
				list.add(egovMap);
			}
		}
		logger.info("################## >> list "+list);
		excelMap.put("template", list);
		MakeExcelManager.downloadBig(request, response, excelMap, "/tckStats/tckSale_detail_templete.xlsx", "티켓판매통계 상세");
	}
	
}
