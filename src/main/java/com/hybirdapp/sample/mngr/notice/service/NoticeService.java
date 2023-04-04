package com.hybirdapp.sample.mngr.notice.service;

import java.util.List;
import java.util.Map;

public interface NoticeService {

	/***
	 * 공지사항 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<NoticeVO> searchNoticeList(NoticeVO vo) throws Exception;
	
	/***
	 * 공지사항 상세 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public NoticeVO searchMngrNotice(NoticeVO vo) throws Exception;
	
	/***
	 * 공지사항 등록
	 * @param vo
	 */
	public int registNotice(List<Map<String, Object>> map, NoticeVO vo) throws Exception;
	
	/***
	 * 공지사항 수정
	 * @param vo
	 * @throws Exception
	 */
	public int modifyNotice(List<Map<String, Object>> listMap, NoticeVO vo) throws Exception;
	
	/***
	 * 공지사항 삭제
	 * @param vo
	 * @throws Exception
	 */
	public int removeMngrNotice( NoticeVO vo) throws Exception;
	
	/***
	 * 메인 공지사항 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<NoticeVO> mainNoticeList(NoticeVO vo) throws Exception;
}

