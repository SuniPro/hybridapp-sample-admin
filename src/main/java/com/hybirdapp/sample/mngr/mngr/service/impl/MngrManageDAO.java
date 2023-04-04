package com.hybirdapp.sample.mngr.mngr.service.impl;

import java.util.List;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.mngr.mber.service.MberManageVO;
import com.hybirdapp.sample.mngr.mngr.service.MngrManageVO;

@OracleMapper("MngrManageDAO")
public interface MngrManageDAO {

	/***
	 * 관리자 목록 조회
	 * @return
	 * @throws Exception
	 */
	public List<MngrManageVO> selectMngrManageList(MngrManageVO vo) throws Exception;
	
	/***
	 * 관리자 이력 조회
	 * @return
	 * @throws Exception
	 */
	public List<MngrManageVO> mngrHistList(MngrManageVO vo) throws Exception;
	
	/***
	 * 관리자 이력 총 건수 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int mngrHistTotalCnt(MngrManageVO vo) throws Exception;	
	
	/***
	 * 관리자 조회
	 * @return
	 * @throws Exception
	 */
	public MngrManageVO selectMngrManage(MngrManageVO vo) throws Exception;
	
	/***
	 * 관리자 조회
	 * @return
	 * @throws Exception
	 */
	public List<MngrManageVO> selectMngrManageGradList(MngrManageVO vo) throws Exception;
	
	/***
	 * 아이디 중복 여부
	 * @return
	 * @throws Exception
	 */
	public int selectMngrIdCo(MngrManageVO vo) throws Exception;
	
	/***
	 * 비밀번호 일치 여부
	 * @return
	 * @throws Exception
	 */
	public int searchMngrPwChk(MngrManageVO vo) throws Exception;
	
	/***
	 * 관리자 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insertMngrManage(MngrManageVO vo) throws Exception;
	
	/***
	 * 로그인 오류 횟수 초기화
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int loginAttemptReset(MngrManageVO vo) throws Exception;
	
	/***
	 * 관리자 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updateMngrManage(MngrManageVO vo) throws Exception;
	
	/***
	 * 관리자 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int modifyMngrHist(MngrManageVO vo) throws Exception;
	
	/***
	 * 관리자 수정 이력 저장
	 * @return
	 * @throws Exception
	 */
	public int mngrWorkHist(MberManageVO vo) throws Exception;
	/***
	 * 비밀번호 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updateMngrManagePwd(MngrManageVO vo) throws Exception;
	
	/***
	 * 관리자 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int deleteMngrManage(MngrManageVO vo) throws Exception;
	
	/***
	 * 관리자 권한 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insertMngrManageAuth(MngrManageVO vo) throws Exception;
	
	/***
	 * 관리자 권한 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updateMngrManageAuth(MngrManageVO vo) throws Exception;
	
	/***
	 * 관리자 목록 총 건수 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectMngrManageTotalCnt(MngrManageVO vo) throws Exception;	

}
