package com.hybirdapp.sample.mngr.pkgPrd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import org.apache.ibatis.annotations.Param;

@OracleMapper("PkgPrdDAO")
public interface PkgPrdDAO {
	
	/**
	 * 패키지 상품 목록 건수
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int searchPkgPrdListCnt(HashMap params) throws Exception;

	/**
	 * 패키지 상품 목록 조회
	 * @param DataClass
	 * @return List
	 * @throws Exception
	 */
	public List searchPkgPrdList(HashMap params) throws Exception;

	/**
	 * 패키지 상품 단건 조회
	 * @param DataClass
	 * @return HashMap
	 * @throws Exception
	 */
	public HashMap searchPkgPrd(HashMap params) throws Exception;

	/**
	 * 패키지 상품 등록
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int registPkgPrd(DataClass params) throws Exception;

	/**
	 * 패키지 상품 수정
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int modifyPkgPrd(DataClass params) throws Exception;

	/**
	 * 패키지 상품 삭제 - 플래그값 update
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int deletePkgPrd(DataClass params) throws Exception;

	/* 패키지 상품 등록 */
	String getPkgPrdSequence() throws Exception;
	String getPkgPrdDtlSequence() throws Exception;
	int insertPkgPrd(Map<String, String> params) throws Exception;
	int insertPkgPrdDtl(Map<String, String> params) throws Exception;
	Map<String, Object> selectPkgPrd(@Param("fcltSeq") String fcltSeq) throws Exception;
	List<Map<String, String>> listPkgPrdDtl(@Param("fcltSeq") String fcltSeq) throws Exception;
	List<Map<String, String>> listTotalPkgPrdDtl(@Param("fcltSeq") String fcltSeq) throws Exception;
	int updatePkgPrd(Map<String, String> params) throws Exception;
	int deletePkgPrdDtl(Map<String, String> params) throws Exception;
	int updatePkgPrdDtl(Map<String, String> params) throws Exception;
	int deletePkgPrd(Map<String, String> params) throws Exception;
}
