package com.hybirdapp.sample.cmmn.script.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.cmmn.script.service.AttachVO;
import com.hybirdapp.sample.cmmn.sys.service.ClCdVO;

@OracleMapper("ScriptDAO")
public interface ScriptDAO {

	/***
	 * 공통코드 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<?> selectCmmnCdList(ClCdVO vo) throws Exception;
	

	/***
	 * 파일 그룹 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertGrpFile(Map<String, Object> map) throws Exception;
	
	/***
	 * 파일 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertFile(Map<String, Object> map) throws Exception;
	
	/**
	 * 첨부파일 정보 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public AttachVO selectAttachInfo(AttachVO vo) throws Exception;
	
	/***
	 * 파일 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteFile(AttachVO vo) throws Exception;
	
	
	/**
	 * 첨부파일 정보 목록 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AttachVO> selectAttachInfoList(@Param("fileGrpSn") String fileGrpSn) throws Exception;
	
	/***
	 * 지역코드 -> 지역화폐 지역코드 조회
	 * @param providerId
	 * @return
	 * @throws Exception
	 */
	public String selectProviderId(@Param("providerId") String providerId) throws Exception;
	
	/* ============================== 시간 설정 ============================== */
		
	/***
	 * 회원가입 가능 일시
	 * @return
	 * @throws Exception
	 */
	public int selectSbscrbTimeCheck() throws Exception;
	
	/***
	 * 교통카드 등록 가능 일시
	 * @return
	 * @throws Exception
	 */
	public int selectTrnsportTimeCheck() throws Exception;
	
	/***
	 * 지역화폐 등록 가능 일시
	 * @return
	 * @throws Exception
	 */
	public int selectCrrncyTimeCheck() throws Exception;
	
	/***
	 * 지원금 신청 가능 일시
	 * @return
	 * @throws Exception
	 */
	public int selectSprmnyTimeCheck() throws Exception;
	
	/***
	 * 이용/신청 내역 열람 가능 일시
	 * @return
	 * @throws Exception
	 */
	public int selectDtlsTimeCheck() throws Exception;
	
	/***
	 * 비회원 신청 가능 일시
	 * @return
	 * @throws Exception
	 */
	public int selectNmbrTimeCheck() throws Exception;
	
	/***
	 * 회원가입 후 바로 지원금 신청 가능 일시
	 * @return
	 * @throws Exception
	 */
	public int selectSbscrbSprmnyTimeCheck() throws Exception;
	
	/***
	 * 첨부파일 저장
	 * @return
	 * @throws Exception
	 */
	public int attachFileSave(AttachVO vo) throws Exception;

	/* 파일저장 추가 : JDS */
	int insertImageFile(Map<String, Object> params) throws Exception;
    /* 단일파일 조회 */
    Map<String, Object> selectSingleFile(Map<String, Object> params) throws Exception;
	/* 단일파일 조회 */
	List<Map<String, Object>> selectMultiFile(Map<String, Object> params) throws Exception;
	/* 이미지 파일 삭제 */
	int deleteImageFile(Map<String, Object> params) throws Exception;
	int updateImageFile(Map<String, Object> params) throws Exception;
}
