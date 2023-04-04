package com.hybirdapp.sample.cmmn.sys.service.impl;

import java.util.HashMap;
import java.util.List;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.cmmn.sys.service.ClCdVO;
import com.hybirdapp.sample.cmmn.sys.service.ClGrpVO;

@OracleMapper("CodeMngDAO")
public interface CodeMngDAO {

	/**
	 * 분류코드 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<ClGrpVO> searchClGrpList(ClGrpVO vo) throws Exception;

	/**
	 * 분류코드 토탈 카운트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int searchClGrpCount() throws Exception;
	
	/**
	 * 분류코드 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int registClgrp(ClGrpVO vo) throws Exception;
	
	/**
	 * 분류코드 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int modifyClGrp(ClGrpVO vo) throws Exception;
	
	/**
	 * 분류코드 상세 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ClGrpVO searchClGrp(ClGrpVO vo) throws Exception;
	
	
	/**
	 * 공통코드 상세 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<ClCdVO> searchClCdList(ClCdVO vo) throws Exception;
	
	/**
	 * 공통코드 토탈 카운트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int searchClCdCount() throws Exception;
	
	/**
	 * 공통코드 상세 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int registClCd(ClCdVO vo) throws Exception;
	
	/**
	 * 공통코드 상세 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int modifyClCd(ClCdVO vo) throws Exception;
	
	
	/**
	 * 공통코드 상세 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ClCdVO searchClCd(ClCdVO vo) throws Exception;

	/**
	 * 분류코드 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int deleteClGrp(ClCdVO vo) throws Exception;
	
	/**
	 * 공통코드 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int deleteClCd(ClCdVO vo) throws Exception;
	
	/* 시설목록 조회 */
	public List selectFcltList(HashMap params) throws Exception;

	/**
	 * 상품그룹목록 조회
	 * @param HashMap
	 * @throws Exception
	 */
	public List selectGrpList(HashMap map) throws Exception;
}
