package com.hybirdapp.sample.mngr.menu.service;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;

import java.util.List;

public class MngrMenuVO extends CmmnMngrVO {
	
	/*팝업 테스트*/

	private String popupTargetId;  		//팝업에서 타겟명
	private String popupCallbackFnNm;   //팝업에서 호출될 펑션명

	private String menuType;			// 메뉴 등록 및 추가(I), 상세 및 수정(U)
	
	private String menuCd;				// 메뉴 코드
	private String menuNm;				// 메뉴명
	private String menuUrl;				// 메뉴 URL
	private int menuLvl;				// 메뉴 레벨
	private int menuOrdr;				// 메뉴 순서
	private String menuNavi;			// 메뉴 네비게이션
	private String menuDc;				// 메뉴 설명
	private String menuParntsCd;		// 메뉴 부모코드
	private String menuSe;				// 권한
	private String menuDsplyAt;			// 메뉴 디스플레이 여부
	private String moblDsplyAt;			// 모바일 디스플레이 여부
	private String useAt;				// 사용 여부
	private String registSn;			// 등록 코드
	private String registDt;			// 등록 일시
	private String updtSn;				// 수정 코드
	private String updtDate;			// 수정 일시
	
	private List<MngrMenuVO> mngrMenuList;
	
	private String searchTotal;			// 검색 구분 - 전체
	private String searchMenuCd;		// 검색 구분 - 코드
	private String searchMenuNm;		// 검색 구분 - 이름
	private String searchText;			// 검색 text
	
	/* 엑셀 추가 시작 */
	private String excelSn;
	private String excelNm;
	private String excelMbtlnum;
	private String excelCn;
	private String excelType;
	private String excelNum;
	/* 엑셀 추가 끝 */

	

	public String getPopupCallbackFnNm() {
		return popupCallbackFnNm;
	}

	public String getPopupTargetId() {
		return popupTargetId;
	}

	public void setPopupTargetId(String popupTargetId) {
		this.popupTargetId = popupTargetId;
	}

	public void setPopupCallbackFnNm(String popupCallbackFnNm) {
		this.popupCallbackFnNm = popupCallbackFnNm;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuCd() {
		return menuCd;
	}

	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}

	public String getMenuNm() {
		return menuNm;
	}

	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public int getMenuLvl() {
		return menuLvl;
	}

	public void setMenuLvl(int menuLvl) {
		this.menuLvl = menuLvl;
	}

	public int getMenuOrdr() {
		return menuOrdr;
	}

	public void setMenuOrdr(int menuOrdr) {
		this.menuOrdr = menuOrdr;
	}

	public String getMenuNavi() {
		return menuNavi;
	}

	public void setMenuNavi(String menuNavi) {
		this.menuNavi = menuNavi;
	}

	public String getMenuDc() {
		return menuDc;
	}

	public void setMenuDc(String menuDc) {
		this.menuDc = menuDc;
	}

	public String getMenuParntsCd() {
		return menuParntsCd;
	}

	public void setMenuParntsCd(String menuParntsCd) {
		this.menuParntsCd = menuParntsCd;
	}
	
	public String getMenuSe() {
		return menuSe;
	}

	public void setMenuSe(String menuSe) {
		this.menuSe = menuSe;
	}

	public String getMenuDsplyAt() {
		return menuDsplyAt;
	}

	public void setMenuDsplyAt(String menuDsplyAt) {
		this.menuDsplyAt = menuDsplyAt;
	}

	public String getMoblDsplyAt() {
		return moblDsplyAt;
	}

	public void setMoblDsplyAt(String moblDsplyAt) {
		this.moblDsplyAt = moblDsplyAt;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public String getRegistSn() {
		return registSn;
	}

	public void setRegistSn(String registSn) {
		this.registSn = registSn;
	}

	public String getRegistDt() {
		return registDt;
	}

	public void setRegistDt(String registDt) {
		this.registDt = registDt;
	}

	public String getUpdtSn() {
		return updtSn;
	}

	public void setUpdtSn(String updtSn) {
		this.updtSn = updtSn;
	}

	public String getUpdtDate() {
		return updtDate;
	}

	public void setUpdtDate(String updtDate) {
		this.updtDate = updtDate;
	}

	public List<MngrMenuVO> getMngrMenuList() {
		return mngrMenuList;
	}

	public void setMngrMenuList(List<MngrMenuVO> mngrMenuList) {
		this.mngrMenuList = mngrMenuList;
	}
	
	public String getSearchTotal() {
		return searchTotal;
	}

	public void setSearchTotal(String searchTotal) {
		this.searchTotal = searchTotal;
	}

	public String getSearchMenuCd() {
		return searchMenuCd;
	}

	public void setSearchMenuCd(String searchMenuCd) {
		this.searchMenuCd = searchMenuCd;
	}

	public String getSearchMenuNm() {
		return searchMenuNm;
	}

	public void setSearchMenuNm(String searchMenuNm) {
		this.searchMenuNm = searchMenuNm;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getExcelSn() {
		return excelSn;
	}

	public void setExcelSn(String excelSn) {
		this.excelSn = excelSn;
	}

	public String getExcelNm() {
		return excelNm;
	}

	public void setExcelNm(String excelNm) {
		this.excelNm = excelNm;
	}

	public String getExcelMbtlnum() {
		return excelMbtlnum;
	}

	public void setExcelMbtlnum(String excelMbtlnum) {
		this.excelMbtlnum = excelMbtlnum;
	}

	public String getExcelCn() {
		return excelCn;
	}

	public void setExcelCn(String excelCn) {
		this.excelCn = excelCn;
	}

	public String getExcelType() {
		return excelType;
	}

	public void setExcelType(String excelType) {
		this.excelType = excelType;
	}

	public String getExcelNum() {
		return excelNum;
	}

	public void setExcelNum(String excelNum) {
		this.excelNum = excelNum;
	}	
}
