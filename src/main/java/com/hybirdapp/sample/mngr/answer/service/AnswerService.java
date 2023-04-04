package com.hybirdapp.sample.mngr.answer.service;

import java.util.List;
import java.util.Map;

public interface AnswerService {

	
	/***
	 * 1:1 문의 답변 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AnswerVO> searchAnswerList(AnswerVO vo) throws Exception;
	
	/***
	 * 1:1 문의 상세 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public AnswerVO searchDetailInqry(AnswerVO vo) throws Exception;
	
	/***
	 * 1:1 문의 답변 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public AnswerVO searchDetailAnswer(AnswerVO vo) throws Exception;
	
	/***
	 * 1:1 문의 답변 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AnswerVO> searchDetailInqryHist(AnswerVO vo) throws Exception;
	
	/***
	 * 1:1 문의 답변 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AnswerVO> searchDetailAnswerHist(AnswerVO vo) throws Exception;
	
	/***
	 * 답변 등록
	 * @param vo
	 * @throws Exception
	 */
	public int registAnswer(AnswerVO vo) throws Exception;
	
	/***
	 * 답변 수정
	 * @param vo
	 * @throws Exception
	 */
	public int modifyAnswer(AnswerVO vo) throws Exception;
	
	/***
	 * 답변 삭제
	 * @param vo
	 * @throws Exception
	 */
	public int removeAnswer(AnswerVO vo) throws Exception;

	/***
	 * 1:1관리자 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AnswerVO> answerMngrManagerSelect(AnswerVO vo) throws Exception;
	
	/***
	 * 1:1 관리자 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int answerMngrManagerRegist(List<Map<String, Object>> answerManagerList) throws Exception;

	
	/***
	 * 1:1 문의 답변 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AnswerVO> mainInqryList(AnswerVO vo) throws Exception;
}
