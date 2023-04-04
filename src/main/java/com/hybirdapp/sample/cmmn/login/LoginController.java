package com.hybirdapp.sample.cmmn.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hybirdapp.sample.cmmn.SessionUtil;
import com.hybirdapp.sample.cmmn.script.service.ScriptService;
import com.hybirdapp.sample.cmmn.security.service.impl.DynamicSecurityDAO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybirdapp.sample.cmmn.CmmnConstant;
import com.hybirdapp.sample.cmmn.CmmnController;

@Controller
public class LoginController extends CmmnController {

	@Resource(name="LoginService")
	private com.hybirdapp.sample.cmmn.login.service.LoginService LoginService;
	
	@Resource(name="ScriptService")
	private ScriptService scriptService;
	
	@Resource(name = "DynamicSecurityDAO")
	private DynamicSecurityDAO dynamicSecurityDAO;
	
	/***
	 * 접속 구분
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bfMngrIndex.do")
	public String bfMngrIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String redirectUrl = "";
		
		redirectUrl = "redirect:/mngrMain.do";
		
		return redirectUrl;
	}
	
	/***
	 * 로그아웃 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bfMngrLogout.do")
	public String bfMngrLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String redirectUrl = "";
		
		// 로그아웃을 실행하면 auth 내용을 클리어
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
//	    redirectUrl = "redirect:/mngrMain.do";
	    redirectUrl = "/mngr/login/movePage";
	    return redirectUrl;
	}
	
	/***
	 * 관리자 인트로 페이지 접근
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mngrMain.do")
	public String mngrMain(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String returnStr = "";
		// 로그인 하지 않은 상용자면 기본 화면을 조회 = 관리자는 Authentication null 발생함
		if (auth == null || CmmnConstant.USER_ANONYMOUS.equals(auth.getPrincipal())){
	    	
			// 로그아웃하면 session이 완전히 사라짐
    		HttpSession session = request.getSession(false);
    		
    		// 그래서 하나 생성해줌 어차피 anonymousUser 이므로 생성해도 될 듯?
    		if (session == null) {
    			session = request.getSession();
    		}
    		
    		// 관리자는 기본 메뉴가 필요없음
    		//session.setAttribute(CmmnConstant.SESSION_MENU, LoginService.searchBasicViewMenuList());
    		//session.setAttribute(CmmnConstant.SESSION_MENU_NAVI, LoginService.searchBasicViewMenuNaviList());
    		
    		// 타입코드가 넘어오면 Head에서 Alert 처리
    		String typeCode = SessionUtil.getDirectAttribute(CmmnConstant.CHECK_QUERY_STRING);
    		
    		if (typeCode != null && !"".equals(typeCode)) {
    			model.addAttribute(CmmnConstant.CHECK_QUERY_STRING, typeCode);
    			// 타입코드가 계속 있으면 안되므로 제거
    			session.removeAttribute(CmmnConstant.CHECK_QUERY_STRING);
    		}
    		returnStr = "/mngr/login/mngrLoginLayer";
    		
    	// 로그인한 사용자면
	    } else {
	    	// 필요시 구분에 따라 초기진입 화면을 정의함
	    	returnStr = "mngr:/intro/mngrMainInfo";
	    }
		
		return returnStr;
	}
}
