package com.hybirdapp.sample.mngr.answer.service.impl;

import java.util.List;
import java.util.Map;

import com.hybirdapp.sample.mngr.answer.service.AnswerVO;
import com.hybirdapp.sample.cmmn.mapper.OracleMapper;

@OracleMapper("AnswerDAO")
public interface AnswerDAO {

	/***
	 * 1:1 문의 답변 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AnswerVO> selectAnswerList(AnswerVO vo) throws Exception;
	
	/***
	 * 1:1 문의 답변 목록 총건수
	 * @return
	 * @throws Exception
	 */
	public int selectAnswerTotalCnt(AnswerVO vo) throws Exception;
	
	/***
	 * 1:1 문의 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public AnswerVO selectMngrInqury(AnswerVO vo) throws Exception;
	
	/***
	 * 1:1 문의 단변 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public AnswerVO selectMngrAnswer(AnswerVO vo) throws Exception;
	
	/***
	 * 1:1답변 히스토리 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AnswerVO> searchDetailAnswerHist(AnswerVO vo) throws Exception;
	
	/***
	 * 1:1 문의 리스토리 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AnswerVO> searchDetailInqryHist(AnswerVO vo) throws Exception;
	
	/***
	 * 답변 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insertAnswer(AnswerVO vo) throws Exception;
	
	/***
	 * 답변 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updateAnswer(AnswerVO vo) throws Exception;
	
	/***
	 * 답변 해스토리 저장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insertAnswerHist(AnswerVO vo) throws Exception;
	
	/***
	 * 답변 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int deleteAnswer(AnswerVO vo) throws Exception;

	/***
	 * 1:1문의 담당자 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AnswerVO> answerMngrManagerSelect(AnswerVO vo) throws Exception;
	
	/***
     * 1:1문의 담당자 등록
     * @param vo
     * @return
     * @throws Exception
     */
    public int answerMngrManagerRegist(Map<String, Object> answerManagerList) throws Exception;
	
    
    /***
     * 1:1문의 담당자 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public List<AnswerVO> mainInqryList(AnswerVO vo) throws Exception;
}
