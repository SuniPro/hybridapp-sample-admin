package com.hybirdapp.sample.mngr.mber.service;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;

public class MberManageVO extends CmmnMngrVO {
	//사용자 정보
	private String companyCd;			// 회사구분
	private String id;					// 아이디
	private String password;			// 패스워드
	private String kname;				// 회원명
	private String resno;				// 일련번호
	private String engName;				// 회원명_영문
	private String chnName;				// 회원명_한자
	private String entryCd;				// 가입구분
	private String memberCd;			// 회원구분
	private String memberSt;			// 회원상태
	private String memberNm;			// 회원상태명
	private String memberNum;			// 멤버쉽번호
	private String memberNo;			// 멤버쉽번호
	private String emdNm;				// 행정동 이름
	private String sidoNm;				// 시도이름
	private String sexCd;				// 성별구분
	private String birthDay;			// 생년월일
	private String lunarCd;				// 음양
	private String homeZipCd;			// 자택우편번호
	private String homeAddr1;			// 자택주소1
	private String homeAddr2;			// 자택주소2
	private String homeTel1;			// 자택전화번호1
	private String homeTel2;			// 자택전화번호2
	private String homeTel3;			// 자택전화번호3
	private String postYn;				// 우편물수신여부
	private String handphone1;			// 핸드폰1
	private String handphone2;			// 핸드폰2
	private String handphone3;			// 핸드폰3
	private String smsYn;				// SMS 수신여부
	private String email;				// 이메일
	private String emailYn;				// 이메일수신여부
	private String isrtId;				// 입력자
	private String isrtDate;			// 입력시간
	private String updtId;				// 수정자
	private String updtDate;			// 수정시간
	private String inqryCnt;			// 1:1문의 건수
	private String paymentCnt;			// 구매 건수
	private String memberType;			// 회원
	
	private String totalMember;			// 총 회원 현황 
	private String memberTotal;			// 일반 회원 합계
	private String membershipTotal;		// 콘도 회원 합게
	private String total;				// 회원 합게
	private String serMember;			// ser 일반 회원
	private String webMember;			// web 일반 회원
	private String serMembership;		// ser 콘도 회원
	private String webMembership;		// web 콘도 회원
	private String serAppTotal;			// ser 총 회원
	private String webSiteTotal;		// web 총 회원
	private String serEve;				// ser 전일 회원
	private String serToday;			// ser 금일 회원
	private String memberToday;			// 오늘날짜
	

	private String searchEntryCd;		// 조건 가입채널
	private String searchMemberCd;		// 조건 회원구분
	private String searchSexCd;			// 조건 성별
	private String searchSidoNm;		// 조건 지역
	private String searchInqryYn;		// 조건 1:1 작성 여부
	private String searchPayYn;			// 조건 구매여부
	private String searchMemberSt;		// 조건 상태
	private String searchSmsYn;			// 조건 sms 동의여부
	private String searchEmailYn;		// 조건 이메일 동의여부
	private String searchKname;			// 조건 회원명
	private String searchId;			// 조건 회원아이디
	private String searchHandphone1;	// 조건 휴대폰번호1
	private String searchHandphone2;	// 조건 휴대폰번호2
	private String searchHandphone3;	// 조건 휴대폰번호3
	private String searchHandphone;	    // 조건 휴대폰번호
	private String searchFromDt;		// 가입일자 from
	private String searchToDt;			// 가입일자 to

