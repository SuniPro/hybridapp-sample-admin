package com.hybirdapp.sample.mngr.tckStats.service;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;

public class TckStatsVO extends CmmnMngrVO {

	
	private String searchType;				// 구분
	private String searchFromDt;			// 조회일자 from
	private String searchToDt;				// 조회일자 to	
	private String searchMonth;				// 월	
	private String searchDay;				// 일	
	private String searchYear;				// 연도	
	private String searchYearFromDt;		// 연도조회일자 from
	private String searchYearToDt;			// 연도조회일자 to	
	private String diffDayDt;				// 일차이 일짜
	private String searchDate;				// 구분
	
	
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
	public String getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}
	
}