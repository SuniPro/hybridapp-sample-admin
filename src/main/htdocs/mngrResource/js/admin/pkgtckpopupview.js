// 티켓 설정
var add2_btn = $('#add2_btn');
var max_append2 = 4;
var min_append2 = 2;
var cnt_append2 = 0;

add2_btn.click(function () {

	cnt_append2++;
	if (cnt_append2 > max_append2) {
		cnt_append2 = max_append2;
		return;
	}
	let id_count_str2 = '__' + cnt_append2;
	let html1 = `
		<tr id="row${id_count_str2}" class="data-row-cnt2">
			<th scope="row">티켓</th>
			<td style="border:2px solid black;">
				<div class="form-inline divbox" style="width:220px;">
					<div class="divboxA">
						<span style="float:left; margin-right:10px">티켓구분</span>
						<select id="ticket_price_type${id_count_str2}" name="el__ticket_price_type${id_count_str2}" class="arrow" style="width:70%;">
							<option value="N">정상가</option>
							<option value="A">App할인가</option>
							<option value="G">콘도회원_Green</option>
							<option value="R">콘도회원_Red</option>
						</select>
					</div>
					<div class="divboxA">
						<span>판매가격</span>
						<input type="number" name="el__m_amount${id_count_str2}" onchange="m_amount_calc(this, '${id_count_str2}')" style="width:70%; margin-left: 5px;">
					</div>
				</div>
		
				<div class="form-inline divbox" style="width:210px;">
					<div class="divboxA">
						<span class="spanbox">티켓타입</span>
						<div class="divboxB">
							<input type="radio" id="m_typeM${id_count_str2}" name="el__m_type${id_count_str2}" value="M" checked>
							<label for="m_typeM${id_count_str2}">기본</label>
						</div>
						<div style="float:left;">
							<input type="radio" id="m_typeA${id_count_str2}" name="el__m_type${id_count_str2}" value="A">
							<label for="m_typeA${id_count_str2}">패키지</label>
						</div>
					</div>
		
					<div class="divboxA">
						<span class="spanbox">부가세</span>
						<div class="divboxB">
							<input type="radio" id="m_vat1${id_count_str2}" onclick="m_amount_recalc(this, '${id_count_str2}')" name="el__m_vat${id_count_str2}" value="1" checked>
							<label for="m_vat1${id_count_str2}">VAT</label>
						</div>
						<div style="float:left;">
							<input type="radio" id="m_vat0${id_count_str2}" onclick="tax_free('m_tax_amount', '${id_count_str2}')" name="el__m_vat${id_count_str2}" value="0">
							<label for="m_vat0${id_count_str2}">면세</label>
						</div>
					</div>
		
					<div class="divboxA">
						<span class="spanbox">개소세</span>
						<div class="divboxB">
							<input type="radio" id="m_tax1Y${id_count_str2}" name="el__m_tax1${id_count_str2}" onclick="m_tax(this, '${id_count_str2}')" value="1">
							<label for="m_tax1Y${id_count_str2}">Y</label>
						</div>
						<div style="float:left;">
							<input type="radio" id="m_tax1N${id_count_str2}" name="el__m_tax1${id_count_str2}" onclick="m_tax(this, '${id_count_str2}')" value="0" checked>
							<label for="m_tax1N${id_count_str2}">N</label>
						</div>
					</div>
		
					<div class="divboxA">
						<span class="spanbox">교육세</span>
						<div class="divboxB">
							<input type="radio" id="m_tax2Y${id_count_str2}" name="el__m_tax2${id_count_str2}" onclick="m_tax(this, '${id_count_str2}')" value="1">
							<label for="m_tax2Y${id_count_str2}">Y</label>
						</div>
						<div style="float:left;">
							<input type="radio" id="m_tax2N${id_count_str2}" name="el__m_tax2${id_count_str2}" onclick="m_tax(this, '${id_count_str2}')" value="0" checked>
							<label for="m_tax2N${id_count_str2}">N</label>
						</div>
					</div>
		
					<div class="divboxA">
						<span class="spanbox">농특세</span>
						<div class="divboxB">
							<input type="radio" id="m_tax3Y${id_count_str2}" name="el__m_tax3${id_count_str2}" onclick="m_tax(this, '${id_count_str2}')" value="1">
							<label for="m_tax3Y${id_count_str2}">Y</label>
						</div>
						<div style="float:left;">
							<input type="radio" id="m_tax3N${id_count_str2}" name="el__m_tax3${id_count_str2}" onclick="m_tax(this, '${id_count_str2}')" value="0" checked>
							<label for="m_tax3N${id_count_str2}">N</label>
						</div>
					</div>
		
					<div class="divboxA">
						<span class="spanbox">기금</span>
						<div class="divboxB">
							<input type="radio" id="m_tax4Y${id_count_str2}" name="el__m_tax4${id_count_str2}" onclick="m_tax(this, '${id_count_str2}')" value="1">
							<label for="m_tax4Y${id_count_str2}">Y</label>
						</div>
						<div style="float:left;">
							<input type="radio" id="m_tax4N${id_count_str2}" name="el__m_tax4${id_count_str2}" onclick="m_tax(this, '${id_count_str2}')" value="0" checked>
							<label for="m_tax4N${id_count_str2}">N</label>
						</div>
					</div>
				</div>
		
				<div class="form-inline divbox" style="width:210px;">
					<div class="divboxA">
						<span class="spanbox">판매여부</span>
						<div class="divboxB">
							<input type="radio" id="sale_yn_y${id_count_str2}" name="el__sale_yn${id_count_str2}" value="Y" checked>
							<label for="sale_yn_y${id_count_str2}">판매</label>
						</div>
						<div style="float:left;">
							<input type="radio" id="sale_yn_n${id_count_str2}" name="el__sale_yn${id_count_str2}" value="N">
							<label for="sale_yn_n${id_count_str2}">판매중지</label>
						</div>
					</div>
					<div class="divboxA">
						<span class="spanbox">비율</span>
						<div class="divboxB">
							<input type="text" name="el__m_tax_pct${id_count_str2}" value="10%" readonly>
						</div>
						<div class="divboxB">
							<input type="number" id="m_tax_amount${id_count_str2}"  value="0" name="el__m_tax_amount${id_count_str2}">
						</div>
					</div>
		
					<div class="divboxA">
						<span class="spanbox">금액</span>
						<div class="divboxB">
							<input type="number" name="el__m_tax1_amount${id_count_str2}" value="0" readonly>
						</div>
					</div>
		
					<div class="divboxA">
						<span class="spanbox">금액</span>
						<div class="divboxB">
							<input type="number" name="el__m_tax2_amount${id_count_str2}" value="0" readonly>
						</div>
					</div>
		
					<div class="divboxA">
						<span class="spanbox">금액</span>
						<div class="divboxB">
							<input type="number" name="el__m_tax3_amount${id_count_str2}" value="0" readonly>
						</div>
					</div>
					<div class="divboxA">
						<span class="spanbox">금액</span>
						<div class="divboxB">
							<input type="number" name="el__m_tax4_amount${id_count_str2}" value="0" readonly>
						</div>
					</div>
				</div>`;
	for (let i = 0; i < cart_array.length; i++) {
		if (cart_array[i].GRADESORT !== cnt_append2) continue;
		let calc_val = excel_math.roundup(cart_array[i].CALC * $('input[name="el__m_amount' + id_count_str2 + '"]').val(), 0);
		html1 += `
				<div class="form-inline divbox" style="width:100%;">
					<span style="float:left; margin-right:10px">단일상품티켓 : ${cart_array[i].TICKET_NM}</span>
					<span>매출분석용가격</span>
					<input type="text" name="el__m_revenue${id_count_str2}" data-calc-val="${cart_array[i].CALC}" value="${calc_val}" style="width:30%; margin-left: 5px;" readonly>
				</div>`;
	}
	let html2 = `
			</td>
			<td style="vertical-align: top" >
				<button type="button" name="remove2_btn[]" id="remove2_btn${id_count_str2}" onclick="remove_btn2(this, '${id_count_str2}', '', '')" style="margin:5px; float:right; padding:0.3rem">삭제</button>
			</td>
		</tr>
		`;
	$('#list3').append(html1 + html2);
});

