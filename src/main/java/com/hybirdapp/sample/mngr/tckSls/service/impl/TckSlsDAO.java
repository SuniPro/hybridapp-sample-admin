package com.hybirdapp.sample.mngr.tckSls.service.impl;

import java.util.HashMap;
import java.util.List;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;

@OracleMapper("TckSlsDAO")
public interface TckSlsDAO {

	/**
	 * 티켓 판매 조회 건수
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	public int searchTckSlsListCnt(HashMap params) throws Exception;
	
	/**
	 * 티켓 판매 조회
	 * @param HashMap
	 * @return List
	 * @throws Exception
	 */
	public List searchTckSlsList(HashMap params) throws Exception;
	
	/**
	 * 티켓 판매 정보 조회(팝업)
	 * @param HashMap
	 * @return List
	 * @throws Exception
	 */
	public List searchTckSls(HashMap params) throws Exception;
	
	
	/**
	 * 티켓 판매 총 금액 조회
	 * @param DataClass
	 * @return HashMap
	 * @throws Exception
	 */
	public int searchTckSlsListAmt(HashMap params) throws Exception;


	/**
	 * 당일환불금액 조회
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int searchCurrentDayRefundAmt (HashMap params) throws Exception ;

	/**
	 * 이전환불금액 조회
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int searchBeforeDayRefundAmt (HashMap params) throws Exception ;

	/**
	 * 이전환불목록 조회(팝업)
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List beforeRefundList (HashMap params) throws Exception ;


	/**
	 * 검색조건을 위한 시설목록 조회
	 * @return
	 * @throws Exception
	 */
	public List searchFcltList ( ) throws Exception ;


	/**
	 * 시설에 등록된 티켓 목록 조회
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List searchFcltTckList ( HashMap params ) throws Exception ;

}
