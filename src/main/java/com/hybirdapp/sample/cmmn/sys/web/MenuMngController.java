package com.hybirdapp.sample.cmmn.sys.web;

import java.util.List;

import javax.annotation.Resource;

import com.hybirdapp.sample.cmmn.sys.service.MenuMngService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.cmmn.sys.service.MenuMngVO;

@Controller
@RequestMapping("/cmmn/sys")
public class MenuMngController extends CmmnController{

	@Resource(name="MenuMngService")
	private MenuMngService menuMngService;
	
	/***
	 * 메뉴 페이지 접근
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/menuMngPage.do")
	public String menuMngPage(@ModelAttribute("MenuMngVO") MenuMngVO vo, Model model) throws Exception {

		model.addAttribute("items", vo);
		
		return "back/system/menu/menuMng";
	}
	
	/***
	 * 메뉴 목록 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchMenuMngList.ajax")
	public String searchMenuMngList(@ModelAttribute("MenuMngVO") MenuMngVO vo, Model model) throws Exception {

		List<MenuMngVO> resultList = menuMngService.searchMenuMngList(vo);
		
		model.addAttribute("rows", resultList);
		/* 페이징 처리 */
		model.addAttribute("pages", vo);
		
		return "jsonView";
	}
	
	/***
	 * 메뉴 등록 팝업
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/popup/mngr/auth/menuPopup.do")
	public String MenuPopup(@ModelAttribute("MenuMngVO") MenuMngVO vo, Model model) throws Exception {
		
		model.addAttribute("mParam", vo);
		
		return "/popup/mngr/auth/menuPopup";
	}
	/***
	 * 메뉴 언어설정 페이지 접근
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/menuMngLangRegistPage.do")
	public String menuMngLangRegistPage(@ModelAttribute("MenuMngVO") MenuMngVO vo, Model model) throws Exception {

		//List<MenuMngVO> resultList = menuMngService.searchMenuMngLangList(vo);
		
		//model.addAttribute("rows", resultList);
		
		model.addAttribute("items", vo);
		
		return "popup/system/menu/menuMngLangRegist";
	}

	/***
	 * 메뉴 언어설정 페이지 접근
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/menuMngLangPage.ajax")
	public String menuMngLangPage(@ModelAttribute("MenuMngVO") MenuMngVO vo, Model model) throws Exception {

		MenuMngVO menuVO = menuMngService.searchMenuMngLangList(vo);
		
		model.addAttribute("row", menuVO);
		
		return "jsonView";
	}
	
	/***
	 * 메뉴 언어설정 저장
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registMenuMngLang.ajax")
	public String registMenuMngLang(@ModelAttribute("MenuMngVO") MenuMngVO vo, Model model) throws Exception {
		
		menuMngService.registMenuMngLang(vo);
		
		return "jsonView";
	}
	
	/**
	 * 메뉴 삭제
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeMenuMng.ajax")
	public String removeMenuMng(@ModelAttribute("MenuMngVO") MenuMngVO vo, Model model) throws Exception {

		menuMngService.removeMenuMng(vo);
		
		return "jsonView";
	}
	
	/**
	 * 메뉴 LANG 삭제
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeMenuMngLang.ajax")
	public String removeMenuMngLang(@ModelAttribute("MenuMngVO") MenuMngVO vo, Model model) throws Exception {
		
		menuMngService.removeMenuMngLangOne(vo);
		
		return "jsonView";
	}
	
	/**
	 * 메뉴 등록
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registMenuMng.ajax")
	public String registMenuMng(@ModelAttribute("MenuMngVO") MenuMngVO vo, Model model) throws Exception {
		
		menuMngService.registMenuMng(vo);
		
		return "jsonView";
	}
}
