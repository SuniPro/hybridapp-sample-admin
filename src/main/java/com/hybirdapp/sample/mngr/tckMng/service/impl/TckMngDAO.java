package com.hybirdapp.sample.mngr.tckMng.service.impl;

import java.util.HashMap;
import java.util.List;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.mngr.tckMng.service.TckMngVO;

@OracleMapper("TckMngDAO")
public interface TckMngDAO {

	/**
	 * 티켓 분류 조회 건수
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	public int searchTckMngListCnt(HashMap params) throws Exception;
	
	/**
	 * 티켓 분류 조회
	 * @param HashMap
	 * @return List
	 * @throws Exception
	 */
	public List searchTckMngList(HashMap params) throws Exception;
	
	/**
	 * 티켓 분류 정보 조회
	 * @param HashMap
	 * @return HashMap
	 * @throws Exception
	 */
	public List searchTckList(HashMap params) throws Exception;
	
	/**
	 * 티켓 분류 정보 수정
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	public int modifyTckMng(TckMngVO vo) throws Exception;
	
	/**
	 * 티켓 분류 정보 수정
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	public int registTckMng(HashMap params) throws Exception;
	
	/**
	 * 티켓 분류 정보 삭제
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	public int deleteTckMng(HashMap params) throws Exception;
}
