package com.hybirdapp.sample.mngr.notice.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hybirdapp.sample.cmmn.CmmnMngrVO;
import com.hybirdapp.sample.cmmn.script.service.AttachVO;

public class NoticeVO extends CmmnMngrVO {

	private String noticeSn;			// 공지사항 일련번호
	private String noticeSe;			// 공지사항 구분
	private String noticeSj;			// 공지사항 제목
	private String noticeCn;			// 공지사항 내용
	private String noticeBgnde;			// 공지사항 시작일
	private String noticeEndde;			// 공지사항 종료일
	private String inqireCnt;			// 조회수
	private String expsrAt;				// 노출여부
	private String useAt;				// 사용여부
	private String mainPopAt;			// 팝업 사용여부
	private String registNm;			// 작성자 이름
	private String isrtNm;				//등록자명
	private String updtNm;				//수정자 명
	
	private String searchTotal;			// 전체 검색
	private String searchNoticeSj;		// 제목 검색
	private String searchNoticeCn;		// 내용 검색
	private String prePageParam;		// 이전 페이지
	private String imageType;			//이미지 수정
	
	private String searchSeType;		//공지구분
	private String searchText;			//검색
	private String searchType;			//검색
	private String searchFromDt;		// 가입일자 from
	private String searchToDt;			// 가입일자 to	
	
	private List<NoticeVO> noitceList;		// 공지사항 list
	private List<AttachVO> noticeFileList;	// 첨부파일 파일목록
	
	@JsonIgnore
	private List<MultipartFile> uploadList;

	public String getNoticeSn() {
		return noticeSn;
	}

	public void setNoticeSn(String noticeSn) {
		this.noticeSn = noticeSn;
	}

	public String getNoticeSe() {
		return noticeSe;
	}

	public void setNoticeSe(String noticeSe) {
		this.noticeSe = noticeSe;
	}

	public String getNoticeSj() {
		return noticeSj;
	}

	public void setNoticeSj(String noticeSj) {
		this.noticeSj = noticeSj;
	}

	public String getNoticeCn() {
		return noticeCn;
	}

	public void setNoticeCn(String noticeCn) {
		this.noticeCn = noticeCn;
	}

	public String getNoticeBgnde() {
		return noticeBgnde;
	}

	public void setNoticeBgnde(String noticeBgnde) {
		this.noticeBgnde = noticeBgnde;
	}

	public String getNoticeEndde() {
		return noticeEndde;
	}

	public void setNoticeEndde(String noticeEndde) {
		this.noticeEndde = noticeEndde;
	}

	public String getInqireCnt() {
		return inqireCnt;
	}

	public void setInqireCnt(String inqireCnt) {
		this.inqireCnt = inqireCnt;
	}

	public String getExpsrAt() {
		return expsrAt;
	}

	public void setExpsrAt(String expsrAt) {
		this.expsrAt = expsrAt;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public String getRegistNm() {
		return registNm;
	}

	public void setRegistNm(String registNm) {
		this.registNm = registNm;
	}

	public String getSearchTotal() {
		return searchTotal;
	}

	public void setSearchTotal(String searchTotal) {
		this.searchTotal = searchTotal;
	}

	public String getSearchNoticeSj() {
		return searchNoticeSj;
	}

	public void setSearchNoticeSj(String searchNoticeSj) {
		this.searchNoticeSj = searchNoticeSj;
	}

	public String getSearchNoticeCn() {
		return searchNoticeCn;
	}

	public void setSearchNoticeCn(String searchNoticeCn) {
		this.searchNoticeCn = searchNoticeCn;
	}

	public String getPrePageParam() {
		return prePageParam;
	}

	public void setPrePageParam(String prePageParam) {
		this.prePageParam = prePageParam;
	}

	public List<NoticeVO> getNoitceList() {
		return noitceList;
	}

	public void setNoitceList(List<NoticeVO> noitceList) {
		this.noitceList = noitceList;
	}

	public List<AttachVO> getNoticeFileList() {
		return noticeFileList;
	}

	public void setNoticeFileList(List<AttachVO> noticeFileList) {
		this.noticeFileList = noticeFileList;
	}

	public List<MultipartFile> getUploadList() {
		return uploadList;
	}

	public void setUploadList(List<MultipartFile> uploadList) {
		this.uploadList = uploadList;
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

}
