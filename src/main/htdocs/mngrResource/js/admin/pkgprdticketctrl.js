var cart_array = [];

//패키지상품명 조회
function menuPkgNmPopup() {
	let param = {};

	mngrAjax("/mngr/tckPkg/searchMenuPkgNmList.ajax", param, false, null, fnSearchMenuPkgNmPopupCallBack);
}

//패키지상품 조회 - 콜백
function fnSearchMenuPkgNmPopupCallBack(data) {

	let resultList = data.result;

	if (nvl(resultList, '') !== '' && resultList.length > 0) {

		let body = $("#menuPkgNmPopup");
		let html = "";

		$(body).html("");

		html += '<option value="" class="select_first">전체</option>';
		for (let i = 0; i < resultList.length; i++) {
			html += '<option value="' + resultList[i].MENU_PKG_SEQ + '">' + resultList[i].MENU_PKG_NM + '</option>';
		}
		$(body).html(html);
		if (viewType === 'U') {
			$('#menuPkgNmPopup').val(menu_pkg_seq);
			$("#menuPkgNmPopup").prop('disabled', true); // 변경 불가
		}
	}
}

//패키지티켓분류 조회
function pkgTckGrpList() {
	let param = {};
	mngrAjax("/mngr/tckPkg/pkgTckGrpList.ajax", param, false, null, fnPkgTckGrpListCallBack);
}

//패키지티켓분류 조회 - 콜백
function fnPkgTckGrpListCallBack(data) {
	let resultList = data.result;

	if (nvl(resultList, '') !== '' && resultList.length > 0) {
		let body = $("#pkgTckGrpList");
		let html = '';
		html += '<option value="" class="select_first">전체</option>';
		for (let i = 0; i < resultList.length; i++) {
			html += '<option value="' + resultList[i].TICKET_GRP_SEQ + '">' + resultList[i].TICKET_GRP_NM + '</option>';
		}
		$(body).html(html);
		if (viewType === 'U') {
			$('#pkgTckGrpList').val(ticket_grp_seq);
			$("#pkgTckGrpList").prop('disabled', true);  // 변경 불가
		}
	}
}

/*

	$('input[name="m_ticket_qt"]').val('${result.PM_TICKET_QT}');
	$('#startDatePopup').val('${result.M_FROM_STR}');
	$('#endDatePopup').val('${result.M_TO_STR}');
	$('#pkgTckGrpList').val('${result.TICKET_GRP_SEQ}');
	$('#menuPkgNmPopup').val('${result.MENU_PKG_SEQ}');
*/

/*

	$('input[name="m_ticket_qt"]').val('${result.PM_TICKET_QT}');
	$('#startDatePopup').val('${result.M_FROM_STR}');
	$('#endDatePopup').val('${result.M_TO_STR}');
	$('#pkgTckGrpList').val('${result.TICKET_GRP_SEQ}');
	$('#menuPkgNmPopup').val('${result.MENU_PKG_SEQ}');
*/

////////////////////////////////////////

// 시설선택 >> 시설구분 조회
function popclGrp(clGrp, clGrpNm) {
	let param = {};

	if (nvl(clGrp, '') !== '') {
		param = {
			"clGrp": clGrp, "clGrpNm": clGrpNm
		};
	}
	mngrAjax("/mngr/common/searchClCdList.ajax", param, false, null, fnPopClGrpCallBack);
}

// 시설구분 조회 - 콜백
function fnPopClGrpCallBack(data) {

	var resultList = data.result;

	if (nvl(resultList, '') !== '' && resultList.length > 0) {

		var body = $("#fclt_class" + '__' + cnt_append1);
		var html = "";

		html += '<option value="" class="select_first">시설구분을 선택하여 주세요.</option>';

		for (var i = 0; i < resultList.length; i++) {
			html += '<option value="' + resultList[i].clCd + '">' + resultList[i].clNm + '</option>';
		}

		$(body).html(html);

		if ('${result.FCLT_CLASS}' !== '') {
			$("#fclt_class" + '__' + cnt_append1).val('${result.FCLT_CLASS}');
			// popclCdList($("#fclt_class" + '__' + cnt_append1).val());
			// $("#fclt_class").prop('disabled', true);
		}
	}
	else {
// 			fnSearchNoData($("#clGrpListTable"));
	}
}

 // 시설선택 >> 시설 조회
