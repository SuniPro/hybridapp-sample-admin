package com.hybirdapp.sample.mngr.fclMng.service;

import java.util.HashMap;
import java.util.List;

import com.hybirdapp.sample.cmmn.DataClass;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

public interface FclMngService {
	
	/**
	 * 시설 관리 목록 건수
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int searchFclMngListCnt(DataClass params) throws Exception;
	
	/**
	 * 시설 관리 목록 조회
	 * @param DataClass
	 * @return List
	 * @throws Exception
	 */
	public List searchFclMngList(DataClass params) throws Exception;
	
	/**
	 * 시설 관리 단건 조회
	 * @param DataClass
	 * @return HashMap
	 * @throws Exception
	 */
	public HashMap searchFclMng(DataClass params) throws Exception;
	
	/**
	 * 시설 관리 목록 등록
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int registFclMng(DataClass params) throws Exception;
	public String registFclMng(MultipartHttpServletRequest mtfRequest, HttpServletRequest request) throws Exception;
	/**
	 * 시설 관리 목록 수정
	 * @param DataClass
	 * @return List
	 * @throws Exception
	 */
	public int modifyFclMng(DataClass params) throws Exception;
	void viewFcltForModify(HttpServletRequest request, Model model) throws Exception;
	void viewFcltForOrder(HttpServletRequest request, Model model) throws Exception;
	void updateFcltOrder(HttpServletRequest request, Model model) throws Exception;
	String modifyFclMng(MultipartHttpServletRequest mtfRequest, HttpServletRequest request) throws Exception;

	/**
	 * 시설 관리 목록 삭제
	 * @param DataClass
	 * @return int
	 * @throws Exception
	 */
	public int deleteFclMng(DataClass params) throws Exception;
	public String deleteFclMng(HttpServletRequest request, Model model) throws Exception;
}
