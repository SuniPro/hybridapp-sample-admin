package com.hybirdapp.sample.mngr.stplat.web;

import javax.annotation.Resource;

import com.hybirdapp.sample.cmmn.SessionUtil;
import com.hybirdapp.sample.mngr.stplat.service.StplatService;
import com.hybirdapp.sample.mngr.stplat.service.StplatVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnController;

@Controller
@RequestMapping("/mngr/stplat")
public class StplatController extends CmmnController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name = "StplatService")
	private StplatService stplatService;
	
	/**
	 * 약관 페이지
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/stplatView.do")
	public String stplatView(Model model) throws Exception {
		return "mngr:/stplat/stplatView";
	}
	
	/**
	 * 약관 목록 조회
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchStplatList.ajax")
	public String searchStplatList(@ModelAttribute("StplatVO") StplatVO vo, Model model) throws Exception {
		
		model.addAttribute("result", stplatService.selectStplatList(vo));
		
		model.addAttribute("pages", vo);
		
		return "jsonView";
	}
	
	/**
	 * 약관 등록 및 상세 페이지
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/stplatDetailView.do")
	public String stplatDetailView(@ModelAttribute("StplatVO") StplatVO vo, Model model) throws Exception {
		
		model.addAttribute("mParam" , vo);
		
		return "mngr:/stplat/stplatDetailView";
	}
	
	/**
	 * 약관 등록 조회
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/stplatRegistList.ajax")
	public String stplatRegistList(@ModelAttribute("StplatVO") StplatVO vo, Model model) throws Exception {
		model.addAttribute("result", stplatService.searchStplatRegist(vo));
		
		return "jsonView";
	}
	
	/**
	 * 약관 상세 조회
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/stplatDetailList.ajax")
	public String stplatDetailList(@ModelAttribute("StplatVO") StplatVO vo, Model model) throws Exception {
		
		model.addAttribute("result", stplatService.searchStplatDetail(vo));
		
		return "jsonView";
	}
	
	/**
	 * 약관 저장
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mngrStplatSave.ajax")
	public String mngrStplatSave(@ModelAttribute("StplatVO") StplatVO vo, Model model) throws Exception {
		vo.setIsrtIp(SessionUtil.getUserIp());
		vo.setUpdtIp(SessionUtil.getUserIp());
		stplatService.registStplat(vo);
		
		return "jsonView";
	}
	
	/**
	 * 사용/미사용 저장
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/useAtSave.ajax")
	public String useAtSave(@ModelAttribute("StplatVO") StplatVO vo, Model model) throws Exception {
		int result = stplatService.updateUseAt(vo.getStplatList());
		
		model.addAttribute("result", result);
		
		return "jsonView";
	}
	
}
