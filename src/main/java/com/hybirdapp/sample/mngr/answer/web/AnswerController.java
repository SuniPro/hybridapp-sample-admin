package com.hybirdapp.sample.mngr.answer.web;

import javax.annotation.Resource;

import com.hybirdapp.sample.cmmn.SessionUtil;
import com.hybirdapp.sample.mngr.answer.service.AnswerVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.mngr.answer.service.AnswerService;

@Controller
@RequestMapping("/mngr/answer")
public class AnswerController extends CmmnController{
	
	
	@Resource(name = "AnswerService")
	private AnswerService answerService;
	
	/***
	 * 1:1 문의 답변 목록 화면
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/answerView.do")
	public String answer(@ModelAttribute("AnswerVO") AnswerVO vo, Model model) throws Exception {
		
		model.addAttribute("mParam", vo);
		
		return "mngr:/answer/answerView";
	}
	
	/***
	 * 1:1 문의 답변 상세 수정 화면
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/answerModify.do")
	public String answerModify(@ModelAttribute("AnswerVO") AnswerVO vo, Model model) throws Exception {
		
		model.addAttribute("mParam", vo);
		
		return "mngr:/answer/answerModify";
	}
	
	/***
	 * 1:1 문의 답변 목록 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchAnswerList.ajax")
	public String searchAnswerList(@ModelAttribute("AnswerVO") AnswerVO vo, Model model) throws Exception {
		
		/* 목록 조회 */
		model.addAttribute("result", answerService.searchAnswerList(vo));
		/* 페이징 처리 */
		model.addAttribute("pages", vo);
		
		return "jsonView";
	}
	
	/***
	 * 1:1 문의 답변 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchInqryAnswer.ajax")
	public String searchInqryAnswer(@ModelAttribute("AnswerVO") AnswerVO vo, Model model) throws Exception {
		
		/* 문의 */
		model.addAttribute("inqry", answerService.searchDetailInqry (vo));
		
		/* 답변 */
		model.addAttribute("answer", answerService.searchDetailAnswer(vo));
		
		/* 문의 히스토리*/
		model.addAttribute("inqryHist", answerService.searchDetailInqryHist (vo));
		
		/* 답변  히스토리*/
		model.addAttribute("answerHist", answerService.searchDetailAnswerHist(vo));

		return "jsonView";
	}
	
	/***
	 * 답변 저장
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registAnswer.ajax")
	public String registAnswer(@ModelAttribute("AnswerVO") AnswerVO vo, Model model) throws Exception {
		vo.setIsrtIp(SessionUtil.getUserIp());
		vo.setUpdtIp(SessionUtil.getUserIp());
		/* 답변 등록 */
		model.addAttribute("result", answerService.registAnswer(vo));
		
		return "jsonView";
	}
	
	/***
	 * 답변 수정
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyAnswer.ajax")
	public String modifyAnswer(@ModelAttribute("AnswerVO") AnswerVO vo, Model model) throws Exception {
		vo.setIsrtIp(SessionUtil.getUserIp());
		vo.setUpdtIp(SessionUtil.getUserIp());
		/* 답변 수정 */
		model.addAttribute("result", answerService.modifyAnswer(vo));
		
		return "jsonView";
	}

	/***
	 * 답변 삭제
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeAnswer.ajax")
	public String removeAnswer(@ModelAttribute("AnswerVO") AnswerVO vo, Model model) throws Exception {
		
		/* 답변 수정 */
		model.addAttribute("result", answerService.removeAnswer(vo));
		
		return "jsonView";
	}
	
	/***
	 * 1:1 문의 담당자 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/answerMngrManagerSelect.ajax")
	public String answerMngrManagerSelect(@ModelAttribute("AnswerVO") AnswerVO vo, Model model) throws Exception {
		
		model.addAttribute("result", answerService.answerMngrManagerSelect (vo));

		return "jsonView";
	}

	/***
	 * 1:1 문의 담당자 등록
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/answerMngrManagerRegist.ajax")
	public String answerMngrManagerRegist(@ModelAttribute("AnswerVO") AnswerVO vo, Model model) throws Exception {
		answerService.answerMngrManagerRegist(vo.getAnswerManagerList());
		
		return "jsonView";
	}
	

	/***
	 * 메인 1:1 문의 답변 목록 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mainInqryList.ajax")
	public String mainInqryList(@ModelAttribute("AnswerVO") AnswerVO vo, Model model) throws Exception {
		
		/* 목록 조회 */
		model.addAttribute("result", answerService.mainInqryList(vo));
		
		return "jsonView";
	}
	
}
