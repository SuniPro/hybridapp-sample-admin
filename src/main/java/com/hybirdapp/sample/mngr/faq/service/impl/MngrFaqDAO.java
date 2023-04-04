package com.hybirdapp.sample.mngr.faq.service.impl;

import java.util.List;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.mngr.faq.service.MngrFaqVO;

@OracleMapper("MngrFaqDAO")
public interface MngrFaqDAO {

	/***
	 * Faq 목록 조회
	 * @return
	 * @throws Exception
	 */
	public List<MngrFaqVO> selectMngrFaqList(MngrFaqVO vo) throws Exception;
	
	/***
	 * Faq 상세 조회
	 * @return
	 * @throws Exception
	 */
	public MngrFaqVO selectMngrFaq(MngrFaqVO vo) throws Exception;
	
	/***
	 * Faq 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insertMngrFaq(MngrFaqVO vo) throws Exception;
	
	/***
	 * Faq 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updateMngrFaq(MngrFaqVO vo) throws Exception;
	
	/***
	 * Faq 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int deleteMngrFaq(MngrFaqVO vo) throws Exception;
	
	/***
	 * Faq 총건수 조회
	 * @return
	 * @throws Exception
	 */
	public int selectMngrFaqTotalCnt(MngrFaqVO vo) throws Exception;

	
}
