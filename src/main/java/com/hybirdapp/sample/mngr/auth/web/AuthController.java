package com.hybirdapp.sample.mngr.auth.web;

import java.util.List;

import javax.annotation.Resource;

import com.hybirdapp.sample.cmmn.SessionUtil;
import com.hybirdapp.sample.mngr.auth.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnConstant;
import com.hybirdapp.sample.mngr.auth.service.AuthVO;

@Controller
@RequestMapping("/mngr/auth")
public class AuthController {
	
	@Resource(name = "AuthService")
	private AuthService authService;

	/**
	 * 권한 관리 페이지
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/authView.do")
	public String authView(@ModelAttribute("AuthVO") AuthVO vo, Model model) throws Exception {
		
		return "mngr:/auth/authView";
	}

	/**
	 * 권한 조회
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectAuthList.ajax")
	public String selectAuthList(@ModelAttribute("AuthVO") AuthVO vo, Model model) throws Exception {
		
		model.addAttribute("result", authService.selectAuthList(vo));

		return "jsonView";
	}
	/**
	 * 권한 단건 조회
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectAuthOne.ajax")
	public String selectAuthOne(@ModelAttribute("AuthVO") AuthVO vo, Model model) throws Exception {
		
		model.addAttribute("result", authService.selectAuthOne(vo));

		return "jsonView";
	}
	
	/**
	 * 권한 등록 페이지
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/authRegist.do")
	public String authRegist(@ModelAttribute("AuthVO") AuthVO vo, Model model) throws Exception {
		
		return "mngr:/auth/authRegist";
	}
	
	/**
	 * 권한 등록
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registAuth.ajax")
	public String registAuth(@ModelAttribute("AuthVO") AuthVO vo, Model model) throws Exception {
		vo.setIsrtIp(SessionUtil.getUserIp());
		vo.setUpdtIp(SessionUtil.getUserIp());
		authService.registAuth(vo);
				
		return "jsonView";
	}
	
	/**
	 * 권한 수정
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateAuth.ajax")
	public String updateAuth(@ModelAttribute("AuthVO") AuthVO vo, Model model) throws Exception {
		vo.setUpdtIp(SessionUtil.getUserIp());
		authService.updateAuth(vo);
		
		return "jsonView";
	}
	
	/***
	 * 권한 등록, 수정 팝업
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/popup/mngr/auth/authRegistPopup.do")
	public String authRegistPopup(@ModelAttribute("AuthVO") AuthVO vo, Model model) throws Exception {
		
		model.addAttribute("mParam", vo);
		
		return "/popup/mngr/auth/authRegistPopup";
	}
	
	
	/**
	 * 권한 삭제
	 * @param checkArr
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/authRemove.ajax")
	public String authRemove(@ModelAttribute("AuthVO") AuthVO vo, Model model) throws Exception {
		
		authService.authRemove(vo);
		
		return "jsonView";
	}
	
	/**
	 * 권한 삭제 여부 확인
	 * @param checkArr
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/authRemoveCheck.ajax")
	public String authRemoveCheck(@ModelAttribute("AuthVO") AuthVO vo, Model model) throws Exception {
		model.addAttribute("result", authService.authRemoveCheck(vo));
		return "jsonView";
	}
	
	/**
	 * 권한별 메뉴 페이지
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectAuthMenu.do")
	public String selectAuthMenu(@ModelAttribute("AuthVO") AuthVO vo, Model model) throws Exception {
		
		model.addAttribute("authCd", vo.getAuthCd());
		model.addAttribute("menuSe", vo.getMenuSe());
		
		return "mngr:/auth/authMenuView";
	}

	/**
	 * 권한별 메뉴 조회
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectAuthMenuList.ajax")
	public String selectAuthMenuList(@ModelAttribute("AuthVO") AuthVO vo, Model model) throws Exception {
				
		model.addAttribute("menuSe", vo.getMenuSe());
		
		model.addAttribute("result", authService.selectAuthMenuList(vo));

		return "jsonView";
	}
	
	/**
	 * 권한별 메뉴 트리 팝업창
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/authMenuTreePopup.do")
	public String authMenuTreePopup(@ModelAttribute("AuthVO") AuthVO vo, Model model) throws Exception {
				
		model.addAttribute("menuSe", vo.getMenuSe());
		
		return "/popup/user/authMenuTreePopup";
	}

	/**
	 * 권한 지정된 메뉴 가져옴
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectMenu.ajax")
	public String selectMenu(@ModelAttribute("AuthVO") AuthVO vo, Model model) throws Exception {
				
		model.addAttribute("result", authService.selectMenu(vo.getAuthCd()));

		return "jsonView";
	}

	/**
	 * 선택한 메뉴 저장
	 * @param checkArr
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/authMenuCheckSave.ajax")
	public String authMenuCheckSave(@RequestBody List<String> checkArr, @ModelAttribute("AuthVO") AuthVO vo, Model model) throws Exception {
		String isrtId = SessionUtil.getAttributeStr(CmmnConstant.SESSION_MNGR, CmmnConstant.MNGR_ID);
		vo.setIsrtId(isrtId);
		vo.setIsrtIp(SessionUtil.getUserIp());
		vo.setUpdtId(isrtId);
		vo.setUpdtIp(SessionUtil.getUserIp());
		authService.authMenuCheckSave(checkArr,vo);
		
		return "jsonView";
	}
	
	/**
	 * 선택 안한 메뉴 삭제
	 * @param unCheckArr
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/authMenuNotCheckSave.ajax")
	public String authMenuNotCheckSave(@RequestBody List<String> unCheckArr, @ModelAttribute("AuthVO") AuthVO vo, Model model) throws Exception {
		
		authService.authMenuNotCheckSave(unCheckArr);

		return "jsonView";
	}

}
