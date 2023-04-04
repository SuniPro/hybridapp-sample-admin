package com.hybirdapp.sample.mngr.promotion.web;

import com.hybirdapp.sample.mngr.promotion.service.PromotionService;
import com.hybirdapp.sample.cmmn.DataClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/mngr/promotion")
public class PromotionController {

    public PromotionController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private final ServletContext servletContext;

    @Resource
    private PromotionService promotionService;

    @RequestMapping("/promotionView.do")
    public String promotionView(Model model, HttpServletRequest request) {
        return "mngr:/promotion/promotionView";
    }

    @RequestMapping("/promotionInsertView.do")
    public String promotionInsertView(Model model, HttpServletRequest request) {
        return "mngr:/promotion/promotionInsertView";
    }

    // 시설명 조회
    @RequestMapping("/searchClCdList.ajax")
    public String searchClCdList(Model model) throws Exception {
        model.addAttribute("searchClCdList", promotionService.searchClCdList());
        return "jsonView";
    }

    // 티켓 조회
    @RequestMapping("/searchFcltList.ajax")
    public String searchFcltList(Model model, HttpServletRequest request) throws Exception {
        DataClass param = new DataClass(request);

        model.addAttribute("searchFcltList", promotionService.searchFcltList(param));
        return "jsonView";
    }

    @RequestMapping(value = "/promotionPopup.do")
    public String promotionPopup(Model model, HttpServletRequest request) throws Exception {
        DataClass params = new DataClass(request);

        model.addAttribute("mParam", params.getMap());



        return "mngr/promotion/popup/promotionPopup";
    }

    @ResponseBody
    @RequestMapping("/promotionExcel.ajax")
    public List<Map<String, Object>> uploadEvent(@RequestParam("excelFile") MultipartFile file, Model model) throws IOException {
        List<Map<String, Object>> dataList;

        String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3

        if (extension.equals("xlsx")) {
            dataList = promotionService.readExcelFile(file);
        } else if (extension.equals("csv")) {
            dataList = promotionService.readCsvFile(file);
        } else {
            throw new IOException("확장자가 xlsx, csv인 파일만 업로드 해주세요.");
        }

        return dataList;
    }

    @RequestMapping("/downloadExample.ajax")
    public ResponseEntity<InputStreamResource> downloadExcel(@RequestParam String fileType) throws IOException {
        String filePath;
        MediaType mediaType;
        if (fileType.equals("xlsx")) {
            filePath = "/WEB-INF/excel/templete/promotion/excel_upload_sample.xlsx";
            mediaType = MediaType.parseMediaType("application/vnd.ms-excel");
        } else if (fileType.equals("csv")) {
            filePath = "/WEB-INF/csv/template/promotion/csv_upload_sample.csv";
            mediaType = MediaType.parseMediaType("text/csv");
        } else {
            throw new IllegalArgumentException("파일타입이 올바르지 않습니다.");
        }
        InputStream inputStream = servletContext.getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new FileNotFoundException("해당 경로에 파일이 없습니다. : " + filePath);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=myFile." + fileType);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(mediaType)
                .body(new InputStreamResource(inputStream));
    }
}
