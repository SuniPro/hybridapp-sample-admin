package com.hybirdapp.sample.cmmn.sys.service.impl;

import java.util.List;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.cmmn.sys.service.MenuMngVO;

@OracleMapper("MenuMngDAO")
public interface MenuMngDAO {

	/**
	 * 메뉴 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<MenuMngVO> searchMenuMngList(MenuMngVO vo) throws Exception;

	/**
	 * 메뉴 토탈 카운트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int searchMenuMngTotalCount() throws Exception;
	
	/**
	 * 메뉴 단건 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MenuMngVO searchMenuMng(MenuMngVO vo) throws Exception;
	
	/**
	 * 메뉴 Lang 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<MenuMngVO> searchMenuMngLangList(MenuMngVO vo) throws Exception;
	
	/**
	 * 권한메뉴버튼 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int removeAuthMenuBtn(MenuMngVO vo) throws Exception;

	/**
	 * 권한메뉴매핑 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int removeAuthMenuMapng(MenuMngVO vo) throws Exception;
	
	/**
	 * 메뉴 LANG 삭제 전체
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int removeMenuMngLangAll(MenuMngVO vo) throws Exception;
	
	/**
	 * 메뉴 LANG 삭제 단건
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int removeMenuMngLangOne(MenuMngVO vo) throws Exception;
	
	/**
	 * 메뉴 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int removeMenuMng(MenuMngVO vo) throws Exception;
	
	/**
	 * 메뉴 LANG 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int registMenuMngLang(MenuMngVO vo) throws Exception;
	
	/**
	 * 메뉴 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int registMenuMng(MenuMngVO vo) throws Exception;
}
