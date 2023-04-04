package com.hybirdapp.sample.cmmn.sys.service;

import java.util.List;

import com.hybirdapp.sample.cmmn.DataClass;

public interface CodeMngService {

	/**
	 * 분류코드 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<ClGrpVO> searchClGrpList(ClGrpVO vo) throws Exception;
	
	
	/**
	 * 분류코드 상세 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ClGrpVO searchClGrp(ClGrpVO vo) throws Exception;
	
	
	/**
	 * 분류코드 등록
	 * @param vo
	 * @throws Exception
	 */
	public void registClGrp(ClGrpVO vo) throws Exception;
	
	/**
	 * 분류코드 수정
	 * @param vo
	 * @throws Exception
	 */
	public void modifyClGrp(ClGrpVO vo) throws Exception;
	
	
	/**
	 * 공통코드 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<ClCdVO> searchClCdList(ClCdVO vo) throws Exception;
	
	/**
	 * 공통코드 상세 등록
	 * @param vo
	 * @throws Exception
	 */
	public void registClCd(ClCdVO vo) throws Exception;
	
	/**
	 * 공통코드 상세 수정
	 * @param vo
	 * @throws Exception
	 */
	public void modifyClCd(ClCdVO vo) throws Exception;
	
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
	 * @throws Exception
	 */
	public void deleteClGrp(ClCdVO vo) throws Exception;
	
	/**
	 * 공통코드 삭제
	 * @param vo
	 * @throws Exception
	 */
	public void deleteClCd(ClCdVO vo) throws Exception;

	/* 시설목록 조회 */
	public List selectFcltList(DataClass params) throws Exception;

	/**
	 * 상품그룹목록 조회
	 * @param DataClass
	 * @throws Exception
	 */
	public List selectGrpList(DataClass params) throws Exception;
}
