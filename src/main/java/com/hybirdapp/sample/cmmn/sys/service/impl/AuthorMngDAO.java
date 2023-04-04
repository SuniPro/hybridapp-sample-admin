package com.hybirdapp.sample.cmmn.sys.service.impl;

import java.util.List;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.cmmn.sys.service.AuthorMngDtlVO;
import com.hybirdapp.sample.cmmn.sys.service.AuthorMngMenuVO;
import com.hybirdapp.sample.cmmn.sys.service.AuthorMngVO;

@OracleMapper("AuthorMngDAO")
public interface AuthorMngDAO {

	/***
	 * 권한 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AuthorMngVO> selectAuthorMngList(AuthorMngVO vo) throws Exception;
	
	/***
	 * 권한 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public AuthorMngVO selectAuthorMng(AuthorMngVO vo) throws Exception;
	
	/***
	 * 권한 언어목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AuthorMngDtlVO> selectAuthorMngDtlList(AuthorMngVO vo) throws Exception;
	
	
	/***
	 * 권한 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insertAuthorMng(AuthorMngVO vo) throws Exception;
	
	/***
	 * 권한 언어 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insertAuthorMngDtl(AuthorMngDtlVO vo) throws Exception;
	
	/***
	 * 권한 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updateAuthorMng(AuthorMngVO vo) throws Exception;
	
	/***
	 * 권한 언어 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updateAuthorMngDtl(AuthorMngDtlVO vo) throws Exception;
	
	/***
	 * 기본사용 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updateAuthorMngBasicYn(AuthorMngVO vo) throws Exception;
	
	/***
	 * 권한 메뉴 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AuthorMngMenuVO> selectAuthorMngMenuList(AuthorMngVO vo) throws Exception;
	
	/***
	 * 권한 메뉴 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insertAuthorMngMenu(AuthorMngMenuVO vo) throws Exception;
	
	/***
	 * 권한 메뉴 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insertAuthorMngMenuBtn(AuthorMngMenuVO vo) throws Exception;
	
	/***
	 * 권한 메뉴 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int deleteAuthorMngMenu(AuthorMngMenuVO vo) throws Exception;
	
	/***
	 * 권한 메뉴 버튼 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int deleteAuthorMngMenuBtn(AuthorMngMenuVO vo) throws Exception;
	
	/***
	 * 권한 언어 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int deleteAuthorMngLang(AuthorMngDtlVO vo) throws Exception;
	
	/***
	 * 권한 공통 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AuthorMngVO> selectAuthorCmmnList(AuthorMngVO vo) throws Exception;
	
	/***
	 * 메타 토탈 카운트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectAuthorMngTotalCount() throws Exception;
	
}
