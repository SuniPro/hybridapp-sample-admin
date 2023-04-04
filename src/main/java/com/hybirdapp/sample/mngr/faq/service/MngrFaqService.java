package com.hybirdapp.sample.mngr.faq.service;

import java.util.List;

public interface MngrFaqService {

	/***
	 * Faq 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<MngrFaqVO> searchMngrFaqList(MngrFaqVO vo) throws Exception;
	
	/***
	 * Faq 상세 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MngrFaqVO searchMngrFaq(MngrFaqVO vo) throws Exception;
	
	/***
	 * Faq 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int registMngrFaq(MngrFaqVO vo) throws Exception;
	
	/***
	 * Faq 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int modifyMngrFaq(MngrFaqVO vo) throws Exception;
	
	
	/***
	 * Faq 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void removeMngrFaq(MngrFaqVO vo) throws Exception;

	
}
