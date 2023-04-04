package com.hybirdapp.sample.mngr.tckSng.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SertbPmenuVo implements Serializable, Cloneable {

	// 티켓명
	public String m_dname;

	// 티켓약칭
	public String m_abbr;
	public String m_abbr_1;
	public String m_abbr_2;

	// 시작일
	public String m_dcfrom;

	// 종료일
	public String m_dcto;

	// 주중/주말/모두
	public String useAt;

	// 티켓사용가능횟수
	public String m_ticket_qt;

	// 판매가격
	public String m_amount; // el__m_amount

	// 티켓구분
	public String ticket_price_type; // el__ticket_price_type

	// 티켓타입 - 기본 / 패키지
	public String m_type; // el__m_type

	// 부가세 || 면세
	public String m_vat; // el__m_vat

	// 부가세율
	public String m_tax_pct; // el__m_tax_pct

	// 부가세 금액
	public String m_tax_amount; // el__m_tax_amount

	// 개소세 금액
	public String m_tax1_amount; // el__m_tax1_amount

	// 교육세 금액
	public String m_tax2_amount; // el__m_tax2_amount

	// 농특세 금액
	public String m_tax3_amount; // el__m_tax3_amount

	// 기금 금액
	public String m_tax4_amount; // el__m_tax4_amount

	// 개소세
	public String m_tax1; // el__m_tax1

	// 교육세
	public String m_tax2; // el__m_tax2

	// 농특세
	public String m_tax3; // el__m_tax3

	// 기금
	public String m_tax4; // el__m_tax4

	// 판매여부 - 판매 / 판매중지
	public String sale_yn; // el__sale_yn

	public String mLoc;
	public String m_loc;
	public String grLoc;
	public String gr_loc;
	public String gr_code;
	public String m_menu;
	// 공급가액
	public String m_price;
	public String m_class; // O1 : 일반상품, O2 : 패키지상품, O3 : Etc

	public String fclt_class;
	public String fclt_seq;
	public String grDesc;
	public String popMenuUnitNm;
	public String popTicketGrp;
	public String menu_unit_seq;
	public String fclt_list;
	public String isrt_id;
	public String updt_id;
	public String ACCESS_IP;
	public String total_el_cnt;
	public String mu_ticket_seq;

	// pkg only
	public String menuPkgNmPopup;
	public String pkgTckGrpList;

	public String mp_ticket_seq;

	public String sa_amt;

	public String pm_loc;
	public String pm_menu;
	public String pm_use_loc;
	public String pm_addmenu;

	public String unitProdTicket;
	public String agent_cd;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}