package com.hybirdapp.sample.mngr.tckStats.service;

import java.util.List;
import java.util.Map;

public interface TckStatsService {

	/***
	 * 일 판매 통계
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> tckSaleStatsList(TckStatsVO vo) throws Exception;
	
	/***
	 * 월 판매 통계
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> tckSaleStatsMonthList(TckStatsVO vo) throws Exception;
	
	/***
	 * 연도 판매 통계
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> tckSaleStatsYearList(TckStatsVO vo) throws Exception;
	
	/***
	 * 판매 통계 상세 패키지
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> tckSaleStatsDetailList(TckStatsVO vo) throws Exception;
	
	/***
	 * 판매 통계 상세
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> tckSaleStatsDetailPkgList(TckStatsVO vo) throws Exception;
	
	/***
	 * 판매 통계 상세 총힙
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> tckSaleStatsDetailListTotal(TckStatsVO vo) throws Exception;
	
	/***
	 * 일 매출 통계
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> tckSelngStatsList(TckStatsVO vo) throws Exception;
	
	/***
	 * 월 매출 통계
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> tckSelngStatsMonthList(TckStatsVO vo) throws Exception;
	
	/***
	 * 연도 매출 통계
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> tckSelngStatsYearList(TckStatsVO vo) throws Exception;
	
	/***
	 * 매출 통계 상세 패키지
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> tckSelngStatsDetailList(TckStatsVO vo) throws Exception;
	
	/***
	 * 매출 통계 상세
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> tckSelngStatsDetailPkgList(TckStatsVO vo) throws Exception;
}
