package com.hybirdapp.sample.mngr.queueMng.service;

import java.util.HashMap;
import java.util.List;

public interface QueueMngService {
	public List<QueueMngVO> waitingList() throws Exception;
	public void updateWaitingQ(HashMap<String, String> map) throws Exception;
	public void updateWaitingTmQ(HashMap<String, String> map) throws Exception;
}
