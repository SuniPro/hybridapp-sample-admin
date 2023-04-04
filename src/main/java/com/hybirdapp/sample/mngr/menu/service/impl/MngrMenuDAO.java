package com.hybirdapp.sample.mngr.menu.service.impl;

import java.util.List;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.mngr.menu.service.MngrMenuVO;

@OracleMapper("MngrMenuDAO")
public interface MngrMenuDAO {

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
	 * 메뉴 추가 시 메뉴 코드 자동 저장을 위함
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MngrMenuVO selectMenuCd(MngrMenuVO vo) throws Exception;
	
	/**
	 * 메뉴 트리 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insertMngrMenu(MngrMenuVO vo) throws Exception;
	
	/**
	 * 메뉴 트리 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updateMngrMenu(MngrMenuVO vo) throws Exception;
	
	/**
	 * 수정할 메뉴 select
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MngrMenuVO selectMenuDetailList(MngrMenuVO vo) throws Exception;
	
	/**
	 * 메뉴 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int deleteMngrMenu(MngrMenuVO vo) throws Exception;


	
}
