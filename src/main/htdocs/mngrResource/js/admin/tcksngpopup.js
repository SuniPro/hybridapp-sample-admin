var add_btn = $('#add_btn');
var max_append = 4;
var min_append = 2;
var cnt_append = 0;

add_btn.click(function () {
	cnt_append++;
	if (cnt_append > max_append) {
		cnt_append = max_append;
		return;
	}
	let id_count_str = '__' + cnt_append;
	let html = `
		<tr id="row${id_count_str}" class="data-row-cnt">
			<th scope="row">티켓</th>
			<td style="border:2px solid black;">
				<div class="form-inline divbox" style="width:220px;">
					<div class="divboxA">
						<span style="float:left; margin-right:10px">티켓구분</span>
						<select id="ticket_price_type${id_count_str}" name="el__ticket_price_type${id_count_str}" class="arrow" style="width:70%;">
							<option value="N">정상가</option>
							<option value="A">App할인가</option>
							<option value="G">콘도회원_Green</option>
							<option value="R">콘도회원_Red</option>
						</select>
					</div>
					<div class="divboxA">
						<span>판매가격</span>
						<input type="number" name="el__m_amount${id_count_str}" onchange="m_amount_calc(this, '${id_count_str}')" style="width:70%; margin-left: 5px;">
					</div>
				</div>
		
				<div class="form-inline divbox" style="width:210px;">
					<div class="divboxA">
						<span class="spanbox">티켓타입</span>
						<div class="divboxB">
							<input type="radio" id="m_typeM${id_count_str}" name="el__m_type${id_count_str}" value="M" checked>
							<label for="m_typeM${id_count_str}">기본</label>
						</div>
						<div style="float:left;">
							<input type="radio" id="m_typeA${id_count_str}" name="el__m_type${id_count_str}" value="A">
							<label for="m_typeA${id_count_str}">패키지</label>
						</div>
					</div>
		
					<div class="divboxA">
						<span class="spanbox">부가세</span>
						<div class="divboxB">
							<input type="radio" id="m_vat1${id_count_str}" onclick="m_amount_recalc(this, '${id_count_str}')" name="el__m_vat${id_count_str}" value="1" checked>
							<label for="m_vat1${id_count_str}">VAT</label>
						</div>
						<div style="float:left;">
							<input type="radio" id="m_vat0${id_count_str}" onclick="tax_free('m_tax_amount', '${id_count_str}')" name="el__m_vat${id_count_str}" value="0">
							<label for="m_vat0${id_count_str}">면세</label>
						</div>
					</div>
		
					<div class="divboxA">
						<span class="spanbox">개소세</span>
						<div class="divboxB">
							<input type="radio" id="m_tax1Y${id_count_str}" name="el__m_tax1${id_count_str}" onclick="m_tax(this, '${id_count_str}')" value="1">
							<label for="m_tax1Y${id_count_str}">Y</label>
						</div>
						<div style="float:left;">
							<input type="radio" id="m_tax1N${id_count_str}" name="el__m_tax1${id_count_str}" onclick="m_tax(this, '${id_count_str}')" value="0" checked>
							<label for="m_tax1N${id_count_str}">N</label>
						</div>
					</div>
		
					<div class="divboxA">
						<span class="spanbox">교육세</span>
						<div class="divboxB">
							<input type="radio" id="m_tax2Y${id_count_str}" name="el__m_tax2${id_count_str}" onclick="m_tax(this, '${id_count_str}')" value="1">
							<label for="m_tax2Y${id_count_str}">Y</label>
						</div>
						<div style="float:left;">
							<input type="radio" id="m_tax2N${id_count_str}" name="el__m_tax2${id_count_str}" onclick="m_tax(this, '${id_count_str}')" value="0" checked>
							<label for="m_tax2N${id_count_str}">N</label>
						</div>
					</div>
		
					<div class="divboxA">
						<span class="spanbox">농특세</span>
						<div class="divboxB">
							<input type="radio" id="m_tax3Y${id_count_str}" name="el__m_tax3${id_count_str}" onclick="m_tax(this, '${id_count_str}')" value="1">
							<label for="m_tax3Y${id_count_str}">Y</label>
						</div>
						<div style="float:left;">
							<input type="radio" id="m_tax3N${id_count_str}" name="el__m_tax3${id_count_str}" onclick="m_tax(this, '${id_count_str}')" value="0" checked>
							<label for="m_tax3N${id_count_str}">N</label>
						</div>
					</div>
		
					<div class="divboxA">
						<span class="spanbox">기금</span>
						<div class="divboxB">
							<input type="radio" id="m_tax4Y${id_count_str}" name="el__m_tax4${id_count_str}" onclick="m_tax(this, '${id_count_str}')" value="1">
							<label for="m_tax4Y${id_count_str}">Y</label>
						</div>
						<div style="float:left;">
							<input type="radio" id="m_tax4N${id_count_str}" name="el__m_tax4${id_count_str}" onclick="m_tax(this, '${id_count_str}')" value="0" checked>
							<label for="m_tax4N${id_count_str}">N</label>
						</div>
					</div>
				</div>
		
				<div class="form-inline divbox" style="width:210px;">
					<div class="divboxA">
						<span class="spanbox">판매여부</span>
						<div class="divboxB">
							<input type="radio" id="sale_yn_y${id_count_str}" name="el__sale_yn${id_count_str}" value="Y" checked>
							<label for="sale_yn_y${id_count_str}">판매</label>
						</div>
						<div style="float:left;">
							<input type="radio" id="sale_yn_n${id_count_str}" name="el__sale_yn${id_count_str}" value="N">
							<label for="sale_yn_n${id_count_str}">판매중지</label>
						</div>
					</div>
					<div class="divboxA">
						<span class="spanbox">비율</span>
						<div class="divboxB">
							<input type="text" name="el__m_tax_pct${id_count_str}" value="10%" readonly>
						</div>
						<div class="divboxB">
							<input type="number" id="m_tax_amount${id_count_str}"  value="0" name="el__m_tax_amount${id_count_str}">
						</div>
					</div>
		
					<div class="divboxA">
						<span class="spanbox">금액</span>
						<div class="divboxB">
							<input type="number" name="el__m_tax1_amount${id_count_str}" value="0" readonly>
						</div>
					</div>
		
					<div class="divboxA">
						<span class="spanbox">금액</span>
						<div class="divboxB">
							<input type="number" name="el__m_tax2_amount${id_count_str}" value="0" readonly>
						</div>
					</div>
		
					<div class="divboxA">
						<span class="spanbox">금액</span>
						<div class="divboxB">
							<input type="number" name="el__m_tax3_amount${id_count_str}" value="0" readonly>
						</div>
					</div>
		
					<div class="divboxA">
						<span class="spanbox">금액</span>
						<div class="divboxB">
							<input type="number" name="el__m_tax4_amount${id_count_str}" value="0" readonly>
						</div>
					</div>
				</div>
			</td>
			<td style="vertical-align: top" >
				<button type="button" name="remove_btn[]" id="remove_btn${id_count_str}" onclick="remove_btn(this, '${id_count_str}')" style="margin:5px; float:right;">삭제</button>	
			</td>
		</tr>
		`;
	$('#list').append(html);
});

