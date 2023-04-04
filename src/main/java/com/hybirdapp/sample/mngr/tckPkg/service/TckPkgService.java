package com.hybirdapp.sample.mngr.tckPkg.service;

import java.util.HashMap;
import java.util.List;

import com.hybirdapp.sample.cmmn.DataClass;

public interface TckPkgService {

	/**
	 * 패키지 상품 티켓 조회 건수
	 */
	public int searchTckPkgListCnt(DataClass params) throws Exception;
	
	/**
	 * 패키지 상품명 조회
	 */
	public List searchMenuPkgNmList() throws Exception;

	public HashMap searchPkgTicket(DataClass params) throws Exception;


	/**
	 * 패키지 상품 티켓 조회
	 */
	public List searchTckPkgList(DataClass params) throws Exception;
	
	/**
	 * 패키지 상품 티켓 정보 단일 조회
	 */
	public HashMap searchTckPkg(DataClass params) throws Exception;

	/**
	 * 패키지티켓분류 조회
	 */
	public List<HashMap> getPkgTckGrp() throws Exception;

	/**
	 * 패키지 상품 티켓 정보 등록
	 */
	public int registTckPkg(DataClass params) throws Exception;
	
	/**
	 * 패키지 상품 티켓 정보 수정
	 */
	public int modifyTckPkg(DataClass params) throws Exception;
	
	/**
	 * 패키지 상품 티켓 정보 삭제
	 */
	public int deleteTckPkg(DataClass params) throws Exception;

	// 패키지상품티켓관리 패키지상품구성  Upsert
	void mergeMenuPkgTicket(DataClass params) throws Exception;

	// 패키지상품티켓관리 패키지상품구성 티켓정보  Upsert
	void mergeMenuPkgUnitTicket(DataClass params) throws Exception;

	List<HashMap> getPkgUnitTickets(String seq) throws Exception;

	void updatePkgTicket(DataClass params) throws Exception;

	HashMap searchTckPkg4Np(DataClass params) throws Exception;

	List<HashMap> searchPkgTicketPopup(DataClass params) throws Exception;

	void removePkg(DataClass params) throws Exception;

	void removePkgTicket(DataClass params) throws Exception;

	List<HashMap> getPkgUnitTicketsPriceSum(DataClass params) throws Exception;

	void makePpkgmenu(DataClass params) throws Exception;
}