	private String lastLoginDate;		// 마지막 로그인 일자
	
	
	public String getCompanyCd() {
		return companyCd;
	}
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getKname() {
		return kname;
	}
	public void setKname(String kname) {
		this.kname = kname;
	}
	public String getEngName() {
		return engName;
	}
	public void setEngName(String engName) {
		this.engName = engName;
	}
	public String getChnName() {
		return chnName;
	}
	public void setChnName(String chnName) {
		this.chnName = chnName;
	}
	public String getEntryCd() {
		return entryCd;
	}
	public void setEntryCd(String entryCd) {
		this.entryCd = entryCd;
	}
	public String getMemberCd() {
		return memberCd;
	}
	public void setMemberCd(String memberCd) {
		this.memberCd = memberCd;
	}
	public String getMemberSt() {
		return memberSt;
	}
	public void setMemberSt(String memberSt) {
		this.memberSt = memberSt;
	}
	public String getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}
	public String getEmdNm() {
		return emdNm;
	}
	public void setEmdNm(String emdNm) {
		this.emdNm = emdNm;
	}
	public String getSidoNm() {
		return sidoNm;
	}
	public void setSidoNm(String sidoNm) {
		this.sidoNm = sidoNm;
	}
	public String getSexCd() {
		return sexCd;
	}
	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getLunarCd() {
		return lunarCd;
	}
	public void setLunarCd(String lunarCd) {
		this.lunarCd = lunarCd;
	}
	public String getHomeZipCd() {
		return homeZipCd;
	}
	public void setHomeZipCd(String homeZipCd) {
		this.homeZipCd = homeZipCd;
	}
	public String getHomeAddr1() {
		return homeAddr1;
	}
	public void setHomeAddr1(String homeAddr1) {
		this.homeAddr1 = homeAddr1;
	}
	public String getHomeAddr2() {
		return homeAddr2;
	}
	public void setHomeAddr2(String homeAddr2) {
		this.homeAddr2 = homeAddr2;
	}
	public String getHomeTel1() {
		return homeTel1;
	}
	public void setHomeTel1(String homeTel1) {
		this.homeTel1 = homeTel1;
	}
	public String getHomeTel2() {
		return homeTel2;
	}
	public void setHomeTel2(String homeTel2) {
		this.homeTel2 = homeTel2;
	}
	public String getHomeTel3() {
		return homeTel3;
	}
	public void setHomeTel3(String homeTel3) {
		this.homeTel3 = homeTel3;
	}
	public String getPostYn() {
		return postYn;
	}
	public void setPostYn(String postYn) {
		this.postYn = postYn;
	}
	public String getHandphone1() {
		return handphone1;
	}
	public void setHandphone1(String handphone1) {
		this.handphone1 = handphone1;
	}
	public String getHandphone2() {
		return handphone2;
	}
	public void setHandphone2(String handphone2) {
		this.handphone2 = handphone2;
	}
	public String getHandphone3() {
		return handphone3;
	}
	public void setHandphone3(String handphone3) {
		this.handphone3 = handphone3;
	}
	public String getSmsYn() {
		return smsYn;
	}
	public void setSmsYn(String smsYn) {
		this.smsYn = smsYn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailYn() {
		return emailYn;
	}
	public void setEmailYn(String emailYn) {
		this.emailYn = emailYn;
	}
	public String getIsrtId() {
		return isrtId;
	}
	public void setIsrtId(String isrtId) {
		this.isrtId = isrtId;
	}
	public String getIsrtDate() {
		return isrtDate;
	}
	public void setIsrtDate(String isrtDate) {
		this.isrtDate = isrtDate;
	}
	public String getUpdtId() {
		return updtId;
	}
	public void setUpdtId(String updtId) {
		this.updtId = updtId;
	}
	public String getUpdtDate() {
		return updtDate;
	}
	public void setUpdtDate(String updtDate) {
		this.updtDate = updtDate;
	}
	public String getMemberNm() {
		return memberNm;
	}
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	public String getInqryCnt() {
		return inqryCnt;
	}
	public void setInqryCnt(String inqryCnt) {
		this.inqryCnt = inqryCnt;
	}
	public String getSearchEntryCd() {
		return searchEntryCd;
	}
	public void setSearchEntryCd(String searchEntryCd) {
		this.searchEntryCd = searchEntryCd;
	}
	public String getSearchMemberCd() {
		return searchMemberCd;
	}
	public void setSearchMemberCd(String searchMemberCd) {
		this.searchMemberCd = searchMemberCd;
	}
	public String getSearchSexCd() {
		return searchSexCd;
	}
	public void setSearchSexCd(String searchSexCd) {
		this.searchSexCd = searchSexCd;
	}
	public String getSearchSidoNm() {
		return searchSidoNm;
	}
	public void setSearchSidoNm(String searchSidoNm) {
		this.searchSidoNm = searchSidoNm;
	}
	public String getSearchInqryYn() {
		return searchInqryYn;
	}
	public void setSearchInqryYn(String searchInqryYn) {
		this.searchInqryYn = searchInqryYn;
	}
	public String getSearchPayYn() {
		return searchPayYn;
	}
	public void setSearchPayYn(String searchPayYn) {
		this.searchPayYn = searchPayYn;
	}
	public String getSearchMemberSt() {
		return searchMemberSt;
	}
	public void setSearchMemberSt(String searchMemberSt) {
		this.searchMemberSt = searchMemberSt;
	}
	public String getSearchSmsYn() {
		return searchSmsYn;
	}
	public void setSearchSmsYn(String searchSmsYn) {
		this.searchSmsYn = searchSmsYn;
	}
	public String getSearchEmailYn() {
		return searchEmailYn;
	}
	public void setSearchEmailYn(String searchEmailYn) {
		this.searchEmailYn = searchEmailYn;
	}
	public String getSearchKname() {
		return searchKname;
	}
	public void setSearchKname(String searchKname) {
		this.searchKname = searchKname;
	}
	public String getSearchId() {
		return searchId;
	}
	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}
	public String getSearchHandphone1() {
		return searchHandphone1;
	}
	public void setSearchHandphone1(String searchHandphone1) {
		this.searchHandphone1 = searchHandphone1;
	}
	public String getSearchHandphone2() {
		return searchHandphone2;
	}
	public void setSearchHandphone2(String searchHandphone2) {
		this.searchHandphone2 = searchHandphone2;
	}
	public String getSearchHandphone3() {
		return searchHandphone3;
	}
	public void setSearchHandphone3(String searchHandphone3) {
		this.searchHandphone3 = searchHandphone3;
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
	public String getPaymentCnt() {
		return paymentCnt;
	}
	public void setPaymentCnt(String paymentCnt) {
		this.paymentCnt = paymentCnt;
	}
	public String getTotalMember() {
		return totalMember;
	}
	public void setTotalMember(String totalMember) {
		this.totalMember = totalMember;
	}
	public String getSerMember() {
		return serMember;
	}
	public void setSerMember(String serMember) {
		this.serMember = serMember;
	}
	public String getWebMember() {
		return webMember;
	}
	public void setWebMember(String webMember) {
		this.webMember = webMember;
	}
	public String getSerMembership() {
		return serMembership;
	}
	public void setSerMembership(String serMembership) {
		this.serMembership = serMembership;
	}
	public String getWebMembership() {
		return webMembership;
	}
	public void setWebMembership(String webMembership) {
		this.webMembership = webMembership;
	}
	public String getSerAppTotal() {
		return serAppTotal;
	}
	public void setSerAppTotal(String serAppTotal) {
		this.serAppTotal = serAppTotal;
	}
	public String getWebSiteTotal() {
		return webSiteTotal;
	}
	public void setWebSiteTotal(String webSiteTotal) {
		this.webSiteTotal = webSiteTotal;
	}
	public String getSerEve() {
		return serEve;
	}
	public void setSerEve(String serEve) {
		this.serEve = serEve;
	}
	public String getSerToday() {
		return serToday;
	}
	public void setSerToday(String serToday) {
		this.serToday = serToday;
	}
	public String getMemberToday() {
		return memberToday;
	}
	public void setMemberToday(String memberToday) {
		this.memberToday = memberToday;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getResno() {
		return resno;
	}
	public void setResno(String resno) {
		this.resno = resno;
	}
	public String getMemberTotal() {
		return memberTotal;
	}
	public void setMemberTotal(String memberTotal) {
		this.memberTotal = memberTotal;
	}
	public String getMembershipTotal() {
		return membershipTotal;
	}
	public void setMembershipTotal(String membershipTotal) {
		this.membershipTotal = membershipTotal;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getSearchHandphone() {
		return searchHandphone;
	}
	public void setSearchHandphone(String searchHandphone) {
		this.searchHandphone = searchHandphone;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
}