package com.hybirdapp.sample.mngr.conSls.service.impl;

import java.util.HashMap;
import java.util.List;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;

@OracleMapper("ConSlsDAO")
public interface ConSlsDAO {

	/**
	 * 콘도 판매 조회 건수
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	public int searchConSlsListCnt(HashMap params) throws Exception;
	
	/**
	 * 콘도 판매 조회
	 * @param HashMap
	 * @return List
	 * @throws Exception
	 */
	public List searchConSlsList(HashMap params) throws Exception;
	
	/**
	 * 콘도 판매 정보 조회(팝업)
	 * @param HashMap
	 * @return List
	 * @throws Exception
	 */
	public List searchConSls(HashMap params) throws Exception;
	
	
	/**
	 * 콘도 판매 총 금액 조회
	 * @param DataClass
	 * @return HashMap
	 * @throws Exception
	 */
	public int searchConSlsListAmt(HashMap params) throws Exception;


	/**
	 * 당일환불금액 조회
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int searchCurrentDayRefundAmt (HashMap params) throws Exception ;

}
