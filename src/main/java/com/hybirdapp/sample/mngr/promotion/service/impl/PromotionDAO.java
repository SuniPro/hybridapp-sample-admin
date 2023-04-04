package com.hybirdapp.sample.mngr.promotion.service.impl;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import com.hybirdapp.sample.mngr.promotion.service.PromotionVO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@OracleMapper("PromotionDAO")
public interface PromotionDAO {

	int insertPromotion(PromotionVO vo) throws Exception;

	int insertPayment(String promotionNm) throws Exception;

	int insertPaymentDtl(@Param("promotionNm") String promotionNm, @Param("ticketNm") String ticketNm) throws Exception;

	int insertPaymentQrcode(String promotionNm) throws Exception;

	int insertItrevActivity(String promotionNm) throws Exception;

	int insertItqrTicketDtl(String promotionNm) throws Exception;

	PromotionVO selectPromotionTicket(String ticketNm) throws Exception;

	int insertGiftPromotion(PromotionVO vo) throws Exception;

	List<PromotionVO> selectGiftPromotion(String promotionNm) throws Exception;

	List<HashMap<String, String>> searchClCdList() throws Exception;

	List<HashMap<String, String>> searchFcltList(HashMap param) throws Exception;
}