function popclCdList(clGrp) {
	let param = {};

	if (nvl(clGrp, '') !== '') {
		param = {"fclt_class": clGrp};
	}
	mngrAjax("/mngr/common/selectFcltList.ajax", param, false, null, fnPopClCdCallBack);
}

// 시설목록 조회 - 콜백
function fnPopClCdCallBack(data) {

	var resultList = data.resultList;
	selectBoxReset($("#grDesc" + '__' + cnt_append1));

	if (nvl(resultList, '') !== '' && resultList.length > 0) {

		var body = $("#fclt_list" + '__' + cnt_append1);
		var html = "";

		if ($('#fclt_class' + '__' + cnt_append1).val() === '') {
			selectBoxReset($("#fclt_list" + '__' + cnt_append1));
			selectBoxReset($("#popMenuUnitNm" + '__' + cnt_append1));
		}
		else {
			html += '<option value="" class="select_first">시설을 선택하여 주세요.</option>';
			for (var i = 0; i < resultList.length; i++) {
				html += '<option value="' + resultList[i].FCLT_SEQ + '">' + resultList[i].FCLT_NM + '</option>';
			}
			$(body).html(html);
		}
		//최초 1회만
		if ('${result.FCLT_SEQ}' !== '' && fcltListCnt === 0) {
			$("#fclt_list" + '__' + cnt_append1).val('${result.FCLT_SEQ}');
			// popGrpList($("#fclt_list" + '__' + cnt_append1).val());
			// $("#fclt_list").prop('disabled', true);
		}
	}
	else {
// 			fnSearchNoData($("#clGrpListTable"));
	}
}

 // 상품그룹선택 조회
function popGrpList() {
	let param = {
		"fcltClass": $("#fclt_class" + '__' + cnt_append1).val(), "fcltSeq": $("#fclt_list" + '__' + cnt_append1).val()
	};
	mngrAjax("/mngr/common/selectGrpList.ajax", param, false, null, fnPopSearchGrpCallBack);
}

 // 상품그룹선택 - 콜백
function fnPopSearchGrpCallBack(data) {

	var resultList = data.resultList;

	selectBoxReset($("#popMenuUnitNm" + '__' + cnt_append1));

	if (nvl(resultList, '') !== '' && resultList.length > 0) {

		var body = $("#grDesc" + '__' + cnt_append1);
		var html = "";

		$(body).html("");	//find 아래 html 삭제

		if ($('#fclt_list' + '__' + cnt_append1).val() === '') {
			selectBoxReset($("#grDesc" + '__' + cnt_append1));
		}
		else {
			html += '<option value="" class="select_first">상품그룹을 선택해 주세요.</option>';
			for (var i = 0; i < resultList.length; i++) {
				html += '<option value="' + resultList[i].GR_LOC + '" data-code="' + resultList[i].GR_CODE + '" id="grDesc' + '__' + cnt_append1 + resultList[i].GR_CODE + '">' + resultList[i].GR_DESC + '</option>';
			}
			$(body).html(html);
		}
		//최초 1회만
		if ('${result.GR_LOC}' !== '' && fcltListCnt === 0) {
			$("#grDesc" + '__' + cnt_append1 + grCode).prop("selected", true);
			// $("#grDesc").prop('disabled', true);
			popMenuUnitList();
		}
	}
	else {
	}
}

 // 단일상품선택
function popMenuUnitList() {
	let param = {};

	if (nvl($("#grDesc" + '__' + cnt_append1).val(), '') !== '') {
		param = {
			"gr_loc": $("#grDesc" + '__' + cnt_append1).val(), "gr_code": $("#grDesc" + '__' + cnt_append1 + " option:checked").data('code')
		};
	}
	mngrAjax("/mngr/tckSng/selectMenuUnitList.ajax", param, false, null, fnPopSearchSngPrdCallBack);
}

/*
var mLoc = $("#mLoc").val();
var mMenu = $("#mLoc").data('code');
*/

 // 단일상품선택 - callback
