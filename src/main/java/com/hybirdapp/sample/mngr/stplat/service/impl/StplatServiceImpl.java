package com.hybirdapp.sample.mngr.stplat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.mngr.stplat.service.StplatService;
import com.hybirdapp.sample.mngr.stplat.service.StplatVO;

@Service("StplatService")
public class StplatServiceImpl implements StplatService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name = "StplatDAO")
	private StplatDAO stplatDAO;
	
	@Resource(name = "StplatService")
	private StplatService stplatService;
	
	@Override
	public List<StplatVO> selectStplatList(StplatVO vo) throws Exception {
		
		List<StplatVO> resultList = null;
		
		try {
			resultList = stplatDAO.selectStplatList(vo);
			vo.setTotPageCnt(stplatDAO.selectStplatTotalCnt());
			
		} catch (Exception e) {
			logger.error("selectStplatList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return resultList;
	}

	@Override
	public void registStplat(StplatVO vo) throws Exception {

		try {
			stplatDAO.updatUseAtRegist(vo);
			stplatDAO.insertStplat(vo);
		} catch (Exception e) {
			logger.error("registStplat", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.insert", null));
		}
	}

	@Override
	public StplatVO searchStplatRegist(StplatVO vo) throws Exception {
		
		StplatVO result = new StplatVO();
		
		try {
			result = stplatDAO.selectStplatRegist(vo);
		} catch (Exception e) {
			logger.error("searchStplatRegist", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}

	@Override
	public StplatVO searchStplatDetail(StplatVO vo) throws Exception {
		
		StplatVO result = new StplatVO();
		
		try {
			result = stplatDAO.selectStplatDetail(vo);
		} catch (Exception e) {
			logger.error("searchStplatDetail", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return result;
	}

	@Override
	public int updateUseAt(List<StplatVO> vo) throws Exception {
		
		try {
			for(int i = 0 ; i  <vo.size() ; i++) {
				stplatDAO.updateUseAt(vo.get(i));
			}
		} catch (Exception e) {
			logger.error("updateUseAt", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.save", null));
		}
		
		return 1;
	}
}
