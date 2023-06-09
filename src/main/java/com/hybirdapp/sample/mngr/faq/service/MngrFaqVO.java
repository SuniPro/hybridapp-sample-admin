package com.hybirdapp.sample.mngr.faq.service;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;

public class MngrFaqVO extends CmmnMngrVO {

	private String searchTotal;			// 전체 검색
	private String searchFaqSj;			// 제목 검색
	private String searchFaqCn;			// 내용 검색
	private String searchFaqSe;			// faq구분
	
	private String faqSn;				//faq 일련번호
	private String faqSj;				//faq 제목
	private String faqCn;				//faq 내용
	private String faqSe;				//faq 구분
	private String faqSeNm;				//faq 구분명
	private String useAt;				//사용여부
	private String searchText;			//검색
	private String searchType;			//검색
	private String searchFromDt;		// 가입일자 from
	private String searchToDt;			// 가입일자 to	
	private String isrtNm;				//등록자명
	private String updtNm;				//수정자 명
	
	public String getSearchTotal() {
		return searchTotal;
	}
	public void setSearchTotal(String searchTotal) {
		this.searchTotal = searchTotal;
	}
	public String getSearchFaqSj() {
		return searchFaqSj;
	}
	public void setSearchFaqSj(String searchFaqSj) {
		this.searchFaqSj = searchFaqSj;
	}
	public String getSearchFaqCn() {
		return searchFaqCn;
	}
	public void setSearchFaqCn(String searchFaqCn) {
		this.searchFaqCn = searchFaqCn;
	}
	public String getSearchFaqSe() {
		return searchFaqSe;
	}
	public void setSearchFaqSe(String searchFaqSe) {
		this.searchFaqSe = searchFaqSe;
	}
	public String getFaqSn() {
		return faqSn;
	}
	public void setFaqSn(String faqSn) {
		this.faqSn = faqSn;
	}
	public String getFaqSj() {
		return faqSj;
	}
	public void setFaqSj(String faqSj) {
		this.faqSj = faqSj;
	}
	public String getFaqCn() {
		return faqCn;
	}
	public void setFaqCn(String faqCn) {
		this.faqCn = faqCn;
	}
	public String getFaqSe() {
		return faqSe;
	}
	public void setFaqSe(String faqSe) {
		this.faqSe = faqSe;
	}
	public String getUseAt() {
		return useAt;
	}
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getSearchFromDt() {
		return searchFromDt;
	}
	public void setSearchFromDt(String searchFromDt) {
		this.searchFromDt = searchFromDt;
	}
	public String getSearchToDt() {
		return searchToDt;
	}
	public void setSearchToDt(String searchToDt) {
		this.searchToDt = searchToDt;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getFaqSeNm() {
		return faqSeNm;
	}
	public void setFaqSeNm(String faqSeNm) {
		this.faqSeNm = faqSeNm;
	}
	public String getIsrtNm() {
		return isrtNm;
	}
	public void setIsrtNm(String isrtNm) {
		this.isrtNm = isrtNm;
	}
	public String getUpdtNm() {
		return updtNm;
	}
	public void setUpdtNm(String updtNm) {
		this.updtNm = updtNm;
	}

	
	
}