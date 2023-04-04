package com.hybirdapp.sample.mngr.promotion.service;


import com.hybirdapp.sample.cmmn.crypt.BCryptManager;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class PromotionVO {
	private String promotionNm; // 입력값
	private Long orderSeq;
	private Long orderDtlSeq;
	private Long qrcodeSeq;
	private String ticketColor;
	private Long fcltSeq;
	private Long muTicketSeq;
	private char muMpFlag;
	private String muTicketNm;
	private String muTicketShortNm;
	private int ticketLimitCnt;
	private String weekFlag;
	private Long saleAmt;
	private String mMenu;
	private String mLoc;
	private String orderNo;
	private String paymentQrcode;
	private Long orderDtlNo;
	private String memName; // 입력값
	private String memHandphone1; // 입력값
	private String memHandphone2; // 입력값
	private String memHandphone3; // 입력값
	private Date startDate; // 입력값
	private Date endDate; // 입력값
	private String sendMemNm; // 입력값
	private String signState; // 입력값
	private String isrtId;
	private String isrtIp;
	private String isrtDate;
	private String fcltClass;
	@Builder
	public PromotionVO(String promotionNm, Long orderSeq, Long orderDtlSeq, Long qrcodeSeq, String ticketColor, Long fcltSeq, Long muTicketSeq, char muMpFlag, String muTicketNm, String muTicketShortNm, int ticketLimitCnt, String weekFlag, Long saleAmt, String mMenu, String mLoc, String orderNo, String paymentQrcode, Long orderDtlNo, String memName, String memHandphone1, String memHandphone2, String memHandphone3, Date startDate, Date endDate, String sendMemNm, String signState, String isrtId, String isrtIp, String isrtDate, String fcltClass) {
		this.promotionNm = promotionNm;
		this.orderSeq = orderSeq;
		this.orderDtlSeq = orderDtlSeq;
		this.qrcodeSeq = qrcodeSeq;
		this.ticketColor = ticketColor;
		this.fcltSeq = fcltSeq;
		this.muTicketSeq = muTicketSeq;
		this.muMpFlag = muMpFlag;
		this.muTicketNm = muTicketNm;
		this.muTicketShortNm = muTicketShortNm;
		this.ticketLimitCnt = ticketLimitCnt;
		this.weekFlag = weekFlag;
		this.saleAmt = saleAmt;
		this.mMenu = mMenu;
		this.mLoc = mLoc;
		this.orderNo = orderNo;
		this.paymentQrcode = paymentQrcode;
		this.orderDtlNo = orderDtlNo;
		this.memName = memName;
		this.memHandphone1 = memHandphone1;
		this.memHandphone2 = memHandphone2;
		this.memHandphone3 = memHandphone3;
		this.startDate = startDate;
		this.endDate = endDate;
		this.sendMemNm = sendMemNm;
		this.signState = signState;
		this.isrtId = isrtId;
		this.isrtIp = isrtIp;
		this.isrtDate = isrtDate;
		this.fcltClass = fcltClass;
	}

	public void BcryptEncodeHandPhone2() {
		this.memHandphone2 = BCryptManager.getInstance().encode(memHandphone2);
	}

}
