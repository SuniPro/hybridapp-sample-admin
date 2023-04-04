package com.hybirdapp.sample.mngr.tckMng.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hybirdapp.sample.mngr.tckMng.service.TckMngService;
import com.hybirdapp.sample.mngr.tckMng.service.TckMngVO;
import com.hybirdapp.sample.cmmn.CmmnConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;

@Controller
@RequestMapping("/mngr/tckMng")
public class TckMngController extends CmmnController {

	@Resource(name = "TckMngService")
	private TckMngService tckMngService;

	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;

	/***
	 * 티켓
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckSlsDtlView.do")
	public String tckSlsDtlView(@ModelAttribute("TckMngVO") TckMngVO vo, Model model) throws Exception {

		model.addAttribute("serStartDate", CmmnConstant.SER_START_DATE);

		return "mngr:/tckMng/tckSlsDtlView";
	}

	/***
	 * 티켓분류관리
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckInqView.do")
	public String tckInqView(Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);
		
		model.addAttribute("mParam", params.getMap());

		return "mngr:/tckMng/tckInqView";
	}
	
	// TODO 삭제대상
	@RequestMapping(value = "/searchTckMngList.ajax")
	public String searchTckMngList(@ModelAttribute("TckMngVO") TckMngVO vo, Model model, HttpServletRequest request) throws Exception {

		DataClass params = new DataClass(request);

		HttpSession ses = request.getSession(true);

		params.set("currPageIdx",  vo.getCurrPageIdx());
		params.set("offsetIndex",  vo.getOffsetIndex());
		params.set("pageMemory",  vo.getPageMemory());
		params.set("pageSize",  vo.getPageSize());
		params.set("pageBottomSize",  vo.getPageBottomSize());

		int totPageCnt = tckMngService.searchTckMngListCnt(params);
		vo.setTotPageCnt(totPageCnt);
		model.addAttribute("totalCnt",totPageCnt);

		model.addAttribute("result", tckMngService.searchTckMngList(params));

		model.addAttribute("pages", vo);

		return "jsonView";
	}

	/***
	 * 티켓분류관리(팝업)
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tckInqPopup.do")
	public String tckInqPopup(Model model, HttpServletRequest request) throws Exception {
		DataClass params = new DataClass(request);                                                           
		HttpSession ses = request.getSession(true);

		model.addAttribute("mParam", params.getMap());
		
		/* 조회 */
		model.addAttribute("resultSngList", tckMngService.searchTckList(params));

		return "mngr/tckMng/popup/tckInqPopup";
	}


	// TODO 삭제 대상
	/* 티켓분류 수정 */
	@RequestMapping(value = "/modifyTckMng.ajax")
	public String modifyTckMng(@RequestBody(required = false) List<TckMngVO> dataArray, Model model) throws Exception {

		for(TckMngVO vo : dataArray) {
			
			tckMngService.modifyTckMng(vo);
		}
		
		return "jsonView";
	}


	// TODO 삭제 대상
	/* 티켓분류 등록 */
	@RequestMapping(value = "/registTckMng.ajax")
	public String registTckMng(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		tckMngService.registTckMng(params);
		
		return "jsonView";
	}


	// TODO 삭제 대상
	/* 티켓분류 삭제 */
	@RequestMapping(value = "/deleteTckMng.ajax")
	public String deleteGrpPrd(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DataClass params = new DataClass(request);
		tckMngService.deleteTckMng(params);
		return "jsonView";
	}

}
