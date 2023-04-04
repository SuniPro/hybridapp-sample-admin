package com.hybirdapp.sample.mngr.fclMng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import org.apache.ibatis.annotations.Param;

@OracleMapper("FclMngDAO")
public interface FclMngDAO {
	
	/**
	 * 시설 관리 목록 건수
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	public int searchFclMngListCnt(HashMap params) throws Exception;

	/**
	 * 시설 관리 목록 조회
	 * @param HashMap
	 * @return List
	 * @throws Exception
	 */
	public List searchFclMngList(HashMap params) throws Exception;

	/**
	 * 시설 관리 단건 조회
	 * @param HashMap
	 * @return HashMap
	 * @throws Exception
	 */
	public HashMap searchFclMng(HashMap params) throws Exception;

	/**
	 * 시설 관리 목록 등록
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int registFclMng(DataClass params) throws Exception;

	/**
	 * 시설 관리 목록 수정
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int modifyFclMng(DataClass params) throws Exception;

	/**
	 * 시설 관리 목록 삭제
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int deleteFclMng(DataClass params) throws Exception;

	/* 시퀀스 채번 관련 Method */
	String getFcltSequence() throws Exception;
	String getFcltDtlSequence() throws Exception;
	String getPlaceSequence() throws Exception;
	/* 시설등록 */
	int insertFclt(Map<String, String> params) throws Exception;
	int insertPlace(Map<String, String> params) throws Exception;
	int insertFcltDtl(Map<String, String> params) throws Exception;
	/* 시설 수정 */
	Map<String, Object> selectFclMng(@Param("fcltSeq") String fcltSeq) throws Exception;
	List<Map<String, String>> listFcltDtl(@Param("fcltSeq") String fcltSeq) throws Exception;
	List<Map<String, String>> listTotalFcltDtl(@Param("fcltSeq") String fcltSeq) throws Exception;
	List<Map<String, String>> listFcltOrder() throws Exception;
	int updateFclt(Map<String, String> params) throws Exception;
	int updatePlace(Map<String, String> params) throws Exception;
	int deleteFcltDtl(Map<String, String> params) throws Exception;
	int updateFcltDtl(Map<String, String> params) throws Exception;
	int deleteFclt(Map<String, String> params) throws Exception;
	int updateFcltOrder(Map<String, String> params) throws Exception;
	int insertFcltWaiting(@Param("fcltSeq") String fcltSeq) throws Exception;
	int getFcltOrder() throws Exception;
}
