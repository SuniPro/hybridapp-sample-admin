package com.hybirdapp.sample.cmmn.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hybirdapp.sample.cmmn.CmmnConstant;
import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.cmmn.sys.service.AuthorMngDtlVO;
import com.hybirdapp.sample.cmmn.sys.service.AuthorMngMenuVO;
import com.hybirdapp.sample.cmmn.sys.service.AuthorMngService;
import com.hybirdapp.sample.cmmn.sys.service.AuthorMngVO;

@Service("AuthorMngService")
public class AuthorMngServiceImpl implements AuthorMngService {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name="AuthorMngDAO")
	private AuthorMngDAO authorMngDAO;

	@Override
	public List<AuthorMngVO> searchAuthorMngList(AuthorMngVO vo) throws Exception {
		
		List<AuthorMngVO> authorMngVOList = null;
		
		try {
			
			authorMngVOList = authorMngDAO.selectAuthorMngList(vo);
			
			if (authorMngVOList != null && authorMngVOList.size() > 0) {
				
				vo.setTotPageCnt(authorMngDAO.selectAuthorMngTotalCount());
				
				for (AuthorMngVO authorMngVO : authorMngVOList) {
					List<AuthorMngDtlVO>  authorMngDtlVOList = authorMngDAO.selectAuthorMngDtlList(authorMngVO);
					authorMngVO.setAuthorMngDtlList(authorMngDtlVOList);
				}
			}
			
		} catch (Exception ex) {
			logger.error("searchAuthorMngList", ex);
			// 조회중 오류가 발생했습니다.
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return authorMngVOList;
	}

	@Override
	public AuthorMngVO searchAuthorMng(AuthorMngVO vo) throws Exception {

		AuthorMngVO resultVo = null;
		
		try {
			
			resultVo = authorMngDAO.selectAuthorMng(vo);
			
			if (resultVo != null) {
				List<AuthorMngDtlVO>  authorMngDtlVOList = authorMngDAO.selectAuthorMngDtlList(resultVo);
				resultVo.setAuthorMngDtlList(authorMngDtlVOList);
			}
			
		} catch (Exception ex) {
			logger.error("searchAuthorMng", ex);
			// 조회중 오류가 발생했습니다.
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return resultVo;
	}

	@Override
	public int registAuthorMng(AuthorMngVO vo) throws Exception {
		
		int resultCnt = 0;
		
		try {
			
			if (vo != null) {
				
				resultCnt = authorMngDAO.insertAuthorMng(vo);
				
				if (CmmnConstant.DEFAULT_Y.equals(vo.getAuthBasicAt())) {
					authorMngDAO.updateAuthorMngBasicYn(vo);
				}
				
				if (resultCnt > 0) {
					
					List<AuthorMngDtlVO>  authorMngDtlVOList = vo.getAuthorMngDtlList();
					
					for (AuthorMngDtlVO authorMngDtlVO : authorMngDtlVOList) {
						authorMngDAO.insertAuthorMngDtl(authorMngDtlVO);
					}
				}
			}
			
		} catch (Exception ex) {
			logger.error("registAuthorMng", ex);
			ex.printStackTrace();
			// 저장중 오류가 발생했습니다.
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.save", null));
		}
		
		return resultCnt;
	}

	@Override
	public int modifyAuthorMng(AuthorMngVO vo) throws Exception {

		int resultCnt = 0;
		
		try {
			
			if (vo != null) {
				
				resultCnt = authorMngDAO.updateAuthorMng(vo);
				
				if (resultCnt > 0) {
					
					if (CmmnConstant.DEFAULT_Y.equals(vo.getAuthBasicAt())) {
						authorMngDAO.updateAuthorMngBasicYn(vo);
					}
					
					List<AuthorMngDtlVO>  authorMngDtlVOList = vo.getAuthorMngDtlList();
					
					for (AuthorMngDtlVO authorMngDtlVO : authorMngDtlVOList) {
						authorMngDAO.updateAuthorMngDtl(authorMngDtlVO);
					}
				}
			}
			
		} catch (Exception ex) {
			logger.error("registAuthorMng", ex);
			// 저장중 오류가 발생했습니다.
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.save", null));
		}
		
		return resultCnt;
	}

	@Override
	public List<AuthorMngMenuVO> searchAuthorMngMenuList(AuthorMngVO vo) throws Exception {

		List<AuthorMngMenuVO> authorMngVOList = null;
		
		try {
			
			authorMngVOList = authorMngDAO.selectAuthorMngMenuList(vo);
			
		} catch (Exception ex) {
			logger.error("searchAuthorMngMenuList", ex);
			// 조회중 오류가 발생했습니다.
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return authorMngVOList;
	}

	@Override
	public int registAuthorMngMenu(AuthorMngMenuVO vo) throws Exception {
		
		int resultCnt = 0;
		
		try {
			
			if (vo != null) {
				
				List<AuthorMngMenuVO>  authorMngMenuVOList = vo.getAuthorMngMenuVOList();
				
				if (authorMngMenuVOList != null && authorMngMenuVOList.size() > 0) {
					
					// 체크된 메뉴는 모두 일단 Merge
					for (int i = 0 ; i < authorMngMenuVOList.size() ; i++) {
						
						AuthorMngMenuVO authorMngMenuVO = authorMngMenuVOList.get(i);
						
						// 일단 다 지우고 다시 넣음
						if (i == 0) {
							authorMngDAO.deleteAuthorMngMenu(authorMngMenuVO);
							authorMngDAO.deleteAuthorMngMenuBtn(authorMngMenuVO);
						}
						
						/*
						 * String isMenuYn = authorMngMenuVO.getAuthorMenuYn();
						 * 
						 * if (CmmnConstant.DEFAULT_Y.equals(isMenuYn)) { resultCnt +=
						 * authorMngDAO.insertAuthorMngMenu(authorMngMenuVO); resultCnt +=
						 * authorMngDAO.insertAuthorMngMenuBtn(authorMngMenuVO); }
						 */
					}
				}
			}
			
		} catch (Exception ex) {
			logger.error("registAuthorMngMenu", ex);
			// 저장중 오류가 발생했습니다.
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.save", null));
		}
		
		return resultCnt;
	}

	@Override
	public int removeAuthorMngLang(AuthorMngDtlVO vo) throws Exception {
		
		int resultCnt = 0;
		
		try {
			
			resultCnt = authorMngDAO.deleteAuthorMngLang(vo);
			
		} catch (Exception ex) {
			logger.error("removeAuthorMngLang", ex);
			// 삭제중 오류가 발생했습니다.
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.delete", null));
		}
		
		return resultCnt;
	}
	
	@Override
	public List<AuthorMngVO> searchAuthorCmmnList(AuthorMngVO vo) throws Exception {
		
		List<AuthorMngVO> authorMngVOList = null;
		
		try {
			
			authorMngVOList = authorMngDAO.selectAuthorCmmnList(vo);
			
		} catch (Exception ex) {
			logger.error("searchAuthorCmmnList", ex);
			// 조회중 오류가 발생했습니다.
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		
		return authorMngVOList;
	}
}