// 신규등록 시 2개 default 추가
if (viewType === 'I') {
	add2_btn.trigger('click');
	add2_btn.trigger('click');
}
else { // 'U'
	cnt_append2 = $(".data-row-cnt2").length;
}

$('#ticket_price_type__1').prop("disabled", true);
$('#ticket_price_type__1').val('N');
$('#ticket_price_type__2').prop("disabled", true);
$('#ticket_price_type__2').val('A');

$('#remove2_btn__1').remove();
$('#remove2_btn__2').remove();

function remove_btn2(o, num, m_menu, m_loc) {
	if (cnt_append2 <= min_append2) {
		cnt_append2 = min_append2;
		return;
	}
	else if (num === '__1' || num === '__2') {
		return;
	}
	else {
		if (confirm('삭제하시겠습니까?')) {
			let form_data = new FormData();
			form_data.set("m_menu", m_menu);
			form_data.set("m_loc", m_loc);
			if (m_menu !== '') {
				$.ajax({
					cache: false, url: "/mngr/tckPkg/removePkgTicket.ajax", processData: false, contentType: false, type: 'POST', data: form_data, success: function (data) {
						$('#row' + num).remove();
						cnt_append2--;
					}, // success
					error: function (xhr, status) {
						alert(xhr + " : " + status);
					}
				})
			}
			else {
				$('#row' + num).remove();
				cnt_append2--;
			}
		}
		else return;
	}
}

