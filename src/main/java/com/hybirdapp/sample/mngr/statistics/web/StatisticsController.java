package com.hybirdapp.sample.mngr.statistics.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hybirdapp.sample.cmmn.fileUtil.MakeExcelManager;
import com.hybirdapp.sample.mngr.statistics.service.StatisticsService;
import com.hybirdapp.sample.mngr.statistics.service.StatisticsVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnController;

import egovframework.rte.psl.dataaccess.util.EgovMap;


@Controller
@RequestMapping("/mngr/statistics")
public class StatisticsController extends CmmnController{
	
	
	@Resource(name = "StatisticsService")
	private StatisticsService statisticsService;
	
	/***
	 * 신규 회원 통계
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/statisticsView.do")
	public String statisticsView(@ModelAttribute("StatisticsVO") StatisticsVO vo, Model model) throws Exception {
		return "mngr:/statistics/statisticsView";
	}
	
	/***
	 * 일 회원 통계
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/dayStatisticsList.ajax")
	public String dayStatisticsList(@ModelAttribute("StatisticsVO") StatisticsVO vo, Model model) throws Exception {
		
		/* 목록 조회 */
		if("month".equals(vo.getSearchType())) {
			model.addAttribute("result", statisticsService.monthStatisticsList(vo));
		}else {
			model.addAttribute("result", statisticsService.dayStatisticsList(vo));
		}
		
