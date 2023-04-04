package com.hybirdapp.sample.mngr.event.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.cmmn.script.service.AttachVO;
import com.hybirdapp.sample.cmmn.script.service.ScriptService;
import com.hybirdapp.sample.mngr.event.service.EventService;
import com.hybirdapp.sample.mngr.event.service.EventVO;

@Service("EventService")
public class EventServiceImpl implements EventService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "EventDAO")
	private EventDAO eventDAO;
	
	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;
	
	@Resource(name = "ScriptService")
	private ScriptService scriptService;
	
	@Override
	public List<EventVO> searchEventList(EventVO vo) throws Exception {
		
		List<EventVO> resultList = null;
		
		try {
			vo.setTotPageCnt(eventDAO.selectEventTotalCnt(vo));
			resultList = eventDAO.searchEventList(vo);
			
			
		} catch (Exception e) {
			logger.error("searchNoticeList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return resultList;
	}
	
	@Override
	public EventVO searchMngrEvent(EventVO vo) throws Exception {

		EventVO notice = new EventVO();
		
		try {
			notice = eventDAO.searchMngrEvent(vo);
			
		} catch (Exception e) {
			logger.error("searchMngrNotice", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return notice;
	}

	@Override
	public int registEvent(List<Map<String, Object>> listMap, EventVO vo) throws Exception {
		
		int result = 0;
		
		try {
			
			result = eventDAO.registEvent(vo);
			if (listMap != null && listMap.size() > 0) {
				for (Map<String, Object> map : listMap) {
					AttachVO attachVO = new AttachVO();
					attachVO.setBoardSe("EVENT");
					attachVO.setBoardSn(Integer.parseInt(vo.getEvntSn()));
					attachVO.setOrgnFileNm((String)map.get("orgnFileNm"));
					attachVO.setNewFileNm((String)map.get("newFileNm"));
					attachVO.setSavePath((String)map.get("savePath"));
					attachVO.setExpsrOrdr((int)map.get("expsrOrdr"));
					attachVO.setUrl((String)map.get("url"));
					attachVO.setIsrtIp(vo.getIsrtIp());
					attachVO.setIsrtId(vo.getIsrtId());
					scriptService.attachFileSave(attachVO);
				}
			}
			
		} catch (Exception e) {
			logger.error("registNotice", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.insert", null));
		}

		return result;
	}

	@Override
	public int modifyEvent(List<Map<String, Object>> listMap, EventVO vo) throws Exception {

		int result = 0;
		
		try {
			
			result = eventDAO.modifyEvent(vo);
			
			if("I".equals(vo.getImageType())) {
				if (listMap != null && listMap.size() > 0) {
					for (Map<String, Object> map : listMap) {
						AttachVO attachVO = new AttachVO();
						attachVO.setBoardSe("EVENT");
						attachVO.setBoardSn(Integer.parseInt(vo.getEvntSn()));
						attachVO.setOrgnFileNm((String)map.get("orgnFileNm"));
						attachVO.setNewFileNm((String)map.get("newFileNm"));
						attachVO.setSavePath((String)map.get("savePath"));
						attachVO.setExpsrOrdr((int)map.get("expsrOrdr"));
						attachVO.setUrl((String)map.get("url"));
						attachVO.setIsrtIp(vo.getIsrtIp());
						attachVO.setIsrtId(vo.getIsrtId());
						scriptService.attachFileSave(attachVO);
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("modifyNotice", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.update", null));
		}
		
		return result;
	}

	@Override
	public int removeMngrEvent(EventVO vo) throws Exception {
		int result = 0;
		try {
			result = eventDAO.removeMngrEvent(vo);
		} catch (Exception e) {
			logger.error("removeMngrEvent", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.update", null));
		}
		return 0;
	}
	
}
