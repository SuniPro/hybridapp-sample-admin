package com.hybirdapp.sample.mngr.pkgPrd.service;

import java.util.List;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;

public class PkgPrdVO extends CmmnMngrVO {

	private List<PkgPrdVO> prdMngList;

	private List<PkgPrdVO> pkgMngList;

	private String pkgPrd;
	private String viewType;
	private String sngPed;

	/* 상품 그룹 */
	private String menu_grp_seq; 	// 상품그룹 ID
	
	private String menu_grp_nm; 	// 상품 그룹 명
	private String menu_grp_desc; 	// 상품 그룹 설명
	private String del_yn; 			// 삭제여부
	
	/* 단일 상품*/
	private String menu_unit_seq;  //단일상품 ID   
	private String menu_unit_nm;   //단일상품 명   
	private String mu_onoff_kb;    //상품판매 구분 
	private String mu_agent_cd;    //상품 판매처   
	private String menu_unit_desc; //단일상품 설명
	
	/* 공통 */
	private String isrt_id; 		// 등록자
	private String isrt_date; 		// 등록일자
	private String updt_id; 		// 수정자
	private String updt_date; 		// 수정일자
	
	/* 시설목록 */
	private String fclt_class; 		// 시설구분
	private String fclt_seq; 		// 시설 ID
	private String fclt_nm;			// 시설 명
	
	public String getFclt_nm() {
		return fclt_nm;
	}

	public void setFclt_nm(String fclt_nm) {
		this.fclt_nm = fclt_nm;
	}

	public List<PkgPrdVO> getPrdMngList() {
		return prdMngList;
	}

	public void setPrdMngList(List<PkgPrdVO> prdMngList) {
		this.prdMngList = prdMngList;
	}

	public List<PkgPrdVO> getPkgMngList() {
		return pkgMngList;
	}

	public void setPkgMngList(List<PkgPrdVO> pkgMngList) {
		this.pkgMngList = pkgMngList;
	}

	public String getPkgPrd() {
		return pkgPrd;
	}

	public void setPkgPrd(String pkgPrd) {
		this.pkgPrd = pkgPrd;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public String getSngPed() {
		return sngPed;
	}

	public void setSngPed(String sngPed) {
		this.sngPed = sngPed;
	}

	public String getMenu_grp_seq() {
		return menu_grp_seq;
	}

	public void setMenu_grp_seq(String menu_grp_seq) {
		this.menu_grp_seq = menu_grp_seq;
	}

	public String getFclt_class() {
		return fclt_class;
	}

	public void setFclt_class(String fclt_class) {
		this.fclt_class = fclt_class;
	}

	public String getFclt_seq() {
		return fclt_seq;
	}

	public void setFclt_seq(String fclt_seq) {
		this.fclt_seq = fclt_seq;
	}

	public String getMenu_grp_nm() {
		return menu_grp_nm;
	}

	public void setMenu_grp_nm(String menu_grp_nm) {
		this.menu_grp_nm = menu_grp_nm;
	}

	public String getMenu_grp_desc() {
		return menu_grp_desc;
	}

	public void setMenu_grp_desc(String menu_grp_desc) {
		this.menu_grp_desc = menu_grp_desc;
	}

	public String getDel_yn() {
		return del_yn;
	}

	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}

	public String getIsrt_id() {
		return isrt_id;
	}

	public void setIsrt_id(String isrt_id) {
		this.isrt_id = isrt_id;
	}

	public String getIsrt_date() {
		return isrt_date;
	}

	public void setIsrt_date(String isrt_date) {
		this.isrt_date = isrt_date;
	}

	public String getUpdt_id() {
		return updt_id;
	}

	public void setUpdt_id(String updt_id) {
		this.updt_id = updt_id;
	}

	public String getUpdt_date() {
		return updt_date;
	}

	public void setUpdt_date(String updt_date) {
		this.updt_date = updt_date;
	}

	public String getMenu_unit_seq() {
		return menu_unit_seq;
	}

	public void setMenu_unit_seq(String menu_unit_seq) {
		this.menu_unit_seq = menu_unit_seq;
	}

	public String getMenu_unit_nm() {
		return menu_unit_nm;
	}

	public void setMenu_unit_nm(String menu_unit_nm) {
		this.menu_unit_nm = menu_unit_nm;
	}

	public String getMu_onoff_kb() {
		return mu_onoff_kb;
	}

	public void setMu_onoff_kb(String mu_onoff_kb) {
		this.mu_onoff_kb = mu_onoff_kb;
	}

	public String getMu_agent_cd() {
		return mu_agent_cd;
	}

	public void setMu_agent_cd(String mu_agent_cd) {
		this.mu_agent_cd = mu_agent_cd;
	}

	public String getMenu_unit_desc() {
		return menu_unit_desc;
	}

	public void setMenu_unit_desc(String menu_unit_desc) {
		this.menu_unit_desc = menu_unit_desc;
	}



	
	
}
