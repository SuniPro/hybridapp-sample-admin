package com.hybirdapp.sample.mngr.answer.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hybirdapp.sample.cmmn.CmmnMngrVO;

public class AnswerVO extends CmmnMngrVO {

	
	private String searchTotal;				// 전체 검색
	private String prePageParam;			// 이전 페이지

	private String no;						// 목록 순서
	private String id;						// 문의자 아이디
	private String kname;					// 문의자 이름
	private String inqrySn;					// 문의 일련번호
	private String inqrySe;					// 문의 구분
	private String inqrySeNm;				// 문의 구분 이름
	private String inqryArea;				// 문의 지역
	private String inqryAreaNm;				// 문의 지역 이름
	private String inqryPwd;				// 문의 비밀번호
	private String inqrySj;					// 문의 제목
	private String inqryCn;					// 문의 내용
	private String inqryFileGrpSn;			// 문의 파일 그룹 일련번호
	private String answerAt;				// 문의에 대한 답변 여부
	private String inqryMngrId;				// 담당등록자아이디
	private String inqryMngrNm;				// 담당등록자명
	private String inqryMngrEmail;			// 담당이메일
	private String inqryMngrSn;				// 담당일련번호
	private String clNm;					// 공통코드명
	private String clCd;					// 공통코드
	private String useAt;					// 사용여부
	
	private String answerDate;				// 답변일시
	private String inqryDate;				// 문의 수정일지
	
	private String handphone1;			// 핸드폰1
	private String handphone2;			// 핸드폰2
	private String handphone3;			// 핸드폰3
	private String mngrId;					// 답변 관리자 아이디
	private String mngrNm;					// 답변 관리자 이름
	private String answerSj;				// 답변 제목
	private String answerCn;				// 답변 내용
	private String answerFileGrpSn;			// 답변 파일 그룹 일련번호
	private String answerIsrtDate;			// 답변 날짜
	
	private String searchText;			//검색
	private String searchType;			//검색
	private String searchFromDt;		// 가입일자 from
	private String searchToDt;			// 가입일자 to	
	private String searchKname;			// 회원명
	private String searchId;			// id	
	private String searchAnswerAt;		// 답변여부
	private String searchInqrySe;		// 답변여부
	
	
	private List<AnswerVO> answerList;		// 문의 답변 목록
	private List<Map<String, Object>> answerManagerList; //
	@JsonIgnore
	private List<MultipartFile> uploadList;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	
	public String getInqryMngrId() {
		return inqryMngrId;
	}
	public void setInqryMngrId(String inqryMngrId) {
		this.inqryMngrId = inqryMngrId;
	}
	public String getSearchTotal() {
		return searchTotal;
	}
	public void setSearchTotal(String searchTotal) {
		this.searchTotal = searchTotal;
	}
	public String getPrePageParam() {
		return prePageParam;
	}
	public void setPrePageParam(String prePageParam) {
		this.prePageParam = prePageParam;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKname() {
		return kname;
	}
	public void setKname(String kname) {
		this.kname = kname;
	}
	public String getInqrySn() {
		return inqrySn;
	}
	public void setInqrySn(String inqrySn) {
		this.inqrySn = inqrySn;
	}
	
	public String getInqrySe() {
		return inqrySe;
	}
	public void setInqrySe(String inqrySe) {
		this.inqrySe = inqrySe;
	}
	
	public String getInqrySeNm() {
		return inqrySeNm;
	}
	public void setInqrySeNm(String inqrySeNm) {
		this.inqrySeNm = inqrySeNm;
	}
	
	public String getInqryArea() {
		return inqryArea;
	}
	public void setInqryArea(String inqryArea) {
		this.inqryArea = inqryArea;
	}
	
	public String getInqryAreaNm() {
		return inqryAreaNm;
	}
	public void setInqryAreaNm(String inqryAreaNm) {
		this.inqryAreaNm = inqryAreaNm;
	}
	
	public String getInqryPwd() {
		return inqryPwd;
	}
	public void setInqryPwd(String inqryPwd) {
		this.inqryPwd = inqryPwd;
	}
	
	public String getInqrySj() {
		return inqrySj;
	}
	public void setInqrySj(String inqrySj) {
		this.inqrySj = inqrySj;
	}
	
	public String getInqryCn() {
		return inqryCn;
	}
	public void setInqryCn(String inqryCn) {
		this.inqryCn = inqryCn;
	}
	
	public String getInqryFileGrpSn() {
		return inqryFileGrpSn;
	}
	public void setInqryFileGrpSn(String inqryFileGrpSn) {
		this.inqryFileGrpSn = inqryFileGrpSn;
	}
	
	public String getAnswerAt() {
		return answerAt;
	}
	public void setAnswerAt(String answerAt) {
		this.answerAt = answerAt;
	}
	
	
	public String getMngrId() {
		return mngrId;
	}
	public void setMngrId(String mngrId) {
		this.mngrId = mngrId;
	}
	
	public String getMngrNm() {
		return mngrNm;
	}
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}
	
