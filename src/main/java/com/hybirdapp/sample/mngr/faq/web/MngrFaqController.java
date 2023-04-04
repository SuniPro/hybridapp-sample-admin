package com.hybirdapp.sample.mngr.faq.web;

import javax.annotation.Resource;

import com.hybirdapp.sample.cmmn.SessionUtil;
import com.hybirdapp.sample.mngr.faq.service.MngrFaqVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnController;

@Controller
@RequestMapping("/mngr/faq")
public class MngrFaqController extends CmmnController{
	
	
	
	@Resource(name = "MngrFaqService")
	private com.hybirdapp.sample.mngr.faq.service.MngrFaqService MngrFaqService;
	
	/***
	 * FAQ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mngrFaqView.do")
	public String mngrFaqView(@ModelAttribute("MngrFaqVO") MngrFaqVO vo, Model model) throws Exception {
		
		model.addAttribute("mParam", vo);
		
		return "mngr:/faq/mngrFaqView";
	}

	/***
	 * FAQ 상세
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mngrFaqDetailView.do")
	public String mngrFaqDetailView(@ModelAttribute("MngrFaqVO") MngrFaqVO vo, Model model) throws Exception {
		
		model.addAttribute("mParam", vo);
		
		return "mngr:/faq/mngrFaqDetailView";
	}
	
	/***
	 * FAQ 목록 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchMngrFaqList.ajax")
	public String searchMngrFaqList(@ModelAttribute("MngrFaqVO") MngrFaqVO vo, Model model) throws Exception {
		
		/* 목록 조회 */
		model.addAttribute("result", MngrFaqService.searchMngrFaqList(vo));
		/* 페이징 처리 */
		model.addAttribute("pages", vo);
		
		return "jsonView";
	}
	
	/***
	 * FAQ 상세 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchMngrFaq.ajax")
	public String searchMngrFaq(@ModelAttribute("MngrFaqVO") MngrFaqVO vo, Model model) throws Exception {
		
		/* 조회 */
		model.addAttribute("result", MngrFaqService.searchMngrFaq(vo));
		
		return "jsonView";
	}
	
	/***
	 * FAQ 등록
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registMngrFaq.ajax")
	public String registMngrFaq(@ModelAttribute("MngrFaqVO") MngrFaqVO vo, Model model) throws Exception {
		vo.setIsrtIp(SessionUtil.getUserIp());
		vo.setUpdtIp(SessionUtil.getUserIp());
		/* 등록 */
		model.addAttribute("result", MngrFaqService.registMngrFaq(vo));
		
		return "jsonView";
	}
	
	/***
	 * FAQ 수정
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyMngrFaq.ajax")
	public String modifyMngrFaq(@ModelAttribute("MngrFaqVO") MngrFaqVO vo, Model model) throws Exception {
		vo.setUpdtIp(SessionUtil.getUserIp());
		/* 수정 */
		model.addAttribute("result", MngrFaqService.modifyMngrFaq(vo));
		
		return "jsonView";
	}
	
	/***
	 * FAQ 수정
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeMngrFaq.ajax")
	public String removeMngrFaq(@ModelAttribute("MngrFaqVO") MngrFaqVO vo, Model model) throws Exception {
		vo.setUpdtIp(SessionUtil.getUserIp());
		 MngrFaqService.removeMngrFaq(vo);
		
		return "jsonView";
	}
	
}
