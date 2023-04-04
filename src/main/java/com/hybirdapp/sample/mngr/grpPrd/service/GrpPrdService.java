package com.hybirdapp.sample.mngr.grpPrd.service;

import java.util.HashMap;
import java.util.List;

import com.hybirdapp.sample.cmmn.DataClass;

public interface GrpPrdService {
	
	/**
	 * 상품 그룹 목록 건수
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int searchGrpPrdListCnt(DataClass params) throws Exception;
	
	/**
	 * 상품 그룹 목록 조회
	 * @param DataClass
	 * @return List
	 * @throws Exception
	 */
	public List searchGrpPrdList(DataClass params) throws Exception;
	
	/**
	 * 상품 그룹 단건 조회
	 * @param DataClass
	 * @return HashMap
	 * @throws Exception
	 */
	public HashMap searchGrpPrd(DataClass params) throws Exception;
	
	/**
	 * 상품 그룹 수정
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int modifyGrpPrd(DataClass params) throws Exception;
	
	/**
	 * 상품 그룹 삭제
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int deleteGrpPrd(DataClass params) throws Exception;

	public int isUsedGrp(DataClass params) throws Exception;
}
