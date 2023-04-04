package com.hybirdapp.sample.mngr.answer.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hybirdapp.sample.cmmn.SessionUtil;
import com.hybirdapp.sample.mngr.answer.service.AnswerVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.mngr.answer.service.AnswerService;

@Service("AnswerService")
public class AnswerServiceImpl implements AnswerService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "AnswerDAO")
	private AnswerDAO answerDAO;
	
	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;
	
	
	@Override
	public List<AnswerVO> searchAnswerList(AnswerVO vo) throws Exception {
		
		List<AnswerVO> resultList = null;
		
		try {
			resultList = answerDAO.selectAnswerList(vo);
			vo.setTotPageCnt(answerDAO.selectAnswerTotalCnt(vo));
			
		} catch (Exception e) {
			logger.error("searchAnswerList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return resultList;
	}

	@Override
	public AnswerVO searchDetailInqry(AnswerVO vo) throws Exception {

		AnswerVO inqry = new AnswerVO();
		
		try {
			inqry = answerDAO.selectMngrInqury(vo);
			
		} catch (Exception e) {
			logger.error("searchDetailInqry", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return inqry;
	}

	@Override
	public AnswerVO searchDetailAnswer(AnswerVO vo) throws Exception {
		
		AnswerVO answer = new AnswerVO();
		
		try {
			answer = answerDAO.selectMngrAnswer(vo);
			
		} catch (Exception e) {
			logger.error("searchDetailAnswer", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return answer;
	}

	@Override
	public List<AnswerVO> searchDetailInqryHist(AnswerVO vo) throws Exception {
		
		List<AnswerVO> resultList = null;
		
		try {
			resultList = answerDAO.searchDetailInqryHist(vo);
			
		} catch (Exception e) {
			logger.error("searchDetailInqryHist", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return resultList;
	}
	
	@Override
	public List<AnswerVO> searchDetailAnswerHist(AnswerVO vo) throws Exception {
		
		List<AnswerVO> resultList = null;
		
		try {
			resultList = answerDAO.searchDetailAnswerHist(vo);
			
		} catch (Exception e) {
			logger.error("searchDetailAnswerHist", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return resultList;
	}
	
	@Override
	public int registAnswer(AnswerVO vo) throws Exception {
		
		
		int result = 0;
		
		try {
			
			result = answerDAO.insertAnswer(vo);
			
		} catch (Exception e) {
			logger.error("registAnswer", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.insert", null));
		};
		
		return result;
	}

	@Override
	public int  modifyAnswer(AnswerVO vo) throws Exception {
		
		int result = 0;
		
		try {
			
			answerDAO.insertAnswerHist(vo);
			result = answerDAO.updateAnswer(vo);
		} catch (Exception e) {
			logger.error("modifyAnswer", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.update", null));
		}
		
		return result;
	}
	
	@Override
	public int removeAnswer(AnswerVO vo) throws Exception {
		
		int result = 0;
		
		try {
			cmmnFileSaveService.deleteFileAll(vo.getAnswerFileGrpSn());
			result = answerDAO.deleteAnswer(vo);
			
		} catch (Exception e) {
			logger.error("removeAnswer", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.delete", null));
		}
		
		return result;
	}

	@Override
	public List<AnswerVO> answerMngrManagerSelect(AnswerVO vo) throws Exception {

		List<AnswerVO> resultList = null;
		
		try {
			resultList = answerDAO.answerMngrManagerSelect(vo);
			
		} catch (Exception e) {
			logger.error("answerMngrManagerSelect", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return resultList;
	}

	@Override
	public int answerMngrManagerRegist(List<Map<String, Object>> answerManagerList) throws Exception {
		int result = 0;
		try {
			if (answerManagerList != null && answerManagerList.size() > 0) {
				for (Map<String, Object> map : answerManagerList) {
					if(!"".equals(map.get("inqryMngrId")) && !"".equals(map.get("inqryMngrEmail"))) {
						map.put("isrtIp", SessionUtil.getUserIp());
						map.put("updtIp", SessionUtil.getUserIp());
						answerDAO.answerMngrManagerRegist(map);
					}
				}
			}
		} catch (Exception e) {
			logger.error("answerMngrManagerSelect", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.update", null));
		}
		
		return result;
	}
	
	@Override
	public List<AnswerVO> mainInqryList(AnswerVO vo) throws Exception {
		
		List<AnswerVO> resultList = null;
		
		try {
			resultList = answerDAO.mainInqryList(vo);
			
		} catch (Exception e) {
			logger.error("mainInqryList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return resultList;
	}
}
