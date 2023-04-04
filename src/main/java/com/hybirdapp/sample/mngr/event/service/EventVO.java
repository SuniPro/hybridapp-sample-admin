package com.hybirdapp.sample.mngr.event.service;

import java.util.List;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;

public class EventVO extends CmmnMngrVO {

	private String evntSn;				//이벤트 일련번호
	private String evntSj;				//이벤트 제목
	private String expsrSe;				//이벤트 구분
	private String expsrAt;				//사용여부
	private String expsrBgnde;			//이벤트 시작일
	private String expsrEndde;			//이벤트 끝일
	private String expsrCn;				//이벤트 내용
	private String mainPopAt;			//메인 팝업
	private String viewType;			//구분
	private String prePageParam;		//페이지구분
	private String imageType;			//이미지 수정
	private String isrtNm;				//등록자명
	private String updtNm;				//수정자 명
	private String postingAt;			//기간 종료
	
	private String searchText;			//검색
	private String searchType;			//검색
	private String searchFromDt;		// 가입일자 from
	private String searchToDt;			// 가입일자 to	
	private String searchSeType;		//공지구분
	private List<EventVO> eventList;		// 공지사항 list

	public String getEvntSn() {
		return evntSn;
	}

	public void setEvntSn(String evntSn) {
		this.evntSn = evntSn;
	}

	public String getEvntSj() {
		return evntSj;
	}

	public void setEvntSj(String evntSj) {
		this.evntSj = evntSj;
	}

	public String getExpsrSe() {
		return expsrSe;
	}

	public void setExpsrSe(String expsrSe) {
		this.expsrSe = expsrSe;
	}

	public String getExpsrAt() {
		return expsrAt;
	}

	public void setExpsrAt(String expsrAt) {
		this.expsrAt = expsrAt;
	}

	public String getExpsrBgnde() {
		return expsrBgnde;
	}

	public void setExpsrBgnde(String expsrBgnde) {
		this.expsrBgnde = expsrBgnde;
	}

	public String getExpsrEndde() {
		return expsrEndde;
	}

	public void setExpsrEndde(String expsrEndde) {
		this.expsrEndde = expsrEndde;
	}

	public String getExpsrCn() {
		return expsrCn;
	}

	public void setExpsrCn(String expsrCn) {
		this.expsrCn = expsrCn;
	}

	public List<EventVO> getEventList() {
		return eventList;
	}

	public void setEventList(List<EventVO> eventList) {
		this.eventList = eventList;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public String getPrePageParam() {
		return prePageParam;
	}

	public void setPrePageParam(String prePageParam) {
		this.prePageParam = prePageParam;
	}

	public String getMainPopAt() {
		return mainPopAt;
	}

	public void setMainPopAt(String mainPopAt) {
		this.mainPopAt = mainPopAt;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
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

	public String getSearchSeType() {
		return searchSeType;
	}

	public void setSearchSeType(String searchSeType) {
		this.searchSeType = searchSeType;
	}

	public String getPostingAt() {
		return postingAt;
	}

	public void setPostingAt(String postingAt) {
		this.postingAt = postingAt;
	}
	

}
