package com.hybirdapp.sample.mngr.statistics.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.StringUtil;
import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.mngr.statistics.service.StatisticsService;
import com.hybirdapp.sample.mngr.statistics.service.StatisticsVO;

@Service("StatisticsService")
public class StatisticsServiceImpl implements StatisticsService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "StatisticsDAO")
	private StatisticsDAO statisticsDAO;
	
	@Override
	public List<StatisticsVO> dayStatisticsList(StatisticsVO vo) throws Exception {
		List<StatisticsVO> result = null;
		
		try {
			if("day".equals(vo.getSearchType())) {
				result = statisticsDAO.dayStatisticsList(vo);
			}else {
				result = statisticsDAO.yearStatisticsList(vo);
			}
			logger.info("############### "+result);
		} catch (Exception e) {
			logger.error("searchMberManageList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}


	@Override
	public List<Map<String, Object>> monthStatisticsList(StatisticsVO vo) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		SimpleDateFormat Format = new SimpleDateFormat("yyyyMMdd");
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		Map<String, Object> resultMonth = new HashMap<String, Object>();
		try {
			Date fromDt = Format.parse(vo.getSearchFromDt());
			Date toDt = Format.parse(vo.getSearchToDt());
			from.set(Calendar.YEAR, fromDt.getYear()+1900);
			from.set(Calendar.MONTH, fromDt.getMonth());
			from.set(Calendar.DATE, fromDt.getDate());
			
			to.set(Calendar.YEAR, toDt.getYear()+1900);
			to.set(Calendar.MONTH, toDt.getMonth());
			to.set(Calendar.DATE, toDt.getDate());
			
			int month1 = toDt.getYear() * 12 + toDt.getMonth();
			int month2 = fromDt.getYear() * 12 + fromDt.getMonth();
			int monthInt = Math.abs(month2 - month1);
			
			String fromFormat = Format.format(from.getTime());
			String toFormat = Format.format(toDt.getTime());
			//월차이 1
			if(monthInt == 0) {
				vo.setSearchMonth(fromFormat.substring(0,6));
				vo.setSearchFromDt(fromFormat.substring(0,6)+"01");
				vo.setSearchToDt(fromFormat.substring(0,6)+from.getActualMaximum(Calendar.DAY_OF_MONTH));
				resultMonth = statisticsDAO.monthStatisticsList(vo);
				if(StringUtil.mapIsEmpty(resultMonth)) {
					Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
					resultMonthEmpty.put("STATMONTH", vo.getSearchMonth());
					resultMonthEmpty.put("APPMBER", 0);
					resultMonthEmpty.put("APPMBERRED", 0);
					resultMonthEmpty.put("APPMBERGRE", 0);
					resultMonthEmpty.put("APPMBERSECSN", 0);
					resultMonthEmpty.put("APPTOTAL", 0);
					resultMonthEmpty.put("WEBMBER", 0);
					resultMonthEmpty.put("WEBMBERRED", 0);
					resultMonthEmpty.put("WEBMBERGRE", 0);
					resultMonthEmpty.put("WEBMBERSECSN", 0);
					resultMonthEmpty.put("WEBTOTAL", 0);
					resultMonthEmpty.put("GRANDTOTAL", 0);
					resultMonth=resultMonthEmpty;
				}
				result.add(resultMonth);
			}else { //월차이 1이상
				for(int i = 0 ; i < monthInt; i++ ) {
					if(i == 0) {
						vo.setSearchMonth(fromFormat.substring(0,6));
						vo.setSearchFromDt(fromFormat.substring(0,6)+"01");
						vo.setSearchToDt(fromFormat.substring(0,6)+from.getActualMaximum(Calendar.DAY_OF_MONTH));
						resultMonth = statisticsDAO.monthStatisticsList(vo);
						if(StringUtil.mapIsEmpty(resultMonth)) {
							Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
							resultMonthEmpty.put("STATMONTH", vo.getSearchMonth());
							resultMonthEmpty.put("APPMBER", 0);
							resultMonthEmpty.put("APPMBERRED", 0);
							resultMonthEmpty.put("APPMBERGRE", 0);
							resultMonthEmpty.put("APPMBERSECSN", 0);
							resultMonthEmpty.put("APPTOTAL", 0);
							resultMonthEmpty.put("WEBMBER", 0);
							resultMonthEmpty.put("WEBMBERRED", 0);
							resultMonthEmpty.put("WEBMBERGRE", 0);
							resultMonthEmpty.put("WEBMBERSECSN", 0);
							resultMonthEmpty.put("WEBTOTAL", 0);
							resultMonthEmpty.put("GRANDTOTAL", 0);
							resultMonth=resultMonthEmpty;
						}
						result.add(resultMonth);
					}else {
						from.add(Calendar.MONTH, 1);
						Format.format(from.getTime());
						vo.setSearchMonth(Format.format(from.getTime()).substring(0,6));
						vo.setSearchFromDt(Format.format(from.getTime()).substring(0,6)+"01");
						vo.setSearchToDt(Format.format(from.getTime()).substring(0,6)+from.getActualMaximum(Calendar.DAY_OF_MONTH));
						resultMonth = statisticsDAO.monthStatisticsList(vo);
						if(StringUtil.mapIsEmpty(resultMonth)) {
							Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
							resultMonthEmpty.put("STATMONTH", vo.getSearchMonth());
							resultMonthEmpty.put("APPMBER", 0);
							resultMonthEmpty.put("APPMBERRED", 0);
							resultMonthEmpty.put("APPMBERGRE", 0);
							resultMonthEmpty.put("APPMBERSECSN", 0);
							resultMonthEmpty.put("APPTOTAL", 0);
							resultMonthEmpty.put("WEBMBER", 0);
							resultMonthEmpty.put("WEBMBERRED", 0);
							resultMonthEmpty.put("WEBMBERGRE", 0);
							resultMonthEmpty.put("WEBMBERSECSN", 0);
							resultMonthEmpty.put("WEBTOTAL", 0);
							resultMonthEmpty.put("GRANDTOTAL", 0);
							resultMonth=resultMonthEmpty;
						}
						result.add(resultMonth);
					}
				}
				//마지막월 조회
				vo.setSearchMonth(toFormat.substring(0,6));
				vo.setSearchFromDt(toFormat.substring(0,6)+"01");
				vo.setSearchToDt(toFormat.substring(0,6)+to.getActualMaximum(Calendar.DAY_OF_MONTH));
				resultMonth = statisticsDAO.monthStatisticsList(vo);
				if(StringUtil.mapIsEmpty(resultMonth)) {
					Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
					resultMonthEmpty.put("STATMONTH", vo.getSearchMonth());
					resultMonthEmpty.put("APPMBER", 0);
					resultMonthEmpty.put("APPMBERRED", 0);
					resultMonthEmpty.put("APPMBERGRE", 0);
					resultMonthEmpty.put("APPMBERSECSN", 0);
					resultMonthEmpty.put("APPTOTAL", 0);
					resultMonthEmpty.put("WEBMBER", 0);
					resultMonthEmpty.put("WEBMBERRED", 0);
					resultMonthEmpty.put("WEBMBERGRE", 0);
					resultMonthEmpty.put("WEBMBERSECSN", 0);
					resultMonthEmpty.put("WEBTOTAL", 0);
					resultMonthEmpty.put("GRANDTOTAL", 0);
					resultMonth=resultMonthEmpty;
				}
				result.add(resultMonth);
			}
			
		} catch (Exception e) {
			logger.error("monthStatisticsList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	
	
	@Override
	public List<Map<String, Object>> dayStatisticsAccmltList(StatisticsVO vo) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		SimpleDateFormat Format = new SimpleDateFormat("yyyyMMdd");
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		Map<String, Object> resultDay = new HashMap<String, Object>();
		try {
			Date fromDt = Format.parse(vo.getSearchFromDt());
			Date toDt = Format.parse(vo.getSearchToDt());
			from.set(Calendar.YEAR, fromDt.getYear()+1900);
			from.set(Calendar.MONTH, fromDt.getMonth());
			from.set(Calendar.DATE, fromDt.getDate());
			
			to.set(Calendar.YEAR, toDt.getYear()+1900);
			to.set(Calendar.MONTH, toDt.getMonth());
			to.set(Calendar.DATE, toDt.getDate());
			
			long diffSec = (from.getTimeInMillis() - to.getTimeInMillis())/1000;
	        
	        long diffDays = Math.abs(diffSec / (24*60*60));
			
			String fromFormat = Format.format(from.getTime());
			String toFormat = Format.format(toDt.getTime());
			//일차이 없을대
			if(diffDays == 0) {
				vo.setSearchDay(fromFormat);
				vo.setDiffDayDt(fromFormat);
				resultDay = statisticsDAO.dayStatisticsAccmltList(vo);
				if(resultDay.get("GRANDTOTAL") == null) {
					Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
					resultMonthEmpty.put("STATDAY", vo.getSearchDay());
					resultMonthEmpty.put("APPMBER", 0);
					resultMonthEmpty.put("APPMBERRED", 0);
					resultMonthEmpty.put("APPMBERGRE", 0);
					resultMonthEmpty.put("APPMBERSECSN", 0);
					resultMonthEmpty.put("APPTOTAL", 0);
					resultMonthEmpty.put("WEBMBER", 0);
					resultMonthEmpty.put("WEBMBERRED", 0);
					resultMonthEmpty.put("WEBMBERGRE", 0);
					resultMonthEmpty.put("WEBMBERSECSN", 0);
					resultMonthEmpty.put("WEBTOTAL", 0);
					resultMonthEmpty.put("GRANDTOTAL", 0);
					resultDay=resultMonthEmpty;
				}
				result.add(resultDay);
			}else { //일차이 1이상
				for(int i = 0 ; i < diffDays; i++ ) {
					if(i == 0) {
						vo.setSearchDay(fromFormat);
						vo.setDiffDayDt(fromFormat);
						logger.info("########################## >> Format.format(from.getTime())000000000000000 "+Format.format(from.getTime()));
						resultDay = statisticsDAO.dayStatisticsAccmltList(vo);
						if(resultDay.get("GRANDTOTAL") == null) {
							Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
							resultMonthEmpty.put("STATDAY", vo.getSearchDay());
							resultMonthEmpty.put("APPMBER", 0);
							resultMonthEmpty.put("APPMBERRED", 0);
							resultMonthEmpty.put("APPMBERGRE", 0);
							resultMonthEmpty.put("APPMBERSECSN", 0);
							resultMonthEmpty.put("APPTOTAL", 0);
							resultMonthEmpty.put("WEBMBER", 0);
							resultMonthEmpty.put("WEBMBERRED", 0);
							resultMonthEmpty.put("WEBMBERGRE", 0);
							resultMonthEmpty.put("WEBMBERSECSN", 0);
							resultMonthEmpty.put("WEBTOTAL", 0);
							resultMonthEmpty.put("GRANDTOTAL", 0);
							resultDay=resultMonthEmpty;
						}
						result.add(resultDay);
					}else {
						from.add(Calendar.DATE, 1);
						fromFormat = Format.format(from.getTime());
						logger.info("########################## >> Format.format(from.getTime()) "+Format.format(from.getTime()));
						vo.setSearchDay(fromFormat);
						vo.setDiffDayDt(fromFormat);
						resultDay = statisticsDAO.dayStatisticsAccmltList(vo);
						if(resultDay.get("GRANDTOTAL") == null) {
							Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
							resultMonthEmpty.put("STATDAY", vo.getSearchDay());
							resultMonthEmpty.put("APPMBER", 0);
							resultMonthEmpty.put("APPMBERRED", 0);
							resultMonthEmpty.put("APPMBERGRE", 0);
							resultMonthEmpty.put("APPMBERSECSN", 0);
							resultMonthEmpty.put("APPTOTAL", 0);
							resultMonthEmpty.put("WEBMBER", 0);
							resultMonthEmpty.put("WEBMBERRED", 0);
							resultMonthEmpty.put("WEBMBERGRE", 0);
							resultMonthEmpty.put("WEBMBERSECSN", 0);
							resultMonthEmpty.put("WEBTOTAL", 0);
							resultMonthEmpty.put("GRANDTOTAL", 0);
							resultDay=resultMonthEmpty;
						}
						result.add(resultDay);
					}
				}
				vo.setSearchDay(toFormat);
				vo.setDiffDayDt(toFormat);
				resultDay = statisticsDAO.dayStatisticsAccmltList(vo);
				if(resultDay.get("GRANDTOTAL") == null) {
					Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
					resultMonthEmpty.put("STATDAY", vo.getSearchDay());
					resultMonthEmpty.put("APPMBER", 0);
					resultMonthEmpty.put("APPMBERRED", 0);
					resultMonthEmpty.put("APPMBERGRE", 0);
					resultMonthEmpty.put("APPMBERSECSN", 0);
					resultMonthEmpty.put("APPTOTAL", 0);
					resultMonthEmpty.put("WEBMBER", 0);
					resultMonthEmpty.put("WEBMBERRED", 0);
					resultMonthEmpty.put("WEBMBERGRE", 0);
					resultMonthEmpty.put("WEBMBERSECSN", 0);
					resultMonthEmpty.put("WEBTOTAL", 0);
					resultMonthEmpty.put("GRANDTOTAL", 0);
					resultDay=resultMonthEmpty;
				}
				result.add(resultDay);
			}
			logger.info("############################  > result "+result);
		} catch (Exception e) {
			logger.error("monthStatisticsList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> monthStatisticsAccmltList(StatisticsVO vo) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		SimpleDateFormat Format = new SimpleDateFormat("yyyyMMdd");
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		Map<String, Object> resultMonth = new HashMap<String, Object>();
		try {
			Date fromDt = Format.parse(vo.getSearchFromDt());
			Date toDt = Format.parse(vo.getSearchToDt());
			from.set(Calendar.YEAR, fromDt.getYear()+1900);
			from.set(Calendar.MONTH, fromDt.getMonth());
			from.set(Calendar.DATE, fromDt.getDate());
			
			to.set(Calendar.YEAR, toDt.getYear()+1900);
			to.set(Calendar.MONTH, toDt.getMonth());
			to.set(Calendar.DATE, toDt.getDate());
			
			int month1 = toDt.getYear() * 12 + toDt.getMonth();
			int month2 = fromDt.getYear() * 12 + fromDt.getMonth();
			int monthInt = Math.abs(month2 - month1);
			
			String fromFormat = Format.format(from.getTime());
			String toFormat = Format.format(toDt.getTime());
			//월차이 1
			if(monthInt == 0) {
				vo.setSearchMonth(fromFormat.substring(0,6));
				vo.setSearchToDt(fromFormat.substring(0,6)+from.getActualMaximum(Calendar.DAY_OF_MONTH));
				resultMonth = statisticsDAO.monthStatisticsAccmltList(vo);
				if(StringUtil.mapIsEmpty(resultMonth)) {
					Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
					resultMonthEmpty.put("STATMONTH", vo.getSearchMonth());
					resultMonthEmpty.put("APPMBER", 0);
					resultMonthEmpty.put("APPMBERRED", 0);
					resultMonthEmpty.put("APPMBERGRE", 0);
					resultMonthEmpty.put("APPMBERSECSN", 0);
					resultMonthEmpty.put("APPTOTAL", 0);
					resultMonthEmpty.put("WEBMBER", 0);
					resultMonthEmpty.put("WEBMBERRED", 0);
					resultMonthEmpty.put("WEBMBERGRE", 0);
					resultMonthEmpty.put("WEBMBERSECSN", 0);
					resultMonthEmpty.put("WEBTOTAL", 0);
					resultMonthEmpty.put("GRANDTOTAL", 0);
					resultMonth=resultMonthEmpty;
				}
				result.add(resultMonth);
			}else { //월차이 1이상
				for(int i = 0 ; i < monthInt; i++ ) {
					if(i == 0) {
						vo.setSearchMonth(fromFormat.substring(0,6));
						vo.setSearchToDt(fromFormat.substring(0,6)+from.getActualMaximum(Calendar.DAY_OF_MONTH));
						resultMonth = statisticsDAO.monthStatisticsAccmltList(vo);
						if(StringUtil.mapIsEmpty(resultMonth)) {
							Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
							resultMonthEmpty.put("STATMONTH", vo.getSearchMonth());
							resultMonthEmpty.put("APPMBER", 0);
							resultMonthEmpty.put("APPMBERRED", 0);
							resultMonthEmpty.put("APPMBERGRE", 0);
							resultMonthEmpty.put("APPMBERSECSN", 0);
							resultMonthEmpty.put("APPTOTAL", 0);
							resultMonthEmpty.put("WEBMBER", 0);
							resultMonthEmpty.put("WEBMBERRED", 0);
							resultMonthEmpty.put("WEBMBERGRE", 0);
							resultMonthEmpty.put("WEBMBERSECSN", 0);
							resultMonthEmpty.put("WEBTOTAL", 0);
							resultMonthEmpty.put("GRANDTOTAL", 0);
							resultMonth=resultMonthEmpty;
						}
						result.add(resultMonth);
					}else {
						from.add(Calendar.MONTH, 1);
						Format.format(from.getTime());
						vo.setSearchMonth(Format.format(from.getTime()).substring(0,6));
						vo.setSearchToDt(Format.format(from.getTime()).substring(0,6)+from.getActualMaximum(Calendar.DAY_OF_MONTH));
						resultMonth = statisticsDAO.monthStatisticsAccmltList(vo);
						if(StringUtil.mapIsEmpty(resultMonth)) {
							Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
							resultMonthEmpty.put("STATMONTH", vo.getSearchMonth());
							resultMonthEmpty.put("APPMBER", 0);
							resultMonthEmpty.put("APPMBERRED", 0);
							resultMonthEmpty.put("APPMBERGRE", 0);
							resultMonthEmpty.put("APPMBERSECSN", 0);
							resultMonthEmpty.put("APPTOTAL", 0);
							resultMonthEmpty.put("WEBMBER", 0);
							resultMonthEmpty.put("WEBMBERRED", 0);
							resultMonthEmpty.put("WEBMBERGRE", 0);
							resultMonthEmpty.put("WEBMBERSECSN", 0);
							resultMonthEmpty.put("WEBTOTAL", 0);
							resultMonthEmpty.put("GRANDTOTAL", 0);
							resultMonth=resultMonthEmpty;
						}
						result.add(resultMonth);
					}
				}
				//마지막월 조회
				vo.setSearchMonth(toFormat.substring(0,6));
				vo.setSearchToDt(toFormat.substring(0,6)+to.getActualMaximum(Calendar.DAY_OF_MONTH));
				resultMonth = statisticsDAO.monthStatisticsAccmltList(vo);
				if(StringUtil.mapIsEmpty(resultMonth)) {
					Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
					resultMonthEmpty.put("STATMONTH", vo.getSearchMonth());
					resultMonthEmpty.put("APPMBER", 0);
					resultMonthEmpty.put("APPMBERRED", 0);
					resultMonthEmpty.put("APPMBERGRE", 0);
					resultMonthEmpty.put("APPMBERSECSN", 0);
					resultMonthEmpty.put("APPTOTAL", 0);
					resultMonthEmpty.put("WEBMBER", 0);
					resultMonthEmpty.put("WEBMBERRED", 0);
					resultMonthEmpty.put("WEBMBERGRE", 0);
					resultMonthEmpty.put("WEBMBERSECSN", 0);
					resultMonthEmpty.put("WEBTOTAL", 0);
					resultMonthEmpty.put("GRANDTOTAL", 0);
					resultMonth=resultMonthEmpty;
				}
				result.add(resultMonth);
			}
			logger.info("############################  > result "+result);
		} catch (Exception e) {
			logger.error("monthStatisticsAccmltList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	//연도
	@Override
	public List<Map<String, Object>> yearStatisticsAccmltList(StatisticsVO vo) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> resultYear = new HashMap<String, Object>();
		try {
			vo.getSearchYearFromDt().substring(2,4);
			vo.getSearchYearToDt().substring(2,4);
			
			int yearInt = Math.abs(Integer.parseInt(vo.getSearchYearFromDt().substring(2,4)) -  Integer.parseInt(vo.getSearchYearToDt().substring(2,4)));
			
			String lastTo = vo.getSearchYearToDt();
			if(yearInt == 0) {
				vo.setSearchYearToDt(vo.getSearchYearFromDt());
				resultYear = statisticsDAO.yearStatisticsAccmltList(vo);
				if(StringUtil.mapIsEmpty(resultYear)) {
					Map<String, Object> resultYearEmpty = new HashMap<String, Object>();
					resultYearEmpty.put("STATYEAR", vo.getSearchYearToDt());
					resultYearEmpty.put("APPMBER", 0);
					resultYearEmpty.put("APPMBERRED", 0);
					resultYearEmpty.put("APPMBERGRE", 0);
					resultYearEmpty.put("APPMBERSECSN", 0);
					resultYearEmpty.put("APPTOTAL", 0);
					resultYearEmpty.put("WEBMBER", 0);
					resultYearEmpty.put("WEBMBERRED", 0);
					resultYearEmpty.put("WEBMBERGRE", 0);
					resultYearEmpty.put("WEBMBERSECSN", 0);
					resultYearEmpty.put("WEBTOTAL", 0);
					resultYearEmpty.put("GRANDTOTAL", 0);
					resultYear=resultYearEmpty;
				}
				result.add(resultYear);
			}else { //월차이 1이상
				for(int i = 0 ; i < yearInt; i++ ) {
					if(i == 0) {
						vo.setSearchYearToDt(vo.getSearchYearFromDt());
						resultYear = statisticsDAO.yearStatisticsAccmltList(vo);
						if(StringUtil.mapIsEmpty(resultYear)) {
							Map<String, Object> resultYearEmpty = new HashMap<String, Object>();
							resultYearEmpty.put("STATYEAR", vo.getSearchYearToDt());
							resultYearEmpty.put("APPMBER", 0);
							resultYearEmpty.put("APPMBERRED", 0);
							resultYearEmpty.put("APPMBERGRE", 0);
							resultYearEmpty.put("APPMBERSECSN", 0);
							resultYearEmpty.put("APPTOTAL", 0);
							resultYearEmpty.put("WEBMBER", 0);
							resultYearEmpty.put("WEBMBERRED", 0);
							resultYearEmpty.put("WEBMBERGRE", 0);
							resultYearEmpty.put("WEBMBERSECSN", 0);
							resultYearEmpty.put("WEBTOTAL", 0);
							resultYearEmpty.put("GRANDTOTAL", 0);
							resultYear=resultYearEmpty;
						}
						result.add(resultYear);
					}else {
						int yearTo = (Integer.parseInt(vo.getSearchYearFromDt())+i);
						String yearToDt = yearTo+"";
						vo.setSearchYearToDt(yearToDt);
						resultYear = statisticsDAO.yearStatisticsAccmltList(vo);
						if(StringUtil.mapIsEmpty(resultYear)) {
							Map<String, Object> resultYearEmpty = new HashMap<String, Object>();
							resultYearEmpty.put("STATYEAR", vo.getSearchYearToDt());
							resultYearEmpty.put("APPMBER", 0);
							resultYearEmpty.put("APPMBERRED", 0);
							resultYearEmpty.put("APPMBERGRE", 0);
							resultYearEmpty.put("APPMBERSECSN", 0);
							resultYearEmpty.put("APPTOTAL", 0);
							resultYearEmpty.put("WEBMBER", 0);
							resultYearEmpty.put("WEBMBERRED", 0);
							resultYearEmpty.put("WEBMBERGRE", 0);
							resultYearEmpty.put("WEBMBERSECSN", 0);
							resultYearEmpty.put("WEBTOTAL", 0);
							resultYearEmpty.put("GRANDTOTAL", 0);
							resultYear=resultYearEmpty;
						}
						result.add(resultYear);
					}
				}
				//마지막조회
				vo.setSearchYearToDt(lastTo);
				resultYear = statisticsDAO.yearStatisticsAccmltList(vo);
				if(StringUtil.mapIsEmpty(resultYear)) {
					Map<String, Object> resultYearEmpty = new HashMap<String, Object>();
					resultYearEmpty.put("STATYEAR", vo.getSearchYearToDt());
					resultYearEmpty.put("APPMBER", 0);
					resultYearEmpty.put("APPMBERRED", 0);
					resultYearEmpty.put("APPMBERGRE", 0);
					resultYearEmpty.put("APPMBERSECSN", 0);
					resultYearEmpty.put("APPTOTAL", 0);
					resultYearEmpty.put("WEBMBER", 0);
					resultYearEmpty.put("WEBMBERRED", 0);
					resultYearEmpty.put("WEBMBERGRE", 0);
					resultYearEmpty.put("WEBMBERSECSN", 0);
					resultYearEmpty.put("WEBTOTAL", 0);
					resultYearEmpty.put("GRANDTOTAL", 0);
					resultYear=resultYearEmpty;
				}
				result.add(resultYear);
			}
			logger.info("############################  > result "+result);
		} catch (Exception e) {
			logger.error("monthStatisticsAccmltList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
}
