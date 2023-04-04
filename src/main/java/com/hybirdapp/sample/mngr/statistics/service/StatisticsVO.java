package com.hybirdapp.sample.mngr.statistics.service;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;

public class StatisticsVO extends CmmnMngrVO {

	private String statDay;					// 일별
	private String statMonth;				// 월별
	private String statYear;				// 년도별
	private String appMber;					// app 일반 회원
	private String appMberRed;				// app 레드 회원
	private String appMberGre;				// app 그린 회원
	private String appMberSecsn;			// app 탈퇴 회원
	private String appTotal;				// app 총 회원
	private String webMber;					// web 일반 회원
	private String webMberRed;				// web 레드 회원
	private String webMberGre;				// web 그린 회원
	private String webMberSecsn;			// web 탈퇴 회원
	private String webTotal;				// web 탈퇴 회원
	private String grandTotal;				// 총 회원
	
	private String searchType;				// 구분
	private String searchFromDt;			// 조회일자 from
	private String searchToDt;				// 조회일자 to	
	private String searchMonth;				// 월	
	private String searchDay;				// 일	
	private String searchYear;				// 연도	
	private String searchYearFromDt;		// 연도조회일자 from
	private String searchYearToDt;			// 연도조회일자 to	
	private String diffDayDt;				// 일차이 일짜
	
	
	public String getStatDay() {
		return statDay;
	}
	public void setStatDay(String statDay) {
		this.statDay = statDay;
	}
	public String getStatMonth() {
		return statMonth;
	}
	public void setStatMonth(String statMonth) {
		this.statMonth = statMonth;
	}
	public String getStatYear() {
		return statYear;
	}
	public void setStatYear(String statYear) {
		this.statYear = statYear;
	}
	public String getAppMber() {
		return appMber;
	}
	public void setAppMber(String appMber) {
		this.appMber = appMber;
	}
	public String getAppMberRed() {
		return appMberRed;
	}
	public void setAppMberRed(String appMberRed) {
		this.appMberRed = appMberRed;
	}
	public String getAppMberGre() {
		return appMberGre;
	}
	public void setAppMberGre(String appMberGre) {
		this.appMberGre = appMberGre;
	}
	public String getAppMberSecsn() {
		return appMberSecsn;
	}
	public void setAppMberSecsn(String appMberSecsn) {
		this.appMberSecsn = appMberSecsn;
	}
	public String getAppTotal() {
		return appTotal;
	}
	public void setAppTotal(String appTotal) {
		this.appTotal = appTotal;
	}
	public String getWebMber() {
		return webMber;
	}
	public void setWebMber(String webMber) {
		this.webMber = webMber;
	}
	public String getWebMberRed() {
		return webMberRed;
	}
	public void setWebMberRed(String webMberRed) {
		this.webMberRed = webMberRed;
	}
	public String getWebMberGre() {
		return webMberGre;
	}
	public void setWebMberGre(String webMberGre) {
		this.webMberGre = webMberGre;
	}
	public String getWebMberSecsn() {
		return webMberSecsn;
	}
	public void setWebMberSecsn(String webMberSecsn) {
		this.webMberSecsn = webMberSecsn;
	}
	public String getWebTotal() {
		return webTotal;
	}
	public void setWebTotal(String webTotal) {
		this.webTotal = webTotal;
	}
	public String getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
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
	public String getSearchMonth() {
		return searchMonth;
	}
	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
	}
	public String getSearchYearFromDt() {
		return searchYearFromDt;
	}
	public void setSearchYearFromDt(String searchYearFromDt) {
		this.searchYearFromDt = searchYearFromDt;
	}
	public String getSearchYearToDt() {
		return searchYearToDt;
	}
	public void setSearchYearToDt(String searchYearToDt) {
		this.searchYearToDt = searchYearToDt;
	}
	public String getSearchDay() {
		return searchDay;
	}
	public void setSearchDay(String searchDay) {
		this.searchDay = searchDay;
	}
	public String getSearchYear() {
		return searchYear;
	}
	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}
	public String getDiffDayDt() {
		return diffDayDt;
	}
	public void setDiffDayDt(String diffDayDt) {
		this.diffDayDt = diffDayDt;
	}
	
}