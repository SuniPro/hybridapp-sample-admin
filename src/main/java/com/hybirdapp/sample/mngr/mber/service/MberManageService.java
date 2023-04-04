package com.hybirdapp.sample.mngr.mber.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MberManageService {
	
	/***
	 * 회원 목록 조회
	 * @param vo	
	 * @return
	 * @throws Exception
	 */
	public List<MberManageVO> searchMberManageList(MberManageVO vo) throws Exception;

	/***
	 * 회원 목록 조회
	 * @param vo	
	 * @return
	 * @throws Exception
	 */
	public MberManageVO selectMngrMemberDetail(MberManageVO vo) throws Exception;
	
	/***
	 * 회원 수정
	 * @param excelInfo
	 * @throws Exception
	 */
	public int memberBlock(MberManageVO vo) throws Exception;
	
	/***
	 * 회원 지역 조회
	 * @param vo	
	 * @return
	 * @throws Exception
	 */
	public List<MberManageVO> areaSelect() throws Exception;
	
	/***
	 * 메인 회원 현황
	 * @param vo	
	 * @return
	 * @throws Exception
	 */
	public MberManageVO mainMemberCount() throws Exception;
	
	/***
	 * 메인 회원 현황 금일
	 * @param vo	
	 * @return
	 * @throws Exception
	 */
	public MberManageVO mainTodayMemberCount() throws Exception;
	
	/***
	 * 메인 회원 현황 전일
	 * @param vo	
	 * @return
	 * @throws Exception
	 */
	public MberManageVO mainEveMemberCount() throws Exception;

	/***
	 * 메인 판매 현황
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> mainMemberSelaCount() throws Exception;
	
	/***
	 * 메인 판매 시설 현황
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> mainMemberSelaFcltCount() throws Exception;
	
	/***
	 * 메인  전날  현황 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> mainMemberSelaEveCount() throws Exception;
	
	/***
	 * 메인 매출 현황
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> mainMemberSelngCount() throws Exception;
	
	/***
	 * 메인 매출 시설 현황
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> mainMemberSelngFcltCount() throws Exception;
	
	/***
	 * 메인 매출 전날  현황 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> mainMemberSelngEveCount() throws Exception;
	
	/***
	 * SQL
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> execSqlString( String  sql ) throws Exception;
	
}

