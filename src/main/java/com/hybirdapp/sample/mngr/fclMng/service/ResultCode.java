package com.hybirdapp.sample.mngr.fclMng.service;

import lombok.Getter;

public enum ResultCode {
    SYS_0000("0000", "SUCCESS"),
    SYS_1000("1000", "시설명을 입력하여 주세요."),
    SYS_1001("1001", "App화면에 노출될 시설 한줄 소개를 입력하여 주세요."),
    SYS_1002("1002", "티켓 구매 메뉴에 노출될 탑승조건/특이사항을 입력하여 주세요.(ex 키 140cm 이상 / 36개월 이상)"),
    SYS_1003("1003", "시설구분을 등록해주세요."),
    SYS_1004("1004", "상세메뉴를 등록해주세요."),
    SYS_1005("1005", "상세메뉴명을 입력하여 주세요."),
    SYS_1006("1006", "상세정보를 입력하여 주세요."),
    SYS_1007("1007", "썸네일이미지 파일을 등록해주세요."),
    SYS_1008("1008", "현장대기시간이미지 파일을 등록해주세요."),
    SYS_1009("1009", "이미지 파일을 등록해주세요."),
    SYS_1010("1010", "상품판매구분을 등록해주세요."),
    SYS_1011("1011", "상품판매처를 등록해주세요."),

    
    SYS_2000("2000", "시설정보가 없습니다."),
    SYS_2001("2001", "썸네일이미지 정보가 없습니다."),
    SYS_2002("2002", "현장대기시간이미지 정보가 없습니다."),
    SYS_2003("2003", "이미지 정보가 없습니다."),
    SYS_2004("2004", "상세메뉴 정보가 없습니다."),

    SYS_3000("3000", "패키지상품 정보가 없습니다."),

    SYS_9999("9999", "요청정보가 없습니다.");

    @Getter
    private final String code;
    @Getter
    private final String msg;

    ResultCode(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMessage(String code) {
        if (code == null) {
            return "기타";
        }
        for (ResultCode resCode : values()) {
            if (resCode.getCode().equals(code)) {
                return resCode.getMsg();
            }
        }

        return "기타";
    }
}
