package com.hybirdapp.sample.mngr.auth.service;

import java.util.List;

public interface AuthService {

	
	/**
	 * 권한 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AuthVO> selectAuthList(AuthVO vo) throws Exception;
	
	/**
	 * 권한 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public AuthVO selectAuthOne(AuthVO vo) throws Exception;

	/**
	 * 권한별 메뉴 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AuthVO> selectAuthMenuList(AuthVO vo) throws Exception;
	
	/**
	 * 메뉴 등록
	 * @param vo
	 * @throws Exception
	 */
	public void registAuth(AuthVO vo) throws Exception;
	
	/**
	 * 메뉴 수정
	 * @param vo
	 * @throws Exception
	 */
	public void updateAuth(AuthVO vo) throws Exception;
	
	/**
	 * 권한 삭제(GYTS_AUTH)
	 * @param checkList
	 * @throws Exception
	 */
	public void authRemove(AuthVO vo) throws Exception;
	
	/**
	 * 권한 삭제 여부 확인
	 * @param checkList
	 * @throws Exception
	 */
	public AuthVO authRemoveCheck(AuthVO vo) throws Exception;
	
	/**
	 * 권한 지정된 메뉴 가져옴
	 * @param authCd
	 * @return
	 * @throws Exception
	 */
	public List<?> selectMenu(String authCd) throws Exception;
	
	/**
	 * 선택한 메뉴 저장
	 * @param checkArr
	 * @throws Exception
	 */
	public void authMenuCheckSave(List<String> checkArr,AuthVO vo ) throws Exception;
	
	/**
	 * 선택 안한 메뉴 저장
	 * @param unCheckArr
	 * @throws Exception
	 */
	public void authMenuNotCheckSave(List<String> unCheckArr) throws Exception;
}
