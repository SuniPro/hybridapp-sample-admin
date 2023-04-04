package com.hybirdapp.sample.mngr.menu.service;


import java.util.List;

public interface MngrMenuService {

	/**
	 * 메뉴 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<MngrMenuVO> selectMngrMenuList(MngrMenuVO vo) throws Exception;
	
	/**
	 * 메뉴 트리 목록
	 * @return
	 * @throws Exception
	 */
	public List<MngrMenuVO> selectMngrMenuTreeList() throws Exception;
	
	/**
	 * 메뉴 등록/추가 페이지 리스트 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MngrMenuVO searchMenuCd(MngrMenuVO vo) throws Exception;
	
	/**
	 * 메뉴 등록
	 * @param vo
	 * @throws Exception
	 */
	public void registMngrMenu(MngrMenuVO vo) throws Exception;
	
	/**
	 * 메뉴 상세/수정 페이지 리스트 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MngrMenuVO selectMenuDetailList(MngrMenuVO vo) throws Exception;
	
	/**
	 * 메뉴 삭제
	 * @param vo
	 * @throws Exception
	 */
	public void deleteMngrMenu(MngrMenuVO vo) throws Exception;


}
