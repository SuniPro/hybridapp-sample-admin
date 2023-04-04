package com.hybirdapp.sample.mngr.event.service;

import java.util.List;
import java.util.Map;

public interface EventService {

	/***
	 * 이벤트 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<EventVO> searchEventList(EventVO vo) throws Exception;
	
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
	 */
	public int registEvent(List<Map<String, Object>> map, EventVO vo) throws Exception;
	
	/***
	 * 이벤트 수정
	 * @param vo
	 * @throws Exception
	 */
	public int modifyEvent(List<Map<String, Object>> listMap, EventVO vo) throws Exception;
	
	/***
	 * 이벤트 수정
	 * @param vo
	 * @throws Exception
	 */
	public int removeMngrEvent( EventVO vo) throws Exception;
	
	
}
