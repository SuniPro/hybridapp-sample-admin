package com.hybirdapp.sample.mngr.gift.service;

import com.hybirdapp.sample.cmmn.DataClass;

import java.util.HashMap;
import java.util.List;

public interface GiftService {

	// 선물내역 목록
	List<HashMap<String, String>> searchGiftList(DataClass params) throws Exception;

	// 선물내역 목록 카운트
	int searchGiftListCnt(DataClass params) throws Exception;

	// 선물내역 Detail 팝업
	List<HashMap<String, String>> searchGift(DataClass params) throws Exception;

	// 티켓 리스트
	List<HashMap<String, String>> searchTicketList() throws Exception;

}
