package com.hybirdapp.sample.mngr.queueMng.service.impl;

import com.hybirdapp.sample.mngr.queueMng.service.QueueMngService;
import com.hybirdapp.sample.mngr.queueMng.service.QueueMngVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("QueueMngService")
public class QueueMngServiceImpl implements QueueMngService {

	@Resource
	private QueueMngDAO queueMngDAO;

	@Override
	public void updateWaitingQ(HashMap<String, String> map) throws Exception {
		map.forEach((k, v) -> {
			String[] arr = k.split("_");
			Map<String, String> data = new HashMap<>();
			data.put("type", arr[0]);
			data.put("idx", arr[1]);
			data.put("val", v);
			try {
				this.queueMngDAO.updateWaitingList(data);
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	public void updateWaitingTmQ(HashMap<String, String> map) throws Exception {
		try {
			this.queueMngDAO.updateOpTime(map);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<QueueMngVO> waitingList() throws Exception {
		return this.queueMngDAO.waitingList();
	}
}
