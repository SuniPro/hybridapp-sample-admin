package com.hybirdapp.sample.cmmn.sys.web;

import java.util.List;

import javax.annotation.Resource;

import com.hybirdapp.sample.cmmn.sys.service.AuthorMngDtlVO;
import com.hybirdapp.sample.cmmn.sys.service.AuthorMngMenuVO;
import com.hybirdapp.sample.cmmn.sys.service.AuthorMngService;
import com.hybirdapp.sample.cmmn.sys.service.AuthorMngVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnController;

@Controller
@RequestMapping("/cmmn/sys")
public class AuthorMngController extends CmmnController{

	@Resource(name="AuthorMngService")
	private AuthorMngService authorMngService;
	
	/***
	 * 권한 페이지 접근
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/authorMngPage.do")
	public String authorMngPage(@ModelAttribute AuthorMngVO vo, Model model) throws Exception {
		
		model.addAttribute("items", vo);
		
		return "back/system/author/authorMng";
	}
	
	/***
	 * 권한등록 페이지 접근
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/authorMngRegistPage.do")
	public String authorMngRegistPage(@ModelAttribute AuthorMngVO vo,  Model model) throws Exception {
		
		model.addAttribute("items", vo);
		
		return "popup/system/author/authorMngRegist";
	}
	
	/***
	 * 권한수정 페이지 접근
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/authorMngModifyPage.do")
	public String authorMngModifyPage(@ModelAttribute AuthorMngVO vo,  Model model) throws Exception {
		
		AuthorMngVO result = authorMngService.searchAuthorMng(vo);
		
		model.addAttribute("row", result);
		
		return "popup/system/author/authorMngModify";
	}
	
	/***
	 * 권한 메뉴(버튼) 설정 페이지 접근
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value = "/system/authorMngSetPage.do")
	@RequestMapping(value = "/authorMngMenuSetPage.do")
	public String authorMngMenuSetPage(@ModelAttribute AuthorMngVO vo, Model model) throws Exception {
		
		//List<AuthorMngMenuVO> resultList = authorMngService.searchAuthorMngMenuList(vo);
		//model.addAttribute("rows", resultList);
		
		model.addAttribute("items", vo);
		
		//return "popup/system/author/authorMngMenuSet";
		return "back/system/author/authorMngMenuSet";
	}
	
	/***
	 * 권한 목록 조회
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchAuthorMngList.ajax")
	public String searchAuthorMngList(@ModelAttribute AuthorMngVO vo, Model model) throws Exception {

		List<AuthorMngVO>  resultList = authorMngService.searchAuthorMngList(vo);
		
		model.addAttribute("rows", resultList);
		model.addAttribute("pages", vo);
		
		return "jsonView";
	}
	
	/***
	 * 권한 메뉴(버튼) 조회
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchAuthorMngMenuList.ajax")
	public String searchAuthorMngMenuList(@ModelAttribute AuthorMngVO vo, Model model) throws Exception {

		List<AuthorMngMenuVO> resultList = authorMngService.searchAuthorMngMenuList(vo);
		model.addAttribute("rows", resultList);
		
		return "jsonView";
	}
	
	
	/***
	 * 권한 조회
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchAuthorMng.ajax")
	public String searchAuthorMng(@ModelAttribute AuthorMngVO vo,  Model model) throws Exception {
		
		AuthorMngVO result = authorMngService.searchAuthorMng(vo);
		
		model.addAttribute("row", result);
		
		return "jsonView";
	}
	
	/***
	 * 권한 등록
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registAuthorMng.ajax")
	public String registAuthorMng(@ModelAttribute AuthorMngVO vo,  Model model) throws Exception {
		
		authorMngService.registAuthorMng(vo);
		
		return "jsonView";
	}
	
	/***
	 * 권한 수정
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyAuthorMng.ajax")
	public String modifyAuthorMng(@ModelAttribute AuthorMngVO vo,  Model model) throws Exception {
		
		authorMngService.modifyAuthorMng(vo);
		
		return "jsonView";
	}
	
	/***
	 * 권한 메뉴(버튼) 등록
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registAuthorMngMenu.ajax")
	public String registAuthorMngMenu(@ModelAttribute AuthorMngMenuVO vo,  Model model) throws Exception {
		
		authorMngService.registAuthorMngMenu(vo);
		
		return "jsonView";
	}
	
	/***
	 * 권한 언어 삭제
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeAuthorMngLang.ajax")
	public String removeAuthorMngLang(@ModelAttribute AuthorMngDtlVO vo, Model model) throws Exception {
		
		authorMngService.removeAuthorMngLang(vo);
		
		return "jsonView";
	}

	/***
	 * 권한 공통 조회
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchAuthorCmmnList.ajax")
	public String searchAuthorCmmnList(@ModelAttribute AuthorMngVO vo,  Model model) throws Exception {
		
		List<AuthorMngVO> resultList = authorMngService.searchAuthorCmmnList(vo);
		
		model.addAttribute("rows", resultList);
		
		return "jsonView";
	}
	
}
