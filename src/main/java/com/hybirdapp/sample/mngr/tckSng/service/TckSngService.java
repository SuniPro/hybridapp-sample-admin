package com.hybirdapp.sample.mngr.tckSng.service;

import com.hybirdapp.sample.cmmn.DataClass;

import java.util.HashMap;
import java.util.List;

public interface TckSngService {

	/**
	 * 단일 상품 티켓 목록 건수
	 *
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int searchTckSngListCnt(DataClass params) throws Exception;

	/**
	 * 단일 상품명 조회
	 *
	 * @param DataClass
	 * @return List
	 * @throws Exception
	 */
	public List selectSngPrdList(DataClass params) throws Exception;

	public List selectMenuUnitList(DataClass params) throws Exception;

	public List<HashMap> selectUnitProdTicket(DataClass params) throws Exception;

	/**
	 * 단일 상품 티켓 목록 조회
	 *
	 * @param DataClass
	 * @return List
	 * @throws Exception
	 */
	public List searchTckSngList(DataClass params) throws Exception;


	/**
	 * 티켓 분류 목록 조회
	 *
	 * @param DataClass
	 * @return List
	 * @throws Exception
	 */
	public List selectTckGrpList(DataClass params) throws Exception;

	/**
	 * 단일 상품 티켓 단건 조회
	 *
	 * @param DataClass
	 * @return HashMap
	 * @throws Exception
	 */
	public List<HashMap> searchTckSng(DataClass params) throws Exception;


	public HashMap searchTckSng4Np(DataClass params) throws Exception;

	/**
	 * 단일 상품 티켓 등록
	 *
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int registTckSng(DataClass params) throws Exception;

	/**
	 * 단일 상품 티켓 수정
	 *
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int modifyTckSng(DataClass params) throws Exception;

	/**
	 * 단일 상품 티켓 삭제
	 *
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int deleteTckSng(DataClass params) throws Exception;

	/**
	 * 단일상품티켓 Upsert
	 *
	 * @author younghwa
	 */
	public void saveTckSng(DataClass params) throws Exception;


	SertbPmenuVo[] makeUnitTicketData(DataClass params) throws Exception;

	/**
	 * 단일상품티켓 삭제
	 *
	 * @author younghwa
	 */
	public void delTicket(DataClass params) throws Exception;
}
