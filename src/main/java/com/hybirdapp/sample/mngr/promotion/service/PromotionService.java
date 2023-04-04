package com.hybirdapp.sample.mngr.promotion.service;

import com.hybirdapp.sample.cmmn.DataClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PromotionService {
	int insertPromotion(PromotionVO vo) throws Exception;

	int insertPayment(String promotionNm) throws Exception;

	int insertPaymentDtl(String promotionNm, String ticketNm) throws Exception;

	int insertPaymentQrcode(String promotionNm) throws Exception;

	int insertItrevActivity(String promotionNm) throws Exception;

	int insertItqrTicketDtl(String promotionNm) throws Exception;

	PromotionVO selectPromotionTicket(String ticketNm) throws Exception;

	int insertGiftPromotion(PromotionVO vo) throws Exception;

	List<PromotionVO> selectGiftPromotion(String promotionNm) throws Exception;

	List<HashMap<String, String>> searchClCdList() throws Exception;

	List<HashMap<String, String>> searchFcltList(DataClass param) throws Exception;

	List<Map<String, Object>> readCsvFile(MultipartFile file) throws IOException;

	List<Map<String, Object>> readExcelFile(MultipartFile file) throws IOException;
}
