package com.hybirdapp.sample.mngr.auth.service;

import java.util.List;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;

public class AuthVO extends CmmnMngrVO {
	
	private String menuCd;				// 메뉴 코드
	private String menuNm;				// 메뉴명
	private String menuUrl;				// 메뉴 URL
	private int	   menuLvl;				// 메뉴 레벨
	private int    menuOrdr;			// 메뉴 순서
	private String menuNavi;			// 메뉴 네비게이션
	private String menuDc;				// 메뉴 설명
	private String menuParntsCd;		// 메뉴 부모코드
	private String menuDsplyAt;			// 메뉴 디스플레이 여부
	private String moblDsplyAt;			// 모바일 디스플레이 여부
	private String menuSe;				// 메뉴 구분
	
	private String authCd;
	private String authNm;
	private String authDc;
	private String authBasicAt;
	private String useAt;
	private String viewType;			// 수정,등록 구분값
	private String searchTyCd;			// 검색 조건 구분값
	private String searchText;			// 검색 키워드
	private String authCnt;				// 권한 삭제 여부 확인용 cnt
	
	public String getAuthCnt() {
		return authCnt;
	}

	public void setAuthCnt(String authCnt) {
		this.authCnt = authCnt;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSearchTyCd() {
		return searchTyCd;
	}

	public void setSearchTyCd(String searchTyCd) {
		this.searchTyCd = searchTyCd;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	private List<AuthVO> AuthList;
	
	
	
	public String getMenuSe() {
		return menuSe;
	}

	public void setMenuSe(String menuSe) {
		this.menuSe = menuSe;
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

	public String getAuthCd() {
		return authCd;
	}

	public void setAuthCd(String authCd) {
		this.authCd = authCd;
	}

	public String getAuthNm() {
		return authNm;
	}

	public void setAuthNm(String authNm) {
		this.authNm = authNm;
	}

	public String getAuthDc() {
		return authDc;
	}

	public void setAuthDc(String authDc) {
		this.authDc = authDc;
	}

	public String getAuthBasicAt() {
		return authBasicAt;
	}

	public void setAuthBasicAt(String authBasicAt) {
		this.authBasicAt = authBasicAt;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public List<AuthVO> getAuthList() {
		return AuthList;
	}

	public void setAuthList(List<AuthVO> authList) {
		AuthList = authList;
	}
	
}