	public String getAnswerSj() {
		return answerSj;
	}
	public void setAnswerSj(String answerSj) {
		this.answerSj = answerSj;
	}
	
	public String getAnswerCn() {
		return answerCn;
	}
	public void setAnswerCn(String answerCn) {
		this.answerCn = answerCn;
	}
	
	public String getAnswerFileGrpSn() {
		return answerFileGrpSn;
	}
	public void setAnswerFileGrpSn(String answerFileGrpSn) {
		this.answerFileGrpSn = answerFileGrpSn;
	}
	public String getAnswerIsrtDate() {
		return answerIsrtDate;
	}
	public void setAnswerIsrtDate(String answerIsrtDate) {
		this.answerIsrtDate = answerIsrtDate;
	}
	public List<AnswerVO> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<AnswerVO> answerList) {
		this.answerList = answerList;
	}
	
	public List<MultipartFile> getUploadList() {
		return uploadList;
	}
	public void setUploadList(List<MultipartFile> uploadList) {
		this.uploadList = uploadList;
	}
	public String getInqryMngrEmail() {
		return inqryMngrEmail;
	}
	public void setInqryMngrEmail(String inqryMngrEmail) {
		this.inqryMngrEmail = inqryMngrEmail;
	}
	public String getInqryMngrSn() {
		return inqryMngrSn;
	}
	public void setInqryMngrSn(String inqryMngrSn) {
		this.inqryMngrSn = inqryMngrSn;
	}
	public String getClNm() {
		return clNm;
	}
	public void setClNm(String clNm) {
		this.clNm = clNm;
	}
	public String getClCd() {
		return clCd;
	}
	public void setClCd(String clCd) {
		this.clCd = clCd;
	}
	public List<Map<String, Object>> getAnswerManagerList() {
		return answerManagerList;
	}
	public void setAnswerManagerList(List<Map<String, Object>> answerManagerList) {
		this.answerManagerList = answerManagerList;
	}
	public String getInqryMngrNm() {
		return inqryMngrNm;
	}
	public void setInqryMngrNm(String inqryMngrNm) {
		this.inqryMngrNm = inqryMngrNm;
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
	public String getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}
	public String getInqryDate() {
		return inqryDate;
	}
	public void setInqryDate(String inqryDate) {
		this.inqryDate = inqryDate;
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
	public String getSearchAnswerAt() {
		return searchAnswerAt;
	}
	public void setSearchAnswerAt(String searchAnswerAt) {
		this.searchAnswerAt = searchAnswerAt;
	}
	public String getSearchInqrySe() {
		return searchInqrySe;
	}
	public void setSearchInqrySe(String searchInqrySe) {
		this.searchInqrySe = searchInqrySe;
	}
	public String getUseAt() {
		return useAt;
	}
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
	
}