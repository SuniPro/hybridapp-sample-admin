package com.hybirdapp.sample.mngr.queueMng.web;

import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.mngr.queueMng.service.QueueMngService;
import com.hybirdapp.sample.mngr.queueMng.service.QueueMngVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/mngr/queueMng")
public class QueueMngController extends CmmnController {

	@Resource
	private QueueMngService queueMngService;

	/***
	 * 대기열관리
	 */
	@RequestMapping(value = "/queueMngView.do")
	public String queueMngView(Model model) throws Exception {
		List<QueueMngVO> items = this.queueMngService.waitingList();
		model.addAttribute("items", items);
		return "mngr:/queueMng/queueMngView";
	}
}
