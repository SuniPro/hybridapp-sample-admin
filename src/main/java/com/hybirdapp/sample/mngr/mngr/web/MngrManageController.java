package com.hybirdapp.sample.mngr.mngr.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hybirdapp.sample.cmmn.SessionUtil;
import com.hybirdapp.sample.mngr.mngr.service.MngrManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.mngr.mngr.service.MngrManageVO;


@Controller
@RequestMapping("/mngr/mngr")
public class MngrManageController extends CmmnController{
	
	
	@Resource(name = "MngrManageService")
	private MngrManageService mngrManageService;
	
	/***
	 * 관리자 관리
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mngrManageView.do")
	public String mngrManageView(@ModelAttribute("MngrManageVO") MngrManageVO vo, Model model) throws Exception {
		return "mngr:/mngr/mngrManageView";
	}
	
	/***
	 * 관리자 관리 상세
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mngrManageDetailView.do")
	public String mngrManageDetailView(@ModelAttribute("MngrManageVO") MngrManageVO vo, Model model) throws Exception {
		
		model.addAttribute("mParam", vo);
		
		return "mngr:/mngr/mngrManageDetailView";
	}
	
	
	/***
	 * 내정보 수정
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mngrMyUpdt.do")
	public String mngrMyUpdt(@ModelAttribute("MngrManageVO") MngrManageVO vo, Model model) throws Exception {
		
		model.addAttribute("mParam", vo);
		
		return "mngr:/mngr/mngrMyUpdt";
	}
	
	/***
	 * 관리자 목록 조회
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchMngrManageList.ajax")
	public String searchMngrManageList(@ModelAttribute("MngrManageVO") MngrManageVO vo, Model model) throws Exception {
		
		/* 목록 조회 */
		model.addAttribute("result", mngrManageService.searchMngrManageList(vo));
		/* 페이징 처리 */
		model.addAttribute("pages", vo);
				
		return "jsonView";
	}
	
	/***
	 * 관리자 목록 조회
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchMngrManage.ajax")
	public String searchMngrManage(@ModelAttribute("MngrManageVO") MngrManageVO vo, Model model) throws Exception {
		
		/* 조회 */
		model.addAttribute("result", mngrManageService.searchMngrManage(vo));
		
		return "jsonView";
	}
	
	/***
	 * 관리자 등급 목록 조회
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchMngrManageGradList.ajax")
	public String searchMngrManageGradList(@ModelAttribute("MngrManageVO") MngrManageVO vo, Model model) throws Exception {
		
		/* 조회 */
		model.addAttribute("result", mngrManageService.searchMngrManageGradList(vo));
		
		return "jsonView";
	}
	
	/***
	 * 관리자 수정
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyMngrManage.ajax")
	public String modifyMngrManage(@ModelAttribute("MngrManageVO") MngrManageVO vo, Model model, HttpServletRequest request) throws Exception {
		vo.setUpdtIp(request.getRemoteAddr());
		mngrManageService.modifyMngrManage(vo);
		
		return "jsonView";
	}
	
	/***
	 * 아이디 중복 확인
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchMngrIdCo.ajax")
	public String searchMngrIdCo(@ModelAttribute("MngrManageVO") MngrManageVO vo, Model model) throws Exception {
		
		/* 조회 */
		model.addAttribute("result", mngrManageService.searchMngrIdCo(vo));
		
		return "jsonView";
	}
	
	/***
	 * 비밀번호 일치 확인
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchMngrPwChk.ajax")
	public String searchMngrPwChk(@ModelAttribute("MngrManageVO") MngrManageVO vo, Model model) throws Exception {
		
		/* 조회 */
		model.addAttribute("result", mngrManageService.searchMngrPwChk(vo));
		
		return "jsonView";
	}
	
	/***
	 * 관리자 등록
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registMngrManage.ajax")
	public String registMngrManage(@ModelAttribute("MngrManageVO") MngrManageVO vo, Model model) throws Exception {
		vo.setIsrtIp(SessionUtil.getUserIp());
		vo.setUpdtIp(SessionUtil.getUserIp());
		mngrManageService.registMngrManage(vo);
		
		return "jsonView";
	}
	
	/***
	 * 관리자 수정
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyMngrManagePwd.ajax")
	public String modifyMngrManagePwd(@ModelAttribute("MngrManageVO") MngrManageVO vo, Model model, HttpServletRequest request) throws Exception {
		vo.setUpdtIp(SessionUtil.getUserIp());
		mngrManageService.modifyMngrManagePwd(vo);
		
		return "jsonView";
	}
	
	/***
	 * 관리자 삭제
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeMngrManage.ajax")
	public String removeMngrManage(@ModelAttribute("MngrManageVO") MngrManageVO vo, Model model) throws Exception {
		vo.setUpdtIp(SessionUtil.getUserIp());
		mngrManageService.removeMngrManage(vo);
		
		return "jsonView";
	}
}
