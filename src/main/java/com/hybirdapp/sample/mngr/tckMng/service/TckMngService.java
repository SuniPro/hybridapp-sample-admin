package com.hybirdapp.sample.mngr.tckMng.service;

import java.util.List;

import com.hybirdapp.sample.cmmn.DataClass;

public interface TckMngService {

	/**
	 * 티켓 분류 조회 건수
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int searchTckMngListCnt(DataClass params) throws Exception;
	
	/**
	 * 티켓 분류 조회
	 * @param DataClass
	 * @return List
	 * @throws Exception
	 */
	public List searchTckMngList(DataClass params) throws Exception;
	
	/**
	 * 티켓 분류 수정 정보 조회
	 * @param DataClass
	 * @return List
	 * @throws Exception
	 */
	public List searchTckList(DataClass params) throws Exception;
	
	/**
	 * 티켓 분류 정보 등록
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int modifyTckMng(TckMngVO vo) throws Exception;
	
	/**
	 * 티켓 분류 정보 수정
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int registTckMng(DataClass params) throws Exception;
	
	/**
	 * 티켓 분류 정보 삭제
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int deleteTckMng(DataClass params) throws Exception;
	
}
