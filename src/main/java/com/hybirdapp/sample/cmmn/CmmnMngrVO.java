package com.hybirdapp.sample.cmmn;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CmmnMngrVO {

	/** 현재페이지 */
	private int currPageIdx;
	private int offsetIndex;
	
	/** 클릭한 페이지 기억 */
	private int pageMemory;
	
	/** 페이지 전체 갯수 */
	private int totPageCnt;
	
	/** 한번에 보여질 갯수 */
	private int pageSize;
	
	/** 하단 페이지 수 */
	private int pageBottomSize;
	
	/** 목록 조회시 순번 변수 */
	private String no;
	
	/* 사용자 정보 */
	private String seMngrSn;      //관리자 일련번호
	private String seMngrId;      //관리자 아이디
	private String seMngrNm;      //관리자 명칭
	private String seAuthCd;	  //관리자 권한 코드
	
	private String popupTargetId;  		//팝업에서 타겟명
	private String popupCallbackFnNm;	//팝업에서 호출될 펑션명
	
	private String isrtId;				//작성자 아이디
	private String isrtIp;				//작성자 아이피
	private String isrtDate;			//작성일
	private String updtId;				//수정자 아이디
	private String updtIp;				//수정자 아이피
	private String updtDate;			//수정일
	
	
	public String getNo() {
	    
	    double dNo = 0d;
	    int i = 0;
	    if (no != null && !"".equals(no)) {
	        
	        try {
	            dNo = Double.parseDouble(no);
	            
	            i = Integer.parseInt(String.valueOf(Math.round(dNo)));
	        }
	        catch (Exception e) {
	            System.out.println("Paging No Error :: " + e.toString());
	        }
	    }
	    return i+"";
	}

	public void setNo(String no) {
		this.no = no;
	}

	public int getOffsetIndex() {
		
		if (pageMemory > 1) {
			currPageIdx = pageMemory;
		}
		
		if (currPageIdx > 0) {
			offsetIndex = ((currPageIdx-1) * 10)+1;
		}
		
		return offsetIndex;
	}
	
	public int getCurrPageIdx() {
		return currPageIdx;
	}
	
	public void setCurrPageIdx(int currPageIdx) {
		this.currPageIdx = currPageIdx;
	}
	
	public int getTotPageCnt() {
		
		if (totPageCnt == 0) {
			totPageCnt = 1;
		}
		
		return totPageCnt;
	}
	
	public void setTotPageCnt(int totPageCnt) {
		this.totPageCnt = totPageCnt;
	}
	
	public int getPageSize() {
		
		if (pageSize == 0) {
			pageSize = 10;
		}
		if(currPageIdx != 0) {
			pageSize = currPageIdx * 10;
		}
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageBottomSize() {
		
		if (pageBottomSize == 0) {
			pageBottomSize = 10;
		}
//		if(currPageIdx != 0) {
//			pageBottomSize = currPageIdx * 10;
//		}
		return pageBottomSize;
	}

	public void setPageBottomSize(int pageBottomSize) {
		this.pageBottomSize = pageBottomSize;
	}

	public String getSeMngrSn() {
		
		if (seMngrSn == null || "".equals(seMngrSn)) {
			seMngrSn = SessionUtil.getAttributeStr(CmmnConstant.SESSION_MNGR, CmmnConstant.MNGR_SN);
		}
		
		return seMngrSn;
	}

	public void setSeMngrSn(String seMngrSn) {
		this.seMngrSn = seMngrSn;
	}

	public String getSeMngrId() {
		
		if (seMngrId == null || "".equals(seMngrId)) {
			seMngrId = SessionUtil.getAttributeStr(CmmnConstant.SESSION_MNGR, "id");
		}
		
		return seMngrId;
	}

	public void setSeMngrId(String seMngrId) {
		this.seMngrId = seMngrId;
	}

	public String getSeMngrNm() {
		
		if (seMngrNm == null || "".equals(seMngrNm)) {
			seMngrNm = SessionUtil.getAttributeStr(CmmnConstant.SESSION_MNGR, CmmnConstant.MNGR_NM);
		}
		
		return seMngrNm;
		
	}
	
	public void setSeMngrNm(String seMngrNm) {
		this.seMngrNm = seMngrNm;
	}

	public String getSeAuthCd() {
		
		if (seAuthCd == null || "".equals(seAuthCd)) {
			seAuthCd = SessionUtil.getAttributeStr(CmmnConstant.SESSION_MNGR, CmmnConstant.AUTH_CD);
		}
		
		return seAuthCd;
	}

	public void setSeAuthCd(String seAuthCd) {
		this.seAuthCd = seAuthCd;
	}
	
	public String getPopupTargetId() {
		return popupTargetId;
	}

	public void setPopupTargetId(String popupTargetId) {
		this.popupTargetId = popupTargetId;
	}

	public String getPopupCallbackFnNm() {
		return popupCallbackFnNm;
	}

	public void setPopupCallbackFnNm(String popupCallbackFnNm) {
		this.popupCallbackFnNm = popupCallbackFnNm;
	}

	public int getPageMemory() {
		return pageMemory;
	}

	public void setPageMemory(int pageMemory) {
		this.pageMemory = pageMemory;
	}

	public String getIsrtId() {
		
		if (isrtId == null || "".equals(isrtId)) {
			isrtId = SessionUtil.getAttributeStr(CmmnConstant.SESSION_MNGR, CmmnConstant.MNGR_ID);
		}
		
		return isrtId;
	}

	public void setIsrtId(String isrtId) {
		this.isrtId = isrtId;
	}

	public String getIsrtIp() {
		return isrtIp;
	}

	public void setIsrtIp(String isrtIp) {
		this.isrtIp = isrtIp;
	}

	public String getUpdtId() {
		if (updtId == null || "".equals(updtId)) {
			updtId = SessionUtil.getAttributeStr(CmmnConstant.SESSION_MNGR, CmmnConstant.MNGR_ID);
		}
		return updtId;
	}

	public void setUpdtId(String updtId) {
		this.updtId = updtId;
	}

	public String getUpdtIp() {
		return updtIp;
	}

	public void setUpdtIp(String updtIp) {
		this.updtIp = updtIp;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getIsrtDate() {
		return isrtDate;
	}

	public void setIsrtDate(String isrtDate) {
		this.isrtDate = isrtDate;
	}

	public String getUpdtDate() {
		return updtDate;
	}

	public void setUpdtDate(String updtDate) {
		this.updtDate = updtDate;
	}
	
}
