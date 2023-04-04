package com.hybirdapp.sample.mngr.auth.service.impl;

import java.util.List;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.mngr.auth.service.AuthVO;

@OracleMapper("AuthDAO")
public interface AuthDAO {
		
	/**
	 * 권한 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AuthVO> selectAuthList(AuthVO vo) throws Exception;
	/**
	 * 권한 단건
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public AuthVO selectAuthOne(AuthVO vo) throws Exception;
	
	/**
	 * 권한 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insertAuth(AuthVO vo) throws Exception;
	/**
	 * 권한 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updateAuth(AuthVO vo) throws Exception;
	
	/**
	 * 권한 삭제(GYTS_AUTH)
	 * @param checkList
	 * @return
	 * @throws Exception
	 */
	public int authRemove(AuthVO vo) throws Exception;
	
	/**
	 * 권한 삭제 여부 확인
	 * @param checkList
	 * @return
	 * @throws Exception
	 */
	public AuthVO authRemoveCheck(AuthVO vo) throws Exception;
	
	/**
	 * 권한 메뉴 삭제(GYTS_AUTH_MENU)
	 * @param checkList
	 * @return
	 * @throws Exception
	 */
	public int authMenuRemove(AuthVO vo) throws Exception;
	
	/**
	 * 권한별 메뉴 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AuthVO> selectAuthMenuList(AuthVO vo) throws Exception;
		
	/**
	 * 권한 지정된 메뉴 가져옴
	 * @param authCd
	 * @return
	 * @throws Exception
	 */
	public List<?> selectMenu(String authCd) throws Exception;
	
	/**
	 * 선택한 메뉴 메뉴 저장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int authMenuCheckSave(AuthVO vo) throws Exception;
	
	/**
	 * 선택 안한 메뉴 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int authMenuNotCheckSave(AuthVO vo) throws Exception;
	
	/**
	 * 선택 안한 메뉴 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public AuthVO authRemoveCnt(AuthVO vo) throws Exception;
	
	/**
	 * 메뉴 리스트 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<String> selectMenuList() throws Exception;
}
