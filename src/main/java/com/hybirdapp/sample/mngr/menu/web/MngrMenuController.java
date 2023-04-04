package com.hybirdapp.sample.mngr.menu.web;

import com.hybirdapp.sample.cmmn.SessionUtil;
import com.hybirdapp.sample.mngr.menu.service.MngrMenuService;
import com.hybirdapp.sample.mngr.menu.service.MngrMenuVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/mngr/menu")
public class MngrMenuController {

	@Resource(name = "MngrMenuService")
	private MngrMenuService mngrMenuService;
	
	/**
	 * 메뉴 목록 페이지
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mngrMenuView.do")
	public String mngrMenuView(@ModelAttribute("MngrMenuVO") MngrMenuVO vo, Model model) throws Exception {
		
		model.addAttribute("mngrId", vo.getSeMngrId());
		
		return "mngr:/menu/menuView";
	}
	
	/**
	 * 메뉴 트리 팝업창
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/menuTreePopup.do")
	public String menuTreePopup(@ModelAttribute("MngrMenuVO") MngrMenuVO vo, Model model) throws Exception {

		model.addAttribute("popupModel", vo);

		return "/popup/user/menuTreePopup";
	}

	/**
	 * 메뉴 트리 등록 페이지
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mngrMenuRegist.do")
	public String mngrMenuRegist(@ModelAttribute("MngrMenuVO") MngrMenuVO vo, Model model) throws Exception {

		model.addAttribute("menuType", vo.getMenuType());
		model.addAttribute("menuCd", vo.getMenuCd());
		model.addAttribute("menuNm", vo.getMenuNm());

		return "mngr:/menu/menuRegist";
	}

	/**
	 * 메뉴 등록/추가
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/menuRegist.ajax")
	private String menuRegist(@ModelAttribute("MngrMenuVO") MngrMenuVO vo, Model model) throws Exception {
		
		model.addAttribute("result", mngrMenuService.searchMenuCd(vo));

		return "jsonView";
	}

	/**
	 * 메뉴 상세/수정
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/menuDetail.ajax")
	private String menuDetail(@ModelAttribute("MngrMenuVO") MngrMenuVO vo, Model model) throws Exception {

		model.addAttribute("result", mngrMenuService.selectMenuDetailList(vo));

		return "jsonView";
	}

	/**
	 * 메뉴 목록 조회
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchMngrMenuList.ajax")
	public String searchMngrMenuList(@ModelAttribute("MngrMenuVO") MngrMenuVO vo, Model model) throws Exception {

		model.addAttribute("result", mngrMenuService.selectMngrMenuList(vo));

		return "jsonView";
	}

	/**
	 * 메뉴 트리 목록 조회
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchMngrMenuTreeList.ajax")
	public String searchMngrMenuTreeList(@ModelAttribute("MngrMenuVO") MngrMenuVO vo, Model model) throws Exception {

		model.addAttribute("result", mngrMenuService.selectMngrMenuTreeList());

		return "jsonView";
	}

	/**
	 * 메뉴 등록
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registMngrMenu.ajax")
	public String registMngrMenu(@ModelAttribute("MngrMenuVO") MngrMenuVO vo, Model model) throws Exception {
		vo.setIsrtIp(SessionUtil.getUserIp());
		vo.setUpdtIp(SessionUtil.getUserIp());
		mngrMenuService.registMngrMenu(vo);

		return "jsonView";
	}

	/**
	 * 메뉴 삭제
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeMngrMenu.ajax")
	public String removeMngrMenu(@ModelAttribute("MngrMenuVO") MngrMenuVO vo, Model model) throws Exception {

		mngrMenuService.deleteMngrMenu(vo);

		return "jsonView";
	}
	

}
