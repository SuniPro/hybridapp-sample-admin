package com.hybirdapp.sample.mngr.mngr.service;

import java.util.List;

public interface MngrManageService {

	/***
	 * 관리자 관리 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<MngrManageVO> searchMngrManageList(MngrManageVO vo) throws Exception;
	
	/***
	 * 관리자 관리 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MngrManageVO searchMngrManage(MngrManageVO vo) throws Exception;
	
	/***
	 * 관리자 등급 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<MngrManageVO> searchMngrManageGradList(MngrManageVO vo) throws Exception;
	
	/***
	 * 관리자 정보 수정
	 * @param vo
	 * @throws Exception
	 */
	public void modifyMngrManage(MngrManageVO vo) throws Exception;
	
	/***
	 * 아이디 중복 여부
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int searchMngrIdCo(MngrManageVO vo) throws Exception;
	
	/***
	 * 비밀번호 일치 여부
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int searchMngrPwChk(MngrManageVO vo) throws Exception;
	
	/***
	 * 관리자 등록
	 * @param vo
	 * @throws Exception
	 */
	public void registMngrManage(MngrManageVO vo) throws Exception;
	
	/***
	 * 관리자 비밀번호 수정
	 * @param vo
	 * @throws Exception
	 */
	public void modifyMngrManagePwd(MngrManageVO vo) throws Exception;
	
	/***
	 * 관리자 삭제 
	 * @param vo
	 * @throws Exception
	 */
	public void removeMngrManage(MngrManageVO vo) throws Exception;
	
}
