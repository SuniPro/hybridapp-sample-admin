package com.hybirdapp.sample.mngr.tckSls.service;

import java.util.List;

import com.hybirdapp.sample.cmmn.DataClass;

public interface TckSlsService {

	/**
	 * 티켓 판매 조회 건수
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int searchTckSlsListCnt(DataClass params) throws Exception;
	
	/**
	 * 티켓 판매 조회
	 * @param DataClass
	 * @return List
	 * @throws Exception
	 */
	public List searchTckSlsList(DataClass params) throws Exception;
	
	/**
	 * 티켓 판매 정보 조회(팝업)
	 * @param DataClass
	 * @return List
	 * @throws Exception
	 */
	public List searchTckSls(DataClass params) throws Exception;
	
	/**
	 * 티켓 판매 총 금액 조회
	 * @param DataClass
	 * @return HashMap
	 * @throws Exception
	 */
	public int searchTckSlsAmt(DataClass params) throws Exception;

	/**
	 * 당일환불금액 조회
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int searchCurrentDayRefundAmt (DataClass params) throws Exception ;

	/**
	 * 이전환불금액 조회
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int searchBeforeDayRefundAmt (DataClass params) throws Exception ;

	/**
	 * 이전환불목록 조회(팝업)
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List beforeRefundList (DataClass params) throws Exception ;

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
	public List searchFcltTckList ( DataClass params ) throws Exception ;

	
}
