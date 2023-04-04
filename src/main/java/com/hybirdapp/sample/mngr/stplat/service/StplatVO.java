package com.hybirdapp.sample.mngr.stplat.service;

import java.util.List;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;

public class StplatVO extends CmmnMngrVO {

	private String type;				// 등록, 상세 및 수정 구분
	
	private String searchTotal;			// 검색 구분 - 전체
	private String searchStplatSj;		// 검색 구분 - 제목
	private String searchStplatCn;		// 검색 구분 - 내용
	private String searchText;			// 검색 text
	private String searchStp;			// stp
	private int	   stplatVer;			// 버전
	private String stplatSe;			// 구분
	private String clNm;				// 구분별 이름
	private String stplatSj;			// 제목
	private String stplatCn;			// 내용
	private int    stplatOrdr;			// 순서
	private String essntlAt;			// 필수 여부
	private String useAt;				// 사용 여부
	private String mngrNm;				// 작성자 이름
	private String mngrId;				// 작성자 아이디
	
	private List<StplatVO> stplatList;	// 목록

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getSearchTotal() {
		return searchTotal;
	}
	public void setSearchTotal(String searchTotal) {
		this.searchTotal = searchTotal;
	}

	public String getSearchStplatSj() {
		return searchStplatSj;
	}
	public void setSearchStplatSj(String searchStplatSj) {
		this.searchStplatSj = searchStplatSj;
	}

	public String getSearchStplatCn() {
		return searchStplatCn;
	}
	public void setSearchStplatCn(String searchStplatCn) {
		this.searchStplatCn = searchStplatCn;
	}

	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSearchStp() {
		return searchStp;
	}
	public void setSearchStp(String searchStp) {
		this.searchStp = searchStp;
	}
	
	public int getStplatVer() {
		return stplatVer;
	}
	public void setStplatVer(int stplatVer) {
		this.stplatVer = stplatVer;
	}

	public String getStplatSe() {
		return stplatSe;
	}
	public void setStplatSe(String stplatSe) {
		this.stplatSe = stplatSe;
	}
	
	public String getClNm() {
		return clNm;
	}
	public void setClNm(String clNm) {
		this.clNm = clNm;
	}

	public String getStplatSj() {
		return stplatSj;
	}
	public void setStplatSj(String stplatSj) {
		this.stplatSj = stplatSj;
	}

	public String getStplatCn() {
		return stplatCn;
	}
	public void setStplatCn(String stplatCn) {
		this.stplatCn = stplatCn;
	}

	public int getStplatOrdr() {
		return stplatOrdr;
	}
	public void setStplatOrdr(int stplatOrdr) {
		this.stplatOrdr = stplatOrdr;
	}

	public String getEssntlAt() {
		return essntlAt;
	}
	public void setEssntlAt(String essntlAt) {
		this.essntlAt = essntlAt;
	}

	public String getUseAt() {
		return useAt;
	}
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public String getMngrId() {
		return mngrId;
	}
	public void setMngrId(String mngrId) {
		this.mngrId = mngrId;
	}
	public String getMngrNm() {
		return mngrNm;
	}
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}

	public List<StplatVO> getStplatList() {
		return stplatList;
	}
	public void setStplatList(List<StplatVO> stplatList) {
		this.stplatList = stplatList;
	}
	
}
