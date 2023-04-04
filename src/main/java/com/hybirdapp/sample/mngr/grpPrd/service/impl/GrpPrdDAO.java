package com.hybirdapp.sample.mngr.grpPrd.service.impl;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;

import java.util.HashMap;
import java.util.List;

@OracleMapper("GrpPrdDAO")
public interface GrpPrdDAO {
	/**
	 * 상품 그룹 목록 건수
	 */
	public int searchGrpPrdListCnt(HashMap params) throws Exception;

	/**
	 * 상품 그룹 목록 조회
	 */
	public List searchGrpPrdList(HashMap params) throws Exception;

	/**
	 * 상품 그룹 단건 조회
	 */
	public HashMap searchGrpPrd(HashMap params) throws Exception;

	/**
	 * 상품 그룹 수정
	 */
	public int upsertGrpPrd(HashMap params) throws Exception;

	/**
	 * 상품 그룹 수정_상세설명
	 */
	public int upsertGrpPrdDesc(HashMap params) throws Exception;

	/**
	 * 상품 그룹 삭제 - 플래그값 update
	 */
	public int deleteGrpPrd(HashMap params) throws Exception;

	// gr_code 생성
	public String makeSertbGrcode() throws Exception;

	// gr_loc 추출
	public String getPlaceCode(String fctl_seq) throws Exception;

	public int isUsedGrp(HashMap map) throws Exception ;
}
