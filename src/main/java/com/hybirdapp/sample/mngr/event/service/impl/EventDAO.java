package com.hybirdapp.sample.mngr.event.service.impl;

import java.util.List;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.mngr.event.service.EventVO;

@OracleMapper("EventDAO")
public interface EventDAO {

	/***
	 * 이벤트 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<EventVO> searchEventList(EventVO vo) throws Exception;
	
	/***
	 * 이벤트 목록 총건수
	 * @return
	 * @throws Exception
	 */
	public int selectEventTotalCnt(EventVO vo) throws Exception;
	
	/***
	 * 이벤트 상세 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public EventVO searchMngrEvent(EventVO vo) throws Exception;
	
	/***
	 * 이벤트 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int registEvent(EventVO vo) throws Exception;
	
	/***
	 * 이벤트 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int modifyEvent(EventVO vo) throws Exception;
	
	/***
	 * 이벤트 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int removeMngrEvent(EventVO vo) throws Exception;
	

}
