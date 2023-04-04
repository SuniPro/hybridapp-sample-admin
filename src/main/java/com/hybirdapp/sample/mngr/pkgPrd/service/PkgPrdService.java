package com.hybirdapp.sample.mngr.pkgPrd.service;

import java.util.HashMap;
import java.util.List;

import com.hybirdapp.sample.cmmn.DataClass;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public interface PkgPrdService {
	
	/**
	 * 패키지 상품 목록 건수
	 * 
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int searchPkgPrdListCnt(DataClass params) throws Exception;
	
	/**
	 * 패키지 상품 목록 조회
	 * 
	 * @param DataClass
	 * @return List
	 * @throws Exception
	 */
	public List searchPkgPrdList(DataClass params) throws Exception;
	
	/**
	 * 패키지 상품 단건 조회
	 * 
	 * @param DataClass
	 * @return HashMap
	 * @throws Exception
	 */
	public HashMap searchPkgPrd(DataClass params) throws Exception;
	
	/**
	 * 패키지 상품 등록
	 * 
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int registPkgPrd(DataClass params) throws Exception;
	String registPkgPrd(MultipartHttpServletRequest mtfRequest, HttpServletRequest request) throws Exception;
	/**
	 * 패키지 상품 수정
	 * 
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int modifyPkgPrd(DataClass params) throws Exception;
	void viewPkgPrdForModify(HttpServletRequest request, Model model) throws Exception;
	String modifyPkgPrd(MultipartHttpServletRequest mtfRequest, HttpServletRequest request) throws Exception;
	String deletePkgPrd(HttpServletRequest request, Model model) throws Exception;
	/**
	 * 패키지 상품 삭제
	 * 
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
//	public int deletePkgPrd(DataClass params) throws Exception;
}
