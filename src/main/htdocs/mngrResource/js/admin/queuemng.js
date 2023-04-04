let token = $("meta[name='_csrf']").attr("content");
let waiting_q_map = new Map();
let waiting_time_map = new Map();
axios.defaults.headers.common['X-CSRF-TOKEN'] = token;

$("button[name^=waiting]").click(function() {
	let code = $(this).attr('data-sertb-code');
	let name = $(this).attr('name');
	$("button[name^=waiting_" + code + "]").attr('class', 'grey');
	$(this).attr('class', 'grey on');
	const arr = name.split('_');
	waiting_q_map.set('waiting_' + code, arr[2]);

	/*
	for (let [key, value] of waiting_q_map) {
		console.log(`${key} = ${value}`);
	}
	*/
})

$("button[name^=status]").click(function() {
	let code = $(this).attr('data-sertb-code');
	let name = $(this).attr('name');
	$("button[name^=status_" + code + "]").attr('class', 'grey');
	$(this).attr('class', 'grey active');
	const arr = name.split('_');
	waiting_q_map.set('status_' + code, arr[2]);
})

function update_q_data() {
	axios.post('/mngr/queueMng/updateq.ajax', Object.fromEntries(waiting_q_map))
		 .then(function (response) {
			 alert('저장하였습니다.')
		 })
		 .catch(function (error) {
			 alert('실패했습니다.')
		 });
	return false;
}

function time_setting(fclt_name, fclt_seq, ds, de, es, ee) {

	waiting_time_map.set('idx', fclt_seq);
	$('#fclt_name').html(fclt_name);
	const arr_ds = ds.split(':');
	const arr_de = de.split(':');
	const arr_es = es.split(':');
	const arr_ee = ee.split(':');

	$('#ds_0').val(arr_ds[0]);
	$('#ds_1').val(arr_ds[1]);

	$('#de_0').val(arr_de[0]);
	$('#de_1').val(arr_de[1]);

	$('#es_0').val(arr_es[0]);
	$('#es_1').val(arr_es[1]);

	$('#ee_0').val(arr_ee[0]);
	$('#ee_1').val(arr_ee[1]);

	$('#modal').show();

}
function update_time_setting() {

	if (!valid_time_format($('#ds_0').val(), 0)) return false;
	if (!valid_time_format($('#ds_1').val(), 1)) return false;
	if (!valid_time_format($('#de_0').val(), 0)) return false;
	if (!valid_time_format($('#de_1').val(), 1)) return false;
	if (!valid_time_format($('#es_0').val(), 0)) return false;
	if (!valid_time_format($('#es_1').val(), 1)) return false;
	if (!valid_time_format($('#ee_0').val(), 0)) return false;
	if (!valid_time_format($('#ee_1').val(), 1)) return false;


	waiting_time_map.set('ds', make_tm_format($('#ds_0').val()) + ':' + make_tm_format($('#ds_1').val()) + ':00');
	waiting_time_map.set('de', make_tm_format($('#de_0').val()) + ':' + make_tm_format($('#de_1').val()) + ':00');
	waiting_time_map.set('es', make_tm_format($('#es_0').val()) + ':' + make_tm_format($('#es_1').val()) + ':00');
	waiting_time_map.set('ee', make_tm_format($('#ee_0').val()) + ':' + make_tm_format($('#ee_1').val()) + ':00');

	axios.post('/mngr/queueMng/updateqtm.ajax', Object.fromEntries(waiting_time_map))
		 .then(function (response) {
			 alert('저장하였습니다.')
		 })
		 .catch(function (error) {
			 alert('실패했습니다.')
		 });
	$('#modal').hide();
	location.href = '/mngr/queueMng/queueMngView.do';
}

function valid_time_format(v, type) {

	const str = $.trim(v);
	if ((str.length >= 1 && str.length <= 2) && !isNaN(str)) {
		if (type === 0 && (parseInt(v) >= 0 && parseInt(v) < 24)) {
			return true;
		}
		else if (type === 1 && (parseInt(v) >= 0 && parseInt(v) < 60)) {
			return true;
		}
	}
	alert('정확한 시간을 입력하세요.')
	return false;
}
function make_tm_format(v) {
	if (v.length === 1) {
		v = '0' + v;
	}
	return v;
}