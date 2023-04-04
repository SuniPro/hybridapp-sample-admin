package com.hybirdapp.sample.mngr.gift.web;

import com.hybirdapp.sample.mngr.gift.service.GiftService;
import com.hybirdapp.sample.mngr.gift.service.GiftVO;
import com.hybirdapp.sample.cmmn.DataClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/mngr/gift")
public class giftController {

    @Resource(name = "GiftService")
    private GiftService giftService;

    /***
     * 선물내역
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/giftView.do")
    public String giftView(Model model, HttpServletRequest request) throws Exception {
        DataClass params = new DataClass(request);

        model.addAttribute("mParam", params.getMap());

        return "mngr:/gift/giftView";
    }

    /***
     * 선물내역 목록 Ajax
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/searchGiftList.ajax")
    public String searchGiftList(@ModelAttribute("GiftVO") GiftVO vo, Model model, HttpServletRequest request) throws Exception {
        DataClass params = new DataClass(request);

        /* ===========[ 페이징 필수값 ]=================== */
        params.set("currPageIdx",  vo.getCurrPageIdx());
        params.set("offsetIndex",  vo.getOffsetIndex());
        params.set("pageMemory",  vo.getPageMemory());
        params.set("pageSize",  vo.getPageSize());
        params.set("pageBottomSize",  vo.getPageBottomSize());
        /* ============================================== */

        /* 총 건수 */
        log.info("searchGiftListCnt Start");
        int totPageCnt = giftService.searchGiftListCnt(params);
        vo.setTotPageCnt(totPageCnt);				//페이징
        model.addAttribute("totalCnt", totPageCnt);
        log.info("searchGiftListCnt End");

        /* 조회 */
        log.info("searchGiftList Start");
        model.addAttribute("result", giftService.searchGiftList(params));
        log.info("searchGiftList End");

        /* 페이징 처리 */
        model.addAttribute("pages", vo);

        return "jsonView";
    }

    /**
     * 검색조건을 위한 티켓 조회
     * @param
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/searchTicketList.ajax")
    public String searchTicketList(Model model) throws Exception {

        model.addAttribute("searchTicketList", giftService.searchTicketList());

        return "jsonView";
    }

    /***
     * 선물내역(팝업)
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/giftPopup.do")
    public String giftPopup(Model model, HttpServletRequest request) throws Exception {
        DataClass params = new DataClass(request);

        model.addAttribute("mParam", params.getMap());

        String tkQrcode = params.get("tkQrcode");

        /* 조회 */
        if(!"".equals(tkQrcode) && tkQrcode != "" && !tkQrcode.isEmpty()) {
            model.addAttribute("result", giftService.searchGift(params));
        }

        return "mngr/gift/popup/giftPopup";
    }

}
