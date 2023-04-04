package com.hybirdapp.sample.mngr.tckPkg.service.impl;

import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.cmmn.script.service.ScriptService;
import com.hybirdapp.sample.mngr.tckPkg.service.TckPkgService;
import com.hybirdapp.sample.mngr.tckSng.service.SertbPmenuVo;
import com.hybirdapp.sample.mngr.tckSng.service.TckSngService;
import com.hybirdapp.sample.mngr.tckSng.service.impl.TckSngDAO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service("TckPkgService")
public class TckPkgServiceImpl implements TckPkgService {

	/**
	 * Logger available to subclasses
	 */
	protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "TckPkgDAO")
	private TckPkgDAO tckPkgDAO;

	@Resource(name = "TckSngDAO")
	private TckSngDAO tckSngDAO;

	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;

	@Resource(name = "ScriptService")
	private ScriptService scriptService;

	@Resource(name = "TckSngService")
	private TckSngService tckSngService;


	// 패키지 상품 티켓 조회 건수
	@Override
	public int searchTckPkgListCnt(DataClass params) throws Exception {
		int result = 0;
		try {
			result = tckPkgDAO.searchTckPkgListCnt(params.getMap());
		}
		catch (Exception e) {
			logger.error("searchTckPkgListCnt", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 패키지 상품명 조회
	@Override
	public List searchMenuPkgNmList() throws Exception {
		List result = null;

		try {
			result = tckPkgDAO.searchMenuPkgNmList();
		}
		catch (Exception e) {
			logger.error("searchMenuPkgNmList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}

	// 패키지 상품 티켓 조회 목록
	@Override
	public List searchTckPkgList(DataClass params) throws Exception {
		List result = null;

		try {
			result = tckPkgDAO.searchTckPkgList(params.getMap());
		}
		catch (Exception e) {
			logger.error("searchTckPkgList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}

	// 패키지 상품 티켓 단일 정보 조회
	@Override
	public HashMap searchTckPkg(DataClass params) throws Exception {
		HashMap result = new HashMap();
		try {
			result = tckPkgDAO.searchTckPkg(params.getMap());
		}
		catch (Exception e) {
			logger.error("searchTckPkg", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 패키지티켓분류 조회
	@Override
	public List<HashMap> getPkgTckGrp() throws Exception {
		List<HashMap> result;
		try {
			result = tckPkgDAO.getPkgTckGrp();
		}
		catch (Exception e) {
			logger.error("getPkgTckGrp", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 패키지 상품 티켓 정보 등록
	@Override
	public int registTckPkg(DataClass params) throws Exception {
		int result = 0;
		try {
			result = tckPkgDAO.registTckPkg(params.getMap());

			/* 첨부파일 - 로직확인 필요(Notice) */
//			if (listMap != null && listMap.size() > 0) {
//				for (Map<String, Object> map : listMap) {
//					AttachVO attachVO = new AttachVO();
//					attachVO.setBoardSe("NOTICE");
//					attachVO.setBoardSn(Integer.parseInt(vo.getNoticeSn()));
//					attachVO.setOrgnFileNm((String)map.get("orgnFileNm"));
//					attachVO.setNewFileNm((String)map.get("newFileNm"));
//					attachVO.setSavePath((String)map.get("savePath"));
//					attachVO.setExpsrOrdr((int)map.get("expsrOrdr"));
//					attachVO.setUrl((String)map.get("url"));
//					attachVO.setIsrtIp(vo.getIsrtIp());
//					attachVO.setIsrtId(vo.getIsrtId());
//					scriptService.attachFileSave(attachVO);
//				}
//			}
			/* 첨부파일 END */
		}
		catch (Exception e) {
			logger.error("registTckPkg", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 패키지 상품 티켓 정보 수정
	@Override
	public int modifyTckPkg(DataClass params) throws Exception {
		int result = 0;
		try {
			result = tckPkgDAO.modifyTckPkg(params.getMap());
		}
		catch (Exception e) {
			logger.error("modifyTckPkg", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 패키지 상품 티켓 정보 삭제
	@Override
	public int deleteTckPkg(DataClass params) throws Exception {
		int result = 0;
		try {
			result = tckPkgDAO.deleteTckPkg(params.getMap());
		}
		catch (Exception e) {
			logger.error("deleteTckPkg", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	@Override
	public HashMap searchPkgTicket(DataClass params) throws Exception {
		HashMap result;
		try {
			result = tckPkgDAO.searchPkgTicket(params.getMap());
		}
		catch (Exception e) {
			logger.error("searchPkgTicket", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 패키지상품티켓관리 > 패키지상품구성정보 Upsert
	@Override
	@Transactional(transactionManager = "txManager")
	public void mergeMenuPkgTicket(DataClass params) throws Exception {

		String viewType = params.get("viewType");
		SertbPmenuVo[] vo = tckSngService.makeUnitTicketData(params);
		for (int i = 1; i < vo.length; i++) {
			System.out.println(vo[i].toString());
		}
		String[] m_type = {"N", "A", "G", "R"};

		try {
			String mpTicketSeq = null;
			if (viewType.equals("I")) { // 신규 입력 시
				mpTicketSeq = String.valueOf(this.tckPkgDAO.getMpTicketSeq());
				vo[1].setMp_ticket_seq(mpTicketSeq);
				this.tckPkgDAO.mergeMenuPkgTicket(vo[1]);
			}

			// Data Setting
			for (int i = 1; i < vo.length; i++) {
				vo[i].setFclt_list(vo[1].getFclt_list()); // PKG의 M_LOC은 첫 설정된 단일상품티켓의 m-
				vo[i].setM_class("O2");
				vo[i].setM_type("M");
				vo[i].setMu_ticket_seq(vo[i].getUnitProdTicket());
				if (viewType.equals("I")) {
					vo[i].setMp_ticket_seq(mpTicketSeq);
				}
				this.tckPkgDAO.mergeMenuPkgTicketDtl(vo[i]);
			}

			SertbPmenuVo cpVo = (SertbPmenuVo) vo[1].clone();
			String mMenuCode = null;
			for (int i = 0; i < m_type.length; i++) {
				mMenuCode = (i == 1) ? mMenuCode : String.valueOf(this.tckSngDAO.makeMMenuCode());
				cpVo.setM_menu(mMenuCode);
				cpVo.setTicket_price_type(m_type[i]);
				this.tckPkgDAO.mergeMenuPkgTicketPrice(cpVo); // N, A --> M_MENU 공유
				if (i == 1) continue;
				this.tckSngDAO.mergePMenu(cpVo); // A -> N 으로 입력
			}
		}
		catch (Exception e) {
			logger.error("mergeMenuPkgTicket", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
	}

	// 패키지상품티켓관리 > 패키지상품구성 티켓정보  Upsert
	@Override
	@Transactional(transactionManager = "txManager")
	public void mergeMenuPkgUnitTicket(DataClass params) throws Exception {

		String viewType = params.get("viewType");
		SertbPmenuVo[] vo = tckSngService.makeUnitTicketData(params);
		 for (int i = 1; i < vo.length; i++) {
			System.out.println(vo[i].toString());
		}
		try {
			for (int i = 1; i < vo.length; i++) {
				if (StringUtils.hasText(vo[i].getM_menu())) {
					// sertb_menu_pkg_ticket_price
					this.tckPkgDAO.mergeMenuPkgTicketPrice(vo[i]);
					// p_menu
					this.tckPkgDAO.updatePkgPMenu(vo[i]);
				}
				// 신규 추가 티켓
				else {
					String mMenuCode = String.valueOf(this.tckSngDAO.makeMMenuCode());
					vo[i].setM_menu(mMenuCode);
					this.tckPkgDAO.mergeMenuPkgTicketPrice(vo[i]);
					this.tckPkgDAO.insertPkgPMenu(vo[i]);
				}
			}
			// generate p_pkgmenu
			List<HashMap> list = this.tckPkgDAO.getPkgMenuInfo(params.getMap()); // mp_ticket_seq 사용
			list.forEach(o -> {
				try {
					this.tckPkgDAO.insertPkgMenu(o);
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			});
			// SERTB_MENU_PKG_TICKET use_yn - Y 처리
			this.tckPkgDAO.updatePkgTicketUse(vo[1].getMp_ticket_seq());
		}
		catch (Exception e) {
			logger.error("mergeMenuPkgUnitTicket", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
	}

	@Override
	public List<HashMap> getPkgUnitTickets(String seq) throws Exception {
		return this.tckPkgDAO.getPkgUnitTickets(seq);
	}

	@Override
	public void updatePkgTicket(DataClass params) throws Exception {
		this.tckPkgDAO.updatePkgTicket(params.getMap());
	}

	@Override
	public HashMap searchTckPkg4Np(DataClass params) throws Exception {
		return this.tckPkgDAO.searchTckPkg4Np(params.getMap());
	}

	@Override
	public List<HashMap> searchPkgTicketPopup(DataClass params) throws Exception {
		return this.tckPkgDAO.searchPkgTicketPopup(params.getMap());
	}

	@Override
	public void removePkg(DataClass params) throws Exception {
		this.tckPkgDAO.removePkg(params.getMap());
	}

	@Override
	public void removePkgTicket(DataClass params) throws Exception {
		this.tckPkgDAO.removePkgTicket(params.getMap());
	}

	@Override
	public List<HashMap> getPkgUnitTicketsPriceSum(DataClass params) throws Exception {
		return this.tckPkgDAO.getPkgUnitTicketsPriceSum(params.getMap());
	}

	@Override
	public void makePpkgmenu(DataClass params) throws Exception {
		List<HashMap> list = this.tckPkgDAO.getPkgMenuInfo(params.getMap());

		for (HashMap map : list) {
			this.tckPkgDAO.insertPkgMenu(map);
		}
	}
}
