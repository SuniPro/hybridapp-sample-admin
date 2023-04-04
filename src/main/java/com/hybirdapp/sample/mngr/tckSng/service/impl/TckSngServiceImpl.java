package com.hybirdapp.sample.mngr.tckSng.service.impl;

import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.cmmn.ex.CmmnUserException;
import com.hybirdapp.sample.cmmn.fileUtil.service.CmmnFileSaveService;
import com.hybirdapp.sample.cmmn.msg.MessageManager;
import com.hybirdapp.sample.cmmn.script.service.ScriptService;
import com.hybirdapp.sample.mngr.tckSng.service.SertbPmenuVo;
import com.hybirdapp.sample.mngr.tckSng.service.TckSngService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service("TckSngService")
public class TckSngServiceImpl implements TckSngService {

	/**
	 * Logger available to subclasses
	 */
	protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "TckSngDAO")
	private TckSngDAO tckSngDAO;

	@Resource(name = "CmmnFileSaveService")
	private CmmnFileSaveService cmmnFileSaveService;

	@Resource(name = "ScriptService")
	private ScriptService scriptService;

	// 단일 상품 목록 건수
	public int searchTckSngListCnt(DataClass params) throws Exception {
		int result = 0;
		try {
			result = tckSngDAO.searchTckSngListCnt(params.getMap());
		}
		catch (Exception e) {
			logger.error("searchTckSngListCnt", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 단일 상품명 조회
	@Override
	public List selectSngPrdList(DataClass params) throws Exception {
		List result = null;

		try {
			result = tckSngDAO.selectSngPrdList(params.getMap());
		}
		catch (Exception e) {
			logger.error("selectSngPrdList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}

	// 단일 상품 목록 조회
	public List searchTckSngList(DataClass params) throws Exception {
		List result = null;

		try {
			result = tckSngDAO.searchTckSngList(params.getMap());
		}
		catch (Exception e) {
			logger.error("searchTckSngList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	@Override
	public List selectMenuUnitList(DataClass params) throws Exception {
		List result = null;

		try {
			result = tckSngDAO.selectMenuUnitList(params.getMap());
		}
		catch (Exception e) {
			logger.error("selectMenuUnitList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}

	@Override
	public List<HashMap> selectUnitProdTicket(DataClass params) throws Exception {
		List<HashMap> result = null;

		try {
			result = tckSngDAO.selectUnitProdTicket(params.getMap());
		}
		catch (Exception e) {
			logger.error("selectUnitProdTicket", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}

		return result;
	}

	// 티켓 분류 목록 조회
	public List selectTckGrpList(DataClass params) throws Exception {
		List result = null;

		try {
			result = tckSngDAO.selectTckGrpList(params.getMap());
		}
		catch (Exception e) {
			logger.error("searchTckSngList", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 단일 상품 단건 조회
	public List<HashMap> searchTckSng(DataClass params) throws Exception {
		List<HashMap> result;
		try {
			result = tckSngDAO.searchTckSng(params.getMap());
		}
		catch (Exception e) {
			logger.error("searchTckSng", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 단일 상품 단건 조회 NP 용
	public HashMap searchTckSng4Np(DataClass params) throws Exception {
		HashMap result;

		try {
			result = tckSngDAO.searchTckSng4Np(params.getMap());
		}
		catch (Exception e) {
			logger.error("searchTckSng", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.select", null));
		}
		return result;
	}

	// 단일 상품 등록
	public int registTckSng(DataClass params) throws Exception {
		int result = 0;
		try {
			result = tckSngDAO.registTckSng(params.getMap());
		}
		catch (Exception e) {
			logger.error("registTckSng", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.insert", null));
		}
		return result;
	}

	// 단일 상품 수정
	public int modifyTckSng(DataClass params) throws Exception {
		int result = 0;

		try {
			result = tckSngDAO.modifyTckSng(params.getMap());
		}
		catch (Exception e) {
			logger.error("modifyTckSng", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.update", null));
		}

		return result;
	}

	// 단일 상품 삭제
	public int deleteTckSng(DataClass params) throws Exception {
		int result = 0;

		try {
			result = tckSngDAO.deleteTckSng(params.getMap());
		}
		catch (Exception e) {
			logger.error("deleteTckSng", e);
			throw new CmmnUserException(MessageManager.getIGMessage("server.fail.delete", null));
		}
		return result;
	}

	@Override
	@Transactional(transactionManager = "txManager")
	public void saveTckSng(DataClass params) throws Exception {

		int cnt = Integer.parseInt(params.get("total_el_cnt"));
		String viewType = params.get("viewType");
		SertbPmenuVo[] arr = this.makeUnitTicketData(params);

		for (int i = 1; i <= cnt; i++) {
			System.out.println(arr[i].toString());
		}

		int[] chk = new int[]{0}; // check array
		String muTicketSeq = null;

		String mLoc = this.tckSngDAO.getMLoc(arr[1].getFclt_list());

		if (viewType.equals("I")) { // 신규 입력 시
			muTicketSeq = String.valueOf(this.tckSngDAO.getMuTicketSeq());
		}
		for (int i = 1; i <= cnt; i++) {
			// 회원타입
			String ticket_price_type = arr[i].getTicket_price_type();
			String mAbbr = arr[i].getM_abbr();
			String[] mAbbrArr = mAbbr.split("_");
			arr[i].setM_abbr_1(mAbbrArr[0]);
			arr[i].setM_abbr_2(mAbbrArr[1]);
			arr[i].setMLoc(mLoc);
			arr[i].setGr_loc(mLoc);
			arr[i].setM_loc(mLoc);
			arr[i].setGrLoc(mLoc);

			if (viewType.equals("I")) {  // 신규 입력 시
				arr[i].setMu_ticket_seq(muTicketSeq);
				String mMenuCode = null;
				if (!arr[i].getAgent_cd().equals("3")) { // APP이 아니면
					mMenuCode = (ticket_price_type.equals("N")) ? "10000" : String.valueOf(this.tckSngDAO.makeMMenuCode6(mLoc)); // 인터넷판매대행 / POS 용 코드
				}
				else {
					mMenuCode = (ticket_price_type.equals("N")) ? "10000" : String.valueOf(this.tckSngDAO.makeMMenuCode()); // SER 코드
				}
				arr[i].setM_menu(mMenuCode);
			}
			else {
				if (arr[i].getM_menu() == null) { // U 시 신규로 추가되는 티켓
					String mMenuCode = String.valueOf(this.tckSngDAO.makeMMenuCode());
					arr[i].setM_menu(mMenuCode);
				}
				// 데이터 보정
				arr[i].setFclt_list(arr[i].getFclt_seq());
			}

			if (ticket_price_type.equals("N")) { // 정상가 - Sertb에서만 사용

				// SERTB_MENU_UNIT_TICKET
				if (chk[0] == 0) {
					this.tckSngDAO.mergeMenuUnitTicket(arr[i]); // 1번만
					chk[0]++;
				}

				// SERTB_MENU_UNIT_TICKET_PRICE
				this.tckSngDAO.mergeMenuUnitTicketPrice(arr[i]);
			}
			else {
				// SERTB_MENU_UNIT_TICKET
				this.tckSngDAO.mergeMenuUnitTicket(arr[i]);

				// SERTB_MENU_UNIT_TICKET_PRICE
				this.tckSngDAO.mergeMenuUnitTicketPrice(arr[i]);

				if (ticket_price_type.equals("A")) { // App 할인가
					arr[i].setTicket_price_type("N"); // 일반회원가(N)로 변경
				}

				if (arr[i].getUseAt().equals("A")) { // 주중 + 주말 = A
					arr[i].setUseAt(" "); // " "으로 입력
				}
				this.tckSngDAO.mergePMenu(arr[i]);  // 1번만
			}
		}
	}

	@Override
	public SertbPmenuVo[] makeUnitTicketData(DataClass params) throws Exception {

		int cnt = Integer.parseInt(params.get("total_el_cnt"));

		SertbPmenuVo[] arr = new SertbPmenuVo[cnt + 1];
		for (int i = 1; i <= cnt; i++) arr[i] = new SertbPmenuVo();

		HashMap map = params.getMap();

		map.forEach((key, val) -> {
			String k = String.valueOf(key);
			String v_str = String.valueOf(val);
			if (k.contains("el__")) {
				String[] str_arr = k.split("__");
				String f_name = str_arr[1];
				int idx = Integer.parseInt(str_arr[2]);
				try {
					Field field = SertbPmenuVo.class.getField(f_name);
					field.set(arr[idx], v_str);
				}
				catch (Exception ignored) {
				}
			}
			else {
				for (int i = 1; i <= cnt; i++) {
					try {
						Field field = SertbPmenuVo.class.getField(k);
						field.set(arr[i], v_str);

						// 공급가액
						arr[i].setM_price(String.valueOf(Integer.parseInt(arr[i].getM_amount()) - Integer.parseInt(arr[i].getM_tax_amount())));
						String m_type = arr[i].getM_type();
						if (!m_type.equals("M")) { // 단일상품이 아니면
							arr[i].setM_class("O2"); // 패키지
							arr[i].setM_type("A"); // 패키지 구성
							arr[i].setM_price("0"); // 패키지 구성
						}
					}
					catch (Exception ignored) {
					}
				}
			}
		});
		return arr;
	}

	@Override
	public void delTicket(DataClass params) throws Exception {
		this.tckSngDAO.delTicket(params.get("m_menu"));
	}
}