package com.hybirdapp.sample.mngr.event.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hybirdapp.sample.cmmn.SessionUtil;
import com.hybirdapp.sample.mngr.event.service.EventService;
import com.hybirdapp.sample.mngr.event.service.EventVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;

@Controller
@RequestMapping("/mngr/event")
public class EventController extends CmmnController{
	
	@Resource(name = "EventService")
	private EventService eventService;
	
	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService  cmmnFileSaveService;
	
	/***
	 * 이벤트 목록
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/eventView.do")
	public String eventView(@ModelAttribute("EventVO") EventVO vo, Model model) throws Exception {
		
		model.addAttribute("mParam", vo);
		
		return "mngr:/event/eventView";
	}
	
	/***
	 * 이벤트 목록 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchEventList.ajax")
	public String searchEventList(@ModelAttribute("EventVO") EventVO vo, Model model) throws Exception {
		
		/* 목록 조회 */
		model.addAttribute("result", eventService.searchEventList(vo));
		
		/* 페이징 처리 */
		model.addAttribute("pages", vo);
		
		return "jsonView";
	}
	
	/***
	 * 이벤트 상세
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/eventDetailView.do")
	public String eventDetailView(@ModelAttribute("EventVO") EventVO vo, Model model) throws Exception {
		
		
		model.addAttribute("mParam", vo);
		
		return "mngr:/event/eventDetailView";
	}
	
	/***
	 * 이벤트 상세 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchMngrEvent.ajax")
	public String searchMngrEvent(@ModelAttribute("EventVO") EventVO vo, Model model) throws Exception {
		
		model.addAttribute("result", eventService.searchMngrEvent(vo));
		/* 이미지 조회 */
		model.addAttribute("image", cmmnFileSaveService.selectFile( "EVENT", Integer.parseInt(vo.getEvntSn())));
		return "jsonView";
	}
		
	/***
	 * 이벤트 저장
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registEvent.ajax")
	public String registEvent(@ModelAttribute("EventVO") EventVO vo, Model model, @RequestParam("fileUpload") MultipartFile[] file,HttpServletRequest request) throws Exception {
		
		List<Map<String, Object>> fileMap = new ArrayList<Map<String, Object>>();
		for(int i=0; i<file.length; i++) {
			if(!"".equals(file[i].getOriginalFilename())) {
	//			String rootUrl = request.getRequestURL().toString().replace(request.getRequestURI(),"")+"/";
				fileMap = cmmnFileSaveService.saveFileMulti(file, "EVENT");
			}
		}
		
		
		vo.setIsrtIp(SessionUtil.getUserIp());
		vo.setUpdtIp(SessionUtil.getUserIp());
		/* 이벤트 등록 */
		model.addAttribute("result", eventService.registEvent(fileMap,vo));
		
		return "jsonView";
	}
	
	/***
	 * 이미지 삭제
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/eventImageDel.ajax")
	public String eventImageDel(@ModelAttribute("EventVO") EventVO vo, Model model) throws Exception {
		cmmnFileSaveService.deleteFile( "EVENT", Integer.parseInt(vo.getEvntSn()));
		
		return "jsonView";
	}
	
	/***
	 * 이벤트 에디터 저장
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editorFileUpload.ajax")
	public String editorFileUpload(@ModelAttribute("EventVO") EventVO vo, Model model, @RequestParam("fileUpload") MultipartFile file,HttpServletRequest request,HttpSession session) throws Exception {
		String rootUrl = request.getRequestURL().toString().replace(request.getRequestURI(),"").replace("http://", "https://");
		String fileUrl = cmmnFileSaveService.editorsaveFile(file, rootUrl);
		model.addAttribute("fileUrl", fileUrl);
		return "jsonView";
	}
	
	/***
	 * 이벤트 수정
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyEvent.ajax")
	public String modifyEvent(@ModelAttribute("EventVO") EventVO vo,@RequestParam("fileUpload") MultipartFile[] file, Model model) throws Exception {
		vo.setIsrtIp(SessionUtil.getUserIp());
		vo.setUpdtIp(SessionUtil.getUserIp());
		
		List<Map<String, Object>> fileMap = new ArrayList<Map<String, Object>>();
		if("I".equals(vo.getImageType())) {
			for(int i=0; i<file.length; i++) {
				if(!"".equals(file[i].getOriginalFilename())) {
					fileMap = cmmnFileSaveService.saveFileMulti(file, "EVENT");
				}
			}
		}
		model.addAttribute("result", eventService.modifyEvent(fileMap,vo));
		return "jsonView";
	}
	
	/***
	 * 이벤트 삭제
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeMngrEvent.ajax")
	public String removeMngrEvent(@ModelAttribute("EventVO") EventVO vo, Model model) throws Exception {
		vo.setUpdtIp(SessionUtil.getUserIp());
		eventService.removeMngrEvent( vo);
		
		return "jsonView";
	}
	
}
