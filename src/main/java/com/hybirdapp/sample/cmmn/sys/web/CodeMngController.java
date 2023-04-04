package com.hybirdapp.sample.cmmn.sys.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hybirdapp.sample.cmmn.SessionUtil;
import com.hybirdapp.sample.cmmn.sys.service.ClGrpVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.sys.service.ClCdVO;
import com.hybirdapp.sample.cmmn.sys.service.CodeMngService;

@Controller
@RequestMapping("/mngr/common")
public class CodeMngController extends CmmnController{

	@Resource(name="CodeMngService")
	private CodeMngService codeMngService;
	
	/***
	 * 코드 리스트조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/clGrpCdView.do")
	public String codeMngPage(@ModelAttribute("clGrpVO") ClGrpVO vo, Model model) throws Exception {
		
		/* 화면전환시 파라미터를 유지하기 위한 처리 */
		return "mngr:/common/clGrpCdView";
	}
	
	
	/***
	 * 분류코드 목록 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchClGrpList.ajax")
	public String searchClGrpList(@ModelAttribute("clGrpVO") ClGrpVO vo, Model model) throws Exception {

		List<ClGrpVO> resultList = codeMngService.searchClGrpList(vo);
		
		model.addAttribute("result", resultList);
		/* 페이징 처리 */
		model.addAttribute("pages", vo);
		
		return "jsonView";
	}
	
	/***
	 * 분류코드 등록, 수정 팝업
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/clGrpPopup.do")
	public String prntClGrpPopup(@ModelAttribute("clGrpVO") ClGrpVO vo, Model model) throws Exception {
		
		model.addAttribute("mParam", vo);
		
		return "/mngr/common/popup/clGrpPopup";
	}
	
	/***
	 * 분류코드 등록,상세 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/clGrpDetailView.do")
	public String codeMngRegistPage(@ModelAttribute("clGrpVO") ClGrpVO vo, Model model) throws Exception {

		/* 화면전환시 파라미터를 유지하기 위한 처리 */
		model.addAttribute("mParam", vo);
		
		return "mngr:/common/clGrpDetailView";
	}
	
	
	/***
	 * 분류코드 상세 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchClGrp.ajax")
	public String searchClGrp(@ModelAttribute("clGrpVO") ClGrpVO vo, Model model) throws Exception {
		
		/* 분류코드상세조회 */
		model.addAttribute("result", codeMngService.searchClGrp(vo));
	
		return "jsonView";
	}

	
	/***
	 * 분류코드 저장 처리
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registClGrp.ajax")
	public String registCodeMng(@ModelAttribute("clGrpVO") ClGrpVO vo, Model model) throws Exception {
		vo.setIsrtIp(SessionUtil.getUserIp());
		vo.setUpdtIp(SessionUtil.getUserIp());
		codeMngService.registClGrp(vo);
		
		return "jsonView";
	}
	/***
	 * 분류코드 수정 처리
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyClGrp.ajax")
	public String modifyCodeMng(@ModelAttribute("clGrpVO") ClGrpVO vo, Model model) throws Exception {
		vo.setIsrtIp(SessionUtil.getUserIp());
		vo.setUpdtIp(SessionUtil.getUserIp());
		codeMngService.modifyClGrp(vo);
		
		return "jsonView";
	}
	
	/***
	 * 공통코드 목록 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchClCdList.ajax")
	public String searchClCdList(@ModelAttribute("clCdVO") ClCdVO vo, Model model) throws Exception {

		List<ClCdVO> resultList = codeMngService.searchClCdList(vo);
		
		model.addAttribute("result", resultList);
		model.addAttribute("clGrpNm", vo.getClGrpNm());
		/* 페이징 처리 */
		model.addAttribute("pages", vo);
		
		return "jsonView";
	}
	
	/***
	 * 공통코드 등록, 수정 팝업
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/clCdPopup.do")
	public String prntClCdPopup(@ModelAttribute("clCdVO") ClCdVO vo, Model model) throws Exception {
		
		model.addAttribute("mParam", vo);
		
		return "/mngr/common/popup/clCdPopup";
	}
	
	/***
	 * 분류코드 등록,상세 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/clCdDetailView.do")
	public String clCdDetailView(@ModelAttribute("clCdVO") ClCdVO vo, Model model) throws Exception {
		
		model.addAttribute("mParam", vo);
		
		return "mngr:/common/clCdDetailView";
	}
	
	
	/***
	 * 공통코드 상세 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchClCd.ajax")
	public String searchClCd(@ModelAttribute("clCdVO") ClCdVO vo, Model model) throws Exception {

		ClCdVO result = codeMngService.searchClCd(vo);
		/* 분류코드상세조회 */
		model.addAttribute("result", result);
		
		return "jsonView";
	}
	
	/**
	 * 공통코드 상세 등록 처리
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registClCd.ajax")
	public String registCodeDtlMng(@ModelAttribute("clCdVO") ClCdVO vo, Model model) throws Exception {
		vo.setIsrtIp(SessionUtil.getUserIp());
		vo.setUpdtIp(SessionUtil.getUserIp());
		codeMngService.registClCd(vo);
		
		return "jsonView";
	}
	/**
	 * 공통코드 상세 수정 처리
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyClCd.ajax")
	public String modifyCodeDtlMng(@ModelAttribute("clCdVO") ClCdVO vo, Model model) throws Exception {
		vo.setIsrtIp(SessionUtil.getUserIp());
		vo.setUpdtIp(SessionUtil.getUserIp());
		codeMngService.modifyClCd(vo);
		
		return "jsonView";
	}

	/**
	 * 분류코드 삭제
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteClGrp.ajax")
	public String deleteClGrp(@ModelAttribute("clCdVO") ClCdVO vo, Model model) throws Exception {
		
		codeMngService.deleteClGrp(vo);
		
		return "jsonView";
	}
	
	/**
	 * 공통코드 삭제
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteClCd.ajax")
	public String deleteClCd(@ModelAttribute("clCdVO") ClCdVO vo, Model model) throws Exception {
		
		codeMngService.deleteClCd(vo);
		
		return "jsonView";
	}

	/***
	 * 시설 목록 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectFcltList.ajax")
	public String selectFlctList(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		
		List resultList = (List)codeMngService.selectFcltList(params);
		model.addAttribute("resultList", resultList);
		
		return "jsonView";
	}
	
	/***
	 * 그룹 목록 조회
	 */
	@RequestMapping(value = "/selectGrpList.ajax")
	public String selectGrpList(HttpServletRequest request, Model model) throws Exception {
		DataClass params = new DataClass(request);
		
		List resultList = (List)codeMngService.selectGrpList(params);
		model.addAttribute("resultList", resultList);
		
		return "jsonView";
	}
}
