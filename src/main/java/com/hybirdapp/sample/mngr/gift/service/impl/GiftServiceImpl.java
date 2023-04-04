package com.hybirdapp.sample.mngr.gift.service.impl;

import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.mngr.gift.service.GiftService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("GiftService")
@Transactional(transactionManager = "txManager", readOnly = false)
public class GiftServiceImpl implements GiftService {

	protected final Log logger = LogFactory.getLog(getClass());
	@Resource(name = "GiftDAO")
	private GiftDAO giftDAO;

	// 선물내역 목록
	@Override
	public List searchGiftList(DataClass params) throws Exception {
		List result = null;

		try {
			result = giftDAO.searchGiftList(params.getMap());
		} catch (Exception e) {
			logger.error("searchGiftListCnt", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}

	// 선물내역 목록 카운트
	@Override
	public int searchGiftListCnt(DataClass params) throws Exception {
		int result = 0;

		try {
			result = giftDAO.searchGiftListCnt(params.getMap());
		} catch (Exception e) {
			logger.error("searchGiftListCnt", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}

	// 선물내역 Detail 팝업
	@Override
	public List searchGift(DataClass params) throws Exception {
		List result = null;

		try {
			result = giftDAO.searchGift(params.getMap());
		} catch (Exception e) {
			logger.error("searchGift", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}

	// 티켓 리스트
	@Override
	public List searchTicketList() throws Exception {
		List result = null;

		try {
			result = giftDAO.searchTicketList();
		} catch (Exception e) {
			logger.error("searchTicketList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}
}
