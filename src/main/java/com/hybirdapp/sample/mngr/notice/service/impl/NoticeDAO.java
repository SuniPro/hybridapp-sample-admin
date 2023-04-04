package com.hybirdapp.sample.mngr.notice.service.impl;

import java.util.List;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.mngr.notice.service.NoticeVO;

@OracleMapper("NoticeDAO")
public interface NoticeDAO {

	/***
	 * 공지사항 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<NoticeVO> selectNoticeList(NoticeVO vo) throws Exception;
	
	/***
	 * 공지사항 목록 총건수
	 * @return
	 * @throws Exception
	 */
	public int selectNoticeTotalCnt(NoticeVO vo) throws Exception;
	
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
	 * @return
	 * @throws Exception
	 */
	public int registNotice(NoticeVO vo) throws Exception;
	
	/***
	 * 공지사항 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updateNotice(NoticeVO vo) throws Exception;
	
	/***
	 * 공지사항 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int removeMngrNotice(NoticeVO vo) throws Exception;
	
	/***
	 * 메인 공지사항 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<NoticeVO> mainNoticeList(NoticeVO vo) throws Exception;
	
}
