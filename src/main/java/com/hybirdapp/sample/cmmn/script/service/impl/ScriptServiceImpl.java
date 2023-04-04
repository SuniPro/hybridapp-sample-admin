package com.hybirdapp.sample.cmmn.script.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.cmmn.script.service.AttachVO;
import com.hybirdapp.sample.cmmn.script.service.ScriptService;
import com.hybirdapp.sample.cmmn.sys.service.ClCdVO;

@Service("ScriptService")
public class ScriptServiceImpl implements ScriptService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name = "ScriptDAO")
	private ScriptDAO scriptDAO;
	
	@Override
	public List<?> selectCmmnCdList(ClCdVO vo) throws Exception {
		return scriptDAO.selectCmmnCdList(vo);
	}

	@Override
	public AttachVO selectAttachInfo(AttachVO vo) throws Exception {
		return scriptDAO.selectAttachInfo(vo);
	}

	@Override
	public int attachFileSave(AttachVO vo) throws Exception {
		int result = 0;
		try {
			scriptDAO.attachFileSave(vo);
		} catch (Exception e) {
			logger.error("attachFileSave", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.insert", null));
		}

		return result;
	}
}
