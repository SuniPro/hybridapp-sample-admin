package com.hybirdapp.sample.mngr.notice.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hybirdapp.sample.cmmn.SessionUtil;
import com.hybirdapp.sample.mngr.notice.service.NoticeService;
import com.hybirdapp.sample.mngr.notice.service.NoticeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;

@Controller
@RequestMapping("/mngr/notice")
public class NoticeController extends CmmnController{
	
	@Resource(name = "NoticeService")
	private NoticeService noticeService;
	
	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService  cmmnFileSaveService;
	
	/***
	 * 공지사항 목록
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/noticeView.do")
	public String noticeView(@ModelAttribute("NoticeVO") NoticeVO vo, Model model) throws Exception {
		
		model.addAttribute("mParam", vo);
		
		return "mngr:/notice/noticeView";
	}
	
	/***
	 * 공지사항 목록 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchNoticeList.ajax")
	public String searchNoticeList(@ModelAttribute("NoticeVO") NoticeVO vo, Model model) throws Exception {
		
		/* 목록 조회 */
		model.addAttribute("result", noticeService.searchNoticeList(vo));
		/* 페이징 처리 */
		model.addAttribute("pages", vo);
		
		return "jsonView";
	}
	
	/***
	 * 공지사항 상세
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/noitceDetailView.do")
	public String noitceDetailView(@ModelAttribute("NoticeVO") NoticeVO vo, Model model) throws Exception {
		
		/* 조회수 증가 */
//		noticeService.modifyMngrNoticeInqireCo(vo);
		
		model.addAttribute("mParam", vo);
		
		return "mngr:/notice/noticeDetailView";
	}
	
	/***
	 * 공지사항 상세 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchMngrNotice.ajax")
	public String searchMngrNotice(@ModelAttribute("NoticeVO") NoticeVO vo, Model model) throws Exception {
		
		model.addAttribute("result", noticeService.searchMngrNotice(vo));
		model.addAttribute("image", cmmnFileSaveService.selectFile( "NOTICE", Integer.parseInt(vo.getNoticeSn())));
		return "jsonView";
	}
		
	/***
	 * 공지사항 저장
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registNotice.ajax")
	public String registNotice(@ModelAttribute("NoticeVO") NoticeVO vo, Model model, @RequestParam("fileUpload") MultipartFile[] file,HttpServletRequest request) throws Exception {
		
		List<Map<String, Object>> fileMap = new ArrayList<Map<String, Object>>();
		for(int i=0; i<file.length; i++) {
			if(!"".equals(file[i].getOriginalFilename())) {
				fileMap = cmmnFileSaveService.saveFileMulti(file, "NOTICE");
			}
		}
		
		
		vo.setIsrtIp(SessionUtil.getUserIp());
		vo.setUpdtIp(SessionUtil.getUserIp());
		
		/* 공지사항 등록 */
		model.addAttribute("result", noticeService.registNotice(fileMap,vo));
		
		return "jsonView";
	}
	
	/***
	 * 이미지 삭제
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/noticeImageDel.ajax")
	public String noticeImageDel(@ModelAttribute("NoticeVO") NoticeVO vo, Model model) throws Exception {
		cmmnFileSaveService.deleteFile( "NOTICE", Integer.parseInt(vo.getNoticeSn()));
		
		return "jsonView";
	}
	
	/***
	 * 공지사항 에디터 저장
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editorFileUpload.ajax")
	public String editorFileUpload(@ModelAttribute("NoticeVO") NoticeVO vo, Model model, @RequestParam("fileUpload") MultipartFile file,HttpServletRequest request,HttpSession session) throws Exception {
		String rootUrl = request.getRequestURL().toString().replace(request.getRequestURI(),"").replace("http://", "https://");
		String fileUrl = cmmnFileSaveService.editorsaveFile(file, rootUrl);
		model.addAttribute("fileUrl", fileUrl);
		return "jsonView";
	}
	
	/***
	 * 공지사항 수정
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyNotice.ajax")
	public String modifyAnswer(@ModelAttribute("NoticeVO") NoticeVO vo, @RequestParam("fileUpload") MultipartFile[] file, Model model) throws Exception {
		vo.setIsrtIp(SessionUtil.getUserIp());
		vo.setUpdtIp(SessionUtil.getUserIp());
		
		
		List<Map<String, Object>> fileMap = new ArrayList<Map<String, Object>>();
		if("I".equals(vo.getImageType())) {
			for(int i=0; i<file.length; i++) {
				if(!"".equals(file[i].getOriginalFilename())) {
					fileMap = cmmnFileSaveService.saveFileMulti(file, "NOTICE");
				}
			}
		}
		
		model.addAttribute("result", noticeService.modifyNotice(fileMap, vo));
		
		return "jsonView";
	}
	
	/***
	 * 공지사항 삭제
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeMngrNotice.ajax")
	public String removeMngrNotice(@ModelAttribute("NoticeVO") NoticeVO vo, Model model) throws Exception {
		vo.setUpdtIp(SessionUtil.getUserIp());
		noticeService.removeMngrNotice( vo);
		
		return "jsonView";
	}
	
	/***
	 * 메인 공지사항 목록 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mainNoticeList.ajax")
	public String mainNoticeList(@ModelAttribute("NoticeVO") NoticeVO vo, Model model) throws Exception {
		
		/* 목록 조회 */
		model.addAttribute("result", noticeService.mainNoticeList(vo));
		/* 페이징 처리 */
		return "jsonView";
	}
}
