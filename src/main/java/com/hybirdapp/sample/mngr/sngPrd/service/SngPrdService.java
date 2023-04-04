package com.hybirdapp.sample.mngr.sngPrd.service;

import java.util.HashMap;
import java.util.List;

import com.hybirdapp.sample.cmmn.DataClass;

public interface SngPrdService {
	
	/**
	 * 단일 상품 목록 건수
	 * 
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int searchSngPrdListCnt(DataClass params) throws Exception;
	
	/**
	 * 단일 상품 목록 조회
	 * 
	 * @param DataClass
	 * @return List
	 * @throws Exception
	 */
    public List searchSngPrdList(DataClass params) throws Exception;
    
	/**
	 * 단일 상품 단건 조회
	 * 
	 * @param DataClass
	 * @return HashMap
	 * @throws Exception
	 */
    public HashMap searchSngPrd(DataClass params) throws Exception;
    
	/**
	 * 단일 상품 등록
	 * 
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
    public int registSngPrd(DataClass params) throws Exception;
    
	/**
	 * 단일 상품 수정
	 * 
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
    public int modifySngPrd(DataClass params) throws Exception;
    
	/**
	 * 단일 상품 삭제
	 * 
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
    public int deleteSngPrd(DataClass params) throws Exception;

	int isUsedSngPrd(DataClass params) throws Exception;
}
