package com.hybirdapp.sample.mngr.tckSls.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.cmmn.script.service.ScriptService;
import com.hybirdapp.sample.mngr.tckSls.service.TckSlsService;

@Service("TckSlsService")
public class TckSlsServiceImpl implements TckSlsService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "TckSlsDAO")
	private TckSlsDAO tckSlsDAO;
	
	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;
	
	@Resource(name = "ScriptService")
	private ScriptService scriptService;

	// 티켓 판매 조회 건수
	@Override
	public int searchTckSlsListCnt(DataClass params) throws Exception {
		int result = 0;
		try {
			result = tckSlsDAO.searchTckSlsListCnt(params.getMap());
		} catch (Exception e) {
			logger.error("searchTckSlsListCnt", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 티켓 판매 조회 목록
	@Override
	public List searchTckSlsList(DataClass params) throws Exception {
		List result = null;

		try {
			result = tckSlsDAO.searchTckSlsList(params.getMap());
		} catch (Exception e) {
			logger.error("searchTckSlsList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}
	
	// 티켓 판매 정보 조회(팝업)
	@Override
	public List searchTckSls(DataClass params) throws Exception {
		List result = null;

		try {
			result = tckSlsDAO.searchTckSls(params.getMap());
		} catch (Exception e) {
			logger.error("searchTckSls", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}
	
	// 티켓 판매 총 금액 조회
	@Override
	public int searchTckSlsAmt(DataClass params) throws Exception {
		int amount = 0;
		HashMap result = new HashMap();
		try {
			amount = tckSlsDAO.searchTckSlsListAmt(params.getMap());
		} catch (Exception e) {
			logger.error("searchTckSls", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return amount;
	}

	/**
	 * 당일환불금액 조회
	 *
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public int searchCurrentDayRefundAmt(DataClass params) throws Exception {
		int amount = 0;
		try {
			amount = tckSlsDAO.searchCurrentDayRefundAmt(params.getMap());
		} catch (Exception e) {
			logger.error("searchCurrentDayRefundAmt", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return amount;
	}

	/**
	 * 이전환불금액 조회
	 *
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public int searchBeforeDayRefundAmt(DataClass params) throws Exception {
		int amount = 0;
		try {
			amount = tckSlsDAO.searchBeforeDayRefundAmt(params.getMap());
		} catch (Exception e) {
			logger.error("searchBeforeDayRefundAmt", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return amount;
	}

    /**
     * 이전환불목록 조회(팝업)
     *
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public List beforeRefundList(DataClass params) throws Exception {
        List result = null;

        try {
            result = tckSlsDAO.beforeRefundList(params.getMap());
        } catch (Exception e) {
            logger.error("beforeRefundListPopup", e);
            throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
        }

        return result;
    }

    /**
	 * 검색조건을 위한 시설목록 조회
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	public List searchFcltList() throws Exception {
		List result = null;

		try {
			result = tckSlsDAO.searchFcltList( );
		} catch (Exception e) {
			logger.error("searchFcltList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}

	/**
	 * 시설에 등록된 티켓 목록 조회
	 *
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public List searchFcltTckList(DataClass params) throws Exception {
		List result = null;

		try {
			result = tckSlsDAO.searchFcltTckList( params.getMap() );
		} catch (Exception e) {
			logger.error("searchFcltTckList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}

}
