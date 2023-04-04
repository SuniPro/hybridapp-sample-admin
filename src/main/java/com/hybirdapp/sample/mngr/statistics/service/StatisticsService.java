package com.hybirdapp.sample.mngr.statistics.service;

import java.util.List;
import java.util.Map;

public interface StatisticsService {

	/***
	 * 일 회원 통계
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<StatisticsVO> dayStatisticsList(StatisticsVO vo) throws Exception;
	
	/***
	 * 월 회원 통계
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> monthStatisticsList(StatisticsVO vo) throws Exception;
	
	/***
	 * 일 누적 회원 통계
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> dayStatisticsAccmltList(StatisticsVO vo) throws Exception;
	
	/***
	 * 월 누적 회원 통계
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> monthStatisticsAccmltList(StatisticsVO vo) throws Exception;
	
	/***
	 * 연도 누적 회원 통계
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> yearStatisticsAccmltList(StatisticsVO vo) throws Exception;
	
}