function fnPopSearchSngPrdCallBack(data) {

	var resultList = data.result;

	//find 아래 html 삭제
	if (nvl(resultList, '') !== '' && resultList.length > 0) {

		var body = $("#popMenuUnitNm" + '__' + cnt_append1);
		var html = "";
		$(body).html("");

		if ($('#grDesc' + '__' + cnt_append1).val() === '') {
			selectBoxReset($("#popMenuUnitNm" + '__' + cnt_append1));
		}
		else {
			html += '<option value="" class="select_first">단일상품을 선택해 주세요.</option>';
			for (var i = 0; i < resultList.length; i++) {
				html += '<option value="' + resultList[i].GR_LOC + '" data-code="' + resultList[i].GR_CODE + '" data-unit-seq="' + resultList[i].MENU_UNIT_SEQ + '" id="popMenuUnitNm' + '__' + cnt_append1 + resultList[i].GR_LOC + '">' + resultList[i].MENU_UNIT_NM + '</option>';
			}
			$(body).html(html);
		}
		//최초 1회만
		if ('${result.M_LOC}' !== '' && fcltListCnt === 0) {
			$("#popMenuUnitNm" + '__' + cnt_append1 + mLoc).prop("selected", true);
			// $("#popMenuUnitNm").prop('disabled', true);
		}
	}
	else {
	}
}

 // 단일상품티켓선택
function popUnitProdTicket() {
	let param = {};

	if (nvl($("#popMenuUnitNm" + '__' + cnt_append1).val(), '') !== '') {
		let popMenuUnitNm = $("#popMenuUnitNm" + '__' + cnt_append1 + " option:selected");
		param = {
			"fclt_seq": $("#fclt_list" + '__' + cnt_append1).val(), "menu_grp_seq": popMenuUnitNm.data('code'), "menu_unit_seq": popMenuUnitNm.data('unit-seq')
		};
	}
	mngrAjax("/mngr/tckSng/selectUnitProdTicket.ajax", param, false, null, fnpopUnitProdTicketCallBack);
}

// 단일상품티켓선택 - 콜백
function fnpopUnitProdTicketCallBack(data) {
	let resultList = data.result;

	if (nvl(resultList, '') !== '' && resultList.length > 0) {

		let body = $("#unitProdTicket" + '__' + cnt_append1);
		let html = "";
		html += '<option value="" class="select_first">단일상품티켓을 선택하여 주세요.</option>';

		for (let i = 0; i < resultList.length; i++) {
			html += '<option value="' + resultList[i].MU_TICKET_SEQ + '">' + resultList[i].MU_TICKET_NM + ' ' + resultList[i].MU_TICKET_SHORT_NM + '</option>';
		}
		$(body).html(html);

		//최초 1회만
		if ('${result.MU_TICKET_SEQ}' !== '' && fcltListCnt === 0) {
			$("#unitProdTicket" + '__' + cnt_append1).val('${result.MU_TICKET_SEQ}');
		}
	}
	else {
	}
}

function unitProdTicketOnChange(obj) {
	let a = $("#" + obj);
	try {
		cart_array.push({id: a.val(), text: a.text()});
	}
	catch (e) {
	}
}


////////////////////////////////////////

/*

/!* 상품 그룹 등록 *!/
function registMenuGrp() {
	var params;
	param = {
		"fclt_class": $("#fclt_class").val()
		, "fclt_list": $("#fclt_list").val()
		, "prd_name": $("#prd_name").val()
		, "prd_desc": $("#prd_desc").val()
		, "isrt_id": "${sessionScope.MngrInfo.mngrId}"
		, "updt_Id": "${sessionScope.MngrInfo.mngrId}"
	};
	if ($("#viewType").val() === "U") {
		mngrAjax("<c:out value='/mngr/prdMng/modifyMenuGrp.ajax' />", param, false, null, fnGoParent);
	}
	else {
		mngrAjax("<c:out value='/mngr/prdMng/registMenuGrp.ajax' />", param, false, null, fnGoParent);
	}
}

//등록 완료
function fnGoParent() {
	var resultText;
	if ($("#viewType").val() === "U") {
		resultText = "수정이 완료 되었습니다.";
	}
	else {
		resultText = "등록이 완료 되었습니다.";
	}
	var param = {}
	fnMngrAlt(resultText, "", function () {
		fnPopupCallParent(popupTargetId, popupParentFn, param);
	});
}

function fnSetDetail() {
	var checkkb = '${result.CD_AMT}';
	var checkcd = '${result.M_REF5}';
	$("#pop_prd_unit_name").val('${result.M_DESC}');
	$("#pop_prd_desc").val('${result.SL_CMT}');
}
*/

//팝업 닫기
function fnCardPopupClose() {
	fnPopupClose(popupTargetId);
}

function selectBoxReset(obj) {
	var body = obj;
	var html = '<option value="" class="select_first">전체</option>';
	$(body).html("");	//find 아래 html 삭제
	$(body).html(html);
}