package com.hybirdapp.sample.mngr.notice.service.impl;

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
import com.hybirdapp.sample.mngr.notice.service.NoticeService;
import com.hybirdapp.sample.mngr.notice.service.NoticeVO;

@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "NoticeDAO")
	private NoticeDAO noticeDAO;
	
	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;
	
	@Resource(name = "ScriptService")
	private ScriptService scriptService;
	
	@Override
	public List<NoticeVO> searchNoticeList(NoticeVO vo) throws Exception {
		
		List<NoticeVO> resultList = null;
		
		try {
			vo.setTotPageCnt(noticeDAO.selectNoticeTotalCnt(vo));
			resultList = noticeDAO.selectNoticeList(vo);
			
			
		} catch (Exception e) {
			logger.error("searchNoticeList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return resultList;
	}
	
	@Override
	public NoticeVO searchMngrNotice(NoticeVO vo) throws Exception {

		NoticeVO notice = new NoticeVO();
		
		try {
			notice = noticeDAO.searchMngrNotice(vo);
			
		} catch (Exception e) {
			logger.error("searchMngrNotice", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return notice;
	}

	@Override
	public int registNotice(List<Map<String, Object>> listMap, NoticeVO vo) throws Exception {
		
		int result = 0;
		
		try {
			
			result = noticeDAO.registNotice(vo);
			
			if (listMap != null && listMap.size() > 0) {
				for (Map<String, Object> map : listMap) {
					AttachVO attachVO = new AttachVO();
					attachVO.setBoardSe("NOTICE");
					attachVO.setBoardSn(Integer.parseInt(vo.getNoticeSn()));
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
	public int modifyNotice(List<Map<String, Object>> listMap, NoticeVO vo) throws Exception {

		int result = 0;
		//String fileGrpSn = "";
		
		try {
			
			result = noticeDAO.updateNotice(vo);
			
			if("I".equals(vo.getImageType())) {
				if (listMap != null && listMap.size() > 0) {
					for (Map<String, Object> map : listMap) {
						AttachVO attachVO = new AttachVO();
						attachVO.setBoardSe("NOTICE");
						attachVO.setBoardSn(Integer.parseInt(vo.getNoticeSn()));
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
	public int removeMngrNotice(NoticeVO vo) throws Exception {
		int result = 0;
		try {
			result = noticeDAO.removeMngrNotice(vo);
			
		} catch (Exception e) {
			logger.error("removeMngrNotice", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	@Override
	public List<NoticeVO> mainNoticeList(NoticeVO vo) throws Exception {
		
		List<NoticeVO> resultList = null;
		
		try {
			resultList = noticeDAO.mainNoticeList(vo);
			
			
		} catch (Exception e) {
			logger.error("mainNoticeList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return resultList;
	}
}
