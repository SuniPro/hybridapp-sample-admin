package com.hybirdapp.sample.mngr.queueMng.web;

import com.hybirdapp.sample.mngr.queueMng.service.QueueMngService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/mngr/queueMng")
public class QueueMngRestController{

	@Resource
	private QueueMngService queueMngService;

	/***
	 * 대기열관리
	 */
	@PostMapping(value = "/updateq.ajax")
	public String updateWaitingQ(@RequestBody HashMap<String, String> map) throws Exception {
		this.queueMngService.updateWaitingQ(map);
		return "{\"result\": \"success\"}";
	}

	@PostMapping(value = "/updateqtm.ajax")
	public String updateWaitingTmQ(@RequestBody HashMap<String, String> map) throws Exception {
		this.queueMngService.updateWaitingTmQ(map);
		return "{\"result\": \"success\"}";
	}
}
