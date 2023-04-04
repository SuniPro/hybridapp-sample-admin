package com.hybirdapp.sample.mngr.queueMng.service.impl;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.mngr.queueMng.service.QueueMngVO;

import java.util.List;
import java.util.Map;

@OracleMapper("QueueMngDAO")
public interface QueueMngDAO {

	List<QueueMngVO> waitingList() throws Exception;
	void updateWaitingList(Map<String, String> map) throws Exception;
	void updateOpTime(Map<String, String> map) throws Exception;
}
