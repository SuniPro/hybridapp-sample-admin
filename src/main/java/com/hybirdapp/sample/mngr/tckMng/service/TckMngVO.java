package com.hybirdapp.sample.mngr.tckMng.service;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;

public class TckMngVO extends CmmnMngrVO {
	
	private String menuClass;		// 티켓상품구분
	private String fcltClass;		// 시설구분
	private String fcltSeq;			// 시설시퀀스
	private String ticketGrpNm;		// 티켓그룹명
	private String ticketGrpSeq;	// 티켓그룹시퀀스
	private String weekFlag; 		// 티켓주중주말구분	
	private String ticketLimitCnt;	// 티켓이용횟수

	/* ================[팝업 호출 필수값 ]================ */
	private String tckInq; //
	private String viewType;

	public String getTckInq() {
		return tckInq;
	}

	public void setTckInq(String tckInq) {
		this.tckInq = tckInq;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	/* ================[팝업 호출 필수값 End]================ */

	public String getMenuClass() {
		return menuClass;
	}

	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
	}

	public String getFcltClass() {
		return fcltClass;
	}

	public void setFcltClass(String fcltClass) {
		this.fcltClass = fcltClass;
	}

	public String getFcltSeq() {
		return fcltSeq;
	}

	public void setFcltSeq(String fcltSeq) {
		this.fcltSeq = fcltSeq;
	}

	public String getTicketGrpNm() {
		return ticketGrpNm;
	}

	public void setTicketGrpNm(String ticketGrpNm) {
		this.ticketGrpNm = ticketGrpNm;
	}

	public String getTicketGrpSeq() {
		return ticketGrpSeq;
	}

	public void setTicketGrpSeq(String ticketGrpSeq) {
		this.ticketGrpSeq = ticketGrpSeq;
	}

	public String getWeekFlag() {
		return weekFlag;
	}

	public void setWeekFlag(String weekFlag) {
		this.weekFlag = weekFlag;
	}

	public String getTicketLimitCnt() {
		return ticketLimitCnt;
	}

	public void setTicketLimitCnt(String ticketLimitCnt) {
		this.ticketLimitCnt = ticketLimitCnt;
	}

	
}
