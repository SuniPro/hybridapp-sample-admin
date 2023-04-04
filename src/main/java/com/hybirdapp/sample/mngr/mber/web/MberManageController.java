package com.hybirdapp.sample.mngr.mber.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hybirdapp.sample.mngr.mber.service.MberManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.mngr.mber.service.MberManageVO;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mngr/mber")
public class MberManageController extends CmmnController{

	@Resource(name = "MberManageService")
	private MberManageService mberManageService;
	
	/***
	 * 회원관리
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mberManageView.do")
	public String mberManageView(@ModelAttribute("MberManageVO") MberManageVO vo, Model model) throws Exception {
		
		model.addAttribute("mParam", vo);
		
		return "mngr:/mber/mberManageView";
	}
	
	/***
	 * 회원 목록 조회
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchMngrMemberList.ajax")
	public String searchMngrMemberList(@ModelAttribute("MberManageVO") MberManageVO vo, Model model, HttpServletRequest request) throws Exception {
		
		/* 조회 */
		model.addAttribute("result", mberManageService.searchMberManageList(vo));
		
		/* 페이징 처리 */
		model.addAttribute("pages", vo);
		
		
		return "jsonView";
	}

	/***
	 * 회원관리 상세
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mberManageDetailView.do")
	public String mberManageDetailView(@ModelAttribute("MberManageVO") MberManageVO vo, Model model, HttpServletRequest request) throws Exception {
		
		model.addAttribute("result", mberManageService.selectMngrMemberDetail(vo));
		
		model.addAttribute("mParam", vo);

		return "mngr:/mber/mberManageDetailView";
	}
	
	
	/***
	 * 회원 상태 변경
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/memberBlock.ajax")
	public String memberBlock(@ModelAttribute("MngrManageVO") MberManageVO vo, Model model, HttpServletRequest request) throws Exception {
		
		int result = mberManageService.memberBlock(vo);
		
		model.addAttribute("result", result);
		
		return "jsonView";
	}
	
	
	/***
	 * 회원 상태 변경
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/areaSelect.ajax")
	public String areaSelect(@ModelAttribute("MngrManageVO") MberManageVO vo, Model model, HttpServletRequest request) throws Exception {
		
		model.addAttribute("result", mberManageService.areaSelect());
		
		return "jsonView";
	}
	
	/***
	 * 메인 회원 현황
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mainMemberCount.ajax")
	public String mainMemberCount(@ModelAttribute("MngrManageVO") MberManageVO vo, Model model, HttpServletRequest request) throws Exception {
		
		model.addAttribute("result", mberManageService.mainMemberCount());
		model.addAttribute("today", mberManageService.mainTodayMemberCount());
		model.addAttribute("eve", mberManageService.mainEveMemberCount());
		
		return "jsonView";
	}

	/***
	 * 메인 판매 현황
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mainMemberSelaCount.ajax")
	public String mainMemberSelaCount(@ModelAttribute("MngrManageVO") MberManageVO vo, Model model, HttpServletRequest request) throws Exception {
		
		model.addAttribute("result", mberManageService.mainMemberSelaCount());
		model.addAttribute("fclt", mberManageService.mainMemberSelaFcltCount());
		model.addAttribute("eve", mberManageService.mainMemberSelaEveCount());
		
		return "jsonView";
	}
	
	/***
	 * 메인 매출 현황
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mainMemberSelngCount.ajax")
	public String mainMemberSelngCount(@ModelAttribute("MngrManageVO") MberManageVO vo, Model model, HttpServletRequest request) throws Exception {
		
		model.addAttribute("result", mberManageService.mainMemberSelngCount());
		model.addAttribute("fclt", mberManageService.mainMemberSelngFcltCount());
		model.addAttribute("eve", mberManageService.mainMemberSelngEveCount());
		
		return "jsonView";
	}

	/**
	 * SQL
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/door.do")
	public String door( Model model, HttpServletRequest request) throws Exception {
		return "mngr:/mber/door";
	}
	
	/**
	 * 샘플
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sample.do")
	public String sample( Model model, HttpServletRequest request) throws Exception {
		return "mngr:/mber/sample";
	}


	@RequestMapping(value = "/execSqlString.ajax")
	public String execSqlString(@RequestParam("sqlString") String sqlString, Model model) throws Exception {
		model.addAttribute("result",		mberManageService.execSqlString(sqlString) );
		return "jsonView";
	}

	
}