		model.addAttribute("pages", vo);
		return "jsonView";
	}
	
	/***
	 * 일 통계 다운로드
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mberDaySttusDwonload.do")
	public void mberDaySttusDwonload(@ModelAttribute("StatisticsVO") StatisticsVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		/* 조회 */
		Map<String, Object> excelMap = new HashMap<String, Object>();
		List<StatisticsVO> sttusList = statisticsService.dayStatisticsList(vo);
		
		List<EgovMap> mberList = new ArrayList<EgovMap>();
		for (int i = 0 ; i < sttusList.size() ; i++) {
			EgovMap egovMap = new EgovMap();
			egovMap.put("statDay", sttusList.get(i).getStatDay());
			egovMap.put("appMber", sttusList.get(i).getAppMber());
			egovMap.put("appMberGre", sttusList.get(i).getAppMberGre());
			egovMap.put("appMberRed", sttusList.get(i).getAppMberRed());
			egovMap.put("appMberSecsn", sttusList.get(i).getAppMberSecsn());
			egovMap.put("appTotal", sttusList.get(i).getAppTotal());
			egovMap.put("webMber", sttusList.get(i).getWebMber());
			egovMap.put("webMberGre", sttusList.get(i).getWebMberGre());
			egovMap.put("webMberRed", sttusList.get(i).getWebMberRed());
			egovMap.put("webMberSecsn", sttusList.get(i).getWebMberSecsn());
			egovMap.put("webTotal", sttusList.get(i).getWebTotal());
			egovMap.put("grandTotal", sttusList.get(i).getGrandTotal());
			mberList.add(egovMap);
		}
		
		excelMap.put("template", mberList);
		MakeExcelManager.downloadBig(request, response, excelMap, "/sttus/sttus_day_templete.xlsx", "일 회원 통계");
	}
	
	/***
	 * 월 통계 다운로드
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mberMonthSttusDwonload.do")
	public void mberMonthSttusDwonload(@ModelAttribute("StatisticsVO") StatisticsVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		/* 조회 */
		Map<String, Object> excelMap = new HashMap<String, Object>();
		List<Map<String, Object>> sttusList = statisticsService.monthStatisticsList(vo);
		List<EgovMap> mberList = new ArrayList<EgovMap>();
		for (int i = 0 ; i < sttusList.size() ; i++) {
			EgovMap egovMap = new EgovMap();
			egovMap.put("statMonth", sttusList.get(i).get("STATMONTH"));
			egovMap.put("appMber", sttusList.get(i).get("APPMBER"));
			egovMap.put("appMberGre", sttusList.get(i).get("APPMBERGRE"));
			egovMap.put("appMberRed", sttusList.get(i).get("APPMBERRED"));
			egovMap.put("appMberSecsn", sttusList.get(i).get("APPMBERSECSN"));
			egovMap.put("appTotal", sttusList.get(i).get("APPTOTAL"));
			egovMap.put("webMber", sttusList.get(i).get("WEBMBER"));
			egovMap.put("webMberGre", sttusList.get(i).get("WEBMBERGRE"));
			egovMap.put("webMberRed", sttusList.get(i).get("WEBMBERRED"));
			egovMap.put("webMberSecsn", sttusList.get(i).get("WEBMBERSECSN"));
			egovMap.put("webTotal", sttusList.get(i).get("WEBTOTAL"));
			egovMap.put("grandTotal", sttusList.get(i).get("GRANDTOTAL"));
			mberList.add(egovMap);
		}
		excelMap.put("template", mberList);
		MakeExcelManager.downloadBig(request, response, excelMap, "/sttus/sttus_month_templete.xlsx", "월 회원 통계");
	}
	
	/***
	 * 월 통계 다운로드
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mberYearSttusDwonload.do")
	public void mberYearSttusDwonload(@ModelAttribute("StatisticsVO") StatisticsVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		/* 조회 */
		Map<String, Object> excelMap = new HashMap<String, Object>();
		List<StatisticsVO> sttusList = statisticsService.dayStatisticsList(vo);
		
		List<EgovMap> mberList = new ArrayList<EgovMap>();
		for (int i = 0 ; i < sttusList.size() ; i++) {
			EgovMap egovMap = new EgovMap();
			egovMap.put("statYear", sttusList.get(i).getStatYear());
			egovMap.put("appMber", sttusList.get(i).getAppMber());
			egovMap.put("appMberGre", sttusList.get(i).getAppMberGre());
			egovMap.put("appMberRed", sttusList.get(i).getAppMberRed());
			egovMap.put("appMberSecsn", sttusList.get(i).getAppMberSecsn());
			egovMap.put("appTotal", sttusList.get(i).getAppTotal());
			egovMap.put("webMber", sttusList.get(i).getWebMber());
			egovMap.put("webMberGre", sttusList.get(i).getWebMberGre());
			egovMap.put("webMberRed", sttusList.get(i).getWebMberRed());
			egovMap.put("webMberSecsn", sttusList.get(i).getWebMberSecsn());
			egovMap.put("webTotal", sttusList.get(i).getWebTotal());
			egovMap.put("grandTotal", sttusList.get(i).getGrandTotal());
			mberList.add(egovMap);
		}
		
		excelMap.put("template", mberList);
		MakeExcelManager.downloadBig(request, response, excelMap, "/sttus/sttus_year_templete.xlsx", "연도 회원 통계");
	}
	
	/***
	 * 신규 회원 통계
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/statisticsAccmltView.do")
	public String statisticsAccmltView(@ModelAttribute("StatisticsVO") StatisticsVO vo, Model model) throws Exception {
		return "mngr:/statistics/statisticsAccmltView";
	}
	
	/***
	 * 일 회원 통계
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/dayStatisticsAccmltList.ajax")
	public String dayStatisticsAccmltList(@ModelAttribute("StatisticsVO") StatisticsVO vo, Model model) throws Exception {
		
		/* 목록 조회 */
		if("month".equals(vo.getSearchType())) {
			model.addAttribute("result", statisticsService.monthStatisticsAccmltList(vo));
		}else if("day".equals(vo.getSearchType())){
			model.addAttribute("result", statisticsService.dayStatisticsAccmltList(vo));
		}else {
			model.addAttribute("result", statisticsService.yearStatisticsAccmltList(vo));
		}
		
		model.addAttribute("pages", vo);
		return "jsonView";
	}
	
	/***
	 * 일 누적 통계 다운로드
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mberDayAccmltSttusDwonload.do")
	public void mberDayAccmltSttusDwonload(@ModelAttribute("StatisticsVO") StatisticsVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		/* 조회 */
		Map<String, Object> excelMap = new HashMap<String, Object>();
		List<Map<String, Object>> sttusList = statisticsService.dayStatisticsAccmltList(vo);
		List<EgovMap> mberList = new ArrayList<EgovMap>();
		for (int i = 0 ; i < sttusList.size() ; i++) {
			EgovMap egovMap = new EgovMap();
			egovMap.put("statDay", sttusList.get(i).get("STATDAY"));
			egovMap.put("appMber", sttusList.get(i).get("APPMBER"));
			egovMap.put("appMberGre", sttusList.get(i).get("APPMBERGRE"));
			egovMap.put("appMberRed", sttusList.get(i).get("APPMBERRED"));
			egovMap.put("appMberSecsn", sttusList.get(i).get("APPMBERSECSN"));
			egovMap.put("appTotal", sttusList.get(i).get("APPTOTAL"));
			egovMap.put("webMber", sttusList.get(i).get("WEBMBER"));
			egovMap.put("webMberGre", sttusList.get(i).get("WEBMBERGRE"));
			egovMap.put("webMberRed", sttusList.get(i).get("WEBMBERRED"));
			egovMap.put("webMberSecsn", sttusList.get(i).get("WEBMBERSECSN"));
			egovMap.put("webTotal", sttusList.get(i).get("WEBTOTAL"));
			egovMap.put("grandTotal", sttusList.get(i).get("GRANDTOTAL"));
			mberList.add(egovMap);
		}
		excelMap.put("template", mberList);
		MakeExcelManager.downloadBig(request, response, excelMap, "/sttus/sttus_day_templete.xlsx", "일 누적 회원 통계");
	}
	
	/***
	 * 월 누적 통계 다운로드
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mberMonthAccmltSttusDwonload.do")
	public void mberMonthAccmltSttusDwonload(@ModelAttribute("StatisticsVO") StatisticsVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		/* 조회 */
		Map<String, Object> excelMap = new HashMap<String, Object>();
		List<Map<String, Object>> sttusList = statisticsService.monthStatisticsAccmltList(vo);
		List<EgovMap> mberList = new ArrayList<EgovMap>();
		for (int i = 0 ; i < sttusList.size() ; i++) {
			EgovMap egovMap = new EgovMap();
			egovMap.put("statMonth", sttusList.get(i).get("STATMONTH"));
			egovMap.put("appMber", sttusList.get(i).get("APPMBER"));
			egovMap.put("appMberGre", sttusList.get(i).get("APPMBERGRE"));
			egovMap.put("appMberRed", sttusList.get(i).get("APPMBERRED"));
			egovMap.put("appMberSecsn", sttusList.get(i).get("APPMBERSECSN"));
			egovMap.put("appTotal", sttusList.get(i).get("APPTOTAL"));
			egovMap.put("webMber", sttusList.get(i).get("WEBMBER"));
			egovMap.put("webMberGre", sttusList.get(i).get("WEBMBERGRE"));
			egovMap.put("webMberRed", sttusList.get(i).get("WEBMBERRED"));
			egovMap.put("webMberSecsn", sttusList.get(i).get("WEBMBERSECSN"));
			egovMap.put("webTotal", sttusList.get(i).get("WEBTOTAL"));
			egovMap.put("grandTotal", sttusList.get(i).get("GRANDTOTAL"));
			mberList.add(egovMap);
		}
		excelMap.put("template", mberList);
		MakeExcelManager.downloadBig(request, response, excelMap, "/sttus/sttus_month_templete.xlsx", "월 누적 회원 통계");
	}
	
	/***
	 * 연도 누적 통계 다운로드
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mberYearAccmltSttusDwonload.do")
	public void mberYearAccmltSttusDwonload(@ModelAttribute("StatisticsVO") StatisticsVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		/* 조회 */
		Map<String, Object> excelMap = new HashMap<String, Object>();
		List<Map<String, Object>> sttusList = statisticsService.yearStatisticsAccmltList(vo);
		List<EgovMap> mberList = new ArrayList<EgovMap>();
		for (int i = 0 ; i < sttusList.size() ; i++) {
			EgovMap egovMap = new EgovMap();
			egovMap.put("statYear", sttusList.get(i).get("STATYEAR"));
			egovMap.put("appMber", sttusList.get(i).get("APPMBER"));
			egovMap.put("appMberGre", sttusList.get(i).get("APPMBERGRE"));
			egovMap.put("appMberRed", sttusList.get(i).get("APPMBERRED"));
			egovMap.put("appMberSecsn", sttusList.get(i).get("APPMBERSECSN"));
			egovMap.put("appTotal", sttusList.get(i).get("APPTOTAL"));
			egovMap.put("webMber", sttusList.get(i).get("WEBMBER"));
			egovMap.put("webMberGre", sttusList.get(i).get("WEBMBERGRE"));
			egovMap.put("webMberRed", sttusList.get(i).get("WEBMBERRED"));
			egovMap.put("webMberSecsn", sttusList.get(i).get("WEBMBERSECSN"));
			egovMap.put("webTotal", sttusList.get(i).get("WEBTOTAL"));
			egovMap.put("grandTotal", sttusList.get(i).get("GRANDTOTAL"));
			mberList.add(egovMap);
		}
		excelMap.put("template", mberList);
		MakeExcelManager.downloadBig(request, response, excelMap, "/sttus/sttus_year_templete.xlsx", "연도 누적 회원 통계");
	}
	
}
