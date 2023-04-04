package com.hybirdapp.sample.mngr.stplat.service;

import java.util.List;

public interface StplatService {
	
	/**
	 * 약관 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<StplatVO> selectStplatList(StplatVO vo) throws Exception;

	/**
	 * 약관 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void registStplat(StplatVO vo) throws Exception;
	
	/**
	 * 약관 등록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public StplatVO searchStplatRegist(StplatVO vo) throws Exception;
	
	/**
	 * 약관 상세 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public StplatVO searchStplatDetail(StplatVO vo) throws Exception;
	
	/**
	 * 약관 사용/미사용 저장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updateUseAt(List<StplatVO> vo) throws Exception;
}
