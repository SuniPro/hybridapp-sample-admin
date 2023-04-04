// 단일상품티켓선택
var max_append1 = 5;
var min_append1 = 1;
var cnt_append1 = 0;

function fn_add_btn1() {
	cnt_append1++;
	if (cnt_append1 > max_append1) {
		cnt_append1 = max_append1;
		return;
	}
	let id_count_str1 = '__' + cnt_append1;
	let html = `
			<tr class="data-row-cnt1${id_count_str1}">
				<th scope="row">시설선택</th>
				<td>
					<div class="form-inline">
						<select name="el__fclt_class${id_count_str1}" id="fclt_class${id_count_str1}" class="arrow" style="width:39%;">
							<option value="" class="select_first">시설구분을 선택하여 주세요.</option>
						</select>
						<select name="el__fclt_list${id_count_str1}" id="fclt_list${id_count_str1}" class="arrow" style="width:60%;">
							<option value="" class="select_first">시설을 선택하여 주세요.</option>
						</select>
					</div>
				</td>
				<td valign="top">
					<button type="button" id="add1_btn${id_count_str1}" onclick="fn_add_btn1()" style="margin:2px; float:right; padding:0.3rem">추가</button>
					<button type="button" id="remove1_btn${id_count_str1}" onclick="remove_btn1(this, '${id_count_str1}')" style="margin:2px; float:right; padding:0.3rem">삭제</button>
				</td>
			</tr>
			<tr class="data-row-cnt1${id_count_str1}">
				<th scope="row">상품그룹선택</th>
				<td colspan="2">
					<div class="form-inline">
						<select name="el__grDesc${id_count_str1}" id="grDesc${id_count_str1}" class="arrow" style="width:60%;">
							<option value="" class="select_first">상품그룹을 선택하여 주세요.</option>
						</select>
					</div>
				</td>
			</tr>
			<tr class="data-row-cnt1${id_count_str1}">
				<th scope="row">단일상품선택</th>
				<td colspan="2">
					<div class="form-inline">
						<select name="el__popMenuUnitNm${id_count_str1}" id="popMenuUnitNm${id_count_str1}" class="arrow" style="width:60%;">
							<option value="" class="select_first">단일상품을 선택하여 주세요.</option>
						</select>
					</div>
				</td>
			</tr>
			<tr class="data-row-cnt1${id_count_str1}">
				<th scope="row">단일상품티켓선택</th>
				<td colspan="2">
					<div class="form-inline">
						<select name="el__unitProdTicket${id_count_str1}" id="unitProdTicket${id_count_str1}" class="arrow" style="width:60%;">
							<option value="" class="select_first">단일상품티켓을 선택하여 주세요.</option>
						</select>
					</div>
				</td>
			</tr>
	`;
	$('#list1').append(html);

	/* 시설구분 조회 */
	popclGrp('FCLT_CLASS', '');

	// $("#fclt_class" + id_count_str1).trigger("change");

	/* 시설 목록 조회 */
	$("[id^='fclt_class']").on("change", function () {
		popclCdList($(this).val());
	});

	$("[id^='fclt_list']").on("change", function () {
		popGrpList(); // 상품그룹
	});

	/* 단일상품명 조회 */
	$("[id^='grDesc']").on("change", function () {
		popMenuUnitList();
	});

	$("[id^='popMenuUnitNm']").on('change', function () {
		popUnitProdTicket();
	});

	$("[id^='unitProdTicket']").on('change', function () {
		unitProdTicketOnChange("unitProdTicket" + '__' + cnt_append1 + " option:selected");
	});
}

if (viewType === 'I') fn_add_btn1();

function remove_btn1(o, num) {
	if (cnt_append1 <= min_append1) {
		cnt_append1 = min_append1;
		return;
	}
	else {
		if (confirm('삭제하시겠습니까?')) {
			let mMenu = $("#mLoc").data('code');
			let formData = new FormData();
			formData.set("m_menu", mMenu);
			let val = "[class^='data-row-cnt1" + num + "']";
			$(val).remove();
			cnt_append1--;
			/*$.ajax({
				cache: false, url: "/mngr/tckSng/delTicket.ajax", processData: false, contentType: false, type: 'POST', data: formData, success: function (data) {
					$('#row' + num).remove();
					cnt_append2--;
				}, // success
				error: function (xhr, status) {
					alert(xhr + " : " + status);
				}
			})*/
		}
		else {
			return;
		}
	}
}

function submit_this() {
	let form = $("#pkgproticket")[0];
	let formData = new FormData(form);
	let gr_code = $("#grDesc__1 option:checked").data('code');
	/*let grCode = $("#grDesc option:checked").data('code');
	let menu_unit_seq = $("#popMenuUnitNm option:checked").data('unit-seq');
	let mMenu = $("#mLoc").data('code');
	let fclt_class = $("#fclt_class").val();*/

	formData.set('total_el_cnt', cnt_append1);
	formData.set('viewType', viewType);
	formData.set('gr_code', gr_code);
	formData.set('mp_ticket_seq', mp_ticket_seq);
	formData.set('m_menu', pm_menu);
	/*formData.set('el__ticket_price_type__1', 'N');
	formData.set('el__ticket_price_type__2', 'A');
	formData.set('m_menu', mMenu);
	formData.set('fclt_class', fclt_class);
	formData.set('menu_unit_seq', menu_unit_seq);*/
	let action_url = '/mngr/tckPkg/savePkgTicket.ajax';
	if (viewType === 'U') {
		action_url = '/mngr/tckPkg/updatePkgTicket.ajax'
		formData.set('gr_code', update_gr_code);
	}
	if (confirm('저장하시겠습니까')) {
		$.ajax({
			cache: false, url: action_url, processData: false, contentType: false, type: 'POST', data: formData, success: function (data) {
				location.href = '/mngr/tckPkg/tckPkgView.do';
			}, // success
			error: function (xhr, status) {
				console.log(xhr + " : " + status);
			}
		});
	}
	return false;
}

function remove_pkg() {

	let action_url = '/mngr/tckPkg/removePkg.ajax';
	let form_data = new FormData();

	form_data.set('mp_ticket_seq', mp_ticket_seq);
	form_data.set('m_loc', m_loc);
	form_data.set('m_menu', m_menu);

	if (confirm('삭제하시겠습니까?')) {
		$.ajax({
			cache: false, url: action_url, processData: false, contentType: false, type: 'POST', data: form_data, success: function (data) {
				location.href = '/mngr/tckPkg/tckPkgView.do';
			}, // success
			error: function (xhr, status) {
				console.log(xhr + " : " + status);
			}
		});
	}
	return false;
}