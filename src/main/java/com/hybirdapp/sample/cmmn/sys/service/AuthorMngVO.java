package com.hybirdapp.sample.cmmn.sys.service;

import java.util.List;

import com.hybirdapp.sample.cmmn.CmmnVO;

public class AuthorMngVO extends CmmnVO {

	private String authCd;			/*권한 코드*/
	private String authNm;			/*권한 명*/
	private String authDc;			/*권한 설명*/
	private String authBasicAt;		/*권한 기본 여부 - 반드시 1건만 Y*/
	private String useAt;			/*사용여부*/
	private String registId;		/*등록코드*/
	private String registDt;		/*등록일시*/
	private String updtId;			/*수정코드*/
	private String updtDt;			/*수정일시*/
	
	private List<AuthorMngDtlVO> authorMngDtlList;

	public String getAuthCd() {
		return authCd;
	}

	public void setAuthCd(String authCd) {
		this.authCd = authCd;
	}

	public String getAuthNm() {
		return authNm;
	}

	public void setAuthNm(String authNm) {
		this.authNm = authNm;
	}

	public String getAuthDc() {
		return authDc;
	}

	public void setAuthDc(String authDc) {
		this.authDc = authDc;
	}

	public String getAuthBasicAt() {
		return authBasicAt;
	}

	public void setAuthBasicAt(String authBasicAt) {
		this.authBasicAt = authBasicAt;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public String getRegistId() {
		return registId;
	}

	public void setRegistId(String registId) {
		this.registId = registId;
	}

	public String getRegistDt() {
		return registDt;
	}

	public void setRegistDt(String registDt) {
		this.registDt = registDt;
	}

	public String getUpdtId() {
		return updtId;
	}

	public void setUpdtId(String updtId) {
		this.updtId = updtId;
	}

	public String getUpdtDt() {
		return updtDt;
	}

	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}

	public List<AuthorMngDtlVO> getAuthorMngDtlList() {
		return authorMngDtlList;
	}

	public void setAuthorMngDtlList(List<AuthorMngDtlVO> authorMngDtlList) {
		this.authorMngDtlList = authorMngDtlList;
	}

	
}
