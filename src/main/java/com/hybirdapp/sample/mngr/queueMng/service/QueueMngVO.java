package com.hybirdapp.sample.mngr.queueMng.service;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class QueueMngVO implements Serializable {
	private String fcltSeq;
	private String fcltNm;
	private String DStartTm;
	private String DEndTm;
	private String EStartTm;
	private String EEndTm;
	/*-- 1: 오픈준비중, 2: 운행중, 3: 운행종료, 4: 정기정검, 5: 정기운휴*/
	private String status;
	private String waitingTm;
}
