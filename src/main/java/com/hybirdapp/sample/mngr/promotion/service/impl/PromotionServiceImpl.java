package com.hybirdapp.sample.mngr.promotion.service.impl;

import com.hybirdapp.sample.cmmn.DataClass;
import com.hybirdapp.sample.mngr.promotion.service.PromotionService;
import com.hybirdapp.sample.mngr.promotion.service.PromotionVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("PromotionService")
public class PromotionServiceImpl implements PromotionService {

	@Resource(name = "PromotionDAO")
	private PromotionDAO promotionDao;

	@Override
	public int insertPromotion(PromotionVO vo) throws Exception {
		return promotionDao.insertPromotion(vo);
	}

	@Override
	public int insertPayment(String promotionNm) throws Exception {
		return promotionDao.insertPayment(promotionNm);
	}

	@Override
	public int insertPaymentDtl(String promotionNm, String ticketNm) throws Exception {
		return promotionDao.insertPaymentDtl(promotionNm, ticketNm);
	}

	@Override
	public int insertPaymentQrcode(String promotionNm) throws Exception {
		return promotionDao.insertPaymentQrcode(promotionNm);
	}

	@Override
	public int insertItrevActivity(String promotionNm) throws Exception {
		return promotionDao.insertItrevActivity(promotionNm);
	}

	@Override
	public int insertItqrTicketDtl(String promotionNm) throws Exception {
		return promotionDao.insertItqrTicketDtl(promotionNm);
	}

	@Override
	public PromotionVO selectPromotionTicket(String ticketNm) throws Exception {
		return promotionDao.selectPromotionTicket(ticketNm);
	}

	@Override
	public int insertGiftPromotion(PromotionVO vo) throws Exception {
		vo.BcryptEncodeHandPhone2();
		return promotionDao.insertGiftPromotion(vo);
	}
	@Override
	public List<PromotionVO> selectGiftPromotion(String promotionNm) throws Exception {
		return promotionDao.selectGiftPromotion(promotionNm);
	}

	@Override
	public List<HashMap<String, String>> searchClCdList() throws Exception {
		return promotionDao.searchClCdList();
	}

	@Override
	public List<HashMap<String, String>> searchFcltList(DataClass param) throws Exception {
		return promotionDao.searchFcltList(param.getMap());
	}

	@Override
	public List<Map<String, Object>> readCsvFile(MultipartFile file) throws IOException {
		List<Map<String, Object>> dataList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
			String line;
			String[] headers = null;

			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");

				if (headers == null) {
					headers = new String[]{"name", "phoneNumber"};
				} else {
					Map<String, Object> map = new HashMap<>();
					for (int i = 0; i < headers.length; i++) {
						map.put(headers[i], values[i]);
					}
					dataList.add(map);
				}
			}
		}

		return dataList;
	}

	@Override
	public List<Map<String, Object>> readExcelFile(MultipartFile file) throws IOException {
		List<Map<String, Object>> dataList = new ArrayList<>();

		try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
			Sheet worksheet = workbook.getSheetAt(0);
			int physicalNumberOfRows = worksheet.getPhysicalNumberOfRows();

			if (physicalNumberOfRows > 10000) {
				Map<String, Object> data = new HashMap<>();
				data.put("alertMessage", "errorMessage");
				dataList.add(data);
				return dataList;
			}

			for (int i = 1; i < physicalNumberOfRows; i++) {
				Row row = worksheet.getRow(i);
				Map<String, Object> data = new HashMap<>();
				data.put("name", row.getCell(0).getStringCellValue());
				data.put("phoneNumber", row.getCell(1).getStringCellValue());
				dataList.add(data);
			}
		}

		return dataList;
	}

}
