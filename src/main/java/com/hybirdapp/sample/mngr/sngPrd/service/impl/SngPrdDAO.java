package com.hybirdapp.sample.mngr.sngPrd.service.impl;

import java.util.HashMap;
import java.util.List;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;

@OracleMapper("SngPrdDAO")
public interface SngPrdDAO {	
	
	/**
	 * 단일 상품 목록 건수
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
    public int searchSngPrdListCnt(HashMap params) throws Exception;

	/**
	 * 단일 상품 목록 조회
	 * @param DataClass
	 * @return List
	 * @throws Exception
	 */
    public List searchSngPrdList(HashMap params) throws Exception;

	/**
	 * 단일 상품 단건 조회
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
    public HashMap searchSngPrd(HashMap params) throws Exception;

	/**
	 * 단일 상품 등록
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
    public int registSngPrd(HashMap params) throws Exception;

	/**
	 * 단일 상품 수정
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
    public int modifySngPrd(HashMap params) throws Exception;

	/**
	 * 단일 상품 삭제 - 플래그값 update
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
    public int deleteSngPrd(HashMap params) throws Exception;

    public int isUsedSngPrd(HashMap params) throws Exception;


}
