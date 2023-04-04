package com.hybirdapp.sample.mngr.stplat.service.impl;

import java.util.List;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.mngr.stplat.service.StplatVO;

@OracleMapper("StplatDAO")
public interface StplatDAO {

	/**
	 * 약관 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<StplatVO> selectStplatList(StplatVO vo) throws Exception;
	
	/***
	 * 약관 목록 총건수
	 * @return
	 * @throws Exception
	 */
	public int selectStplatTotalCnt() throws Exception;
	
	/**
	 * 약관 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insertStplat(StplatVO vo) throws Exception;
	
	/**
	 * 약관 등록 시 나머지 미사용 처리
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updatUseAtRegist(StplatVO vo) throws Exception;
	
	/**
	 * 약관 등록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public StplatVO selectStplatRegist(StplatVO vo) throws Exception;
	
	/**
	 * 약관 상세 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public StplatVO selectStplatDetail(StplatVO vo) throws Exception;
	
	/**
	 * 약관 사용/미사용 저장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updateUseAt(StplatVO vo) throws Exception;
}
