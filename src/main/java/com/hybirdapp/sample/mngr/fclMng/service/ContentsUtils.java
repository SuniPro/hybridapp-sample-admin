package com.hybirdapp.sample.mngr.fclMng.service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentsUtils {
    /* 시설 등록요청 파라미터 */
    public static final String[] REGIST_PARAMS = {"fcltNm", "slCmt", "limitDesc", "fcltClass", "descIndex"};
    /* 시설 등록요청 파라미터 */
    public static final String[] MODIFY_PARAMS = {"fcltSeq", "fcltNm", "slCmt", "limitDesc", "fcltClass", "descIndex", "deleteMenuIndex"};
    /* 패키지상품 등록요청 파라미터 */
    public static final String[] REGIST_PKGPRD_PARAMS = {"fcltNm", "slCmt", "limitDesc", "muOnoffKb", "muAgentCd", "descIndex"};
    /* 패키지상품 수정요청 파라미터 */
    public static final String[] MODIFY_PKGPRD_PARAMS = {"fcltSeq", "fcltNm", "slCmt", "limitDesc", "muOnoffKb", "muAgentCd", "descIndex", "deleteMenuIndex"};
    /* 이미지 : 썸네일이미지 */
    public static final String THUMBNAIL_IMAGE = "thumbFile";
    /* 이미지 : 현장대기시간이미지 */
    public static final String WAITING_IMAGE = "waitingFile";
    /* 이미지 : 이미지 */
    public static final String SLIDE_IMAGE = "imageUpload";
    /* 이미지 : 상세메뉴 */
    public static final String MENU_IMAGE = "descFile";


    /* 등록요청 파라미터값 저장 */
    public static Map<String, String> setRegistParams(HttpServletRequest request, final String[] paramArray) {
        if (request == null) {
            return null;
        }

        Map<String, String> params = new HashMap<>();
        for (String param : paramArray) {
            System.out.println("param : " + param + " | " + request.getParameter(param));
            params.put(param, request.getParameter(param));
        }

        return params;
    }

    /* 시설관리 등록 유효성 체크 */
    public static String validateFcltRegist(HttpServletRequest request) {
        if (request == null) {
            return ResultCode.SYS_9999.getCode();
        }
        String value = null;
        /* 1. 시설명 */
        value = request.getParameter("fcltNm");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1000.getCode();
        }
        /* 2. 한줄소개 */
        value = request.getParameter("slCmt");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1001.getCode();
        }
        /* 3. 탑승조건/특이사항 */
        value = request.getParameter("limitDesc");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1002.getCode();
        }
        /* 4. 시설구분 */
        value = request.getParameter("fcltClass");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1003.getCode();
        }
        /* 5. 상세메뉴 */
        value = request.getParameter("descIndex");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1004.getCode();
        }
        String[] menuArray = value.split(",");
        for (String index : menuArray) {
            value = request.getParameter("inMenu" + index);
            if (value == null || value.trim().isEmpty()) {
                return ResultCode.SYS_1005.getCode();
            }
            value = request.getParameter("inDesc" + index);
            if (value == null || value.trim().isEmpty()) {
                return ResultCode.SYS_1006.getCode();
            }
        }

        return ResultCode.SYS_0000.getCode();
    }

    /* 시설관리 등록 유효성 체크 */
    public static String validateFcltModify(HttpServletRequest request) {
        if (request == null) {
            return ResultCode.SYS_9999.getCode();
        }
        String value = null;
        /* 0. 시설 시퀀스 */
        value = request.getParameter("fcltSeq");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_2000.getCode();
        }
        /* 1. 시설명 */
        value = request.getParameter("fcltNm");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1000.getCode();
        }
        /* 2. 한줄소개 */
        value = request.getParameter("slCmt");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1001.getCode();
        }
        /* 3. 탑승조건/특이사항 */
        value = request.getParameter("limitDesc");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1002.getCode();
        }
        /* 4. 시설구분 */
        value = request.getParameter("fcltClass");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1003.getCode();
        }
        /* 5. 상세메뉴 */
        value = request.getParameter("descIndex");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1004.getCode();
        }
        String[] menuArray = value.split(",");
        for (String index : menuArray) {
            value = request.getParameter("inMenu" + index);
            if (value == null || value.trim().isEmpty()) {
                return ResultCode.SYS_1005.getCode();
            }
            value = request.getParameter("inDesc" + index);
            if (value == null || value.trim().isEmpty()) {
                return ResultCode.SYS_1006.getCode();
            }
        }

        return ResultCode.SYS_0000.getCode();
    }

    /* 시설관리 등록 유효성 체크 */
    public static String validatePkgPrdRegist(HttpServletRequest request) {
        if (request == null) {
            return ResultCode.SYS_9999.getCode();
        }
        String value = null;
        /* 1. 시설명 */
        value = request.getParameter("fcltNm");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1000.getCode();
        }
        /* 2. 한줄소개 */
        value = request.getParameter("slCmt");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1001.getCode();
        }
        /* 3. 탑승조건/특이사항 */
        value = request.getParameter("limitDesc");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1002.getCode();
        }
        /* 4. 상품판매구분 */
        value = request.getParameter("muOnoffKb");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1010.getCode();
        }
        /* 5. 상품판매처 */
        value = request.getParameter("muAgentCd");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1011.getCode();
        }
        /* 6. 상세메뉴 */
        value = request.getParameter("descIndex");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1004.getCode();
        }
        String[] menuArray = value.split(",");
        for (String index : menuArray) {
            value = request.getParameter("inMenu" + index);
            if (value == null || value.trim().isEmpty()) {
                return ResultCode.SYS_1005.getCode();
            }
            value = request.getParameter("inDesc" + index);
            if (value == null || value.trim().isEmpty()) {
                return ResultCode.SYS_1006.getCode();
            }
        }

        return ResultCode.SYS_0000.getCode();
    }

    /* 시설관리 등록 유효성 체크 */
    public static String validatePkgPrdModify(HttpServletRequest request) {
        if (request == null) {
            return ResultCode.SYS_9999.getCode();
        }
        String value = null;
        /* 0. 시설 시퀀스 */
        value = request.getParameter("fcltSeq");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_3000.getCode();
        }
        /* 1. 시설명 */
        value = request.getParameter("fcltNm");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1000.getCode();
        }
        /* 2. 한줄소개 */
        value = request.getParameter("slCmt");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1001.getCode();
        }
        /* 3. 탑승조건/특이사항 */
        value = request.getParameter("limitDesc");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1002.getCode();
        }
        /* 4. 상품판매구분 */
