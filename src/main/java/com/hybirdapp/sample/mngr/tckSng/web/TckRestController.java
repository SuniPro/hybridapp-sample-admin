package com.hybirdapp.sample.mngr.tckSng.web;

import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/mngr/tckSng")
public class TckRestController extends CmmnController {

	@Resource(name = "TckSngService")
	private com.hybirdapp.sample.mngr.tckSng.service.TckSngService TckSngService;

	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;

	/* 단일상품티켓관리 */
	@RequestMapping(value = "/saveTckSng.ajax")
	public String saveTckSng(HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);

		this.TckSngService.saveTckSng(params);
		return "{\"result\": \"success\"}";
	}
	
	/* 단일상품티켓삭제 */
	@RequestMapping(value = "/delTicket.ajax")
	public String delTicket(HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);

		this.TckSngService.delTicket(params);
		return "{\"result\": \"success\"}";
	}
}