package com.hybirdapp.sample.cmmn.script.web;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnController;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import com.hybirdapp.sample.cmmn.script.service.AttachVO;
import com.hybirdapp.sample.cmmn.script.service.ScriptService;
import com.hybirdapp.sample.cmmn.security.service.DynamicSecurityService;
import com.hybirdapp.sample.cmmn.sys.service.ClCdVO;

@Controller
@RequestMapping("/cmmn/script")
public class ScriptController extends CmmnController {

	@Resource(name = "ScriptService")
	private ScriptService  scriptService;
	
	@Resource(name = "DynamicSecurityService")
	private DynamicSecurityService  dynamicSecurityService;
	
	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService  cmmnFileSaveService;

	/***
	 * 공통코드 목록 조회
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchCmmnCdList.ajax")
	public String searchCmmnCdList(@ModelAttribute("ClCdVO") ClCdVO vo, Model model) throws Exception {

		List<?> resultList = scriptService.selectCmmnCdList(vo);
		model.addAttribute("rows", resultList);
		
		return "jsonView";
	}
	
	/***
	 * 공통스크립트 로드
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/scriptCmmn.do")
	public String backCommonScript() throws Exception {
		return "cmmn/script/scriptCmmn";
	}
	
	/***
	 * 공통 메세지 팝업 로드
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sysCmmnScriptMsgAlert.do")
	public String backCommonScriptPopup() throws Exception {
		return "cmmn/script/sysCmmnScriptMsgAlert";
	}
	
	/***
	 * 로그인 레이어 스크립트 로드
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sysCmmnLoginLayer.do")
	public String backCommonScriptLoginLayer() throws Exception {
		return "cmmn/script/sysCmmnLoginLayer";
	}
	
	/**
	 * 파일 다운로드
	 * @param vo
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/attachDownload.ajax")
	public void attachDownload(@ModelAttribute("AttachVO") AttachVO vo, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AttachVO attachInfo = scriptService.selectAttachInfo(vo);
	    
	    String sFileFullPathWithFileName = attachInfo.getFilePath();
	    String sLogicalFileNm = attachInfo.getFileOrgNm();
	     
	    byte byFile[] = FileUtils.readFileToByteArray(new File(sFileFullPathWithFileName));
	    
	    response.setContentType("application/octet-stream");
	    response.setContentLength(byFile.length);
	    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(sLogicalFileNm,"UTF-8")+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.getOutputStream().write(byFile);
	     
	    response.getOutputStream().flush();
	    response.getOutputStream().close();

	}
	
	/***
	 * 파일 삭제
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/attachFileDelete.ajax")
	public String attachFileDelete(@ModelAttribute("AttachVO") AttachVO vo, Model model) throws Exception {

		String fileGrpSn = vo.getFileGrpSn();
//		String fileSn = vo.getFileSn();
		
//		cmmnFileSaveService.deleteFile(fileGrpSn, fileSn);

		return "jsonView";
	}
	
	/***
	 * 날씨
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectWeather.ajax")
	public String selectWeather(Model model) throws Exception {

		String day = "";
		String tmp_day = "";
		String temp = "";
		String sky = "";
		String ws = "";
		String wdKor = "";
		String wdEn = "";
		String weatherImg = "";
		String weatherTmp = "";
		String windImg = "";
		String windSpeed = "";
		
		SAXBuilder builder = new SAXBuilder();
		Document jdomdoc = builder.build(new java.net.URL("https://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4374531000"));

		Element root= jdomdoc.getRootElement();
		Element peresons_E1 =root.getChild("channel");
		Element peresons_E2 =peresons_E1.getChild("item");
		Element peresons_E3 =peresons_E2.getChild("description");
		Element peresons_E4 =peresons_E3.getChild("body");

		List<Element> list= peresons_E4.getChildren("data");
		
		for(int i=0;i<list.size(); i++){
	         Element person_E=list.get(i);

	         day = person_E.getChild("day").getValue();
	         if(!day.equals(tmp_day)){
	               /* 온도 */
	               temp = person_E.getChild("temp").getValue().replace(".0", "");
	               /* 최고-최저 온도 */
	               /* tmx = person_E.getChild("tmx").getValue().replace(".0", "");
	               tmn = person_E.getChild("tmn").getValue().replace(".0", ""); */
	               /* 날씨 */
	               sky = person_E.getChild("wfKor").getValue();
	               /* 풍속,풍향 */
	               String tmp_ws = person_E.getChild("ws").getValue();
	               double wsDouble = Double.parseDouble(tmp_ws);
	
	               ws = String.format("%.1f", wsDouble); // 소수점 한 자리까지 표시
	               wdKor = person_E.getChild("wdKor").getValue();
	               wdEn = person_E.getChild("wdEn").getValue();
	
	               String icon = "";
	
	               String windIcon = "Wind";
	
	               //날씨구분 (icon)
	               if(sky.equals("맑음")) icon = "Clearly";
	               else if(sky.equals("구름 조금")) icon ="Partly Cloud";
	               else if(sky.equals("구름 많음")) icon = "Mostly Cloud";
	               else if(sky.equals("흐림")) icon = "Cloud";
	               else if(sky.equals("비")) icon = "Rainy";
	               else if(sky.equals("눈")) icon ="Snow_night";
	               else if(sky.equals("눈/비")) icon = "SnowRain";
	               else icon = "Clearly";
	
	               if(day.equals("0") && person_E.getAttributeValue("seq").equals("0")){
                          weatherImg = "<img src='/mngrResource/images/weather/"+ icon +".png' alt='현재날씨 아이콘' style='width:100%;'>";
                          weatherTmp = temp+"<span class='symbol'>℃</span>";
                          windImg = "<img class='wind " + wdEn + "' src='/mngrResource/images/weather/"+ windIcon +".png' alt='풍향 아이콘' style='width:100%;'>";
                          windSpeed += ws + "<span class='symbol'>m/s</span>";
                          wdEn = wdEn;
                          break;
	               }
	         }         

	         tmp_day = day;
		}
		model.addAttribute("windSpeed", windSpeed);
		model.addAttribute("windImg", windImg);
		model.addAttribute("weatherTmp", weatherTmp);
		model.addAttribute("weatherImg", weatherImg);
		model.addAttribute("wdKor", wdKor);
		model.addAttribute("wdEn", wdEn);
		return "jsonView";
	}
}