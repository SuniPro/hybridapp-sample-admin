package com.hybirdapp.sample.mngr.faq.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.mngr.faq.service.MngrFaqService;
import com.hybirdapp.sample.mngr.faq.service.MngrFaqVO;

@Service("MngrFaqService")
public class MngrFaqServiceImpl implements MngrFaqService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name = "MngrFaqDAO")
	private MngrFaqDAO mngrFaqDAO;
	
	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;

	@Override
	public List<MngrFaqVO> searchMngrFaqList(MngrFaqVO vo) throws Exception {
		
		List<MngrFaqVO> resultList = null;
		
		try {
			resultList = mngrFaqDAO.selectMngrFaqList(vo);
			
			vo.setTotPageCnt(mngrFaqDAO.selectMngrFaqTotalCnt(vo));
			
		} catch (Exception e) {
			logger.error("searchMngrQnaList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return resultList;
	}

	@Override
	public MngrFaqVO searchMngrFaq(MngrFaqVO vo) throws Exception {
		
		MngrFaqVO result = new MngrFaqVO();
		
		try {
			result = mngrFaqDAO.selectMngrFaq(vo);
		} catch (Exception e) {
			logger.error("searchMngrQna", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}

	@Override
	public int registMngrFaq(MngrFaqVO vo) throws Exception {
		
		int result = 0;
		
		//String fileGrpSn = "";
		
		try {
			
			result = mngrFaqDAO.insertMngrFaq(vo);
			
		} catch (Exception e) {
			logger.error("registMngrQna", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.insert", null));
		}
		
		return result;
	}

	@Override
	public int modifyMngrFaq(MngrFaqVO vo) throws Exception {
		
		int result = 0;
		
		
		try {
			
			result = mngrFaqDAO.updateMngrFaq(vo);
			
		} catch (Exception e) {
			logger.error("modifyMngrQna", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.update", null));
		}
		
		return result;
	}


	@Override
	public void removeMngrFaq(MngrFaqVO vo) throws Exception {
		
		try {
			mngrFaqDAO.deleteMngrFaq(vo);
			
		} catch (Exception e) {
			logger.error("removeMngrQna", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.delete", null));
		}
	}

}
