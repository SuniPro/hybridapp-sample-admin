package com.hybirdapp.sample.mngr.gift.service;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GiftVO extends CmmnMngrVO {

    /* ================[팝업 호출 필수값 ]================ */

    private String gift;

    public String getGift() { return gift; }

    public void setGift(String gift) {
        this.gift = gift;
    }

    /* ================[팝업 호출 필수값 End]================ */

}