// 신규등록 시 2개 default 추가
if ($("#viewType").val() === 'I') {
	add_btn.trigger('click');
	add_btn.trigger('click');
}
else { // 'U'
	cnt_append = $(".data-row-cnt").length;
}

$('#ticket_price_type__1').prop("disabled", true);
$('#ticket_price_type__1').val('N');
$('#ticket_price_type__2').prop("disabled", true);
$('#ticket_price_type__2').val('A');

$('#remove_btn__1').remove();
$('#remove_btn__2').remove();

function remove_btn(o, num) {
	if (cnt_append <= min_append) {
		cnt_append = min_append;
		return;
	}
	else if (num === '__1' || num === '__2') {
		return;
	}
	else {
		if (confirm('삭제하면 복구되지 않습니다. 삭제하시겠습니까?')) {
			let mMenu = $("#mLoc").data('code'); // `U`
			let formData = new FormData();
			formData.set("m_menu", mMenu);
			$.ajax({
				cache: false
				, url: "/mngr/tckSng/delTicket.ajax"
				, processData: false
				, contentType: false
				, type: 'POST'
				, data: formData
				, success: function (data) {
					$('#row' + num).remove();
					cnt_append--;
				}, // success
				error: function (xhr, status) {
					alert(xhr + " : " + status);
				}
			})
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
}

function tax_free(id, num) {
	let obj = $('#' + id + num);
	obj.val(0);
}

function m_amount_recalc(o, num) {
	m_amount_calc(o, num);
}

function submit_this() {

	$('#fclt_count').prop('disabled', false);

	let form = $("#tcksngpopup")[0];
	let formData = new FormData(form);
	let grCode = $("#grDesc option:checked").data('code');
	let menu_unit_seq = $("#popMenuUnitNm option:checked").data('unit-seq');
	let agent_cd = $("#popMenuUnitNm option:checked").data('agent-cd');
	let fclt_class = $("#fclt_class").val();

	formData.set('total_el_cnt', cnt_append);
	formData.set('el__ticket_price_type__1', 'N');
	formData.set('el__ticket_price_type__2', 'A');
	formData.set('gr_code', grCode);
	formData.set('fclt_class', fclt_class);
	formData.set('menu_unit_seq', menu_unit_seq);
	formData.set('agent_cd', agent_cd);
	formData.set('fclt_seq', $('#fclt_list').val());
	formData.set('fclt_list', $('#fclt_list').val());
	formData.set('viewType', $("#viewType").val());
	if (confirm('저장하시겠습니까?')) {
		$.ajax({
			cache: false
			, url: "/mngr/tckSng/saveTckSng.ajax"
			, processData: false
			, contentType: false
			, type: 'POST'
			, data: formData
			, success: function (data) {
				location.href = '/mngr/tckSng/tckSngView.do';
			}, // success
			error: function (xhr, status) {
			}
		});
	}
	return false;
}