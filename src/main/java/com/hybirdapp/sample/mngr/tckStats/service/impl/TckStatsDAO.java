package com.hybirdapp.sample.mngr.tckStats.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.mngr.tckStats.service.TckStatsVO;

@OracleMapper("TckStatsDAO")
public interface TckStatsDAO {

	/***
	 * 판매현황 통계 일
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> tckSaleStatsList(TckStatsVO vo) throws Exception;
	
	/***
	 * 판매현황 통계 월
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> tckSaleStatsMonthList(TckStatsVO vo) throws Exception;
	
	/***
	 * 판매현황 통계 연도
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> tckSaleStatsYearList(TckStatsVO vo) throws Exception;
	
	/***
	 * 판매현황 통계 상세 
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> tckSaleStatsDetailList(TckStatsVO vo) throws Exception;
	
	/***
	 * 판매현황 통계 상세 패키지
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> tckSaleStatsDetailPkgList(TckStatsVO vo) throws Exception;
	
	/***
	 * 매출현황 통계 일
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> tckSelngStatsList(TckStatsVO vo) throws Exception;
	
	/***
	 * 매출현황 통계 월
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> tckSelngStatsMonthList(TckStatsVO vo) throws Exception;
	
	/***
	 * 매출현황 통계 연도
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> tckSelngStatsYearList(TckStatsVO vo) throws Exception;
	
	/***
	 * 매출현황 통계 상세 
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> tckSelngStatsDetailList(TckStatsVO vo) throws Exception;
	
	/***
	 * 매출현황 통계 상세 패키지
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> tckSelngStatsDetailPkgList(TckStatsVO vo) throws Exception;

}
