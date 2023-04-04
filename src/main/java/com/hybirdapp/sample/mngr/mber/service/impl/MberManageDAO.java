package com.hybirdapp.sample.mngr.mber.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.mngr.mber.service.MberManageVO;

@OracleMapper("MberManageDAO")
public interface MberManageDAO {

	/***
	 * 멤버 목록 조회
	 * @return
	 * @throws Exception
	 */
	public List<MberManageVO> selectMngrMemberList(MberManageVO vo) throws Exception;
	
	/***
	 * 멤버 목록 전체 건수 조회
	 * @return
	 * @throws Exception
	 */
	public int selectMberManageTotalCnt(MberManageVO vo) throws Exception;
	
	
	/***
	 * 멤버 목록 조회
	 * @return
	 * @throws Exception
	 */
	public MberManageVO selectMngrMemberDetail(MberManageVO vo) throws Exception;
	
	/***
	 * 회원수정
	 * @param excelInfo
	 * @return
	 * @throws Exception
	 */
	public int memberBlock(MberManageVO vo) throws Exception;
	
	/***
	 * 회원 지역 조회
	 * @return
	 * @throws Exception
	 */
	public List<MberManageVO> areaSelect() throws Exception;
	
	/***
	 * 메인 회원 현황
	 * @return
	 * @throws Exception
	 */
	public MberManageVO mainMemberCount() throws Exception;
	
	/***
	 * 메인 회원 현황 금일
	 * @return
	 * @throws Exception
	 */
	public MberManageVO mainTodayMemberCount() throws Exception;
	
	/***
	 * 메인 회원 현황 전일
	 * @return
	 * @throws Exception
	 */
	public MberManageVO mainEveMemberCount() throws Exception;
	
	/***
	 * 회원 판매 현황 조회
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> mainMemberSelaCount () throws Exception;
	
	/***
	 * 회원 판매 현황 조회 시설
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> mainMemberSelaFcltCount () throws Exception;
	
	/***
	 * 회원 판매 현황 조회 전일
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> mainMemberSelaEveCount () throws Exception;
	
	/***
	 * 회원 매출 현황 조회
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> mainMemberSelngCount () throws Exception;
	
	/***
	 * 회원 매출 현황 조회 시설
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> mainMemberSelngFcltCount () throws Exception;
	
	/***
	 * 회원 매출 현황 조회 전일
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> mainMemberSelngEveCount () throws Exception;

	/***
	 * 회원 지역 조회
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> execSqlString ( String sql ) throws Exception;
	
}

