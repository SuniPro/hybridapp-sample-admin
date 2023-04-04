package com.hybirdapp.sample.cmmn.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.cmmn.sys.service.MenuMngService;
import com.hybirdapp.sample.cmmn.sys.service.MenuMngVO;

@Service("MenuMngService")
public class MenuMngServiceImpl implements MenuMngService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name = "MenuMngDAO")
	private MenuMngDAO menuMngDAO;
	
	@Override
	public List<MenuMngVO> searchMenuMngList(MenuMngVO vo) throws Exception {
		
		List<MenuMngVO> resultList = null;
				
		try {
			
			resultList = menuMngDAO.searchMenuMngList(vo);
			vo.setTotPageCnt(menuMngDAO.searchMenuMngTotalCount());
		}
		catch (Exception ex) {
			logger.debug("searchMenuMngList", ex);
			// 조회중 오류가 발생했습니다.
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return resultList;
	}

	@Override
	public MenuMngVO searchMenuMngLangList(MenuMngVO vo) throws Exception {

		MenuMngVO menuVO = null;
		List<MenuMngVO> menuLangList = null;
				
		try {
			
			menuVO = menuMngDAO.searchMenuMng(vo);
			
			menuLangList = menuMngDAO.searchMenuMngLangList(vo);
			
			if(null != menuLangList && menuLangList.size() > 0) {
			
				menuVO.setMenuMngList(menuLangList);
			}
		}
		catch (Exception ex) {
			logger.debug("searchMenuMngList", ex);
			// 조회중 오류가 발생했습니다.
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return menuVO;
	}

	@Override
	public void removeMenuMng(MenuMngVO vo) throws Exception {
		
		int result = 0;
		
		try {
			
			// 권한메뉴버튼 삭제
			result = menuMngDAO.removeAuthMenuBtn(vo);
			
			logger.info(vo.getMenuCd() + " AUTH_MENU_BTN 삭제처리 : " + result + "건.");
			
			// 권한메뉴매핑 삭제
			result =  menuMngDAO.removeAuthMenuMapng(vo);
			
			logger.info(vo.getMenuCd() + " AUTH_MENU_MAPNG 삭제처리 : " + result + "건.");

			// 메뉴 LANG 삭제
			result =  menuMngDAO.removeMenuMngLangAll(vo);
			
			logger.info(vo.getMenuCd() + " MENU_LANG 삭제처리 : " + result + "건.");

			// 메뉴 삭제
			result =  menuMngDAO.removeMenuMng(vo);
			
			logger.info(vo.getMenuCd() + " MENU 삭제처리 : " + result + "건.");
		}
		catch(Exception ex) {
			logger.debug("removeMenuMng", ex);
			// 조회중 오류가 발생했습니다.
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.delete", null));
		}
	}

	@Override
	public void removeMenuMngLangOne(MenuMngVO vo) throws Exception {

		int result = 0;
		
		try {

			// 메뉴 LANG 삭제
			result =  menuMngDAO.removeMenuMngLangOne(vo);
			
			if(result < 1) {
				logger.info("[" + vo.getMenuCd() + "]	:	삭제 대상 없음");
			}
		}
		catch(Exception ex) {
			logger.debug("removeMenuMngLangOne", ex);
			// 조회중 오류가 발생했습니다.
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.delete", null));
		}
	}

	@Override
	public void registMenuMngLang(MenuMngVO vo) throws Exception {
		
		int result = 0;
		List<MenuMngVO> listVO = vo.getMenuMngList();
		
		try {
			
			if (listVO != null && listVO.size() > 0) {
				
				for(MenuMngVO menuLang : listVO) {
					
					result += menuMngDAO.registMenuMngLang(menuLang);
				}
			}
			
			logger.info("registMenuMngLang 등록 결과 : " + result);
		}
		catch(Exception ex ) {

			logger.error("registMenuMngLang", ex);
			// 저장중 오류발생
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.save", null));
		}
	}

	@Override
	public void registMenuMng(MenuMngVO vo) throws Exception {

		int result = 0;
		List<MenuMngVO> listVO = vo.getMenuMngList();
		
		try {
			
			result = menuMngDAO.registMenuMng(vo);
			
			if (listVO != null && listVO.size() > 0) {
				
				for(MenuMngVO menu : listVO) {
					
					menu.setMenuCd(vo.getMenuCd());
					menuMngDAO.registMenuMngLang(menu);
				}
			}
			
			logger.info("registMenuMng 등록 결과 : " + result);
		}
		catch(Exception ex ) {

			logger.error("registMenuMng", ex);
			// 저장중 오류발생
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.save", null));
		}
	}
}
