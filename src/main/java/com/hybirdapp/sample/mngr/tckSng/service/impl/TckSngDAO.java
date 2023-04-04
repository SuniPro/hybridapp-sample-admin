package com.hybirdapp.sample.mngr.tckSng.service.impl;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.mngr.tckSng.service.SertbPmenuVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@OracleMapper("TckSngDAO")
public interface TckSngDAO {	
	
	/**
	 * 단일 상품 티켓 목록 건수
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
    public int searchTckSngListCnt(HashMap params) throws Exception;
    
	/**
	 * 단일 상품명 조회
	 * @param HashMap
	 * @return List
	 * @throws Exception
	 */
	public List selectSngPrdList(HashMap params) throws Exception;


	/**
	 * 단일 상품명 조회 - sertb_menu_unit
	 */
	public List<Map> selectMenuUnitList(HashMap params) throws Exception;


	/**
	 * 단일상품티켓조회
	 */
	public List<HashMap> selectUnitProdTicket(HashMap params) throws Exception;

	/**
	 * 단일 상품 티켓 목록 조회
	 * @param HashMap
	 * @return List
	 * @throws Exception
	 */
    public List searchTckSngList(HashMap params) throws Exception;
    
	/**
	 * 티켓 분류 목록 조회
	 * 
	 * @param DataClass
	 * @return List
	 * @throws Exception
	 */
    public List selectTckGrpList(HashMap params) throws Exception;

	/**
	 * 단일 상품 티켓 단건 조회
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
    public List<HashMap> searchTckSng(HashMap params) throws Exception;

    public HashMap searchTckSng4Np(HashMap params) throws Exception;

	/**
	 * 단일 상품 티켓 등록
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
    public int registTckSng(HashMap params) throws Exception;

	/**
	 * 단일 상품 티켓 수정
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
    public int modifyTckSng(HashMap params) throws Exception;

	/**
	 * 단일 상품 티켓 삭제 - 플래그값 update
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
    public int deleteTckSng(HashMap params) throws Exception;

	/*
	 * 단일상품티켓 Upsert
	 * P_MENU
	 */
    public int mergePMenu(SertbPmenuVo vo) throws Exception;
    public int mergeMenuUnitTicket(SertbPmenuVo vo) throws Exception;
    public int mergeMenuUnitTicketPrice(SertbPmenuVo vo) throws Exception;


	/*
	 * 단일티켓상품 채번
	 */
    public int getMuTicketSeq() throws Exception;

	/*
	 * M_MENU 채번
	 */
    public String makeMMenuCode() throws Exception;
    public String makeMMenuCode6(String m_loc) throws Exception;
    public String getMLoc(String fctl_list) throws Exception;


	/*
	 * 단일티켓상품 삭제
	 */
    public void delTicket(String m_menu) throws Exception;

}