//        value = request.getParameter("muOnoffKb");
//        if (value == null || value.trim().isEmpty()) {
//            return ResultCode.SYS_1010.getCode();
//        }
        /* 5. 상품판매처 */
//        value = request.getParameter("muAgentCd");
//        if (value == null || value.trim().isEmpty()) {
//            return ResultCode.SYS_1011.getCode();
//        }
        /* 6. 상세메뉴 */
        value = request.getParameter("descIndex");
        if (value == null || value.trim().isEmpty()) {
            return ResultCode.SYS_1004.getCode();
        }
        String[] menuArray = value.split(",");
        for (String index : menuArray) {
            value = request.getParameter("inMenu" + index);
            if (value == null || value.trim().isEmpty()) {
                return ResultCode.SYS_1005.getCode();
            }
            value = request.getParameter("inDesc" + index);
            if (value == null || value.trim().isEmpty()) {
                return ResultCode.SYS_1006.getCode();
            }
        }

        return ResultCode.SYS_0000.getCode();
    }

    /* 등록시 파일 체크 */
    public static String validateFileRegist(MultipartHttpServletRequest mtfRequest) {
        if (mtfRequest == null) {
            return ResultCode.SYS_9999.getCode();
        }
        /* 1. 썸네일이미지 */
        MultipartFile thumbFile = getSingleUploadFile(mtfRequest, THUMBNAIL_IMAGE);
        if (thumbFile == null) {
            return ResultCode.SYS_1007.getCode();
        }
        /* 2. 현장대기시간이미지 */
        MultipartFile waitingFile = getSingleUploadFile(mtfRequest, WAITING_IMAGE);
        if (waitingFile == null) {
            return ResultCode.SYS_1008.getCode();
        }
        /* 3. 이미지 */
        List<MultipartFile> listFile = getMultiUploadFile(mtfRequest, SLIDE_IMAGE);
        if (listFile == null || listFile.size() == 0) {
            return ResultCode.SYS_1009.getCode();
        }

        return ResultCode.SYS_0000.getCode();
    }

    /* 다중업로드 파일 추출 */
    public static List<MultipartFile> getMultiUploadFile(MultipartHttpServletRequest mtfRequest, final String key) {
        if (mtfRequest == null) {
            return null;
        }
        if (mtfRequest.getFiles(key) == null || mtfRequest.getFiles(key).size() == 0) {
            return null;
        }
        List<MultipartFile> fileList = new ArrayList<>();
        for (MultipartFile multipartFile : mtfRequest.getFiles(key)) {
            if (multipartFile.getOriginalFilename() == null || multipartFile.getOriginalFilename().trim().isEmpty()) {
                continue;
            }
            if (multipartFile.getSize() <= 0) {
                continue;
            }

            fileList.add(multipartFile);
        }

        return fileList;
    }

    /* 단일 업로드 파일 추출 */
    public static MultipartFile getSingleUploadFile(MultipartHttpServletRequest mtfRequest, final String key) {
        if (mtfRequest == null) {
            return null;
        }
        if (mtfRequest.getFile(key) == null) {
            return null;
        }
        MultipartFile multipartFile = mtfRequest.getFile(key);
        if (multipartFile.getOriginalFilename() == null || multipartFile.getOriginalFilename().trim().isEmpty()) {
            return null;
        }
        if (multipartFile.getSize() <= 0) {
            return null;
        }

        return multipartFile;
    }

    public static EgovMap listMemberAttributes(HttpServletRequest request) {
        if (request == null) {
            return null;
        }

        HttpSession session = request.getSession();
        Object sessionObj = session.getAttribute("MngrInfo");
        EgovMap memberInfo = null;
        if (sessionObj != null) {
            memberInfo = (EgovMap) sessionObj;
        }

        return memberInfo;
    }

    /**
     * Session에서 사용자 정보중 속성값 추출
     * authCd, mngrId, mngrNm, mngrJob, mngrGrad
     * @param request
     * @param key
     * @return
     */
    public static String getMemberAttribute(HttpServletRequest request, final String key) {
        EgovMap memberInfo = listMemberAttributes(request);
        if (memberInfo == null) {
            return null;
        }

        String value = null;
        if (memberInfo.containsKey(key)) {
            value = String.valueOf(memberInfo.get(key));
            if (value == null || "".equals(value) || "null".equals(value)) {
                value = null;
            }
        }

        return value;
    }

    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
