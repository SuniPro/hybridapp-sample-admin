package com.hybirdapp.sample.cmmn.sys.service;

import java.util.List;

import com.hybirdapp.sample.cmmn.CmmnVO;

public class MenuMngVO extends CmmnVO {

	private String menuCd;					/*메뉴코드*/
	private String menuNm;					/*메뉴명*/
	private String menuUrl;					/*메뉴URL*/
	private String menuLvl;					/*메뉴레벨*/
	private String menuOrdr;				/*메뉴 순서*/
	private String menuNavi;				/*메뉴 네비게이션*/
	private String menuDc;					/*메뉴 설명*/
	private String menuParntsCd;			/*메뉴부모코드*/
	private String menuDsplyAt;				/*메뉴 디스플레이 여부*/
	private String moblDsplyYt;				/*모바일 디스플레이 여부*/
	private String useAt;					/*사용여부*/
	private String registSn;				/*등록코드*/
	private String registDt;				/*메뉴 순서*/
	private String updtSn;					/*등록일시*/
	private String updtDt;					/*수정코드*/
	private String isLeaf;					/*수정일시*/
	private String menuType;				/*등록 수정 구분값*/
	private String menuSe;					/*메뉴구분*/
	public String getMenuSe() {
		return menuSe;
	}
	public void setMenuSe(String menuSe) {
		this.menuSe = menuSe;
	}
	private List<MenuMngVO> menuMngList;
	
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
	public String getMenuLvl() {
		return menuLvl;
	}
	public void setMenuLvl(String menuLvl) {
		this.menuLvl = menuLvl;
	}
	public String getMenuOrdr() {
		return menuOrdr;
	}
	public void setMenuOrdr(String menuOrdr) {
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
	public String getMoblDsplyYt() {
		return moblDsplyYt;
	}
	public void setMoblDsplyYt(String moblDsplyYt) {
		this.moblDsplyYt = moblDsplyYt;
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
	public String getUpdtDt() {
		return updtDt;
	}
	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public List<MenuMngVO> getMenuMngList() {
		return menuMngList;
	}
	public void setMenuMngList(List<MenuMngVO> menuMngList) {
		this.menuMngList = menuMngList;
	}
	


}
