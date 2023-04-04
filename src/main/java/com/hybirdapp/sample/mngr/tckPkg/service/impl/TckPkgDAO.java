package com.hybirdapp.sample.mngr.tckPkg.service.impl;

import java.util.HashMap;
import java.util.List;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.mngr.tckSng.service.SertbPmenuVo;

@OracleMapper("TckPkgDAO")
public interface TckPkgDAO {

	/**
	 * 패키지 상품 티켓 조회 건수
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	public int searchTckPkgListCnt(HashMap params) throws Exception;
	
	/**
	 * 패키지 상품명 조회
	 * @param 
	 * @return List
	 * @throws Exception
	 */
	public List searchMenuPkgNmList() throws Exception;
	
	/**
	 * 패키지 상품 티켓 조회
	 * @param HashMap
	 * @return List
	 * @throws Exception
	 */
	public List searchTckPkgList(HashMap params) throws Exception;
	
	/**
	 * 패키지 상품 티켓 정보 단일 조회
	 * @param HashMap
	 * @return HashMap
	 * @throws Exception
	 */
	public HashMap searchTckPkg(HashMap params) throws Exception;

	public List<HashMap> getPkgTckGrp() throws Exception;


	/**
	 * 패키지 상품 티켓 정보 등록
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	public int registTckPkg(HashMap params) throws Exception;
	
	/**
	 * 패키지 상품 티켓 정보 수정
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */
	public int modifyTckPkg(HashMap params) throws Exception;
	
	/**
	 * 패키지 상품 티켓 정보 삭제
	 * @param HashMap
	 * @return int
	 * @throws Exception
	 */

	public int deleteTckPkg(HashMap params) throws Exception;

	public HashMap searchPkgTicket(HashMap params) throws Exception;


	public int getMpTicketSeq() throws Exception;
	public int mergeMenuPkgTicket(SertbPmenuVo vo) throws Exception;
	public int mergeMenuPkgTicketDtl(SertbPmenuVo vo) throws Exception;

	public int mergePPkgMenu(SertbPmenuVo vo) throws Exception;
	public int mergeMenuPkgTicketPrice(SertbPmenuVo vo) throws Exception;
	public int updatePkgPMenu(SertbPmenuVo vo) throws Exception;
	public int insertPkgPMenu(SertbPmenuVo vo) throws Exception;


	public List<HashMap> getPkgUnitTickets(String seq) throws Exception;

	void updatePkgTicket(HashMap map) throws Exception;

	void updatePkgTicketUse(String seq) throws Exception;
	HashMap searchTckPkg4Np(HashMap map) throws Exception;
	List<HashMap> searchPkgTicketPopup(HashMap map) throws Exception;
	void removePkgTicket(HashMap map) throws Exception;
	void removePkg(HashMap map) throws Exception;
	List<HashMap> getPkgUnitTicketsPriceSum(HashMap map) throws Exception;
	List<HashMap> getPkgMenuInfo(HashMap map) throws Exception;

	void insertPkgMenu(HashMap map) throws Exception;
}