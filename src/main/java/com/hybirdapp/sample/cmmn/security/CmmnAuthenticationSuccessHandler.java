package com.hybirdapp.sample.cmmn.security;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hybirdapp.sample.cmmn.security.service.DynamicSecurityService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hybirdapp.sample.cmmn.CmmnConstant;

public class CmmnAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name = "DynamicSecurityService")
	private DynamicSecurityService iGDynamicSecurityService;
	
	private RedirectStrategy redirectUrl = new DefaultRedirectStrategy();
	
	// 사용자가 성공적으로 로그인했다면 이동할 URL
	private String successUrl; 

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
		try {
			
			successUrl = iGDynamicSecurityService.setSessionInfo(request, response);
			
			/* 로그인 성공후 Ajax 호출이면 */
			if (CmmnConstant.REQUEST_IS_AJAX.equals(request.getHeader(CmmnConstant.REQUEST_AJAX_CALL))) {
			
				ObjectMapper om = new ObjectMapper();

				Map<String, Object> map = new HashMap<String, Object>();
				
				// 로그인 이전 화면으로 보내려 할 때
				//map.put(CmmnConstant.REQUEST_REDIRECT, getReturnUrl(request, response));
				
				// 메인으로 보낸다.
				map.put(CmmnConstant.REQUEST_REDIRECT, successUrl);

				String jsonString = om.writeValueAsString(map);

				OutputStream out = response.getOutputStream();
				out.write(jsonString.getBytes());
				
			/* 로그인 성공후 Form submit 호출이면 */
			} else {
				
				// 로그인 이전 화면으로 보내려 할 때
				//redirectUrl.sendRedirect(request, response, getReturnUrl(request, response));
				
				// 메인으로 보낸다.
				redirectUrl.sendRedirect(request, response, successUrl);
			}
			
		} catch (Exception e) {
			logger.error("onAuthenticationSuccess", e);
		}
	}
	
	
	/**
	 * 로그인 하기 전의 요청했던 URL을 알아낸다.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getReturnUrl(HttpServletRequest request, HttpServletResponse response) {
		
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if (savedRequest == null) {
			return successUrl;
		} 
		
		return savedRequest.getRedirectUrl();
	}
	
}
