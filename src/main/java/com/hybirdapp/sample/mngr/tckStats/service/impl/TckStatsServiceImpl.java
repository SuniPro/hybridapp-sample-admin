package com.hybirdapp.sample.mngr.tckStats.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.mngr.tckStats.service.TckStatsService;
import com.hybirdapp.sample.mngr.tckStats.service.TckStatsVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.StringUtil;

@Service("TckStatsService")
public class TckStatsServiceImpl implements TckStatsService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "TckStatsDAO")
	private TckStatsDAO tckStatsDAO;
	
	@Override
	public List<Map<String, Object>> tckSaleStatsDetailList(TckStatsVO vo) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		try {
			result = tckStatsDAO.tckSaleStatsDetailList(vo);
			
			
		} catch (Exception e) {
			logger.error("tckSaleStatsDetailList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> tckSaleStatsDetailPkgList(TckStatsVO vo) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		try {
			result = tckStatsDAO.tckSaleStatsDetailPkgList(vo);
			
			
		} catch (Exception e) {
			logger.error("tckSaleStatsDetailPkgList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> tckSelngStatsDetailList(TckStatsVO vo) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		try {
			result = tckStatsDAO.tckSelngStatsDetailList(vo);
			
			
		} catch (Exception e) {
			logger.error("tckSelngStatsDetailList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> tckSelngStatsDetailPkgList(TckStatsVO vo) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		try {
			result = tckStatsDAO.tckSelngStatsDetailPkgList(vo);
			
			
		} catch (Exception e) {
			logger.error("tckSelngStatsDetailPkgList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> tckSaleStatsDetailListTotal(TckStatsVO vo) throws Exception {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> resultPkg = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> resultTotal = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		try {
			resultList = tckStatsDAO.tckSaleStatsDetailList(vo);
			resultPkg = tckStatsDAO.tckSaleStatsDetailPkgList(vo);
			if(!resultList.isEmpty()) {
				for (int i = 0 ; i < resultList.size() ; i++) {
					if("-".equals(resultList.get(i).get("PLDESC").toString())) {
						resultTotal.add(resultList.get(i));
					}
				}
			}
			if(!resultPkg.isEmpty()) {
				for (int i = 0 ; i < resultPkg.size() ; i++) {
					if("-".equals(resultPkg.get(i).get("PLDESC").toString())) {
						resultTotal.add(resultPkg.get(i));
					}
				}
			}
			if(resultTotal.size() > 1) {
				Map<String, Object> resultPlus = new HashMap<String, Object>();
				resultPlus.put("SALEAPP", Integer.parseInt(resultTotal.get(0).get("SALEAPP").toString())+Integer.parseInt(resultTotal.get(1).get("SALEAPP").toString())); 
				resultPlus.put("USEAPP", Integer.parseInt(resultTotal.get(0).get("USEAPP").toString())+Integer.parseInt(resultTotal.get(1).get("USEAPP").toString())); 
				resultPlus.put("CANCELAPP", Integer.parseInt(resultTotal.get(0).get("CANCELAPP").toString())+Integer.parseInt(resultTotal.get(1).get("CANCELAPP").toString())); 
				resultPlus.put("REFUNDAPP", Integer.parseInt(resultTotal.get(0).get("REFUNDAPP").toString())+Integer.parseInt(resultTotal.get(1).get("REFUNDAPP").toString())); 
				resultPlus.put("SALEAPPGRE", Integer.parseInt(resultTotal.get(0).get("SALEAPPGRE").toString())+Integer.parseInt(resultTotal.get(1).get("SALEAPPGRE").toString())); 
				resultPlus.put("USEAPPGRE", Integer.parseInt(resultTotal.get(0).get("USEAPPGRE").toString())+Integer.parseInt(resultTotal.get(1).get("USEAPPGRE").toString())); 
				resultPlus.put("CANCELAPPGRE", Integer.parseInt(resultTotal.get(0).get("CANCELAPPGRE").toString())+Integer.parseInt(resultTotal.get(1).get("CANCELAPPGRE").toString())); 
				resultPlus.put("REFUNDAPPGRE", Integer.parseInt(resultTotal.get(0).get("REFUNDAPPGRE").toString())+Integer.parseInt(resultTotal.get(1).get("REFUNDAPPGRE").toString())); 
				resultPlus.put("SALEAPPRED", Integer.parseInt(resultTotal.get(0).get("SALEAPPRED").toString())+Integer.parseInt(resultTotal.get(1).get("SALEAPPRED").toString())); 
				resultPlus.put("USEAPPRED", Integer.parseInt(resultTotal.get(0).get("USEAPPRED").toString())+Integer.parseInt(resultTotal.get(1).get("USEAPPRED").toString())); 
				resultPlus.put("CANCELAPPRED", Integer.parseInt(resultTotal.get(0).get("CANCELAPPRED").toString())+Integer.parseInt(resultTotal.get(1).get("CANCELAPPRED").toString())); 
				resultPlus.put("REFUNDAPPRED", Integer.parseInt(resultTotal.get(0).get("REFUNDAPPRED").toString())+Integer.parseInt(resultTotal.get(1).get("REFUNDAPPRED").toString())); 
				resultPlus.put("SALEAPPSUBTOTAL", Integer.parseInt(resultTotal.get(0).get("SALEAPPSUBTOTAL").toString())+Integer.parseInt(resultTotal.get(1).get("SALEAPPSUBTOTAL").toString())); 
				resultPlus.put("USEAPPSUBTOTAL", Integer.parseInt(resultTotal.get(0).get("USEAPPSUBTOTAL").toString())+Integer.parseInt(resultTotal.get(1).get("USEAPPSUBTOTAL").toString())); 
				resultPlus.put("CANCELAPPSUBTOTAL", Integer.parseInt(resultTotal.get(0).get("CANCELAPPSUBTOTAL").toString())+Integer.parseInt(resultTotal.get(1).get("CANCELAPPSUBTOTAL").toString())); 
				resultPlus.put("MTICKETQTAPPSUBTOTAL", Integer.parseInt(resultTotal.get(0).get("MTICKETQTAPPSUBTOTAL").toString())+Integer.parseInt(resultTotal.get(1).get("MTICKETQTAPPSUBTOTAL").toString())); 
				resultPlus.put("REFUNDAPPSUBTOTAL", Integer.parseInt(resultTotal.get(0).get("REFUNDAPPSUBTOTAL").toString())+Integer.parseInt(resultTotal.get(1).get("REFUNDAPPSUBTOTAL").toString())); 
				resultPlus.put("SALEPOS", Integer.parseInt(resultTotal.get(0).get("SALEPOS").toString())+Integer.parseInt(resultTotal.get(1).get("SALEPOS").toString())); 
				resultPlus.put("USEPOS", Integer.parseInt(resultTotal.get(0).get("USEPOS").toString())+Integer.parseInt(resultTotal.get(1).get("USEPOS").toString())); 
				resultPlus.put("CANCELPOS", Integer.parseInt(resultTotal.get(0).get("CANCELPOS").toString())+Integer.parseInt(resultTotal.get(1).get("CANCELPOS").toString())); 
				resultPlus.put("REFUNDPOS", Integer.parseInt(resultTotal.get(0).get("REFUNDPOS").toString())+Integer.parseInt(resultTotal.get(1).get("REFUNDPOS").toString())); 
				resultPlus.put("SALEPOSGRE", Integer.parseInt(resultTotal.get(0).get("SALEPOSGRE").toString())+Integer.parseInt(resultTotal.get(1).get("SALEPOSGRE").toString())); 
				resultPlus.put("USEPOSGRE", Integer.parseInt(resultTotal.get(0).get("USEPOSGRE").toString())+Integer.parseInt(resultTotal.get(1).get("USEPOSGRE").toString())); 
				resultPlus.put("CANCELPOSGRE", Integer.parseInt(resultTotal.get(0).get("CANCELPOSGRE").toString())+Integer.parseInt(resultTotal.get(1).get("CANCELPOSGRE").toString())); 
				resultPlus.put("REFUNDPOSGRE", Integer.parseInt(resultTotal.get(0).get("REFUNDPOSGRE").toString())+Integer.parseInt(resultTotal.get(1).get("REFUNDPOSGRE").toString())); 
				resultPlus.put("SALEPOSRED", Integer.parseInt(resultTotal.get(0).get("SALEPOSRED").toString())+Integer.parseInt(resultTotal.get(1).get("SALEPOSRED").toString())); 
				resultPlus.put("USEPOSRED", Integer.parseInt(resultTotal.get(0).get("USEPOSRED").toString())+Integer.parseInt(resultTotal.get(1).get("USEPOSRED").toString())); 
				resultPlus.put("CANCELPOSRED", Integer.parseInt(resultTotal.get(0).get("CANCELPOSRED").toString())+Integer.parseInt(resultTotal.get(1).get("CANCELPOSRED").toString())); 
				resultPlus.put("REFUNDPOSRED", Integer.parseInt(resultTotal.get(0).get("REFUNDPOSRED").toString())+Integer.parseInt(resultTotal.get(1).get("REFUNDPOSRED").toString())); 
				resultPlus.put("SALEPOSSUBTOTAL", Integer.parseInt(resultTotal.get(0).get("SALEPOSSUBTOTAL").toString())+Integer.parseInt(resultTotal.get(1).get("SALEPOSSUBTOTAL").toString())); 
				resultPlus.put("USEPOSSUBTOTAL", Integer.parseInt(resultTotal.get(0).get("USEPOSSUBTOTAL").toString())+Integer.parseInt(resultTotal.get(1).get("USEPOSSUBTOTAL").toString())); 
				resultPlus.put("MTICKETQTPOSSUBTOTAL", Integer.parseInt(resultTotal.get(0).get("MTICKETQTPOSSUBTOTAL").toString())+Integer.parseInt(resultTotal.get(1).get("MTICKETQTPOSSUBTOTAL").toString())); 
				resultPlus.put("CANCELPOSSUBTOTAL", Integer.parseInt(resultTotal.get(0).get("CANCELPOSSUBTOTAL").toString())+Integer.parseInt(resultTotal.get(1).get("CANCELPOSSUBTOTAL").toString())); 
				resultPlus.put("REFUNDPOSSUBTOTAL", Integer.parseInt(resultTotal.get(0).get("REFUNDPOSSUBTOTAL").toString())+Integer.parseInt(resultTotal.get(1).get("REFUNDPOSSUBTOTAL").toString()));
				resultPlus.put("SALEONLINE", Integer.parseInt(resultTotal.get(0).get("SALEONLINE").toString())+Integer.parseInt(resultTotal.get(1).get("SALEONLINE").toString())); 
				resultPlus.put("USEONLINE", Integer.parseInt(resultTotal.get(0).get("USEONLINE").toString())+Integer.parseInt(resultTotal.get(1).get("USEONLINE").toString())); 
				resultPlus.put("CANCELONLINE", Integer.parseInt(resultTotal.get(0).get("CANCELONLINE").toString())+Integer.parseInt(resultTotal.get(1).get("CANCELONLINE").toString())); 
				resultPlus.put("REFUNDONLINE", Integer.parseInt(resultTotal.get(0).get("REFUNDONLINE").toString())+Integer.parseInt(resultTotal.get(1).get("REFUNDONLINE").toString())); 
				resultPlus.put("SALEONLINE", Integer.parseInt(resultTotal.get(0).get("SALEONLINE").toString())+Integer.parseInt(resultTotal.get(1).get("SALEONLINE").toString())); 
				resultPlus.put("USEONLINE", Integer.parseInt(resultTotal.get(0).get("USEONLINE").toString())+Integer.parseInt(resultTotal.get(1).get("USEONLINE").toString())); 
				resultPlus.put("MTICKETQTONLINE", Integer.parseInt(resultTotal.get(0).get("MTICKETQTONLINE").toString())+Integer.parseInt(resultTotal.get(1).get("MTICKETQTONLINE").toString())); 
				resultPlus.put("CANCELONLINE", Integer.parseInt(resultTotal.get(0).get("CANCELONLINE").toString())+Integer.parseInt(resultTotal.get(1).get("CANCELONLINE").toString())); 
				resultPlus.put("REFUNDONLINE", Integer.parseInt(resultTotal.get(0).get("REFUNDONLINE").toString())+Integer.parseInt(resultTotal.get(1).get("REFUNDONLINE").toString())); 
				resultPlus.put("SALEGRANDTOTAL", Integer.parseInt(resultTotal.get(0).get("SALEGRANDTOTAL").toString())+Integer.parseInt(resultTotal.get(1).get("SALEGRANDTOTAL").toString())); 
				resultPlus.put("USEGRANDTOTAL", Integer.parseInt(resultTotal.get(0).get("USEGRANDTOTAL").toString())+Integer.parseInt(resultTotal.get(1).get("USEGRANDTOTAL").toString())); 
				resultPlus.put("USEMTICKETQTGRANDTOTAL", Integer.parseInt(resultTotal.get(0).get("USEMTICKETQTGRANDTOTAL").toString())+Integer.parseInt(resultTotal.get(1).get("USEMTICKETQTGRANDTOTAL").toString())); 
				resultPlus.put("CANCELGRANDTOTAL", Integer.parseInt(resultTotal.get(0).get("CANCELGRANDTOTAL").toString())+Integer.parseInt(resultTotal.get(1).get("CANCELGRANDTOTAL").toString())); 
				resultPlus.put("REFUNDGRANDTOTAL", Integer.parseInt(resultTotal.get(0).get("REFUNDGRANDTOTAL").toString())+Integer.parseInt(resultTotal.get(1).get("REFUNDGRANDTOTAL").toString())); 
				result.add(resultPlus);
			}else {
				result = resultTotal;
			}
		} catch (Exception e) {
			logger.error("tckSaleStatsDetailPkgList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	
	@Override
	public List<Map<String, Object>> tckSaleStatsList(TckStatsVO vo) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		try {
			result = tckStatsDAO.tckSaleStatsList(vo);
			
			
		} catch (Exception e) {
			logger.error("tckSaleStatsList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> tckSaleStatsMonthList(TckStatsVO vo) throws Exception {
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
				resultMonth = tckStatsDAO.tckSaleStatsMonthList(vo);
				if(StringUtil.mapIsEmpty(resultMonth)) {
					Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
					resultMonthEmpty.put("DT", vo.getSearchMonth());
					resultMonthEmpty.put("SALEAPP", 0);
					resultMonthEmpty.put("USEAPP", 0);
					resultMonthEmpty.put("CANCELAPP", 0);
					resultMonthEmpty.put("REFUNDAPP", 0);
					resultMonthEmpty.put("SALEONLINE", 0);
					resultMonthEmpty.put("USEONLINE", 0);
					resultMonthEmpty.put("CANCELONLINE", 0);
					resultMonthEmpty.put("REFUNDONLINE", 0);
					resultMonthEmpty.put("SALEPOS", 0);
					resultMonthEmpty.put("USEPOS", 0);
					resultMonthEmpty.put("CANCELPOS", 0);
					resultMonthEmpty.put("REFUNDPOS", 0);
					resultMonthEmpty.put("SALETOTAL", 0);
					resultMonthEmpty.put("USETOTAL", 0);
					resultMonthEmpty.put("CANCELETOTAL", 0);
					resultMonthEmpty.put("REFUNDTOTAL", 0);
					resultMonth=resultMonthEmpty;
				}
				result.add(resultMonth);
			}else { //월차이 1이상
				for(int i = 0 ; i < monthInt; i++ ) {
					if(i == 0) {
						vo.setSearchMonth(fromFormat.substring(0,6));
						vo.setSearchFromDt(fromFormat.substring(0,6)+"01");
						vo.setSearchToDt(fromFormat.substring(0,6)+from.getActualMaximum(Calendar.DAY_OF_MONTH));
						resultMonth = tckStatsDAO.tckSaleStatsMonthList(vo);
						if(StringUtil.mapIsEmpty(resultMonth)) {
							Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
							resultMonthEmpty.put("DT", vo.getSearchMonth());
							resultMonthEmpty.put("SALEAPP", 0);
							resultMonthEmpty.put("USEAPP", 0);
							resultMonthEmpty.put("CANCELAPP", 0);
							resultMonthEmpty.put("REFUNDAPP", 0);
							resultMonthEmpty.put("SALEONLINE", 0);
							resultMonthEmpty.put("USEONLINE", 0);
							resultMonthEmpty.put("CANCELONLINE", 0);
							resultMonthEmpty.put("REFUNDONLINE", 0);
							resultMonthEmpty.put("SALEPOS", 0);
							resultMonthEmpty.put("USEPOS", 0);
							resultMonthEmpty.put("CANCELPOS", 0);
							resultMonthEmpty.put("REFUNDPOS", 0);
							resultMonthEmpty.put("SALETOTAL", 0);
							resultMonthEmpty.put("USETOTAL", 0);
							resultMonthEmpty.put("CANCELETOTAL", 0);
							resultMonthEmpty.put("REFUNDTOTAL", 0);
							resultMonth=resultMonthEmpty;
						}
						result.add(resultMonth);
					}else {
						from.add(Calendar.MONTH, 1);
						Format.format(from.getTime());
						vo.setSearchMonth(Format.format(from.getTime()).substring(0,6));
						vo.setSearchFromDt(Format.format(from.getTime()).substring(0,6)+"01");
						vo.setSearchToDt(Format.format(from.getTime()).substring(0,6)+from.getActualMaximum(Calendar.DAY_OF_MONTH));
						resultMonth = tckStatsDAO.tckSaleStatsMonthList(vo);
						if(StringUtil.mapIsEmpty(resultMonth)) {
							Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
							resultMonthEmpty.put("DT", vo.getSearchMonth());
							resultMonthEmpty.put("SALEAPP", 0);
							resultMonthEmpty.put("USEAPP", 0);
							resultMonthEmpty.put("CANCELAPP", 0);
							resultMonthEmpty.put("REFUNDAPP", 0);
							resultMonthEmpty.put("SALEONLINE", 0);
							resultMonthEmpty.put("USEONLINE", 0);
							resultMonthEmpty.put("CANCELONLINE", 0);
							resultMonthEmpty.put("REFUNDONLINE", 0);
							resultMonthEmpty.put("SALEPOS", 0);
							resultMonthEmpty.put("USEPOS", 0);
							resultMonthEmpty.put("CANCELPOS", 0);
							resultMonthEmpty.put("REFUNDPOS", 0);
							resultMonthEmpty.put("SALETOTAL", 0);
							resultMonthEmpty.put("USETOTAL", 0);
							resultMonthEmpty.put("CANCELETOTAL", 0);
							resultMonthEmpty.put("REFUNDTOTAL", 0);
							resultMonth=resultMonthEmpty;
						}
						result.add(resultMonth);
					}
				}
				//마지막월 조회
				vo.setSearchMonth(toFormat.substring(0,6));
				vo.setSearchFromDt(toFormat.substring(0,6)+"01");
				vo.setSearchToDt(toFormat.substring(0,6)+to.getActualMaximum(Calendar.DAY_OF_MONTH));
				resultMonth = tckStatsDAO.tckSaleStatsMonthList(vo);
				if(StringUtil.mapIsEmpty(resultMonth)) {
					Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
					resultMonthEmpty.put("DT", vo.getSearchMonth());
					resultMonthEmpty.put("SALEAPP", 0);
					resultMonthEmpty.put("USEAPP", 0);
					resultMonthEmpty.put("CANCELAPP", 0);
					resultMonthEmpty.put("REFUNDAPP", 0);
					resultMonthEmpty.put("SALEONLINE", 0);
					resultMonthEmpty.put("USEONLINE", 0);
					resultMonthEmpty.put("CANCELONLINE", 0);
					resultMonthEmpty.put("REFUNDONLINE", 0);
					resultMonthEmpty.put("SALEPOS", 0);
					resultMonthEmpty.put("USEPOS", 0);
					resultMonthEmpty.put("CANCELPOS", 0);
					resultMonthEmpty.put("REFUNDPOS", 0);
					resultMonthEmpty.put("SALETOTAL", 0);
					resultMonthEmpty.put("USETOTAL", 0);
					resultMonthEmpty.put("CANCELETOTAL", 0);
					resultMonthEmpty.put("REFUNDTOTAL", 0);
					resultMonth=resultMonthEmpty;
				}
				result.add(resultMonth);
			}
		} catch (Exception e) {
			logger.error("tckSaleStatsMonthList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> tckSaleStatsYearList(TckStatsVO vo) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> resultYear = new HashMap<String, Object>();
		try {
			vo.getSearchYearFromDt().substring(2,4);
			vo.getSearchYearToDt().substring(2,4);
			
			int yearInt = Math.abs(Integer.parseInt(vo.getSearchYearFromDt().substring(2,4)) -  Integer.parseInt(vo.getSearchYearToDt().substring(2,4)));
			
			String lastTo = vo.getSearchYearToDt();
			if(yearInt == 0) {
				vo.setSearchYearToDt(vo.getSearchYearFromDt());
				resultYear = tckStatsDAO.tckSaleStatsYearList(vo);
				if(StringUtil.mapIsEmpty(resultYear)) {
					Map<String, Object> resultYearEmpty = new HashMap<String, Object>();
					resultYearEmpty.put("DT", vo.getSearchYearToDt());
					resultYearEmpty.put("SALEAPP", 0);
					resultYearEmpty.put("USEAPP", 0);
					resultYearEmpty.put("CANCELAPP", 0);
					resultYearEmpty.put("REFUNDAPP", 0);
					resultYearEmpty.put("SALEONLINE", 0);
					resultYearEmpty.put("USEONLINE", 0);
					resultYearEmpty.put("CANCELONLINE", 0);
					resultYearEmpty.put("REFUNDONLINE", 0);
					resultYearEmpty.put("SALEPOS", 0);
					resultYearEmpty.put("USEPOS", 0);
					resultYearEmpty.put("CANCELPOS", 0);
					resultYearEmpty.put("REFUNDPOS", 0);
					resultYearEmpty.put("SALETOTAL", 0);
					resultYearEmpty.put("USETOTAL", 0);
					resultYearEmpty.put("CANCELETOTAL", 0);
					resultYearEmpty.put("REFUNDTOTAL", 0);
					resultYear=resultYearEmpty;
				}
				result.add(resultYear);
			}else { //월차이 1이상
				for(int i = 0 ; i < yearInt; i++ ) {
					if(i == 0) {
						vo.setSearchYearToDt(vo.getSearchYearFromDt());
						resultYear = tckStatsDAO.tckSaleStatsYearList(vo);
						if(StringUtil.mapIsEmpty(resultYear)) {
							Map<String, Object> resultYearEmpty = new HashMap<String, Object>();
							resultYearEmpty.put("DT", vo.getSearchYearToDt());
							resultYearEmpty.put("SALEAPP", 0);
							resultYearEmpty.put("USEAPP", 0);
							resultYearEmpty.put("CANCELAPP", 0);
							resultYearEmpty.put("REFUNDAPP", 0);
							resultYearEmpty.put("SALEONLINE", 0);
							resultYearEmpty.put("USEONLINE", 0);
							resultYearEmpty.put("CANCELONLINE", 0);
							resultYearEmpty.put("REFUNDONLINE", 0);
							resultYearEmpty.put("SALEPOS", 0);
							resultYearEmpty.put("USEPOS", 0);
							resultYearEmpty.put("CANCELPOS", 0);
							resultYearEmpty.put("REFUNDPOS", 0);
							resultYearEmpty.put("SALETOTAL", 0);
							resultYearEmpty.put("USETOTAL", 0);
							resultYearEmpty.put("CANCELETOTAL", 0);
							resultYearEmpty.put("REFUNDTOTAL", 0);
							resultYear=resultYearEmpty;
						}
						result.add(resultYear);
					}else {
						int yearTo = (Integer.parseInt(vo.getSearchYearFromDt())+i);
						String yearToDt = yearTo+"";
						vo.setSearchYearToDt(yearToDt);
						resultYear = tckStatsDAO.tckSaleStatsYearList(vo);
						if(StringUtil.mapIsEmpty(resultYear)) {
							Map<String, Object> resultYearEmpty = new HashMap<String, Object>();
							resultYearEmpty.put("DT", vo.getSearchYearToDt());
							resultYearEmpty.put("SALEAPP", 0);
							resultYearEmpty.put("USEAPP", 0);
							resultYearEmpty.put("CANCELAPP", 0);
							resultYearEmpty.put("REFUNDAPP", 0);
							resultYearEmpty.put("SALEONLINE", 0);
							resultYearEmpty.put("USEONLINE", 0);
							resultYearEmpty.put("CANCELONLINE", 0);
							resultYearEmpty.put("REFUNDONLINE", 0);
							resultYearEmpty.put("SALEPOS", 0);
							resultYearEmpty.put("USEPOS", 0);
							resultYearEmpty.put("CANCELPOS", 0);
							resultYearEmpty.put("REFUNDPOS", 0);
							resultYearEmpty.put("SALETOTAL", 0);
							resultYearEmpty.put("USETOTAL", 0);
							resultYearEmpty.put("CANCELETOTAL", 0);
							resultYearEmpty.put("REFUNDTOTAL", 0);
							resultYear=resultYearEmpty;
						}
						result.add(resultYear);
					}
				}
				//마지막조회
				vo.setSearchYearToDt(lastTo);
				resultYear = tckStatsDAO.tckSaleStatsYearList(vo);
				if(StringUtil.mapIsEmpty(resultYear)) {
					Map<String, Object> resultYearEmpty = new HashMap<String, Object>();
					resultYearEmpty.put("DT", vo.getSearchYearToDt());
					resultYearEmpty.put("SALEAPP", 0);
					resultYearEmpty.put("USEAPP", 0);
					resultYearEmpty.put("CANCELAPP", 0);
					resultYearEmpty.put("REFUNDAPP", 0);
					resultYearEmpty.put("SALEONLINE", 0);
					resultYearEmpty.put("USEONLINE", 0);
					resultYearEmpty.put("CANCELONLINE", 0);
					resultYearEmpty.put("REFUNDONLINE", 0);
					resultYearEmpty.put("SALEPOS", 0);
					resultYearEmpty.put("USEPOS", 0);
					resultYearEmpty.put("CANCELPOS", 0);
					resultYearEmpty.put("REFUNDPOS", 0);
					resultYearEmpty.put("SALETOTAL", 0);
					resultYearEmpty.put("USETOTAL", 0);
					resultYearEmpty.put("CANCELETOTAL", 0);
					resultYearEmpty.put("REFUNDTOTAL", 0);
					resultYear=resultYearEmpty;
				}
				result.add(resultYear);
			}
		} catch (Exception e) {
			logger.error("monthStatisticsAccmltList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	
	@Override
	public List<Map<String, Object>> tckSelngStatsList(TckStatsVO vo) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		try {
			result = tckStatsDAO.tckSelngStatsList(vo);
			
			
		} catch (Exception e) {
			logger.error("tckSaleStatsList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> tckSelngStatsMonthList(TckStatsVO vo) throws Exception {
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
				resultMonth = tckStatsDAO.tckSelngStatsMonthList(vo);
				if(StringUtil.mapIsEmpty(resultMonth)) {
					Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
					resultMonthEmpty.put("DT", vo.getSearchMonth());
					resultMonthEmpty.put("SALEAPP", 0);
					resultMonthEmpty.put("USEAPP", 0);
					resultMonthEmpty.put("CANCELAPP", 0);
					resultMonthEmpty.put("REFUNDAPP", 0);
					resultMonthEmpty.put("TOTALAPP", 0);
					resultMonthEmpty.put("SALEONLINE", 0);
					resultMonthEmpty.put("USEONLINE", 0);
					resultMonthEmpty.put("CANCELONLINE", 0);
					resultMonthEmpty.put("REFUNDONLINE", 0);
					resultMonthEmpty.put("TOTALONLINE", 0);
					resultMonthEmpty.put("SALEPOS", 0);
					resultMonthEmpty.put("USEPOS", 0);
					resultMonthEmpty.put("CANCELPOS", 0);
					resultMonthEmpty.put("REFUNDPOS", 0);
					resultMonthEmpty.put("TOTALPOS", 0);
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
						resultMonth = tckStatsDAO.tckSelngStatsMonthList(vo);
						if(StringUtil.mapIsEmpty(resultMonth)) {
							Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
							resultMonthEmpty.put("DT", vo.getSearchMonth());
							resultMonthEmpty.put("SALEAPP", 0);
							resultMonthEmpty.put("USEAPP", 0);
							resultMonthEmpty.put("CANCELAPP", 0);
							resultMonthEmpty.put("REFUNDAPP", 0);
							resultMonthEmpty.put("TOTALAPP", 0);
							resultMonthEmpty.put("SALEONLINE", 0);
							resultMonthEmpty.put("USEONLINE", 0);
							resultMonthEmpty.put("CANCELONLINE", 0);
							resultMonthEmpty.put("REFUNDONLINE", 0);
							resultMonthEmpty.put("TOTALONLINE", 0);
							resultMonthEmpty.put("SALEPOS", 0);
							resultMonthEmpty.put("USEPOS", 0);
							resultMonthEmpty.put("CANCELPOS", 0);
							resultMonthEmpty.put("REFUNDPOS", 0);
							resultMonthEmpty.put("TOTALPOS", 0);
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
						resultMonth = tckStatsDAO.tckSelngStatsMonthList(vo);
						if(StringUtil.mapIsEmpty(resultMonth)) {
							Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
							resultMonthEmpty.put("DT", vo.getSearchMonth());
							resultMonthEmpty.put("SALEAPP", 0);
							resultMonthEmpty.put("USEAPP", 0);
							resultMonthEmpty.put("CANCELAPP", 0);
							resultMonthEmpty.put("REFUNDAPP", 0);
							resultMonthEmpty.put("TOTALAPP", 0);
							resultMonthEmpty.put("SALEONLINE", 0);
							resultMonthEmpty.put("USEONLINE", 0);
							resultMonthEmpty.put("CANCELONLINE", 0);
							resultMonthEmpty.put("REFUNDONLINE", 0);
							resultMonthEmpty.put("TOTALONLINE", 0);
							resultMonthEmpty.put("SALEPOS", 0);
							resultMonthEmpty.put("USEPOS", 0);
							resultMonthEmpty.put("CANCELPOS", 0);
							resultMonthEmpty.put("REFUNDPOS", 0);
							resultMonthEmpty.put("TOTALPOS", 0);
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
				resultMonth = tckStatsDAO.tckSelngStatsMonthList(vo);
				if(StringUtil.mapIsEmpty(resultMonth)) {
					Map<String, Object> resultMonthEmpty = new HashMap<String, Object>();
					resultMonthEmpty.put("DT", vo.getSearchMonth());
					resultMonthEmpty.put("SALEAPP", 0);
					resultMonthEmpty.put("USEAPP", 0);
					resultMonthEmpty.put("CANCELAPP", 0);
					resultMonthEmpty.put("REFUNDAPP", 0);
					resultMonthEmpty.put("TOTALAPP", 0);
					resultMonthEmpty.put("SALEONLINE", 0);
					resultMonthEmpty.put("USEONLINE", 0);
					resultMonthEmpty.put("CANCELONLINE", 0);
					resultMonthEmpty.put("REFUNDONLINE", 0);
					resultMonthEmpty.put("TOTALONLINE", 0);
					resultMonthEmpty.put("SALEPOS", 0);
					resultMonthEmpty.put("USEPOS", 0);
					resultMonthEmpty.put("CANCELPOS", 0);
					resultMonthEmpty.put("REFUNDPOS", 0);
					resultMonthEmpty.put("TOTALPOS", 0);
					resultMonthEmpty.put("GRANDTOTAL", 0);
					resultMonth=resultMonthEmpty;
				}
				result.add(resultMonth);
			}
		} catch (Exception e) {
			logger.error("tckSelngStatsMonthList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> tckSelngStatsYearList(TckStatsVO vo) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> resultYear = new HashMap<String, Object>();
		try {
			vo.getSearchYearFromDt().substring(2,4);
			vo.getSearchYearToDt().substring(2,4);
			
			int yearInt = Math.abs(Integer.parseInt(vo.getSearchYearFromDt().substring(2,4)) -  Integer.parseInt(vo.getSearchYearToDt().substring(2,4)));
			
			String lastTo = vo.getSearchYearToDt();
			if(yearInt == 0) {
				vo.setSearchYearToDt(vo.getSearchYearFromDt());
				resultYear = tckStatsDAO.tckSelngStatsYearList(vo);
				if(StringUtil.mapIsEmpty(resultYear)) {
					Map<String, Object> resultYearEmpty = new HashMap<String, Object>();
					resultYearEmpty.put("DT", vo.getSearchYearToDt());
					resultYearEmpty.put("SALEAPP", 0);
					resultYearEmpty.put("USEAPP", 0);
					resultYearEmpty.put("CANCELAPP", 0);
					resultYearEmpty.put("REFUNDAPP", 0);
					resultYearEmpty.put("TOTALAPP", 0);
					resultYearEmpty.put("SALEONLINE", 0);
					resultYearEmpty.put("USEONLINE", 0);
					resultYearEmpty.put("CANCELONLINE", 0);
					resultYearEmpty.put("REFUNDONLINE", 0);
					resultYearEmpty.put("TOTALONLINE", 0);
					resultYearEmpty.put("SALEPOS", 0);
					resultYearEmpty.put("USEPOS", 0);
					resultYearEmpty.put("CANCELPOS", 0);
					resultYearEmpty.put("REFUNDPOS", 0);
					resultYearEmpty.put("TOTALPOS", 0);
					resultYearEmpty.put("GRANDTOTAL", 0);
					resultYear=resultYearEmpty;
				}
				result.add(resultYear);
			}else { //월차이 1이상
				for(int i = 0 ; i < yearInt; i++ ) {
					if(i == 0) {
						vo.setSearchYearToDt(vo.getSearchYearFromDt());
						resultYear = tckStatsDAO.tckSelngStatsYearList(vo);
						if(StringUtil.mapIsEmpty(resultYear)) {
							Map<String, Object> resultYearEmpty = new HashMap<String, Object>();
							resultYearEmpty.put("DT", vo.getSearchYearToDt());
							resultYearEmpty.put("SALEAPP", 0);
							resultYearEmpty.put("USEAPP", 0);
							resultYearEmpty.put("CANCELAPP", 0);
							resultYearEmpty.put("REFUNDAPP", 0);
							resultYearEmpty.put("TOTALAPP", 0);
							resultYearEmpty.put("SALEONLINE", 0);
							resultYearEmpty.put("USEONLINE", 0);
							resultYearEmpty.put("CANCELONLINE", 0);
							resultYearEmpty.put("REFUNDONLINE", 0);
							resultYearEmpty.put("TOTALONLINE", 0);
							resultYearEmpty.put("SALEPOS", 0);
							resultYearEmpty.put("USEPOS", 0);
							resultYearEmpty.put("CANCELPOS", 0);
							resultYearEmpty.put("REFUNDPOS", 0);
							resultYearEmpty.put("TOTALPOS", 0);
							resultYearEmpty.put("GRANDTOTAL", 0);
							resultYear=resultYearEmpty;
						}
						result.add(resultYear);
					}else {
						int yearTo = (Integer.parseInt(vo.getSearchYearFromDt())+i);
						String yearToDt = yearTo+"";
						vo.setSearchYearToDt(yearToDt);
						resultYear = tckStatsDAO.tckSelngStatsYearList(vo);
						if(StringUtil.mapIsEmpty(resultYear)) {
							Map<String, Object> resultYearEmpty = new HashMap<String, Object>();
							resultYearEmpty.put("DT", vo.getSearchYearToDt());
							resultYearEmpty.put("SALEAPP", 0);
							resultYearEmpty.put("USEAPP", 0);
							resultYearEmpty.put("CANCELAPP", 0);
							resultYearEmpty.put("REFUNDAPP", 0);
							resultYearEmpty.put("TOTALAPP", 0);
							resultYearEmpty.put("SALEONLINE", 0);
							resultYearEmpty.put("USEONLINE", 0);
							resultYearEmpty.put("CANCELONLINE", 0);
							resultYearEmpty.put("REFUNDONLINE", 0);
							resultYearEmpty.put("TOTALONLINE", 0);
							resultYearEmpty.put("SALEPOS", 0);
							resultYearEmpty.put("USEPOS", 0);
							resultYearEmpty.put("CANCELPOS", 0);
							resultYearEmpty.put("REFUNDPOS", 0);
							resultYearEmpty.put("TOTALPOS", 0);
							resultYearEmpty.put("GRANDTOTAL", 0);
							resultYear=resultYearEmpty;
						}
						result.add(resultYear);
					}
				}
				//마지막조회
				vo.setSearchYearToDt(lastTo);
				resultYear = tckStatsDAO.tckSelngStatsYearList(vo);
				if(StringUtil.mapIsEmpty(resultYear)) {
					Map<String, Object> resultYearEmpty = new HashMap<String, Object>();
					resultYearEmpty.put("DT", vo.getSearchYearToDt());
					resultYearEmpty.put("SALEAPP", 0);
					resultYearEmpty.put("USEAPP", 0);
					resultYearEmpty.put("CANCELAPP", 0);
					resultYearEmpty.put("REFUNDAPP", 0);
					resultYearEmpty.put("TOTALAPP", 0);
					resultYearEmpty.put("SALEONLINE", 0);
					resultYearEmpty.put("USEONLINE", 0);
					resultYearEmpty.put("CANCELONLINE", 0);
					resultYearEmpty.put("REFUNDONLINE", 0);
					resultYearEmpty.put("TOTALONLINE", 0);
					resultYearEmpty.put("SALEPOS", 0);
					resultYearEmpty.put("USEPOS", 0);
					resultYearEmpty.put("CANCELPOS", 0);
					resultYearEmpty.put("REFUNDPOS", 0);
					resultYearEmpty.put("TOTALPOS", 0);
					resultYearEmpty.put("GRANDTOTAL", 0);
					resultYear=resultYearEmpty;
				}
				result.add(resultYear);
			}
		} catch (Exception e) {
			logger.error("tckSelngStatsYearList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}

	
}
