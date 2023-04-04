package com.hybirdapp.sample.cmmn.sys.service;

import java.util.List;

public interface MenuMngService {

	/**
	 * 메뉴 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<MenuMngVO> searchMenuMngList(MenuMngVO vo) throws Exception;
	
	/**
	 * 메뉴 LANG 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MenuMngVO searchMenuMngLangList(MenuMngVO vo) throws Exception;
	
	/**
	 * 메뉴 삭제
	 * @param vo
	 * @throws Exception
	 */
	public void removeMenuMng(MenuMngVO vo) throws Exception;
	
	/**
	 * 메뉴 LANG 단건 삭제
	 * @param vo
	 * @throws Exception
	 */
	public void removeMenuMngLangOne(MenuMngVO vo) throws Exception;
	
	/**
	 * 메뉴 LANG 등록
	 * @param vo
	 * @throws Exception
	 */
	public void registMenuMngLang(MenuMngVO vo) throws Exception;
	
	/**
	 * 메뉴 등록
	 * @param vo
	 * @throws Exception
	 */
	public void registMenuMng(MenuMngVO vo) throws Exception;
}
