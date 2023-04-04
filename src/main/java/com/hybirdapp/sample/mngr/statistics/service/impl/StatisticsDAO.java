package com.hybirdapp.sample.mngr.statistics.service.impl;

import java.util.HashMap;
import java.util.List;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.mngr.statistics.service.StatisticsVO;

@OracleMapper("StatisticsDAO")
public interface StatisticsDAO {

	/***
	 * 일 회원 통계
	 * @return
	 * @throws Exception
	 */
	public List<StatisticsVO> dayStatisticsList(StatisticsVO vo) throws Exception;
	
	/***
	 * 월 회원 통계
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> monthStatisticsList(StatisticsVO vo) throws Exception;
	
	/***
	 * 연도 회원 통계
	 * @return
	 * @throws Exception
	 */
	public List<StatisticsVO> yearStatisticsList(StatisticsVO vo) throws Exception;
	
	/***
	 * 일 누적 회원 통계
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> dayStatisticsAccmltList(StatisticsVO vo) throws Exception;
	
	/***
	 * 월 누적 회원 통계
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> monthStatisticsAccmltList(StatisticsVO vo) throws Exception;
	
	/***
	 * 연도 누적 회원 통계
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> yearStatisticsAccmltList(StatisticsVO vo) throws Exception;

}