function m_tax(o, num) {
	let obj = $(o);
	const name = obj.attr('name').split('__');
	let amount_name = name[1] + '_amount' + num;
	let sel_amount_name = $("input[name='el__" + amount_name + "']");
	if ($(o).val() === '1') {
		sel_amount_name.attr('readonly', false);
	}
	else {
		sel_amount_name.val('');
		sel_amount_name.attr('readonly', true);
	}
}

function m_amount_calc(o, num) {
	let obj = $(o);
	let vat = 0; // default 면세
	let amount = obj.val(); // 판매가격
	if ($('#m_vat1' + num).is(':checked')) { // 부가세 대상
		vat = amount - excel_math.roundup(amount / 1.1, 0); // 판매가격 - 공급가격
	}
	$('#m_tax_amount' + num).val(vat);
	m_revenue_calc(amount, num);
}

function tax_free(id, num) {
	let obj = $('#' + id + num);
	obj.val(0);
}

function m_amount_recalc(o, num) {
	m_amount_calc(o, num);
}

function m_revenue_calc(amount, num) {
	let obj = $('input[name="el__m_revenue' + num + '"]');
	let v = obj.data('calc-val');
	obj.val(excel_math.roundup(amount * v, 0));
}

function submit_pkgticketpopup(m_loc, mp_ticket_seq, gr_code) {
	let form = $("#pkgtckpopup")[0];
	let form_data = new FormData(form);
	let menu_unit_seq = $("#popMenuUnitNm option:checked").data('unit-seq');
	let mMenu = $("#mLoc").data('code');
	let fclt_class = $("#fclt_class").val();

	form_data.set('total_el_cnt', cnt_append2);
	form_data.set('el__ticket_price_type__1', 'N');
	form_data.set('el__ticket_price_type__2', 'A');
	form_data.set('gr_code', gr_code);
	form_data.set('m_loc', m_loc);
	form_data.set('m_menu', mMenu);
	form_data.set('fclt_class', fclt_class);
	form_data.set('menu_unit_seq', menu_unit_seq);
	form_data.set('viewType', viewType);
	form_data.set('mp_ticket_seq', mp_ticket_seq);

	if (confirm("저장하시겠습니까?")) {
		$.ajax({
			cache: false, url: "/mngr/tckPkg/savePkgUnitTicket.ajax", processData: false, contentType: false, type: 'POST', data: form_data, success: function (data) {
				location.href = "/mngr/tckPkg/tckPkgView.do";
			}, // success
			error: function (xhr, status) {
				alert(xhr + " : " + status);
			}
		});
	}
	return false;
}