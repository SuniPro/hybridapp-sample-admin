package com.hybirdapp.sample.mngr.gift.service.impl;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;

import java.util.HashMap;
import java.util.List;

@OracleMapper("GiftDAO")
public interface GiftDAO {

    // 선물내역 목록
    List searchGiftList(HashMap params) throws Exception;

    // 선물내역 목록 카운트
    int searchGiftListCnt(HashMap params) throws Exception;

    // 선물내역 Detail 팝업
    List searchGift(HashMap params) throws Exception;

    // 티켓 리스트
    List searchTicketList() throws Exception;
}
