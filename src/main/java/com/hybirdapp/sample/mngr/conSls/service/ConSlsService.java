package com.hybirdapp.sample.mngr.conSls.service;

import java.util.List;

import com.hybirdapp.sample.cmmn.DataClass;

public interface ConSlsService {

	/**
	 * 콘도 판매 조회 건수
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int searchConSlsListCnt(DataClass params) throws Exception;
	
	/**
	 * 콘도 판매 조회
	 * @param DataClass
	 * @return List
	 * @throws Exception
	 */
	public List searchConSlsList(DataClass params) throws Exception;
	
	/**
	 * 콘도 판매 정보 조회(팝업)
	 * @param DataClass
	 * @return List
	 * @throws Exception
	 */
	public List searchConSls(DataClass params) throws Exception;
	
	/**
	 * 콘도 판매 총 금액 조회
	 * @param DataClass
	 * @return HashMap
	 * @throws Exception
	 */
	public int searchConSlsAmt(DataClass params) throws Exception;

	/**
	 * 당일환불금액 조회
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int searchCurrentDayRefundAmt (DataClass params) throws Exception ;

}
