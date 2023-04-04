package com.hybirdapp.sample.cmmn.script.service;

import java.util.List;

import com.hybirdapp.sample.cmmn.sys.service.ClCdVO;

public interface ScriptService {

	/***
	 * 공통코드 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<?> selectCmmnCdList(ClCdVO vo) throws Exception;
	
	/**
	 * 첨부파일 정보 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public AttachVO selectAttachInfo(AttachVO vo) throws Exception;

	/***
	 * 관리자 이미지 파일 업로드
	 * @param serverName
	 * @return
	 * @throws Exception
	 */
	public int attachFileSave(AttachVO vo) throws Exception;
	
	
}
