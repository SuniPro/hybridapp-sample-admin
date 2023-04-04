package com.hybirdapp.sample.cmmn.sys.service;

import java.util.List;

public interface AuthorMngService {

	/***
	 * 권한 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AuthorMngVO> searchAuthorMngList(AuthorMngVO vo) throws Exception;
	
	/***
	 * 권한 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public AuthorMngVO searchAuthorMng(AuthorMngVO vo) throws Exception;
	
	/***
	 * 권한 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int registAuthorMng(AuthorMngVO vo) throws Exception;
	
	/***
	 * 권한 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int modifyAuthorMng(AuthorMngVO vo) throws Exception;
	
	/***
	 * 권한 메뉴 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AuthorMngMenuVO> searchAuthorMngMenuList(AuthorMngVO vo) throws Exception;
	
	/***
	 * 권한 메뉴(버튼) 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int registAuthorMngMenu(AuthorMngMenuVO vo) throws Exception;
	
	/***
	 * 권한 언어 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int removeAuthorMngLang(AuthorMngDtlVO vo) throws Exception;
	
	/***
	 * 권한 공통 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AuthorMngVO> searchAuthorCmmnList(AuthorMngVO vo) throws Exception;
}
